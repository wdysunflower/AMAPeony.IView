package com.peony.iview.service;

import java.sql.SQLException;

import org.apache.thrift.TException;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

import com.peony.iview.service.db.UserTable;
import com.peony.iview.service.db.UserRecord;
import com.peony.iview.service.db.ConnectionPool;

/**
 * Created by Shining100 on 2014-08-23.
 */
public class Server {

    static TServer server;
    static Thread thread;

    public static void main(String[] args) {
        try {
            start();
            System.in.read();
            stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void start() {
        try {
            ConnectionPool.getInstance().init("127.0.0.1", 3306, "root", "123456");
            IViewServiceHandler handler = new IViewServiceHandler();
            final IViewService.Processor processor = new IViewService.Processor<
                    IViewServiceHandler>(handler);
            final Runnable target = new Runnable() {
                @Override
                public void run() {
                    try {
                        TServerTransport serverTransport = new TServerSocket(9090);
                        server = new TSimpleServer(new Args(serverTransport).processor(processor));

                        // Use this for a multithreaded server
                        // TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));

                        System.out.println("Starting the server...");
                        server.serve();
                        System.out.println("Stopping the server...");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            thread = new Thread(target);
            thread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stop() throws InterruptedException {
        server.stop();
        thread.join();
    }

    static class IViewServiceHandler implements IViewService.Iface {

        @Override
        public long login(LoginType loginType, String user, String password) throws TException {
            if (LoginType.APP != loginType) return -4;

            long userId;
            try {
                UserTable table = new UserTable();
                UserRecord record = table.getUser(user);
                if (null == record) return -1;
                if (!password.equals(record.getPassword())) return -2;
                userId = record.getId();
                table.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return -3;
            }

            return userId;
        }

        @Override
        public void logout() throws TException {

        }
    }
}
