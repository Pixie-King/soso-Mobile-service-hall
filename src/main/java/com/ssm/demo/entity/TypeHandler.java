package com.ssm.demo.entity;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TypeHandler extends BaseTypeHandler<ServicePackage> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, ServicePackage servicePackage, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, servicePackage.toString());
    }
    @Override
    public ServicePackage getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String str = resultSet.getString(s);
        if(str.equals("TalkPackage"))return new TalkPackage();
        if (str.equals("NetPackage"))return new NetPackage();
        if(str.equals("SuperPackage"))return new SuperPackage();
        return null;
    }

    @Override
    public ServicePackage getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    @Override
    public ServicePackage getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
