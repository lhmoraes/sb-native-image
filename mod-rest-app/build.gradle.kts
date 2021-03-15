import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
	id("org.springframework.boot")
	id("org.springframework.experimental.aot")
}

// Module group and version configuration .................................
base.archivesBaseName = rootProject.name

// Dependencies Project Modules............................................
dependencies {
	implementation(project(":mod-domain"))
	implementation(project(":mod-business"))

	// Spring Boot ..........
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-webflux")

	//implementation("org.springframework.cloud:spring-cloud-starter-sleuth")
	//developmentOnly("org.springframework.boot:spring-boot-devtools")

	// Spring Native Image ..............
	implementation("org.springframework.experimental:spring-native:0.9.0")

	// Spring Kubernetes ................
	implementation("org.springframework.cloud:spring-cloud-starter-kubernetes-client-config")
	implementation("org.springframework.cloud:spring-cloud-kubernetes-fabric8-istio")
}


tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootBuildImage>("bootBuildImage") {
	builder = "paketobuildpacks/builder:tiny"
	environment = mapOf("BP_NATIVE_IMAGE" to "true")
}
