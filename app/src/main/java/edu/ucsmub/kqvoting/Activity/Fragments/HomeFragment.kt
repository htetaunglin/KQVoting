package edu.ucsmub.kqvoting.Activity.Fragments

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import edu.ucsmub.kqvoting.R
import kotlinx.android.synthetic.main.fragment_home.view.*
import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import edu.ucsmub.kqvoting.extra.Ratio
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import edu.ucsmub.kqvoting.Activity.QRScanActivity
import edu.ucsmub.kqvoting.extra.dateToMilliSec
import kotlinx.android.synthetic.main.countdownlayout.view.*
import kotlinx.android.synthetic.main.votinglayout.view.*
import java.util.concurrent.TimeUnit

import edu.ucsmub.kqvoting.extra.UZawString
import java.util.*


class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val ratio = Ratio(view.context)
        ratio.calculateSize(view.imageView, view.context as Activity, 720, 480)
        countTime(view)
        view.qrCodeLayout.setOnClickListener {
            checkVotingPermission(view)
        }
        return view
    }

    private fun checkVotingPermission(view: View) {
        with(view) {
            val db = FirebaseFirestore.getInstance()
            val controller = db.collection("controller")
            controller.document("votingOnOff").get(Source.SERVER).addOnSuccessListener {
                val isOn = it!!.data!!["onoff"].toString().toBoolean()
                if (isOn) {
                    checkPermission()
                } else {
                    Toast.makeText(context, "မဲပေးလို့ မရသေးပါ".UZawString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getCurrentLength(): Long =
        dateToMilliSec("2019-01-09 06-00-00", "yyyy-MM-dd HH-mm-ss") - System.currentTimeMillis()

    private fun countTime(view: View) {
        val countDownTimer = object : CountDownTimer(getCurrentLength(), 1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {

                val day = TimeUnit.DAYS.toDays(TimeUnit.MILLISECONDS.toDays(millisUntilFinished))

                val hour = TimeUnit.MILLISECONDS.toHours(millisUntilFinished) - TimeUnit.DAYS.toHours(
                    TimeUnit.MILLISECONDS.toDays(millisUntilFinished)
                )
                val minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                    TimeUnit.MILLISECONDS.toHours(millisUntilFinished)
                )
                val second = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
                )

                view.txtday.text = day.fixNumber()
                view.txthour.text = hour.fixNumber()
                view.txtminute.text = minutes.fixNumber()
                view.txtsec.text = second.fixNumber()
            }

            override fun onFinish() {
                view.timer.visibility = View.GONE
                view.votebtn.visibility = View.VISIBLE
            }
        }
        countDownTimer.start()
    }


    fun Long.fixNumber(): String {
        var str = this.toString()
        if (this < 10) {
            str = "0$str"
        }

        return str
    }


    private fun checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context!!.checkCallingOrSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.CAMERA), MY_PERMISSIONS_REQUEST_ACCESS_CAMERA)
            } else {
                startActivity(Intent(context, QRScanActivity::class.java))
            }
        } else {
            startActivity(Intent(context, QRScanActivity::class.java))
        }
    }


    val MY_PERMISSIONS_REQUEST_ACCESS_CAMERA = 1

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_ACCESS_CAMERA -> {
                if (!grantResults.isEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    startActivity(Intent(context, QRScanActivity::class.java))
                else
                    Toast.makeText(context, "Camera Permission မရရှိပါ".UZawString(), Toast.LENGTH_SHORT).show()
                return
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


}
