package com.peony.iview.service.db;

import java.util.LinkedList;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
 * Created by Shining100 on 2014-08-24.
 */
public class UserTable {
    public UserTable() throws SQLException {
        conn = ConnectionPool.getInstance().createConnection();
    }

    public void close() throws SQLException {
        if (null != insertStmt) insertStmt.close();
        if (null != deleteStmt) deleteStmt.close();
        if (null != deleteAllStmt) deleteAllStmt.close();
        if (null != getUsersStmt) getUsersStmt.close();
        if (null != getUserStmt) getUserStmt.close();
        conn.close();
    }

    public void insert(UserRecord record) throws SQLException {
        if (null == insertStmt) insertStmt = conn.prepareStatement(insertSql);
        insertStmt.setString(1, record.getName());
        insertStmt.setString(2, record.getPassword());
        insertStmt.executeUpdate();
    }

    public void delete(String name) throws SQLException {
        if (null == deleteStmt) deleteStmt = conn.prepareStatement(deleteSql);
        deleteStmt.setString(1, name);
        deleteStmt.executeUpdate();
    }

    public void delete() throws SQLException {
        if (null == deleteAllStmt) deleteAllStmt = conn.prepareStatement(deleteAllSql);
        deleteAllStmt.executeUpdate();
    }

    public LinkedList<UserRecord> getUsers() throws SQLException {
        if (null == getUsersStmt) getUsersStmt = conn.prepareStatement(getUsersSql);
        ResultSet rs = getUsersStmt.executeQuery();
        LinkedList<UserRecord> records = new LinkedList<UserRecord>();
        while (rs.next()) {
            UserRecord record = new UserRecord();
            record.setId(rs.getLong(1));
            record.setName(rs.getString(2));
            record.setPassword(rs.getString(3));
            records.push(record);
        }
        return records;
    }

    public UserRecord getUser(String name) throws SQLException {
        if (null == getUserStmt) getUserStmt = conn.prepareStatement(getUserSql);
        getUserStmt.setString(1, name);
        ResultSet rs = getUserStmt.executeQuery();
        UserRecord record = null;
        while (rs.next()) {
            record = new UserRecord();
            record.setId(rs.getLong(1));
            record.setName(rs.getString(2));
            record.setPassword(rs.getString(3));
        }
        return record;
    }

    Connection conn;
    static final String insertSql = "insert into user (name, password) values(?, ?)";
    PreparedStatement insertStmt;
    static final String deleteSql = "delete from user where name = ?";
    PreparedStatement deleteStmt;
    static final String deleteAllSql = "delete from user";
    PreparedStatement deleteAllStmt;
    static final String getUsersSql = "select * from user";
    PreparedStatement getUsersStmt;
    static final String getUserSql = "select * from user where name = ?";
    PreparedStatement getUserStmt;
}
