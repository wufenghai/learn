package com.example.learn.design.pattern.han._06行为型模式._04迭代器模式.core;

import java.util.ArrayList;
import java.util.List;

public class Client {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //´´½¨Ñ§Ôº
        List<College> collegeList = new ArrayList<College>();

        ComputerCollege computerCollege = new ComputerCollege();
        InfoCollege infoCollege = new InfoCollege();

        collegeList.add(computerCollege);
        //collegeList.add(infoCollege);

        OutPutImpl outPutImpl = new OutPutImpl(collegeList);
        outPutImpl.printCollege();
    }

}
