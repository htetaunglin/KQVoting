package com.bumptech.glide;

import android.content.Context;
import androidx.annotation.NonNull;
import com.bumptech.glide.manager.Lifecycle;
import com.bumptech.glide.manager.RequestManagerRetriever;
import com.bumptech.glide.manager.RequestManagerTreeNode;
import edu.ucsmub.kqvoting.customUI.GlideRequests;
import java.lang.Override;

/**
 * Generated code, do not modify
 */
final class GeneratedRequestManagerFactory implements RequestManagerRetriever.RequestManagerFactory {
  @Override
  @NonNull
  public RequestManager build(@NonNull Glide glide, @NonNull Lifecycle lifecycle,
      @NonNull RequestManagerTreeNode treeNode, @NonNull Context context) {
    return new GlideRequests(glide, lifecycle, treeNode, context);
  }
}
