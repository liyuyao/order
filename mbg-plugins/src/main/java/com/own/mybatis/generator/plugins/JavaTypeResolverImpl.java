package com.own.mybatis.generator.plugins;

import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

import java.math.BigDecimal;
import java.sql.Types;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.IntrospectedColumn;

public class JavaTypeResolverImpl extends JavaTypeResolverDefaultImpl {
    public JavaTypeResolverImpl() {
        super();
        // System.out.println("Gen TEST !!!!!");
    }

    @Override
    public FullyQualifiedJavaType calculateJavaType(IntrospectedColumn introspectedColumn) {
        FullyQualifiedJavaType result = super.calculateJavaType(introspectedColumn);
        String jdbcType = this.calculateJdbcTypeName(introspectedColumn);

        if (jdbcType.equals("DECIMAL")) {
            if (introspectedColumn.getLength() == 1 && introspectedColumn.getScale() == 0) {
                // number(1) 映射为 Boolean
                return new FullyQualifiedJavaType(Boolean.class.getName());
            } else if (introspectedColumn.getLength() > 1 && introspectedColumn.getScale() == 0) {
                // 不带小数的映射为 Integer
                return new FullyQualifiedJavaType(Integer.class.getName());
            } else {
                // 小数统一为 Decimal 类型
                return new FullyQualifiedJavaType(BigDecimal.class.getName());
            }
        }

        // System.out.println(introspectedColumn.getActualColumnName() + " javaType: " +
        // result.getShortName() + " scale:"
        // + introspectedColumn.getScale() + " len: " + introspectedColumn.getLength() +
        // " jdbcType: " + jdbcType);

        return result;
    }

    /**
     * 决定 Mapper 的 jdbcType
     */
    @Override
    public String calculateJdbcTypeName(IntrospectedColumn introspectedColumn) {
        String result = super.calculateJdbcTypeName(introspectedColumn);

        if (result.equals("DATE") || result.equals("TIME") || result.equals("TIMESTAMP")
                || result.equals("TIME_WITH_TIMEZONE") || result.equals("TIMESTAMP_WITH_TIMEZONE")) {
            // 日期类型统一为 TIMESTAMP
            return "TIMESTAMP";
        } else if (result.equals("TINYINT") || result.equals("SMALLINT") || result.equals("INTEGER")
                || result.equals("BIGINT") || result.equals("FLOAT") || result.equals("REAL") || result.equals("DOUBLE")
                || result.equals("NUMERIC") || result.equals("DECIMAL")) {
            // 数值统一为 DECIMAL
            return "DECIMAL";
        } else {
            return result;
        }

        // System.out.println(introspectedColumn.getActualColumnName() + " JdbcType: "
        // + getJdbcTypeName(introspectedColumn.getJdbcType()) + " <==> " + result);
    }

    private String getJdbcTypeName(int jdbcJype) {
        switch (jdbcJype) {
        case Types.BIT:
            return "Types." + "BIT";
        case Types.TINYINT:
            return "Types." + "TINYINT";
        case Types.SMALLINT:
            return "Types." + "SMALLINT";
        case Types.INTEGER:
            return "Types." + "INTEGER";
        case Types.BIGINT:
            return "Types." + "BIGINT";
        case Types.FLOAT:
            return "Types." + "FLOAT";
        case Types.REAL:
            return "Types." + "REAL";
        case Types.DOUBLE:
            return "Types." + "DOUBLE";
        case Types.NUMERIC:
            return "Types." + "NUMERIC";
        case Types.DECIMAL:
            return "Types." + "DECIMAL";
        case Types.CHAR:
            return "Types." + "CHAR";
        case Types.VARCHAR:
            return "Types." + "VARCHAR";
        case Types.LONGVARCHAR:
            return "Types." + "LONGVARCHAR";
        case Types.DATE:
            return "Types." + "DATE";
        case Types.TIME:
            return "Types." + "TIME";
        case Types.TIMESTAMP:
            return "Types." + "TIMESTAMP";
        case Types.BINARY:
            return "Types." + "BINARY";
        case Types.VARBINARY:
            return "Types." + "VARBINARY";
        case Types.LONGVARBINARY:
            return "Types." + "LONGVARBINARY";
        case Types.NULL:
            return "Types." + "NULL";
        case Types.OTHER:
            return "Types." + "OTHER";
        case Types.JAVA_OBJECT:
            return "Types." + "JAVA_OBJECT";
        case Types.DISTINCT:
            return "Types." + "DISTINCT";
        case Types.STRUCT:
            return "Types." + "STRUCT";
        case Types.ARRAY:
            return "Types." + "ARRAY";
        case Types.BLOB:
            return "Types." + "BLOB";
        case Types.CLOB:
            return "Types." + "CLOB";
        case Types.REF:
            return "Types." + "REF";
        case Types.DATALINK:
            return "Types." + "DATALINK";
        case Types.BOOLEAN:
            return "Types." + "BOOLEAN";
        // ------------------------- JDBC 4.0 ----------------------------------- ;
        case Types.ROWID:
            return "Types." + "ROWID";
        case Types.NCHAR:
            return "Types." + "NCHAR";
        case Types.NVARCHAR:
            return "Types." + "NVARCHAR";
        case Types.LONGNVARCHAR:
            return "Types." + "LONGNVARCHAR";
        case Types.NCLOB:
            return "Types." + "NCLOB";
        case Types.SQLXML:
            return "Types." + "SQLXML";
        // --------------------------JDBC 4.2 ----------------------------- ;
        case Types.REF_CURSOR:
            return "Types." + "REF_CURSOR";
        case Types.TIME_WITH_TIMEZONE:
            return "Types." + "TIME_WITH_TIMEZONE";
        case Types.TIMESTAMP_WITH_TIMEZONE:
            return "Types." + "TIMESTAMP_WITH_TIMEZONE";
        default:
            return "";
        }
    }
}