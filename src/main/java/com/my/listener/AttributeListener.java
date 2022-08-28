package com.my.listener;

import jakarta.servlet.ServletRequestAttributeEvent;
import jakarta.servlet.ServletRequestAttributeListener;
import jakarta.servlet.annotation.WebListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class AttributeListener implements ServletRequestAttributeListener {
    private static final Logger LOG = LoggerFactory.getLogger(AttributeListener.class);
    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        LOG.debug("attributeAdded " + srae.getName() + " : " + srae.getValue());
//        System.out.println("attributeAdded " + srae.getName() + " : " + srae.getValue());
//        LOG.debug("attributeAdded in - " + srae.getSource().getClass().getName());
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        ServletRequestAttributeListener.super.attributeRemoved(srae);
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        ServletRequestAttributeListener.super.attributeReplaced(srae);
    }
}
