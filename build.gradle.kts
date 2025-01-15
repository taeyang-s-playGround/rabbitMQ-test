plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.4.1"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "practice"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	implementation("io.jsonwebtoken:jjwt-api:0.10.7")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.10.7")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.10.7")

	//coroutines
	implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
	implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.6.0")

	//open api 파싱
	implementation ("com.google.code.gson:gson:2.8.6")
	implementation ("org.json:json:20210307")

	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-validation")

	implementation("org.springframework.boot:spring-boot-starter-web")

	//S3
	implementation ("com.amazonaws:aws-java-sdk-s3:1.12.281")

	//fcm
	implementation("com.google.firebase:firebase-admin:8.1.0")

	//mysql
	runtimeOnly("com.mysql:mysql-connector-j")

	//lombok
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	//test
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")

	//rabbitMQ
	implementation("org.springframework.boot:spring-boot-starter-amqp")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
