package com.example.notedapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class DBmanager {

    public static User[] users = {
            new User(1, "Artist", "Gold", "nikos_kal", "pass123", 25),
            new User(2, "User", "Silver", "maria89", "maria@123", 8),
            new User(3, "User", "Bronze", "giorgos_p", "gp_pass", 3),
            new User(4, "Artist", "Platinum", "eleni_music", "el3n1P@ss", 40),
            new User(5, "User", "Gold", "alex_theo", "al3xTh90", 15),
            new User(6, "Artist", "Silver", "dimitris.art", "dim@rts!", 10),
            new User(7, "User", "Bronze", "sofia.k", "sofia1234", 2),
            new User(8, "Artist", "Gold", "katerina_v", "katV!2024", 18),
            new User(9, "User", "Silver", "manolis92", "man@pass", 7),
            new User(10, "User", "Bronze", "vasiliki_l", "vassil1ki", 1)
    };

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

    public static Review[] reviews = {
            new Review(1,"Φοβερός δίσκος!!!", "5/5", "20/05/2025", 1),
            new Review(2,"Ο καλυτερος δίσκος των smiths!!!", "4.9/5", "21/05/2025", 1),
            new Review(3,"Lorem ipsum dolor sit amet, consectetur adipiscing elit...", "4.5/5", "19/05/2025", 2)
    };

    public static ReleaseList[] userLists = {
            new ReleaseList(
                    "nikos_kal",
                    "Best of The Smiths",
                    "A curated list of my favorite releases by The Smiths.",
                    new ArrayList<>(Arrays.asList(
                            releases[0],
                            releases[2],
                            releases[4],
                            releases[6],
                            releases[7],
                            releases[9]
                    )),
                    "2024-04-21"
            ),
            new ReleaseList(
                    "nikos_kal",
                    "Foundations of Heavy Metal",
                    "A beginner-friendly dive into essential metal albums.",
                    new ArrayList<>(Arrays.asList(
                            releases[1],
                            releases[5],
                            releases[8]
                    )),
                    "2024-04-30"
            )
    };

    public static Event[] events = {
            new Event("Jazz Night", "Μαρία Παπαδοπούλου", "Jazz", "Αθήνα", "Πλ. Συντάγματος", "2025-06-15", "21:00"),
            new Event("Rock Fest", "The Rockers", "Rock", "Θεσσαλονίκη", "Πλ. Αριστοτέλους", "2025-07-01", "20:00")
    };


    static {
        // Συνδέουμε τις κριτικές με τις κυκλοφορίες
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

    public static User getUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public static int getUserIdByUsername(String username){
        for (User user : users) {
            if (Objects.equals(user.getUsername(), username)) {
                return user.getId();
            }
        }
        return -1;
    }

    public static boolean usernameExists(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }

    public static List<ReleaseList> getListsByUser(String username) {
        return Arrays.stream(userLists)
                .filter(list -> list.getAuthor().equalsIgnoreCase(username))
                .collect(Collectors.toList());
    }

    public static void addReview(Review newReview) {
        Review[] newArray = new Review[reviews.length + 1]; //neos pinakas me ena epipleon stoixeio
        System.arraycopy(reviews, 0, newArray, 0, reviews.length); // Antigrafh tou pinaka reviews ston newArray
        newArray[reviews.length] = newReview;
        reviews = newArray;

        // Συνδέεται άμεσα με την κυκλοφορία αν υπάρχει
        Release release = getReleaseById(newReview.getReleaseId());
        if (release != null) {
            release.getReviews().add(newReview);
        }
    }

    public static void addReleaseList(ReleaseList newReleaseList) {
        ReleaseList[] newArray = new ReleaseList[userLists.length + 1]; //neos pinakas me ena epipleon stoixeio
        System.arraycopy(userLists, 0, newArray, 0, userLists.length); // Antigrafh tou pinaka user ston newArray
        newArray[userLists.length] = newReleaseList;
        userLists = newArray;
    }

    public static void addUser(User newUser){
        User[] newArray = new User[users.length + 1]; //neos pinakas me ena epipleon stoixeio
        System.arraycopy(users, 0, newArray, 0, users.length); // Antigrafh tou pinaka reviews ston newArray
        newArray[users.length] = newUser;
        users = newArray;

    }

    public static void addEvent(Event newEvent){
        Event[] newArray = new Event[events.length + 1]; //neos pinakas me ena epipleon stoixeio
        System.arraycopy(events, 0, newArray, 0, events.length); // Antigrafh tou pinaka reviews ston newArray
        newArray[events.length] = newEvent;
        events = newArray;
    }

    public static List<User> searchUsers(String query) {
        List<User> result = new ArrayList<>();
        for (User user : users) {
            if (user.getUsername().toLowerCase().contains(query.toLowerCase())) {
                result.add(user);
            }
        }
        return result;

    }

    //Gia na emfanizetai apo prin
    public static List<User> getAllUsers() {
        return new ArrayList<>(Arrays.asList(users));
    }




}
