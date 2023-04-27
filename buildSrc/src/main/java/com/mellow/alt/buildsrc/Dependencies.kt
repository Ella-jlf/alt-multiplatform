package com.mellow.alt.buildsrc

object ConfigDataAndroid {
    const val namespace = "com.mellow.alt"
    const val compileSdkVersion = 33
    const val buildToolsVersion = "30.0.3"
    const val minSdkVersion = 23
    const val targetSdkVersion = 33
    const val versionCode = 1
    const val versionName = "naked"
}

object Libs {
    const val androidGradlePlugin = "com.android.tools.build:gradle:7.2.2"

    object Realm {
        const val realmGradlePlugin = "io.realm:realm-gradle-plugin:10.7.0"
    }

    object Accompanist {
        const val version = "0.21.3-beta"
        const val insets = "com.google.accompanist:accompanist-insets:$version"
    }

    object Kotlin {
        private const val version = "1.7.20"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"
    }

    object Coroutines {
        private const val version = "1.5.2"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object JUnit {
        private const val version = "4.13"
        const val junit = "junit:junit:$version"
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:1.7.0"
        const val navigation = "androidx.navigation:navigation-compose:2.4.0-beta02"

        object Activity {
            const val activityCompose = "androidx.activity:activity-compose:1.4.0"
        }

        object Compose {
            const val snapshot = ""
            const val version = "1.3.1"

            const val animation = "androidx.compose.animation:animation:$version"
            const val foundation = "androidx.compose.foundation:foundation:$version"
            const val layout = "androidx.compose.foundation:foundation-layout:$version"
            const val iconsExtended = "androidx.compose.material:material-icons-extended:$version"
            const val material = "androidx.compose.material:material:$version"
            const val tooling = "androidx.compose.ui:ui-tooling:$version"
            const val ui = "androidx.compose.ui:ui:$version"
            const val uiUtil = "androidx.compose.ui:ui-util:$version"
            const val uiTest = "androidx.compose.ui:ui-test-junit4:$version"
            const val uiTestManifest = "androidx.compose.ui:ui-test-manifest:$version"
            const val runtime = "androidx.compose.runtime:runtime:$version"
            const val runtimeLiveData = "androidx.compose.runtime:runtime-livedata:$version"
        }

        object ConstraintLayout {
            const val constraintLayoutCompose =
                "androidx.constraintlayout:constraintlayout-compose:1.0.0-rc01"
        }

        object Test {
            private const val version = "1.4.0"
            const val core = "androidx.test:core:$version"
            const val rules = "androidx.test:rules:$version"

            object Ext {
                private const val version = "1.1.2"
                const val junit = "androidx.test.ext:junit-ktx:$version"
            }

            const val espressoCore = "androidx.test.espresso:espresso-core:3.3.0"
        }
    }

    object Coil {
        const val coilCompose = "io.coil-kt:coil-compose:2.2.2"
    }

    object Default {
        const val appCompat = "androidx.appcompat:appcompat:1.4.1"
        const val material = "com.google.android.material:material:1.6.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.3"
        const val flexboxLayout = "com.google.android.flexbox:flexbox:3.0.0"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:4.9.0"

        private const val glideVersion = "4.12.0"
        const val glide = "com.github.bumptech.glide:glide:$glideVersion"
        const val glideKapt = "com.github.bumptech.glide:compiler:$glideVersion"

        const val viewBindingDelegate =
            "com.github.kirich1409:viewbindingpropertydelegate-noreflection:1.5.0-beta01"
        const val gson = "com.google.code.gson:gson:2.8.8"
    }

    object KTX {
        const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1"
        const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:2.5.0-beta01"
        const val fragment = "androidx.fragment:fragment-ktx:1.3.0"
        const val activity = "androidx.activity:activity-ktx:1.3.0"
    }

    object Dagger {
        private const val daggerVersion = "2.42"
        const val dagger = "com.google.dagger:dagger:$daggerVersion"
        const val daggerKapt = "com.google.dagger:dagger-compiler:$daggerVersion"

        private const val daggerSupportVersion = "2.27"
        const val daggerSupport = "com.google.dagger:dagger-android-support:$daggerSupportVersion"
        const val daggerSupportKapt =
            "com.google.dagger:dagger-android-processor:$daggerSupportVersion"
    }

    object Network {
        private const val retrofitVersion = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
        const val retrofitRxJava = "com.squareup.retrofit2:adapter-rxjava3:$retrofitVersion"

        private const val ktorVersion = "1.6.4"
        const val ktorCore = "io.ktor:ktor-client-core:$ktorVersion"
        const val ktorSerialization = "io.ktor:ktor-client-serialization:$ktorVersion"
        const val ktorLogging = "io.ktor:ktor-client-logging:$ktorVersion"
        const val ktorEngineOkhttp = "io.ktor:ktor-client-okhttp:$ktorVersion"

        private const val okHttpVersion = "4.10.0"
        const val okHttp = "com.squareup.okhttp3:okhttp:$okHttpVersion"

    }

    object Serialization {
        private const val serializerVersion = "1.2.2"
        const val serializer = "org.jetbrains.kotlinx:kotlinx-serialization-json:$serializerVersion"
    }
}
