package com.own.mybatis.generator.plugins;

import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.TopLevelClass;

public class CommentGeneratorImpl extends DefaultCommentGenerator {

    public CommentGeneratorImpl() {
        super();
    }

    @Override
    public void addFieldComment(org.mybatis.generator.api.dom.java.Field field,
            org.mybatis.generator.api.IntrospectedTable introspectedTable,
            org.mybatis.generator.api.IntrospectedColumn introspectedColumn) {

        // StringBuilder sb = new StringBuilder();
        // field.addJavaDocLine("/**");
        // sb.append(" * ");
        // sb.append(introspectedTable.getFullyQualifiedTable());
        // field.addJavaDocLine(sb.toString().replace("\n", " "));
        // field.addJavaDocLine(" */");

        field.addAnnotation("@ApiModelProperty(value = \"" + introspectedColumn.getRemarks() + "\", required = false)");

        // System.out.println("addFieldComment: " + field.getName() + " " +
        // introspectedTable.getTableType() + " "
        // + introspectedColumn.getRemarks());
    }

    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.addJavaDocLine("");
        topLevelClass.addJavaDocLine("import io.swagger.annotations.ApiModel;");
        topLevelClass.addJavaDocLine("import io.swagger.annotations.ApiModelProperty;");
        topLevelClass.addJavaDocLine("");
        topLevelClass.addAnnotation("@ApiModel(description = \"" + introspectedTable.getRemarks() + "\")");
    }
}