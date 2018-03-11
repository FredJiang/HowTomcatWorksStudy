
### 参考资料

* [深入剖析Tomcat.pdf](https://pan.baidu.com/s/1H4sxYglrgyMsuiCcRJqk7g)
* [深入剖析Tomcat 原书代码 HowTomcatWorks.zip](https://pan.baidu.com/s/1aUa74QpAQfkGh-Rv73CWYA)
* [maven 下整理的 HowTomcatWorksStudy 示例代码](https://github.com/FredJiang/HowTomcatWorksStudy)

### 开发环境

```txt
java version "1.8.0_112"
Apache Maven 3.5.2
```

<!--more-->

### 运行第一章的例子

```bash
cd HowTomcatWorksStudy
mvn clean package exec:java -Dexec.mainClass="ex01.pyrmont.HttpServer"
```
