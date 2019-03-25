package com.own.mybatis.generator.plugins;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Method;

import java.util.List;

public class ExampleEnhancedPlugin extends PluginAdapter {
  /**
   * {@inheritDoc}
   *
   * @param introspectedTable
   */
  @Override
  public void initialized(IntrospectedTable introspectedTable) {
    super.initialized(introspectedTable);
  }

  @Override
  public boolean validate(List<String> warnings) {
    return true;
  }

  @Override
  public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    addGetFieldNameMethodToExample(topLevelClass, introspectedTable);

    return true;
  }

  /**
   * 为 Example 添加根据实体属性获取字段名的方法
   */
  private void addGetFieldNameMethodToExample(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    Method method = new Method();
    method.setVisibility(JavaVisibility.PUBLIC);
    method.setReturnType(new FullyQualifiedJavaType(String.class.getName()));
    method.addJavaDocLine("/**");
    method.addJavaDocLine("* 获取实体属性对应的字段名");
    method.addJavaDocLine("*/");
    method.setName("getFieldNameByPropName");
    method.addParameter(new Parameter(new FullyQualifiedJavaType(String.class.getName()), "propertyName"));
    method.addBodyLine("String result = \"\";");
    method.addBodyLine("switch (propertyName) {");

    List<IntrospectedColumn> cols = introspectedTable.getAllColumns();
    StringBuilder sb = new StringBuilder();
    for (IntrospectedColumn col : cols) {
      String colName = col.getActualColumnName();
      String alias = col.getTableAlias();
      String prefix = "";
      if (alias != null && alias.length() > 0) {
        prefix = alias + ".";
      }

      sb.setLength(0);
      sb.append("case \"");
      sb.append(col.getJavaProperty());
      sb.append("\":");
      method.addBodyLine(sb.toString());

      sb.setLength(0);
      sb.append("result = \"");
      sb.append(prefix);
      sb.append(colName);
      sb.append("\";");
      method.addBodyLine(sb.toString());
      method.addBodyLine("break;");
    }

    method.addBodyLine("default:");
    method.addBodyLine("result = \"\";");
    method.addBodyLine("}");

    method.addBodyLine("return result;");

    topLevelClass.getMethods().add(0, method);
  }
}
