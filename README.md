插件使用：

在generator-configuration.xml 添加插件,使用自定义解析器
<generatorConfiguration>
    ...
    <context>
        <plugin type="com.linlishi.mybatis.generator.plugin.MysqlLimitPlugin"></plugin>
        <plugin type="com.linlishi.mybatis.generator.plugin.LombokPlugin"></plugin>
        ...
        <javaTypeResolver type="com.linlishi.mybatis.generator.resolver.JavaTypeResolver">
            <property name="forceBigDecimals" value="false" />
        </javaTypeResolver>
        ...
    </context>
    ...
</generatorConfiguration>
pom.xml文件中增加依赖
<plugins>
    <plugin>
        <groupId>org.mybatis.generator</groupId>
        <artifactId>mybatis-generator-maven-plugin</artifactId>
        <version>1.4.0</version>
        ...
        <dependencies>
            <dependency>
                <groupId>com.linlishi</groupId>
                <artifactId>mybatis-generator-plugin</artifactId>
                <version>1.0.0</version>
            </dependency>
        </dependencies>
    </plugin>
</plugins>

