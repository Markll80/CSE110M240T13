package cse110mt13.tritonprofessorraterv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseUser;

public class SearchNotFound extends AppCompatActivity {

    EditText etSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_not_found);

        TextView searchString = (TextView)findViewById(R.id.searchString_TV);
        etSearch = (EditText)findViewById(R.id.search_ET);
        /* TODO: get the string being searched, and substitute the current string */

        Intent intent = getIntent();
        searchString.setText(intent.getStringExtra("search"));

        findViewById(R.id.search_B).setOnClickListener(onclickListener);
        findViewById(R.id.add_Prof_B).setOnClickListener(onclickListener);

       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
}


    private View.OnClickListener onclickListener = new View.OnClickListener() {
    @Override
       public void onClick(View v) {
           switch (v.getId()) {
               case R.id.search_B:
                 /*  TODO: write backend function to determine whether clicking on search
                   should jump to none found page, or search-found page
                 */
                   Intent searchIntent = new Intent(SearchNotFound.this, SearchPage.class);
                   searchIntent.putExtra("search", etSearch.getText().toString());
                   finish(); //end current activity
                   startActivity(searchIntent); //create a new activity*/
                   break;
               case R.id.add_Prof_B:
                   finish(); //end current activity
                   startActivity(new Intent(SearchNotFound.this, AddProf.class)); //create a new activity
                   break;
           }
        }
    };

    public void ForwardToAddProf(View view) {
        startActivity(new Intent(this, AddProf.class));
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
        Intent intent = new Intent(SearchNotFound.this, SearchNotFound.class);
        finish();
        startActivity(intent);
    }

    public void logout() {
        Intent intent = new Intent(SearchNotFound.this, LoginActivity.class);
        if(ParseUser.getCurrentUser() != null) {
            Log.d("Test123", "Logging out");
            ParseUser.logOut();
        }
        finish();
        startActivity(intent);
    }



}
