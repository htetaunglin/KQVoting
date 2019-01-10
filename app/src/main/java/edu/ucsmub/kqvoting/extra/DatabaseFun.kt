package edu.ucsmub.kqvoting.extra

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.util.Log

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import com.kyawhtut.FontUtil
import edu.ucsmub.kqvoting.R
import edu.ucsmub.kqvoting.db.AppDatabase
import edu.ucsmub.kqvoting.db.model.Selection
import me.myatminsoe.mdetect.MDetect
import me.myatminsoe.mdetect.Rabbit
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


fun dataRefresh(context: Context) {
    context.doAsync {
        uiThread { _ ->
            val pd = ProgressDialog(context)
            pd.setMessage("Data ရယူနေပါသည်".UZawString())
            pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
            pd.isIndeterminate = true
            pd.setProgressNumberFormat(null)
            pd.setProgressPercentFormat(null)
            pd.setCancelable(false)
            pd.show()


            val db = FirebaseFirestore.getInstance()
            val collection = db.collection("selection")
            collection.get(Source.SERVER).addOnSuccessListener { querySnapshot ->
                val appDatabase = AppDatabase.getInstance(context)
                doAsync {
                    appDatabase.selectionDao().deleteAllData()
                }

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
                        Log.d("Data is >> ", it.data.toString())
                        Log.d("Data photo is >> ", "${photos[0]} and ${photos[1]}")

                        pd.dismiss()
                    }
                }
            }.addOnFailureListener {
                Log.e("ERROR", it.toString())
                pd.dismiss()
                failDataloading(context)
            }
        }
    }
}

fun String.UZawString(): String {
    val fontUtil = FontUtil()
    return if (MDetect.isUnicode()) {
        if (fontUtil.isZawgyi(this)) {
            Rabbit.zg2uni(this)
        } else {
            this
        }
    } else {
        if (fontUtil.isZawgyi(this)) {
            this
        } else {
            Rabbit.uni2zg(this)
        }
    }
}

private fun failDataloading(context: Context) {
    val text = "အင်တာနက် ကွန်နက်ရှင်ကို စစ်ဆေးပါ".UZawString()
    AlertDialog.Builder(context)
        .setTitle("Error!")
        .setMessage(text)
        .setPositiveButton("Try Again") { dialogInterface, _ ->
            dialogInterface.dismiss()
            dataRefresh(context)
        }.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }
        .setCancelable(false).show()
}