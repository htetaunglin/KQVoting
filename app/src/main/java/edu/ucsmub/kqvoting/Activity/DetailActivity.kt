package edu.ucsmub.kqvoting.Activity

import android.os.Bundle
import android.view.MenuItem
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.AppBarLayout
import edu.ucsmub.kqvoting.R
import edu.ucsmub.kqvoting.customUI.GlideApp
import edu.ucsmub.kqvoting.db.model.Selection
import edu.ucsmub.kqvoting.extra.Ratio
import kotlinx.android.synthetic.main.activity_detail.*
import android.widget.LinearLayout
import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Build
import androidx.appcompat.widget.AppCompatImageView
import android.util.Log


class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        //toolbar
        setSupportActionBar(detail_toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val selection = getSelectionFromPrev()
        supportActionBar!!.title = if (selection.isBoy) "Boy Selection" else "Girl Selection"
        //Build UI
        buildUI()

        //DataBind
        loadDataBind(selection)


    }

    private fun buildUI() {
        //View bind
        val ratio = Ratio(this)
        ratio.calculateSize(detail_appbar, this, ViewGroup.LayoutParams.MATCH_PARENT, 480)
        ratio.calculateSize(cover_photo, this, ViewGroup.LayoutParams.MATCH_PARENT, 480)
        detail_appbar.setExpanded(true, true)
        detail_appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            var scrollRange = -1
            if (scrollRange == -1) {
                scrollRange = appBarLayout.totalScrollRange
            }
            if (scrollRange + verticalOffset == 0) {
                //close
            } else {
                //Open
            }
        })

    }

    private fun loadDataBind(selection: Selection) {
        GlideApp.with(this)
            .load("https://raw.githubusercontent.com/htetaunglin/KQVote/master/cover_.jpg")
            .placeholder(R.drawable.toolbar_placeholder)
            .error(R.drawable.errorimage1)
            .into(cover_photo)


        name_detail.text = selection.name
        detail_section.text = "Section - ${selection.section}"
        detail_info.text = selection.detail
        detail_facebook.text = selection.facebookName
        detail_facebook.paintFlags = detail_facebook.paintFlags or Paint.UNDERLINE_TEXT_FLAG


        detail_facebook.setOnClickListener {
            try {
                val link1 = "fb://profile/${selection.facebookID}"
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/${selection.facebookID}")))
            } catch (e: Exception) {
                val link1 = "https://www.facebook.com/profile.php?id=${selection.facebookID}"
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.facebook.com/profile.php?id=${selection.facebookID}")
                    )
                )
            }
        }

        GlideApp.with(this)
            .load(selection.profile_image)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.errorimage1)
            .into(profile_image)

        LoadPhotos(selection.photos)

        profile_image.setOnClickListener {
            val intent = Intent(this@DetailActivity, ShowPhotosActivity::class.java)
            intent.putStringArrayListExtra("photos", arrayListOf(selection.profile_image))
            val index = 0
            intent.putExtra("index", index)
            intent.putExtra("name", name_detail.text.toString())
            val transitionName = "photos"
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val transitionActivityOptions =
                    ActivityOptions.makeSceneTransitionAnimation(this, profile_image, transitionName)
                startActivity(intent, transitionActivityOptions!!.toBundle())
            } else {
                startActivity(intent)
            }
        }

        cover_photo.setOnClickListener {
            val intent = Intent(this@DetailActivity, ShowPhotosActivity::class.java)
            intent.putStringArrayListExtra(
                "photos",
                arrayListOf("https://raw.githubusercontent.com/htetaunglin/KQVote/master/cover_.jpg")
            )
            val index = 0
            intent.putExtra("index", index)
            intent.putExtra("name", name_detail.text.toString())
            val transitionName = "photos"
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val transitionActivityOptions =
                    ActivityOptions.makeSceneTransitionAnimation(this, cover_photo, transitionName)
                startActivity(intent, transitionActivityOptions!!.toBundle())
            } else {
                startActivity(intent)
            }
        }
    }

    @SuppressLint("NewApi")
    private fun LoadPhotos(images: ArrayList<String>) {
        val imageList = ArrayList<AppCompatImageView>()
        val ratio = Ratio(this)
        for (i in 0 until images.size) {
            val imageView = AppCompatImageView(this)
            imageView.setPadding(4, 4, 4, 4)
            imageView.layoutParams = LinearLayout.LayoutParams(300, 450)
            ratio.calculateSize(imageView, this, 300, 450)
            GlideApp.with(this)
                .load(images[i])
                .placeholder(R.drawable.imgview_placeholder)
                .error(R.drawable.errorimage1)
                .into(imageView)
            imageView.transitionName = "photos"
            imageList.add(imageView)
        }
        addLinear(imageList, images)
    }


    fun addLinear(imageList: ArrayList<AppCompatImageView>, images: ArrayList<String>) {
        for (imageView in imageList) {
            image_linear.addView(imageView)
            imageView.setOnClickListener {
                val intent = Intent(this@DetailActivity, ShowPhotosActivity::class.java)
                intent.putStringArrayListExtra("photos", images)
                val index = imageList.indexOf(imageView)
                intent.putExtra("index", index)
                intent.putExtra("name", name_detail.text.toString())
                val transitionName = "photos"
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    val transitionActivityOptions =
                        ActivityOptions.makeSceneTransitionAnimation(this, imageView, transitionName)
                    startActivity(intent, transitionActivityOptions!!.toBundle())
                } else {
                    startActivity(intent)
                }
            }
        }
    }

    private fun getSelectionFromPrev(): Selection {
        return intent!!.getSerializableExtra("selection") as Selection
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home) {
            super.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}