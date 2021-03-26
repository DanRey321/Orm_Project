package Manager;
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

public class EntityManager<O> {


    public void insert(O o) throws SQLException, IllegalAccessException {
        Insert in = new Insert(o);
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

    public void update(O o) throws IllegalAccessException {
        Update update = new Update(o);
        String sql = update.updateQuery();
        int size = o.getClass().getDeclaredFields().length;
        int index = 1;
        try{
            ConnectionSession session = new ConnectionSession();
            Connection connection = session.getActiveConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            Field[] fields = o.getClass().getDeclaredFields();
            for(int i = 0; i < size ; i++){
                fields[i].setAccessible(true);
                pstmt.setObject(index, fields[i].get(o));
                index++;
            }
            pstmt.setObject(index, fields[0].get(o));
            System.out.println(pstmt.toString());
            pstmt.executeUpdate();
            session.close();


        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    public void delete(O o){
        Delete de = new Delete(o);
        String sql = de.deleteQuery();
        int index = 1;

        try{
            ConnectionSession session = new ConnectionSession();
            Connection connection = session.getActiveConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);

            Field[] fields = o.getClass().getDeclaredFields();
            fields[0].setAccessible(true);
            pstmt.setObject(index, fields[0].get(o));

            System.out.println(pstmt.toString());
            pstmt.executeUpdate();
            session.close();

        }catch(SQLException | IllegalAccessException e){
            e.printStackTrace();
        }

    }

    public void deleteByValue(O o) throws IllegalAccessException {
        Delete de = new Delete(o);
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


    public void read(O o){
        Select select = new Select(o);
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

    public void readByValue(O o) throws IllegalAccessException {
        Select select = new Select(o);
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
