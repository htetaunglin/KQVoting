package edu.ucsmub.kqvoting.service;

import java.lang.System;

@android.annotation.SuppressLint(value = {"NewApi"})
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\fH\u0016J\b\u0010\u000e\u001a\u00020\fH\u0017J\b\u0010\u000f\u001a\u00020\fH\u0017J\u0012\u0010\u0010\u001a\u00020\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u0012\u0010\u0013\u001a\u00020\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0014"}, d2 = {"Ledu/ucsmub/kqvoting/service/MyNotificationListenerService;", "Landroid/service/notification/NotificationListenerService;", "()V", "APP_PACKAGE", "", "getAPP_PACKAGE", "()Ljava/lang/String;", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "", "onDestroy", "onListenerConnected", "onListenerDisconnected", "onNotificationPosted", "sbn", "Landroid/service/notification/StatusBarNotification;", "onNotificationRemoved", "app_release"})
public final class MyNotificationListenerService extends android.service.notification.NotificationListenerService {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String APP_PACKAGE = "edu.ucsmub.kqvoting";
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @java.lang.Override()
    public void onNotificationRemoved(@org.jetbrains.annotations.Nullable()
    android.service.notification.StatusBarNotification sbn) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.os.IBinder onBind(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAPP_PACKAGE() {
        return null;
    }
    
    @java.lang.Override()
    public void onNotificationPosted(@org.jetbrains.annotations.Nullable()
    android.service.notification.StatusBarNotification sbn) {
    }
    
    @android.annotation.TargetApi(value = android.os.Build.VERSION_CODES.N)
    @java.lang.Override()
    public void onListenerDisconnected() {
    }
    
    @android.annotation.TargetApi(value = android.os.Build.VERSION_CODES.N)
    @java.lang.Override()
    public void onListenerConnected() {
    }
    
    public MyNotificationListenerService() {
        super();
    }
}