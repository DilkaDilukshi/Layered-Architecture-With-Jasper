/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.dao;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import lk.ijse.thogakade.dto.SuperDTO;

/**
 *
 * @author Dil
 */
public interface SuperDAO<T extends SuperDTO> {

    public void setConnection(Connection connection);

    public Connection getConnection();

    public default boolean add(T dto) throws Exception {
        Class<? extends SuperDAO> aClass = this.getClass();

        Field constTabelName = aClass.getDeclaredField("TABLE_NAME");
        constTabelName.setAccessible(true);
        String tableName = (String) constTabelName.get(this);

        Connection connection = this.getConnection();
        Class<? extends SuperDTO> aClass1 = dto.getClass();
        Field[] declaredFields = aClass1.getDeclaredFields();
        String sqlStm = "INSERT INTO " + tableName + " VALUES(";
        for (int i = 1; i < declaredFields.length; i++) {
            sqlStm += "?,";
        }
        sqlStm += "?)";

        PreparedStatement pst = connection.prepareStatement(sqlStm);
        int i = 1;

        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            Object value = declaredField.get(dto);
            pst.setObject(i, value);
            i++;
        }

        int result = pst.executeUpdate();

        return result > 0;
    }

    public default boolean update(T dto) throws Exception {
        Class<? extends SuperDAO> aClass = this.getClass();

        Field constTabelName = aClass.getDeclaredField("TABLE_NAME");
        constTabelName.setAccessible(true);
        String tableName = (String) constTabelName.get(this);

        Connection connection = this.getConnection();

        Class<? extends SuperDTO> aClass1 = dto.getClass();
        Field[] declaredFields = aClass1.getDeclaredFields();
        Statement statement = connection.createStatement();
        ResultSet rstColoumnHedding = statement.executeQuery("desc " + tableName + " ;");

        rstColoumnHedding.next();
        String primaryField = rstColoumnHedding.getString(1);

        String sqlStm = "UPDATE " + tableName + " set ";

        while (rstColoumnHedding.next()) {
            sqlStm += (rstColoumnHedding.getString(1) + " = ?,");
        }
        sqlStm = sqlStm.substring(0, sqlStm.length() - 1);
        sqlStm += " WHERE " + primaryField + "=?;";
        PreparedStatement pst = connection.prepareStatement(sqlStm);
        int i = 4;

        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            Object value = declaredField.get(dto);
            if (i == 4) {
                pst.setObject(i, value);
                i = 0;
            } else {
                pst.setObject(i, value);
            }
            i++;
        }
        int result = pst.executeUpdate();

        return result > 0;

    }

    public default boolean delete(T dto) throws Exception {
        Class<? extends SuperDAO> aClass = this.getClass();

        Field constTabelName = aClass.getDeclaredField("TABLE_NAME");
        constTabelName.setAccessible(true);
        String tableName = (String) constTabelName.get(this);

        Connection connection = this.getConnection();

        Statement statement = connection.createStatement();
        ResultSet rstColoumnHedding = statement.executeQuery("desc " + tableName + " ;");

        rstColoumnHedding.next();
        String primaryField = rstColoumnHedding.getString(1);
        Class<? extends SuperDTO> aClass1 = dto.getClass();
        Field[] declaredFields = aClass1.getDeclaredFields();
        declaredFields[0].setAccessible(true);
        String id = declaredFields[0].get(dto).toString();
        
        String sqlStm = "DELETE FROM " + tableName + " WHERE " + primaryField ;
        sqlStm+="= '"+id+"'";
        Statement st = connection.createStatement();
        int result = st.executeUpdate(sqlStm);

        return result > 0;
    }

    public default T get(T dto) throws Exception{
        Class<? extends SuperDAO> aClass = this.getClass();

        Field constTabelName = aClass.getDeclaredField("TABLE_NAME");
        constTabelName.setAccessible(true);
        String tableName = (String) constTabelName.get(this);

        Connection connection = this.getConnection();

        Statement statement = connection.createStatement();
        ResultSet rstColoumnHedding = statement.executeQuery("desc " + tableName + " ;");

        rstColoumnHedding.next();
        String primaryField = rstColoumnHedding.getString(1);
        Class<? extends SuperDTO> aClass1 = dto.getClass();
        Field[] declaredFields = aClass1.getDeclaredFields();
        declaredFields[0].setAccessible(true);
        String id = declaredFields[0].get(dto).toString();
        
        String sqlStm = "Select * FROM " + tableName + " WHERE " + primaryField +"= '"+id+"'";
        Statement st = connection.createStatement();
        ResultSet rset = st.executeQuery(sqlStm);
        rset.next();
        int i=1;
        for(Field f: declaredFields){
            f.setAccessible(true);
            Object object = rset.getObject(i);
            if (object instanceof BigDecimal){
                BigDecimal b = (BigDecimal) object;
                f.set(dto, b.doubleValue());
            }else{
                f.set(dto, object);
            }
            i++;
        }
        
        return dto;
    }

    public  ArrayList<T> getAll() throws Exception;
