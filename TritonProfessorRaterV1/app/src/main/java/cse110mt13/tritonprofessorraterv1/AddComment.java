package cse110mt13.tritonprofessorraterv1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class AddComment extends AppCompatActivity {


    final Context context = this;

    EditText acCourseName, acComment;
    RatingBar ac_RatingC, ac_RatingE, ac_RatingH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Parse.enableLocalDatastore(this);
        Parse.initialize(this);
        ParseObject.registerSubclass(Professor.class);
        ParseObject.registerSubclass(Course.class);
        ParseObject.registerSubclass(Comment.class);
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        acCourseName = (EditText) findViewById(R.id.ac_AddCourse_ET);
        acComment = (EditText) findViewById(R.id.ac_Comment_ET);

        ac_RatingC = (RatingBar) findViewById(R.id.ac_RatingC);
        ac_RatingE = (RatingBar) findViewById(R.id.ac_RatingE);
        ac_RatingH = (RatingBar) findViewById(R.id.ac_RatingH);

        findViewById(R.id.ac_Submit_B).setOnClickListener(onclickListener);
        findViewById(R.id.ac_Cancel_B).setOnClickListener(onclickListener);

      //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private View.OnClickListener onclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ac_Submit_B:

                     /*  TODO:

                           1. If course name is empty(done) or invalid(to do) (such as CSE9999),make a warning toast(done)
                           2. if course name is valid (CSE100), parse it properly (to CSE 100, with space)(done)
                           3. check if comment is empty or a bunch of empty spaces ( >25%-or more of chars are space-or more?),
                              if so, send warning - comment is empty/invalid(done)
                           4. verification box when clicking submit
                           5. verification box when clicking cancel
                     */



                    /*
                    TODO: Add the new comment , let Neil or Eric do this part
                     */

                    String ac_Course, ac_Comment;
                    int ac_C, ac_E, ac_H;

                    ac_Course = acCourseName.getText().toString();
                    ac_Comment = acComment.getText().toString();

                    ac_C = (int) ac_RatingC.getRating();
                    ac_E = (int) ac_RatingE.getRating();
                    ac_H = (int) ac_RatingH.getRating();



                    if (!validCourse(ac_Course))
                        acCourseName.setError("Invalid Course Number!");

                    else if (!validComment(ac_Comment))
                        acComment.setError("Invalid Comment! Please enter comment and don't enter too much space.");


                    else{

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                        alertDialogBuilder.setTitle("Are you sure?");

                        alertDialogBuilder
                                .setMessage("Click yes to submit!")
                                .setCancelable(false)
                                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        submitComment();
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
                case R.id.ac_Cancel_B:

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                    alertDialogBuilder.setTitle("Are you sure?");

                    alertDialogBuilder
                            .setMessage("Click yes to cancel!")
                            .setCancelable(false)
                            .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    cancelComment();
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


    // Functions below are for testing, will update later.

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

        newCourse = newCourse.toUpperCase().replaceAll("CSE *", "CSE ");

        return newCourse;
    }

    private void submitComment(){
        finish(); //end current activity
        startActivity(new Intent(AddComment.this, ProfPage.class)); //create a new activity
    }

    private void cancelComment(){

        finish(); //end current activity
        startActivity(new Intent(AddComment.this, MainActivity.class)); //create a new activity
    }

    public void BackToProfPage(View view) {
        startActivity(new Intent(this, ProfPage.class));
    }


}