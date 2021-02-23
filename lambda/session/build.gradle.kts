import com.github.ricardocunha.Artifacts

plugins {
    java
    id("io.quarkus")
    id("io.freefair.lombok") version "5.3.0"
    id("org.kordamp.gradle.jandex") version "0.6.0"
}

repositories {
    mavenLocal()
    mavenCentral()
}

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project

dependencies {
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
    if (hasProperty("buildProfile") && findProperty("buildProfile") == "lambda") {
        implementation(Artifacts.Quarkus.lambda)
        implementation(Artifacts.Quarkus.lambdaHttp)
    }
    implementation(Artifacts.Quarkus.vertx)
    implementation(Artifacts.Quarkus.vertxWeb)
    implementation(Artifacts.Quarkus.amazonDynamodb)
    implementation(Artifacts.Quarkus.arc)

    testImplementation(Artifacts.Quarkus.junit5)
    testImplementation(Artifacts.Quarkus.testLambda)

    compileOnly (Artifacts.Other.lombok)
    annotationProcessor(Artifacts.Other.lombok)

    testCompileOnly (Artifacts.Other.lombok)
    testAnnotationProcessor (Artifacts.Other.lombok)

    implementation(Artifacts.Other.gson)

    implementation(Artifacts.AWS.urlConnectionClient)
    implementation(Artifacts.AWS.apacheClient)

}

group = "com.github.ricardocunha"
version = "0.0.1"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.compilerArgs.add("-parameters")
}

