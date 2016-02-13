package cse110mt13.tritonprofessorraterv1;
import android.os.Bundle;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Neil on 2/12/2016.
 */
public class ProfList{
    public List<Professor> professors = new ArrayList<Professor>();

    public ProfList(){
        ParseQuery<Professor> query = new ParseQuery<Professor>("Professor");
        query.findInBackground(new FindCallback<Professor>() {
            @Override
            public void done(List<Professor> objects, ParseException e) {
                for (Professor prof: objects){
                    Professor newProf = new Professor();
                    newProf.setProf(prof.getName(), prof.getNumRatings(),
                    prof.getClarity(), prof.getEasiness(), prof.getHelpfulness());
                    professors.add(newProf);
                }
            }
        });
    }


}
