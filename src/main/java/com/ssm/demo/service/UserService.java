package com.ssm.demo.service;

import com.ssm.demo.entity.ConsumeInfo;
import com.ssm.demo.entity.MobileCard;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface UserService {
    public MobileCard thisCard(String number);
    public boolean isExistCard(String number, String password);
    public  boolean isExistCard(String number);
    public void addCard(MobileCard card) throws SQLException, ClassNotFoundException;
    public String createNumber();
    public String showRemainDetail(MobileCard thisCard);
    public String changingPack(MobileCard thisCard, String packNum);
    public String chargeMoney(String number, double money);
    public void delCard(String Number);
    public String newCard(MobileCard card,String pnum,String name,String pwd,String money) throws SQLException, ClassNotFoundException;
    public String usSOso(MobileCard m,int Sce);
    public List<ConsumeInfo> getInfo(String number);//由卡号找到其全部消费记录
}
