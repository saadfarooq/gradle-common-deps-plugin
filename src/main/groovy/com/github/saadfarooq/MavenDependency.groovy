package com.github.saadfarooq

import org.gradle.api.artifacts.Dependency

public class MavenDependency implements Dependency {
    def group;
    def name;
    def version;

    @Override
    String getGroup() {
        return group
    }

    @Override
    String getName() {
        return name.replaceAll('_', '-')
    }

    @Override
    String getVersion() {
        return version
    }

    @Override
    boolean contentEquals(Dependency dependency) {
        return dependency.getGroup() == group && dependency.getName() == name && dependency.getVersion() == version
    }

    @Override
    Dependency copy() {
        return new MavenDependency(group, name, version)
    }

    @Override
    String toString() {
        return "${group}:${getName()}:${version}"
    }
}
