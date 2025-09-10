plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
    sourceSets {
        jvm()
        iosX64()
        iosArm64()
        iosSimulatorArm64()

        val commonMain by getting {
            dependencies {
                implementation(libs.kotlinx.coroutines.core)
            }
        }
    }
}