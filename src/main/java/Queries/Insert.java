package Queries;
import Util.ConnectionSession;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Locale;


public class Insert<T> {
    private T t = null;
    private PreparedStatement pstmt = null;

    public Insert(T t, PreparedStatement pstmt) {
        this.t = t;
        this.pstmt = pstmt;
    }

    public Insert(T t) {
        this.t = t;
    }

    public String insertQuery() throws SQLException, IllegalAccessException {
        int size = t.getClass().getDeclaredFields().length;
        StringBuilder string = new StringBuilder();
        string.append("insert into ");
        string.append(t.getClass().getSimpleName().toLowerCase() + "( ");
        Field[] fields = t.getClass().getDeclaredFields();
        for(int i = 1; i < size - 1; i++){
            string.append(fields[i].getName() + ", ");
        }
        string.append(fields[size - 1].getName());
        string.append(") values (");
        for(int i = 1; i < size - 1; i++){
            string.append("?, ");
        }
        string.append("?)");


        //System.out.println(string.toString());
        return string.toString();
    }

    public PreparedStatement insertStatement( PreparedStatement pstmt) throws IllegalAccessException, SQLException {
        int index = 1;
        int size = t.getClass().getDeclaredFields().length;


        Field[] fields = t.getClass().getDeclaredFields();
        for(int i = 1; i < size ; i++){
            fields[i].setAccessible(true);
            pstmt.setObject(index, fields[i].get(t));
            index++;
        }

        System.out.println(pstmt.toString());



        return pstmt;
    }


}
