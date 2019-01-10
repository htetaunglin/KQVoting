package edu.ucsmub.kqvoting.db.dao;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0014\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\b0\u0007H\'\u00a8\u0006\t"}, d2 = {"Ledu/ucsmub/kqvoting/db/dao/NotificationDao;", "", "insertNotification", "", "notification", "Ledu/ucsmub/kqvoting/db/model/Notification;", "selectNotification", "Landroidx/lifecycle/LiveData;", "", "app_release"})
public abstract interface NotificationDao {
    
    @androidx.room.Insert()
    public abstract void insertNotification(@org.jetbrains.annotations.NotNull()
    edu.ucsmub.kqvoting.db.model.Notification notification);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "select * from notification order by id desc")
    public abstract androidx.lifecycle.LiveData<java.util.List<edu.ucsmub.kqvoting.db.model.Notification>> selectNotification();
}