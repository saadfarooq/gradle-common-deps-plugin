package com.github.saadfarooq

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.DependencyResolutionListener
import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.artifacts.ResolvableDependencies
import org.gradle.api.internal.artifacts.dependencies.DefaultExternalModuleDependency

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
                project.getConfigurations().getByName("compile").getDependencies()
                        .add(new MavenDependency(support.DEPS_GROUP, prop as String, support.libsVersion))
            }
        }
    }
}
