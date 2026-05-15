# WearingEmoji

**English (en-US)** | [简体中文 (zh-CN)](/README.zh-cn.md)

## Wear Emojis on Your Character!!

Emojis play an indispensable role in online communication. With this mod, you can wear emojis on your character to show off your personality while also triggering a variety of expressive gameplay effects.

## In-Game Screenshots

![Screenshots-1]()

## IntelliJ / Gradle Setup

This project targets NeoForge `26.1.2` and compiles mod code with Java `25`.

Recommended:
- Import the project as a Gradle project.
- Let Gradle auto-detect or auto-download the required JDK toolchains.

Current toolchain expectations:
- Java `25` is the compile target declared by the project toolchain.
- Some local `runClient` / `runServer` workflows may also require Java `21` to be discoverable by Gradle, depending on the local NeoForge runtime chain and whether toolchains can be downloaded automatically.

If your machine cannot download toolchains automatically, configure local JDKs in your user Gradle config:
- Edit `%USERPROFILE%\.gradle\gradle.properties`

Example:
- `org.gradle.java.installations.paths=C:/jdks/jdk-21,C:/jdks/jdk-25`
- `org.gradle.java.installations.auto-download=false`

You can also set the Gradle JVM in IntelliJ IDEA to a local JDK `21` or `25`, as long as Gradle can still discover both required toolchains.

Portability note:
- This repository does not commit machine-specific JDK paths.
- Device-specific JDK locations belong in user-level Gradle config, not in the repository.

## Links

Check out the introduction on [MCMOD]()!

[GitHub]() Repository!
