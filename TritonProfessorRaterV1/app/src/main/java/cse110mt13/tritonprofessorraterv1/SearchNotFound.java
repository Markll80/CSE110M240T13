package cse110mt13.tritonprofessorraterv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearchNotFound extends AppCompatActivity {

    EditText etSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_not_found);

        EditText searchString = (EditText)findViewById(R.id.searchString_TV);
        etSearch = (EditText)findViewById(R.id.search_ET);
        /* TODO: get the string being searched, and substitute the current string */


        findViewById(R.id.search_B).setOnClickListener(onclickListener);
        findViewById(R.id.add_Prof_B).setOnClickListener(onclickListener);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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



}
