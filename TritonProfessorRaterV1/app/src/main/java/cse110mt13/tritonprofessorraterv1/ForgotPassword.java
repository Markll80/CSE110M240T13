package cse110mt13.tritonprofessorraterv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ForgotPassword extends AppCompatActivity {

    ParseUser person;

    @InjectView(R.id.input_forgetEmail) EditText _forgetEmail;
    @InjectView(R.id.btn_findUser) Button _findUser;
    @InjectView(R.id.forget_link_login) TextView _fwloginLink;
    @InjectView(R.id.forget_link_signup) TextView _fwsignupLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.inject(this);

        _findUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findUser();
            }
        });

        _fwsignupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Signup activity
                finish();
                Intent intent = new Intent(ForgotPassword.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        _fwloginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Login activity
                forwardToLoginPage();
            }
        });

    }

    public void findUser(){

        String email = _forgetEmail.getText().toString().toLowerCase();
        ParseUser.requestPasswordResetInBackground(email,
                new RequestPasswordResetCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            String success = "Email sent";
                            Toast.makeText(getApplicationContext(), success, Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            // Something went wrong. Look at the ParseException to see what's up.
                            String fail = "Unable to send email";
                            Toast.makeText(getApplicationContext(), fail, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }


    public void forwardToLoginPage(){
        finish();
    }

}
