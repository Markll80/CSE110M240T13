package cse110mt13.tritonprofessorraterv1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.regex.Pattern;

public class LogIn extends AppCompatActivity  {
/*
    Button bLogin;
    Button bRegister;
    EditText etEmail;
    EditText etPassword;
    TextParser textParser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Parse.enableLocalDatastore(this);
        //ParseObject.registerSubclass(Professor.class);
        Parse.initialize(this);
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        ParseACL.setDefaultACL(defaultACL, true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        textParser = new TextParser();
        bLogin = (Button) findViewById(R.id.bLogin);
        bRegister = (Button) findViewById(R.id.bRegister);
        etEmail = (EditText) findViewById(R.id.username);
        etPassword = (EditText) findViewById(R.id.etPassword);     */
     /*   for(Professor professor: profs.professors){
            System.out.println(professor.toString());
        }*/

    /*    setupButtons();

    }

    private void setupButtons(){
        bLogin.setOnClickListener(onclickListener);
        bRegister.setOnClickListener(onclickListener);

    }

    private View.OnClickListener onclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.bLogin:
                    String email = etEmail.getText().toString();
                    String password = etPassword.getText().toString();
                    boolean validEmail = textParser.validEmail(email);
                    boolean validPassword = textParser.validPassword(password);
                    //display message if not valid password or email
                    if(!validEmail || !validPassword || !validUser(email, password)){

                        etPassword.clearComposingText();
                        Toast.makeText(getApplicationContext(), "Invalid Email or password", Toast.LENGTH_LONG).show();

                    }
                    else{ //if is validUser, go to mainpage

                        finish(); //end current activity
                        startActivity(new Intent(LogIn.this, MainActivity.class));
                    }
                    break;

                case R.id.bRegister:
                    Bundle username = new Bundle();
                    username.putString("username", etEmail.getText().toString());
                    Intent registerTransition = new Intent(LogIn.this, Register.class);
                    registerTransition.putExtras(username);
                    startActivity(registerTransition);
                    break;
            }
        }
    };


    private boolean validUser(String username, String password){


        return true;
    } */
}

