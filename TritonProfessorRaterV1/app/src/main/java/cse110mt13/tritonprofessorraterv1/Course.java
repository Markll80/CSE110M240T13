package cse110mt13.tritonprofessorraterv1;

import android.widget.ListView;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONArray;

import java.lang.reflect.Array;

@ParseClassName("Course")
public class Course extends ParseObject{

    private String objectId;
    // this method initializes prof stats
    public void setup(String courseName) {
        put("ClassName", courseName);
    }

    public String getCourseName() {
        return getString("ClassName");
    }

    public JSONArray getProfessors(){
        return getJSONArray("ProfTaught");
    }

    @Override
    public String toString(){
        return this.getCourseName();
    }

}
