package cse110mt13.tritonprofessorraterv1;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import org.json.JSONArray;

import java.lang.reflect.Array;

@ParseClassName("Professor")
public class Professor extends ParseObject{

    public String name;
    public int numRatings;
    public int easiness;
    public int helpfulness;
    public int clarity;
    public String objectId;

    // this method initializes prof stats
    public void setup(String name) {
        put("name", name);
        put("numRatings", 0);
        put("easiness", 0);
        put("helpfulness", 0);
        put("clarity", 0);
    }



  /*
  use this method to quickly add a rating to the prof
  Parameter: 3 integers, each corresponding to a rating for clarity, easiness and helpfulness
  Return: bool - whether adding the rating is successful
   */
    public boolean addRating(int clarity, int easiness, int helpfulness){
        boolean s1 = addClarity(clarity);       //use helper methods to add the rating
        boolean s2 = addEasiness(easiness);
        boolean s3 = addHelpfulness(helpfulness);
        if(!s1 || !s2 || !s3)
            return false;
        incrementNumRating();
        return true;
    }

    /*
    create a new comment parseobject and save to database
    *  TODO: complete comment class and add associated prof object id to it
    * */
    public void addComment(String comment){
        Comment commentObj = new Comment();
        commentObj.putComment(comment);
        commentObj.saveInBackground();
    }

   


    public void putName(String name){
        put("name", name);
    }

    //increment numRatings
    public void incrementNumRating(){
        put("numRatings", getNumRatings() + 1);
    }


    public int getNumRatings() {
        return getInt("numRatings");
    }

    public int getEasiness() {
        return getInt("easiness");
    }

    public int getHelpfulness() {
        return getInt("helpfulness");
    }

    public int getClarity() {
        return getInt("clarity");
    }

    public String getName() {
        return getString("name");
    }

    public String getObjectID() {
        return getString("objectId");
    }

    public JSONArray getComments(){
        return getJSONArray("comments");
    }

    private boolean addClarity(int clarity){
        if( clarity < 0 || clarity > 5 )
            return false;
        int averageClarity = getClarity();
        int numCount = getNumRatings();
        int newAvg = ((averageClarity*(numCount++))+clarity)/numCount;
        put("clarity", newAvg);
        return true;
    }

    private boolean addEasiness(int easiness){
        if( easiness < 0 || easiness > 5 )
            return false;
        int averageEasiness = getEasiness();
        int numCount = getNumRatings();
        int newAvg = ((averageEasiness*(numCount++))+easiness)/numCount;
        put("easiness", newAvg);
        return true;
    }

    private boolean addHelpfulness(int helpfulness){
        if( helpfulness < 0 || helpfulness > 5 )
            return false;
        int averageHelpfulness = getHelpfulness();
        int numCount = getNumRatings();
        int newAvg = ((averageHelpfulness*(numCount++))+helpfulness)/numCount;
        put("helpfulness", newAvg);
        return true;
    }

    // used for ParseQuery only, do not use this method to add new prof
    public void setProf(String name, int numRatings, int clarity, int easiness, int helpfulness,
                        String objectId){
        this.name = name;
        this.numRatings = numRatings;
        this.clarity = clarity;
        this.easiness = easiness;
        this.helpfulness = helpfulness;
        this.objectId = objectId;
    }

    @Override
    public String toString(){
        return name + "\nEasiness: "+ easiness + "\nHelpfulness: "+helpfulness
                + "\nClarity: "+clarity;
    }

}
