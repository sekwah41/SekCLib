# Quickstart

If you just want to build or run the project then you can use the following commands.

These are meant for linux/mac, you'll need to use gradlew.bat on windows
```bash
./gradlew build
./gradlew runFabricClient
./gradlew runForgeClient
```

Cloche builds the Fabric and Forge artifacts together. Final jars are written
to `build/libs` with `-fabric` and `-forge` classifiers. The test mod uses the
same targets and can be launched with `testmod:runFabricClient` or
`testmod:runForgeClient`.
