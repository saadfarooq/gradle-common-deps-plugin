package com.github.saadfarooq

import com.github.saadfarooq.util.Util

class GoogleSupportDepsExtension {
    final String DEPS_GROUP = 'com.android.support'
    String libsVersion = DefaultVersions.SUPPORT_LIBS_VERSION
    String support
    String appcompat
    String cardview
    String design
    String recyclerview
    
    def support(version) {
        support = Util.setDepProperty(DEPS_GROUP, 'support_v4', version, libsVersion)
    }

    def appcompat(version) {
        appcompat = Util.setDepProperty(DEPS_GROUP, 'appcompat_v7', version, libsVersion)
    }

    def cardview(version) {
        cardview = Util.setDepProperty(DEPS_GROUP, 'cardview_v7', version, libsVersion)
    }

    def recyclerview(version) {
        recyclerview = Util.setDepProperty(DEPS_GROUP, 'recyclerview_v7', version, libsVersion)
    }

    def design(version) {
        design = Util.setDepProperty(DEPS_GROUP, 'design', version, libsVersion)
    }
}
