package com.jys.weibo.controller;

import com.jys.weibo.service.WbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Component
@Controller
public class ImgUtil {
    @Autowired
    public WbService wbItemService;

    public FileInputStream query_getPhotoImageBlob(String adress) {
        FileInputStream is = null;
        File filePic = new File(adress);
        try {
            is = new FileInputStream(filePic);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return is;

    }

    /*
     * 获取图片并显示在页面
     * @return
     * @throws SQLException
     */
    @RequestMapping("/queryPic/{type}/{address}")
    public void queryPic(@PathVariable("address") String address, @PathVariable("type") int type,
                         HttpServletRequest request,
                         HttpServletResponse response) throws IOException {

        if (address != null) {
            //从数据库查询地址
            String address1= "";
            if(type==1){
                response.setContentType("image/jpeg");
                address1 = wbItemService.findImagePathById(Integer.valueOf(address));
            }
            if(type==2){
                response.setContentType("application/octet-stream");
                address1 = wbItemService.findVideoPathById(Integer.valueOf(address));
            }
            FileInputStream is = this.query_getPhotoImageBlob(address1);//"E:\\idea_workspace\\123.gif"

            if (is != null) {
                int i = is.available(); // 得到文件大小  
                byte data[] = new byte[i];
                is.read(data); // 读数据  
                is.close();
                response.setContentType("image/jpeg"); // 设置返回的文件类型  
                OutputStream toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象  
                toClient.write(data); // 输出数据  
                toClient.close();
            }
        }
    }
}