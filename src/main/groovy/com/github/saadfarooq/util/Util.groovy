package com.github.saadfarooq.util

class Util {
    def static setDepProperty(String group, String pckg, version, String defaultVersion) {
        if (version != false) {
            def ver = version instanceof Boolean ? defaultVersion : version
            return "${group}:${pckg}:${ver}"
        }
    }
}
