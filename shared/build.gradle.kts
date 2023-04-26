import com.mellow.alt.buildsrc.ConfigDataAndroid
import com.mellow.alt.buildsrc.Libs

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization")
}

kotlin {
    android()

    val iosTarget: (String, org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget.() -> Unit) -> org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget =
        when {
            System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
            System.getenv("NATIVE_ARCH")?.startsWith("arm") == true -> ::iosSimulatorArm64
            else -> ::iosX64
        }

    iosTarget("ios") {
        binaries {
            framework {
                baseName = "shared"
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Libs.Network.ktorCore)
                implementation(Libs.Network.ktorLogging)
                implementation(Libs.Network.ktorSerialization)
                implementation(Libs.Serialization.serializer)
                implementation(Libs.Coroutines.core)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(Libs.Network.okHttp)
                implementation(Libs.Coroutines.android)
                implementation(Libs.Network.ktorEngineOkhttp)
            }
        }

        val iosMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-ios:2.2.0")
            }
        }
    }

}

android {
    namespace = ConfigDataAndroid.namespace
    compileSdk = ConfigDataAndroid.compileSdkVersion
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = ConfigDataAndroid.minSdkVersion
        targetSdk = ConfigDataAndroid.targetSdkVersion
    }
}


