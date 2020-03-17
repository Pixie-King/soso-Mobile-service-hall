package com.ssm.demo.entity;

public class MobileCard {
    public String cardNumber;
    public String userName;
    public String passWord;
    public ServicePackage serPackage;
    public double consumeAmount=0;
    public double money = 0;
    public int realTalkTime = 0;
    public int realSMSCount = 0;
    public int realFlow = 0;
    public MobileCard(){}

    public void setConsumeAmount(double consumeAmount) {
        this.consumeAmount = consumeAmount;
    }

    public void setMoney(double money) {
        this.money = money;
    }
    public void setRealTalkTime(int realTalkTime){
        this.realTalkTime = realTalkTime;
    }

    public void setRealSMSCount(int realSMSCount) {
        this.realSMSCount = realSMSCount;
    }

    public void setRealFlow(int realFlow) {
        this.realFlow = realFlow;
    }
}

