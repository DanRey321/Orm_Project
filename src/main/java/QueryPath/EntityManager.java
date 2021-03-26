package QueryPath;
import Queries.Delete;
import Queries.Insert;
import Queries.Select;
import Queries.Update;
import Util.ConnectionSession;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class EntityManager<T> {


    public void insert(T t) throws SQLException, IllegalAccessException {
        Insert in = new Insert(t);
        String sql = in.insertQuery();
        //System.out.println(sql);
        try{
            ConnectionSession session = new ConnectionSession();
            Connection connection = session.getActiveConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt = in.insertStatement(pstmt);

            pstmt.executeUpdate();
            session.close();

        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    public void update(T t) throws IllegalAccessException {
        Update update = new Update(t);
        String sql = update.updateQuery();
        int size = t.getClass().getDeclaredFields().length;
        int index = 1;
        try{
            ConnectionSession session = new ConnectionSession();
            Connection connection = session.getActiveConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            Field[] fields = t.getClass().getDeclaredFields();
            for(int i = 0; i < size ; i++){
                fields[i].setAccessible(true);
                pstmt.setObject(index, fields[i].get(t));
                index++;
            }
            pstmt.setObject(index, fields[0].get(t));
            System.out.println(pstmt.toString());
            pstmt.executeUpdate();
            session.close();


        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    public void delete(T t){
        Delete de = new Delete(t);
        String sql = de.deleteQuery();
        int index = 1;

        try{
            ConnectionSession session = new ConnectionSession();
            Connection connection = session.getActiveConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);

            Field[] fields = t.getClass().getDeclaredFields();
            fields[0].setAccessible(true);
            pstmt.setObject(index, fields[0].get(t));

            System.out.println(pstmt.toString());
            pstmt.executeUpdate();
            session.close();

        }catch(SQLException | IllegalAccessException e){
            e.printStackTrace();
        }

    }

    public void deleteByValue(T t) throws IllegalAccessException {
        Delete de = new Delete(t);
        String sql = de.deleteQueryByValue();
        int index = 1;

        try{
            ConnectionSession session = new ConnectionSession();
            Connection connection = session.getActiveConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);

//            Field[] fields = t.getClass().getDeclaredFields();
//            fields[0].setAccessible(true);
//            pstmt.setObject(index, fields[0].get(t));

            //System.out.println(pstmt.toString());
            pstmt.executeUpdate();
            session.close();

        }catch(SQLException e){
            e.printStackTrace();
        }

    }


    public void read(T t){
        Select select = new Select(t);
        String sql = select.selectQuery();
        int index = 1;
        StringBuilder tableValues = new StringBuilder();

        try{
            ConnectionSession session = new ConnectionSession();
            Connection connection = session.getActiveConnection();
            //System.out.println(sql);
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet result = pstmt.executeQuery();

            while(result.next()){
                for(int i = 0; i < result.getMetaData().getColumnCount(); i++){
                    tableValues.append(result.getString(i + 1));
                    tableValues.append("   ");
                    //System.out.println("PassFor");
                }
                tableValues.append(System.lineSeparator());
            }

            System.out.println(tableValues.toString());
            //System.out.println(pstmt.toString());
            //pstmt.executeUpdate();
            session.close();

        }catch(SQLException e){
            e.printStackTrace();
        }


    }

    public void readByValue(T t) throws IllegalAccessException {
        Select select = new Select(t);
        String sql = select.selectQueryByColumn();
        int index = 1;
        StringBuilder tableValues = new StringBuilder();

        try{
            ConnectionSession session = new ConnectionSession();
            Connection connection = session.getActiveConnection();
            //System.out.println(sql);
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet result = pstmt.executeQuery();

            while(result.next()){
                for(int i = 0; i < result.getMetaData().getColumnCount(); i++){
                    tableValues.append(result.getString(i + 1));
                    tableValues.append("   ");
                    //System.out.println("PassFor");
                }
                tableValues.append(System.lineSeparator());
            }

            System.out.println(tableValues.toString());
            //System.out.println(pstmt.toString());
            //pstmt.executeUpdate();
            session.close();

        }catch(SQLException e){
            e.printStackTrace();
        }


    }

}
