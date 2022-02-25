package com.springbook.TobySpring.one.six;

// 기본 생성자를 제공하지 않는 클래스. 팩토리 빈을 이용해서 만들거야
public class Message {
    String text;

    public Message(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static Message newMessage(String text) {
        return new Message(text);
    }
}
