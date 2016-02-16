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

public class SearchPage extends AppCompatActivity {
    //UI--- search page
    int[] searcImages = {R.drawable.name1, R.drawable.name2, R.drawable.name3};
    String[] searchNames;
    String[] searchDesc;
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
        searchNames = res.getStringArray(R.array.prof_name);
        searchDesc = res.getStringArray(R.array.descriptions);
        //get listview for main page
        l= (ListView) findViewById(R.id.listViewSearch_page);
        MyAdapter adapter = new MyAdapter(this, searchNames, searcImages, searchDesc);
        l.setAdapter(adapter);
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
