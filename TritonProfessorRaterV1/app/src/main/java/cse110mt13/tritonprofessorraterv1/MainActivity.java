package cse110mt13.tritonprofessorraterv1;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    ListView list;
    //String[] profNames;
    Vector<String> profNames =  new Vector<>();
    Vector<String> easiness = new Vector<>();
    Vector<String> helpfulness = new Vector<>();
    Vector<String> clarity = new Vector<>();
    ProfList profs;
    int[] images = {R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
    //int[] images = {R.drawable.name1, R.drawable.name2, R.drawable.name3};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        setContentView(R.layout.activity_main);

        setupSearchButton();

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.single_row,R.id.textView8,data);

        //l.setAdapter(adapter);
        Resources res = getResources();
        ProfList nameList = new ProfList();
        //Log.d("ratingTest", "Easiness: " + nameList.professors.g);
        String easy;
        String help;
        String clear;
        for(Professor name: nameList.professors){
            profNames.add(name.getName());
            easy = "easiness : " + name.getEasiness();
            help = "helpfulness : " + name.getHelpfulness();
            clear = "clarity : " + name.getClarity();
            easiness.add(easy);
            helpfulness.add(help);
            clarity.add(clear);
        }

        list= (ListView) findViewById(R.id.listView);
        MyAdapter adapter = new MyAdapter(this, profNames, images, easiness, helpfulness, clarity);
        list.setAdapter(adapter);
        Log.d("test", "hi");
       /*profs = new ProfList();
        //profs.nameSearch("B");
        for(int i = 0; i < profs.professors.size(); i++) {
            Log.d("searchTest", profs.professors.get(i).toString());
            ArrayList<Comment> testComments = profs.getComments(profs.professors.get(i).getObjectID());
            for (int j = 0; j < testComments.size(); j++) {
                Log.d("getCommentsTest", testComments.get(j).toString());
            }
        }*/
    }

    class MyAdapter extends ArrayAdapter<String>
    {
        Context context;
        int[] images;
        Vector<String> nameArray;
        Vector<String> easyR;
        Vector<String> helpfulR;
        Vector<String> clarityR;
        MyAdapter(Context c, Vector<String> prof_name, int imgs[], Vector<String> easiness, Vector<String> helpfulness, Vector<String> clarity)
        {
            super(c, R.layout.single_row, R.id.textView9, prof_name);
            this.context = c;
            this.nameArray = prof_name;
            this.images = imgs;
            this.easyR = easiness;
            this.helpfulR = helpfulness;
            this.clarityR = clarity;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.single_row, parent, false);
            ImageView myImage = (ImageView)row.findViewById(R.id.imageView);
            TextView myProfNames = (TextView) row.findViewById(R.id.textView9);
            TextView myEasiness = (TextView) row.findViewById(R.id.easiness);
            TextView myHelpfulness = (TextView) row.findViewById(R.id.helpfulness);
            TextView myClarity = (TextView) row.findViewById(R.id.clarity);
            myImage.setImageResource(images[position]);
            myProfNames.setText(nameArray.get(position));
            myEasiness.setText(easyR.get(position));
            myHelpfulness.setText(helpfulR.get(position));
            myClarity.setText(clarityR.get(position));

            return row;
        }
    }

    private void setupSearchButton() {
        Button searchB = (Button) findViewById(R.id.search_B);
        searchB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*  TODO: write backend function to determine whether clicking on search
                   should jump to none found page, or search-found page
                 */
                finish(); //end current activity
                startActivity(new Intent(MainActivity.this, SearchPage.class)); //create a new activity
            }
        });
        ;
    }
}
