package com.ssm.demo.entity;

public class SuperPackage extends ServicePackage implements CallService,SendService,NetService {
    public int talkTime = 200;
    public int smsCount = 50;
    public int flow = 1024;
    public SuperPackage(){price = 78;}
    public String showInfo(){
        return "超人套餐:通话时长为200分钟/月，短信条数为50条/月，上网流量为1GB/月。";
    }

    //send,call,netPlay说明 ：套餐余量充足时只扣除套餐，返回0；套餐余量不足余额充足扣除费用，返回0；余额不足时耗尽余额，返回使用时长
    public int call(int minCount, MobileCard card){
        if(card.realTalkTime<=200){
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
        if(card.realSMSCount<=50){
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
    public int netPlay(int flow,MobileCard card){
        if(card.realFlow<=1024) {
            card.setRealFlow(card.realFlow+flow);
            return 0;
        }
        else if(card.money>=0.1*flow) {
            card.setMoney(card.money-0.1*flow);
            card.setRealFlow(card.realFlow+flow);
            card.setConsumeAmount(card.consumeAmount+0.1*flow);
            return 0;
        }
        else {
            card.setConsumeAmount(card.consumeAmount+card.money);
            card.setRealFlow(card.realFlow+(int) (card.money/0.1));
            return (int) (card.money/0.1);
        }
    }

}
