package com.brave.controller.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sun.net.ftp.FtpClient;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

@Component
public class FTPClientFactory
{
    //读取参数配置的主机地址
    @Value("${ftp.server.hostname}")
    private String hostName;
    //读取参数配置的用户名
    @Value("${ftp.server.username}")
    private String userName;
    //读取参数配置的密码
    @Value("${ftp.server.password}")
    private String passWord;
    //读取参数配置的端口号
    @Value("${ftp.server.port}")
    private int port;

    /**
     * 2017年8月25日 09:47:50
     * 获取实例
     * @return
     */
    public FtpClient getClientInstance()
    {
        try
        {
            SocketAddress address = new InetSocketAddress(hostName, port);
            FtpClient client = FtpClient.create();
            client.connect(address);
            client.login(userName, passWord.toCharArray());
            return client;
        }catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
}