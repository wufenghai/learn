package com.example.learn.design.pattern.han._01.七大设计原理._05开闭原则.improve;


public class Ocp {

    public static void main(String[] args) {
        //使用看看存在的问题
        GraphicEditor graphicEditor = new GraphicEditor();
        graphicEditor.drawShape(new Rectangle());
        graphicEditor.drawShape(new Circle());
        graphicEditor.drawShape(new Triangle());
        graphicEditor.drawShape(new OtherGraphic());
        /**
         * 接口和抽象类的区别
         *         1.抽象类里面可以有构造方法，接口中不能。
         *         2.抽象类中可以有普通成员变量，而接口不能。
         *         3.抽象类可以包含非抽象的普通方法，接口中必须是抽象方法。
         *         4.抽象类中的抽象方法访问类型可以是public,protected,和private，但接口中的方法只能是public的即默认为public abstract。
         *         5.抽象类中可以包含静态方法，接口内不能。
         *         6.抽象类和接口都可以包含静态变量，抽象类中的静态成员变量的访问类型可以任意，接口中只能是public static类型，
         *          并且默认为public static final类型。
         *         7.一个类可以实现多个接口，但只能继承一个抽象类。
         */
        System.out.println(new OtherGraphic().m_type);//接口的话不能有自己的成员变量
    }

}

//这是一个用于绘图的类 [使用方]
class GraphicEditor {
    //接收Shape对象，调用draw方法
    public void drawShape(Shape s) {
        s.draw();
    }


}

//Shape类，基类
abstract class Shape {
    int m_type;

    public abstract void draw();//抽象方法
}

class Rectangle extends Shape {
    Rectangle() {
        super.m_type = 1;
    }

    @Override
    public void draw() {
        // TODO Auto-generated method stub
        System.out.println(" 绘制矩形 ");
    }
}

class Circle extends Shape {
    Circle() {
        super.m_type = 2;
    }

    @Override
    public void draw() {
        // TODO Auto-generated method stub
        System.out.println(" 绘制圆形 ");
    }
}

//新增画三角形
class Triangle extends Shape {
    Triangle() {
        super.m_type = 3;
    }

    @Override
    public void draw() {
        // TODO Auto-generated method stub
        System.out.println(" 绘制三角形 ");
    }
}

//新增一个图形
class OtherGraphic extends Shape {
    OtherGraphic() {
        super.m_type = 4;
    }

    @Override
    public void draw() {
        // TODO Auto-generated method stub
        System.out.println(" 绘制其它图形 ");
    }
}