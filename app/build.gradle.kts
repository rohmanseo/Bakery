plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdk = 30

    defaultConfig {
        applicationId = "com.icodeu.bakeryapp"
        minSdk = 21
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures{
        dataBinding = true
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(Dependencies.AndroidX.core)
    implementation(Dependencies.AndroidX.appCompat)
    implementation(Dependencies.AndroidX.constrain_layout)

    implementation(Dependencies.AndroidX.livedata)
    implementation(Dependencies.AndroidX.viewmodel)
    implementation(Dependencies.AndroidX.runtime)

    implementation(Dependencies.AndroidX.navigation)
    implementation(Dependencies.AndroidX.navigation_dynamic_support)
    implementation(Dependencies.AndroidX.navigation_ui)
    implementation(Dependencies.Material.legacy_support)
    implementation(Dependencies.Material.material)

    implementation(Dependencies.AndroidX.glide)
    kapt(Dependencies.AndroidX.glide_annotation)
    implementation(Dependencies.CustomView.carousel_view)

    implementation(Dependencies.DI.koin)
    testImplementation(Dependencies.DI.koin_test_imp)

    testImplementation(Dependencies.Test.jUnit)
    androidTestImplementation(Dependencies.Test.jUnitExt)
    androidTestImplementation(Dependencies.Test.espresso)
}