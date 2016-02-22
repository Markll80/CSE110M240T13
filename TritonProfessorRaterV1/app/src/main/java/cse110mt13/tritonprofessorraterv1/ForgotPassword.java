package cse110mt13.tritonprofessorraterv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

public class ForgotPassword extends AppCompatActivity {

    ParseUser person;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //TODO finish method once the ForgotPassword page is done
    /*
    private View.OnClickListener onclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.reset_B:
                    ParseUser.requestPasswordResetInBackground("", new RequestPasswordResetCallback() {
                        public void done(ParseException e) {
                            if (e == null) {
                               // An email was successfully sent with reset instructions.
                            } else {
                              // Something went wrong. Look at the ParseException to see what's up.
                            }
                        }
                    });
                    break;
            }
        }
    };
    */
}
