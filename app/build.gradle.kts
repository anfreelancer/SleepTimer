import org.gradle.api.JavaVersion.VERSION_1_8

plugins {
    id("com.android.application")
    kotlin("android")
}

val versionMajor = 1
val versionMinor = 1
val versionPatch = 0
val versionBuild = 0

android {
    compileSdk = 30
    defaultConfig {
        applicationId = "com.luteapp.sleeptimer"
        minSdk = 26
        targetSdk = 30
        versionCode = 10
        versionName = "1.5.0"
    }
    buildTypes {
        getByName("release") {
            isUseProguard = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt")
            )
        }
    }
    compileOptions {
        sourceCompatibility = VERSION_1_8
        targetCompatibility = VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = VERSION_1_8.toString()
    }
}
dependencies {
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("com.android.billingclient:billing:4.0.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.0")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
}