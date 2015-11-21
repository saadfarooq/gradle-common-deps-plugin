package com.github.saadfarooq

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.DependencyResolutionListener
import org.gradle.api.artifacts.ResolvableDependencies

class CommonDepsPlugin implements Plugin<Project> {
    def compileDeps
    def logger
    Project project

    @Override
    void apply(Project project) {
        this.project = project
        CommonDepsExtension commonDeps = project.extensions.create("commonDeps", CommonDepsExtension)
        GoogleSupportDepsExtension support = project.commonDeps.extensions.create("support", GoogleSupportDepsExtension)
        GooglePlayServicesExtension gps = project.commonDeps.extensions.create("gps", GooglePlayServicesExtension)
        RxDepsExtension rx = project.commonDeps.extensions.create('rx', RxDepsExtension)
//        RxBindingDepsExtension rxBinding = rx.extensions.create('binding', RxBindingDepsExtension)

        TestDepsExtension testing = project.commonDeps.extensions.create('testing', TestDepsExtension)
//        AssertJDepsExtension assertj = testing.extensions.create('assertj', AssertJDepsExtension)

        logger = project.logger
        compileDeps = project.getConfigurations().getByName("compile").getDependencies()
        project.getGradle().addListener(new DependencyResolutionListener() {
            @Override
            void beforeResolve(ResolvableDependencies resolvableDependencies) {
                addDep(support)
                addDep(commonDeps)
                addDep(gps)
                addDep(rx)
//                addDep(rxBinding)
                addTestDeps(testing)
//                addTestDeps(assertj)
                project.getGradle().removeListener(this)
            }

            @Override
            void afterResolve(ResolvableDependencies resolvableDependencies) {}
        })
    }

    def addDep(support) {
        support.getProperties().each { prop, val ->
            if (val.getClass().equals(String.class) && val ==~/.*:*:.*/) {
                logger.info "${prop}, ${val}"
                compileDeps.add(project.getDependencies().create(val))
            }
        }
    }

    def addTestDeps(testing) {
        def testDeps = project.getConfigurations().getByName("testCompile").getDependencies()
        testing.getProperties().each { prop, value ->
            if (value instanceof String && !value.isEmpty()) {
                testDeps.add(project.getDependencies().create(value))
            }
        }
    }
}
