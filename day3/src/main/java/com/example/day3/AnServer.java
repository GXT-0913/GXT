package com.example.day3;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class AnServer extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "onDestroy" + "服务开启", Toast.LENGTH_SHORT).show();
        NotificationManager service = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        String id = "1";
        String name = "pop";
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setLightColor(Color.BLACK);
            channel.enableLights(true);
            channel.setShowBadge(true);
            service.createNotificationChannel(channel);
        }
        Notification build = new NotificationCompat.Builder(this, id)
                .setContentTitle("onCreate")
                .setContentText("onCreate")
                .setSmallIcon(R.drawable.icon_music)
                .build();
        service.notify(200, build);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "onStartCommand" + "运行", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy" + "服务销毁", Toast.LENGTH_SHORT).show();
    }
}
