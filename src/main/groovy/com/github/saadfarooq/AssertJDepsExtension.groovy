package com.github.saadfarooq

import com.github.saadfarooq.util.Util

class AssertJDepsExtension {
    final String DEPS_GROUP = 'com.squareup.assertj'
    String libsVersion = DefaultVersions.ASSERTJ_LIBS_VERSION
    String android
    String support
    String appcompat
    String cardview
    String design
    String recyclerview
    String palette

    def android(version) {
        android = Util.setDepProperty(DEPS_GROUP, 'assertj-android', version, libsVersion)
    }
    
    def support(version) {
        support = Util.setDepProperty(DEPS_GROUP, 'assertj-android-support-v4', version, libsVersion)
    }

    def appcompat(version) {
        appcompat = Util.setDepProperty(DEPS_GROUP, 'assertj-android-appcompat-v7', version, libsVersion)
    }

    def mediarouter(version) {
        appcompat = Util.setDepProperty(DEPS_GROUP, 'assertj-android-mediarouter-v7', version, libsVersion)
    }

    def gps(version) {
        appcompat = Util.setDepProperty(DEPS_GROUP, 'assertj-android-play-services-v7', version, libsVersion)
    }

    def gridlayout(version) {
        appcompat = Util.setDepProperty(DEPS_GROUP, 'assertj-android-gridlayout-v7', version, libsVersion)
    }

    def cardview(version) {
        cardview = Util.setDepProperty(DEPS_GROUP, 'assertj-android-cardview-v7', version, libsVersion)
    }

    def recyclerview(version) {
        recyclerview = Util.setDepProperty(DEPS_GROUP, 'recyclerview-v7', version, libsVersion)
    }

    def design(version) {
        design = Util.setDepProperty(DEPS_GROUP, 'assertj-android-design', version, libsVersion)
    }

    def palette(version) {
        palette = Util.setDepProperty(DEPS_GROUP, 'assertj-android-palette-v7', version, libsVersion)
    }
}
