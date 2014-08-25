package com.peony.iview.service.db;

import java.util.LinkedList;

import java.sql.SQLException;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

public class UserTableTest {
    @Test
    public void test() throws SQLException {
        ConnectionPool.getInstance().init("127.0.0.1", 3306, "root", "123456");
        UserTable table = new UserTable();
        UserRecord record = new UserRecord();
        record.setName("user");
        record.setPassword("password");
        table.delete(record.getName());
        table.insert(record);
        UserRecord record2 = table.getUser(record.getName());
        assertNotNull(record2);
        assertThat(record.getName(), is(record2.getName()));
        assertThat(record.getPassword(), is(record2.getPassword()));
        LinkedList<UserRecord> records = table.getUsers();
        assertThat(1, is(records.size()));
        UserRecord record3 = records.getFirst();
        assertThat(record.getName(), is(record3.getName()));
        assertThat(record.getPassword(), is(record3.getPassword()));
        table.delete(record.getName());
        record2 = table.getUser(record.getName());
        records = table.getUsers();
        assertThat(true, is(records.isEmpty()));
        assertNull(record2);
        table.close();
    }
}
