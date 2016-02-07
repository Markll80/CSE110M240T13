package cse110mt13.tritonprofessorraterv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //parse setup
        Parse.enableLocalDatastore(this);
        ParseObject.registerSubclass(Professor.class);  //---------------
        Parse.initialize(this);
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        ParseACL.setDefaultACL(defaultACL, true);





        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupSearchButton();

    }

    private void setupSearchButton() {
        Button searchB = (Button) findViewById(R.id.search_B);
        searchB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*  TODO: write backend function to determine whether clicking on search
                   should jump to none found page, or search-found page
                 */
                finish(); //end current activity
                startActivity(new Intent(MainActivity.this, SearchPage.class)); //create a new activity
            }
        });
        ;
    }
}
