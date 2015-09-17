package com.github.saadfarooq

import com.neenbedankt.gradle.androidapt.AndroidAptPlugin
import me.tatarka.RetrolambdaPluginAndroid
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
        logger = project.logger
        compileDeps = project.getConfigurations().getByName("compile").getDependencies()

        project.afterEvaluate {
            project.apply plugin: RetrolambdaPluginAndroid
        }

        project.getGradle().addListener(new DependencyResolutionListener() {
            @Override
            void beforeResolve(ResolvableDependencies resolvableDependencies) {
                addDep(support)
                addOthers(commonDeps)
                project.getGradle().removeListener(this)
//                addDaggerDep(commonDeps)
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
    }

    def addOthers(CommonDepsExtension common) {
        if (common.butterknife instanceof Boolean && common.butterknife == true) {
            compileDeps.add(new MavenDependency('com.jakewharton', 'butterknife', '7.0.1'))
        } else if (common.butterknife instanceof String) {
            compileDeps.add(new MavenDependency('com.jakewharton', 'butterknife', (String) common.butterknife))
        }

        if (common.picasso instanceof Boolean && common.picasso == true) {
            compileDeps.add(new MavenDependency('com.squareup.picasso', 'picasso', '2.5.2'))
        } else if (common.picasso instanceof String) {
            compileDeps.add(new MavenDependency('com.squareup.picasso', 'picasso', (String) common.picasso))
        }
    }

    def addRetroLambda(CommonDepsExtension common) {
        if (common.retrolambda) {
//            project.getPluginManager().apply(RetrolambdaPlugin.class)
            project.apply plugin: RetrolambdaPluginAndroid
        }
    }
}
