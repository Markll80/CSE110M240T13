package cse110mt13.tritonprofessorraterv1;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
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
        Resources res = getResources();

      //  String profID = "";
        Intent intentBundle = getIntent();
      //  if(intentBundle.hasExtra("profID")) {  //ProfID is stored to profID
          final String profID = intentBundle.getStringExtra("profID");
      //  }

        TextView profName = (TextView)findViewById(R.id.Prof_name);
        TextView easiness_v = (TextView)findViewById(R.id.easiness_val);
        TextView helpfulness_v = (TextView)findViewById(R.id.helpfulness_v);
        TextView clarity_v = (TextView)findViewById(R.id.clarity_v);
        Button addratingButton = (Button)findViewById(R.id.addRatingButton);
        addratingButton.setOnClickListener((new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        //  finish();
                        Intent intent = new Intent(ProfPage.this, AddComment.class);
                        intent.putExtra("profID", profID);
                        startActivity(intent);
                    }
                })
        );




        Professor prof = Professor.getProf(profID);  //set
        profName.setText(prof.getName());
        easiness_v.setText(String.valueOf(prof.getEasiness()));
        helpfulness_v.setText(String.valueOf(prof.getHelpfulness()));
       clarity_v.setText(String.valueOf(prof.getClarity()));

        nameList = new ProfList();
        coList = nameList.getComments(profID);

        int size = coList.size();
        Log.d("Comments", "comment size:" + size);
        comData = new String[size];
        courseName = new String[size];
        num = new int[size];


        for(int i = 0; i < coList.size(); i++){
            comData[i] = coList.get(i).getComment();
          //  Log.d("ProfPage", comData[i]);
            num[i] = coList.get(i).getNumLikes();
            courseName[i] = coList.get(i).getCourseName();
        }
        list= (ListView) findViewById(R.id.list_view_prof_comment);
        list.setOnTouchListener(new View.OnTouchListener() {
                                    // Setting on Touch Listener for handling the touch inside ScrollView
                                    @Override
                                    public boolean onTouch(View v, MotionEvent event) {
                                        // Disallow the touch request for parent scroll on touch of child view
                                        v.getParent().requestDisallowInterceptTouchEvent(true);
                                        return false;
                                    }
                                });
        setListViewHeightBasedOnChildren(list);

            MyAdapter adapter = new MyAdapter(this, courseName, num, comData);
            list.setAdapter(adapter);


            //   getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        }

    /**** Method for Setting the Height of the ListView dynamically.
     **** Hack to fix the issue of not showing all the items of the ListView
     **** when placed inside a ScrollView  ****/
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, AbsListView.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
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
          //  myNum.setText(numArray[position]);             //TODO : CAUSING CRASH
            myCourseName.setText(courseArray[position]);
            myComment.setText(comArray[position]);

            return row;
        }
    }
}
