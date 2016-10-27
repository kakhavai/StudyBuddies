package kakhavai.calpoly.edu.studybuddy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;
import android.widget.Toast;

/**
 * Calendar Class. Allows for specific day to be selected on calendar.
 * @Author: Ani
 */

public class CalendarActivity extends AppCompatActivity {
    CalendarView calendar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_view);

        calendar = (CalendarView)findViewById(R.id.calendarSetup);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                Toast.makeText(getApplicationContext(), "Gotta implement next screen", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
