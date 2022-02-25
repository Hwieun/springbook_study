package com.springbook.TobySpring.one.six;

import com.springbook.TobySpring.one.six.Message;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;

// 해당 인터페이스를 구현하고 스프링 빈으로 등록하면 팩토리 빈으로 동작
public class MessageFactoryBean implements FactoryBean<Message> {

    String text;

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public Message getObject() throws Exception {
        return Message.newMessage(text);
    }

    @Override
    public Class<? extends Message> getObjectType() {
        return Message.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
