package cse110mt13.tritonprofessorraterv1;

import android.util.Log;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ParseClassName("Professor")
public class Professor extends ParseObject{

    private String objectId;
    // this method initializes prof stats
    public void setup(String name) {
        put("name", name);
        put("numRatings", 0);
        put("easiness", 0);
        put("helpfulness", 0);
        put("clarity", 0);
    }

    public static Professor createProf(String name, int clarity, int easiness, int helpfulness, String comment){
        Professor newProf = new Professor();
        JSONArray comments = new JSONArray();
        comments.put(comment);
        newProf.setProf(name, 1, clarity, easiness, helpfulness, comments, "");
        try{
            newProf.save();
        }
        catch(ParseException e){
            Log.e("createProfError", e.getMessage());
        }
        newProf.objectId = newProf.getObjectId();
        return newProf;
    }


// Professor prof = Professor.getProf("SomeID")
    public static Professor getProf(String profId) {
        ParseQuery<Professor> query = ParseQuery.getQuery(Professor.class);
        Professor prof = new Professor();
        try {
            prof = query.get(profId);
        }
        catch(ParseException e){
        }
      //  Log.d("ProfTest", prof.getName());
       return prof;
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
    *  Precondition: corresponding prof must be in database
    * */
    public void addComment(String comment, String courseName){
        //create new comment and save to database
        Comment commentObj = new Comment();
        commentObj.setup(comment);
        commentObj.put("ProfID", this.objectId);
        commentObj.setCourseName(TextParser.convertToUpperCase(courseName));
        try {
            commentObj.save();
        }
        catch(ParseException e){}
        //gets professor of from database of corresponding professor
        ParseQuery<Professor> query = ParseQuery.getQuery(Professor.class);
        Professor prof = new Professor();
        try{
            prof = query.get(this.getObjID());
        }
        catch(ParseException e){}
        //add comment to both the prof in the profList and the database
        JSONArray comments = prof.getComments();
        comments.put(commentObj.getObjectId());
        prof.put("comments", comments);
        this.put("comments", comments);
        try {
            prof.save();
        }
        catch(ParseException e1){}
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
        return TextParser.convertToUpperCase(getString("name"));

    }

    //ONLY USE THIS METHOD ON LOCALLY CREATED PROFESSORS
    public String getObjID(){
        return this.objectId;
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
                        JSONArray comments, String objectId){
        put("name", name);
        put("numRatings", numRatings);
        put("clarity", clarity);
        put("easiness", easiness);
        put("helpfulness", helpfulness);
        put("comments", comments);
        this.objectId = objectId;
    }

    public ArrayList<Comment> getStringComments(){
        ArrayList<Comment> comments = new ArrayList<>();
        retrieveComments(this.getObjectId(), comments);
        Collections.sort(comments);
        return comments;
    }

    //helper method to get an ArrayList of commentIDs of a certain professor
    private void retrieveComments(String profId, ArrayList<Comment> comments){
        ParseQuery<Professor> query = ParseQuery.getQuery(Professor.class);
        Professor object = new Professor();
        try {
            object = query.get(profId);
        }
        catch(ParseException e){}
        JSONArray commentIdArray = object.getComments();
        ArrayList<String> commentIds = new ArrayList<String>();
        if (commentIdArray != null) {
            for (int i = 0; i < commentIdArray.length(); i++) {
                try {
                    commentIds.add(commentIdArray.get(i).toString());
                } catch (JSONException e1) {
                }
            }
            idsToComments(commentIds, comments);
        }
        /*
        query.getInBackground(profId, new GetCallback<Professor>() {
            @Override
            public void done(Professor object, ParseException e) {
                JSONArray commentIdArray = object.getComments();
                ArrayList<String> commentIds = new ArrayList<String>();
                if (commentIdArray != null) {
                    for (int i = 0; i < commentIdArray.length(); i++) {
                        try {
                            commentIds.add(commentIdArray.get(i).toString());
                        } catch (JSONException e1) {
                        }
                    }
                    idsToComments(commentIds);
                }
            }
        });*/
    }

    //helper method to convert an ArrayList of Comment objectIDs to an ArrayList of Comments
    private void idsToComments(ArrayList<String> commentIds, ArrayList<Comment> comments){
        if(comments == null) {
            comments = new ArrayList<Comment>();
        }
        else if(!comments.isEmpty()){
            comments.clear();
        }
        for(int i = 0; i < commentIds.size(); i++) {
            ParseQuery<Comment> query = ParseQuery.getQuery(Comment.class);
            query.whereEqualTo("objectId", commentIds.get(i));
            List<Comment> object = new ArrayList<Comment>();
            try{
                object = query.find();
            }
            catch(ParseException e){}
            for(Comment comm: object) {
                //  Comment newComment = new Comment();
                //    newComment.makeNewComment(comm.getComment(), comm.getNumLikes());
                comments.add(comm);
            }
            /*
            query.findInBackground(new FindCallback<Comment>() {
                @Override
                public void done(List<Comment> object, ParseException e) {
                    for(Comment comm: object) {
                        Comment newComment = new Comment();
                        Log.d("newComment", comm.getComment());
                        newComment.makeNewComment(comm.getComment(), comm.getNumLikes());
                        Log.d("newComment1", newComment.toString());
                        comments.add(newComment);
                        Integer size = comments.size();
                        Log.d("size", size.toString());
                    }
                }
            });*/
        }
    }


    @Override
    public String toString(){
        return this.getName() + "\nEasiness: "+ this.getEasiness() + "\nHelpfulness: "+this.getHelpfulness()
                + "\nClarity: "+this.getClarity();
    }

}
