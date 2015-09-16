package com.github.saadfarooq

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

public class CommonDepsPluginTest {
    @Test
    public void withNoClosure_shouldAddDefaultDependencies() {
        def project = createProject()
        project.evaluate()
        def deps = project.getConfigurations().getByName('compile').getDependencies()
        assert deps.size() == 2
        assert deps.contains(new MavenDependency('com.android.support', 'appcompat-v7', '23.0.1'))
        assert deps.contains(new MavenDependency('com.android.support', 'support-v4', '23.0.1'))
    }

    @Test
    public void withClosure_shouldEnableClosureDependencies() {
        def project = createProject()
        project.commonDeps {
            support {
                design true
                cardview_v7 true
            }
        }
        project.evaluate()
        def deps = project.getConfigurations().getByName('compile').getDependencies()
        assert deps.size() == 4
        assert deps.contains(new MavenDependency('com.android.support', 'cardview-v7', '23.0.1'))
        assert deps.contains(new MavenDependency('com.android.support', 'design', '23.0.1'))
    }

    @Test
    public void withClosure_shouldDisableDefaultDependencies() {
        def project = createProject()
        project.commonDeps {
            support {
                support_v4 false
                appcompat_v7 false
            }
        }
        project.evaluate()
        def deps = project.getConfigurations().getByName('compile').getDependencies()
        assert deps.size() == 0
    }

    def createProject() {
        Project project = ProjectBuilder.builder().build()
        project.getPluginManager().apply 'java'
        project.getPluginManager().apply 'com.github.saadfarooq.commondeps'

        project.repositories {
            jcenter()
            def androidHome = System.getenv("ANDROID_HOME")
            maven {
                url "$androidHome/extras/android/m2repository/"
            }
        }
        return project
    }

}