# 插件支持列表：
1. tinyint/smallint 转成 Integer
2. 支持分页
3. lombok替换getter/setter
4. 生成的DO类支持自定义后缀名，比如XxxDO
# 效果：
1. 使用自定义解析器, tinyint/smallint 转成 Integer<br>
sql定义
```
`is_delete` tinyint(3) unsigned NOT NULL default 0 COMMENT '是否逻辑删除 0:否；1:是'
```
XxxMapper.xml
```
<result column="is_delete" jdbcType="INTEGER" property="isDelete" />
```
2. 使用插件MysqlLimitPlugin会生成分页参数<br>
XxxExample

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

3. 使用插件LombokPlugin, 在生成的DO类用lombok替换getter/setter，并且将数据库的列的comment自动添加到字段注释上<br>
DO类
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
4. 使用SuffixNameDomainObjectClassPlugin插件，在生成的DO类中添加后缀名
DO类
```
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArticleDO {
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
        <plugin type="com.linlishi.mybatis.generator.plugin.SuffixNameDomainObjectClassPlugin">
            <property name="suffixName" value="DO"/>
        </plugin>
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

