package com.eio.controller;


import com.eio.pojo.Admin;
import com.eio.pojo.LoginForm;
import com.eio.pojo.Student;
import com.eio.pojo.Teacher;
import com.eio.service.AdminService;
import com.eio.service.StudentService;
import com.eio.service.TeacherService;
import com.eio.util.*;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/sms/system")
public class SystemController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;


    /**
     * 处理获取验证码请求
     *
     * @param request
     * @param response
     */
    @GetMapping("/getVerifiCodeImage")
    public void getVerifiCodeImage(HttpServletRequest request, HttpServletResponse response) {
        //获取图片
        BufferedImage verifiCodeImage = CreateVerifiCodeImage.getVerifiCodeImage();
        //获取图片上的验证码
        String verifiCode = new String(CreateVerifiCodeImage.getVerifiCode());
        //将验证码文本放入session域，为下一次验证做准备
        HttpSession session = request.getSession();
        session.setAttribute("verifiCode", verifiCode);
        //将验证码图片响应给浏览器
        try {
            ImageIO.write(verifiCodeImage, "JPEG", response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 处理登录请求
     *
     * @param loginForm
     * @param request
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginForm loginForm, HttpServletRequest request) {
        //获取用户提交的验证码和session域中的验证码
        HttpSession session = request.getSession();
        String sessionVerifiCode = (String) session.getAttribute("verifiCode");
        String loginFormVerifiCode = loginForm.getVerifiCode();

        //System.out.println("sessionVerifiCode = " + sessionVerifiCode + "; loginFormVerifiCode = " + loginFormVerifiCode);

        if ("".equals(sessionVerifiCode) || sessionVerifiCode == null) {
            //session过期，验证码失效
            return Result.fail().message("验证失效，请刷新后重试！");
        }
        //判断用户的验证码是否和session中的验证码是否一致
        //不一致返回错误信息
        if (!loginFormVerifiCode.equalsIgnoreCase(sessionVerifiCode)) {
            return Result.fail().message("验证码有误，请重新输入！");
        }
        //若以上判断通过，则验证码无误
        // 验证码使用完毕,移除当前请求域中的验证码
        session.removeAttribute("verifiCode");

        // 准备一个Map集合,用户存放响应的信息
        Map<String, Object> map = new HashMap<>();
        // 根据用户身份,验证登录的用户信息
        switch (loginForm.getUserType()) {
            case 1://管理员身份
                try {
                    // 调用服务层登录方法,根据用户提交的LoginInfo信息,查询对应的Admin对象,找不到返回Null
                    Admin admin = adminService.Login(loginForm);
                    //System.out.println( "Admin = " + admin + "LoginForm = " + loginForm);

                    if (admin != null) {
                        // 登录成功,将用户id和用户类型转换为token口令,作为信息响应给前端
                        map.put("token", JwtHelper.createToken(admin.getId().longValue(), 1));
                    } else {
                        throw new RuntimeException("用户名或密码错误！");
                    }
                    return Result.ok(map);
                } catch (RuntimeException exception) {
                    //响应异常，向用户输出错误信息
                    return Result.fail().message(exception.getMessage());
                }

            case 2://学生身份
                try {
                    Student student = studentService.Login(loginForm);
                    if (student != null) {
                        //登录成功，将用户id和用户类型转换为token口令，作为信息响应给前端
                        map.put("token", JwtHelper.createToken(student.getId().longValue(), 2));
                    } else {
                        throw new RuntimeException("用户名或密码错误！");
                    }
                    return Result.ok(map);
                } catch (RuntimeException exception) {
                    return Result.fail().message(exception.getMessage());
                }

            case 3://教师身份
                try {
                    Teacher teacher = teacherService.Login(loginForm);
                    if (teacher != null) {
                        //登录成功，将用户id和用户类型转换为token口令，作为信息响应给前端
                        map.put("token", JwtHelper.createToken(teacher.getId().longValue(), 3));
                    } else {
                        throw new RuntimeException("用户名或密码错误！");
                    }
                    return Result.ok(map);
                } catch (RuntimeException exception) {
                    return Result.fail().message(exception.getMessage());
                }
        }
        //若所有不匹配，查不到该用户
        //响应失败，返回信息
        return Result.fail().message("查无此用户！");
    }


    /**
     * 向前端返回登录的用户信息，由前端实现不同身份跳转到不同页面
     *
     * @param request
     * @param token
     * @return
     */
    @GetMapping("/getInfo")
    public Result getUserInfoByToken(HttpServletRequest request, @RequestHeader("token") String token) {
        //获取用户请求的token
        //检查token是否过期
        boolean expiration = JwtHelper.isExpiration(token);
        if (expiration) {
            return Result.build(null, ResultCodeEnum.TOKEN_ERROR);
        }
        //解析token，获取用户id和用户类型
        Long userId = JwtHelper.getUserId(token);
        Integer userType = JwtHelper.getUserType(token);

        // 准备一个Map集合,用户存放响应的信息
        Map<String, Object> map = new HashMap<>();

        switch (userType) {
            case 1:
                Admin admin = adminService.getAdminById(userId);
                map.put("user", admin);
                map.put("userType", 1);
                break;
            case 2:
                Student student = studentService.getStudentById(userId);
                map.put("user", student);
                map.put("userType", 2);
                break;
            case 3:
                Teacher teacher = teacherService.getTeacherById(userId);
                map.put("user", teacher);
                map.put("userType", 3);
                break;
        }
        return Result.ok(map);
    }


    /**
     * 处理头像上传请求
     *
     * @return
     */
    @PostMapping("/headerImgUpload")
    public Result headerImgUpload(@RequestPart("multipartFile") MultipartFile multipartFile) {
        //使用UUID随机生成文件名
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        //生成新的文件名字
        String filename = uuid.concat(multipartFile.getOriginalFilename());
        //获取当前工程下的路径
        String projectPath = this.getClass().getClassLoader().getResource("public/upload/").getPath();
        //文件真正保存路径
        String portraitPath = projectPath.concat(filename);
        //保存文件
        try {
            multipartFile.transferTo(new File(portraitPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //响应给前端文件名
        String headerImg = "upload/" + filename;

        return Result.ok(headerImg);

    }


    /**
     * 实现用户密码的修改
     * @param token
     * @param oldPwd
     * @param newPwd
     * @return
     */
    @PostMapping("/updatePwd/{oldPwd}/{newPwd}")
    public Result updatePwd(
            @RequestHeader("token") String token,
            @PathVariable("oldPwd") String oldPwd,
            @PathVariable("newPwd") String newPwd
    ) {
        //判断用户token是否过期
        boolean expiration = JwtHelper.isExpiration(token);
        if (expiration) {
            //token过期
            return Result.fail().message(" 当前登录失效，请重新登录后修改密码！");
        }
        //获取用户ID和用户类型
        Long userId = JwtHelper.getUserId(token);
        Integer userType = JwtHelper.getUserType(token);

        //将明文密码改为暗文
        oldPwd = MD5.encrypt(oldPwd);
        newPwd = MD5.encrypt(newPwd);

        //判断用户类型，以查询不同表修改密码
        switch (userType) {
            case 1://管理员
                Admin admin = adminService.getAdmin(userId,oldPwd);
                if (admin != null) {
                    admin.setPassword(newPwd);
                    adminService.saveOrUpdate(admin);
                } else {
                   return Result.fail() .message("原密码输入有误！");
                }
                break;
            case 2://学生
                Student student = studentService.getStudent(userId,oldPwd);
                if (student != null) {
                    student.setPassword(newPwd);
                    studentService.saveOrUpdate(student);
                } else {
                    return Result.fail() .message("原密码输入有误！");
                }
                break;
            case 3:
                Teacher teacher = teacherService.getTeacher(userId,oldPwd);
                if (teacher != null) {
                    teacher.setPassword(newPwd);
                    teacherService.saveOrUpdate(teacher);
                } else {
                    return Result.fail() .message("原密码输入有误！");
                }
        }
        return Result.ok();
    }

}
