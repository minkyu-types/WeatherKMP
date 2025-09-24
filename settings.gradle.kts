rootProject.name = "KmpSampleApp20250825"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {
        kotlin("jvm") version "2.0.21"
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        gradlePluginPortal()
    }
}

include(":shared")
include(":shared:weather-data")
project(":shared:weather-data").projectDir = file("shared/weather-data")
include(":shared:weather-domain")
project(":shared:weather-domain").projectDir = file("shared/weather-domain")
include(":shared:todo-domain")
project(":shared:todo-domain").projectDir = file("shared/todo-domain")
include(":shared:todo-data")
project(":shared:todo-data").projectDir = file("shared/todo-data")

include(":shared:base-domain")
project(":shared:base-domain").projectDir = file("shared/base-domain")
include(":androidapp")
