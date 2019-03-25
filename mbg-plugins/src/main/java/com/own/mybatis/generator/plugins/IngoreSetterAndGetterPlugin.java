package com.own.mybatis.generator.plugins;

import org.mybatis.generator.api.dom.java.Method;
import java.util.List;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.Plugin;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/**
 * 因为使用了Lombok，所以应用此插件阻止生成实体的 getter 和 setter
 */
public class IngoreSetterAndGetterPlugin extends PluginAdapter {
    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.addJavaDocLine("import lombok.Getter;");
        topLevelClass.addJavaDocLine("import lombok.Setter;");
        topLevelClass.addJavaDocLine("");

        //该代码表示在生成class的时候，向topLevelClass添加一个@Setter和@Getter注解
        topLevelClass.addAnnotation("@Getter");
        topLevelClass.addAnnotation("@Setter");
        return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
    }

    // 该方法在生成每一个属性的setter方法时候调用，如果我们不想生成setter，直接返回false即可
    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass,
            IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable,
            Plugin.ModelClassType modelClassType) {
        return false;
    }

    // 该方法在生成每一个属性的setter方法时候调用，如果我们不想生成setter，直接返回false即可
    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass,
            IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }
}
