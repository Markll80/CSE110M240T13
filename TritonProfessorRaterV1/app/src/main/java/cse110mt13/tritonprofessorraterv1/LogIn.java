package cse110mt13.tritonprofessorraterv1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

public class LogIn extends AppCompatActivity implements View.OnClickListener{

    Button bLogin;
    Button bRegister;
    EditText etEmail;
    EditText etPassword;

    ProfList profs = new ProfList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        bLogin = (Button)findViewById(R.id.bLogin);
        bRegister = (Button)findViewById(R.id.bRegister);
        etEmail = (EditText)findViewById(R.id.username);
        etPassword = (EditText)findViewById(R.id.etPassword);
        for(Professor professor: profs.professors){
            System.out.println(professor.toString());
        }
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.bRegister:
                Bundle username = new Bundle();
                username.putString("username", etEmail.getText().toString());
                Intent registerTransition = new Intent(this, Register.class);
                registerTransition.putExtras(username);
                startActivity(registerTransition);
                break;
        }

    }

}
