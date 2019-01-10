package edu.ucsmub.kqvoting.Activity

import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Html
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import edu.ucsmub.kqvoting.Activity.Fragments.HomeFragment
import edu.ucsmub.kqvoting.Activity.Fragments.ListFragment
import edu.ucsmub.kqvoting.Activity.Fragments.ResultFragment
import edu.ucsmub.kqvoting.R
import edu.ucsmub.kqvoting.adapters.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import androidx.annotation.UiThread
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.firestore.FirebaseFirestore
import edu.ucsmub.kqvoting.db.AppDatabase
import edu.ucsmub.kqvoting.db.model.Selection
import edu.ucsmub.kqvoting.Pref.MyPreference
import edu.ucsmub.kqvoting.extra.BottomNavigationViewBehavior
import edu.ucsmub.kqvoting.extra.dataRefresh
import org.jetbrains.anko.doAsync
import android.provider.Settings.Secure
import android.content.ComponentName
import android.content.Context
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import edu.ucsmub.kqvoting.extra.UZawString
import edu.ucsmub.kqvoting.service.MyNotificationListenerService


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Toolbar
        val actionBarTitle = "<b>KQ</b>Voting"
        setSupportActionBar(main_tool_bar)
        supportActionBar!!.title = Html.fromHtml(actionBarTitle)

        Log.d("TITLE", supportActionBar!!.title.toString())
        //nav
        navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    main_viewpager.setCurrentItem(0, true)
                    true
                }
                R.id.navigation_dashboard -> {
                    main_viewpager.setCurrentItem(1, true)
                    true
                }
                R.id.navigation_notifications -> {
                    main_viewpager.setCurrentItem(2, true)
                    true
                }
                else -> {
                    false
                }
            }
        }
        (navigation.layoutParams as CoordinatorLayout.LayoutParams).behavior = BottomNavigationViewBehavior()

        //View Pager
        setupViewPager()
        settingOfViewPager()
    }

    private fun getNotiPermission() {
        val cn = ComponentName(this, MyNotificationListenerService::class.java)
        val flat = Settings.Secure.getString(contentResolver, "enabled_notification_listeners")
        val enabled = flat != null && flat!!.contains(cn.flattenToString())
        if (!enabled) {
            val text = "အင်တာနက် ကွန်နက်ရှင်ကို စစ်ဆေးပါ".UZawString()
            AlertDialog.Builder(this)
                .setTitle("Notification")
                .setMessage("Setting ထဲတွင် notification listener permission ကို ဖွင့်ပေးထားရန်လိုအပ်ပါသည်။".UZawString())
                .setPositiveButton("Set") { dialogInterface, _ ->
                    dialogInterface.dismiss()
                    val mIntent = Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
                    startActivity(mIntent)
                }
                .setCancelable(false).show()
        } else {
            val preference = MyPreference(this)
            if (preference.isFirstTime()) {
                showNoti(
                    "Welcome",
                    "KQ Voting application မှကြိုဆိုပါသည်။ application အသုံးပြုနည်း လမ်းညွှန်ကို https://bit.ly/2RfN0Kc တွင် ဝင်ရောက်ကြည့်ရှုလို့ရပါသည်"
                )
                preference.setFirstTime(false)
            }
        }
    }

    override fun onResume() {
        getNotiPermission()
        super.onResume()
    }

    private fun showNoti(title: String, body: String) {
        val APP_PACKAGE = "edu.ucsmub.kqvoting"
        val CHANNEL_ID = "$APP_PACKAGE.APP_CHANNEL"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel(CHANNEL_ID, "KQVoting", "Notification", NotificationManager.IMPORTANCE_HIGH)
        }
        val intent = Intent(this, NotificationActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        val mBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.king_icon)
            .setContentTitle(title.UZawString())
            .setContentText(body.UZawString())
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        NotificationManagerCompat.from(this).apply {
            notify(3, mBuilder.build())
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private fun createChannel(channel_id: String, name: String, description: String, importance: Int) {
        val mChannel = NotificationChannel(channel_id, name, importance)
        mChannel.description = description
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(mChannel)
    }

    private fun setSelection2Database() {
        val selectionList = ArrayList<Selection>()

        selectionList.add(
            Selection(
                "1",
                1,
                "မောင်မြတ်မင်းကို",
                "A",
                true,
                "https://raw.githubusercontent.com/htetaunglin/KQVote/master/MMK.png",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                "100015863230648",
                "Myat Minn Ko",
                arrayListOf(
                    "https://raw.githubusercontent.com/htetaunglin/KQVote/master/k1_1.png",
                    "https://raw.githubusercontent.com/htetaunglin/KQVote/master/k1_2.png",
                    "https://raw.githubusercontent.com/htetaunglin/KQVote/master/k1_3.png",
                    "https://raw.githubusercontent.com/htetaunglin/KQVote/master/k1_4.png"
                )
            )
        )
        selectionList.add(
            Selection(
                "2",
                2,
                "မောင်အေးချမ်း",
                "B",
                true,
                "https://raw.githubusercontent.com/htetaunglin/KQVote/master/AC.png",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                "100018113701338",
                "Aye Chan",
                arrayListOf(
                    "https://raw.githubusercontent.com/htetaunglin/KQVote/master/k2_1.png",
                    "https://raw.githubusercontent.com/htetaunglin/KQVote/master/k2_2.png",
                    "https://raw.githubusercontent.com/htetaunglin/KQVote/master/k2_3.png",
                    "https://raw.githubusercontent.com/htetaunglin/KQVote/master/k2_4.png"
                )
            )
        )
        selectionList.add(
            Selection(
                "3",
                3,
                "မောင်ရှင်းသန့်အောင်",
                "C",
                true,
                "https://raw.githubusercontent.com/htetaunglin/KQVote/master/STA.png",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                "100003920283378",
                "Mg Yoe Shine",
                arrayListOf(
                    "https://raw.githubusercontent.com/htetaunglin/KQVote/master/k3_1.png",
                    "https://raw.githubusercontent.com/htetaunglin/KQVote/master/k3_2.png",
                    "https://raw.githubusercontent.com/htetaunglin/KQVote/master/k3_3.png",
                    "https://raw.githubusercontent.com/htetaunglin/KQVote/master/k3_4.png"
                )
            )
        )
        selectionList.add(
            Selection(
                "4",
                4,
                "မောင်သန့်ဇင်ဆွေ",
                "D",
                true,
                "https://raw.githubusercontent.com/htetaunglin/KQVote/master/TZS.png",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                "100009604402288",
                "Thant Zin Swe",
                arrayListOf(
                    "https://raw.githubusercontent.com/htetaunglin/KQVote/master/k4_1.png",
                    "https://raw.githubusercontent.com/htetaunglin/KQVote/master/k4_2.png",
                    "https://raw.githubusercontent.com/htetaunglin/KQVote/master/k4_3.png",
                    "https://raw.githubusercontent.com/htetaunglin/KQVote/master/k4_4.png"
                )
            )
        )
        selectionList.add(
            Selection(
                "5",
                5,
                "မကြည်ဖြူသာဖေ",
                "A",
                false,
                "https://raw.githubusercontent.com/htetaunglin/KQVote/master/KPTP.png",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                "100012040428219",
                "Kyi Phyu Thar Phay",
                arrayListOf(
                    "https://raw.githubusercontent.com/htetaunglin/KQVote/master/q1_1.png",
                    "https://raw.githubusercontent.com/htetaunglin/KQVote/master/q1_2.png",
                    "https://raw.githubusercontent.com/htetaunglin/KQVote/master/q1_3.png",
                    "https://raw.githubusercontent.com/htetaunglin/KQVote/master/q1_4.png"
                )
            )
        )
        selectionList.add(
            Selection(
                "6",
                6,
                "မအေးဆုငြိမ်း",
                "B",
                false,
                "https://raw.githubusercontent.com/htetaunglin/KQVote/master/ASN.png",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                "100005482418170",
                "Aye Su Nyein",
                arrayListOf(
                    "https://raw.githubusercontent.com/htetaunglin/KQVote/master/q2_1.png",
                    "https://raw.githubusercontent.com/htetaunglin/KQVote/master/q2_2.png",
                    "https://raw.githubusercontent.com/htetaunglin/KQVote/master/q2_3.png",
                    "https://raw.githubusercontent.com/htetaunglin/KQVote/master/q2_4.png"
                )
            )
        )
        selectionList.add(
            Selection(
                "7",
                7,
                "မမိုမိုမြင့်",
                "C",
                false,
                "https://raw.githubusercontent.com/htetaunglin/KQVote/master/MMM.png",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                "100023014249140",
                "Triple M",
                arrayListOf(
                    "https://raw.githubusercontent.com/htetaunglin/KQVote/master/q3_1.png",
                    "https://raw.githubusercontent.com/htetaunglin/KQVote/master/q3_2.png",
                    "https://raw.githubusercontent.com/htetaunglin/KQVote/master/q3_3.png",
                    "https://raw.githubusercontent.com/htetaunglin/KQVote/master/q3_4.png"
                )
            )
        )
        selectionList.add(
            Selection(
                "8",
                8,
                "မသဉ္ဖာဆု",
                "D",
                false,
                "https://raw.githubusercontent.com/htetaunglin/KQVote/master/TZH.png",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                "100023338700032",
                "Thanzar Hsu",
                arrayListOf(
                    "https://raw.githubusercontent.com/htetaunglin/KQVote/master/q4_1.png",
                    "https://raw.githubusercontent.com/htetaunglin/KQVote/master/q4_2.png",
                    "https://raw.githubusercontent.com/htetaunglin/KQVote/master/q4_3.png",
                    "https://raw.githubusercontent.com/htetaunglin/KQVote/master/q4_4.png"
                )
            )
        )

        val appDatabase = AppDatabase.getInstance(this)
        doAsync {
            for (s in selectionList) {
                appDatabase.selectionDao().insertSelection(s)
            }

        }
        val myPref = MyPreference(this)
        myPref.setFirstTime(false)
    }


    private fun setupViewPager() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(HomeFragment())
        adapter.addFragment(ListFragment())
        adapter.addFragment(ResultFragment())
        main_viewpager.adapter = adapter
    }

    private fun settingOfViewPager() {
        main_viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                BottomNavigationViewBehavior().slideUp(navigation)
                navigation.menu.getItem(position).isChecked = true
            }
        })
    }

    var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please press again to exit", Toast.LENGTH_SHORT).show()
        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item!!.itemId) {
            R.id.notification -> {
                startActivity(Intent(this, NotificationActivity::class.java))
                true
            }
            R.id.setting -> {
                startActivity(Intent(this, SettingActivity::class.java))
                true
            }
            else -> {
                false
            }
        }
    }
}
