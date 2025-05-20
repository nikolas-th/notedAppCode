package com.example.notedapp;

import java.util.ArrayList;
import java.util.List;

public class DBmanager {

    public static final Release[] releases = {
            new Release(1,"The Queen Is Dead", R.drawable.smithscover, "5/5", 1986,
                    "The Smiths", "Album", null, "Seminal indie rock album", "Alternative"),

            new Release(2,"Paranoid", R.drawable.paranoid, "4.5/5", 1970,
                    "Black Sabbath", "Album", null, "Classic metal album", "Heavy Metal"),

            new Release(3,"Meat Is Murder", R.drawable.smithscover2, "4/5", 1985,
                    "The Smiths", "Album", null, "Controversial vegetarian-themed album", "Alternative"),

            new Release(4,"Swimming", R.drawable.swimming2, "3.5/5", 2018,
                    "Mac Miller", "Album", null, "Final studio album before passing", "Hip-Hop"),

            new Release(5,"Strangeways, Here We Come", R.drawable.smithscover, "4/5", 1987,
                    "The Smiths", "Album", null, "The Smiths' final studio album", "Alternative"),

            new Release(6,"Master of Reality", R.drawable.paranoid, "4.5/5", 1971,
                    "Black Sabbath", "Album", null, "Pioneering doom metal sound", "Heavy Metal"),

            new Release(7,"Louder Than Bombs", R.drawable.smithscover, "4/5", 1987,
                    "The Smiths", "Compilation", null, "Collection of singles and B-sides", "Alternative"),

            new Release(8,"The World Won't Listen", R.drawable.smithscover2, "3.5/5", 1987,
                    "The Smiths", "Compilation", null, "International compilation album", "Alternative"),

            new Release(9,"Vol. 4", R.drawable.paranoid, "4/5", 1972,
                    "Black Sabbath", "Album", null, "Experimental metal album", "Heavy Metal"),

            new Release(10,"Hatful of Hollow", R.drawable.smithscover2, "5/5", 1984,
                    "The Smiths", "Compilation", null, "Early recordings and BBC sessions", "Alternative")
    };

    //dedomena gia ta reviews
    public static final Review[] reviews = {
        new Review("Alex123","Φοβερός δίσκος!!!", "5/5", "20/05/2025", 1),
        new Review("ouaou","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", "4.5/5", "19/05/2025", 2),
    };

    static { // eyresh twn reviews gia kathe kykloforia
        // Συνδέουμε τις κριτικές με κάθε κυκλοφορία
        for (Release release : releases) {
            List<Review> matchingReviews = new ArrayList<>();
            for (Review review : reviews) {
                if (review.getReleaseId() == release.id) {
                    matchingReviews.add(review);
                }
            }
            release.setReviews(matchingReviews);
        }
    }

    public static Release getReleaseById(int id) {
        for (Release release : releases) {
            if (release.id == id) {
                return release;
            }
        }
        return null; // Not found
    }


}

