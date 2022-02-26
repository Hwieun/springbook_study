package com.springbook.TobySpring.one.three;

import org.springframework.dao.EmptyResultDataAccessException;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// JdbcTemplate 가 Spring이 제공하는 템플릿/콜백 패턴이 적용된 Jdbc 코드용 기본 템플릿
public class JdbcContext {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     *
     * @param stmt
     *  stmt가 일종의 callBack이 된다
     * @throws SQLException
     */
    public void executeUpdate(StatementStrategy stmt) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = dataSource.getConnection();
            ps = stmt.makePreparedStatement(c);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
        finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {

                }
            }
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {

                }
            }
        }
    }

    public <T> T executeQuery(StatementStrategy stmt, Class<T> tClass) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        T result = null;
        try {
            c = dataSource.getConnection();
            ps = stmt.makePreparedStatement(c);
            rs = ps.executeQuery();
            if(!rs.next()) throw new EmptyResultDataAccessException(1);
            result = rs.getObject(1, tClass);
        } catch (SQLException e) {
            throw e;
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {

                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {

                }
            }
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {

                }
            }
        }
        return result;
    }

    public void executeSql(String sql) throws SQLException {
        executeUpdate(c -> c.prepareStatement(sql));
    }

}
