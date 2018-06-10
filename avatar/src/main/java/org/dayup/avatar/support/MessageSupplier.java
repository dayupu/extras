package org.dayup.avatar.support;


import org.dayup.avatar.support.common.SpringUtil;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

public final class MessageSupplier {

    private static MessageSource messageSource;

    private MessageSupplier(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    private static MessageSource getMessageSource() {
        if (messageSource == null) {
            messageSource = SpringUtil.getBean(MessageSource.class);
        }
        return messageSource;
    }


    public static String getMsg(String key, String... args) {
        try {
            return getMessageSource().getMessage(key, args, Locale.CHINESE);
        } catch (NoSuchMessageException e) {
            return key;
        }

    }


}
