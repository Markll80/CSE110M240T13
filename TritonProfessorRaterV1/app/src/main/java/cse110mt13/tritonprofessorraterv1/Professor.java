package cse110mt13.tritonprofessorraterv1;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Professor")
public class Professor extends ParseObject {


    public void setup(String name) {
        put("name", name);
        put("numRatings", 0);
        put("easiness", 0);
        put("helpfulness", 0);
        put("clarity", 0);
    }

    public String getName() {
        return getString("Name");
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

    public void putName(String name){
        put("name",name);
    }

    public void addNumRating(){
        put("numRatings",getNumRatings()+1);
    }

    public void addRating(int clarity, int easiness, int helpfulness){
        addClarity(clarity);
        addEasiness(easiness);
        addHelpfulness(helpfulness);
        addNumRating();
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

    public void addComment(String comment){
        add("comments",comment);
    }

    /*
    // TODO: set values for members associated for the comment
    public void addComment(Comment comment){
        add("comments",comment.getObjectId());

    }*/

    @Override
    public String toString(){
        return getName() + "\nEasiness: "+getEasiness() + "\nHelpfulness: "+getHelpfulness()
                + "\nClarity: "+getClarity();
    }

}
