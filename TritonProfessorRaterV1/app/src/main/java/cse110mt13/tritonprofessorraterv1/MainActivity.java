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
   final int LIST_LIMIT = 10;
    ListView list;
    //String[] profNames;
    Vector<String> profNames =  new Vector<>();
    Vector<String> easiness = new Vector<>();
    Vector<String> helpfulness = new Vector<>();
    Vector<String> clarity = new Vector<>();
    ProfList profs;
    EditText etSearch;
    ProfList nameList;
    int[] images = {R.mipmap.defaultprofile,R.mipmap.defaultprofile};
    //int[] images = {R.drawable.name1, R.drawable.name2, R.drawable.name3};
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment main = new MainFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, main);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(null);
        ft.commit();
//        Log.d("User","Current Username: "+ ParseUser.getCurrentUser().getUsername());
        setupSearchButton();

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.single_row,R.id.textView8,data);

        //l.setAdapter(adapter);
        etSearch = (EditText)findViewById(R.id.search_ET);
        Resources res = getResources();
        nameList = new ProfList();
        nameList.nameSearch("");//make a list containing all profs
        //Log.d("ratingTest", "Easiness: " + nameList.professors.g);
        String easy;
        String help;
        String clear;
        int counter = 0;
        for(Professor name: nameList.professors){
           if(counter >= LIST_LIMIT) break;
            profNames.add(name.getName());
            easy = "Easiness : " + name.getEasiness();
            help = "Helpfulness : " + name.getHelpfulness();
            clear = "Clarity : " + name.getClarity();
            easiness.add(easy);
            helpfulness.add(help);
            clarity.add(clear);
            ++counter;
        }

        list= (ListView) findViewById(R.id.listView);
        MyAdapter adapter = new MyAdapter(this, profNames, images, easiness, helpfulness, clarity);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(getApplicationContext(), ((TextView)view).getText(),
             //           Toast.LENGTH_SHORT).show();
             //  String sText = ((TextView) view).getText().toString();
                Intent intent = null;
                intent = new Intent(getBaseContext(), ProfPage.class);
                intent.putExtra("profID",nameList.professors.get(position).getObjID());
                Log.d("Intent",nameList.professors.get(position).getObjID());
                if(intent != null)
                    startActivity(intent);

            }
        });

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
            myImage.setImageResource(images[0]);
            myProfNames.setText(nameArray.get(position));

            if(nameList.professors.get(position).getNumRatings() >=1){
                myEasiness.setText(easyR.get(position));
                myHelpfulness.setText(helpfulR.get(position));
                myClarity.setText(clarityR.get(position));
            }
            else{
                myEasiness.setText("Easiness: N/A");
                myHelpfulness.setText("Helpfulness: N/A");
                myClarity.setText("Clarity: N/A");
            }




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
        switch (item.getItemId()) {
            case R.id.action_refresh:
                refresh();
                return true;
            case R.id.action_Logout:
                logout();
                return true;
        }
        return false;
       // return super.onOptionsItemSelected(item);
    }

    public void refresh () {
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        finish();
        startActivity(intent);
    }
    
    public void logout() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        if(ParseUser.getCurrentUser() != null) {
            Log.d("Test123","Logging out");
            ParseUser.logOut();
        }
        finish();
        startActivity(intent);
    }

}
