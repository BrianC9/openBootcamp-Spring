package com.example.entregable1_2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainEjercicios {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
//        Saludo saludoBean = (Saludo) context.getBean("saludo");


    }
}
