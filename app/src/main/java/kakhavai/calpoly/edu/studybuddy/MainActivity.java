package kakhavai.calpoly.edu.studybuddy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Class> classes;
    private ClassListAdapter classAdapter;
    private ListView classListView;
    private static final int REQUEST_CODE = 10;

    /**
     * Class "Class" which allows us to instantiate an object of type class. It can hold the name of
     * the class, and added tests/assignments as indicated by the user.
     */
    static class Class implements Parcelable{
        ArrayList<String> tests; //holds the tests
        ArrayList<String> assignments; //holds the assignments
        String classname; //holds the name of the class

        public Class(String name){
            this.classname = name;
        }

        public void setClassName(String classname){
            this.classname = classname;
        }

        public String getClassName(){
            return classname;
        }

        public void setTests(ArrayList<String> tests){
            this.tests = tests;
        }

        public ArrayList<String> getTests(){
            return tests;
        }

        public void setAssignments(ArrayList<String> assignments){
            this.assignments = assignments;
        }

        public ArrayList<String> getAssignments(){
            return assignments;
        }

        public int describeContents(){
            return 0;
        }

        public void writeToParcel(Parcel out, int flags){
            out.writeString(classname);
            out.writeStringList(tests);
            out.writeStringList(assignments);
        }

        public static final Parcelable.Creator<Class> CREATOR = new Parcelable.Creator<Class>(){
            public Class createFromParcel(Parcel in){
                Class e = new Class(null);
                String data = in.readString();
                ArrayList<String> t = in.createStringArrayList();
                ArrayList<String> a = in.createStringArrayList();
                e.setClassName(data);
                e.setTests(t);
                e.setAssignments(a);
                return e;
            }

            @Override
            public Class[] newArray(int size) {
                return new Class[size];
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        showActionBar();

        classes = new ArrayList<Class>();
        //Linking the adapter to the listview for classes
        classListView = (ListView)findViewById(R.id.classListView);
        classAdapter = new ClassListAdapter(this, classes);
        classListView.setAdapter(classAdapter);

        buildFabOnClick();

    }

    /**
     * This method provides an action bar with the necessary items
     * The ids of which are calendar, addClass, and settings
     * @Author: Kian, Ani
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

        /**
         * Instantiating the TextView Objects on the actionbar
         */
        TextView addClass = (TextView)v.findViewById(R.id.addClass);
        TextView calendar = (TextView)v.findViewById(R.id.calendar);

        /**
         * Pops calendar intent when clicked on "calendar"
         */
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent calendarStarter = new Intent(getApplicationContext(), CalendarActivity.class);
                startActivity(calendarStarter);
            }
        });

        /**
         * pops add class intent when clicked on "add class"
         */
        addClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent classAdder = new Intent(getApplicationContext(), ClassAdderView.class);
                startActivityForResult(classAdder, REQUEST_CODE);
            }
        });

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
            Class className = classAdderIntent.getExtras().getParcelable("class name");
            classAdapter.add(className);
        }
    }
}
