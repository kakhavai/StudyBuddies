package kakhavai.calpoly.edu.studybuddy;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> classes = new ArrayList<String>();
    private ClassListAdapter classAdapter;
    private ListView classListView;

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
     * @Author: Kian
     */
    private void buildFabOnClick(){
        ImageButton fab = (ImageButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                classAdapter.add("class1");
            }
        });
    }

}
