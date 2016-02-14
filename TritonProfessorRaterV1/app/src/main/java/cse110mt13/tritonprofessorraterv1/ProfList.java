package cse110mt13.tritonprofessorraterv1;
import android.os.Bundle;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Neil on 2/12/2016.
 */
public class ProfList{
    public List<Professor> professors;

    public ProfList(){
        professors = new ArrayList<Professor>();
        ParseQuery<Professor> query = ParseQuery.getQuery(Professor.class);

        query.findInBackground(new FindCallback<Professor>() {
            @Override
            public void done(List<Professor> objects, ParseException e) {
                for (Professor prof : objects) {
                    Professor newProf = new Professor();
                    newProf.setProf(prof.getName(), prof.getNumRatings(),
                            prof.getClarity(), prof.getEasiness(), prof.getHelpfulness());
                    professors.add(newProf);
                }
            }
        });
    }


}
