package com.example.learn.design.pattern.han._06行为型模式._11职责链模式.core;

public class SchoolMasterApprover extends Approver {

    public SchoolMasterApprover(String name) {
        // TODO Auto-generated constructor stub
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest purchaseRequest) {
        // TODO Auto-generated method stub
        if(purchaseRequest.getPrice() > 30000) {
            System.out.println(" 请求编号 id= " + purchaseRequest.getId() + " 被 " + this.name + " 处理 ");
        }else {
            approver.processRequest(purchaseRequest);
        }
    }
}
