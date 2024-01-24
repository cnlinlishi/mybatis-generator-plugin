# 效果：
使用自定义解析器, tinyint/smallint 转成 Integer
```
<result column="is_delete" jdbcType="INTEGER" property="isDelete" />
```
使用插件MysqlLimitPlugin, XxxExample会生成分页参数

```
private Integer limit;
private Integer offset;
public void setLimit(Integer limit) {
    this.limit = limit;
}
public Integer getLimit() {
    return limit;
}
public void setOffset(Integer offset) {
    this.offset = offset;
}
public Integer getOffset() {
    return offset;
}
```
XxxMapper.xml
```
<select id="selectByExample" parameterType="com.linlishi.XxxExample" resultMap="BaseResultMap">
  ...
  <if test="limit != null">
    <if test="offset != null">
      limit ${offset}, ${limit}
    </if>
    <if test="offset == null">
      limit ${limit}
    </if>
  </if>
</select>
```

使用插件LombokPlugin, 在生成的DO类加上@Data和@NoArgsConstructor注解，引入lombok.Data和lombok.NoArgsConstructor，并且将数据库的列的comment自动添加到字段注释上
```
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Article {
  /**
     * 标题
     */
    private String title;

    /**
     * 子标题
     */
    private String subTitle;
}
```

# 插件使用：

在generator-configuration.xml 添加插件,使用自定义解析器
```
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
```
pom.xml文件中增加依赖
```
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
```

