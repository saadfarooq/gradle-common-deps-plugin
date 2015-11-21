package com.github.saadfarooq

import com.github.saadfarooq.util.Util

class TestDepsExtension {
    String robolectric
    String junit
    String mockito
    String assertj

    def robolectric(version) {
        robolectric = Util.setDepProperty('org.robolectric', 'robolectric', version, DefaultVersions.ROBOLECTRIC)
    }

    def junit(version) {
        junit = Util.setDepProperty("junit", "junit", version, DefaultVersions.JUNIT)
    }

    def mockito(version) {
        mockito = Util.setDepProperty("org.mockito", "mockito-core", version, DefaultVersions.MOCKITO)
    }
}
