package com.github.saadfarooq

import com.github.saadfarooq.util.Util

class CommonDepsExtension {
    String butterknife
    String picasso
    String timber

    def butterknife(version) {
        butterknife = Util.setDepProperty('com.jakewharton', 'butterknife', version, DefaultVersions.BUTTERKNIFE)
    }

    def picasso(version) {
        picasso = Util.setDepProperty('com.squareup.picasso', 'picasso', version, DefaultVersions.PICASSO)
    }

    def timber(version) {
        timber = Util.setDepProperty('com.jakewharton.timber', 'timber', version, DefaultVersions.TIMBER)
    }
}
