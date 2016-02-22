package cse110mt13.tritonprofessorraterv1;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    EditText etSearch;
    ProfList nameList;
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

        Fragment main = new MainFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, main);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(null);
        ft.commit();

        setupSearchButton();

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.single_row,R.id.textView8,data);

        //l.setAdapter(adapter);
        etSearch = (EditText)findViewById(R.id.search_ET);
        Resources res = getResources();
        nameList = new ProfList();
        //Log.d("ratingTest", "Easiness: " + nameList.professors.g);
        String easy;
        String help;
        String clear;
        for(Professor name: nameList.professors){
            String convertedName = TextParser.convertToUpperCase(name.getName());
            profNames.add(convertedName);
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

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(getApplicationContext(), ((TextView)view).getText(),
             //           Toast.LENGTH_SHORT).show();
             //  String sText = ((TextView) view).getText().toString();
                Intent intent = null;
                intent = new Intent(getBaseContext(), ProfPage.class);
                if(intent != null)
                    startActivity(intent);

            }
        });

     //   getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
                Intent searchIntent = new Intent(MainActivity.this, SearchPage.class);
                searchIntent.putExtra("search", etSearch.getText().toString());
               // finish();
                startActivity(searchIntent); //create a new activity*/
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.action_call:
                Intent dialer= new Intent(Intent.ACTION_DIAL);
                startActivity(dialer);
                return true;
            case R.id.action_speech:
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                startActivityForResult(intent, 1234);

                return true;
            case R.id.action_contacts:
                Toast.makeText(getApplicationContext(),"Contacts Clicked",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings:
                Toast.makeText(getApplicationContext(),"Settings Clicked",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_status:
                Toast.makeText(getApplicationContext(),"Status Clicked",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1234 && resultCode == RESULT_OK) {
            String voice_text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).get(0);
            Toast.makeText(getApplicationContext(),voice_text,Toast.LENGTH_LONG).show();

        }
    }
}
