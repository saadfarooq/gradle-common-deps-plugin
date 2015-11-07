[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-gradle--common--deps--plugin-green.svg?style=flat)](https://android-arsenal.com/details/1/2680)

Gradle Common Dependencies Plugin
========================

This plugin provides convenient opt-in/opt-outs for commonly used dependencies. Right now it's targeting Android only.
Hopefully, we'll expand it over time.

Usage
----

1. Add the following to your build.gradle

   ```groovy
   buildscript {
      repositories {
         mavenCentral()
      }

      dependencies {
         classpath 'com.github.saadfarooq:gradle-common-deps-plugin:{version}'
      }
   }

   repositories {
      mavenCentral()
   }

   apply plugin: 'com.android.application'
   apply plugin: 'com.github.saadfarooq.commondeps'
   ```
   alternatively, you can use the new plugin syntax for gradle `2.1+`
   ```groovy
   plugins {
      id "com.github.saadfarooq.commondeps" version "<latestVersion>"
   }
   ```

2. Define the dependencies in the commonDeps closure (Following is the complete listing and will serve as documentation
of dependencies as they are added.

   ```groovy
   // Enable a dependency by either specifying `true` to install the default version 
   // or a version string to include a particular version
   commonDeps {
       support {
           libsVersion '23.1.0`
           support true
           appcompat true
           cardview false
           design false
           palette false
       }
       gps {
            ads true
            analytics true
            base true
            cast true
            games true
            gcm true
            fitness true
            identity true
            maps true
            nearby true
            panorama true
            plus true
       }
       butterknife '7.0.1' // you can also specify true to use latest version
       picasso true
       timber true
       glide true
       testing {
           robolectric true
           junit true
       }
   }
   ``` 

The plugin adds the dependencies to the compile configuration

Contributing
------------

The library is pretty simple and contributing should be relatively easy if you follow the steps below:

1. Think of a dependency you commonly use and that you think is popular enough to merit inclusion

2. Determine appropriate build.gradle closure level to use for said dependency. Most dependencies will go in the 
commonDeps top level closure. Others, such as Google support libraries go in sub-levels. Assume you decided to add `xyz`
dependency to the commonDeps top level closure. (Follow the examples for new closure addition in `CommonDepsPlugin` if 
you need to create new sub-closure)

3. Add a method corresponding to the name you want to use in the build file. For `xyz` add a method named `xyz` that 
takes an argument `version`. This method is called during the build process when the closure is parsed.

4. Create string field corresponding to the property you want to add in the Extension object. My convention is to name 
this the same as the method name / build property name, in our assumption `xyz`.

5. Add the default version for that property to the DefaultVersions file.

6. Simply call the `Util.setDepProperty` with the group name, artifact id, version argument passed to the method and
DefaultVersion reference. e.g. `xyz = Util.setDepProperty('com.xyz', 'xyz', version, DefaultVersion.XYZ)`. This method
add the appropriate dependency to the project depending on the argument passed.

7. Following a test pattern from `CommonDepsPluginTest` to add a test for your newly added dependency.

8. Add example usage in `README.md`. This is important so visitors know what dependencies are available. The gradle
closure properties are not discoverable at this point.

9. Make a PR.

10. Go forth and prosper.

License
-------

    Copyright 2015 Saad Farooq
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
       http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
