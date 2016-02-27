package cse110mt13.tritonprofessorraterv1;

import android.widget.ListView;

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

@ParseClassName("Course")
public class Course extends ParseObject{

    private String objectId;
    // this method initializes prof stats
    public void setup(String courseName) {
        put("CourseName", courseName);
    }

    public String getCourseName() {
        return getString("CourseName");
    }

    public JSONArray getProfessors(){
        return getJSONArray("ProfTaught");
    }

    public void addProfToCourse(String profId){
        JSONArray profs = this.getProfessors();
        profs.put(profId);
        this.put("ProfTaught", profs);
        try {
            this.save();
        }
        catch(ParseException e1){}
    }

    public ArrayList<Professor> getStringProfs(){
        ArrayList<Professor> profs = new ArrayList<>();
        JSONArray profIdArray = this.getProfessors();
        ArrayList<String> profIds = new ArrayList<String>();
        if (profIdArray != null) {
            for (int i = 0; i < profIdArray.length(); i++) {
                try {
                    profIds.add(profIdArray.get(i).toString());
                } catch (JSONException e1) {
                }
            }
            idsToProfs(profIds, profs);
        }
        return profs;
    }

    //helper method to convert an ArrayList of Comment objectIDs to an ArrayList of Comments
    private void idsToProfs(ArrayList<String> profIds, ArrayList<Professor> profs){
        if(profs == null) {
            profs = new ArrayList<Professor>();
        }
        else if(!profs.isEmpty()){
            profs.clear();
        }
        for(int i = 0; i < profIds.size(); i++) {
            ParseQuery<Professor> query = ParseQuery.getQuery(Professor.class);
            query.whereEqualTo("objectId", profIds.get(i));
            List<Professor> object = new ArrayList<Professor>();
            try{
                object = query.find();
            }
            catch(ParseException e){}
            for(Professor professor: object) {
                //  Comment newComment = new Comment();
                //    newComment.makeNewComment(comm.getComment(), comm.getNumLikes());
                profs.add(professor);
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
        return this.getCourseName();
    }

}
