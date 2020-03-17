package com.ssm.demo.entity;

public class ConsumeInfo {
    public String consumeType;
    public int consumeData;
    public ConsumeInfo(String type, int consumeData){this.consumeType=type;this.consumeData=consumeData;}
    public ConsumeInfo(){}

    public String getConsumeType() {
        return consumeType;
    }

    public String getConsumeData() {
        return String.valueOf(consumeData);
    }
}
