package com.springbook.TobySpring.one.three.calculator;

import java.io.BufferedReader;

public interface LineCallback<T> {
    T doSomethingWithLine(String line, T value);
}
