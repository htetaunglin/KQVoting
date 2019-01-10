package edu.ucsmub.kqvoting.Activity;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013J\b\u0010\u0015\u001a\u00020\u0011H\u0003J\u0006\u0010\u0016\u001a\u00020\u0011J\b\u0010\u0017\u001a\u00020\u0011H\u0016J\u0012\u0010\u0018\u001a\u00020\u00112\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0014J\u0010\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u0011H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Ledu/ucsmub/kqvoting/Activity/VotingRoomActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "count", "", "kingID", "", "pref", "Ledu/ucsmub/kqvoting/Pref/MyPreference;", "getPref", "()Ledu/ucsmub/kqvoting/Pref/MyPreference;", "setPref", "(Ledu/ucsmub/kqvoting/Pref/MyPreference;)V", "queenID", "voted", "", "bindViewToRG", "", "selectionStudentList", "", "Ledu/ucsmub/kqvoting/db/model/Selection;", "createPresetValueButton", "initial", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "resetPref", "app_debug"})
public final class VotingRoomActivity extends androidx.appcompat.app.AppCompatActivity {
    private int count;
    private java.lang.String kingID;
    private java.lang.String queenID;
    private boolean voted;
    @org.jetbrains.annotations.NotNull()
    public edu.ucsmub.kqvoting.Pref.MyPreference pref;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final edu.ucsmub.kqvoting.Pref.MyPreference getPref() {
        return null;
    }
    
    public final void setPref(@org.jetbrains.annotations.NotNull()
    edu.ucsmub.kqvoting.Pref.MyPreference p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    public final void initial() {
    }
    
    public final void bindViewToRG(@org.jetbrains.annotations.NotNull()
    java.util.List<edu.ucsmub.kqvoting.db.model.Selection> selectionStudentList) {
    }
    
    @android.annotation.SuppressLint(value = {"SetTextI18n"})
    private final void createPresetValueButton() {
    }
    
    @java.lang.Override()
    public void onBackPressed() {
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    private final void resetPref() {
    }
    
    public VotingRoomActivity() {
        super();
    }
}