package com.ssm.demo.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssm.demo.entity.ConsumeInfo;
import com.ssm.demo.entity.MobileCard;
import com.ssm.demo.entity.Scene;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDao {
    void save(MobileCard mobileCard) throws SQLException, ClassNotFoundException;//保存参数中的卡信息
    void del(String cardNumber);//删除卡号对应的卡信息
    List<ConsumeInfo> getInfo(String number);//由卡号找到其全部消费记录
    void addInfo(@Param("cardNumber")String number,@Param("consumeInfo") ConsumeInfo i);//像该卡号消费记录表中添加新纪录
    void deleteInfo(String cardNumber);//删除该号的消费记录
    void update(MobileCard mobileCard);//更新该卡的信息
    String containNumber(String number);//判断数据库中是否包含该卡信息
    Scene getScene(int id);//由ID在数据库中找到对应场景
    String passWordGetByNumber(String number);//由卡号返回密码
    MobileCard getByNumber(String number);//由卡号返回卡
}
