package com.example.bourbon.activities.clement_activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.bourbon.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    SharedPreferences sharedPreferences;
    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        Log.e("New Token",s);
        sharedPreferences = getSharedPreferences("default", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Token",s);
        editor.commit();
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {

        if(remoteMessage.getData().size()>0){

//            Log.e("Notify",remoteMessage.getFrom());
            if(remoteMessage.getFrom().equals("/topics/stores")){
                Log.e("Notify","Enter Store");
                if(remoteMessage.getData().get("shopId").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                    Log.e("Notify","Enter UID");
                    showNotification(remoteMessage.getData().get("title"), remoteMessage.getData().get("message"));
                }
            }else {
                showNotification(remoteMessage.getData().get("title"), remoteMessage.getData().get("message"));
            }
        }


        if(remoteMessage.getNotification() !=null){
//            Log.e("Notify",remoteMessage.getFrom());
            if(remoteMessage.getFrom().equals("/topics/stores")){
                Log.e("Notify","Enter Store");
                if(remoteMessage.getData().get("shopId").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                    Log.e("Notify","Enter UID");
                    showNotification(remoteMessage.getData().get("title"), remoteMessage.getData().get("message"));
                }
            }else {
                showNotification(remoteMessage.getData().get("title"), remoteMessage.getData().get("message"));
            }
        }
    }

    public void showNotification(String title,String message){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"MyNotifications")
                .setContentTitle(title)
                .setSmallIcon(R.drawable.ic_pan_tool_black_24dp)
                .setAutoCancel(true)
                .setContentText(message);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(999,builder.build());
    }
}
