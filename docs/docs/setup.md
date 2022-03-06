---
sidebar_position: 1
description: Info on how to add the library to your mod.
---

# Adding the library to your mod
At the moment we only have plans to support the latest versions of mc and forge.
We are also planning to host our own maven though for now you can use [Curse Maven](https://www.cursemaven.com/).

## build.gradle (Maven)
We need to finish setting up our maven. For now check out the CurseMaven method.
We will try to keep it up to date with the latest file id.

## build.gradle
```groovy
repositories {
    maven { url = "https://maven.sekwah.com" }
}
```
```groovy
dependencies {
    implementation fg.deobf("com.sekwah:SekCLib:0.0.2") // Remember to check the latest version as this may not be up to date.
}
```

## build.gradle (Curse Maven)
```groovy
repositories {
    maven {
        url "https://cursemaven.com"
        content {
            includeGroup "curse.maven"
        }
    }
}
```
```groovy
dependencies {
    implementation fg.deobf("curse.maven:sekclib-371078:3675891") // replace final id with the specific file id you want to use
}
```
You can get the file id by going to the [CurseForge files page](https://www.curseforge.com/minecraft/mc-mods/sekclib/files).
Click the file you want to depend on and then copy the file id from the end of the url.

More info can be found on the [CurseMaven](https://www.cursemaven.com/) website.
