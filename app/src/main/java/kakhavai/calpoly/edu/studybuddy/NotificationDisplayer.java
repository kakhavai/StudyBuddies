package kakhavai.calpoly.edu.studybuddy;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

/**
 * Actually gets the notification and displays it via the use of a service. Will also display
 * when app is in background.
 * @Author: Ani
 */

public class NotificationDisplayer extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent i = new Intent(this, testsAndAssignments.class);
        NotificationManager mNotificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent pi = PendingIntent.getActivity(this, 101, i, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notification = new NotificationCompat.Builder(this).setContentIntent(pi).setContentTitle("New Notification")
                .setContentText("YOU GOT STUFF TO DO").setSmallIcon(R.drawable.ic_hot_tub_black_24dp).setAutoCancel(true);
        mNotificationManager.notify(101, notification.build());

        return super.onStartCommand(intent, flags, startId);
    }
}
