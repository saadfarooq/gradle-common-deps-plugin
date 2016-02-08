package com.github.saadfarooq

import com.github.saadfarooq.util.Util

class RxDepsExtension {
    String android
    String location
    String lifecycle
    String permissions

    def android(version) {
        android = Util.setDepProperty("io.reactivex", "rxandroid", version, DefaultVersions.RXANDROID)
    }

    def location(version) {
        location = Util.setDepProperty("pl.charmas.android", "android-reactive-location", version, DefaultVersions.RXLOCATION)
    }

    def lifecycle(version) {
        lifecycle = Util.setDepProperty("com.trello", "rxlifecycler", version, DefaultVersions.RXLIFECYCLE)
    }

    def permissions(version) {
        permissions = Util.setDepProperty("com.tbruyelle.rxpermissions", "rxpermissions", version, DefaultVersions.RXPERMISSIONS)
    }
}
