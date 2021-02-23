plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    mavenCentral()
    google()
    jcenter()
    gradlePluginPortal()

}

dependencies {

    implementation(plugin("com.gradle.enterprise", "3.5"))

    implementation(plugin("com.github.node-gradle.node", "2.2.3"))

    /* Depend on the kotlin plugin, since we want to access it in our plugin */
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.50")

    /* Depend on the default Gradle API's since we want to build a custom plugin */
    implementation(gradleApi())
    implementation(localGroovy())
}

fun plugin(name: String, version: String): String {
    return "$name:$name.gradle.plugin:$version"
}




