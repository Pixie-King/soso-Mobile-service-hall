package com.ssm.demo.entity;

public class ServicePackage{
    public double price;
    public String showInfo(){
        return null;
    };
    @Override
    public String toString() {
        if(this instanceof TalkPackage)return "TalkPackage";
        else if(this instanceof NetPackage)return "NetPackage";
        else return "SuperPackage";
    }
}
