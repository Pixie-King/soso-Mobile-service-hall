package com.ssm.demo.entity;

public class NetPackage extends ServicePackage implements NetService{
    public int flow = 3072;
    public NetPackage(){price=68;}
    public String showInfo(){
        return "网虫套餐:通话时长为0分钟/月，短信条数为0条/月，上网流量为3GB/月。";
    }
    public int netPlay(int flow,MobileCard card){
        if(card.realFlow<=3072) {
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
