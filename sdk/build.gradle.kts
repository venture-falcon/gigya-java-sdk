plugins {
     `java-library`
     `maven-publish`
}

java {
    withSourcesJar()
}

tasks {
    compileJava {
        sourceCompatibility = "1.8"
        targetCompatibility = "1.8"
    }
}

repositories {
    maven { url = uri("https://jitpack.io") }
    mavenCentral()
}

group = "com.gigya"
//archivesBaseName = "gigya-java-sdk"
//version = sdk_library_version

dependencies {
    testImplementation("junit:junit:4.13.1")
    testImplementation("org.skyscreamer:jsonassert:1.2.3")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.mockito:mockito-core:2.8.9")
    testImplementation("org.powermock:powermock-module-junit4:1.7.4")
    testImplementation("org.powermock:powermock-api-mockito2:1.7.4")
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.json:json:20230227")

    compileOnly("org.json:json:20230227")
}

tasks{
    test {
        useJUnitPlatform()
    }
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/venture-falcon/gigya-java-sdk")
            credentials {
                username = System.getenv("GPR_USER")
                password = System.getenv("GPR_TOKEN")
            }
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            groupId = "com.gigya"
            artifactId = "gigya-java-sdk"
            version = project.findProperty("sdk_library_version") as String?
            from(components["java"])
        }
    }
}
