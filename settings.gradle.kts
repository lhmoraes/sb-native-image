
// Application Dependency Management .......................
rootProject.name = "kinder"

include ("mod-domain")
include ("mod-business")
include ("mod-repository")
include ("mod-rest-app")

// Spring Framework Plugin Dependency Management ...........
pluginManagement {
    repositories {
        mavenLocal()
        maven { url = uri( "https://repo.spring.io/release") }
        maven { url = uri( "https://repo.spring.io/milestone") }
        maven { url = uri( "https://repo.spring.io/snapshot") }
        gradlePluginPortal()
        mavenCentral()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "org.springframework.boot") {
                useModule("org.springframework.boot:spring-boot-gradle-plugin:${requested.version}")
            }
        }
    }
}
