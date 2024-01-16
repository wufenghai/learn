package com.example.server.demo.util;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author wfh
 * @create 2024/1/16 14:48
 */
public class CalculatorUtil {

    private final static ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
    public static Object cal(String expression){
        try {
            return jse.eval(expression);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return null;
    }

}