//        Class<? extends SuperDAO> aClass = this.getClass();
//
//        Field constTabelName = aClass.getDeclaredField("TABLE_NAME");
//        constTabelName.setAccessible(true);
//        String tableName = (String) constTabelName.get(this);
//
//        Connection connection = this.getConnection();
//        
//        // Hodatama Reflection
//        Type t=aClass.getInterfaces()[0].getGenericInterfaces()[0];
//        ParameterizedType pt = (ParameterizedType) t;
//        Class dtoTemp = (Class) pt.getActualTypeArguments()[0];
//        T dto = (T) dtoTemp.newInstance();
//
//        Class<? extends SuperDTO> aClassDTO = dto.getClass();
//        Field[] declaredFields = aClassDTO.getDeclaredFields();

    }

//    public default boolean update(Tables table,T t)throws Exception{
//        Connection connection=ConnectionFactory.getInstance().getConnection();
//        Class<? extends SuperDTO> aClass = t.getClass();
//        Field[] declaredFields;
//        declaredFields = aClass.getDeclaredFields();
//        java.sql.Statement stm = connection.createStatement();
//        ResultSet rst = stm.executeQuery("desc "+table.getTableName()+" ;");
//        String sqlStm="UPDATE "+table.getTableName()+" set ";
//        rst.next();
//        String primaryName=rst.getString(1);
//        while (rst.next()) {            
//            String s = rst.getString(1);
//            sqlStm+=s+"= ?, ";
//        }
//        sqlStm+="\b WHERE "+primaryName+"=?";
//        System.out.println(sqlStm);
//        
//        PreparedStatement ps=connection.prepareStatement(sqlStm);
//        declaredFields[0].setAccessible(true);
//        ps.setObject(4, declaredFields[0].get(t));
//        declaredFields[1].setAccessible(true);
//        ps.setObject(1, declaredFields[1].get(t));
//        declaredFields[2].setAccessible(true);
//        ps.setObject(2, declaredFields[2].get(t));
//        declaredFields[3].setAccessible(true);
//        ps.setObject(3, declaredFields[3].get(t).toString());
//        for(Field f:declaredFields){
//            System.out.println(f.get(t));
//        }
//        
//        int result = ps.executeUpdate();
//        connection.close();
//        
//        return 0<result;
//    }
//    public default boolean delete(Tables table,T t)throws Exception{
//        Connection connection=ConnectionFactory.getInstance().getConnection();
//        Class<? extends SuperDTO> aClass = t.getClass();
//        Field[] declaredFields;
//        declaredFields = aClass.getDeclaredFields();
//        Statement stm = connection.createStatement();
//        ResultSet rst = stm.executeQuery("desc "+table.getTableName()+" ;");
//        rst.next();
//        String primaryName=rst.getString(1);
//        
//        String sqlStm="DELETE FROM "+table.getTableName()+" WHERE "+primaryName+"='";
//        declaredFields[0].setAccessible(true);
//        sqlStm+=declaredFields[0].get(t).toString()+"'";
//        Statement stm2=connection.createStatement();
//        int result=stm2.executeUpdate(sqlStm);
//        connection.close();
//        
//        
//        return result>0;
//    }
//    public default T get(Tables table,T t)throws Exception{
//        Connection connection=ConnectionFactory.getInstance().getConnection();
//        Class<? extends SuperDTO> aClass = t.getClass();
//        Field[] declaredFields;
//        declaredFields = aClass.getDeclaredFields();
//        Statement stm = connection.createStatement();
//        ResultSet rst1 = stm.executeQuery("desc "+table.getTableName()+" ;");
//        rst1.next();
//        String primaryName;
//        primaryName = rst1.getString(1);
//        
//        String sqlStm="SELECT * FROM "+table.getTableName()+" WHERE "+primaryName+"='";
//        declaredFields[0].setAccessible(true);        
//        sqlStm+=declaredFields[0].get(t).toString()+"'";
//        Statement stm2=connection.createStatement();
//        ResultSet rst = stm2.executeQuery(sqlStm);
//        rst.next();
//        int i=0;
//        for(Field field:declaredFields){
//            field.set(t, rst.getObject(i));
//        }
//        return t;
//    }

