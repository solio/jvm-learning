# agent 客户端关键点:
## 运行shell，其中cp的分隔符使用在windows和linux下不一样
su - {user same with the process user}
java -cp org-solio-agent-sample-1.0.0-SNAPSHOT.jar org.solio.agent.sample.HelloWorld
java -cp "org-solio-agent-client-1.0.0-SNAPSHOT.jar:${JAVA_HOME}/lib/tools.jar:btrace-asm-5.0.4.jar:" org.solio.agent.client.Main 3489 /home/xzd-agent/Sayhello.class
## 代码关键点
1. 使用VirtualMachine连接目标jvm，命令目标jvm启动一个agent代理程序
2. 将需要替换的类文件路径和类名发给agent(启动的时候，或者网络通知)
3. agent接收这些参数将classloader中的相应类名替换成目标字节码

# 编译依赖
<dependency>
  <groupId>com.sun</groupId>
  <artifactId>tools</artifactId>
  <version>1.8</version>
  <scope>system</scope>
  <systemPath>${JAVA_HOME}/lib/tools.jar</systemPath>
</dependency>
<dependency>
  <groupId>com.sun</groupId>
  <artifactId>btrace</artifactId>
  <version>5.0.4</version>
  <scope>system</scope>
  <systemPath>E:/ows/code/platform/Xzd/agent/agent-server/src/btrace-asm-5.0.4.jar</systemPath>
</dependency>
<dependency>
  <groupId>asm</groupId>
  <artifactId>asm</artifactId>
  <version>4.1</version>
</dependency>

# Manifest.mf
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-jar-plugin</artifactId>
    <version>2.4</version>
    <configuration>
      <archive>
        <manifestEntries>
            <Premain-Class>org.solio.agent.AgentMain</Premain-Class>
            <Agent-Class>org.solio.agent.AgentMain</Agent-Class>
            <Can-Retransform-Classes>true</Can-Retransform-Classes>
        </manifestEntries>
      </archive>
    </configuration>
</plugin>