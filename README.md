# 论文查重（Java / Maven）

基于字符级最长公共子序列（LCS）和 n-gram 的论文重复率计算程序。程序从命令行读取两个输入文件和答案文件路径，并把重复率（保留两位小数）写入答案文件。

## 环境要求

- JDK 8 或更高版本
- Maven 3.6+（IDEA 自带 Maven 也可以）
- IntelliJ IDEA（导入时选择 Maven 项目）

## 目录结构

```text
paper-check/
├─ pom.xml
├─ README.md
├─ examples/
│  ├─ orig.txt
│  └─ orig_add.txt
└─ src/
   ├─ main/java/com/papercheck/
   │  ├─ Main.java
   │  ├─ core/        # 核心算法
   │  ├─ exception/   # 自定义异常
   │  └─ io/          # 参数、文件读写
   └─ test/java/com/papercheck/   # JUnit 5 单元测试
```

## 编译并打包

在项目根目录执行：

```bash
mvn clean package
```

打包产物为 `target/main.jar`。由于运行时没有第三方依赖，直接 `java -jar target/main.jar ...` 即可。

## 运行

```bash
java -jar target/main.jar [原文文件绝对路径] [抄袭版文件绝对路径] [答案文件绝对路径]
```

示例：

```bash
java -jar target/main.jar C:\workspace\orig.txt C:\workspace\orig_add.txt C:\workspace\ans.txt
```

例如答案文件中可能写入：

```text
0.78
```

## 运行测试

```bash
mvn test
```

## 查看覆盖率报告

执行 `mvn test` 后，JaCoCo 会生成 HTML 报告：

```text
target/site/jacoco/index.html
```

用浏览器打开即可查看行覆盖率、分支覆盖率。

## 在 IDEA 中查看性能分析图

1. 打开 IDEA，导入本 Maven 项目。
2. 在右上角 Run/Debug Configurations 中新建 Application 配置，Main class 选择 `com.papercheck.Main`。
3. 在 Program arguments 中填入三个绝对路径，例如：
   ```text
   C:\workspace\orig.txt C:\workspace\orig_add.txt C:\workspace\ans.txt
   ```
4. IDEA Ultimate：点击工具栏的 `Profile` 按钮（带计时器图标），选择 `IntelliJ Profiler`。
5. IDEA Community：安装 `JProfiler` 或 `Async Profiler` 插件；安装 JProfiler 客户端后，在 IDEA 的 `Run > Profile` 中选择 `JProfiler`。
6. 运行结束后打开 Profiler 面板，切换到 `Call Tree` 或 `Flame Graph`，找到 `com.papercheck.core.LcsSimilarity.longestCommonSubsequenceLength`，截图保存。

## 代码质量分析

IDEA 自带的 Inspections 可用于静态检查：

1. 右键项目根目录或 `src`，选择 `Analyze > Inspect Code`。
2. 检查范围为 Whole project。
3. 修复 Warning 和 Typo 之外的所有提示。

Maven 编译也会输出 Warning，可结合 `mvn clean compile` 确认无警告。
