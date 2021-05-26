
plugins {
    kotlin("jvm") version "1.5.10"
    id("idea")
}

allprojects {
    apply {
        plugin("kotlin")
        plugin("idea")
    }

    repositories{
        mavenCentral()
        maven {
            url = uri("https://hyperledger.jfrog.io/hyperledger/fabric-maven")
        }

        maven {
            url = uri("https://jitpack.io")
        }
        exclusiveContent {
            forRepository {
                maven {
                    url = uri("https://regnosys.jfrog.io/regnosys/libs-snapshot")
                    credentials {
                        username = "isda"
                        password = "isda"
                    }
                }
            }
            filter {

                includeGroupByRegex("com\\.regnosys.*")
                includeGroupByRegex("com\\.isda.*")
                includeGroupByRegex("rosetta-*")
            }
        }
    }

    dependencies {
        testImplementation("org.junit.jupiter:junit-jupiter:5.7.1")
        testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
    }
}