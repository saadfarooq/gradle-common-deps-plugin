package com.github.saadfarooq

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.DependencyResolutionListener
import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.artifacts.ResolvableDependencies
import org.gradle.api.internal.artifacts.dependencies.DefaultExternalModuleDependency

class CommonDepsPlugin implements Plugin<Project> {
    def compileDeps
    def logger

    @Override
    void apply(Project project) {
        CommonDepsExtension commonDeps = project.extensions.create("commonDeps", CommonDepsExtension)
        GoogleSupportDepsExtension support = project.commonDeps.extensions.create("support", GoogleSupportDepsExtension)
        logger = project.logger
        compileDeps = project.getConfigurations().getByName("compile").getDependencies()
        project.getGradle().addListener(new DependencyResolutionListener() {
            @Override
            void beforeResolve(ResolvableDependencies resolvableDependencies) {
                addDep(support)
                addDaggerDep(commonDeps)
                addOthers(commonDeps)
                project.getGradle().removeListener(this)
            }

            @Override
            void afterResolve(ResolvableDependencies resolvableDependencies) {}
        })
    }

    def addDep(GoogleSupportDepsExtension support) {
        support.getProperties().each { prop, val ->
            if (val.getClass().equals(Boolean.class) && val == true) {
                logger.info "${prop}, ${val}"
                compileDeps.add(new MavenDependency(support.DEPS_GROUP, prop as String, support.libsVersion))
            }
        }
    }

    def addDaggerDep(CommonDepsExtension common) {
        if (common.dagger == 1) {
            compileDeps.add(new MavenDependency('com.squareup.dagger', 'dagger', '1.2.2'))
        } else  if (common.dagger == 2) {
            compileDeps.add(new MavenDependency('com.google.dagger', 'dagger', '2.0.1'))
        } else if (common.dagger instanceof String) {
            compileDeps.add(new MavenDependency('com.google.dagger', 'dagger', (String) common.dagger))
        }
    }


    def addOthers(CommonDepsExtension common) {
        if (common.butterknife instanceof Boolean && common.butterknife == true) {
            compileDeps.add(new MavenDependency('com.jakewharton', 'butterknife', '7.0.1'))
        } else if (common.butterknife instanceof String) {
            compileDeps.add(new MavenDependency('com.jakewharton', 'butterknife', (String) common.butterknife))
        }
    }
}
