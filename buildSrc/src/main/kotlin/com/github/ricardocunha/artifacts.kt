package com.github.ricardocunha

import org.gradle.api.Project

object Artifacts {

    object AWS {
        const val urlConnectionClient = "software.amazon.awssdk:url-connection-client:2.15.24"
        object Dynamodb {
            const val enhanced = "software.amazon.awssdk:dynamodb-enhanced:2.15.24"
        }
        const val apacheClient = "software.amazon.awssdk:apache-client"
    }
    object JUnit {
        const val jupiterApi = "org.junit.jupiter:junit-jupiter-api:5.7.0"
        const val jupiterEngine = "org.junit.jupiter:junit-jupiter-engine"
        const val mockitoCore = "org.mockito:mockito-core:3.6.28"
        const val assertJCore = "org.assertj:assertj-core:3.18.1"
        const val restAssured = "io.rest-assured:rest-assured:4.3.3"
    }

    object Quarkus {
        val amazonDynamodb = quarkusArtifact("amazon-dynamodb")

        val lambda = quarkusArtifact("amazon-lambda")
        val lambdaHttp = quarkusArtifact("amazon-lambda-http")
        val testLambda = quarkusArtifact("test-amazon-lambda")

        val arc = quarkusArtifact("arc")

        val junit5 = quarkusArtifact("junit5")
        val junit5Mock = quarkusArtifact("junit5-mockito")
        val lambdaTest = quarkusArtifact("test-amazon-lambda")

        val vertx = quarkusArtifact("vertx")
        val vertxWeb = quarkusArtifact("vertx-web")

         private fun quarkusArtifact(name: String) = "io.quarkus:quarkus-$name"
    }

    object Other {
        const val lombok = "org.projectlombok:lombok:1.18.16"
        const val gson = "com.google.code.gson:gson:2.7"
    }
}

