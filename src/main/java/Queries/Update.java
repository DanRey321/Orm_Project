package Queries;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;

public class Update<T> {
    private T t = null;
    private PreparedStatement pstmt = null;

    public Update(T t, PreparedStatement pstmt) {
        this.t = t;
        this.pstmt = pstmt;
    }

    public Update(T t) {
        this.t = t;
    }

    public String updateQuery() throws IllegalAccessException {
        int size = t.getClass().getDeclaredFields().length;
        StringBuilder sql = new StringBuilder();
        sql.append("update ");
        sql.append(t.getClass().getSimpleName().toLowerCase());
        sql.append(" set ");
        Field[] fields = t.getClass().getDeclaredFields();
        for (int i = 0; i < size - 1; i++){
            sql.append(fields[i].getName());
            sql.append(" = ");
            sql.append(" ?, ");
        }
        sql.append(fields[size - 1].getName()).append(" = ?");
        sql.append(" where ");
        sql.append(fields[0].getName()).append(" = ");
        fields[0].setAccessible(true);
        sql.append("?");




        //System.out.println(sql.toString());


        return sql.toString();
    }

}
