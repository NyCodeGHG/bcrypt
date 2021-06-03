import java.util.Base64

plugins {
    kotlin("multiplatform") version "1.5.10"
    id("org.jetbrains.dokka") version "1.4.32"
    `maven-publish`
    signing
}

group = "de.nycode"
version = "2.0.0"

repositories {
    mavenCentral()
}

kotlin {
    explicitApi()
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    js(BOTH) {
        nodejs()
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0-native-mt")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("at.favre.lib:bcrypt:0.9.0")
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit5"))
                implementation("org.junit.jupiter:junit-jupiter-api:5.7.2")
                runtimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.2")
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(npm("bcrypt", "5.0.1"))
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
    }
}

tasks {
    dokkaHtml {
        outputDirectory.set(file("docs"))
    }
}

val javadocJar = tasks.register<Jar>("javadocJar") {
    archiveClassifier.set("javadoc")
}

val sonatypeUsername = project.findProperty("sonatypeUsername").toString()
val sonatypePassword = project.findProperty("sonatypePassword").toString()

publishing {
    publications {
        repositories {
            maven {
                name = "oss"
                val releasesRepoUrl = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                val snapshotsRepoUrl = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
                url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl
                credentials {
                    username = sonatypeUsername
                    password = sonatypePassword
                }
            }
            maven {
                name = "nycode"
                val releasesRepoUrl = uri("https://nycode.jfrog.io/artifactory/nycode-releases/")
                val snapshotsUrl = uri("https://nycode.jfrog.io/artifactory/nycode-snapshots/")
                url = if (version.toString().endsWith("SNAPSHOT")) snapshotsUrl else releasesRepoUrl
                credentials {
                    username = findProperty("jfrogUsername")?.toString()
                    password = findProperty("jfrogPassword")?.toString()
                }
            }
        }
        withType<MavenPublication> {
            artifact(javadocJar)
            pom {
                name.set("BCrypt")
                description.set("Kotlin multiplatform bcrypt library")
                licenses {
                    license {
                        name.set("MIT")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                url.set("https://github.com/NyCodeGHG/bcrypt")
                issueManagement {
                    system.set("GitHub")
                    url.set("https://github.com/NyCodeGHG/bcrypt/issues")
                }
                scm {
                    connection.set("https://github.com/NyCodeGHG/bcrypt.git")
                    url.set("https://github.com/NyCodeGHG/bcrypt")
                }
                developers {
                    developer {
                        name.set("NyCode")
                        email.set("nico@nycode.de")
                        url.set("https://nycode.de")
                        timezone.set("Europe/Berlin")
                    }
                }
            }
        }
    }
}

signing {
    val signingKey = findProperty("signingKey")?.toString()
    val signingPassword = findProperty("signingPassword")?.toString()
    if (signingKey != null && signingPassword != null) {
        useInMemoryPgpKeys(
            String(Base64.getDecoder().decode(signingKey.toByteArray())),
            signingPassword
        )
    }

    publishing.publications.withType<MavenPublication> {
        sign(this)
    }
}
