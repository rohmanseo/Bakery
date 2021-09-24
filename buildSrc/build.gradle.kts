plugins{
    `kotlin-dsl`
    kotlin("jvm") version "1.4.31"
}

repositories{
    jcenter()
    google()
    mavenCentral()
    maven ( "https://oss.jfrog.org/libs-snapshot" )
}