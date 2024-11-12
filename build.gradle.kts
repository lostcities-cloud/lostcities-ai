plugins {
    id("java")
    kotlin("jvm")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    maven {
        url = uri("https://maven.pkg.github.com/lostcities-cloud/lostcities-models")
        credentials {
            username = System.getenv("GH_USER")
            password = System.getenv("GH_TOKEN")
        }
    }

    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.1")


    // https://mvnrepository.com/artifact/com.rabbitmq/amqp-client
    implementation("com.rabbitmq:amqp-client:5.22.0")
    implementation("io.dereknelson.lostcities-cloud:lostcities-common:0.0.3")
    implementation("io.dereknelson.lostcities-cloud:lostcities-models:0.0.3")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation(kotlin("stdlib-jdk8"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}