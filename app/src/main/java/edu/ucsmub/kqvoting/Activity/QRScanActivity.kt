package edu.ucsmub.kqvoting.Activity

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import edu.ucsmub.kqvoting.Pref.MyPreference
import edu.ucsmub.kqvoting.R
import edu.ucsmub.kqvoting.extra.UZawString
import kotlinx.android.synthetic.main.activity_scan.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class QRScanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        scanner.setResultHandler {
                LoadFirebase(it.text)

        }
        scanner.startCamera()
    }

    private fun LoadFirebase(mKey: String) {
        doAsync {
            uiThread {
                val pd = ProgressDialog(this@QRScanActivity)
                pd.setMessage("Loading....")
                pd.setCancelable(false)
                pd.show()

                val db = FirebaseFirestore.getInstance()
                val collection = db.collection("key_collection")
                collection.document(mKey).get().addOnSuccessListener {
                    if (it.exists()) {
                        val isVoted = it["isVoted"].toString().toBoolean()
                        if (!isVoted) {
                            val pref = MyPreference(this@QRScanActivity)
                            pref.setQRKey(mKey)
                            startActivity(Intent(this@QRScanActivity, VotingRoomActivity::class.java))
                            pd.dismiss()
                            finish()
                        } else {
                            Toast.makeText(this@QRScanActivity, "သင် မဲပေးပြီးပါပြီ".UZawString(), Toast.LENGTH_SHORT).show()
                            pd.dismiss()
                            finish()
                        }
                    } else {
                        Toast.makeText(this@QRScanActivity, "တစ်စုံတစ်ရာမှားယွင်းနေပါသည်".UZawString(), Toast.LENGTH_SHORT).show()
                        pd.dismiss()
                        finish()
                    }
                }.addOnFailureListener {
                    Toast.makeText(this@QRScanActivity, "တစ်စုံတစ်ရာမှားယွင်းနေပါသည်".UZawString(), Toast.LENGTH_SHORT).show()
                    pd.dismiss()
                    finish()
                }
            }
        }
    }
}