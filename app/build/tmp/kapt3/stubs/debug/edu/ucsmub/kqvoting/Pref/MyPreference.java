package edu.ucsmub.kqvoting.Pref;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0016\u001a\u00020\u0006J\u0006\u0010\u0017\u001a\u00020\u0006J\u0006\u0010\u0018\u001a\u00020\u0006J\b\u0010\u0019\u001a\u00020\u0015H\u0002J\u0006\u0010\u001a\u001a\u00020\u0006J\u0006\u0010\u001b\u001a\u00020\u0006J\u0006\u0010\u001c\u001a\u00020\u001dJ\u0006\u0010\u001e\u001a\u00020\u001dJ\u000e\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u001dJ\u000e\u0010\"\u001a\u00020 2\u0006\u0010#\u001a\u00020\u0006J\u000e\u0010$\u001a\u00020 2\u0006\u0010#\u001a\u00020\u0006J\u000e\u0010%\u001a\u00020 2\u0006\u0010#\u001a\u00020\u0006J\u000e\u0010&\u001a\u00020 2\u0006\u0010\'\u001a\u00020\u0006J\u000e\u0010(\u001a\u00020 2\u0006\u0010#\u001a\u00020\u0006J\u000e\u0010)\u001a\u00020 2\u0006\u0010*\u001a\u00020\u001dR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n \u0010*\u0004\u0018\u00010\u000f0\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0004R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006+"}, d2 = {"Ledu/ucsmub/kqvoting/Pref/MyPreference;", "", "mContext", "Landroid/content/Context;", "(Landroid/content/Context;)V", "FIRST_ITME", "", "INNOCENCE_ID", "KING_ID", "MY_PREFER", "POPULAR_ID", "QUEEN_ID", "UZAWFONT", "YOUR_VOTE_KEY", "editor", "Landroid/content/SharedPreferences$Editor;", "kotlin.jvm.PlatformType", "getMContext", "()Landroid/content/Context;", "setMContext", "prefer", "Landroid/content/SharedPreferences;", "getInnocenceID", "getKingID", "getPopularID", "getPreferences", "getQRKey", "getQueenID", "isFirstTime", "", "isZawgyi", "setFirstTime", "", "boolean", "setInnocenceID", "id", "setKingID", "setPopularID", "setQRKey", "key", "setQueenID", "setZawgyi", "b", "app_debug"})
public final class MyPreference {
    private final java.lang.String MY_PREFER = "MyPref";
    private final java.lang.String FIRST_ITME = "FIRST_TIME";
    private final java.lang.String KING_ID = "KING_ID";
    private final java.lang.String QUEEN_ID = "QUEEN_ID";
    private final java.lang.String POPULAR_ID = "POPULAR_ID";
    private final java.lang.String INNOCENCE_ID = "INNOCENCE_ID";
    private final java.lang.String YOUR_VOTE_KEY = "YOUR_KEY";
    private final java.lang.String UZAWFONT = "UZAW";
    private final android.content.SharedPreferences prefer = null;
    private final android.content.SharedPreferences.Editor editor = null;
    @org.jetbrains.annotations.NotNull()
    private android.content.Context mContext;
    
    private final android.content.SharedPreferences getPreferences() {
        return null;
    }
    
    public final boolean isFirstTime() {
        return false;
    }
    
    public final void setFirstTime(boolean p0_32355860) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getKingID() {
        return null;
    }
    
    public final void setKingID(@org.jetbrains.annotations.NotNull()
    java.lang.String id) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getQueenID() {
        return null;
    }
    
    public final void setQueenID(@org.jetbrains.annotations.NotNull()
    java.lang.String id) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPopularID() {
        return null;
    }
    
    public final void setPopularID(@org.jetbrains.annotations.NotNull()
    java.lang.String id) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getInnocenceID() {
        return null;
    }
    
    public final void setInnocenceID(@org.jetbrains.annotations.NotNull()
    java.lang.String id) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getQRKey() {
        return null;
    }
    
    public final void setQRKey(@org.jetbrains.annotations.NotNull()
    java.lang.String key) {
    }
    
    public final boolean isZawgyi() {
        return false;
    }
    
    public final void setZawgyi(boolean b) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getMContext() {
        return null;
    }
    
    public final void setMContext(@org.jetbrains.annotations.NotNull()
    android.content.Context p0) {
    }
    
    public MyPreference(@org.jetbrains.annotations.NotNull()
    android.content.Context mContext) {
        super();
    }
}