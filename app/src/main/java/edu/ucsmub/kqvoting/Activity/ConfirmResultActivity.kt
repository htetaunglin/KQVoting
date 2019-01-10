package edu.ucsmub.kqvoting.Activity

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import edu.ucsmub.kqvoting.Pref.MyPreference
import edu.ucsmub.kqvoting.R
import edu.ucsmub.kqvoting.adapters.ConfirmListAdapter
import edu.ucsmub.kqvoting.db.AppDatabase
import edu.ucsmub.kqvoting.extra.Category
import edu.ucsmub.kqvoting.extra.ConfirmItem
import edu.ucsmub.kqvoting.extra.UZawString
import kotlinx.android.synthetic.main.activity_confirm_result.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ConfirmResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_result)

        val actionBarTitle = "<b>KQ</b>Voting"
        setSupportActionBar(conf_tool_bar)
        supportActionBar!!.title = Html.fromHtml(actionBarTitle)

        val pref = MyPreference(this)
        val confirmItemList = ArrayList<ConfirmItem>()

        val confAdapter = ConfirmListAdapter(this, confirmItemList)

        recycler_voted_list.layoutManager = LinearLayoutManager(this)
        recycler_voted_list.setHasFixedSize(true)
        recycler_voted_list.adapter = confAdapter
        confAdapter.notifyDataSetChanged()


        //SetData
        doAsync {
            val appDatabase = AppDatabase.getInstance(this@ConfirmResultActivity)

            val voteKing = appDatabase.selectionDao().getSelectionByID(pref.getKingID())
            val c1 = ConfirmItem(voteKing, Category.King, R.drawable.king_icon)
            confirmItemList.add(c1)

            val voteQueen = appDatabase.selectionDao().getSelectionByID(pref.getQueenID())
            val c2 = ConfirmItem(voteQueen, Category.Queen, R.drawable.crown)
            confirmItemList.add(c2)

            val votePopular = appDatabase.selectionDao().getSelectionByID(pref.getPopularID())
            val c3 = ConfirmItem(votePopular, Category.Popularity, R.drawable.male_icon)
            confirmItemList.add(c3)

            val voteInnocence = appDatabase.selectionDao().getSelectionByID(pref.getInnocenceID())
            val c4 = ConfirmItem(voteInnocence, Category.Innocence, R.drawable.female_icon)
            confirmItemList.add(c4)
            Log.d(
                "Hello",
                "${pref.getKingID()} and ${pref.getQueenID()} and ${pref.getPopularID()} and ${pref.getInnocenceID()}"
            )
        }

        revotebtn.setOnClickListener {
            resetInPref()
            startActivity(Intent(this, VotingRoomActivity::class.java))
            finish()
        }
        confirmbtn.setOnClickListener {
            val pref = MyPreference(this)
            //TODO Sent to server

            doAsync {
                uiThread {
                    val pd = ProgressDialog(this@ConfirmResultActivity)
                    pd.setMessage("Loading....")
                    pd.setCancelable(false)
                    pd.show()

                    val db = FirebaseFirestore.getInstance()
                    val collection = db.collection("key_collection")

                    val voteHashMap = HashMap<String, Any>()
                    voteHashMap["kingID"] = pref.getKingID().toString()
                    voteHashMap["queenID"] = pref.getQueenID().toString()
                    voteHashMap["popularID"] = pref.getPopularID().toString()
                    voteHashMap["innocenceID"] = pref.getInnocenceID().toString()
                    voteHashMap["isVoted"] = true
                    collection.document(pref.getQRKey()).update(voteHashMap).addOnSuccessListener {
                        pd.dismiss()
                        resetInPref()
                        pref.setQRKey("")
                        finish()
                    }.addOnFailureListener {
                        pd.dismiss()
                        AlertDialog.Builder(this@ConfirmResultActivity)
                            .setTitle("Error!")
                            .setMessage("Internet Connection ကို စစ်ဆေးပေးပါ)".UZawString())
                            .setPositiveButton("ေကာင္းၿပီ".UZawString()) { dialogInterface, _ ->
                                dialogInterface.dismiss()
                            }.show()
                    }
                }
            }

        }
    }


    override fun onDestroy() {
        resetInPref()
        super.onDestroy()
    }

    private fun resetInPref() {
        val pref = MyPreference(this)
        pref.setKingID("")
        pref.setQueenID("")
        pref.setPopularID("")
        pref.setInnocenceID("")
    }

    override fun onBackPressed() {
        //TODO show dialog
        AlertDialog.Builder(this)
            .setTitle("Warning!")
            .setMessage("Voting Result ကို အတည်ပြုပေးပါဦး\n(ပြန်ထွက်မယ်ကို နှိပ်ပါက မဲမပေးရသေးပါဟု သတ်မှတ်ပါသည်)".UZawString())
            .setPositiveButton("ကောင်းပြီ".UZawString()) { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
            .setNegativeButton("ပြန်ထွက်မယ်".UZawString()) { dialogInterface, i ->
                dialogInterface.dismiss()
                resetInPref()
                this.finish()
            }
            .setCancelable(false).show()
    }
}