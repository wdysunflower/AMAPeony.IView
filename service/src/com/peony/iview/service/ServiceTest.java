package com.peony.iview.service;

import org.junit.Test;
import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.is;

import com.peony.iview.service.db.UserTable;
import com.peony.iview.service.db.UserRecord;

public class ServiceTest {

    @Test
    public void test() throws Exception {
        Server.start();
        UserTable table = new UserTable();
        UserRecord record = new UserRecord();
        final String admin = "admin";
        record.setName(admin);
        record.setPassword(admin);
        table.delete(record.getName());
        table.insert(record);

        Client client = new Client();
        client.connect("localhost", 9090, 30000);
        assertNotNull(client.transport);
        assertNotNull(client.client);

        try {
            client.login(LoginType.QQ, admin, admin);
            fail();
        } catch (LoginFailedException e) {
            assertThat(e.getMessage(), is("暂不支持该类型登陆"));
        }
        try {
            client.login(LoginType.WEIBO, admin, admin);
            fail();
        } catch (LoginFailedException e) {
            assertThat(e.getMessage(), is("暂不支持该类型登陆"));
        }
        try {
            client.login(LoginType.APP, "", "");
            fail();
        } catch (LoginFailedException e) {
            assertThat(e.getMessage(), is("用户不存在"));
        }
        try {
            client.login(LoginType.APP, admin, "");
            fail();
        } catch (LoginFailedException e) {
            assertThat(e.getMessage(), is("密码错误"));
        }
        client.login(LoginType.APP, admin, admin);
        assertNotEquals(-1, client.userId);
        client.logout();
        assertEquals(-1, client.userId);
        client.close();
        assertNull(client.transport);
        assertNull(client.client);

        table.delete(record.getName());
        table.close();
        Server.stop();
    }
}