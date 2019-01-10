package edu.ucsmub.kqvoting.db.dao;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import edu.ucsmub.kqvoting.db.StringGsonConverters;
import edu.ucsmub.kqvoting.db.model.Selection;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings("unchecked")
public final class SelectionDao_Impl implements SelectionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfSelection;

  private final StringGsonConverters __stringGsonConverters = new StringGsonConverters();

  private final SharedSQLiteStatement __preparedStmtOfVoted;

  private final SharedSQLiteStatement __preparedStmtOfUnVoted;

  private final SharedSQLiteStatement __preparedStmtOfUpdateCountsByID;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllData;

  public SelectionDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSelection = new EntityInsertionAdapter<Selection>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `selection`(`id`,`orderNumber`,`name`,`section`,`isBoy`,`profile`,`detail`,`fbId`,`fbName`,`photos`,`isVote`,`firstCount`,`secondCount`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Selection value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
        stmt.bindLong(2, value.getOrder());
        if (value.getName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getName());
        }
        if (value.getSection() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getSection());
        }
        final int _tmp;
        _tmp = value.isBoy() ? 1 : 0;
        stmt.bindLong(5, _tmp);
        if (value.getProfile_image() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getProfile_image());
        }
        if (value.getDetail() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getDetail());
        }
        if (value.getFacebookID() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getFacebookID());
        }
        if (value.getFacebookName() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getFacebookName());
        }
        final String _tmp_1;
        _tmp_1 = __stringGsonConverters.fromArrayList(value.getPhotos());
        if (_tmp_1 == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, _tmp_1);
        }
        final int _tmp_2;
        _tmp_2 = value.isVote() ? 1 : 0;
        stmt.bindLong(11, _tmp_2);
        stmt.bindLong(12, value.getFirstCount());
        stmt.bindLong(13, value.getSecondCount());
      }
    };
    this.__preparedStmtOfVoted = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "update selection set isVote = 1 where id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUnVoted = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "update selection set isVote = 0 where id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateCountsByID = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "update selection set firstCount = ?, secondCount = ? where id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllData = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "delete from selection";
        return _query;
      }
    };
  }

  @Override
  public void insertSelection(final Selection selection) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfSelection.insert(selection);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void voted(final int id) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfVoted.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      _stmt.bindLong(_argIndex, id);
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfVoted.release(_stmt);
    }
  }

  @Override
  public void unVoted(final int id) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfUnVoted.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      _stmt.bindLong(_argIndex, id);
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUnVoted.release(_stmt);
    }
  }

  @Override
  public void updateCountsByID(final int firstCount, final int secondCount, final String ID) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateCountsByID.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      _stmt.bindLong(_argIndex, firstCount);
      _argIndex = 2;
      _stmt.bindLong(_argIndex, secondCount);
      _argIndex = 3;
      if (ID == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, ID);
      }
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateCountsByID.release(_stmt);
    }
  }

  @Override
  public void deleteAllData() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllData.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllData.release(_stmt);
    }
  }

  @Override
  public LiveData<List<Selection>> getSelections() {
    final String _sql = "select * from selection";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"selection"}, new Callable<List<Selection>>() {
      @Override
      public List<Selection> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "orderNumber");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfSection = CursorUtil.getColumnIndexOrThrow(_cursor, "section");
          final int _cursorIndexOfIsBoy = CursorUtil.getColumnIndexOrThrow(_cursor, "isBoy");
          final int _cursorIndexOfProfileImage = CursorUtil.getColumnIndexOrThrow(_cursor, "profile");
          final int _cursorIndexOfDetail = CursorUtil.getColumnIndexOrThrow(_cursor, "detail");
          final int _cursorIndexOfFacebookID = CursorUtil.getColumnIndexOrThrow(_cursor, "fbId");
          final int _cursorIndexOfFacebookName = CursorUtil.getColumnIndexOrThrow(_cursor, "fbName");
          final int _cursorIndexOfPhotos = CursorUtil.getColumnIndexOrThrow(_cursor, "photos");
          final int _cursorIndexOfIsVote = CursorUtil.getColumnIndexOrThrow(_cursor, "isVote");
          final int _cursorIndexOfFirstCount = CursorUtil.getColumnIndexOrThrow(_cursor, "firstCount");
          final int _cursorIndexOfSecondCount = CursorUtil.getColumnIndexOrThrow(_cursor, "secondCount");
          final List<Selection> _result = new ArrayList<Selection>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Selection _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final int _tmpOrder;
            _tmpOrder = _cursor.getInt(_cursorIndexOfOrder);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpSection;
            _tmpSection = _cursor.getString(_cursorIndexOfSection);
            final boolean _tmpIsBoy;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsBoy);
            _tmpIsBoy = _tmp != 0;
            final String _tmpProfile_image;
            _tmpProfile_image = _cursor.getString(_cursorIndexOfProfileImage);
            final String _tmpDetail;
            _tmpDetail = _cursor.getString(_cursorIndexOfDetail);
            final String _tmpFacebookID;
            _tmpFacebookID = _cursor.getString(_cursorIndexOfFacebookID);
            final String _tmpFacebookName;
            _tmpFacebookName = _cursor.getString(_cursorIndexOfFacebookName);
            final ArrayList<String> _tmpPhotos;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfPhotos);
            _tmpPhotos = __stringGsonConverters.fromString(_tmp_1);
            final boolean _tmpIsVote;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsVote);
            _tmpIsVote = _tmp_2 != 0;
            final int _tmpFirstCount;
            _tmpFirstCount = _cursor.getInt(_cursorIndexOfFirstCount);
            final int _tmpSecondCount;
            _tmpSecondCount = _cursor.getInt(_cursorIndexOfSecondCount);
            _item = new Selection(_tmpId,_tmpOrder,_tmpName,_tmpSection,_tmpIsBoy,_tmpProfile_image,_tmpDetail,_tmpFacebookID,_tmpFacebookName,_tmpPhotos,_tmpIsVote,_tmpFirstCount,_tmpSecondCount);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<Selection>> getSelectionBoys() {
    final String _sql = "select * from selection where isBoy = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"selection"}, new Callable<List<Selection>>() {
      @Override
      public List<Selection> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "orderNumber");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfSection = CursorUtil.getColumnIndexOrThrow(_cursor, "section");
          final int _cursorIndexOfIsBoy = CursorUtil.getColumnIndexOrThrow(_cursor, "isBoy");
          final int _cursorIndexOfProfileImage = CursorUtil.getColumnIndexOrThrow(_cursor, "profile");
          final int _cursorIndexOfDetail = CursorUtil.getColumnIndexOrThrow(_cursor, "detail");
          final int _cursorIndexOfFacebookID = CursorUtil.getColumnIndexOrThrow(_cursor, "fbId");
          final int _cursorIndexOfFacebookName = CursorUtil.getColumnIndexOrThrow(_cursor, "fbName");
          final int _cursorIndexOfPhotos = CursorUtil.getColumnIndexOrThrow(_cursor, "photos");
          final int _cursorIndexOfIsVote = CursorUtil.getColumnIndexOrThrow(_cursor, "isVote");
          final int _cursorIndexOfFirstCount = CursorUtil.getColumnIndexOrThrow(_cursor, "firstCount");
          final int _cursorIndexOfSecondCount = CursorUtil.getColumnIndexOrThrow(_cursor, "secondCount");
          final List<Selection> _result = new ArrayList<Selection>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Selection _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final int _tmpOrder;
            _tmpOrder = _cursor.getInt(_cursorIndexOfOrder);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpSection;
            _tmpSection = _cursor.getString(_cursorIndexOfSection);
            final boolean _tmpIsBoy;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsBoy);
            _tmpIsBoy = _tmp != 0;
            final String _tmpProfile_image;
            _tmpProfile_image = _cursor.getString(_cursorIndexOfProfileImage);
            final String _tmpDetail;
            _tmpDetail = _cursor.getString(_cursorIndexOfDetail);
            final String _tmpFacebookID;
            _tmpFacebookID = _cursor.getString(_cursorIndexOfFacebookID);
            final String _tmpFacebookName;
            _tmpFacebookName = _cursor.getString(_cursorIndexOfFacebookName);
            final ArrayList<String> _tmpPhotos;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfPhotos);
            _tmpPhotos = __stringGsonConverters.fromString(_tmp_1);
            final boolean _tmpIsVote;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsVote);
            _tmpIsVote = _tmp_2 != 0;
            final int _tmpFirstCount;
            _tmpFirstCount = _cursor.getInt(_cursorIndexOfFirstCount);
            final int _tmpSecondCount;
            _tmpSecondCount = _cursor.getInt(_cursorIndexOfSecondCount);
            _item = new Selection(_tmpId,_tmpOrder,_tmpName,_tmpSection,_tmpIsBoy,_tmpProfile_image,_tmpDetail,_tmpFacebookID,_tmpFacebookName,_tmpPhotos,_tmpIsVote,_tmpFirstCount,_tmpSecondCount);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<Selection>> getSelectionGirls() {
    final String _sql = "select * from selection where isBoy = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"selection"}, new Callable<List<Selection>>() {
      @Override
      public List<Selection> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "orderNumber");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfSection = CursorUtil.getColumnIndexOrThrow(_cursor, "section");
          final int _cursorIndexOfIsBoy = CursorUtil.getColumnIndexOrThrow(_cursor, "isBoy");
          final int _cursorIndexOfProfileImage = CursorUtil.getColumnIndexOrThrow(_cursor, "profile");
          final int _cursorIndexOfDetail = CursorUtil.getColumnIndexOrThrow(_cursor, "detail");
          final int _cursorIndexOfFacebookID = CursorUtil.getColumnIndexOrThrow(_cursor, "fbId");
          final int _cursorIndexOfFacebookName = CursorUtil.getColumnIndexOrThrow(_cursor, "fbName");
          final int _cursorIndexOfPhotos = CursorUtil.getColumnIndexOrThrow(_cursor, "photos");
          final int _cursorIndexOfIsVote = CursorUtil.getColumnIndexOrThrow(_cursor, "isVote");
          final int _cursorIndexOfFirstCount = CursorUtil.getColumnIndexOrThrow(_cursor, "firstCount");
          final int _cursorIndexOfSecondCount = CursorUtil.getColumnIndexOrThrow(_cursor, "secondCount");
          final List<Selection> _result = new ArrayList<Selection>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Selection _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final int _tmpOrder;
            _tmpOrder = _cursor.getInt(_cursorIndexOfOrder);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpSection;
            _tmpSection = _cursor.getString(_cursorIndexOfSection);
            final boolean _tmpIsBoy;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsBoy);
            _tmpIsBoy = _tmp != 0;
            final String _tmpProfile_image;
            _tmpProfile_image = _cursor.getString(_cursorIndexOfProfileImage);
            final String _tmpDetail;
            _tmpDetail = _cursor.getString(_cursorIndexOfDetail);
            final String _tmpFacebookID;
            _tmpFacebookID = _cursor.getString(_cursorIndexOfFacebookID);
            final String _tmpFacebookName;
            _tmpFacebookName = _cursor.getString(_cursorIndexOfFacebookName);
            final ArrayList<String> _tmpPhotos;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfPhotos);
            _tmpPhotos = __stringGsonConverters.fromString(_tmp_1);
            final boolean _tmpIsVote;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsVote);
            _tmpIsVote = _tmp_2 != 0;
            final int _tmpFirstCount;
            _tmpFirstCount = _cursor.getInt(_cursorIndexOfFirstCount);
            final int _tmpSecondCount;
            _tmpSecondCount = _cursor.getInt(_cursorIndexOfSecondCount);
            _item = new Selection(_tmpId,_tmpOrder,_tmpName,_tmpSection,_tmpIsBoy,_tmpProfile_image,_tmpDetail,_tmpFacebookID,_tmpFacebookName,_tmpPhotos,_tmpIsVote,_tmpFirstCount,_tmpSecondCount);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<Selection>> getPopularList(final String kingID) {
    final String _sql = "select * from selection where isBoy = 1 AND id != ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (kingID == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, kingID);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"selection"}, new Callable<List<Selection>>() {
      @Override
      public List<Selection> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "orderNumber");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfSection = CursorUtil.getColumnIndexOrThrow(_cursor, "section");
          final int _cursorIndexOfIsBoy = CursorUtil.getColumnIndexOrThrow(_cursor, "isBoy");
          final int _cursorIndexOfProfileImage = CursorUtil.getColumnIndexOrThrow(_cursor, "profile");
          final int _cursorIndexOfDetail = CursorUtil.getColumnIndexOrThrow(_cursor, "detail");
          final int _cursorIndexOfFacebookID = CursorUtil.getColumnIndexOrThrow(_cursor, "fbId");
          final int _cursorIndexOfFacebookName = CursorUtil.getColumnIndexOrThrow(_cursor, "fbName");
          final int _cursorIndexOfPhotos = CursorUtil.getColumnIndexOrThrow(_cursor, "photos");
          final int _cursorIndexOfIsVote = CursorUtil.getColumnIndexOrThrow(_cursor, "isVote");
          final int _cursorIndexOfFirstCount = CursorUtil.getColumnIndexOrThrow(_cursor, "firstCount");
          final int _cursorIndexOfSecondCount = CursorUtil.getColumnIndexOrThrow(_cursor, "secondCount");
          final List<Selection> _result = new ArrayList<Selection>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Selection _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final int _tmpOrder;
            _tmpOrder = _cursor.getInt(_cursorIndexOfOrder);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpSection;
            _tmpSection = _cursor.getString(_cursorIndexOfSection);
            final boolean _tmpIsBoy;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsBoy);
            _tmpIsBoy = _tmp != 0;
            final String _tmpProfile_image;
            _tmpProfile_image = _cursor.getString(_cursorIndexOfProfileImage);
            final String _tmpDetail;
            _tmpDetail = _cursor.getString(_cursorIndexOfDetail);
            final String _tmpFacebookID;
            _tmpFacebookID = _cursor.getString(_cursorIndexOfFacebookID);
            final String _tmpFacebookName;
            _tmpFacebookName = _cursor.getString(_cursorIndexOfFacebookName);
            final ArrayList<String> _tmpPhotos;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfPhotos);
            _tmpPhotos = __stringGsonConverters.fromString(_tmp_1);
            final boolean _tmpIsVote;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsVote);
            _tmpIsVote = _tmp_2 != 0;
            final int _tmpFirstCount;
            _tmpFirstCount = _cursor.getInt(_cursorIndexOfFirstCount);
            final int _tmpSecondCount;
            _tmpSecondCount = _cursor.getInt(_cursorIndexOfSecondCount);
            _item = new Selection(_tmpId,_tmpOrder,_tmpName,_tmpSection,_tmpIsBoy,_tmpProfile_image,_tmpDetail,_tmpFacebookID,_tmpFacebookName,_tmpPhotos,_tmpIsVote,_tmpFirstCount,_tmpSecondCount);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<Selection>> getInnocenceList(final String queenID) {
    final String _sql = "select * from selection where isBoy = 0 AND id  != ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (queenID == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, queenID);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"selection"}, new Callable<List<Selection>>() {
      @Override
      public List<Selection> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "orderNumber");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfSection = CursorUtil.getColumnIndexOrThrow(_cursor, "section");
          final int _cursorIndexOfIsBoy = CursorUtil.getColumnIndexOrThrow(_cursor, "isBoy");
          final int _cursorIndexOfProfileImage = CursorUtil.getColumnIndexOrThrow(_cursor, "profile");
          final int _cursorIndexOfDetail = CursorUtil.getColumnIndexOrThrow(_cursor, "detail");
          final int _cursorIndexOfFacebookID = CursorUtil.getColumnIndexOrThrow(_cursor, "fbId");
          final int _cursorIndexOfFacebookName = CursorUtil.getColumnIndexOrThrow(_cursor, "fbName");
          final int _cursorIndexOfPhotos = CursorUtil.getColumnIndexOrThrow(_cursor, "photos");
          final int _cursorIndexOfIsVote = CursorUtil.getColumnIndexOrThrow(_cursor, "isVote");
          final int _cursorIndexOfFirstCount = CursorUtil.getColumnIndexOrThrow(_cursor, "firstCount");
          final int _cursorIndexOfSecondCount = CursorUtil.getColumnIndexOrThrow(_cursor, "secondCount");
          final List<Selection> _result = new ArrayList<Selection>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Selection _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final int _tmpOrder;
            _tmpOrder = _cursor.getInt(_cursorIndexOfOrder);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpSection;
            _tmpSection = _cursor.getString(_cursorIndexOfSection);
            final boolean _tmpIsBoy;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsBoy);
            _tmpIsBoy = _tmp != 0;
            final String _tmpProfile_image;
            _tmpProfile_image = _cursor.getString(_cursorIndexOfProfileImage);
            final String _tmpDetail;
            _tmpDetail = _cursor.getString(_cursorIndexOfDetail);
            final String _tmpFacebookID;
            _tmpFacebookID = _cursor.getString(_cursorIndexOfFacebookID);
            final String _tmpFacebookName;
            _tmpFacebookName = _cursor.getString(_cursorIndexOfFacebookName);
            final ArrayList<String> _tmpPhotos;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfPhotos);
            _tmpPhotos = __stringGsonConverters.fromString(_tmp_1);
            final boolean _tmpIsVote;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsVote);
            _tmpIsVote = _tmp_2 != 0;
            final int _tmpFirstCount;
            _tmpFirstCount = _cursor.getInt(_cursorIndexOfFirstCount);
            final int _tmpSecondCount;
            _tmpSecondCount = _cursor.getInt(_cursorIndexOfSecondCount);
            _item = new Selection(_tmpId,_tmpOrder,_tmpName,_tmpSection,_tmpIsBoy,_tmpProfile_image,_tmpDetail,_tmpFacebookID,_tmpFacebookName,_tmpPhotos,_tmpIsVote,_tmpFirstCount,_tmpSecondCount);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Selection getSelectionByID(final String ID) {
    final String _sql = "select * from selection where id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (ID == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, ID);
    }
    final Cursor _cursor = DBUtil.query(__db, _statement, false);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "orderNumber");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfSection = CursorUtil.getColumnIndexOrThrow(_cursor, "section");
      final int _cursorIndexOfIsBoy = CursorUtil.getColumnIndexOrThrow(_cursor, "isBoy");
      final int _cursorIndexOfProfileImage = CursorUtil.getColumnIndexOrThrow(_cursor, "profile");
      final int _cursorIndexOfDetail = CursorUtil.getColumnIndexOrThrow(_cursor, "detail");
      final int _cursorIndexOfFacebookID = CursorUtil.getColumnIndexOrThrow(_cursor, "fbId");
      final int _cursorIndexOfFacebookName = CursorUtil.getColumnIndexOrThrow(_cursor, "fbName");
      final int _cursorIndexOfPhotos = CursorUtil.getColumnIndexOrThrow(_cursor, "photos");
      final int _cursorIndexOfIsVote = CursorUtil.getColumnIndexOrThrow(_cursor, "isVote");
      final int _cursorIndexOfFirstCount = CursorUtil.getColumnIndexOrThrow(_cursor, "firstCount");
      final int _cursorIndexOfSecondCount = CursorUtil.getColumnIndexOrThrow(_cursor, "secondCount");
      final Selection _result;
      if(_cursor.moveToFirst()) {
        final String _tmpId;
        _tmpId = _cursor.getString(_cursorIndexOfId);
        final int _tmpOrder;
        _tmpOrder = _cursor.getInt(_cursorIndexOfOrder);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final String _tmpSection;
        _tmpSection = _cursor.getString(_cursorIndexOfSection);
        final boolean _tmpIsBoy;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsBoy);
        _tmpIsBoy = _tmp != 0;
        final String _tmpProfile_image;
        _tmpProfile_image = _cursor.getString(_cursorIndexOfProfileImage);
        final String _tmpDetail;
        _tmpDetail = _cursor.getString(_cursorIndexOfDetail);
        final String _tmpFacebookID;
        _tmpFacebookID = _cursor.getString(_cursorIndexOfFacebookID);
        final String _tmpFacebookName;
        _tmpFacebookName = _cursor.getString(_cursorIndexOfFacebookName);
        final ArrayList<String> _tmpPhotos;
        final String _tmp_1;
        _tmp_1 = _cursor.getString(_cursorIndexOfPhotos);
        _tmpPhotos = __stringGsonConverters.fromString(_tmp_1);
        final boolean _tmpIsVote;
        final int _tmp_2;
        _tmp_2 = _cursor.getInt(_cursorIndexOfIsVote);
        _tmpIsVote = _tmp_2 != 0;
        final int _tmpFirstCount;
        _tmpFirstCount = _cursor.getInt(_cursorIndexOfFirstCount);
        final int _tmpSecondCount;
        _tmpSecondCount = _cursor.getInt(_cursorIndexOfSecondCount);
        _result = new Selection(_tmpId,_tmpOrder,_tmpName,_tmpSection,_tmpIsBoy,_tmpProfile_image,_tmpDetail,_tmpFacebookID,_tmpFacebookName,_tmpPhotos,_tmpIsVote,_tmpFirstCount,_tmpSecondCount);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public LiveData<List<Selection>> getCurrentKing() {
    final String _sql = "select * from selection where isBoy = 1 and firstCount = (select max(firstCount) from selection where isBoy = 1) limit 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"selection"}, new Callable<List<Selection>>() {
      @Override
      public List<Selection> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "orderNumber");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfSection = CursorUtil.getColumnIndexOrThrow(_cursor, "section");
          final int _cursorIndexOfIsBoy = CursorUtil.getColumnIndexOrThrow(_cursor, "isBoy");
          final int _cursorIndexOfProfileImage = CursorUtil.getColumnIndexOrThrow(_cursor, "profile");
          final int _cursorIndexOfDetail = CursorUtil.getColumnIndexOrThrow(_cursor, "detail");
          final int _cursorIndexOfFacebookID = CursorUtil.getColumnIndexOrThrow(_cursor, "fbId");
          final int _cursorIndexOfFacebookName = CursorUtil.getColumnIndexOrThrow(_cursor, "fbName");
          final int _cursorIndexOfPhotos = CursorUtil.getColumnIndexOrThrow(_cursor, "photos");
          final int _cursorIndexOfIsVote = CursorUtil.getColumnIndexOrThrow(_cursor, "isVote");
          final int _cursorIndexOfFirstCount = CursorUtil.getColumnIndexOrThrow(_cursor, "firstCount");
          final int _cursorIndexOfSecondCount = CursorUtil.getColumnIndexOrThrow(_cursor, "secondCount");
          final List<Selection> _result = new ArrayList<Selection>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Selection _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final int _tmpOrder;
            _tmpOrder = _cursor.getInt(_cursorIndexOfOrder);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpSection;
            _tmpSection = _cursor.getString(_cursorIndexOfSection);
            final boolean _tmpIsBoy;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsBoy);
            _tmpIsBoy = _tmp != 0;
            final String _tmpProfile_image;
            _tmpProfile_image = _cursor.getString(_cursorIndexOfProfileImage);
            final String _tmpDetail;
            _tmpDetail = _cursor.getString(_cursorIndexOfDetail);
            final String _tmpFacebookID;
            _tmpFacebookID = _cursor.getString(_cursorIndexOfFacebookID);
            final String _tmpFacebookName;
            _tmpFacebookName = _cursor.getString(_cursorIndexOfFacebookName);
            final ArrayList<String> _tmpPhotos;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfPhotos);
            _tmpPhotos = __stringGsonConverters.fromString(_tmp_1);
            final boolean _tmpIsVote;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsVote);
            _tmpIsVote = _tmp_2 != 0;
            final int _tmpFirstCount;
            _tmpFirstCount = _cursor.getInt(_cursorIndexOfFirstCount);
            final int _tmpSecondCount;
            _tmpSecondCount = _cursor.getInt(_cursorIndexOfSecondCount);
            _item = new Selection(_tmpId,_tmpOrder,_tmpName,_tmpSection,_tmpIsBoy,_tmpProfile_image,_tmpDetail,_tmpFacebookID,_tmpFacebookName,_tmpPhotos,_tmpIsVote,_tmpFirstCount,_tmpSecondCount);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<Selection>> getPopularList() {
    final String _sql = "select * from selection where isBoy = 1 order by secondCount desc";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"selection"}, new Callable<List<Selection>>() {
      @Override
      public List<Selection> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "orderNumber");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfSection = CursorUtil.getColumnIndexOrThrow(_cursor, "section");
          final int _cursorIndexOfIsBoy = CursorUtil.getColumnIndexOrThrow(_cursor, "isBoy");
          final int _cursorIndexOfProfileImage = CursorUtil.getColumnIndexOrThrow(_cursor, "profile");
          final int _cursorIndexOfDetail = CursorUtil.getColumnIndexOrThrow(_cursor, "detail");
          final int _cursorIndexOfFacebookID = CursorUtil.getColumnIndexOrThrow(_cursor, "fbId");
          final int _cursorIndexOfFacebookName = CursorUtil.getColumnIndexOrThrow(_cursor, "fbName");
          final int _cursorIndexOfPhotos = CursorUtil.getColumnIndexOrThrow(_cursor, "photos");
          final int _cursorIndexOfIsVote = CursorUtil.getColumnIndexOrThrow(_cursor, "isVote");
          final int _cursorIndexOfFirstCount = CursorUtil.getColumnIndexOrThrow(_cursor, "firstCount");
          final int _cursorIndexOfSecondCount = CursorUtil.getColumnIndexOrThrow(_cursor, "secondCount");
          final List<Selection> _result = new ArrayList<Selection>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Selection _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final int _tmpOrder;
            _tmpOrder = _cursor.getInt(_cursorIndexOfOrder);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpSection;
            _tmpSection = _cursor.getString(_cursorIndexOfSection);
            final boolean _tmpIsBoy;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsBoy);
            _tmpIsBoy = _tmp != 0;
            final String _tmpProfile_image;
            _tmpProfile_image = _cursor.getString(_cursorIndexOfProfileImage);
            final String _tmpDetail;
            _tmpDetail = _cursor.getString(_cursorIndexOfDetail);
            final String _tmpFacebookID;
            _tmpFacebookID = _cursor.getString(_cursorIndexOfFacebookID);
            final String _tmpFacebookName;
            _tmpFacebookName = _cursor.getString(_cursorIndexOfFacebookName);
            final ArrayList<String> _tmpPhotos;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfPhotos);
            _tmpPhotos = __stringGsonConverters.fromString(_tmp_1);
            final boolean _tmpIsVote;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsVote);
            _tmpIsVote = _tmp_2 != 0;
            final int _tmpFirstCount;
            _tmpFirstCount = _cursor.getInt(_cursorIndexOfFirstCount);
            final int _tmpSecondCount;
            _tmpSecondCount = _cursor.getInt(_cursorIndexOfSecondCount);
            _item = new Selection(_tmpId,_tmpOrder,_tmpName,_tmpSection,_tmpIsBoy,_tmpProfile_image,_tmpDetail,_tmpFacebookID,_tmpFacebookName,_tmpPhotos,_tmpIsVote,_tmpFirstCount,_tmpSecondCount);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<Selection>> getCurrentQueen() {
    final String _sql = "select * from selection where isBoy = 0 and firstCount = (select max(firstCount) from selection where isBoy = 0) limit 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"selection"}, new Callable<List<Selection>>() {
      @Override
      public List<Selection> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "orderNumber");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfSection = CursorUtil.getColumnIndexOrThrow(_cursor, "section");
          final int _cursorIndexOfIsBoy = CursorUtil.getColumnIndexOrThrow(_cursor, "isBoy");
          final int _cursorIndexOfProfileImage = CursorUtil.getColumnIndexOrThrow(_cursor, "profile");
          final int _cursorIndexOfDetail = CursorUtil.getColumnIndexOrThrow(_cursor, "detail");
          final int _cursorIndexOfFacebookID = CursorUtil.getColumnIndexOrThrow(_cursor, "fbId");
          final int _cursorIndexOfFacebookName = CursorUtil.getColumnIndexOrThrow(_cursor, "fbName");
          final int _cursorIndexOfPhotos = CursorUtil.getColumnIndexOrThrow(_cursor, "photos");
          final int _cursorIndexOfIsVote = CursorUtil.getColumnIndexOrThrow(_cursor, "isVote");
          final int _cursorIndexOfFirstCount = CursorUtil.getColumnIndexOrThrow(_cursor, "firstCount");
          final int _cursorIndexOfSecondCount = CursorUtil.getColumnIndexOrThrow(_cursor, "secondCount");
          final List<Selection> _result = new ArrayList<Selection>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Selection _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final int _tmpOrder;
            _tmpOrder = _cursor.getInt(_cursorIndexOfOrder);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpSection;
            _tmpSection = _cursor.getString(_cursorIndexOfSection);
            final boolean _tmpIsBoy;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsBoy);
            _tmpIsBoy = _tmp != 0;
            final String _tmpProfile_image;
            _tmpProfile_image = _cursor.getString(_cursorIndexOfProfileImage);
            final String _tmpDetail;
            _tmpDetail = _cursor.getString(_cursorIndexOfDetail);
            final String _tmpFacebookID;
            _tmpFacebookID = _cursor.getString(_cursorIndexOfFacebookID);
            final String _tmpFacebookName;
            _tmpFacebookName = _cursor.getString(_cursorIndexOfFacebookName);
            final ArrayList<String> _tmpPhotos;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfPhotos);
            _tmpPhotos = __stringGsonConverters.fromString(_tmp_1);
            final boolean _tmpIsVote;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsVote);
            _tmpIsVote = _tmp_2 != 0;
            final int _tmpFirstCount;
            _tmpFirstCount = _cursor.getInt(_cursorIndexOfFirstCount);
            final int _tmpSecondCount;
            _tmpSecondCount = _cursor.getInt(_cursorIndexOfSecondCount);
            _item = new Selection(_tmpId,_tmpOrder,_tmpName,_tmpSection,_tmpIsBoy,_tmpProfile_image,_tmpDetail,_tmpFacebookID,_tmpFacebookName,_tmpPhotos,_tmpIsVote,_tmpFirstCount,_tmpSecondCount);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<Selection>> getInnocenceList() {
    final String _sql = "select * from selection where isBoy = 0 order by secondCount desc";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"selection"}, new Callable<List<Selection>>() {
      @Override
      public List<Selection> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "orderNumber");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfSection = CursorUtil.getColumnIndexOrThrow(_cursor, "section");
          final int _cursorIndexOfIsBoy = CursorUtil.getColumnIndexOrThrow(_cursor, "isBoy");
          final int _cursorIndexOfProfileImage = CursorUtil.getColumnIndexOrThrow(_cursor, "profile");
          final int _cursorIndexOfDetail = CursorUtil.getColumnIndexOrThrow(_cursor, "detail");
          final int _cursorIndexOfFacebookID = CursorUtil.getColumnIndexOrThrow(_cursor, "fbId");
          final int _cursorIndexOfFacebookName = CursorUtil.getColumnIndexOrThrow(_cursor, "fbName");
          final int _cursorIndexOfPhotos = CursorUtil.getColumnIndexOrThrow(_cursor, "photos");
          final int _cursorIndexOfIsVote = CursorUtil.getColumnIndexOrThrow(_cursor, "isVote");
          final int _cursorIndexOfFirstCount = CursorUtil.getColumnIndexOrThrow(_cursor, "firstCount");
          final int _cursorIndexOfSecondCount = CursorUtil.getColumnIndexOrThrow(_cursor, "secondCount");
          final List<Selection> _result = new ArrayList<Selection>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Selection _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final int _tmpOrder;
            _tmpOrder = _cursor.getInt(_cursorIndexOfOrder);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpSection;
            _tmpSection = _cursor.getString(_cursorIndexOfSection);
            final boolean _tmpIsBoy;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsBoy);
            _tmpIsBoy = _tmp != 0;
            final String _tmpProfile_image;
            _tmpProfile_image = _cursor.getString(_cursorIndexOfProfileImage);
            final String _tmpDetail;
            _tmpDetail = _cursor.getString(_cursorIndexOfDetail);
            final String _tmpFacebookID;
            _tmpFacebookID = _cursor.getString(_cursorIndexOfFacebookID);
            final String _tmpFacebookName;
            _tmpFacebookName = _cursor.getString(_cursorIndexOfFacebookName);
            final ArrayList<String> _tmpPhotos;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfPhotos);
            _tmpPhotos = __stringGsonConverters.fromString(_tmp_1);
            final boolean _tmpIsVote;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsVote);
            _tmpIsVote = _tmp_2 != 0;
            final int _tmpFirstCount;
            _tmpFirstCount = _cursor.getInt(_cursorIndexOfFirstCount);
            final int _tmpSecondCount;
            _tmpSecondCount = _cursor.getInt(_cursorIndexOfSecondCount);
            _item = new Selection(_tmpId,_tmpOrder,_tmpName,_tmpSection,_tmpIsBoy,_tmpProfile_image,_tmpDetail,_tmpFacebookID,_tmpFacebookName,_tmpPhotos,_tmpIsVote,_tmpFirstCount,_tmpSecondCount);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }
}
