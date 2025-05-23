package com.example.notedapp;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;


public class CreateListScreen extends AppCompatActivity {
    private RecyclerView listItemRecycler;
    private CreateListItemAdapter listItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list_screen);

        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        toolbar.setTitle("Επεξεργασία Λίστας"); // set title for activity top bar.

        listItemRecycler = findViewById(R.id.release_list_item_recycler);

        //Get extra info from intent
        ReleaseList releaseList = (ReleaseList) getIntent().getSerializableExtra("releaseListObject");

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
            listItemAdapter = new CreateListItemAdapter(this, releasesList, position -> { //Initialize adapter and write remove button method
               releasesList.remove(position);
               listItemAdapter.notifyItemRemoved(position);
               listItemAdapter.notifyItemRangeChanged(position, releasesList.size());

            });
            listItemRecycler.setAdapter(listItemAdapter);
        }
        else {
            //this is a new list, we're gonna make a new one.
        }



    }
}