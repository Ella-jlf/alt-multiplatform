import com.mellow.alt.buildsrc.Libs

plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("7.3.1").apply(false)
    id("com.android.library").version("7.3.1").apply(false)
    kotlin("android").version("1.7.10").apply(false)
    kotlin("multiplatform").version("1.7.10").apply(false)
    kotlin("kapt").version("1.7.20").apply(false)
    kotlin("plugin.serialization").version("1.5.0").apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}