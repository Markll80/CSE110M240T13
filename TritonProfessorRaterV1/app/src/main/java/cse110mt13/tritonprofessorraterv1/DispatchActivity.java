package cse110mt13.tritonprofessorraterv1;

import android.content.Intent;
import android.os.Bundle;
import com.parse.ParseUser;


/**
 * Created by Cherish on 16/2/14.
 */
public class DispatchActivity extends MainActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //check if there is a current user info
        if(ParseUser.getCurrentUser() != null){
            //start an intent for the logged in activity
            startActivity(new Intent(this, MainActivity.class));
        } else {
            //start and intent for the logged out activity
            startActivity(new Intent(this, LogIn.class));
        }
    }
}
