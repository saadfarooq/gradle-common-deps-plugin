package com.github.saadfarooq

import com.github.saadfarooq.util.Util

class CommonDepsExtension {
    String butterknife
    String picasso
    String timber
    String glide
    String rxandroid
    String jodatime

    def butterknife(version) {
        butterknife = Util.setDepProperty('com.jakewharton', 'butterknife', version, DefaultVersions.BUTTERKNIFE)
    }

    def picasso(version) {
        picasso = Util.setDepProperty('com.squareup.picasso', 'picasso', version, DefaultVersions.PICASSO)
    }

    def timber(version) {
        timber = Util.setDepProperty('com.jakewharton.timber', 'timber', version, DefaultVersions.TIMBER)
    }

    def glide(version) {
        glide = Util.setDepProperty('com.github.bumptech.glide', 'glide', version, DefaultVersions.GLIDE)
    }

    def rxandroid(version) {
        rxandroid = Util.setDepProperty('io.reactivex', 'rxandroid', version, DefaultVersions.RXANDROID)
    }

    def jodatime(version) {
        jodatime = Util.setDepProperty('net.danlew', 'android.joda', version, DefaultVersions.JODA_TIME_ANDROID)
    }
}
