package com.linlishi.mybatis.generator.plugin;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;

import java.util.List;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

/**
 * @author chutou
 * @date 2024/1/25 13:52
 **/
public class SuffixNameDomainObjectClassPlugin extends PluginAdapter {

    private String suffixName;

    @Override
    public boolean validate(List<String> warnings) {
        this.suffixName = properties.getProperty("suffixName");
        return stringHasValue(suffixName);
    }

    @Override
    public void initialized(IntrospectedTable introspectedTable) {
        String baseRecordType = introspectedTable.getBaseRecordType();
        introspectedTable.setBaseRecordType(baseRecordType + suffixName);
    }
}
