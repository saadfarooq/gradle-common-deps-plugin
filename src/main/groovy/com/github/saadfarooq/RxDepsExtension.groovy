package com.github.saadfarooq

import com.github.saadfarooq.util.Util
import lombok.ast.Default

class RxDepsExtension {
    String android
    String location
    String lifecycle

    def android(version) {
        android = Util.setDepProperty("io.reactivex", "rxandroid", version, DefaultVersions.RXANDROID)
    }

    def location(version) {
        location = Util.setDepProperty("pl.charmas.android", "android-reactive-location", version, DefaultVersions.RXLOCATION)
    }

    def lifecycle(version) {
        lifecycle = Util.setDepProperty("com.trello", "rxlifecycler", version, DefaultVersions.RXLIFECYCLE)
    }

}
