package edu.ucsmub.kqvoting.Activity

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import edu.ucsmub.kqvoting.R
import edu.ucsmub.kqvoting.adapters.SlidePagerAdapter
import kotlinx.android.synthetic.main.activity_show_image.*

class ShowPhotosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_image)

        setSupportActionBar(show_toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }

        val imageList = intent.getStringArrayListExtra("photos")
        val index = intent.getIntExtra("index", 0)
        val name = intent.getStringExtra("name")
        supportActionBar!!.title = name

        val pagerAdapter = SlidePagerAdapter(this, imageList)
        show_viewPager.adapter = pagerAdapter
        show_viewPager.setCurrentItem(index, false)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}