# bcrypt
![Maven Central](https://img.shields.io/maven-central/v/de.nycode/bcrypt?style=flat-square)
[![Gradle CI](https://github.com/NyCodeGHG/bcrypt/actions/workflows/ci.yml/badge.svg)](https://github.com/NyCodeGHG/bcrypt/actions/workflows/ci.yml)

Kotlin multiplatform library for working with BCrypt.

(Updated version of [bcrypt-mpp](https://github.com/ToxicBakery/bcrypt-mpp)
by [@ToxicBakery](https://github.com/ToxicBakery))

This library is available via [maven central](https://search.maven.org/artifact/de.nycode/bcrypt).

## Gradle
```kotlin
implementation("de.nycode:bcrypt:2.1.1")
```

## Maven
```xml
<dependency>
  <groupId>de.nycode</groupId>
  <artifactId>bcrypt</artifactId>
  <version>2.1.1</version>
  <type>pom</type>
</dependency>
```

## Usage
### Hash
```kotlin
val hash = hash("Hello pls hash me")
```

### Verify
```kotlin
if (verify("Hello pls hash me", hash)) {
    println("Valid")
} else {
    println("Invalid")
}
```
