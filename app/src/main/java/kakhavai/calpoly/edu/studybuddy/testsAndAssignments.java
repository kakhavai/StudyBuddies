package kakhavai.calpoly.edu.studybuddy;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import kakhavai.calpoly.edu.studybuddy.MainActivity.Class;

/**
 * Shows assignments and tests for each individual course. Also has buttons to add more assignments/tests.
 * @Author: Ani
 */

public class testsAndAssignments extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener{

    Button addToDoWork;
    ListView toDoList;
    Button removeAlarm;
    Button addAnAlarm;
    Context context;
    int index;
    Class currObject;
    int day = 0, year = 0, month = 0;
    int second = 0, minute = 0, hour = 0;
    AlarmManager alarm;
    PendingIntent intentV;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tests_assignments);
        this.context = this;
        addToDoWork = (Button)findViewById(R.id.addWorkButton);
        toDoList = (ListView)findViewById(R.id.toDoList);
        removeAlarm = (Button)findViewById(R.id.removeAlarm); // just for the sake of vertical prototype
        addAnAlarm = (Button)findViewById(R.id.addAnAlarm); //adds an alarm based on chosen time

        Bundle extras = getIntent().getExtras();
        /* uncomment when continuing (getting class object and index, which needs to be passed back)
        currObject = extras.getParcelable("object");
        index = extras.getInt("index");
        */

        //change button text to "add Assignment/Test"
        addToDoWork.setOnClickListener(this);
        removeAlarm.setOnClickListener(this);
        addAnAlarm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(v == addToDoWork){
            Calendar date = Calendar.getInstance();
            DatePickerDialog dateChooser = new DatePickerDialog(testsAndAssignments.this, testsAndAssignments.this,
                    date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH));
            dateChooser.show();
        }
        //removes the set alarm
        if(v == removeAlarm){
            alarm.cancel(intentV);
        }
        if(v == addAnAlarm){
            Calendar c = Calendar.getInstance();
            c.set(Calendar.DATE, day);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.YEAR, year);

            c.set(Calendar.HOUR_OF_DAY, hour);
            c.set(Calendar.MINUTE, minute);
            c.set(Calendar.SECOND, second);

            Log.i(""+month, ""+Calendar.OCTOBER);
            Toast.makeText(this.context, "alarm set to: "+hour+":"+minute, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this.context, notificationUpdater.class);
            /*intentV = PendingIntent.getBroadcast(testsAndAssignments.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);*/

            final int _id = (int) System.currentTimeMillis();
            intentV = PendingIntent.getBroadcast(this, _id, intent,PendingIntent.FLAG_ONE_SHOT);
            alarm = (AlarmManager)getSystemService(ALARM_SERVICE);
            alarm.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), intentV);
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        month = i1;
        year = i;
        day = i2;
        Calendar date = Calendar.getInstance();
        TimePickerDialog timePicker = new TimePickerDialog(testsAndAssignments.this, testsAndAssignments.this,
                date.get(Calendar.HOUR), date.get(Calendar.MINUTE), true);
        timePicker.show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        minute = i1;
        second = 0;
        hour = i;
    }
}
