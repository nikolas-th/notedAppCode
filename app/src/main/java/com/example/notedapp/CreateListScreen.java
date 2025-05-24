package com.example.notedapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;


public class CreateListScreen extends AppCompatActivity {
    private RecyclerView listItemRecycler;
    private CreateListItemAdapter listItemAdapter;

    private ReleaseList releaseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list_screen);

        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Επεξεργασία Λίστας"); // set title for activity top bar.

        listItemRecycler = findViewById(R.id.release_list_item_recycler);

        //Get extra info from intent
        int index = getIntent().getIntExtra("listIndex", -1);
        if (index != -1) {
            releaseList = DBmanager.getListsByUser("johndoe").get(index);
        }


        if (releaseList != null){
            //releaseList != empty, we're working on an existing list.
            //Populate fields with list data
            EditText listTitleInput = findViewById(R.id.list_title_input);
            EditText listDescriptionInput = findViewById(R.id.list_description_input);

            listTitleInput.setText(releaseList.getTitle());
            listDescriptionInput.setText(releaseList.getDescription());

            List<Release> releasesList = releaseList.getReleasesList();

            //Populate list RecyclerView
            LinearLayoutManager listRecyclerLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            listItemRecycler.setLayoutManager(listRecyclerLayoutManager);

            //Initialize adapter and write remove button method
            listItemAdapter = new CreateListItemAdapter(this, releasesList, position -> {
               releasesList.remove(position);
               listItemAdapter.notifyItemRemoved(position);
               listItemAdapter.notifyItemRangeChanged(position, releasesList.size());

            });

            listItemRecycler.setAdapter(listItemAdapter);

            //Drag and drop reordering behavior.
            ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(
                    ItemTouchHelper.UP | ItemTouchHelper.DOWN, 0) {

                @Override
                public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                      RecyclerView.ViewHolder target) {
                    int fromPosition = viewHolder.getAdapterPosition();
                    int toPosition = target.getAdapterPosition();
                    listItemAdapter.swapItems(fromPosition, toPosition);
                    return true;
                }

                @Override
                public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                    // No swipe support here
                }

                @Override
                public boolean isLongPressDragEnabled() {
                    return true; // or control this manually
                }
            };

            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
            itemTouchHelper.attachToRecyclerView(listItemRecycler);

            //Complete button.

        }
        else {
            //this is a new list, we're gonna make a new one.
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.complete_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_complete) {
            //Call method to handle completed ReleaseList.
            if (releaseList != null){
                handleListEdit();
            }

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void handleNewListCompletion() {
        String newTitle = ((EditText) findViewById(R.id.list_title_input)).getText().toString();
        String newDescription = ((EditText) findViewById(R.id.list_description_input)).getText().toString();

    }

    private void handleListEdit() {
        String newTitle = ((EditText) findViewById(R.id.list_title_input)).getText().toString();
        String newDescription = ((EditText) findViewById(R.id.list_description_input)).getText().toString();
        //Save new list details
        releaseList.setTitle(newTitle);
        releaseList.setDescription(newDescription);

        // Return to previous activity
        Intent resultIntent = new Intent();
        setResult(RESULT_OK, resultIntent); // set result code and data
        finish(); // close the activity
    }


}