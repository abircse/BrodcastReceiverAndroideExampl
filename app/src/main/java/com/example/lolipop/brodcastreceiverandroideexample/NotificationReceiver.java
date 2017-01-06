package com.example.lolipop.brodcastreceiverandroideexample;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.NotificationCompat;

/**
 * Created by lolipop on 1/5/17.
 */

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if (!isNetworkAvailable(context)){
            notification(context , "Wifi connection Off");
        }
        else {
            notification(context , "Wifi connection on");
        }
    }


    public void notification(Context context , String message){
        String title = context.getString(R.string.app_name);
        Intent intent = new Intent(context , MainActivity.class);
        intent.putExtra("title" , title);
        intent.putExtra("text" , message);

        PendingIntent pendingIntent = PendingIntent.getActivity(context , 0 , intent ,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker(message)
                .setContentTitle(context.getString(R.string.app_name))
                .setContentText(message)
                .addAction(R.mipmap.ic_launcher , "Action Button" , pendingIntent)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        // Creae Notification Manager

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0 , builder.build());
    }


    private boolean isNetworkAvailable(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo!=null;
    }
}
