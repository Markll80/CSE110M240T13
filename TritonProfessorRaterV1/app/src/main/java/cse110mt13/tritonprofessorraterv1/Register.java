package cse110mt13.tritonprofessorraterv1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class Register extends AppCompatActivity {

    Button bRegister;
    Button bCancel;
    EditText etUsername;
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        bRegister = (Button) findViewById(R.id.bRegister);
        bCancel = (Button) findViewById(R.id.reg_Cancel);
        etUsername = (EditText) findViewById(R.id.username);
        etPassword = (EditText) findViewById(R.id.password);

        Intent transition = getIntent();
        Bundle username = transition.getExtras();
        if (!username.isEmpty()) {
            etUsername.setText(username.getString("username"));
        }


        setupButtons();

    }

    private void setupButtons() {
        bCancel.setOnClickListener(onclickListener);
        bRegister.setOnClickListener(onclickListener);

    }


    private View.OnClickListener onclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bRegister:
                    finish();
                    startActivity(new Intent(Register.this, MainActivity.class));
                    break;
                case R.id.reg_Cancel:
                    finish();
                    startActivity(new Intent(Register.this, LogIn.class));
                    break;
            }
        }
    };

    private void createUser(String username, String password, String email) {
        //Creating new users
        ParseUser.logOut();  //important!!!!!!!!!!!
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(getApplicationContext(), "Sign Up Sucessful! Please go to your e-mail and verify your account!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Error: Unable to create account!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}