plugins {
    java
    application
    id("io.freefair.lombok") version "6.6.3"
    id("org.springframework.boot") version "2.7.8"
}

group = "ru.dimaggio"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.projectlombok:lombok:1.18.26")
    implementation(platform("org.junit:junit-bom:5.9.1"))
    implementation("io.rest-assured:rest-assured:5.4.0")
    implementation("io.rest-assured:json-path:5.4.0")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.7.5")
    implementation("javax.annotation:javax.annotation-api:1.3.2")
    implementation("org.hamcrest:hamcrest:2.2")
    implementation("org.springframework.boot:spring-boot-starter-test:2.7.5")
    implementation("org.springframework.boot:spring-boot-starter-validation:2.7.5")
    implementation("org.springframework.boot:spring-boot-starter-aop:2.7.5")
    implementation("org.postgresql:postgresql:42.6.0")

}

tasks.test {
    useJUnitPlatform()
}