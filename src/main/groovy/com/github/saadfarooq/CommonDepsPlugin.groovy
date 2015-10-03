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
        TestDepsExtension testing = project.commonDeps.extensions.create('testing', TestDepsExtension)
        logger = project.logger
        compileDeps = project.getConfigurations().getByName("compile").getDependencies()
        project.getGradle().addListener(new DependencyResolutionListener() {
            @Override
            void beforeResolve(ResolvableDependencies resolvableDependencies) {
                addDep(support)
                addDep(commonDeps)
                addTestDeps(testing)
                project.getGradle().removeListener(this)
//                addDaggerDep(commonDeps)
            }

            @Override
            void afterResolve(ResolvableDependencies resolvableDependencies) {}
        })
    }

    def addDep(support) {
        support.getProperties().each { prop, val ->
            if (val.getClass().equals(String.class) && val ==~/.*:*:.*/) {
                logger.info "${prop}, ${val}"
                compileDeps.add(val)
            }
        }
    }

//    def addOthers(CommonDepsExtension common) {
//        common.getProperties().each { prop, val ->
//            if (val.getClass().equals(String.class) && val ==~/.*:*:.*/) {
//                logger.info "${prop}, ${val}"
//                compileDeps.add(val)
//            }
//        }
//    }

    /*def addRetroLambda(CommonDepsExtension common) {
        if (common.retrolambda) {
//            project.getPluginManager().apply(RetrolambdaPlugin.class)
//            project.apply plugin: RetrolambdaPluginAndroid
        }
    }*/

    def addTestDeps(TestDepsExtension testing) {
        def testDeps = project.getConfigurations().getByName("testCompile").getDependencies()
        testing.getProperties().each { prop, value ->
            if (value instanceof String && !value.isEmpty()) {
                testDeps.add(value)
            }
        }
    }

    /*def addDaggerDep(CommonDepsExtension common) {
        if (common.dagger == 1) {
            compileDeps.add(new MavenDependency('com.squareup.dagger', 'dagger', '1.2.2'))
            project.getConfigurations().getByName("provided").getDependencies().add(new MavenDependency('com.squareup.dagger', 'dagger-compiler', '1.2.2'))
        } else if (common.dagger == 2) {
            project.apply plugin: AndroidAptPlugin
            project.getConfigurations().getByName('provided').getDependencies().add(new MavenDependency('org.glassfish', 'javax.annotation', '10.0-b28'))
            project.getConfigurations().getByName("apt").getDependencies().add(new MavenDependency('com.google.dagger', 'dagger-compiler', '2.0.1'))
            compileDeps.add(new MavenDependency('com.google.dagger', 'dagger', '2.0.1'))
        } else if (common.dagger instanceof String) {
            project.apply plugin: AndroidAptPlugin
            project.getConfigurations().getByName("apt").getDependencies().add(new MavenDependency('com.google.dagger', 'dagger-compiler', (String) common.dagger))
            project.getConfigurations().getByName('provided').getDependencies().add(new MavenDependency('org.glassfish', 'javax.annotation', '10.0-b28'))
            compileDeps.add(new MavenDependency('com.google.dagger', 'dagger', (String) common.dagger))
        }
    }*/
}
