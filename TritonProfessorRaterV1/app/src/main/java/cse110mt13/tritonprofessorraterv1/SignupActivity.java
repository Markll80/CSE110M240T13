package cse110mt13.tritonprofessorraterv1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Cherish on 16/2/15.
 */
public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

    @InjectView(R.id.input_name) EditText _nameText;
    @InjectView(R.id.input_email) EditText _emailText;
    @InjectView(R.id.input_password) EditText _passwordText;
    @InjectView(R.id.btn_signup) Button _signupButton;
    @InjectView(R.id.link_login) TextView _loginLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
/*
        Parse.enableLocalDatastore(this);
        Parse.initialize(this);
        ParseObject.registerSubclass(Professor.class);
        ParseObject.registerSubclass(Course.class);
        ParseObject.registerSubclass(Comment.class);
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
*/

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.inject(this);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the log in activity
                finish();

            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }
        _signupButton.setEnabled(false);
/*
        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme_AppBarOverlay);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();
*/
        String name = _nameText.getText().toString().toLowerCase();
        String email = _emailText.getText().toString().toLowerCase();
        String password = _passwordText.getText().toString();


        ParseUser currentUser =  ParseUser.getCurrentUser();
        if(currentUser != null)
            currentUser.logOut();

        Log.d("SignUp", "Trying to create new user");
        ParseUser newUser = new ParseUser();
        newUser.setUsername(name);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e==null){  //signup sucessfull
                    Log.d("SignUp", "Sign Up Successful");
                    onSignupSuccess();
                }
                else{         //sign up fail
                    if(e.getCode() ==203) //if email is taken
                        _emailText.setError("Email is taken");
                    if(e.getCode() == 202) //if username is taken
                        _nameText.setError("Username is taken");

                    Log.d("SignUp", "Sign Up Fail: "+e.getMessage());
                    onSignupFailed();
                }
            }
        });

/*
        new android.os.Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        // On complete call either onSigupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                       // progressDialog.dismiss();
                    }
                }, 3000);
                */
    }

    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Signup failed", Toast.LENGTH_LONG).show();
        _signupButton.setEnabled(true);
    }



    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (name.isEmpty() || name.length() < 4) {
            _nameText.setError("At least 4 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("Enter a valid UCSD email address");
            valid = false;
        }
        else if(!email.endsWith("@ucsd.edu") && !email.endsWith("@gmail.ucsd.edu")){
            _emailText.setError("Enter a valid UCSD email address");
        }
        else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("Between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }
        return valid;
    }
}
