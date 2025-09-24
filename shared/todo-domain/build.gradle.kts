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
                api(project(":shared:base-domain"))

                implementation(libs.koin.core)
                implementation(libs.koin.annotations)
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.androidx.paging3.common)
            }
        }
    }
}