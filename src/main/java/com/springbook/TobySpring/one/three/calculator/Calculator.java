package com.springbook.TobySpring.one.three.calculator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {

    public <T> T lineReadTemplate(String filepath, LineCallback<T> callback, T initVal) throws IOException {
        BufferedReader br = null;
        try {
            T res = initVal;
            br = new BufferedReader(new FileReader(filepath));
            String line = null;

            while ((line = br.readLine()) != null) {
                res = callback.doSomethingWithLine(line, res);
            }
            return res;
        } catch (IOException e) {
            throw e;
        }finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

    }

    public Integer calcSum(String filepath) throws IOException {
        return lineReadTemplate(filepath, (LineCallback<Integer>) (line, value) -> value + Integer.valueOf(line), 0);
    }

    public Integer calcMultiply(String filepath) throws IOException {
        return lineReadTemplate(filepath, (LineCallback<Integer>) (line, value) -> value * Integer.valueOf(line), 1);
    }
}
