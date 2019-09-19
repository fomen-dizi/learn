package com.jys.weibo.controller;

import com.google.common.collect.Lists;
import com.jys.weibo.model.WbUser;
import com.jys.weibo.model.Weibo;
import com.jys.weibo.repository.WeiboRepository;
import com.jys.weibo.service.WbService;
import com.jys.weibo.service.WbUserService;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 描述：访问微博首页控制器
 */
@Controller
public class HomeController {
    @Autowired
    public WbService wbItemService;
    @Autowired
    private WeiboRepository weiboRepository;

    @RequestMapping("/wb")
    public String home(Model model, HttpServletRequest request){
        /*List<Weibo> wblist = wbItemService.getWeiboItemListList();*/
        if(request.getSession().getAttribute("user") == null){
            return "redirect:/wb1";//重定向
        }
        List<Weibo> wblist = wbItemService.findAllWb();
        model.addAttribute("wblist",wblist);
        Weibo weibo = new Weibo();
        weibo.setWbContent("ceshio");
        model.addAttribute("weibo",weibo);
        return "home";
    }

    /**
     * 插入微博
     * @param model
     * @param weibo
     * @param request
     * @return
     */
    @PostMapping("/wb/insert")
    public String insertWb(Model model, @ModelAttribute Weibo weibo,HttpServletRequest request){
        WbUser user = (WbUser)request.getSession().getAttribute("user");
        String imgpath=fileUpload(weibo.getFile());//上传图片
        String videopath=fileUpload(weibo.getFile1());//上传视频
        weibo.setWbType(1);
        weibo.setUserId(1);
        if(!imgpath.equals("null"))
            weibo.setWbImagePath(imgpath);
        if(!videopath.equals("null"))
            weibo.setWbVideoPath(videopath);
        wbItemService.insertWb(weibo);

        weibo.setId(3);
        //保存到es里
        saveWeiToEs(weibo);
        return "redirect:/wb";//重定向
    }

    @RequestMapping("/wb1")
    public String home1(Model model){
        model.addAttribute("wbUser",new WbUser());
        return "home1";
    }

    /**
     * 登录成功跳转到主界面，登录不成功就留在原页面
     * @param model
     * @param wbUser
     * @param request
     * @return
     */
    @RequestMapping("/login")
    public String loginSuccess(Model model,@ModelAttribute WbUser wbUser,HttpServletRequest request){
        WbUser wbUser1 = wbUserService.findUserByEmailOrNameOrTel(
                wbUser.getUserEmail(),wbUser.getUserName(),1,wbUser.getUserPassword());
        if(wbUser1 != null){
            request.getSession().setAttribute("user",wbUser1);
            return "redirect:/wb";//重定向
        }
        return "redirect:/wb1";//重定向
    }

    /**
     * 退出登录
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/loginout")
    public String loginout(Model model,HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return "redirect:/wb1";//重定向
    }

    @Autowired
    public WbUserService wbUserService;

    /**
     * 点击注册按钮，跳转到注册页面
     * @param model
     * @return
     */
    @RequestMapping("/signupform")
    public String signup(Model model){
        WbUser wbUser = new WbUser();
        model.addAttribute("wbUser",wbUser);
        return "signup";
    }

    /**
     * 注册用户
     * @param model
     * @param wbUser
     * @return
     */
    @PostMapping("/signup")
    public String signupSuccess(Model model,@ModelAttribute WbUser wbUser){
        wbUserService.insertWbUser(wbUser);
        return "redirect:/wb";//重定向
    }

    /*@ResponseBody
    @RequestMapping("/search1")
    public String search1(@RequestParam("data") Object data) {
        return "search";
    }*/

    /**
     * 保存到Es
     * @param weibo
     */
    public void saveWeiToEs(Weibo weibo){
        weiboRepository.save(weibo);
    }

    /**
     * 从es里查询
     * @param content
     * @param model
     * @return
     */
    @RequestMapping("/search")
    public String search(@RequestParam("name") String content,Model model) {
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("wbContent", content);
        Iterable<Weibo> list = weiboRepository.search(queryBuilder);
        List<Weibo> weiboList = Lists.newArrayList(list);
        model.addAttribute("wblist",weiboList);
        return "search";
    }

    @RequestMapping("/uploadimg")
    public String uploadimg() {
       return "uploadimg";
    }

    SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 文件上传方法
     * @param srcFile
     * @return
     */
    public String fileUpload(MultipartFile srcFile){
        //前端没有选择文件，srcFile为空
        if (srcFile.isEmpty()){
//            redirectAttributes.addFlashAttribute("message","请选择一个文件");
            return "null";
        }
        //选择了文件，开始进行上传操作
        String filepath=null;
        try {
            //构建上传目标路径,
//            File destFile=new File
//                    (ResourceUtils.getURL("classpath:").getPath());
            File destFile=new File
                    ("E:\\idea_workspace");
            if(!destFile.exists()){
                destFile=new File("");
            }
            //出处目标文件的绝对路径
            System.out.println("file path"+destFile.getAbsolutePath());
            File upload=new File(destFile.getAbsolutePath(),dateformat.format(new Date())+"/");

            String fileName= srcFile.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            fileName= UUID.randomUUID() +suffixName;
            //若目标文件夹不存在，则创建一个
            if(!upload.exists()){
                upload.mkdir();
            }
            filepath=upload.getAbsolutePath()+"\\"+fileName;
            System.out.println("完整的上传路径:"+upload.getAbsolutePath()+"\\"+fileName);

            //根据srcFile的大小，准备一个字节数组
            byte[] bytes = srcFile.getBytes();
            //拼接上传路径
            Path path = Paths.get(upload.getAbsolutePath()+"/"+fileName);
            //最重要的一步，将源文件写入目标地址
            Files.write(path , bytes);
            //将文件上传成功的信息写入message
//            redirectAttributes.addFlashAttribute("message","文件上传成功！，文件已重命名为："+fileName);
        }catch (IOException e){
            e.printStackTrace();
        }
    return filepath;
    }

}
