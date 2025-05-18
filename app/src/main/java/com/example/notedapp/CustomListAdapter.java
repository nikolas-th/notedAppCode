package com.example.notedapp;

import android.content.Context;
import android.graphics.pdf.models.ListItem;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomListAdapter extends ArrayAdapter<String> {
    private final Context context;

    private final String[] values;
    private final int[] imageIds;
    private  final String[] ratingIds;
    private List<String> originalValues;
    private List<Integer> originalImageIds;
    private List<String> filteredValues;
    private List<Integer> filteredImageIds;
    private final TextView resSearch;

    public CustomListAdapter(Context context, String[] values, int[] imageIds, String[] ratingIds, TextView resSearch) {
        super(context, R.layout.list_item_with_icon, values);
        this.context = context;
        this.values = values;
        this.imageIds = imageIds;
        this.ratingIds = ratingIds;
        this.resSearch = resSearch;
        this.originalValues = Arrays.asList(values);
        this.originalImageIds = new ArrayList<>();
        for (int id : imageIds) originalImageIds.add(id);

        this.filteredValues = new ArrayList<>(originalValues);
        this.filteredImageIds = new ArrayList<>(originalImageIds);

    }


    @Override
    public int getCount() {
        return filteredValues.size(); // to plhthos twn kykloforiwn pou anazhthse o xrhsths
    }

    @Override
    public String getItem(int position) {
        return filteredValues.get(position); //evresh ths kykloforias poy anazhthse o xrhsths
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View rowView = inflater.inflate(R.layout.list_item_with_icon, parent, false);

        TextView textView = rowView.findViewById(R.id.itemTitle);
        ImageView imageView = rowView.findViewById(R.id.itemIcon);
        RatingBar ratingBar = rowView.findViewById(R.id.ratingBar);
        TextView ratingText = rowView.findViewById(R.id.ratingInd);


        ratingBar.setStepSize(0.1f); // Αυτό επιτρέπει την βαθμολογία με 0.1 ακρίβεια

        textView.setText(values[position]); // o titlos poy analogei se kathe kykloforia
        imageView.setImageResource(imageIds[position]); // h eikona pou analogei se kathe cover

        textView.setText(filteredValues.get(position));
        imageView.setImageResource(filteredImageIds.get(position));

        // Rating parsing


        //Symplhrwma twn asteriwn me vash to rating ind
        if (ratingText != null) {
            String raw = ratingIds[position].replace("(", "").replace(")", ""); // gia thn topothrtish twn ratings
            ratingText.setText(raw); // eisagwgh ths timhs sto rating text
            // anagnwrish vathmologiwn opws to 3.5/5
            String regex = "^([0-9]+\\.?[0-9]*)\\/([0-9]+)$";

            // Έλεγχος αν η μορφή ταιριάζει
            if (raw.matches(regex)) {
                String[] parts = raw.split("/");

                try {
                    // Παίρνουμε την βαθμολογία και την ορίζουμε στο RatingBar
                    float ratingValue = Float.parseFloat(parts[0]);
                    ratingBar.setRating(ratingValue); // Ορίζουμε τη βαθμολογία
                } catch (NumberFormatException e) {
                    ratingBar.setRating(0); // Προεπιλεγμένο σε περίπτωση σφάλματος
                }
            } else {
                // Αν δεν ταιριάζει η μορφή
                ratingBar.setRating(0); // Προεπιλεγμένο
            }
        }

        return rowView;
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<String> filteredTitles = new ArrayList<>();
                List<Integer> filteredImages = new ArrayList<>();

                if (constraint == null || constraint.length() == 0) { //an den yparxei keimeno anazhthshs
                    //epistrefei tis arxikes times

                    //filteredTitles.addAll(originalValues);
                   // filteredImages.addAll(originalImageIds);
                    filteredTitles = new ArrayList<>(originalValues);
                    filteredImages = new ArrayList<>(originalImageIds);
                } else {
                    String query = constraint.toString().toLowerCase().trim(); // yparxei keimeno anazhthshs
                    for (int i = 0; i < originalValues.size(); i++) {
                        String title = originalValues.get(i);
                        if (title.toLowerCase().contains(query)) { // elegxei an to keimeno anazhthshs tairiazei me kapoio stoixeio apo thn lista
                            filteredTitles.add(title);
                            filteredImages.add(originalImageIds.get(i));
                        }

                    }
                }

                FilterResults results = new FilterResults();
                results.values = new Object[]{filteredTitles, filteredImages};
                results.count = filteredTitles.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                Object[] filteredData = (Object[]) results.values;
                filteredValues.clear();
                filteredImageIds.clear();
                filteredValues.addAll((List<String>) filteredData[0]);
                filteredImageIds.addAll((List<Integer>) filteredData[1]);
                notifyDataSetChanged();

                if (resSearch != null) {
                    if (filteredValues.isEmpty()) {
                        resSearch.setText("Δεν βρέθηκαν αποτελέσματα");
                    }else {
                        resSearch.setText("Πρόσφατες αναζητήσεις");
                    }
                }
            }
        };
    }

}




