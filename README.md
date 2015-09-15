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
         classpath 'com.github.saadfarooq:gradle-common-deps-plugin:0.0.1-SNAPSHOT'
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
      id "com.github.saadfarooq.commondeps" version "0.0.1"
   }
   ```

2. Define the opt-in / opt-out dependencies in the commonDeps closure

   ```groovy
   // The following are the defaults, you only need to add the ones you want to change
   commonDeps {
       support {
           libsVersion '23.0.1`
           support_v4 true
           appcompat_v7 true
           cardview_v7 false
           design false
       }
   }
   ``` 

The plugin adds the dependencies to the compile configuration


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