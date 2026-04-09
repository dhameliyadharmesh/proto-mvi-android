
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.proto.mvi"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.proto.mvi"
        minSdk = 24
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.foundation.layout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    //noinspection UseTomlInstead,NewerVersionAvailable
    testImplementation("app.cash.turbine:turbine:1.1.0")  // For testing Flows
    //noinspection UseTomlInstead,NewerVersionAvailable
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.1")
    //noinspection UseTomlInstead
    testImplementation("androidx.arch.core:core-testing:2.2.0") // InstantTaskExecutorR

//    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.6")
//    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")
//    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.6")

    // Hilt
    //noinspection NewerVersionAvailable,UseTomlInstead
    implementation("com.google.dagger:hilt-android:2.51")
    //noinspection UseTomlInstead,NewerVersionAvailable
    ksp("com.google.dagger:hilt-compiler:2.51")

    // Retrofit
    //noinspection UseTomlInstead,NewerVersionAvailable
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    //noinspection NewerVersionAvailable,UseTomlInstead
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
//
//
//    // Hilt ViewModel extension
    implementation(libs.androidx.hilt.navigation.compose)

    // For Preferences DataStore
    implementation(libs.androidx.datastore.preferences)

    // Coil for image loading
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

}