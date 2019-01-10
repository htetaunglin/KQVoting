package edu.ucsmub.kqvoting.customUI.customRatioBtn;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;

import edu.ucsmub.kqvoting.R;
import edu.ucsmub.kqvoting.customUI.GlideApp;
import edu.ucsmub.kqvoting.customUI.MyBounceInterpolator;
import edu.ucsmub.kqvoting.customUI.UZawTextView;
import edu.ucsmub.kqvoting.db.model.Selection;
import edu.ucsmub.kqvoting.extra.Ratio;

public class PresetValueButton extends RelativeLayout implements RadioCheckable {
    //Animation
    MyBounceInterpolator interpolator;

    // Views
    public UZawTextView nametv;
    public UZawTextView sectiontv;
    public ImageView profileUI;
    public ImageView voteHeart;

    //value container
    String stringName;
    String stringSection;

    Uri profile;
    boolean vote;

    // Variables
    private OnClickListener mOnClickListener;
    private OnTouchListener mOnTouchListener;
    private boolean mChecked;
    private ArrayList<OnCheckedChangeListener> mOnCheckedChangeListeners = new ArrayList<>();

    public Selection selection;


    //================================================================================
    // Constructors
    //================================================================================

    public PresetValueButton(Context context) {
        super(context);
        setupView();
    }

    public PresetValueButton(Context context, Selection student) {
        super(context);
        this.selection = student;
        stringName = student.getName();
        stringSection = student.getSection();
        vote = student.isVote();
        profile = Uri.parse(student.getProfile_image());
        setupView();
    }

    public PresetValueButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        parseAttributes(attrs);
        setupView();
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public PresetValueButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        parseAttributes(attrs);
        setupView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PresetValueButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        parseAttributes(attrs);
        setupView();
    }

    //================================================================================
    // Init & inflate methods
    //================================================================================

    private void parseAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs,
                R.styleable.PresetValueButton, 0, 0);
        try {
            stringName = a.getString(R.styleable.PresetValueButton_name);
            stringSection = a.getString(R.styleable.PresetValueButton_section);
            profile = Uri.parse(a.getString(R.styleable.PresetValueButton_profile));
            vote = a.getBoolean(R.styleable.PresetValueButton_vote, false);
        } finally {
            a.recycle();
        }
    }

    // Template method
    private void setupView() {
        inflateView();
        bindView();
        setCustomTouchListener();
    }

    protected void inflateView() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View v = inflater.inflate(R.layout.voting_card, this, true);

        int buttonWidth = Ratio.Companion.getScreenWidth((Activity) getContext());
        v.setLayoutParams(new LinearLayout.LayoutParams(buttonWidth / 2, ViewGroup.LayoutParams.WRAP_CONTENT));
        Ratio ratio = new Ratio(getContext());

        nametv = v.findViewById(R.id.nametv);
        sectiontv = v.findViewById(R.id.section);
        profileUI = v.findViewById(R.id.profile);
        ratio.calculateSize(profileUI, (Activity) getContext(), 300, 300);
        ratio.calculateSize(sectiontv, (Activity) getContext(), 200, PresetRadioGroup.LayoutParams.WRAP_CONTENT);
        voteHeart = v.findViewById(R.id.voteHeart);
    }

    protected void bindView() {
        nametv.setText(stringName);
        nametv.setSelected(true);
        sectiontv.setText("Section - "+stringSection);
        sectiontv.setSelected(true);
        voteHeart.setImageResource(vote ? R.drawable.heart_red : R.drawable.heart_white);
        GlideApp.with(getContext())
                .load(profile)
                .placeholder(R.drawable.pending_people)
                .error(R.drawable.errorimage1)
                .into(profileUI);
    }

    //================================================================================
    // Overriding default behavior
    //================================================================================

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        mOnClickListener = l;
    }

    protected void setCustomTouchListener() {
        super.setOnTouchListener(new TouchListener());
    }

    @Override
    public void setOnTouchListener(OnTouchListener onTouchListener) {
        mOnTouchListener = onTouchListener;
    }

    public OnTouchListener getOnTouchListener() {
        return mOnTouchListener;
    }

    private void onTouchDown(MotionEvent motionEvent) {
        setChecked(true);
    }

    private void onTouchUp(MotionEvent motionEvent) {
        // Handle user defined click listeners
        if (mOnClickListener != null) {
            mOnClickListener.onClick(this);
        }
    }
    //================================================================================
    // Public methods
    //================================================================================

    public void setCheckedState() {
        voteHeart.setImageResource(mChecked ? R.drawable.heart_red : R.drawable.heart_white);
    }

    public void setNormalState() {
        voteHeart.setImageResource(mChecked ? R.drawable.heart_red : R.drawable.heart_white);
    }

    public String getStringName() {
        return stringName;
    }

    public void setStringName(String stringName) {
        this.stringName = stringName;
    }

    public String getStringSection() {
        return stringSection;
    }

    public void setStringSection(String stringSection) {
        this.stringSection = stringSection;
    }

    public Uri getProfile() {
        return profile;
    }

    public void setProfile(Uri profile) {
        this.profile = profile;
    }

    public boolean isVote() {
        return vote;
    }

    public void setVote(boolean vote) {
        this.vote = vote;
    }

    //================================================================================
    // Checkable implementation
    //================================================================================

    @Override
    public void setChecked(boolean checked) {
        if (mChecked != checked) {
            mChecked = checked;
            if (!mOnCheckedChangeListeners.isEmpty()) {
                for (int i = 0; i < mOnCheckedChangeListeners.size(); i++) {
                    mOnCheckedChangeListeners.get(i).onCheckedChanged(this, mChecked);
                }
            }
            if (mChecked) {
                animationLoad(voteHeart);
                setCheckedState();
            } else {
                setNormalState();
            }
        }
    }

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void toggle() {
        setChecked(!mChecked);
    }

    @Override
    public void addOnCheckChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        mOnCheckedChangeListeners.add(onCheckedChangeListener);
    }

    @Override
    public void removeOnCheckChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        mOnCheckedChangeListeners.remove(onCheckedChangeListener);
    }

    //================================================================================
    // Inner classes
    //================================================================================
    private final class TouchListener implements OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    onTouchDown(event);
                    break;
                case MotionEvent.ACTION_UP:
                    onTouchUp(event);
                    break;
            }
            if (mOnTouchListener != null) {
                mOnTouchListener.onTouch(v, event);
            }
            return true;
        }
    }

    private void animationLoad(View v) {
        Animation myAnim = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
        v.startAnimation(myAnim);
        interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);
        myAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
