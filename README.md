
### 参考资料

* [深入剖析Tomcat.pdf](https://pan.baidu.com/s/1H4sxYglrgyMsuiCcRJqk7g)
* [深入剖析Tomcat 原书代码 HowTomcatWorks.zip](https://pan.baidu.com/s/1aUa74QpAQfkGh-Rv73CWYA)
* [自己整理的 HowTomcatWorksStudy 示例代码](https://github.com/FredJiang/HowTomcatWorksStudy)

### 开发环境

|  软件 |        版本       |
|-------|-------------------|
| Java  | 1.8.0_112         |
| Maven | 3.5.2             |
| Emacs | 25.3.1            |
| OS    | macOS High Sierra |

<!--more-->

### 第一章


```bash
cd HowTomcatWorksStudy
mvn clean package exec:java -Dexec.mainClass="ex01.pyrmont.HttpServer"

curl -v "http://127.0.0.1:8080/index.html"
curl -v "http://127.0.0.1:8080/test.html"
curl -v "http://127.0.0.1:8080/no.html"
```


### 第二章

* `src/main/java/ex02/pyrmont/servlet-api-2.5-sources.jar` 是通过 `Maven` 下载的源代码
* 参考 `src/main/java/javax/servlet/ServletResponseWrapper.java` 修改 `src/main/java/ex02/pyrmont/ResponseFacade.java` 
* 参考 `src/main/java/javax/servlet/ServletRequestWrapper.java` 修改 `src/main/java/ex02/pyrmont/RequestFacade.java`

```bash
mvn clean package exec:java -Dexec.mainClass="ex02.pyrmont.HttpServer1"
```

