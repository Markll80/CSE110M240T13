package cse110mt13.tritonprofessorraterv1;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Vector;

public class SearchPage extends AppCompatActivity {
    //UI--- search page
    int[] searcImages = {R.drawable.name1, R.drawable.name2, R.drawable.name3};
    Vector<String> searchNames =  new Vector<>();
    Vector<String> easiness = new Vector<>();
    Vector<String> helpfulness = new Vector<>();
    Vector<String> clarity = new Vector<>();
    ListView l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        findViewById(R.id.search_B).setOnClickListener(onclickListener);
        findViewById(R.id.sp_AddProf_B).setOnClickListener(onclickListener);
        //initialization for main page;
        Resources res = getResources();
        ProfList nameList = new ProfList();
        String easy = "easiness : ";
        String help = "helpfulness : ";
        String clear = "clarity : ";
        for(Professor name: nameList.professors){
            searchNames.add(name.name);
            easy = easy + name.getEasiness();
            help = help + name.getHelpfulness();
            clear = clear + name.getClarity();
            easiness.add(easy);
            helpfulness.add(help);
            clarity.add(clear);
        }
        //get listview for main page
        l= (ListView) findViewById(R.id.listViewSearch_page);
        MyAdapter adapter = new MyAdapter(this, searchNames, searcImages,easiness, helpfulness, clarity);
        l.setAdapter(adapter);
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


    private View.OnClickListener onclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.search_B:
                     /*  TODO: write backend function to determine whether clicking on search
                   should jump to none found page, or search-found page
                 */
                    finish(); //end current activity
                    startActivity(new Intent(SearchPage.this, SearchPage.class)); //create a new activity
                    break;
                case R.id.sp_AddProf_B:
                    finish(); //end current activity
                    startActivity(new Intent(SearchPage.this, AddProf.class)); //create a new activity
                    break;
            }
        }
    };
}
