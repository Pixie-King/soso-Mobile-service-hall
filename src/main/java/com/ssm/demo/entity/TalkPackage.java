package com.ssm.demo.entity;

public class TalkPackage extends ServicePackage implements CallService,SendService{
    public int talkTime = 500;
    public int smsCount = 30;
    public TalkPackage(){price = 58;}
    public String showInfo(){
        return "话痨套餐:通话时长为500分钟/月，短信条数为30条/月，上网流量为0GB/月。";
    }
    public int call(int minCount, MobileCard card){

        if(card.realTalkTime<=500){
            card.setRealTalkTime(card.realTalkTime+minCount);
            return 0;
        }
        else if(card.money>=0.2*minCount){
            card.setMoney(card.money-0.2*minCount);
            card.setRealTalkTime(card.realTalkTime+minCount);
            card.setConsumeAmount(card.consumeAmount+0.2*minCount);
            return 0;
        }
        else {
            card.setConsumeAmount(card.consumeAmount+card.money);
            card.setRealTalkTime(card.realTalkTime+(int) (card.money/0.2));
            return (int) (card.money/0.2);
        }
    }
    public int send(int count, MobileCard card){
        if(card.realSMSCount<=30){
            card.setRealSMSCount( card.realSMSCount+count);
            return 0;
        }
        else if(card.money>=0.1*count){
            card.setMoney(card.money-0.1*count);
            card.setRealSMSCount( card.realSMSCount+count);
            card.setConsumeAmount(card.consumeAmount+0.1*count);
            return 0;
        }
        else  {
            card.setConsumeAmount(card.consumeAmount+=card.money);
            card.setRealSMSCount( card.realSMSCount+(int)(card.money/0.1));
            return (int)(card.money/0.1);
        }
    }
}
