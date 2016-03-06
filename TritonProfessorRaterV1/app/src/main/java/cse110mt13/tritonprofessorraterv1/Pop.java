package cse110mt13.tritonprofessorraterv1;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseQuery;

import org.json.JSONArray;

public class Pop extends AppCompatActivity {

    Comment toBeEdited;
    String commentID;
    String profID;
    EditText etEditComment;
    @Override

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popwindow);
        etEditComment = (EditText)findViewById(R.id.editComment);
        findViewById(R.id.editSubmit).setOnClickListener(onclickListener);
        findViewById(R.id.editCancel).setOnClickListener(onclickListener);

        Intent intentBundle = getIntent();
        //  if(intentBundle.hasExtra("profID")) {  //ProfID is stored to profID
        commentID = intentBundle.getStringExtra("commentID");
        profID = intentBundle.getStringExtra("profID");
        ParseQuery<Comment> query = ParseQuery.getQuery(Comment.class);
        try{
            toBeEdited = query.get(commentID);
        }
        catch(ParseException e){
            Log.e("get Comment error", e.getMessage());
        }

        etEditComment.setText(toBeEdited.getComment());

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.5));

    }

    private View.OnClickListener onclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.editSubmit:
                    onSubmitPress();
                case R.id.editCancel:
                    onCancelPress();
            }
        }
    };

    private void onSubmitPress(){
        toBeEdited.setComment(etEditComment.getText().toString());
        toBeEdited.setNumLikes(0);
        toBeEdited.put("UsersLiked", new JSONArray());
        try {
            toBeEdited.save();
        }
        catch(ParseException e){
            Log.e("failed to save comment", e.getMessage());
        }
        BackToProfPage();
    }

    private void onCancelPress(){
        BackToProfPage();
    }

    public void BackToProfPage() {
        Intent intent = null;
        intent = new Intent(getBaseContext(), ProfPage.class);
        intent.putExtra("profID", profID);
        finish(); //end current activity
        if(intent != null)
            startActivity(intent);
        //  Intent intent = new Intent(AddComment.this, ProfPage.class);
        //  intent.putExtra("profID", profID);
        //  startActivity(intent);
    }


}
