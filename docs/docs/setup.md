---
sidebar_position: 1
description: Info on how to add the library to your mod.
---

# Adding the library to your mod
At the moment we only have plans to support the latest versions of mc and forge.

If you want older versions of mc please check if the features you need are already supported in older versions.

## build.gradle (Maven)
```groovy
repositories {
    maven { url = "https://maven.sekwah.com" }
}
```
```groovy
dependencies {
    implementation fg.deobf("com.sekwah:SekCLib:0.2.0") // Remember to check the latest version as this may not be up to date.
}
```
