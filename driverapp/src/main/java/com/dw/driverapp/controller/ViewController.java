package com.dw.driverapp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/driverapp/index.html")
    public String index(){return "forward:/index.html";}

    @GetMapping("/driverapp/video.html")
    public String video(){return "forward:/video.html";}

    @GetMapping("/driverapp/login.html")
    public String login(){return "forward:/login.html";}

    @GetMapping("/driverapp/QNA.html")
    public String QNA(){return "forward:/QNA.html";}

    @GetMapping("/driverapp/notice.html")
    public String notice(){return "forward:/notice.html";}

    @GetMapping("/driverapp/noticeCheck.html")
    public String noticeCheck(){return "forward:/noticeCheck.html";}

    @GetMapping("/driverapp/noticeRegister.html")
    public String noticeRegister(){return "forward:/noticeRegister.html";}

    @GetMapping("/driverapp/usermypage.html")
    public String usermypage(){return "forward:/usermypage.html";}

    @GetMapping("/driverapp/password.html")
    public String password(){return "forward:/password.html";}

    @GetMapping("/driverapp/introduction.html")
    public String introduction(){return "forward:/introduction.html";}

    @GetMapping("/driverapp/oneNormalExplain.html")
    public String oneNormalExplain(){return "forward:/oneNormalExplain.html";}

    @GetMapping("/driverapp/twoNormalExplain.html")
    public String twoNormalExplain(){return "forward:/twoNormalExplain.html";}

    @GetMapping("/driverapp/QNAadd.html")
    public String QNAadd(){return "forward:/QNAadd.html";}

    @GetMapping("/driverapp/register.html")
    public String register(){return "forward:/register.html";}

    @GetMapping("/driverapp/oneBigExplain.html")
    public String oneBigExplain(){return "forward:/oneBigExplain.html";}

    @GetMapping("/driverapp/adminpage.html")
    public String adminpage(){return "forward:/adminpage.html";}

    @GetMapping("/driverapp/twoSmallExplain.html")
    public String twoSmallExplain(){return "forward:/twoSmallExplain.html";}

    @GetMapping("/driverapp/cart.html")
    public String cart(){return "forward:/cart.html";}

    @GetMapping("/driverapp/replyQ&A.html")
    public String replyQA(){return "forward:/replyQ&A.html";}

    @GetMapping("/driverapp/replyQ&A1.html")
    public String replyQA1(){return "forward:/replyQ&A1.html";}

    @GetMapping("/driverapp/noticeupdate.html")
    public String noticeupdate(){return "forward:/noticeupdate.html";}

    @GetMapping("/driverapp/correctionQ&A.html")
    public String correctionQ(){return "forward:/correctionQ&A.html";}
}


