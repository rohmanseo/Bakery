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
            buildConfigField("String","BASE_URL",Core.DEPLOY_BASE_URL)
            isMinifyEnabled =  false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            buildConfigField("String","BASE_URL",Core.DEBUG_BASE_URL)
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

}