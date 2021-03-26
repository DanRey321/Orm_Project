package Queries;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;

public class Delete<T> {

    private T t = null;
    private PreparedStatement pstmt = null;

    public Delete(T t, PreparedStatement pstmt) {
        this.t = t;
        this.pstmt = pstmt;
    }

    public Delete(T t) {
        this.t = t;
    }

    public String deleteQuery() {
        int size = t.getClass().getDeclaredFields().length;
        StringBuilder sql = new StringBuilder();
        sql.append("delete from ");
        sql.append(t.getClass().getSimpleName().toLowerCase() + " " );
        sql.append("where ");
        Field[] fields = t.getClass().getDeclaredFields();
        sql.append(fields[0].getName()).append( " = ");
        sql.append("?");
        System.out.println(sql.toString());

        return sql.toString();
    }

    public String deleteQueryByValue() throws IllegalAccessException {
        int size = t.getClass().getDeclaredFields().length;
        StringBuilder sql = new StringBuilder();
        sql.append("delete from ");
        sql.append(t.getClass().getSimpleName().toLowerCase() + " " );
        sql.append("where ");
        Field[] fields = t.getClass().getDeclaredFields();
        for(int i = 1; i < size - 1; i++){
            fields[i].setAccessible(true);
            System.out.println(fields[i].get(t));
            if(!fields[i].get(t).equals("null") ) {
                sql.append(fields[i].getName() + " = '");
                sql.append((fields[i].get(t)));
                sql.append("'");
                System.out.println(sql.toString());
                return sql.toString();
            }
        }

        //System.out.println(sql.toString());

        return sql.toString();
    }


}
