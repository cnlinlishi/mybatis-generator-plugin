package com.linlishi.mybatis.generator.resolver;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

import java.sql.Types;

/**
 * @author chutou
 * @date 2024/1/24 11:43
 **/
public class JavaTypeResolver extends JavaTypeResolverDefaultImpl {
    public JavaTypeResolver() {
        super();
        // tinyint 映射到java integer
        this.typeMap.put(Types.TINYINT, new JdbcTypeInformation("INTEGER", new FullyQualifiedJavaType(Integer.class.getName())));
        // smallint 映射到java integer
        this.typeMap.put(Types.SMALLINT, new JdbcTypeInformation("INTEGER", new FullyQualifiedJavaType(Integer.class.getName())));
    }
}
