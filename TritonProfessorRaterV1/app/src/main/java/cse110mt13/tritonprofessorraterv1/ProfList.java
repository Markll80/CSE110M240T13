package cse110mt13.tritonprofessorraterv1;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
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
        List<Professor> objects = new ArrayList<Professor>();
        try{
            objects = query.find();
        }
        catch(ParseException e){
        }
        for (Professor prof : objects) {
            Professor newProf = new Professor();
            newProf.setProf(prof.getName(), prof.getNumRatings(), prof.getClarity(),
                    prof.getEasiness(), prof.getHelpfulness(), prof.getComments(), prof.getObjectId());
            professors.add(newProf);
        }
            /*
        query.findInBackground(new FindCallback<Professor>() {
            @Override
            public void done(List<Professor> objects, ParseException e) {
                for (Professor prof : objects) {
                    Professor newProf = new Professor();
                    newProf.setProf(prof.getName(), prof.getNumRatings(), prof.getClarity(),
                            prof.getEasiness(), prof.getHelpfulness(), prof.getObjectId());
                    professors.add(newProf);
                }
            }
        });*/
    }

    public void nameSearch(String searchedName){
        if(professors == null) {
            professors = new ArrayList<Professor>();
        }
        else if(!professors.isEmpty()) {
            professors.clear();
        }
        ParseQuery<Professor> query = ParseQuery.getQuery(Professor.class);
        query.whereContains("name", searchedName);
        List<Professor> objects = new ArrayList<Professor>();
        try {
            objects = query.find();
        }
        catch(ParseException e){}
        for (Professor prof : objects) {
            Professor newProf = new Professor();
            newProf.setProf(prof.getName(), prof.getNumRatings(), prof.getClarity(),
                    prof.getEasiness(), prof.getHelpfulness(), prof.getComments(), prof.getObjectId());
            professors.add(newProf);
        }
    /*    query.findInBackground(new FindCallback<Professor>() {
            @Override
            public void done(List<Professor> objects, ParseException e) {
                for (Professor prof : objects) {
                    Professor newProf = new Professor();
                    newProf.setProf(prof.getName(), prof.getNumRatings(), prof.getClarity(),
                            prof.getEasiness(), prof.getHelpfulness(), prof.getObjectId());
                    professors.add(newProf);
                }
            }
        });*/
    }

    public ArrayList<Comment> getComments(String profId){
        retrieveComments(profId);
        return comments;
    }

    //helper method to get an ArrayList of commentIDs of a certain professor
    private void retrieveComments(String profId){
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
            idsToComments(commentIds);
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
    private void idsToComments(ArrayList<String> commentIds){
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
                Comment newComment = new Comment();
                newComment.makeNewComment(comm.getComment(), comm.getNumLikes());
                comments.add(newComment);
                Integer size = comments.size();
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


}
