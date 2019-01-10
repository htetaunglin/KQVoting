package edu.ucsmub.kqvoting.customUI

import android.view.animation.Interpolator

class MyBounceInterpolator: Interpolator {

    private var mAmplitude = 1.0
    private var mFrequency = 10.0

    constructor(amplitude: Double, frequency: Double) {
        mAmplitude = amplitude
        mFrequency = frequency
    }

    override fun getInterpolation(time: Float): Float {
        return (-1.0 * Math.pow(Math.E, -time / mAmplitude) * Math.cos(mFrequency * time) + 1).toFloat()
    }
}