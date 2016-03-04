package cse110mt13.tritonprofessorraterv1;

import android.content.Intent;
import android.os.Bundle;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;


/**
 * Created by Cherish on 16/2/14.
 */
public class DispatchActivity extends MainActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Parse.enableLocalDatastore(this);
        Parse.initialize(this);
        ParseObject.registerSubclass(Professor.class);
        ParseObject.registerSubclass(Course.class);
        ParseObject.registerSubclass(Comment.class);
        // ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);

        super.onCreate(savedInstanceState);

        //check if there is a current user info
        if(ParseUser.getCurrentUser() != null){
            //start an intent for the logged in activity
            finish();
            startActivity(new Intent(this, MainActivity.class));
        } else {
            //start and intent for the logged out activity
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
