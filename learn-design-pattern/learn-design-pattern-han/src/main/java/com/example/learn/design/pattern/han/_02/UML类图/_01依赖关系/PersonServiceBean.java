package com.example.learn.design.pattern.han._02.UML类图._01依赖关系;

public class PersonServiceBean {
    private PersonDao personDao;// Àà

    public void save(Person person) {
    }

    public IDCard getIDCard(Integer personid) {
        return null;
    }

    public void modify() {
        Department department = new Department();
    }

}
