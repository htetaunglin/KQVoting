package edu.ucsmub.kqvoting.customUI.customRatioBtn;

import android.view.View;
import android.widget.Checkable;

public interface RadioCheckable extends Checkable {
    void addOnCheckChangeListener(OnCheckedChangeListener onCheckedChangeListener);
    void removeOnCheckChangeListener(OnCheckedChangeListener onCheckedChangeListener);

    public static interface OnCheckedChangeListener {
        void onCheckedChanged(View buttonView, boolean isChecked);
    }
}
