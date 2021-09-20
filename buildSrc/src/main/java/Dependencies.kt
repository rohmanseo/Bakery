
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
    const val rx_android2 = "2.1.1"
    const val rx_java2 = "2.2.10"
    const val circle_indicator = "2.1.6"
    const val simple_storage = "0.12.0"
    const val koin_version = "3.1.2"
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
        const val room_compiler = "androidx.room:room-compiler:${Versions.room_version}"
        const val room_rxjava2 = "androidx.room:room-rxjava2:${Versions.room_version}"
        const val view_pager_2 = "androidx.viewpager2:viewpager2:${Versions.view_pager2}"
        const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val glide_annotation = "com.github.bumptech.glide:compiler:${Versions.glide}"
    }
    object Material {
        const val material = "com.google.android.material:material:${Versions.material}"
        const val legacy_support = "androidx.legacy:legacy-support-v4:${Versions.legacy_support}"
        const val circle_indicator = "me.relex:circleindicator:${Versions.circle_indicator}"
    }
    object Reactivex{
        const val rx_android_2 = "io.reactivex.rxjava2:rxandroid:${Versions.rx_android2}"
        const val rx_java2 =     "io.reactivex.rxjava2:rxjava:${Versions.rx_java2}"
    }
    object Storage{
        const val simple_storage = "com.anggrayudi:storage:${Versions.simple_storage}"
    }
    object DI{
        const val koin = "io.insert-koin:koin-core:${Versions.koin_version}"
        const val koin_test_imp = "io.insert-koin:koin-test:${Versions.koin_version}"
    }

    object Test {
        const val jUnit = "junit:junit:${Versions.jUnit}"
        const val jUnitExt = "androidx.test.ext:junit:${Versions.jUnitExt}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    }

}