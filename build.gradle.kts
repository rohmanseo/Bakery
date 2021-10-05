// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        maven (  "https://jitpack.io" )
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:7.0.1")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.30")
        classpath(Dependencies.AndroidX.navigation_safe_args)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.register<Delete>(name="type"){
    delete(rootProject.buildDir)
}