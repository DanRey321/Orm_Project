package Queries;

import Util.ConnectionSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class xmlDDL {

    private static xmlDDL instance;

    public xmlDDL(){

    }

    public static xmlDDL getDaoInstance(){
        if (instance == null) {
            instance = new xmlDDL();
        }
        return instance;
    }

    public boolean remove(String tableName, int id, String value){

        String sqlQuery = "delete from Jets where jetid = ?";

        try(ConnectionSession session = new ConnectionSession()){
            Connection connection = session.getActiveConnection();
            PreparedStatement pstmt = connection.prepareStatement(sqlQuery);
            //pstmt.setString(1, value);
            pstmt.setInt(1, id);

            int i = pstmt.executeUpdate();

            session.close();


            return i > 0;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }catch(Exception b){
            //print false but still executes query.
            //b.printStackTrace();
            return false;
        }

    }

    public String getTable(String tableName){
        String sqlQuery = "select * from " + tableName ;
        //System.out.println("Pass1");

        try{
            ConnectionSession session = new ConnectionSession();
            Connection connection = session.getActiveConnection();
            //DatabaseMetaData data = connection.getMetaData();
            StringBuilder tableValues = new StringBuilder();
            //System.out.println("Pass2");

            PreparedStatement pstmt = connection.prepareStatement(sqlQuery);
            //pstmt.setString(1, tableName);
            ResultSet result = pstmt.executeQuery();
            while(result.next()){
                for(int i = 0; i < result.getMetaData().getColumnCount(); i++){
                    tableValues.append(result.getString(i + 1));
                    tableValues.append("   ");
                    //System.out.println("PassFor");
                }
                //Remove
                tableValues.append(System.lineSeparator());
            }
            //System.out.println("Pass3");
            session.close();

            return tableValues.toString();



        }catch(SQLException e){
            //e.printStackTrace();
        }

        return null;
    }

    public String getTable(String tableName,  ArrayList<String> columns, ArrayList<String> values ){
        String sqlQuery = "select * from " + tableName + " where " + columns.get(0) + " = '" + values.get(0) + "'" ;
        //System.out.println("Pass1");

        try{
            ConnectionSession session = new ConnectionSession();
            Connection connection = session.getActiveConnection();
            //DatabaseMetaData data = connection.getMetaData();
            StringBuilder tableValues = new StringBuilder();
            //System.out.println("Pass2");

            PreparedStatement pstmt = connection.prepareStatement(sqlQuery);
            //pstmt.setString(1, tableName);
            ResultSet result = pstmt.executeQuery();
            while(result.next()){
                for(int i = 0; i < result.getMetaData().getColumnCount(); i++){
                    tableValues.append(result.getString(i + 1));
                    tableValues.append("   ");
                    //System.out.println("PassFor");
                }
                //Remove
                tableValues.append(System.lineSeparator());
            }
            //System.out.println("Pass3");
            session.close();

            return tableValues.toString();



        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public boolean AddObject(String tableName, ArrayList<String> columns, ArrayList<String> values){

        try{
            ConnectionSession session = new ConnectionSession();
            Connection connection = session.getActiveConnection();
            StringBuilder insertSql = new StringBuilder("insert into " + tableName + " (");

            for (int i = 0; i < columns.size() - 1; i++){
                insertSql.append(columns.get(i));
                insertSql.append(", ");
            }
            insertSql.append(columns.get(columns.size() - 1));
            insertSql.append(") values (");
            for (int i = 0; i < values.size() - 1 ; i++){
                insertSql.append("'");
                insertSql.append(values.get(i));
                insertSql.append("'");
                insertSql.append(", ");
            }
            insertSql.append("'").append(values.get(values.size() - 1));
            insertSql.append("') ");

            PreparedStatement pstmt = connection.prepareStatement(insertSql.toString());

            System.out.println(insertSql);

            if(pstmt.executeUpdate() > 0){
                session.close();
                return true;
            }else{
                session.close();
                return false;
            }


        }catch(SQLException e){
            e.printStackTrace();
        }


        return false;
    }

    public boolean deleteValue(String tableName, ArrayList<String> columns, ArrayList<String> values){
        String sqlQuery = "delete from " + tableName +  " where " + columns.get(0) + " = ?";

        try{
            ConnectionSession session = new ConnectionSession();
            Connection connection = session.getActiveConnection();
            PreparedStatement pstmt = connection.prepareStatement(sqlQuery);
            pstmt.setString(1, values.get(0));

            session.close();
            return pstmt.executeUpdate() > 0;


        }catch(SQLException e){
            e.printStackTrace();

        }
        return false;
    }

    public boolean dropTable(String tableName){

        String sqlQuery = "drop table " + tableName;
        try{
            ConnectionSession session = new ConnectionSession();
            Connection connection = session.getActiveConnection();
            PreparedStatement pstmt = connection.prepareStatement(sqlQuery);
            int i = pstmt.executeUpdate();
            session.close();
            return i >= 0;
        }catch(SQLException e){
            //session.close();
            e.printStackTrace();
        }


        return false;
    }

    public boolean createTable(String tableName, ArrayList<String> columns, ArrayList<String> types, String constraint){

        try{
            ConnectionSession session = new ConnectionSession();
            Connection connection = session.getActiveConnection();
            StringBuilder insertSql = new StringBuilder("create table " + tableName + " ( ");
            insertSql.append(columns.get(0));
            insertSql.append(" ").append(constraint).append(", ");
            for(int i = 1; i < columns.size()-1; i++){
                insertSql.append(columns.get(i)).append(" ").append(types.get(i)).append(", ");
            }
            insertSql.append(columns.get(columns.size() - 1)).append(" ").append(types.get(columns.size()- 1) ).append(" )");

            PreparedStatement pstmt = connection.prepareStatement(insertSql.toString());

            System.out.println(insertSql);

            session.close();
            boolean check = pstmt.executeUpdate() >= 0;
            System.out.println(check);
            return check;


        }catch(SQLException e){
            e.printStackTrace();
        }


        return false;
    }

    public String getTableObject(String tableName){
        String s = System.lineSeparator();
        String sqlQuery = "select * from " + tableName ;


        try{
            ConnectionSession session = new ConnectionSession();
            Connection connection = session.getActiveConnection();
            StringBuilder tableValues = new StringBuilder();

            PreparedStatement pstmt = connection.prepareStatement(sqlQuery);

            ResultSet result = pstmt.executeQuery();
            System.out.println(tableName.toUpperCase()+ " Table: ");
            System.out.println("----------");
            int size = result.getMetaData().getColumnCount();
            ArrayList<String> arrayList = new ArrayList<>();
            while(result.next()){
                for(int i = 0; i < size; i++){
                    tableValues.append(result.getString(i + 1));
                    tableValues.append("     ");

                }

                tableValues.append(s);
            }
            for(int i = 0; i < size; i++){
                arrayList.add(result.getMetaData().getColumnName(i + 1));
                System.out.print(result.getMetaData().getColumnName(i + 1).toUpperCase() + "     ");
            }
            System.out.println("\n--------------------------------------------");

            session.close();

            return tableValues.toString();

        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }


    public String getTableObject(String tableName,  ArrayList<String> columns, ArrayList<String> values ){
        String s = System.lineSeparator();
        String sqlQuery = "select * from " + tableName + " where " + columns.get(0) + " = '" + values.get(0) + "'" ;


        try{
            ConnectionSession session = new ConnectionSession();
            Connection connection = session.getActiveConnection();
            StringBuilder tableValues = new StringBuilder();
            ArrayList<String> arrayList = new ArrayList<>();

            PreparedStatement pstmt = connection.prepareStatement(sqlQuery);
            ResultSet result = pstmt.executeQuery();
            int size = result.getMetaData().getColumnCount();
            while(result.next()){
                for(int i = 0; i < size; i++){
                    tableValues.append(result.getString(i + 1));
                    tableValues.append("   ");

                }

                tableValues.append(s);
            }
            for(int i = 0; i < size; i++){
                arrayList.add(result.getMetaData().getColumnName(i + 1));
                System.out.print(result.getMetaData().getColumnName(i + 1).toUpperCase() + "     ");
            }
            System.out.println("\n--------------------------------------------");

            session.close();

            return tableValues.toString();

        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }








}
