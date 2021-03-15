// Application Dependency Management .......................
rootProject.name = "native"

include ("mod-domain")
include ("mod-business")
include ("mod-repository")
include ("mod-rest-app")

pluginManagement {
    repositories {
		maven { url = uri("https://repo.spring.io/release") }
		gradlePluginPortal()
    }
}
