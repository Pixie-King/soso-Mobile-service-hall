package com.ssm.demo.service.impl;

import com.ssm.demo.dao.UsersDao;
import com.ssm.demo.entity.*;
import com.ssm.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UsersDao usersDao;
    public MobileCard thisCard(String number){
    return usersDao.getByNumber(number);
    }
    public boolean isExistCard(String number, String password){
        if(isExistCard(number)==false)return false;
        else if(password.equals(usersDao.passWordGetByNumber(number)))return true;
        else return false;
    }
    public boolean isExistCard(String number) {
        if (usersDao.containNumber(number) != null) return true;
        else return false;
    }
    public void addCard(MobileCard card) throws SQLException, ClassNotFoundException {
        this.usersDao.save(card);
        // this.mysql.newTable(card.cardNumber);
    }
    public String createNumber(){
        StringBuilder val = new StringBuilder("139");
        Random random = new Random();
        for (int i = 0; i < 7; i++) {
            val.append(String.valueOf(random.nextInt(10)));
        }
        return val.toString();  //调用random类的nextInt方法生成0~9的随机数，并转为String加到字符串上
    }
    public String showRemainDetail(MobileCard thisCard){
        DecimalFormat format= new DecimalFormat("#.0");  //用于对小数输出进行格式控制（输出一位）
        StringBuffer str=new StringBuffer("您的卡号:");
        str.append(thisCard.cardNumber).append(",套餐内剩余:").append('\n');
        if(thisCard.serPackage instanceof TalkPackage) {
            str.append("通话时长:")
                    .append(((TalkPackage) thisCard.serPackage).talkTime>thisCard.realTalkTime?((TalkPackage) thisCard.serPackage).talkTime-thisCard.realTalkTime:0)
                    .append("分钟").append('\n').append("短信条数:")
                    .append(((TalkPackage) thisCard.serPackage).smsCount>thisCard.realSMSCount?((TalkPackage) thisCard.serPackage).smsCount-thisCard.realSMSCount:0)
                    .append("条");
        }
        if (thisCard.serPackage instanceof NetPackage){
            str.append("上网流量:")
                    .append(((NetPackage) thisCard.serPackage).flow>thisCard.realFlow?format.format((double)(((NetPackage) thisCard.serPackage).flow-thisCard.realFlow)/1024):0)
                    .append("GB");
        }
        if(thisCard.serPackage instanceof SuperPackage){
            str.append("通话时长:")
                    .append(((SuperPackage) thisCard.serPackage).talkTime>thisCard.realTalkTime?((SuperPackage) thisCard.serPackage).talkTime-thisCard.realTalkTime:0)
                    .append("分钟").append('\n').append("短信条数:")
                    .append(((SuperPackage) thisCard.serPackage).smsCount>thisCard.realSMSCount?((SuperPackage) thisCard.serPackage).smsCount-thisCard.realSMSCount:0)
                    .append("条").append('\n').append("上网流量:")
                    .append(((SuperPackage) thisCard.serPackage).flow>thisCard.realFlow?format.format((double)(((SuperPackage) thisCard.serPackage).flow-thisCard.realFlow)/1024):0)
                    .append("GB");;
        }
    return str.toString();
    }
    private ServicePackage packageType(String packNum){
        switch (packNum){
            case "1":
                return new TalkPackage();
            case"2":
                return new NetPackage();
            case"3":
                return new SuperPackage();
            default:
                return null;
        }
    }
    public String changingPack(MobileCard thisCard, String packNum){
        if(thisCard.serPackage.getClass().equals(Objects.requireNonNull(packageType(packNum)).getClass())){
            return "对不起，您已是该套餐用户，无需换套餐！";
        }//调用getClass方法，判断当前套餐与由packageType得到的套餐是否类相同，相同提示错误信息
        else {
            if (thisCard.money < Objects.requireNonNull(packageType(packNum)).price) {
                return "对不起，您的余额不足以支付新套餐本月资费，请充值后再办理更换套餐业务！";
            }//若卡余额低于由packageType得到的套餐资费，提示错误信息
            else {
                thisCard.money -= Objects.requireNonNull(packageType(packNum)).price;
                thisCard.serPackage = packageType(packNum);
                thisCard.realFlow = thisCard.realSMSCount = thisCard.realTalkTime = 0;
                usersDao.update(thisCard);
                return "变更成功！"; //扣除卡余额，使用套餐量清零
            }
        }
    }
    public String chargeMoney(String number, double money){
        DecimalFormat format= new DecimalFormat("#.0");
        try{
            MobileCard m =thisCard(number);
            System.out.println(m.serPackage);
            if(money<50)throw new IllegalArgumentException(); //充值金额小于50抛出异常
            m.money+=money;
            usersDao.update(m);
            return "充值成功，您当前的余额为"+format.format(m.money)+"元。";
        }
        catch(IllegalArgumentException e) {
            return "错误！您充值的金额不足50元";
        }
    }
    public void delCard(String Number) {
        usersDao.del(Number);
        usersDao.deleteInfo(Number);
    }

    public String newCard(MobileCard card,String pnum,String name,String pwd,String money) throws SQLException, ClassNotFoundException {
        switch (pnum){
            case "1":
                card.serPackage = new TalkPackage();
                break;
            case "2":
                card.serPackage = new NetPackage();
                break;
            case "3":
                card.serPackage = new SuperPackage(); //根据输入不同，选择的卡的套餐类指向的子类对象不同
        }
        if(name=="")return "用户名不得为空";
        if(pwd=="")return "密码不得为空";
        if(money=="")return "请输入预存金额！";
        card.userName = name;
        card.passWord = pwd;
        int myMoney = Integer.parseInt(money);
        if (myMoney < card.serPackage.price) {   //若输入的预存金额低于套餐资费则不断重新输入
            return "您预存的话费金额不足以支付本月固定套餐资费，请重新充值:";
        }
        card.money = myMoney;
        card.money -= card.serPackage.price;
        addCard(card);  //完成注册，调用addCard把选择的卡放入数据库
        return "注册成功!\n卡号:" + card.cardNumber + "\n用户名:" + card.userName + "\n当前余额:" + card.money +"元。\n" +card.serPackage.showInfo() ;


    }

    public String usSOso(MobileCard m,int Sce){
        int talkCount = 0,sendCount = 0,SurfaceCount = 0;   //用于接收call,send,netPlay的返回值
        Scene thisScene = usersDao.getScene(Sce);   //根据序号找到场景赋给thisScene
        if(((Sce == 5 || Sce == 6) && Objects.requireNonNull(m).serPackage instanceof TalkPackage) || ((Sce != 5 && Sce != 6)&&Objects.requireNonNull(m).serPackage instanceof NetPackage)){
            assert thisScene != null;
            return "对不起，您的套餐不包含"+thisScene.type+"服务";
        }  //卡的套餐不存在场景对应的服务时返回false，重新输入卡号进行使用操作
        if(m.money==0)return "您已经身无分文，赶快充值吧~";
            CallService A;
            SendService B;
            NetService C; //创建3个服务接口
        ConsumeInfo e = null;
            if(Sce == 1 ||Sce == 4) {
                if(Objects.requireNonNull(m).serPackage instanceof TalkPackage){
                    A = new TalkPackage();
                    assert thisScene != null;
                    talkCount=A.call(thisScene.data,m);
                    assert thisScene != null;
                    e =new ConsumeInfo(thisScene.type,thisScene.data);
                    usersDao.update(m);
                    if(talkCount!=0) {
                        m.setMoney(0);
                        usersDao.update(m);
                        e.consumeData = talkCount;
                        return "本次已通话" + talkCount + "分钟，您的余额不足，请充值后再使用" + "。\n" + addConsumeInfo(m.cardNumber, e);
                    }
                }
                if(Objects.requireNonNull(m).serPackage instanceof SuperPackage){
                    A = new SuperPackage();
                    assert thisScene != null;
                    talkCount=A.call(thisScene.data,m);
                    assert thisScene != null;
                    e =new ConsumeInfo(thisScene.type,thisScene.data);
                    usersDao.update(m);
                    if(talkCount!=0){
                        m.setMoney(0);
                        usersDao.update(m);
                        e.consumeData = talkCount;
                        return "本次已通话"+talkCount+"分钟，您的余额不足，请充值后再使用" + "。\n"+addConsumeInfo(m.cardNumber,e);
                    }
                }

            }
            if(Sce == 2 ||Sce == 3) {
                if(Objects.requireNonNull(m).serPackage instanceof TalkPackage) {
                    B = new TalkPackage();
                    assert thisScene != null;
                    sendCount = B.send(thisScene.data, m);
                    assert thisScene != null;
                    e =new ConsumeInfo(thisScene.type,thisScene.data);
                    usersDao.update(m);
                    if (sendCount != 0) {
                        m.setMoney(0);
                        usersDao.update(m);
                        e.consumeData = sendCount;
                        return "本次已发送短信"+sendCount+"条，您的余额不足，请充值后再使用"+ "。\n"+addConsumeInfo(m.cardNumber,e);
                    }
                }
                if(Objects.requireNonNull(m).serPackage instanceof SuperPackage) {
                    B = new SuperPackage();
                    assert thisScene != null;
                    sendCount = B.send(thisScene.data, m);
                    assert thisScene != null;
                    e =new ConsumeInfo(thisScene.type,thisScene.data);
                    usersDao.update(m);
                    if (sendCount != 0)  {
                        m.setMoney(0);
                        usersDao.update(m);
                        e.consumeData = sendCount;
                        return "本次已发送短信"+sendCount+"条，您的余额不足，请充值后再使用"+ "。\n"+addConsumeInfo(m.cardNumber,e);
                    }
                }
            }
            if(Sce == 5 ||Sce == 6){
                if(Objects.requireNonNull(m).serPackage instanceof NetPackage) {
                    C = new NetPackage();
                    assert thisScene != null;
                    SurfaceCount = C.netPlay(thisScene.data, m);
                    assert thisScene != null;
                    e =new ConsumeInfo(thisScene.type,thisScene.data);
                    usersDao.update(m);
                    if (SurfaceCount != 0){
                        m.setMoney(0);
                        usersDao.update(m);
                        e.consumeData = SurfaceCount;
                        return "本次已使用上网流量"+SurfaceCount+"MB，您的余额不足，请充值后再使用"+ "。\n"+addConsumeInfo(m.cardNumber,e);
                    }
                }
                if(Objects.requireNonNull(m).serPackage instanceof SuperPackage) {
                    C = new SuperPackage();
                    assert thisScene != null;
                    SurfaceCount = C.netPlay(thisScene.data, m);
                    assert thisScene != null;
                    e =new ConsumeInfo(thisScene.type,thisScene.data);
                    usersDao.update(m);
                    if (SurfaceCount != 0) {
                        m.setMoney(0);
                        usersDao.update(m);
                        e.consumeData = SurfaceCount;
                        return "本次已使用上网流量"+SurfaceCount+"MB，您的余额不足，请充值后再使用"+ "。\n"+addConsumeInfo(m.cardNumber,e);
                    }
                }

            }
            return thisScene.description + "。\n"+addConsumeInfo(m.cardNumber,e) ;
    }

    private String addConsumeInfo(String number, ConsumeInfo info){
        usersDao.addInfo(number,info);
        if(usersDao.getInfo(number).isEmpty()){
            return "不存在此卡的消费记录，已添加一条消费记录";
        }
        else {
            return "已存在此卡的消费记录，已添加一条消费记录";
        }
    }
    public List<ConsumeInfo> getInfo(String number){
        return usersDao.getInfo(number);
    }
}


