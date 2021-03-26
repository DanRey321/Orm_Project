package Queries;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;

public class Select<T>{
    private T t = null;
    private PreparedStatement pstmt = null;

    public Select(T t, PreparedStatement pstmt) {
        this.t = t;
        this.pstmt = pstmt;
    }

    public Select(T t) {
        this.t = t;
    }

    public String selectQuery(){
        int size = t.getClass().getDeclaredFields().length;
        StringBuilder sql = new StringBuilder();
        sql.append("select * from ");
        sql.append(t.getClass().getSimpleName().toLowerCase()).append(" ");
        System.out.println(sql.toString());

        return sql.toString();
    }

    public String selectQueryByColumn() throws IllegalAccessException {
        int size = t.getClass().getDeclaredFields().length;
        StringBuilder sql = new StringBuilder();
        sql.append("select * from ");
        sql.append(t.getClass().getSimpleName().toLowerCase()).append(" ");
        sql.append(" where ");
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
        System.out.println(sql.toString());

        return sql.toString();
    }


}
