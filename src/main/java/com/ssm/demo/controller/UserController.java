package com.ssm.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssm.demo.entity.MobileCard;
import com.ssm.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

@RequestMapping("/mobileCard")
@Controller
public class UserController {

    @Autowired
    private UserService userService;
    private String numberNow = null;
    private MobileCard[] alternativeCard = new MobileCard[9];
    @RequestMapping(value = "/getCard",method = RequestMethod.POST)
    @ResponseBody
    public MobileCard getCardByNumber() throws IOException {
        return userService.thisCard("1392512485");
    }
    @RequestMapping(value = "/testcfg")
    public String judge(HttpServletRequest request,Model model) throws IOException {
        request.setCharacterEncoding("utf-8");
        String number = request.getParameter("num");
        String pwd = request.getParameter("password");
        ObjectMapper mapper = new ObjectMapper();
        String message = null;
        if(number==""){
            message = "用户名不得为空";
            model.addAttribute("mess",message);
            return "welcom";
        }
        else if(pwd==""){
            message = "密码不得为空";
            model.addAttribute("mess",message);
            return "welcom";
        }
        if(!userService.isExistCard(number,pwd)) {
            message = "用户名或密码错误，请检查无误后重新输入";
            model.addAttribute("mess",message);
            return "welcom";
        }
        else {
            this.numberNow = number;
            MobileCard mobileCard = userService.thisCard(numberNow);
            Calendar cal=Calendar.getInstance();
            int y=cal.get(Calendar.YEAR);
            int m=cal.get(Calendar.MONTH)+1;
            int d=cal.get(Calendar.DATE);
            int h=cal.get(Calendar.HOUR_OF_DAY);
            String str = null;
            if(h>=4&&h<=9)str = "早上好";
            if(h>9&&h<12)str = "中午好";
            if(h>=12&&h<=17)str = "下午好";
            if(h>=18&&h<=23)str = "晚上好";
            if(h>=0&&h<4)str = "夜深了，快点睡吧";
            model.addAttribute("date","今天是"+y+"年"+m+"月"+d+"日");
            model.addAttribute("hello",mobileCard.userName+"，"+str);
            return "user";
        }
    }
    public String judge(String num,String pwd) throws IOException {
        if(!userService.isExistCard(num,pwd))
        {
            return "index";
        }
        else return "users";
    }
    @RequestMapping(value = "/zhuce2")
    public String getNewCard(HttpServletRequest request,Model model) throws UnsupportedEncodingException, SQLException, ClassNotFoundException {
        List<String> nums= new ArrayList<String>();
        String k[] = new String[10];
        for(int i=0;i<9;i++){
            alternativeCard[i] = new MobileCard();
        }
        for(int i=1;i<=9;i++) {
            k[i]="key"+String.valueOf(i);
            while (true) {
                String temp = userService.createNumber();
                if (!userService.isExistCard(temp)&&!nums.contains(temp)) {
                    this.alternativeCard[i - 1].cardNumber = temp;
                    nums.add(temp);
                    model.addAttribute(k[i],temp);
                    break;
                }
            }
            model.addAttribute("m","a");
        }
        request.setCharacterEncoding("utf-8");
        String num = request.getParameter("group1");
        int nrealum = Integer.valueOf(num);
        String num2 = request.getParameter("group2");
        int nlum = Integer.valueOf(num2);
        model.addAttribute("key",userService.newCard(alternativeCard[nrealum-1],request.getParameter("group2"),request.getParameter("name"),request.getParameter("password"),request.getParameter("money")));
        return "zhuce";
    }
    @RequestMapping(value = "/chaxun1")
    public String showAmountDetail(Model model){
        MobileCard mobileCard = userService.thisCard(numberNow);
        model.addAttribute("s",mobileCard.cardNumber);
        double mon = mobileCard.serPackage.price+mobileCard.consumeAmount;
        model.addAttribute("m",mon);
        model.addAttribute("r",mobileCard.money);
        model.addAttribute("p",mobileCard.serPackage.price);
        return "chaxun1";
    }
    @RequestMapping(value = "/chaxun2")
    public String showRemainDetail(Model model){
        MobileCard mobileCard = userService.thisCard(numberNow);
        String str = userService.showRemainDetail(mobileCard);
        model.addAttribute("ss",str);
        return "chaxun2";
    }
    @RequestMapping(value = "/change")
    public String Change(HttpServletRequest request,Model model) throws UnsupportedEncodingException {
        MobileCard mobileCard = userService.thisCard(numberNow);
        request.setCharacterEncoding("utf-8");
        String num = request.getParameter("group2");
        String str = userService.changingPack(mobileCard,num);
        System.out.println(num);
        model.addAttribute("num",num);
        model.addAttribute("s12",str);
        return "change";
    }
    @RequestMapping(value = "/charge")
    public String charge(HttpServletRequest request,Model model) throws UnsupportedEncodingException {
        MobileCard mobileCard = userService.thisCard(numberNow);
        request.setCharacterEncoding("utf-8");
        double money = Double.valueOf(request.getParameter("money"));
        String str = userService.chargeMoney(numberNow,money);
        model.addAttribute("smoney",str);
        return "chargr";
    }
    @RequestMapping(value = "/change0")
    public String Change0() throws UnsupportedEncodingException {
        return "change";
    }
    @RequestMapping(value = "/return")
    public String back(Model model) throws UnsupportedEncodingException {
        MobileCard mobileCard = userService.thisCard(numberNow);
        Calendar cal=Calendar.getInstance();
        int y=cal.get(Calendar.YEAR);
        int m=cal.get(Calendar.MONTH)+1;
        int d=cal.get(Calendar.DATE);
        int h=cal.get(Calendar.HOUR_OF_DAY);
        String str = null;
        if(h>=4&&h<=9)str = "早上好";
        if(h>9&&h<12)str = "中午好";
        if(h>=12&&h<=17)str = "下午好";
        if(h>=18&&h<=23)str = "晚上好";
        if(h>=0&&h<4)str = "夜深了，快点睡吧";
        model.addAttribute("date","今天是"+y+"年"+m+"月"+d+"日");
        model.addAttribute("hello",mobileCard.userName+"，"+str);
        return "user";
    }
    @RequestMapping(value = "/chongzhi")
    public String tocharge() throws UnsupportedEncodingException {
        return "chargr";
    }
    @RequestMapping(value = "/touse")
    public String toUse() throws UnsupportedEncodingException {
        return "use";
    }
    @RequestMapping(value = "/tozhuce")
    public String tozhuce(Model model) throws UnsupportedEncodingException {
        List<String> nums= new ArrayList<String>();
        String k[] = new String[10];
        for(int i=0;i<9;i++){
            alternativeCard[i] = new MobileCard();
        }
        for(int i=1;i<=9;i++) {
            k[i]="key"+String.valueOf(i);
            while (true) {
                String temp = userService.createNumber();
                if (!userService.isExistCard(temp)&&!nums.contains(temp)) {
                    this.alternativeCard[i - 1].cardNumber = temp;
                    nums.add(temp);
                    model.addAttribute(k[i],temp);
                    break;
                }
            }
            model.addAttribute("m","a");
        }
        return "zhuce";
    }
    @RequestMapping(value = "/zhuxiao")
    public String del() throws UnsupportedEncodingException {
        MobileCard mobileCard = userService.thisCard(numberNow);
        userService.delCard(numberNow);
        numberNow=null;
        return "redirect:/";
    }
    @RequestMapping(value = "/use")
    public String using(HttpServletRequest request,Model model) throws UnsupportedEncodingException {
        MobileCard mobileCard = userService.thisCard(numberNow);
        request.setCharacterEncoding("utf-8");
        int Sce = Integer.valueOf(request.getParameter("use"));
        model.addAttribute("s12",userService.usSOso(mobileCard,Sce));
        return "use";
    }
    @RequestMapping(value = "/towel")
    public String towel() throws UnsupportedEncodingException {
        return "welcom";
    }
    @RequestMapping(value = "/detail")
    public String showDetail(Model model) throws UnsupportedEncodingException {
        model.addAttribute("list",userService.getInfo(numberNow));
        model.addAttribute("card",numberNow);
        return "detail";
    }
}
