plugins {
    id ("com.android.library")
    id ("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")

}

android {
    compileSdk  = 31

    defaultConfig {
        minSdk = 21
        targetSdk  =31


        testInstrumentationRunner  = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled =  false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility  =JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(Dependencies.DI.koin)
    testImplementation(Dependencies.DI.koin_test_imp)
    implementation(project(":domain"))
    implementation(project(":core"))
    implementation(Dependencies.AndroidX.core)
    implementation(Dependencies.AndroidX.room)
    implementation(Dependencies.AndroidX.room_ktx)
    implementation(Dependencies.Networking.retrofit)
    implementation(Dependencies.Networking.moshi)
    implementation(Dependencies.Networking.moshi_kt)
    implementation(Dependencies.Networking.logging_interceptor)
    kapt(Dependencies.AndroidX.room_compiler)
}