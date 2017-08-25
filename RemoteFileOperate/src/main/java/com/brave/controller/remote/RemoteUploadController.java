package com.brave.controller.remote;

import com.brave.controller.BaseController;
import com.brave.controller.common.FTPClientFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpProtocolException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Api("远程文件上传服务")
@RequestMapping("/remote")
@Controller
public class RemoteUploadController extends BaseController
{
    @Autowired
    private FTPClientFactory factory;

    /**
     * 2017年8月25日 10:02:53
     * 远程文件上传服务
     * @param localFilePath 本地文件路径
     * @param remoteFilePath 远程文件路径
     * @return
     */
    @ApiOperation("上传文件")
    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "application/json")
    public String uplaod(
            @RequestParam("localFilePath") String localFilePath, 
            @RequestParam("remoteFilePath") String remoteFilePath)
    {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try
        {
            //获取FTP服务的实例
            FtpClient client = factory.getClientInstance();
            if(null == client)
            {
                return fail(resultMap, "FTP服务器的验证未通过，请检查对应的服务器地址、用户名、密码、端口等参数.");
            }
            //如果成功获取到实例，代码继续执行
            OutputStream os = null;
            FileInputStream fis = null;
            try {
                // 将ftp文件加入输出流中。输出到ftp上 
                os = client.putFileStream(remoteFilePath);
                File file = new File(localFilePath);

                // 创建一个缓冲区  
                fis = new FileInputStream(file);
                byte[] bytes = new byte[1024];
                int c;
                while((c = fis.read(bytes)) != -1){
                    os.write(bytes, 0, c);
                }
            } catch (FtpProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if(os!=null) {
                        os.close();
                    }
                    if(fis!=null) {
                        fis.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }catch(Exception ex)
        {
            //异常错误捕捉
            ex.printStackTrace();
            return fail(resultMap, ex.getMessage());
        }
        return success(resultMap, "上传成功!");
    }
}