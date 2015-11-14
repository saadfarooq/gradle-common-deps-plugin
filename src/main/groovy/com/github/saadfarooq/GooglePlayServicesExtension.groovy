package com.github.saadfarooq

import com.github.saadfarooq.util.Util

class GooglePlayServicesExtension {
    final String DEPS_GROUP = 'com.google.android.gms'
    String libsVersion = DefaultVersions.PLAY_SERVICES_LIBS_VERSION
    String ads
    String analytics
    String base
    String cast
    String games
    String gcm
    String fitness
    String identity
    String location
    String maps
    String nearby
    String panorama
    String plus

    def ads(version) {
        ads = Util.setDepProperty(DEPS_GROUP, 'play-services-ads', version, libsVersion)
    }

    def analytics(version) {
        analytics = Util.setDepProperty(DEPS_GROUP, 'play-services-analytics', version, libsVersion)
    }

    def base(version) {
        base = Util.setDepProperty(DEPS_GROUP, 'play-services-base', version, libsVersion)
    }

    def cast(version) {
        cast = Util.setDepProperty(DEPS_GROUP, 'play-services-cast', version, libsVersion)
    }

    def games(version) {
        games = Util.setDepProperty(DEPS_GROUP, 'play-services-games', version, libsVersion)
    }

    def gcm(version) {
        gcm = Util.setDepProperty(DEPS_GROUP, 'play-services-gcm', version, libsVersion)
    }

    def fitness(version) {
        fitness = Util.setDepProperty(DEPS_GROUP, 'play-services-fitness', version, libsVersion)
    }

    def identity(version) {
        identity = Util.setDepProperty(DEPS_GROUP, 'play-services-identity', version, libsVersion)
    }
    
    def location(version) {
        location = Util.setDepProperty(DEPS_GROUP, 'play-services-location', version, libsVersion)
    }

    def maps(version) {
        maps = Util.setDepProperty(DEPS_GROUP, 'play-services-maps', version, libsVersion)
    }

    def nearby(version) {
        nearby = Util.setDepProperty(DEPS_GROUP, 'play-services-nearby', version, libsVersion)
    }

    def panorama(version) {
        panorama = Util.setDepProperty(DEPS_GROUP, 'play-services-panorama', version, libsVersion)
    }

    def plus(version) {
        plus = Util.setDepProperty(DEPS_GROUP, 'play-services-plus', version, libsVersion)
    }
}
