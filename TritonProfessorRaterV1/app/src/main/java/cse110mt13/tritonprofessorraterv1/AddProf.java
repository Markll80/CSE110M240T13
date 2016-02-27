package cse110mt13.tritonprofessorraterv1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;

import android.app.AlertDialog;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;
import com.parse.ParseObject;

public class AddProf extends AppCompatActivity {

    final Context context = this;

    EditText ProfName, apCourseName, apComment;
    RatingBar ap_RatingC, ap_RatingE, ap_RatingH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    /*    Parse.enableLocalDatastore(this);
        ParseObject.registerSubclass(Professor.class);
        ParseObject.registerSubclass(Comment.class);
        Parse.initialize(this);
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        ParseACL.setDefaultACL(defaultACL, true);
*/

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prof);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ProfName = (EditText) findViewById(R.id.ap_name_ET);
        apCourseName = (EditText) findViewById(R.id.ap_course_ET);
        apComment = (EditText) findViewById(R.id.ap_comment_ET);

        ap_RatingC = (RatingBar) findViewById(R.id.ap_RatingC);
        ap_RatingE = (RatingBar) findViewById(R.id.ap_RatingE);
        ap_RatingH = (RatingBar) findViewById(R.id.ap_RatingH);

        findViewById(R.id.ap_sumbit_B).setOnClickListener(onclickListener);
        findViewById(R.id.ap_cancel_B).setOnClickListener(onclickListener);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private View.OnClickListener onclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ap_sumbit_B:
                     /*  TODO:


                            Connect to Parse!!
                            Check valid course name!!

                           Tasks below are finished...

                           1. If course name is empty or invalid (to do)(such as CSE9999),make a warning toast
                           2. if course name is valid (CSE100), parse it properly (to CSE 100, with space)
                           (I think we should check space first, then check if the course name is valid, then parse)
                           3. check if comment is empty or a bunch of empty spaces
                              ( >25%-or more of chars are space-or more?)
                              if so, send warning - comment is empty/invalid
                              https://www.youtube.com/watch?v=nW_nvmmxURc
                           4. verification box when clicking sumbit
                           5. verification box when clicking cancel
                           6. set initial step size to 1
                 */

                    String vap_Course, vap_Comment;

                    vap_Course = apCourseName.getText().toString();
                    vap_Comment = apComment.getText().toString();


                    if (!validCourse(vap_Course))
                    { apCourseName.setError("Invalid Course Number!");}

                    else if (!validComment(vap_Comment))
                    { apComment.setError("Invalid Comment! Please enter a valid comment");}


                    // test rating num
                    /*
                    String text = "" + ap_C;
                    Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
                    */

                    // TODO: check if the professor is already in the database, if so,
                    //ask if the user still want to create the professor


                    else {

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                        alertDialogBuilder.setTitle("Are you sure?");

                        alertDialogBuilder
                                .setMessage("Click yes to submit!")
                                .setCancelable(false)
                                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {


                                        String ap_Prof, ap_Course, ap_Comment;
                                        int ap_C, ap_E, ap_H;

                                        ap_Prof = ProfName.getText().toString();
                                        ap_Course = apCourseName.getText().toString();
                                        ap_Comment = apComment.getText().toString();
                                        ap_C = (int) ap_RatingC.getRating();
                                        ap_E = (int) ap_RatingE.getRating();
                                        ap_H = (int) ap_RatingH.getRating();

                                        createProf(ap_Prof,ap_C,ap_E,ap_H,ap_Comment);


                                        submitProf();
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                        AlertDialog alertDialog = alertDialogBuilder.create();

                        alertDialog.show();

                    }

                    break;
                case R.id.ap_cancel_B:


                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                    alertDialogBuilder.setTitle("Are you sure?");

                    alertDialogBuilder
                            .setMessage("Click yes to cancel!")
                            .setCancelable(false)
                            .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    cancelProf();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alertDialog = alertDialogBuilder.create();

                    alertDialog.show();

                    break;
            }
        }
    };

    private void createProf(String apName, int apRatingC, int apRatingE, int apRatingH, String apComment){
        Professor newProf = new Professor();
        newProf.setup(apName);
        newProf.addRating(apRatingC, apRatingE, apRatingH);
        newProf.addComment(apComment);
    }

    private boolean validComment(String newComment){

        if (newComment.isEmpty() || newComment == null)
            return false;

        String replace = newComment.replace(" ","");

        int countSpace = newComment.length() - replace.length();

        if(countSpace > (int) 0.25 * newComment.length())
            return false;

        else return true;

    }

    private boolean validCourse(String newCourse){

        newCourse = newCourse.toLowerCase();

        if (newCourse.isEmpty() || newCourse == null)
            return false;

        // TODO: check if the course number is valid

        else return true;
    }

    private String addSpace(String newCourse){
        /*
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
         */
        newCourse = newCourse.toUpperCase().replaceAll(" *", "");
        newCourse = newCourse.replaceAll("(?<=[A-Z])(?=[0-9])"," ");

        return newCourse;
    }

    private void submitProf(){
        finish(); //end current activity
        startActivity(new Intent(AddProf.this, ProfPage.class)); //create a new activity
    }

    private void cancelProf() {
        finish(); //end current activity
        startActivity(new Intent(AddProf.this, MainActivity.class)); //create a new activity
    }
}
