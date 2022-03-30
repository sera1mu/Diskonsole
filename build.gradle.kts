plugins {
    kotlin("jvm") version "1.6.10"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("com.diffplug.spotless") version "6.3.0"
}

group = "me.seraimu"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
    maven("https://nexus.scarsz.me/content/repositories/releases/")
    maven("https://m2.dv8tion.net/releases")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

configure<com.diffplug.gradle.spotless.SpotlessExtension> {
    val ktlintVersion = "0.45.1"

    format("misc") {
        target("**/*.md", "**/*.yml", "**/.gitignore")
        indentWithSpaces()
        endWithNewline()
        trimTrailingWhitespace()
    }

    kotlin {
        target(
            fileTree(".") {
                include("**/*.kt")
                exclude("**/.gradle/**")
                exclude("**/build/**")
            }
        )

        ktlint(ktlintVersion)
        indentWithSpaces()
        endWithNewline()
        trimTrailingWhitespace()
    }

    kotlinGradle {
        target("*.gradle.kts")
        ktlint(ktlintVersion)
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("me.scarsz:jdaappender:1.0.2")
    implementation("net.dv8tion:JDA:5.0.0-alpha.9") {
        exclude("club.minnced", "opus-java")
    }
    compileOnly("io.papermc.paper:paper-api:1.18.2-R0.1-SNAPSHOT")
}

tasks {
    build {
        dependsOn("shadowJar")
    }

    compileKotlin {
        kotlinOptions {
            jvmTarget = "17"
        }
    }

    processResources {
        filteringCharset = "UTF-8"
        from(sourceSets["main"].resources.srcDirs) {
            include("**/*.yml")
            duplicatesStrategy = DuplicatesStrategy.INCLUDE
            filter<org.apache.tools.ant.filters.ReplaceTokens>("tokens" to mapOf("version" to project.version))
            filter<org.apache.tools.ant.filters.ReplaceTokens>("tokens" to mapOf("name" to project.name))
        }
    }
}