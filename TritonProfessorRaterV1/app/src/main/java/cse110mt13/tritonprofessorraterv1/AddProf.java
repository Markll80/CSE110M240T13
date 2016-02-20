package cse110mt13.tritonprofessorraterv1;

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

public class AddProf extends AppCompatActivity {

    EditText ProfName, apCourseName, apComment;
    RatingBar ap_RatingC, ap_RatingE, ap_RatingH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
    }

    private View.OnClickListener onclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ap_sumbit_B:
                     /*  TODO: write backend function to determine whether clicking on search
                   should jump to none found page, or search-found page
                 */

                    String ap_Prof, ap_Course, ap_Comment;
                    int ap_C, ap_E, ap_H;

                    ap_Prof = ProfName.getText().toString();
                    ap_Course = apCourseName.getText().toString();
                    ap_Comment = apComment.getText().toString();
                    ap_C = (int) ap_RatingC.getRating();
                    ap_E = (int) ap_RatingE.getRating();
                    ap_H = (int) ap_RatingH.getRating();

                    // test rating num
                    /*
                    String text = "" + ap_C;
                    Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
                    */

                    finish(); //end current activity
                    startActivity(new Intent(AddProf.this, ProfPage.class)); //create a new activity
                    break;
                case R.id.ap_cancel_B:
                    finish(); //end current activity
                    startActivity(new Intent(AddProf.this, MainActivity.class)); //create a new activity
                    break;
            }
        }
    };

    private void createProf(String apName, int apRatingC, int apRatingE, int apRatingH){
        Professor newProf = new Professor();
        newProf.setup(apName);
        newProf.addRating(apRatingC,apRatingE,apRatingH);

        //TODO:add comment
    }
}
