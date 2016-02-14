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

public class MainActivity extends AppCompatActivity {

    ListView list;
    String[] profNames;
    String[] descriptions;
    ProfList profs;
    int[] images = {R.drawable.name1, R.drawable.name2, R.drawable.name3};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //parse setup
        Parse.enableLocalDatastore(this);
        ParseObject.registerSubclass(Professor.class);
        Parse.initialize(this);
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupSearchButton();

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.single_row,R.id.textView8,data);

        //l.setAdapter(adapter);
        Resources res = getResources();
        profNames = res.getStringArray(R.array.prof_name);
        descriptions = res.getStringArray(R.array.descriptions);

        list= (ListView) findViewById(R.id.listView);
        MyAdapter adapter = new MyAdapter(this, profNames, images, descriptions );
        list.setAdapter(adapter);
        profs = new ProfList();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < profs.professors.size(); i++){
                    Log.d("crashing", profs.professors.get(i).toString());
                }
            }
        }, 5000);
    }

    class MyAdapter extends ArrayAdapter<String>
    {
        Context context;
        int[] images;
        String[] nameArray;
        String[] descArray;
        MyAdapter(Context c, String[] prof_name, int imgs[], String[] desc)
        {
            super(c, R.layout.single_row, R.id.textView9, prof_name);
            this.context = c;
            this.nameArray = prof_name;
            this.images = imgs;
            this.descArray = desc;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.single_row, parent, false);
            ImageView myImage = (ImageView)row.findViewById(R.id.imageView);
            TextView myProfNames = (TextView) row.findViewById(R.id.textView9);
            TextView myDescription = (TextView) row.findViewById(R.id.textView10);

            myImage.setImageResource(images[position]);
            myProfNames.setText(nameArray[position]);
            myDescription.setText(descArray[position]);

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
