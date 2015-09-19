package com.github.saadfarooq

class TestDepsExtension {
    String robolectric
    String junit

    def robolectric(version) {
        if (version != false) {
            def ver = version instanceof Boolean ? DefaultVersions.ROBOLECTRIC : version
            this.robolectric = "org.robolectric:robolectric:${ver}"
        }

    }

    def junit(version) {
        if (version != false) {
            def ver = version instanceof Boolean ? DefaultVersions.JUNIT : version
            this.junit = "junit:junit:${ver}"
        }
    }
}
