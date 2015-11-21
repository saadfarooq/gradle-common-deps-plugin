package com.github.saadfarooq

import com.github.saadfarooq.util.Util

class RxBindingDepsExtension {
    final String DEPS_GROUP = 'com.jakewharton.rxbinding'
    String libsVersion = DefaultVersions.RXBINDING_VERSION
    String support
    String appcompat
    String design
    String recyclerview
    String leanback
    
    def support(version) {
        support = Util.setDepProperty(DEPS_GROUP, 'support-v4', version, libsVersion)
    }

    def appcompat(version) {
        appcompat = Util.setDepProperty(DEPS_GROUP, 'appcompat-v7', version, libsVersion)
    }

    def cardview(version) {
        cardview = Util.setDepProperty(DEPS_GROUP, 'cardview-v7', version, libsVersion)
    }

    def recyclerview(version) {
        recyclerview = Util.setDepProperty(DEPS_GROUP, 'recyclerview-v7', version, libsVersion)
    }

    def design(version) {
        design = Util.setDepProperty(DEPS_GROUP, 'design', version, libsVersion)
    }

    def leanback(version) {
        leanback = Util.setDepProperty(DEPS_GROUP, 'leanback-v17', version, libsVersion)
    }
}
