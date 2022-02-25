package com.springbook.TobySpring.one.three.calculator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {
    public Integer calcSum(String filepath) throws IOException {
        BufferedReader br = null;
        Integer sum = 0;
        try {
            br = new BufferedReader(new FileReader(filepath));
            String line = null;
            while ((line = br.readLine()) != null) {
                sum += Integer.valueOf(line);
            }
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


        return sum;
    }

    public Integer calcMultiply(String filepath) throws IOException {
        BufferedReader br = null;
        Integer sum = 0;
        try {
            br = new BufferedReader(new FileReader(filepath));
            String line = null;
            while ((line = br.readLine()) != null) {
                sum *= Integer.valueOf(line);
            }
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


        return sum;
    }
}
