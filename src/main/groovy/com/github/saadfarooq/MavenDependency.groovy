package com.github.saadfarooq

import org.gradle.api.internal.artifacts.dependencies.DefaultExternalModuleDependency

public class MavenDependency extends DefaultExternalModuleDependency {
    MavenDependency(String group, String name, String version) {
        super(group, name.replaceAll('_', '-'), version)
    }
}
