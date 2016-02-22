package cse110mt13.tritonprofessorraterv1;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProfPage extends AppCompatActivity {

    String[] comData;
    ArrayList<Comment> coList;
    ListView list;
    String[] courseName;
    int[] num;
    ProfList nameList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_page);
        /*l=(ListView) findViewById(R.id.list_view_prof_comment);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.single_comment, R.id.textView, comData);
        l.setAdapter(adapter);*/
        //l.setAdapter(adapter);
        Resources res = getResources();

        String profID = "";
        Intent intentBundle = getIntent();
        if(intentBundle.hasExtra("profID")) {  //ProfID is stored to profID
            profID = intentBundle.getStringExtra("profID");
        }



        nameList = new ProfList();
        coList = nameList.getComments(profID);
        for(int i = 0; i < coList.size(); i++){
            comData[i] = coList.get(i).getComment();
            num[i] = coList.get(i).getNumLikes();
            if(coList.get(i).getClassName()!= null){
                courseName[i] = coList.get(i).getClassName();
            }
        }
        list= (ListView) findViewById(R.id.list_view_prof_comment);
        MyAdapter adapter = new MyAdapter(this, courseName, num, comData);
        list.setAdapter(adapter);

       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }
    class MyAdapter extends ArrayAdapter<String>
    {
        Context context;
        String[] comArray;
        String[] courseArray;
        int[] numArray;
        MyAdapter(Context c, String[] courseName, int[] num, String[] comData)
        {
            super(c, R.layout.single_row, R.id.className, courseName);
            this.context = c;
            this.comArray = comData;
            this.courseArray= courseName;
            this.numArray = num;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.prof_layout, parent, false);
            TextView myNum= (TextView)row.findViewById(R.id.numOfLikes);
            TextView myCourseName = (TextView) row.findViewById(R.id.className);
            TextView myComment = (TextView) row.findViewById(R.id.commentText);

            myNum.setText(numArray[position]);
            myCourseName.setText(courseArray[position]);
            myComment.setText(comArray[position]);

            return row;
        }
    }
}
