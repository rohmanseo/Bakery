
object Versions {
    const val core = "1.6.0"
    const val appCompat = "1.3.1"
    const val kotlin = "1.4.31"
    const val material = "1.4.0"
    const val jUnit = "4.+"
    const val jUnitExt = "1.1.3"
    const val espresso = "3.4.0"
    const val constrainLayout = "2.1.0"
    const val lifecycle_version = "2.4.0-alpha03"
    const val nav_version = "2.3.5"
    const val room_version = "2.3.0"
    const val legacy_support = "1.0.0"
    const val view_pager2 = "1.0.0"
    const val glide = "4.12.0"
    const val circle_indicator = "2.1.6"
    const val simple_storage = "0.12.0"
    const val koin_version = "3.1.2"
    const val retrofit = "2.9.0"
    const val moshi_kt = "1.11.0"
    const val logging_interceptor = "3.10.0"
    const val room_ktx = "2.3.0"
    const val db_debug = "1.0.6"
    const val shimmer = "0.5.0"
    const val goog_truth = "1.1.3"
}

object Dependencies {
    object AndroidX {
        const val core = "androidx.core:core-ktx:${Versions.core}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val constrain_layout = "androidx.constraintlayout:constraintlayout:${Versions.constrainLayout}"
        const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle_version}"
        const val viewmodel =  "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle_version}"
        const val livedata =  "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle_version}"
        const val navigation = "androidx.navigation:navigation-fragment-ktx:${Versions.nav_version}"
        const val navigation_ui = "androidx.navigation:navigation-ui-ktx:${Versions.nav_version}"
        const val navigation_safe_args = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.nav_version}"
        const val navigation_dynamic_support = "androidx.navigation:navigation-dynamic-features-fragment:${Versions.nav_version}"
        const val room = "androidx.room:room-runtime:${Versions.room_version}"
        const val room_ktx = "androidx.room:room-ktx:${Versions.room_ktx}"
        const val room_compiler = "androidx.room:room-compiler:${Versions.room_version}"
        const val view_pager_2 = "androidx.viewpager2:viewpager2:${Versions.view_pager2}"
        const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val glide_annotation = "com.github.bumptech.glide:compiler:${Versions.glide}"
    }
    object Material {
        const val material = "com.google.android.material:material:${Versions.material}"
        const val legacy_support = "androidx.legacy:legacy-support-v4:${Versions.legacy_support}"
    }

    object CustomView{
        const val circle_indicator = "me.relex:circleindicator:${Versions.circle_indicator}"
        const val shimmer = "com.facebook.shimmer:shimmer:${Versions.shimmer}"
    }

    object Storage{
        const val simple_storage = "com.anggrayudi:storage:${Versions.simple_storage}"
    }
    object Networking{
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val moshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
        const val moshi_kt = "com.squareup.moshi:moshi-kotlin:${Versions.moshi_kt}"
        const val logging_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.logging_interceptor}"
    }
    object DI{
        const val koin = "io.insert-koin:koin-android:${Versions.koin_version}"
        const val koin_test_imp = "io.insert-koin:koin-test:${Versions.koin_version}"
    }

    object Test {
        const val goog_truth = "com.google.truth:truth:${Versions.goog_truth}"
        const val db_debug = "com.amitshekhar.android:debug-db:${Versions.db_debug}"
        const val jUnit = "junit:junit:${Versions.jUnit}"
        const val jUnitExt = "androidx.test.ext:junit:${Versions.jUnitExt}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    }

}