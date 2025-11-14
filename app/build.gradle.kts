plugins {
    // This plugin is used for building Android applications.
    alias(libs.plugins.android.application)
    // This plugin provides Kotlin support for Android.
    alias(libs.plugins.kotlin.android)
    // This plugin is used for processing annotations in Kotlin. It is used here for Room.
    alias(libs.plugins.ksp)
    // This plugin provides a type-safe way to navigate between fragments in your app.
    alias(libs.plugins.androidx.navigation.safeargs.kotlin)
    // This plugin is also used for annotation processing in Kotlin, and can provide more detailed error reporting.
    id("kotlin-kapt")
}

android {
    namespace = "com.example.aurasense"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.aurasense"
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures{
        dataBinding = true
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.service)

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)

    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.androidx.recyclerview)

    implementation(libs.play.services.location)

    implementation(libs.androidx.security.crypto)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
    implementation("io.coil-kt:coil:2.4.0")
    implementation("com.squareup.picasso:picasso:2.71828")


    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.androidx.core.testing)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}