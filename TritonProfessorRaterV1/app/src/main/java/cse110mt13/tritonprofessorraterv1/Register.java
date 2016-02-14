package cse110mt13.tritonprofessorraterv1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity implements View.OnClickListener{

    Button bRegister;
    EditText etUsername;
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        bRegister = (Button)findViewById(R.id.bRegister);
        etUsername = (EditText)findViewById(R.id.username);
        etPassword = (EditText)findViewById(R.id.password);

        Intent transition = getIntent();
        Bundle username = transition.getExtras();
        if(!username.isEmpty()){
            etUsername.setText(username.getString("username"));
        }
    }


    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.bRegister:
                Intent loginTransition = new Intent(this, LogIn.class);
                startActivity(loginTransition);
                break;
        }

    }
}