package com.github.saadfarooq

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.DependencyResolutionListener
import org.gradle.api.artifacts.ResolvableDependencies

class CommonDepsPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.extensions.create("commonDeps", CommonDepsExtension)
        GoogleSupportDepsExtension support = project.commonDeps.extensions.create("support", GoogleSupportDepsExtension)
        project.getGradle().addListener(new DependencyResolutionListener() {
            @Override
            void beforeResolve(ResolvableDependencies resolvableDependencies) {
                addDep(project, support)
                project.getGradle().removeListener(this)
            }

            @Override
            void afterResolve(ResolvableDependencies resolvableDependencies) {}
        })
    }

    static def addDep(Project project, GoogleSupportDepsExtension support) {
        support.getProperties().each { prop, val ->
            if (val.getClass().equals(Boolean.class) && val == true) {
                project.logger.info "${prop}, ${val}"
                MavenDependency dependency = new MavenDependency(group: support.DEPS_GROUP, name: prop, version: support.libsVersion)
                project.getConfigurations().getByName("compile").getDependencies().add(project.dependencies.create(dependency.toString()))
            }
        }
    }
}
