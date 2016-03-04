package cse110mt13.tritonprofessorraterv1;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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


import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ProfPage extends AppCompatActivity {

    String[] comData;
    ArrayList<Comment> coList;
    ListView list;
    String[] courseName;
    int[] num;
    ProfList nameList;
    //TextView clarityTV;
  //  TextView easinessTV;
  //  TextView helpfullnessTV;
    String profID = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_page);
        Resources res = getResources();

      //  String profID = "";
        Intent intentBundle = getIntent();
      //  if(intentBundle.hasExtra("profID")) {  //ProfID is stored to profID
          final String profid2 = intentBundle.getStringExtra("profID");
          profID = intentBundle.getStringExtra("profID");
      //  }

        TextView profName = (TextView)findViewById(R.id.Prof_name);
      /*  TextView easiness_v = (TextView)findViewById(R.id.easiness_val);
        TextView helpfulness_v = (TextView)findViewById(R.id.helpfulness_v);
        TextView clarity_v = (TextView)findViewById(R.id.clarity_v);*/
        TextView easiness_v = (TextView)findViewById(R.id.Prof_Ess);
        TextView helpfulness_v = (TextView)findViewById(R.id.Prof_Hlp);
        TextView clarity_v = (TextView)findViewById(R.id.Prof_Clt);
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
        if(prof.getNumRatings() >= 1) {
            easiness_v.setText("Easiness: " + String.valueOf(prof.getEasiness()));
            helpfulness_v.setText("Helpfulness: " + String.valueOf(prof.getHelpfulness()));
            clarity_v.setText("Clarity: " + String.valueOf(prof.getClarity()));
        }
        else{
            easiness_v.setText("Easiness: N/A" );
            helpfulness_v.setText("Helpfulness: N/A");
            clarity_v.setText("Clarity: N/A");
        }

        nameList = new ProfList();
        coList = prof.getStringComments();

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
        list.setEnabled(false);
        list.setFocusable(false);



        MyAdapter adapter = new MyAdapter(this, courseName, num, comData);
        list.setAdapter(adapter);
        list.setOnTouchListener(new View.OnTouchListener() {

            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
              //  Log.d("Test1", "Here!");
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        ProfPage.setListViewHeightBasedOnChildren(list);


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
            case R.id.action_settings:
                goSetting();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void refresh () {
        Intent intent = new Intent(ProfPage.this, ProfPage.class);
        finish();
        intent.putExtra("profID",profID);
        startActivity(intent);
    }

    public void goSetting() {
        Intent intent = new Intent(ProfPage.this, SettingActivity.class);
        startActivity(intent);
    }

    private static void setListViewHeightBasedOnChildren(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight()+ 30;
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
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

        private boolean onLikePress(String commentID){
            boolean removedPresser = false;
            ParseQuery<Comment> query= ParseQuery.getQuery(Comment.class);
            Comment comment = new Comment();
            try{
                comment = query.get(commentID);
            }
            catch(ParseException e){
                Log.e("getCommentFromId error", e.getMessage());
            }
            String presser  = ParseUser.getCurrentUser().getObjectId();
            JSONArray usersLiked = comment.getUsersLiked();
            for(int i = 0; i < usersLiked.length(); ++i){
                try {
                    if (((String) usersLiked.get(i)).equals(presser)){
                        usersLiked.remove(i);
                        comment.subNumLikes();
                        removedPresser = true;
                        break;
                    }
                }
                catch(JSONException e){
                    Log.e("JSONArray Error", e.getMessage());
                }
            }
            if(!removedPresser) {
                usersLiked.put(presser);
                comment.addNumLikes();
            }
            comment.put("UsersLiked", usersLiked);
            try{
                comment.save();
            }
            catch(ParseException e){
                Log.e("comment save error", e.getMessage());
            }
            return removedPresser;
        }

        private void onReportPress(String commentID){
            ParseQuery<Comment> query= ParseQuery.getQuery(Comment.class);
            Comment comment = new Comment();
            try{
                comment = query.get(commentID);
            }
            catch(ParseException e){
                Log.e("getCommentFromId error", e.getMessage());
            }
            String presser  = ParseUser.getCurrentUser().toString();
            JSONArray usersReported = comment.getUsersReported();
            for(int i = 0; i < usersReported.length(); ++i){
                try {
                    if (((String) usersReported.get(i)).equals(presser)){
                        usersReported.remove(i);
                        return;
                    }
                }
                catch(JSONException e){
                    Log.e("JSONArray Error", e.getMessage());
                }
            }
            usersReported.put(presser);
            if(usersReported.length() > 0){
                comment.deleteSelf();
            }
            else{
                comment.put("UsersReported", usersReported);
                try{
                    comment.save();
                }
                catch(ParseException e){
                    Log.e("save comment failed", e.getMessage());
                }
            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.linear_prof_layout, parent, false);
            final TextView myNum= (TextView)row.findViewById(R.id.numOfLikes);
            TextView myCourseName = (TextView) row.findViewById(R.id.className);
            TextView myComment = (TextView) row.findViewById(R.id.commentText);
            myCourseName.setText(courseArray[position]);
            myComment.setText(comArray[position]);
            myNum.setText("" + num[position]);
            TextView reportB = (TextView)row.findViewById(R.id.Report);
            TextView likeB = (TextView)row.findViewById(R.id.Like);
            TextView editB = (TextView)row.findViewById(R.id.Edit);
            final int anotherPostion = position;
            reportB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String commentID = coList.get(anotherPostion).getObjectId();
                    onReportPress(commentID);
                }
            });

            likeB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String commentID = coList.get(anotherPostion).getObjectId();
                    boolean decrement = onLikePress(commentID);
                    if(decrement) {
                        myNum.setText("" + (Integer.parseInt(myNum.getText().toString()) - 1));
                    }
                    else{
                        myNum.setText("" + (Integer.parseInt(myNum.getText().toString()) + 1));
                    }
                }
            });

            editB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String commentID = coList.get(anotherPostion).getObjectId();
                }
            });

            return row;
        }
    }

}
