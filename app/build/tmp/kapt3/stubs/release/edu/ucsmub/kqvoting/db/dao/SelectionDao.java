package edu.ucsmub.kqvoting.db.dao;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\'J\u0014\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005H\'J\u0014\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005H\'J\u0014\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005H\'J\u001c\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u00052\u0006\u0010\n\u001a\u00020\u000bH\'J\u0014\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005H\'J\u001c\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u00052\u0006\u0010\r\u001a\u00020\u000bH\'J\u0014\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005H\'J\u0010\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u000bH\'J\u0014\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005H\'J\u0014\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005H\'J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0007H\'J\u0010\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0017H\'J \u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u0010\u001a\u00020\u000bH\'J\u0010\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0017H\'\u00a8\u0006\u001c"}, d2 = {"Ledu/ucsmub/kqvoting/db/dao/SelectionDao;", "", "deleteAllData", "", "getCurrentKing", "Landroidx/lifecycle/LiveData;", "", "Ledu/ucsmub/kqvoting/db/model/Selection;", "getCurrentQueen", "getInnocenceList", "queenID", "", "getPopularList", "kingID", "getSelectionBoys", "getSelectionByID", "ID", "getSelectionGirls", "getSelections", "insertSelection", "selection", "unVoted", "id", "", "updateCountsByID", "firstCount", "secondCount", "voted", "app_release"})
public abstract interface SelectionDao {
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "select * from selection")
    public abstract androidx.lifecycle.LiveData<java.util.List<edu.ucsmub.kqvoting.db.model.Selection>> getSelections();
    
    @androidx.room.Insert()
    public abstract void insertSelection(@org.jetbrains.annotations.NotNull()
    edu.ucsmub.kqvoting.db.model.Selection selection);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "select * from selection where isBoy = 1")
    public abstract androidx.lifecycle.LiveData<java.util.List<edu.ucsmub.kqvoting.db.model.Selection>> getSelectionBoys();
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "select * from selection where isBoy = 0")
    public abstract androidx.lifecycle.LiveData<java.util.List<edu.ucsmub.kqvoting.db.model.Selection>> getSelectionGirls();
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "select * from selection where isBoy = 1 AND id != :kingID")
    public abstract androidx.lifecycle.LiveData<java.util.List<edu.ucsmub.kqvoting.db.model.Selection>> getPopularList(@org.jetbrains.annotations.NotNull()
    java.lang.String kingID);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "select * from selection where isBoy = 0 AND id  != :queenID")
    public abstract androidx.lifecycle.LiveData<java.util.List<edu.ucsmub.kqvoting.db.model.Selection>> getInnocenceList(@org.jetbrains.annotations.NotNull()
    java.lang.String queenID);
    
    @androidx.room.Query(value = "update selection set isVote = 1 where id = :id")
    public abstract void voted(int id);
    
    @androidx.room.Query(value = "update selection set isVote = 0 where id = :id")
    public abstract void unVoted(int id);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "select * from selection where id = :ID")
    public abstract edu.ucsmub.kqvoting.db.model.Selection getSelectionByID(@org.jetbrains.annotations.NotNull()
    java.lang.String ID);
    
    @androidx.room.Query(value = "update selection set firstCount = :firstCount, secondCount = :secondCount where id = :ID")
    public abstract void updateCountsByID(int firstCount, int secondCount, @org.jetbrains.annotations.NotNull()
    java.lang.String ID);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "select * from selection where isBoy = 1 and firstCount = (select max(firstCount) from selection where isBoy = 1) limit 1")
    public abstract androidx.lifecycle.LiveData<java.util.List<edu.ucsmub.kqvoting.db.model.Selection>> getCurrentKing();
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "select * from selection where isBoy = 1 order by secondCount desc")
    public abstract androidx.lifecycle.LiveData<java.util.List<edu.ucsmub.kqvoting.db.model.Selection>> getPopularList();
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "select * from selection where isBoy = 0 and firstCount = (select max(firstCount) from selection where isBoy = 0) limit 1")
    public abstract androidx.lifecycle.LiveData<java.util.List<edu.ucsmub.kqvoting.db.model.Selection>> getCurrentQueen();
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "select * from selection where isBoy = 0 order by secondCount desc")
    public abstract androidx.lifecycle.LiveData<java.util.List<edu.ucsmub.kqvoting.db.model.Selection>> getInnocenceList();
    
    @androidx.room.Query(value = "delete from selection")
    public abstract void deleteAllData();
}