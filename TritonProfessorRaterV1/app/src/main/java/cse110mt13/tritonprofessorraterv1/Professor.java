package cse110mt13.tritonprofessorraterv1;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Professor")
public class Professor extends ParseObject {


    public void addProfessor(String name) {
        ParseObject testObject = new ParseObject("Professor");
        testObject.put("Name", name);
        testObject.put("numRatings", 0);
        testObject.put("Easiness", 0);
        testObject.put("Helpfulness", 0);
        testObject.put("Clarity", 0);
    }

    public String getName() {
        return getString("Name");
    }

    public int getNumRatings() {
        return getInt("numRatings");
    }

    public int getEasiness() {
        return getInt("Easiness");
    }

    public int getHelpfulness() {
        return getInt("Helpfulness");
    }

    public int getClarity() {
        return getInt("Clarity");
    }

    public void putName(String name){
        put("name",name);
    }

    public boolean addClarity(int clarity){
        if( clarity < 0 || clarity > 5 )
            return false;
        int averageClarity = getClarity();
        int numCount = getNumRatings();
        int newAvg = ((averageClarity*(numCount++))+clarity)/numCount;
        put("Clarity", newAvg);
        return true;
    }

    public boolean addEasiness(int easiness){
        if( easiness < 0 || easiness > 5 )
            return false;
        int averageEasiness = getEasiness();
        int numCount = getNumRatings();
        int newAvg = ((averageEasiness*(numCount++))+easiness)/numCount;
        put("Easiness", newAvg);
        return true;
    }

    public boolean addHelpfulness(int helpfulness){
        if( helpfulness < 0 || helpfulness > 5 )
            return false;
        int averageHelpfulness = getHelpfulness();
        int numCount = getNumRatings();
        int newAvg = ((averageHelpfulness*(numCount++))+helpfulness)/numCount;
        put("Helpfulness", newAvg);
        return true;
    }

    // TODO: set values for members associated for the comment
    public void addComment(String comment){
        add("comments",comment);

    }

    @Override
    public String toString(){
        return getName() + "\nEasiness: "+getEasiness() + "\nHelpfulness: "+getHelpfulness()
                + "\nClarity: "+getClarity();
    }

}
