plugins {
    id("java")
    // https://github.com/GradleUp/shadow
    alias(libs.plugins.shadow)
    // https://github.com/JetBrains/gradle-idea-ext
    alias(libs.plugins.gradleideaext)
    id("maven-publish")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}


repositories {
    mavenLocal()
    mavenCentral()
    
    // Sonatype
    maven("https://oss.sonatype.org/content/groups/public")
    // JitPack
    maven("https://jitpack.io")
    // SpigotMC
    maven("https://hub.spigotmc.org/nexus/content/groups/public")
    // FoliaLib
    maven("https://repo.tcoded.com/releases")
}

dependencies {
    // https://mvnrepository.com/artifact/org.jetbrains/annotations
    compileOnly(libs.annotations)
    // https://github.com/google/gson
    implementation(libs.gson)
    // https://mvnrepository.com/artifact/org.apache.commons/commons-lang3
    implementation(libs.commonslang3)
    // https://hub.spigotmc.org/stash/projects/SPIGOT/repos/spigot
    compileOnly(libs.spigot)
    // https://github.com/TechnicallyCoded/FoliaLib
    implementation(libs.folia)
    // https://github.com/KyoriPowered/adventure
    implementation(libs.adventureapi)
    // https://github.com/Bastian/bstats-metrics
    implementation(libs.bstats)
}


tasks {
    compileJava {
        options.encoding = "UTF-8"
    }
    
    shadowJar {
        exclude("META-INF/**", "LICENSE")
        relocate("org.jetbrains.annotations", "fr.byswiizen.solosleep.libs.annotations")
        relocate("com.google.gson", "fr.byswiizen.solosleep.libs.gson")
        relocate("org.apache.commons", "fr.byswiizen.solosleep.libs.commonslang3")
        relocate("com.tcoded.folialib", "fr.byswiizen.solosleep.libs.folialib")
        relocate("org.bstats", "fr.byswiizen.solosleep.libs.metrics")
        archiveFileName.set("${project.name}-${project.version}.jar")
    }
    
    build {
        dependsOn(shadowJar)
    }

    processResources {
        filesMatching("plugin.yml") {
            expand(
                "name" to project.property("name").toString(),
                "groupid" to project.property("groupid").toString(),
                "artifactid" to project.property("artifactid").toString(),
                "description" to project.property("description").toString(),
                "version" to project.property("version").toString(),
                "apiversion" to project.property("apiversion").toString(),
                "author" to project.property("author").toString()
            )
        }
    }
}


publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.property("groupid").toString()
            artifactId = project.property("artifactid").toString()
            version = project.property("version").toString()
            artifact(tasks.shadowJar)
        }
    }
}