package edu.ucsmub.kqvoting.Activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import com.google.firebase.messaging.FirebaseMessaging
import edu.ucsmub.kqvoting.Pref.MyPreference
import edu.ucsmub.kqvoting.R
import edu.ucsmub.kqvoting.customUI.UZawTextView
import edu.ucsmub.kqvoting.db.AppDatabase
import edu.ucsmub.kqvoting.db.model.Selection

import kotlinx.android.synthetic.main.activity_launch.*
import me.myatminsoe.mdetect.MDetect
import org.jetbrains.anko.doAsync

class LaunchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        FirebaseMessaging.getInstance().isAutoInitEnabled = true
        launchFun()
        initUI()


    }

    fun initUI() {
        retrybtn.visibility = View.GONE
        (loadingtxt as UZawTextView).text = "Loading...."
    }

    fun retry() {
        retrybtn.visibility = View.VISIBLE
        (loadingtxt as UZawTextView).text = "အင်တာနက် ကွန်နက်ရှင်ကို စစ်ဆေးပါ"
        retrybtn.setOnClickListener {
            launchFun()
        }
    }

    fun launchFun() {
        initUI()
        val myPref = MyPreference(this)
        if (myPref.isFirstTime()) {
            var count = 0
            val appDatabase = AppDatabase.getInstance(this)
            doAsync {
                appDatabase.selectionDao().deleteAllData()

                val db = FirebaseFirestore.getInstance()
                val collection = db.collection("selection")
                collection.get(Source.SERVER).addOnSuccessListener { querySnapshot ->

                    launchprogressBar.max = querySnapshot.documents.size
                    querySnapshot.documents.forEach {
                        val id = it["hash"] as String
                        val order = it["order"].toString().toInt()
                        val name = it["name"] as String
                        val section = it["section"] as String
                        val profile = it["profile"] as String
                        val isBoy = it["isBoy"] as Boolean
                        val detail = it["detail"] as String
                        val facebookID = it["fbId"] as String
                        val facebookName = it["fbName"] as String
                        val photos = it["photos"] as ArrayList<String>
                        val firstCount = it["firstCount"].toString().toInt()
                        val secondCount = it["secondCount"].toString().toInt()

                        val selection =
                            Selection(
                                id,
                                order,
                                name,
                                section,
                                isBoy,
                                profile,
                                detail,
                                facebookID,
                                facebookName,
                                photos,
                                false,
                                firstCount,
                                secondCount
                            )

                        doAsync {
                            appDatabase.selectionDao().insertSelection(selection)
                            launchprogressBar.progress = count++
                        }
                    }
                    startActivity(Intent(this@LaunchActivity, MainActivity::class.java))
                    this@LaunchActivity.finish()
                }.addOnFailureListener {
                    //TODO
                    retry()
                    Log.e("ERROR", it.toString())
                }
            }
        } else {
            launchprogressBar.max = 10
            Handler().postDelayed(
                {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        launchprogressBar.setProgress(1, true)
                        launchprogressBar.setProgress(2, true)
                        launchprogressBar.setProgress(3, true)
                        launchprogressBar.setProgress(4, true)
                        launchprogressBar.setProgress(5, true)
                        launchprogressBar.setProgress(6, true)
                        launchprogressBar.setProgress(7, true)
                        launchprogressBar.setProgress(8, true)
                        launchprogressBar.setProgress(9, true)
                        launchprogressBar.setProgress(10, true)
                    } else {
                        launchprogressBar.progress = 0
                        launchprogressBar.progress = 1
                        launchprogressBar.progress = 2
                        launchprogressBar.progress = 3
                        launchprogressBar.progress = 4
                        launchprogressBar.progress = 5
                        launchprogressBar.progress = 6
                        launchprogressBar.progress = 7
                        launchprogressBar.progress = 8
                        launchprogressBar.progress = 9
                        launchprogressBar.progress = 10
                    }
                    startActivity(Intent(this@LaunchActivity, MainActivity::class.java))
                    this@LaunchActivity.finish()
                }, 2000
            )
        }
    }
}

