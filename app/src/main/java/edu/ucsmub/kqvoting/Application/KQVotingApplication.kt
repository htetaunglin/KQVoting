package edu.ucsmub.kqvoting.Application

import android.app.Application
import me.myatminsoe.mdetect.MDetect

class KQVotingApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        MDetect.init(this)
    }
}