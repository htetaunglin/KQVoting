package edu.ucsmub.kqvoting.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.ucsmub.kqvoting.R
import edu.ucsmub.kqvoting.customUI.GlideApp
import kotlinx.android.synthetic.main.activity_setting.*
import android.content.Intent
import android.net.Uri
import android.view.MenuItem
import android.view.View
import edu.ucsmub.kqvoting.extra.dataRefresh


class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        setSupportActionBar(main_tool_bar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Setting"

        GlideApp.with(this)
            .load("https://scontent.frgn1-1.fna.fbcdn.net/v/t31.0-8/27021170_1847282028902857_3650957232680529891_o.jpg?_nc_cat=104&_nc_ht=scontent.frgn1-1.fna&oh=c30813034dc8b34ad51463e5c6310ae9&oe=5C9E401E")
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.errorimage1)
            .into(androiddev)

        GlideApp.with(this)
            .load("https://scontent.frgn1-1.fna.fbcdn.net/v/t1.0-1/41141768_2225628997716514_3914495668168687616_n.jpg?_nc_cat=109&_nc_ht=scontent.frgn1-1.fna&oh=21cafc249e97e58d6e6a3ed55a0fdf20&oe=5C98D330")
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.errorimage1)
            .into(webdev)

        androiddev.setOnClickListener {
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/100008632337396")))
            } catch (e: Exception) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.facebook.com/profile.php?id=100008632337396")
                    )
                )
            }
        }

        webdev.setOnClickListener {
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/100008083052495")))
            } catch (e: Exception) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.facebook.com/profile.php?id=100008083052495")
                    )
                )
            }
        }

        refreshBtn.setOnClickListener {
            dataRefresh(this)
        }


//        https://www.facebook.com/photo.php?fbid=1847282028902857&set=a.1388298001467931&type=3&theater
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    public fun gotoFirebase(v: View) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://firebase.google.com/")))
    }
}