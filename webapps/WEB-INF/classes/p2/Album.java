package p2;

public class Album implements Comparable<Album>{

    public Album(){

    }

    String Name;
    String Country;
    String Performer;
    String Company;
    String ISBN;
    String format;
    String aid;
    String Review;
    String year;
    String record;


//I only have a constructor for the parameters needed to make it faster
    public Album (String Name, String Country, String Performer,String aid, String Review,String year,String record){
        this.Name=Name;
        this.Country=Country;
        this.Performer=Performer;
        this.aid=aid;
        this.Review=Review;
        this.year=year;
        this.record=record;
    }
//getters
    public String getName(){
        return this.Name;
    }

    public String getCountry(){
        return this.Country;
    }

    public String getSinger(){
        return this.Performer;
    }

    public String getaid(){
        return this.aid;
    }

    public String getReview(){
        return this.Review;
    }

    public String getYear(){
        return this.year;
    }

    public String getRecord(){
        return this.record;
    }

    //we override the method in order to sort the list
    @Override
    public int compareTo(Album o) {
        // Compare the year of this object with the year of the object passed as a parameter
        int compareYear = Integer.compare(Integer.parseInt(this.year), Integer.parseInt(o.getYear()));
        if (compareYear != 0) {
            // If the years are different, return the result of the year comparison
            return compareYear;
        } else {
            // If the years are equal, compare the names of the albums
            return this.Name.compareTo(o.getName());
        }
    }
    
}
