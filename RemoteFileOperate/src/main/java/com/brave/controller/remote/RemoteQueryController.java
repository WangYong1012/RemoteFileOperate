package com.brave.controller.remote;

import com.brave.controller.BaseController;
import com.brave.controller.common.FTPClientFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpDirEntry;
import sun.net.ftp.FtpProtocolException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Api("查询文件列表")
@Controller
@RequestMapping("/remote")
public class RemoteQueryController extends BaseController
{
    @Autowired
    private FTPClientFactory factory;
    
    
    @ApiOperation("遍历FTP服务器文件")
    @RequestMapping(value = "query", method = RequestMethod.POST, produces = "application/json")
    public String query() throws IOException, FtpProtocolException
    {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        FtpClient client = factory.getClientInstance();
        if(null == client)
        {
            return fail(resultMap, "FTP服务器的验证未通过，请检查对应的服务器地址、用户名、密码、端口等参数.");
        }
        
        
        Iterator<FtpDirEntry> dirs = client.listFiles("");
        
        while(dirs.hasNext())
        {
            FtpDirEntry dir = dirs.next();
            
        }
        return success(resultMap, "遍历成功");
    }
}