package cse110mt13.tritonprofessorraterv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Parse.enableLocalDatastore(this);
        Parse.initialize(this);

        //------------NOT WORKING
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
// Optionally enable public read access.
// defaultACL.setPublicReadAccess﴾true﴿;
        ParseACL.setDefaultACL(defaultACL, true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();*/
    }
}
