

subprojects {
    apply {
        plugin("org.jetbrains.kotlin.jvm")
    }

    repositories {
        mavenCentral()
    }

    val implementation by configurations

    dependencies {
        implementation(kotlin("stdlib-jdk8"))
    }
}

allprojects {
    group = "com.github.ricardocunha"
    version = "1.0.0"
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}



