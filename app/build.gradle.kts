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
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.test:runner:1.4.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.0-alpha01")
    androidTestImplementation("androidx.test:rules:1.4.1-alpha01")
    annotationProcessor(Dependencies.AndroidX.glide_annotation)

    implementation(Dependencies.DI.koin)
    testImplementation(Dependencies.DI.koin_test_imp)

    implementation(Dependencies.Reactivex.rx_android_3)
    implementation(Dependencies.Reactivex.rx_java_3)
    implementation(Dependencies.Reactivex.androidx_binding)

    implementation(Dependencies.Networking.retrofit)
    implementation(Dependencies.Networking.moshi)
    implementation(Dependencies.Networking.moshi_kt)

    implementation(Dependencies.AndroidX.room)
    implementation(Dependencies.AndroidX.room_ktx)
    kapt(Dependencies.AndroidX.room_compiler)

    testImplementation(Dependencies.Test.jUnit)
    androidTestImplementation(Dependencies.Test.jUnitExt)
    androidTestImplementation(Dependencies.Test.espresso)
}