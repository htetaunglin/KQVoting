package edu.ucsmub.kqvoting.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import edu.ucsmub.kqvoting.R
import edu.ucsmub.kqvoting.customUI.GlideApp
import kotlinx.android.synthetic.main.photo_view.view.*

class SlidePagerAdapter(var context: Context, var images: List<String>) : PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun getCount(): Int = images.size

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context)
        val imageLayout = inflater.inflate(R.layout.photo_view, container, false)
        with(imageLayout) {
            GlideApp.with(context)
                .load(images[position])
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.errorimage1)
                .into(this.photoView)
        }
        container.addView(imageLayout)
        return imageLayout
    }
}