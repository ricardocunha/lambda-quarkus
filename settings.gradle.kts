pluginManagement {
    val quarkusPluginVersion: String by settings
    val quarkusPluginId: String by settings
    repositories {
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {
        id(quarkusPluginId) version quarkusPluginVersion
        id("org.kordamp.gradle.jandex") version ("0.8.0")
    }
}
rootProject.name="lambda-quarkus"


include(":session")
project(":session").projectDir = file("lambda/session")


