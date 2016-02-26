package cse110mt13.tritonprofessorraterv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ForgotPassword extends AppCompatActivity {

    ParseUser person;

    @InjectView(R.id.input_forgetEmail) EditText _forgetEmail;
    @InjectView(R.id.btn_findUser) Button _findUser;
    @InjectView(R.id.btn_findPW) Button _findPW;
    @InjectView(R.id.forget_link_login) TextView _fwloginLink;
    @InjectView(R.id.forget_link_signup) TextView _fwsignupLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.inject(this);

        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        _findUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findUser();
            }
        });

        _findPW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findPW();
            }
        });

        _fwsignupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        _fwloginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Login activity
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivityForResult(intent, 0);
            }
        });

    }

    public void findUser(){}

    public void findPW(){}

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
