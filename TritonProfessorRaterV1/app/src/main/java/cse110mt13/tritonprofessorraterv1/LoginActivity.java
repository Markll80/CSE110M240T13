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

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Cherish on 16/2/15.
 */
public class LoginActivity extends AppCompatActivity{
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @InjectView(R.id.input_email) EditText _emailText;
    @InjectView(R.id.input_password) EditText _passwordText;
    @InjectView(R.id.btn_login) Button _loginButton;
    @InjectView(R.id.link_signup) TextView _signupLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Parse.enableLocalDatastore(this);
        Parse.initialize(this);
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
      //  ParseACL.setDefaultACL(defaultACL, true);

        ParseUser currentUser = ParseUser.getCurrentUser();
        if(currentUser != null){

        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        ButterKnife.inject(this);
        _loginButton.setOnClickListener(onclickListener);
        _signupLink.setOnClickListener(onclickListener);
/*
        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("LoginTest", "1");
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });*/
    }

    private View.OnClickListener onclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btn_login:    //when login button is clicked,
                    login();
                    break;
                case R.id.link_signup:  //simpy jump to register page when signup link is clicked
                    finish();
                    startActivity(new Intent(LoginActivity.this, Register.class));
                    break;
            }
        }

    };

    public void login() {
        Log.d(TAG, "Login");
        if (!validate()) {
            onLoginFailed();
            return;
        }
        _loginButton.setEnabled(false);
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_AppBarOverlay);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

    }


/*
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        Log.d("LoginTest", "5");
                        progressDialog.dismiss();
                    }
                }, 3000);

    }
    */
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {
                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
              //  this.finish();
               // startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        }
    }
*/
    @Override
    public void onBackPressed(){
        //disable going back to the MainActivity
        moveTaskToBack(true);

    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        finish(); //end current activity
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        _loginButton.setEnabled(true);
    }

    public boolean validate() {
      /*
        boolean valid = false;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("Enter a valid email address");
            valid = false;
        }
        else if(!email.endsWith("@ucsd.edu") && !email.endsWith("@gmail.ucsd.edu")){
            _emailText.setError("Enter a valid UCSD email address");
        }
        else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 16) {
            _passwordText.setError("Between 4 and 16 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
        */
        return true;
    }

    private boolean validUser(String username, String password){


        return true;
    }

}
