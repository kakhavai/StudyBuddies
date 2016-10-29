package kakhavai.calpoly.edu.studybuddy;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.Calendar;

/**
 * gets the Alarm broadcast from the intent in testsAndAssignments and passes intent to notification displayer.
 * @Author: Ani
 */

public class notificationUpdater extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("hi", "yolo"+Calendar.JANUARY);
        Intent transferToService = new Intent(context, NotificationDisplayer.class);
        context.startService(transferToService);
        /*Intent i = new Intent(context, testsAndAssignments.class);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent pi = PendingIntent.getActivity(context, 101, i, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notification = new NotificationCompat.Builder(context).setContentIntent(pi).setContentTitle("New Notification")
                .setContentText("YOU GOT STUFF TO DO").setSmallIcon(R.drawable.ic_hot_tub_black_24dp).setAutoCancel(true);
        mNotificationManager.notify(101, notification.build());*/
    }
}
