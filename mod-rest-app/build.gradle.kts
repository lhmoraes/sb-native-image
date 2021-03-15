import org.springframework.boot.gradle.tasks.bundling.BootJar

    // Module group and version configuration .................................
    base.archivesBaseName = rootProject.name

    // Dependencies Project Modules............................................
    dependencies {
        implementation(project(":mod-domain"))
        implementation(project(":mod-business"))
    }

//    // Tasks ..................................................................
//    tasks.getByName<BootJar>("bootJar") {
//        enabled = true
//    }
//
//    tasks.getByName<Jar>("jar") {
//        enabled = true
//    }
