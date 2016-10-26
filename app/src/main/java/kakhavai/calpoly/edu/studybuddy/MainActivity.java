package kakhavai.calpoly.edu.studybuddy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> classes = new ArrayList<String>();
    private ClassListAdapter classAdapter;
    private ListView classListView;
    private static final int REQUEST_CODE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        showActionBar();

        //Linking the adapter to the listview for classes
        classListView = (ListView)findViewById(R.id.classListView);
        classAdapter = new ClassListAdapter(this, classes);
        classListView.setAdapter(classAdapter);

        buildFabOnClick();

    }

    /**
     * This method provides an action bar with the necessary items
     * The ids of which are calendar, addClass, and settings
     * @Author: Kian
     */
    private void showActionBar() {
        LayoutInflater inflator = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.actionbar, null);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled (false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setCustomView(v);
    }

    /**
     * This method will build the onclick functionality for the fab
     * @Authors: Kian, Ani
     */
    private void buildFabOnClick(){
        ImageButton fab = (ImageButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //adds an intent to trigger the add class screen (ClassAdderView activity)
                Intent classAdder = new Intent(getApplicationContext(), ClassAdderView.class);
                startActivityForResult(classAdder, REQUEST_CODE);
            }
        });
    }

    /**
     * onActivityResult is when the Add Class button is clicked. It gets the string via intent
     * and adds the string to the class list adapter.
     * @param requestCode
     * @param resultCode
     * @param classAdderIntent
     * @Author: Ani
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent classAdderIntent){
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            String className = classAdderIntent.getExtras().getString("class name");
            classAdapter.add(className);
        }
    }

}
