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
import java.util.Collections;
import java.util.List;
/**
 * Created by Neil on 2/12/2016.
 */
public class ProfList{
    public ArrayList<Professor> professors;

    public ProfList(){
        professors = new ArrayList<Professor>();
        /*
        ParseQuery<Professor> query = ParseQuery.getQuery(Professor.class);
        List<Professor> objects = new ArrayList<Professor>();
        try{
            objects = query.find();
        }
        catch(ParseException e){
        }
        //Log.d("ProfListDebug", "Query Size:"+objects.size());
        for (Professor prof : objects) {
            Professor newProf = new Professor();
            Log.d("ProfListDebug", "Prof Query: "+prof.getName());
            newProf.setProf(prof.getName(), prof.getNumRatings(), prof.getClarity(),
                    prof.getEasiness(), prof.getHelpfulness(), prof.getComments(), prof.getObjectId());
            professors.add(newProf);
        }
        */
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
        searchedName = searchedName.toLowerCase();
        if(!searchedName.matches(".*\\d.*")) {//if doesn't have number, search by prof
            ParseQuery<Professor> query = ParseQuery.getQuery(Professor.class);
            query.whereContains("name", searchedName);
            List<Professor> objects = new ArrayList<Professor>();
            try {
                objects = query.find();
            } catch (ParseException e) {
                Log.e("failed to find prof", e.getMessage());
            }
            for (Professor prof : objects) {
                Professor newProf = new Professor();
                newProf.setProf(prof.getName(), prof.getNumRatings(), prof.getClarity(),
                        prof.getEasiness(), prof.getHelpfulness(), prof.getComments(), prof.getObjectId());
                professors.add(newProf);
                Log.d("ProfListSearch", "prof found: " + newProf.getName());
            }
        }
        else if(searchedName.matches(".*\\d.*")){ //if contains number, search by course
            char currentChar;
            for(int i = 0; i < searchedName.length(); i++){
                currentChar = searchedName.charAt(i);
                if(i > 0 && Character.isDigit(currentChar)){
                    if(!((Character)searchedName.charAt(i - 1)).equals(' ')){
                        searchedName = searchedName.substring(0, i) + " " + searchedName.substring(i, searchedName.length());
                    }
                    break;
                }
            }
            ParseQuery<Course> query = ParseQuery.getQuery(Course.class);
            query.whereContains("CourseName", searchedName);
            List<Course> objects = new ArrayList<Course>();
            try {
                objects = query.find();
            }
            catch (ParseException e) {
                Log.e("courseSearchError", e.getMessage());
            }
            for (Course courses : objects) {
                JSONArray ProfArray = courses.getProfessors();
                ArrayList<String> profIds = new ArrayList<String>();
                if (ProfArray != null) {
                    for (int i = 0; i < ProfArray.length(); i++) {
                        try {
                            profIds.add(ProfArray.get(i).toString());
                        }
                        catch (JSONException e1) {
                            Log.e("courseSearchError", e1.getMessage());
                        }
                    }
                    this.idsToProfs(profIds);
                }
            }
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

    private void idsToProfs(ArrayList<String> profIds) {
        for (int i = 0; i < profIds.size(); i++) {
            ParseQuery<Professor> query = ParseQuery.getQuery(Professor.class);
            query.whereEqualTo("objectId", profIds.get(i));
            List<Professor> object = new ArrayList<Professor>();
            try {
                object = query.find();
            } catch (ParseException e) {
                Log.e("idsToProfsError", e.getMessage());
            }
            for (Professor prof : object) {
                Professor newProf = new Professor();
                newProf.setProf(prof.getName(), prof.getNumRatings(), prof.getClarity(),
                        prof.getEasiness(), prof.getHelpfulness(), prof.getComments(), prof.getObjectId());
                professors.add(newProf);
            }
        }
    }
}
