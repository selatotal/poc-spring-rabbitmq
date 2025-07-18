plugins {
	idea
	java
	id("org.springframework.boot") version "3.5.3"
	id("io.spring.dependency-management") version "1.1.7"
	id("io.freefair.lombok") version "8.14"
	id("com.diffplug.spotless") version "7.0.4"
}

group = "br.com.selat"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-amqp")
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.amqp:spring-rabbit-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.compileJava {
	dependsOn(tasks.spotlessApply)
}

tasks.compileTestJava {
	dependsOn(tasks.spotlessApply)
}

spotless {
	java {
		importOrder()
		removeUnusedImports()
		palantirJavaFormat()
	}
	afterEvaluate {
		tasks.getByName("spotlessCheck").dependsOn(tasks.getByName("spotlessApply"))
	}
}
