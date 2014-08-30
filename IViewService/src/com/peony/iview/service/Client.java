package com.peony.iview.service;

import org.apache.thrift.TException;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.protocol.TBinaryProtocol;

/**
 * IView客户端. 使用该类与服务器进行交互并获得各种服务.
 * 在获取各种服务前请首先调用connect函数与服务器建立连接.
 * 本类中所有与服务器之间通讯的操作均为同步操作. 即服务器返回结果后,相应函数才返回.
 * 因此为了避免服务器由于长时间不返回结果造成的客户端阻塞. 请调用setTimeout设置超时时间.
 */
public class Client {

    /**
     * 建立同服务器之间的连接.
     * 注意在该操作中设置的timeout值,会对后续与服务器交互操作的超时时间产生影响.
     * 例如调用本函数时设置timeout为3000,后续的login或者logout等与服务器交互操作的超时时间为3000.
     * 如想在连接完成后修改操作超时时间，请调用setTimeout函数.
     * 如果在调用本函数后没有调用close函数再次调用本函数,本函数的语义为在内部调用close关闭连接,
     * 然后建立新的连接.
     * @param host 服务器主机地址. 可以是ip、主机名称或者域名.
     * @param port 服务器主机监听端口. IView服务器的默认端口为9090.
     * @param timeout 操作超时时间. 单位为ms.
     * @throws TException 与服务器建立连接失败时抛出该异常.
     */
    public void connect(String host, int port, int timeout) throws TException {
        close();
        transport = new TSocket(host, port, timeout);
        transport.open();
        TProtocol protocol = new TBinaryProtocol(transport);
        client = new IViewService.Client(protocol);
    }

    /**
     * 关闭与服务器之间的连接.
     */
    public void close() {
        if (!isConnected()) return;
        transport.close();
        transport = null;
        client = null;
        userId = -1;
    }

    /**
     * 设置操作超时时间.
     * @param timeout 操作超时时间. 单位为ms.
     */
    public void setTimeout(int timeout) {
        transport.setTimeout(timeout);
    }

    /**
     * 判断是否与服务器之间建立了连接.
     * @return true表示以建立连接, false表示未建立连接.
     */
    public boolean isConnected() {
        return null != transport;
    }

    /**
     * 登陆.
     * @param loginType 登陆的类型. 支持APP、QQ以及微博登陆.
     * @param user 登陆用户名.
     * @param password 登陆密码.
     * @throws TException 与服务器之间通讯失败时返回该异常.
     * @throws LoginFailedException 服务器认证失败时返回该异常.
     */
    public void login(LoginType loginType, String user, String password) throws TException,
            LoginFailedException {
        userId = client.login(loginType, user, password);
        if (0 < userId) return;
        if (-1 == userId) throw new LoginFailedException("用户不存在");
        else if (-2 == userId) throw new LoginFailedException("密码错误");
        else if (-3 == userId) throw new LoginFailedException("服务器内部错误");
        throw new LoginFailedException("暂不支持该类型登陆");
    }

    /**
     * 注销.
     * @throws TException 与服务器之间通讯失败时返回该异常.
     */
    public void logout() throws TException {
        client.logout();
        userId = -1;
    }

    TSocket transport = null;
    IViewService.Client client = null;
    long userId = -1;
}
