package edu.ucsmub.kqvoting.adapters;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001bB\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\u0002\u0010\bJ\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u0012H\u0017J\u0018\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0012H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010\u00a8\u0006\u001c"}, d2 = {"Ledu/ucsmub/kqvoting/adapters/ConfirmListAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Ledu/ucsmub/kqvoting/adapters/ConfirmListAdapter$MyViewHolder;", "context", "Landroid/content/Context;", "list", "", "Ledu/ucsmub/kqvoting/extra/ConfirmItem;", "(Landroid/content/Context;Ljava/util/List;)V", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "getList", "()Ljava/util/List;", "setList", "(Ljava/util/List;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "MyViewHolder", "app_debug"})
public final class ConfirmListAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<edu.ucsmub.kqvoting.adapters.ConfirmListAdapter.MyViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private android.content.Context context;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<edu.ucsmub.kqvoting.extra.ConfirmItem> list;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public edu.ucsmub.kqvoting.adapters.ConfirmListAdapter.MyViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @android.annotation.SuppressLint(value = {"SetTextI18n"})
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    edu.ucsmub.kqvoting.adapters.ConfirmListAdapter.MyViewHolder holder, int position) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getContext() {
        return null;
    }
    
    public final void setContext(@org.jetbrains.annotations.NotNull()
    android.content.Context p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<edu.ucsmub.kqvoting.extra.ConfirmItem> getList() {
        return null;
    }
    
    public final void setList(@org.jetbrains.annotations.NotNull()
    java.util.List<edu.ucsmub.kqvoting.extra.ConfirmItem> p0) {
    }
    
    public ConfirmListAdapter(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.util.List<edu.ucsmub.kqvoting.extra.ConfirmItem> list) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R\u001a\u0010\u0014\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\b\"\u0004\b\u0016\u0010\n\u00a8\u0006\u0017"}, d2 = {"Ledu/ucsmub/kqvoting/adapters/ConfirmListAdapter$MyViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "categoryIcon", "Landroid/widget/ImageView;", "getCategoryIcon", "()Landroid/widget/ImageView;", "setCategoryIcon", "(Landroid/widget/ImageView;)V", "categoryName", "Ledu/ucsmub/kqvoting/customUI/UZawTextView;", "getCategoryName", "()Ledu/ucsmub/kqvoting/customUI/UZawTextView;", "setCategoryName", "(Ledu/ucsmub/kqvoting/customUI/UZawTextView;)V", "name", "getName", "setName", "profile", "getProfile", "setProfile", "app_debug"})
    public static final class MyViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private android.widget.ImageView profile;
        @org.jetbrains.annotations.NotNull()
        private edu.ucsmub.kqvoting.customUI.UZawTextView name;
        @org.jetbrains.annotations.NotNull()
        private android.widget.ImageView categoryIcon;
        @org.jetbrains.annotations.NotNull()
        private edu.ucsmub.kqvoting.customUI.UZawTextView categoryName;
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getProfile() {
            return null;
        }
        
        public final void setProfile(@org.jetbrains.annotations.NotNull()
        android.widget.ImageView p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final edu.ucsmub.kqvoting.customUI.UZawTextView getName() {
            return null;
        }
        
        public final void setName(@org.jetbrains.annotations.NotNull()
        edu.ucsmub.kqvoting.customUI.UZawTextView p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getCategoryIcon() {
            return null;
        }
        
        public final void setCategoryIcon(@org.jetbrains.annotations.NotNull()
        android.widget.ImageView p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final edu.ucsmub.kqvoting.customUI.UZawTextView getCategoryName() {
            return null;
        }
        
        public final void setCategoryName(@org.jetbrains.annotations.NotNull()
        edu.ucsmub.kqvoting.customUI.UZawTextView p0) {
        }
        
        public MyViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View itemView) {
            super(null);
        }
    }
}