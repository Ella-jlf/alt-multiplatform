import com.mellow.alt.buildsrc.ConfigDataAndroid
import com.mellow.alt.buildsrc.Libs

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = ConfigDataAndroid.namespace
    compileSdk = ConfigDataAndroid.compileSdkVersion
    defaultConfig {
        applicationId = ConfigDataAndroid.namespace
        minSdk = ConfigDataAndroid.minSdkVersion
        targetSdk = ConfigDataAndroid.targetSdkVersion
        versionCode = ConfigDataAndroid.versionCode
        versionName = ConfigDataAndroid.versionName
    }
    buildFeatures {
        viewBinding = true
        compose = true
        buildConfig = true
        aidl = false
        renderScript = false
        resValues = true
        shaders = false
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        debug {
            applicationIdSuffix = ".dev"

            buildConfigField("String", "server_url", "\"https://127.0.0.1\"")
            buildConfigField("String", "mock_phone", "\"375000000000\"")
            buildConfigField("String", "mock_code", "\"0000\"")

            isMinifyEnabled = false
        }
        release {
            applicationIdSuffix = ".release"

            buildConfigField("String", "server_url", "\"dev\"")

            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(Libs.Kotlin.stdlib)
    implementation(Libs.Coroutines.android)
    implementation(Libs.Coroutines.core)

    implementation(Libs.AndroidX.coreKtx)
    implementation(Libs.AndroidX.navigation)
    implementation(Libs.AndroidX.Activity.activityCompose)
    implementation(Libs.AndroidX.ConstraintLayout.constraintLayoutCompose)

    implementation(Libs.AndroidX.Compose.runtime)
    implementation(Libs.AndroidX.Compose.foundation)
    implementation(Libs.AndroidX.Compose.layout)
    implementation(Libs.AndroidX.Compose.ui)
    implementation(Libs.AndroidX.Compose.uiUtil)
    implementation(Libs.AndroidX.Compose.material)
    implementation(Libs.AndroidX.Compose.animation)
    implementation(Libs.AndroidX.Compose.iconsExtended)
    implementation(Libs.AndroidX.Compose.tooling)

    implementation(Libs.Coil.coilCompose)

    implementation(Libs.Default.appCompat)
    implementation(Libs.Default.material)
    implementation(Libs.Default.constraintLayout)
    implementation(Libs.Default.flexboxLayout)
    implementation(Libs.Default.glide)
    implementation("androidx.core:core-ktx:+")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
    kapt(Libs.Default.glideKapt)
    implementation(Libs.Default.gson)
    implementation(Libs.Default.loggingInterceptor)
    implementation(Libs.Default.viewBindingDelegate)


    implementation(Libs.KTX.viewmodel)
    implementation(Libs.KTX.activity)
    implementation(Libs.KTX.fragment)
    implementation(Libs.KTX.livedata)

    implementation(Libs.Dagger.dagger)
    implementation(Libs.Dagger.daggerSupport)
    kapt(Libs.Dagger.daggerKapt)
    kapt(Libs.Dagger.daggerSupportKapt)

    implementation(Libs.Network.retrofit)
    implementation(Libs.Network.retrofitGsonConverter)
    implementation(Libs.Network.retrofitRxJava)
}