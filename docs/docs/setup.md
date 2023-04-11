---
sidebar_position: 1
description: Info on how to add the library to your mod
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
    implementation fg.deobf("com.sekwah:SekCLib:[0.4.3,1.0.0)") // Remember to check the latest version as this may not be up to date.
}
```
Don't forget to also add the entry to your mods.toml file too.
```yaml
[[dependencies.yourmodhere]]
    modId="sekclib"
    mandatory=true
    versionRange="[0.2.0,)"
    ordering="NONE"
    side="BOTH"
```

# Including the mod as jar in jar

If you would like to include the mod within your mod, feel free to. This can be achieved with the following build.gradle changes.

I only ask that you list it as an embedded library on Curse or Modrinth.

```groovy
jarJar.enable()

dependencies {
    implementation jarJar(fg.deobf("com.sekwah:SekCLib:[0.4.3,1.0.0)"))
}

```