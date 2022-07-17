plugins {
    id("java")
}

group = "cc.ricecx"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":packets"))
}
