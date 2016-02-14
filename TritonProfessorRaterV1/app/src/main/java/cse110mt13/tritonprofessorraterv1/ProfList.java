package cse110mt13.tritonprofessorraterv1;
import android.os.Bundle;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Neil on 2/12/2016.
 */
public class ProfList{
    public ArrayList<Professor> professors;
    private ArrayList<Comment> comments;

    public ProfList(){
        professors = new ArrayList<Professor>();
        ParseQuery<Professor> query = ParseQuery.getQuery(Professor.class);

        query.findInBackground(new FindCallback<Professor>() {
            @Override
            public void done(List<Professor> objects, ParseException e) {
                for (Professor prof : objects) {
                    Professor newProf = new Professor();
                    newProf.setProf(prof.getName(), prof.getNumRatings(), prof.getClarity(),
                            prof.getEasiness(), prof.getHelpfulness(), prof.getObjectID());
                    professors.add(newProf);
                }
            }
        });
    }

    public void nameSearch(String searchedName){
        ParseQuery<Professor> query = ParseQuery.getQuery(Professor.class);
        query.whereContains("name", searchedName);

        query.findInBackground(new FindCallback<Professor>() {
            @Override
            public void done(List<Professor> objects, ParseException e) {
                for (Professor prof : objects) {
                    Professor newProf = new Professor();
                    newProf.setProf(prof.getName(), prof.getNumRatings(), prof.getClarity(),
                            prof.getEasiness(), prof.getHelpfulness(), prof.getObjectID());
                    professors.add(newProf);
                }
            }
        });
    }

    public ArrayList<Comment> getComments(String profId){
        retrieveComments(profId);
        return comments;
    }

    //helper method to get an ArrayList of commentIDs of a certain professor
    private void retrieveComments(String profId){
        ParseQuery<Professor> query = ParseQuery.getQuery(Professor.class);

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
        });
    }

    //helper method to convert an ArrayList of Comment objectIDs to an ArrayList of Comments
    private void idsToComments(ArrayList<String> commentIds){
        comments = new ArrayList<Comment>();
        for(int i = 0; i < commentIds.size(); i++) {
            ParseQuery<Comment> query = ParseQuery.getQuery(Comment.class);

            query.getInBackground(commentIds.get(i), new GetCallback<Comment>() {
                @Override
                public void done(Comment object, ParseException e) {
                    Comment newComment = new Comment();
                    newComment.makeNewComment(object.getComment(), object.getNumLikes());
                    comments.add(newComment);

                }
            });
        }
    }


}
