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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        bLogin = (Button)findViewById(R.id.bLogin);
        bRegister = (Button)findViewById(R.id.bRegister);
        etEmail = (EditText)findViewById(R.id.username);
        etPassword = (EditText)findViewById(R.id.etPassword);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.bRegister:
                startActivity(new Intent(this, Register.class));
                break;
        }

    }

}
