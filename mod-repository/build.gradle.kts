import org.springframework.boot.gradle.tasks.bundling.BootJar

    // Module group and version configuration .................................
    base.archivesBaseName = "${rootProject.name}-${project.name.substring(4)}"

    // Dependencies Project Modules............................................
    dependencies {
        implementation(project(":mod-domain"))
		implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    }

//    // Tasks ..................................................................
//    tasks.getByName<BootJar>("bootJar") {
//        enabled = true
//    }
//
//    tasks.getByName<Jar>("jar") {
//        enabled = true
//    }
