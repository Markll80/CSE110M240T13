package cse110mt13.tritonprofessorraterv1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class AddProf extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prof);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.ap_sumbit_B).setOnClickListener(onclickListener);;
        findViewById(R.id.ap_cancel_B).setOnClickListener(onclickListener);;
    }


    private View.OnClickListener onclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ap_sumbit_B:
                     /*  TODO: write backend function to determine whether clicking on search
                   should jump to none found page, or search-found page
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
}
