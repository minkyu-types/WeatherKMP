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

include(":composeApp")
include(":composeApp:weather-data")
project(":composeApp:weather-data").projectDir = file("composeApp/weather-data")
include(":composeApp:weather-domain")
project(":composeApp:weather-domain").projectDir = file("composeApp/weather-domain")
include(":composeApp:todo-domain")
project(":composeApp:todo-domain").projectDir = file("composeApp/todo-domain")
include(":composeApp:todo-data")
project(":composeApp:todo-data").projectDir = file("composeApp/todo-data")

include(":composeApp:base-domain")
project(":composeApp:base-domain").projectDir = file("composeApp/base-domain")
