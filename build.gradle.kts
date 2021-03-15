import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.springframework.boot") version "2.4.3" apply false
    id("org.springframework.experimental.aot") version "0.9.0" apply false

    kotlin("jvm") version "1.4.30"
    kotlin("plugin.spring") version "1.4.30"
}

allprojects {

    // Apply plugins for the all sub-projects .......................
    apply(plugin = "java-library")
    apply(plugin = "kotlin")
    apply(plugin = "kotlin-spring")
    apply(plugin = "io.spring.dependency-management")

    // Project configuration ........................................
    val PROJECT_GROUP                = "net.native"
    val PROJECT_VERSION              = "1.0.RELEASE"
    val SPRING_CLOUD_VERSION         = "2020.0.1"
    val SPRING_DOC_OPENAPI_VERSION   = "1.5.4"
    val MAPSTRUCT_VERSION            = "1.4.2.Final"
    val SENTRY_VERSION               = "4.2.0"
    val R2DBC_MARIADB_VERSION        = "1.0.0"
    val WAVEFRONT_VERSION            = "2.1.0"
    val LOKI_VERSION                 = "1.1.0"

    // Project group and version configuration .......................
    group   = PROJECT_GROUP!!
    version = PROJECT_VERSION!!

    base.archivesBaseName    = rootProject.name
    java.sourceCompatibility = JavaVersion.VERSION_11

	dependencyManagement {
		imports {
			mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
			mavenBom("org.springframework.cloud:spring-cloud-dependencies:${SPRING_CLOUD_VERSION}")
			mavenBom("com.wavefront:wavefront-spring-boot-bom:${WAVEFRONT_VERSION}")
		}
	}

    // Dependencies Libraries .........................................
    dependencies {

        // Kotlin ....................
        implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

        // Micrometer Prometheus ......
        runtimeOnly("io.micrometer:micrometer-registry-prometheus")

        // Jackson ...................
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

        // Wavefront .................
        implementation("com.wavefront:wavefront-spring-boot-starter")

        // MariaDB ...................
        runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
        runtimeOnly("org.mariadb:r2dbc-mariadb:${R2DBC_MARIADB_VERSION}")

        // Loki Grafana Logging.......
        implementation ("com.github.loki4j:loki-logback-appender:${LOKI_VERSION}")

        // Sentry ....................
        implementation("io.sentry:sentry-logback:${SENTRY_VERSION}")

        // MapStruct .................
        implementation("org.mapstruct:mapstruct:${MAPSTRUCT_VERSION}")

        // SpringDoc-OpenApi .........
        implementation("org.springdoc:springdoc-openapi-webflux-ui:${SPRING_DOC_OPENAPI_VERSION}")
        implementation("org.springdoc:springdoc-openapi-kotlin:${SPRING_DOC_OPENAPI_VERSION}")

        // Apache Commons ............
        implementation("commons-io:commons-io:2.6")

        // Spring Test ...............
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("io.projectreactor:reactor-test")
    }



    // Dependencies repositories .....................................
    repositories {
        mavenCentral()
        maven { url = uri( "https://repo.spring.io/release") }
    }

    // Tasks ..................................................................
    tasks {
        withType<Test> {
            useJUnitPlatform()
        }

        withType<KotlinCompile> {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = java.sourceCompatibility.toString()
            }
        }

    }
}
