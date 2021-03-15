import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootBuildImage
import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("org.springframework.boot") version "2.4.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.springframework.experimental.aot") version "0.9.0"

    kotlin("jvm") version "1.4.30"
    kotlin("plugin.spring") version "1.4.30"
    kotlin("kapt") version "1.4.30"

    base
    java
}

allprojects {

    // Apply plugins for the all sub-projects .......................
    apply(plugin = "java")
    apply(plugin = "base")
    apply(plugin = "kotlin")
    apply(plugin = "kotlin-spring")
    apply(plugin = "kotlin-kapt")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    // Project configuration ........................................
    val PROJECT_GROUP                = "net.parlacom"
    val PROJECT_VERSION              = "1.0.RELEASE"
    val SPRING_CLOUD_VERSION         = "2020.0.1"
//    val SPRING_CLOUD_KUBERNETES      = "1.1.8.RELEASE"
    val SPRING_DOC_OPENAPI_VERSION   = "1.5.4"
    val PARLA_COMMON_VERSION         = "4.0.RELEASE"
    val MAPSTRUCT_VERSION            = "1.4.2.Final"
    val SENTRY_VERSION               = "4.2.0"
//    val R2DBC_MARIADB_CLIENT_VERSION = "2.7.1"
    val R2DBC_MARIADB_VERSION        = "1.0.0"
    val WAVEFRONT_VERSION            = "2.1.0"
    val LOKI_VERSION                 = "1.1.0"
    val JODA_VERSION                 = "2.10.1"

//    val IMAGE_NAME           = project.property("IMAGE_NAME").toString()
     val IMAGE_NAME        = "registry.parlacom.net:5000/leadingquest/leadingquest-kinder:prod-v1.0"
//    val REGISTRY_URL         = project.property("REGISTRY_URL").toString()
     val REGISTRY_URL      = "http://registry.parlacom.net:5000"
//    val REGISTRY_USERNAME    = project.property("REGISTRY_USERNAME").toString()
     val REGISTRY_USERNAME = "parla"
//    val REGISTRY_PASSWORD    = project.property("REGISTRY_PASSWORD").toString()
     val REGISTRY_PASSWORD = "Leodouve10@"
//    val REGISTRY_CONTACT     = project.property("REGISTRY_CONTACT").toString()
    val REGISTRY_CONTACT   = "luiz@leadingguest.com"

////    val REPOSITORY_NAME        = project.property("REPOSITORY_NAME").toString()
//     val REPOSITORY_NAME     = "parla"
////    val REPOSITORY_URL         = project.property("REPOSITORY_URL").toString()
//     val REPOSITORY_URL      = "https://repository.parlacom.net:8081/repository/parla/"
////    val REPOSITORY_USERNAME    = project.property("REPOSITORY_USERNAME").toString()
//     val REPOSITORY_USERNAME = "parla"
////    val REPOSITORY_PASSWORD    = project.property("REPOSITORY_PASSWORD").toString()
//     val REPOSITORY_PASSWORD = "apisuccess2018"


    // Project group and version configuration .......................
    group   = PROJECT_GROUP!!
    version = PROJECT_VERSION!!

    base.archivesBaseName    = rootProject.name
    java.sourceCompatibility = JavaVersion.VERSION_11

    // Dependencies Libraries .........................................
    dependencies {

        // Parlacom .................
        implementation("net.parlacom:milka:${PARLA_COMMON_VERSION}")

        // Spring Framework ..........
        implementation("org.springframework.boot:spring-boot-starter-actuator")
        implementation("org.springframework.boot:spring-boot-starter-webflux")
        implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
        implementation("org.springframework.cloud:spring-cloud-starter-sleuth")
        developmentOnly("org.springframework.boot:spring-boot-devtools")

        // Spring Native Image ..............
        implementation("org.springframework.experimental:spring-native:0.9.0")

        // Spring Kubernetes ................
        implementation("org.springframework.cloud:spring-cloud-starter-kubernetes-client-config")
        implementation("org.springframework.cloud:spring-cloud-kubernetes-fabric8-istio")

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
        kapt("org.mapstruct:mapstruct-processor:${MAPSTRUCT_VERSION}")

        // Joda Date Time ..............
        //implementation("joda-time:joda-time:${JODA_VERSION}")

        // SpringDoc-OpenApi .........
        implementation("org.springdoc:springdoc-openapi-webflux-ui:${SPRING_DOC_OPENAPI_VERSION}")
        implementation("org.springdoc:springdoc-openapi-kotlin:${SPRING_DOC_OPENAPI_VERSION}")


// TODO: TEMPORARILY Apache Commons IO
implementation("commons-io:commons-io:2.6")


        // Spring Test ...............
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("io.projectreactor:reactor-test")
    }

    // Spring Cloud dependency management .....................................
    dependencyManagement {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${SPRING_CLOUD_VERSION}")
            mavenBom("com.wavefront:wavefront-spring-boot-bom:${WAVEFRONT_VERSION}")
        }
    }

    // Dependencies repositories .....................................
    repositories {
        maven {
            name = "parla"
            url = uri("https://repository.parlacom.net:8081/repository/parla/")
            credentials{
                username = "parla"
                password = "apisuccess2018"
            }
        }
        maven { url = uri( "https://repo.spring.io/release") }
        maven { url = uri( "https://repo.spring.io/milestone") }
        maven { url = uri( "https://repo.spring.io/snapshot") }
        mavenCentral()
        //maven { url = uri("https://mymavenrepo.com/repo/jAdYcoPJW46SYQr459hM/") }
    }

    // Tasks ..................................................................
    tasks {
    // Tasks ..................................................................

        getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
            enabled = true
        }
        getByName<Jar>("jar") {
            enabled = true
        }

        withType<Test> {
            useJUnitPlatform()
        }

        withType<KotlinCompile> {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = java.sourceCompatibility.toString()
            }
        }

        // Image: Build the image and publish it to the private parlacom registry
        // https://github.com/spring-projects/spring-boot/blob/472c563451e141b2df31d31c7cfbf8bed22b3bc3/spring-boot-project/spring-boot-tools/spring-boot-maven-plugin/src/docs/asciidoc/packaging-oci-image.adoc
        withType<BootBuildImage> {
            builder = "paketobuildpacks/builder:tiny"
            environment = mapOf("BP_NATIVE_IMAGE" to "true")

            imageName = IMAGE_NAME
            isPublish = true

            docker {
                publishRegistry {
                    username = REGISTRY_USERNAME
                    password = REGISTRY_PASSWORD
                    url      = REGISTRY_URL
                    email    = REGISTRY_CONTACT
                }
            }
        }
    }
}
