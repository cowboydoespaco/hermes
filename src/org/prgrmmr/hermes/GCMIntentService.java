package org.prgrmmr.hermes;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Vibrator;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;

public class GCMIntentService extends GCMBaseIntentService {

    public GCMIntentService() {
        super(MainActivity.SENDER_ID);
    }

    @Override
    protected void onError(Context context, String errorId) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void onMessage(Context context, Intent intent) {
        Log.d(MainActivity.TAG, "Message Received: "+ intent.getStringExtra("message"));
        String msg = intent.getStringExtra("message");
        MessageDataSource.saveMessage(context, msg);
        NotificationManager manager = (NotificationManager) context
            .getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = prepareNotification(context, msg);
        manager.notify(R.id.notification_id, notification);
        Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long[] vibratePattern = {1000, 2000, 1000, 2000};
        vib.vibrate(vibratePattern, -1);
    }

    @Override
    protected void onRegistered(Context context, String regId) {
        Log.i(MainActivity.TAG, "Successfully registered?: "+ regId);

    }

    @Override
    protected void onUnregistered(Context context, String regId) {
        Log.i(MainActivity.TAG, "onUnregistered: "+ regId);
    }

    @Override
    protected boolean onRecoverableError(Context context, String errorId) {
        return false;
    }

    private Notification prepareNotification(Context context, String msg) {
        long when = System.currentTimeMillis();
        Notification notification = new Notification(android.R.drawable.stat_notify_chat, msg,
            when);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        Intent intent = new Intent(context, MessageActivity.class);
        // Set a unique data uri for each notification to make sure the activity
        // gets updated
        intent.setData(Uri.parse(msg));
        intent.putExtra("msg", msg);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
            | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent,
            0);
        String title = context.getString(R.string.app_name);
        notification.setLatestEventInfo(context, title, msg, pendingIntent);

        return notification;
      }



}
