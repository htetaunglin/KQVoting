package edu.ucsmub.kqvoting.customUI

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.kyawhtut.FontUtil
import edu.ucsmub.kqvoting.extra.UZawString
import me.myatminsoe.mdetect.MDetect
import me.myatminsoe.mdetect.Rabbit

class UZawTextView : AppCompatTextView {

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
//        var theText = ""
//        val fontUtil = FontUtil()
//        theText = if (MDetect.isUnicode()) {
//            if (fontUtil.isZawgyi(text.toString())) {
//                Rabbit.zg2uni(text.toString())
//            } else {
//                text.toString()
//            }
//        } else {
//            if (fontUtil.isZawgyi(text)) {
//                text.toString()
//            } else {
//                Rabbit.uni2zg(text.toString())
//            }
//        }
        super.setText(text.toString().UZawString(), type)
    }
}