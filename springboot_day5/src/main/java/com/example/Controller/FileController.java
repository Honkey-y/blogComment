package com.example.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Controller
@RequestMapping("file")
public class FileController {

    /*
    * 用来测试文件上传
    * */
    @RequestMapping("upload")
    public String upload(MultipartFile file, HttpServletRequest request) throws IOException { //定义：接收文件对象multipartfile  file变量名要与form中input type=“file”标签的name属性一直

        //文件名
        String originalFilename = file.getOriginalFilename();
        log.println("文件名：" + file.getOriginalFilename());
        log.println("文件大小：" + file.getSize());
        log.println("文件类型：" + file.getContentType());

        //1.根据相对 上传 “upload” 获取绝对路径（真实路径） /user/桌面...    服务器：/home/springboot_day5....
        String realPath = request.getSession().getServletContext().getRealPath("/upload");
        log.println("获取绝对路径：" + realPath);

        //2.上传文件 参数1：将文件写入到哪个目录
        //修改文件名

        String ext = originalFilename.substring(originalFilename.lastIndexOf("."));       //找到最后的（.） 截断文件名
        String NewFileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + ext;
        file.transferTo(new File(realPath,NewFileName));

        return "redirect:/upload.jsp";
    }

}
