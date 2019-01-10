package edu.ucsmub.kqvoting.db;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import edu.ucsmub.kqvoting.db.dao.NotificationDao;
import edu.ucsmub.kqvoting.db.dao.NotificationDao_Impl;
import edu.ucsmub.kqvoting.db.dao.SelectionDao;
import edu.ucsmub.kqvoting.db.dao.SelectionDao_Impl;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("unchecked")
public final class AppDatabase_Impl extends AppDatabase {
  private volatile SelectionDao _selectionDao;

  private volatile NotificationDao _notificationDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `selection` (`id` TEXT NOT NULL, `orderNumber` INTEGER NOT NULL, `name` TEXT NOT NULL, `section` TEXT NOT NULL, `isBoy` INTEGER NOT NULL, `profile` TEXT NOT NULL, `detail` TEXT NOT NULL, `fbId` TEXT NOT NULL, `fbName` TEXT NOT NULL, `photos` TEXT NOT NULL, `isVote` INTEGER NOT NULL, `firstCount` INTEGER NOT NULL, `secondCount` INTEGER NOT NULL, PRIMARY KEY(`orderNumber`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `notification` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `title` TEXT NOT NULL, `body` TEXT NOT NULL, `time` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"23fdf4ea2c3ffcf3c228595d048b5c31\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `selection`");
        _db.execSQL("DROP TABLE IF EXISTS `notification`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsSelection = new HashMap<String, TableInfo.Column>(13);
        _columnsSelection.put("id", new TableInfo.Column("id", "TEXT", true, 0));
        _columnsSelection.put("orderNumber", new TableInfo.Column("orderNumber", "INTEGER", true, 1));
        _columnsSelection.put("name", new TableInfo.Column("name", "TEXT", true, 0));
        _columnsSelection.put("section", new TableInfo.Column("section", "TEXT", true, 0));
        _columnsSelection.put("isBoy", new TableInfo.Column("isBoy", "INTEGER", true, 0));
        _columnsSelection.put("profile", new TableInfo.Column("profile", "TEXT", true, 0));
        _columnsSelection.put("detail", new TableInfo.Column("detail", "TEXT", true, 0));
        _columnsSelection.put("fbId", new TableInfo.Column("fbId", "TEXT", true, 0));
        _columnsSelection.put("fbName", new TableInfo.Column("fbName", "TEXT", true, 0));
        _columnsSelection.put("photos", new TableInfo.Column("photos", "TEXT", true, 0));
        _columnsSelection.put("isVote", new TableInfo.Column("isVote", "INTEGER", true, 0));
        _columnsSelection.put("firstCount", new TableInfo.Column("firstCount", "INTEGER", true, 0));
        _columnsSelection.put("secondCount", new TableInfo.Column("secondCount", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSelection = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSelection = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSelection = new TableInfo("selection", _columnsSelection, _foreignKeysSelection, _indicesSelection);
        final TableInfo _existingSelection = TableInfo.read(_db, "selection");
        if (! _infoSelection.equals(_existingSelection)) {
          throw new IllegalStateException("Migration didn't properly handle selection(edu.ucsmub.kqvoting.db.model.Selection).\n"
                  + " Expected:\n" + _infoSelection + "\n"
                  + " Found:\n" + _existingSelection);
        }
        final HashMap<String, TableInfo.Column> _columnsNotification = new HashMap<String, TableInfo.Column>(4);
        _columnsNotification.put("id", new TableInfo.Column("id", "INTEGER", false, 1));
        _columnsNotification.put("title", new TableInfo.Column("title", "TEXT", true, 0));
        _columnsNotification.put("body", new TableInfo.Column("body", "TEXT", true, 0));
        _columnsNotification.put("time", new TableInfo.Column("time", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysNotification = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesNotification = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoNotification = new TableInfo("notification", _columnsNotification, _foreignKeysNotification, _indicesNotification);
        final TableInfo _existingNotification = TableInfo.read(_db, "notification");
        if (! _infoNotification.equals(_existingNotification)) {
          throw new IllegalStateException("Migration didn't properly handle notification(edu.ucsmub.kqvoting.db.model.Notification).\n"
                  + " Expected:\n" + _infoNotification + "\n"
                  + " Found:\n" + _existingNotification);
        }
      }
    }, "23fdf4ea2c3ffcf3c228595d048b5c31", "9dde6132d492c8d31956dfd20001ebf9");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "selection","notification");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `selection`");
      _db.execSQL("DELETE FROM `notification`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public SelectionDao selectionDao() {
    if (_selectionDao != null) {
      return _selectionDao;
    } else {
      synchronized(this) {
        if(_selectionDao == null) {
          _selectionDao = new SelectionDao_Impl(this);
        }
        return _selectionDao;
      }
    }
  }

  @Override
  public NotificationDao notificationDao() {
    if (_notificationDao != null) {
      return _notificationDao;
    } else {
      synchronized(this) {
        if(_notificationDao == null) {
          _notificationDao = new NotificationDao_Impl(this);
        }
        return _notificationDao;
      }
    }
  }
}
