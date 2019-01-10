package edu.ucsmub.kqvoting.Activity.Fragments;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J&\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0017J\u0010\u0010$\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0003J\u0014\u0010%\u001a\u00020\u000e*\u00020&2\u0006\u0010\'\u001a\u00020&H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012R\u001a\u0010\u0016\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0006\"\u0004\b\u0018\u0010\b\u00a8\u0006("}, d2 = {"Ledu/ucsmub/kqvoting/Activity/Fragments/ResultFragment;", "Landroidx/fragment/app/Fragment;", "()V", "currentCount", "", "getCurrentCount", "()I", "setCurrentCount", "(I)V", "db", "Lcom/google/firebase/firestore/FirebaseFirestore;", "getDb", "()Lcom/google/firebase/firestore/FirebaseFirestore;", "kingID", "", "getKingID", "()Ljava/lang/String;", "setKingID", "(Ljava/lang/String;)V", "queenID", "getQueenID", "setQueenID", "totalCount", "getTotalCount", "setTotalCount", "initResultUI", "", "view", "Landroid/view/View;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "showResult", "toPercentIn", "", "baseNum", "app_debug"})
public final class ResultFragment extends androidx.fragment.app.Fragment {
    private int currentCount;
    private int totalCount;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String kingID;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String queenID;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.FirebaseFirestore db = null;
    private java.util.HashMap _$_findViewCache;
    
    public final int getCurrentCount() {
        return 0;
    }
    
    public final void setCurrentCount(int p0) {
    }
    
    public final int getTotalCount() {
        return 0;
    }
    
    public final void setTotalCount(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getKingID() {
        return null;
    }
    
    public final void setKingID(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getQueenID() {
        return null;
    }
    
    public final void setQueenID(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.firebase.firestore.FirebaseFirestore getDb() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @android.annotation.SuppressLint(value = {"SetTextI18n"})
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    private final java.lang.String toPercentIn(float $receiver, float baseNum) {
        return null;
    }
    
    private final void initResultUI(android.view.View view) {
    }
    
    @android.annotation.SuppressLint(value = {"SetTextI18n"})
    private final void showResult(android.view.View view) {
    }
    
    public ResultFragment() {
        super();
    }
}