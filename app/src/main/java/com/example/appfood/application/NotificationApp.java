package com.example.appfood.application;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;

public class NotificationApp extends Application {

   public static final String CHANNEL_ID = "push_notification_id";

   @Override
   public void onCreate() {
      super.onCreate();

      createChannelNotification();
   }

   private void createChannelNotification() {
//      if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//         NotificationChannel channel = new NotificationChannel(CHANNEL_ID,"PushNotification", NotificationManager.IMPORTANCE_DEFAULT);
//         NotificationManager manager = getSystemService(NotificationManager.class);
//         manager.createNotificationChannel(channel);
//      }
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
         CharSequence name = "BaoTrungShop";
         String description = "Đặt hàng thành công. Hãy kiểm tra lại giỏ hàng";
         int importance = NotificationManager.IMPORTANCE_DEFAULT;
         NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
         channel.setDescription(description);
         NotificationManager notificationManager = getSystemService(NotificationManager.class);
         notificationManager.createNotificationChannel(channel);
      }
   }
}