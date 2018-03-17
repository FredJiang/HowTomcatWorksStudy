
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

* `src/main/java/ex02fred/pyrmont/servlet-api-2.5-sources.jar` 是通过 `Maven` 下载的源代码
* 参考 `src/main/java/javax/servlet/ServletResponseWrapper.java` 修改 `src/main/java/ex02fred/pyrmont/ResponseFacade.java` 
* 参考 `src/main/java/javax/servlet/ServletRequestWrapper.java` 修改 `src/main/java/ex02fred/pyrmont/RequestFacade.java`


* `PrimitiveServlet.class` 无效的话，可以把 `target/classes/PrimitiveServlet.class` 拷贝到相应的 `webroot` 下

#### 1

```bash
cd HowTomcatWorksStudy/src/main/java

javac ex02/pyrmont/*java -Xlint:deprecation

java ex02.pyrmont.HttpServer1

curl -v "http://127.0.0.1:8080/servlet/PrimitiveServlet"
```


#### 2

```bash
cd HowTomcatWorksStudy/src/main/java

javac -classpath ".:/Users/Fred/.m2/repository/org/slf4j/slf4j-api/1.7.25/slf4j-api-1.7.25.jar:/Users/Fred/.m2/repository/ch/qos/logback/logback-core/1.2.3/logback-core-1.2.3.jar:/Users/Fred/.m2/repository/ch/qos/logback/logback-classic/1.2.3/logback-classic-1.2.3.jar:" \
ex02fred/pyrmont/*java -Xlint:deprecation

java  -classpath ".:/Users/Fred/.m2/repository/org/slf4j/slf4j-api/1.7.25/slf4j-api-1.7.25.jar:/Users/Fred/.m2/repository/ch/qos/logback/logback-core/1.2.3/logback-core-1.2.3.jar:/Users/Fred/.m2/repository/ch/qos/logback/logback-classic/1.2.3/logback-classic-1.2.3.jar:" \
ex02fred.pyrmont.HttpServer1

curl -v "http://127.0.0.1:8080/servlet/PrimitiveServlet"
```


#### 3

```bash
cd HowTomcatWorksStudy

mvn clean package

java  -classpath ".:/Users/Fred/.m2/repository/org/slf4j/slf4j-api/1.7.25/slf4j-api-1.7.25.jar:/Users/Fred/.m2/repository/ch/qos/logback/logback-core/1.2.3/logback-core-1.2.3.jar:/Users/Fred/.m2/repository/ch/qos/logback/logback-classic/1.2.3/logback-classic-1.2.3.jar:target/HowTomcatWorksStudy.jar:" \
ex02fred2.pyrmont.HttpServer1

curl -v "http://127.0.0.1:8080/servlet/PrimitiveServlet"
```


#### 4

可能报错 `java.lang.ClassCastException: PrimitiveServlet cannot be cast to javax.servlet.Servlet`

* <https://stackoverflow.com/questions/11865917/classcastexception-because-of-classloaders>
* <https://stackoverflow.com/questions/2591779/cast-across-classloader>
* <https://stackoverflow.com/questions/11613988/how-to-get-classpath-from-classloader>

```bash
cd HowTomcatWorksStudy

mvn clean package exec:java -Dexec.mainClass="ex02fred2.pyrmont.HttpServer1"

curl -v "http://127.0.0.1:8080/servlet/PrimitiveServlet"
```

#### 5

```bash
cd HowTomcatWorksStudy

mvn clean package exec:java -Dexec.mainClass="ex02fred2.pyrmont.HttpServer2"

curl -v "http://127.0.0.1:8080/servlet/PrimitiveServlet"
```


### 第三章

#### 1

```
cd HowTomcatWorks/src

javac -classpath ".:../lib/servlet.jar:" ex03/pyrmont/*java ex03/pyrmont/startup/*java -Xlint:unchecked -Xlint:deprecation

java -classpath ".:../lib/servlet.jar:" "ex03.pyrmont.startup.Bootstrap"

curl -v "http://127.0.0.1:8080/servlet/PrimitiveServlet"
```

#### 2

```
cd HowTomcatWorksStudy/src/main/java

javac -classpath ".:../lib/servlet.jar:" ex03/pyrmont/*java ex03/pyrmont/startup/*java -Xlint:unchecked -Xlint:deprecation

java -classpath ".:../lib/servlet.jar:" "ex03.pyrmont.startup.Bootstrap"

curl -v "http://127.0.0.1:8080/servlet/PrimitiveServlet"
```