package cse110mt13.tritonprofessorraterv1;

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

    EditText acCourseName, acComment;
    RatingBar ac_RatingC, ac_RatingE, ac_RatingH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

    }

    private View.OnClickListener onclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ac_Submit_B:
                     /*  TODO: write backend function to determine whether clicking on search
                   should jump to none found page, or search-found page
                 */


                    // TODO: Get Prof name from database and add comment to that particular prof???


                    String ac_Course, ac_Comment;
                    int ac_C, ac_E, ac_H;

                    ac_Course = acCourseName.getText().toString();
                    ac_Comment = acComment.getText().toString();

                    ac_C = (int) ac_RatingC.getRating();
                    ac_E = (int) ac_RatingE.getRating();
                    ac_H = (int) ac_RatingH.getRating();
                    
                    finish(); //end current activity
                    startActivity(new Intent(AddComment.this, ProfPage.class)); //create a new activity
                    break;
                case R.id.ac_Cancel_B:
                    finish(); //end current activity
                    startActivity(new Intent(AddComment.this, MainActivity.class)); //create a new activity
                    break;
            }
        }
    };


}
