# WearingEmoji

[English (en-US)](/README.md) | **简体中文 (zh-CN)**

## 把 Emoji 穿在身上

Emoji 在网络交流中有很强的表达力。这个模组让你把不同的 Emoji 戴在角色头上，并把它们变成可以直接影响周围生物的互动能力。

## 游戏内截图

![Screenshots-1]()

## IntelliJ / Gradle 配置

本项目基于 NeoForge `26.1.2`，模组代码的编译目标为 Java `25`。

推荐做法：
- 以 Gradle 项目方式导入。
- 让 Gradle 自动探测或自动下载所需的 JDK toolchain。

当前工具链说明：
- 项目在构建脚本中声明的编译目标是 Java `25`。
- 某些本地 `runClient` / `runServer` 场景下，Gradle 还可能需要同时能发现 Java `21`，这取决于本地 NeoForge 运行链以及工具链是否可以自动下载。

如果你的设备不能自动下载 toolchain，可以在用户级 Gradle 配置里声明本地 JDK：
- 编辑 `%USERPROFILE%\.gradle\gradle.properties`

示例：
- `org.gradle.java.installations.paths=C:/jdks/jdk-21,C:/jdks/jdk-25`
- `org.gradle.java.installations.auto-download=false`

你也可以在 IntelliJ IDEA 中把 Gradle JVM 设置为本地的 JDK `21` 或 `25`，前提是 Gradle 仍然能探测到这两个所需 toolchain。

可移植性说明：
- 仓库本身不再提交任何机器专属的 JDK 路径。
- 每台设备自己的 JDK 路径应放在用户级 Gradle 配置中，而不是写入仓库。

## 链接

查看 [MCMOD]() 上的介绍。

[GitHub]() 仓库。
