import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinxSerialization)
}

val abcNotifications = "com.linecorp.abc:kmm-notifications:0.4.1"

android {
    namespace = "org.dosys.shared"
    compileSdk = 35
    defaultConfig {
        minSdk = 29
    }
}

kotlin {
    val xcf = XCFramework()

    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            isStatic = true
            baseName = "shared"
            export(libs.lifecycle.viewmodel)
            xcf.add(this)
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(project(":shared:todo-data"))
            implementation(project(":shared:weather-data"))
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.orbit.compose)
            implementation(libs.ktor.okhttp)
            implementation(libs.koin.android)
            implementation(libs.androidx.workmanager)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.json)
            api(abcNotifications)
        }
        commonMain.dependencies {
            implementation(project(":shared:todo-domain"))
            implementation(project(":shared:weather-domain"))

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(compose.materialIconsExtended)
            implementation(libs.orbit.core)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.androidx.paging3.common)
            implementation(libs.androidx.paging3.compose)
            implementation(libs.androidx.paging3.runtime)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.datetime)
            implementation(libs.koin.core)
            implementation(libs.koin.annotations)
            implementation(libs.androidx.room.runtime)
            implementation(libs.jetpack.navigation)
            implementation(libs.ktorfit)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.encoding)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.lifecycle.viewmodel)
            implementation(libs.coil.compose)
            implementation(libs.coil.network)
            implementation(libs.log.kermit)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.koin.test)
        }
        iosMain.dependencies {
            implementation(project(":shared:todo-data"))
            implementation(project(":shared:weather-data"))

            implementation(libs.ktor.darwin)
        }
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}
