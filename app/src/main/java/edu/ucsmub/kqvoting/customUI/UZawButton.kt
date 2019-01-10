package edu.ucsmub.kqvoting.customUI

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import com.kyawhtut.FontUtil
import edu.ucsmub.kqvoting.extra.UZawString
import me.myatminsoe.mdetect.MDetect
import me.myatminsoe.mdetect.Rabbit

class UZawButton : AppCompatButton {
    constructor(context: Context) : super(context) {
        text = this.text
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        text = this.text
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        text = this.text
    }

    override fun setText(text: CharSequence?, type: BufferType?) {
        super.setText(text.toString().UZawString(), type)
    }
}