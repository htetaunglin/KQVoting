package edu.ucsmub.kqvoting.db;

import java.lang.System;

@androidx.room.Database(entities = {edu.ucsmub.kqvoting.db.model.Selection.class, edu.ucsmub.kqvoting.db.model.Notification.class}, version = 1, exportSchema = false)
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&\u00a8\u0006\b"}, d2 = {"Ledu/ucsmub/kqvoting/db/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "notificationDao", "Ledu/ucsmub/kqvoting/db/dao/NotificationDao;", "selectionDao", "Ledu/ucsmub/kqvoting/db/dao/SelectionDao;", "Companion", "app_debug"})
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    private static edu.ucsmub.kqvoting.db.AppDatabase INSTANCE;
    public static final edu.ucsmub.kqvoting.db.AppDatabase.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    public abstract edu.ucsmub.kqvoting.db.dao.SelectionDao selectionDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract edu.ucsmub.kqvoting.db.dao.NotificationDao notificationDao();
    
    public AppDatabase() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Ledu/ucsmub/kqvoting/db/AppDatabase$Companion;", "", "()V", "INSTANCE", "Ledu/ucsmub/kqvoting/db/AppDatabase;", "destoryInstance", "", "getInstance", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final edu.ucsmub.kqvoting.db.AppDatabase getInstance(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
        
        public final void destoryInstance() {
        }
        
        private Companion() {
            super();
        }
    }
}