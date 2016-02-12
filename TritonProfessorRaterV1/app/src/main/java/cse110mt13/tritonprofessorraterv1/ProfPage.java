package cse110mt13.tritonprofessorraterv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ProfPage extends AppCompatActivity {

    string[] comData=("Comment#1", "Comment#2", "Comment#3");
    ListView l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_page);
        l=(ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.single_comment, R.id.textView, comData);
        l.setAdapter(adapter);

    }
}
