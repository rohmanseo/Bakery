
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
    const val rx_android_3 = "3.0.0"
    const val rx_java_3 = "3.0.0"
    const val circle_indicator = "2.1.6"
    const val simple_storage = "0.12.0"
    const val koin_version = "3.1.2"
    const val rx_binding = "4.0.0"
    const val retrofit = "2.9.0"
    const val moshi_kt = "1.11.0"
    const val room_ktx = "2.3.0"
    const val lup = "0.2.1"
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
        const val room_rx = "androidx.room:room-rxjava3:${Versions.room_version}"
        const val room_ktx = "androidx.room:room-ktx:${Versions.room_ktx}"
        const val room_compiler = "androidx.room:room-compiler:${Versions.room_version}"
        const val view_pager_2 = "androidx.viewpager2:viewpager2:${Versions.view_pager2}"
        const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val glide_annotation = "com.github.bumptech.glide:compiler:${Versions.glide}"
        const val moshi_kt = "1.11.0"
    }
    object Material {
        const val material = "com.google.android.material:material:${Versions.material}"
        const val legacy_support = "androidx.legacy:legacy-support-v4:${Versions.legacy_support}"
    }

    object CustomView{
        const val circle_indicator = "me.relex:circleindicator:${Versions.circle_indicator}"
    }

    object Reactivex{
        const val rx_android_3 = "io.reactivex.rxjava3:rxandroid:${Versions.rx_android_3}"
        const val rx_java_3 =     "io.reactivex.rxjava3:rxjava:${Versions.rx_java_3}"
        const val androidx_binding = "com.jakewharton.rxbinding4:rxbinding-core:${Versions.rx_binding}"
    }
    object Storage{
        const val simple_storage = "com.anggrayudi:storage:${Versions.simple_storage}"
    }
    object Networking{
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val moshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
        const val moshi_kt = "com.squareup.moshi:moshi-kotlin:${Versions.moshi_kt}"
    }
    object DI{
        const val koin = "io.insert-koin:koin-android:${Versions.koin_version}"
        const val koin_test_imp = "io.insert-koin:koin-test:${Versions.koin_version}"
    }

    object Test {
        const val lup = "com.github.icodeuDev:Lup:${Versions.lup}"
        const val jUnit = "junit:junit:${Versions.jUnit}"
        const val jUnitExt = "androidx.test.ext:junit:${Versions.jUnitExt}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    }

}