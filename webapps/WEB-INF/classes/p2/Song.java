package p2;

import java.util.ArrayList;

public class Song implements Comparable<Song> {

    public Song(){

    }

    String Title;
    String sid;
    String lang;
    String duration;
    String composer;
    ArrayList<String> genre;


//I only have a constructor for the parameters needed to make it faster

    public Song(String Title, String lang, String composer,ArrayList<String> genre){
        this.Title = Title;
        this.lang = lang;
        this.composer = composer;
        this.genre=genre;
    }

    public String getTitle(){
        return this.Title;
    }

    public ArrayList<String>  getGenre(){
        return this.genre;
    }


    public String getLang(){
        return this.lang;
    }

    public String getComposer(){
        return this.composer;
    }
    @Override
    public int compareTo(Song o) {
        // Compare first by the size of the genre list
        int comparison = this.genre.size() - o.getGenre().size();
        // If the size of the genre list is equal in both objects,
        // then compare by the title in alphabetical order
        if (comparison == 0) {
            comparison = this.Title.compareTo(o.getTitle());
        }
        return comparison;
    }
}
