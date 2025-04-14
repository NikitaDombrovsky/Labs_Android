plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.network_okhttp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.network_okhttp"
        minSdk = 26
        targetSdk = 35
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
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("com.squareup.okhttp3:okhttp:4.12.0")

    implementation("com.google.code.gson:gson:2.12.1")
    implementation("com.squareup.moshi:moshi:1.8.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")

    implementation("com.squareup.okhttp3:mockwebserver:5.0.0-alpha.14")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.12.0")
    implementation("com.squareup.okhttp3:okhttp-tls:5.0.0-alpha.14")
}