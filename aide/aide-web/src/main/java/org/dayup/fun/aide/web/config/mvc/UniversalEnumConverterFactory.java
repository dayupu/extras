package org.dayup.fun.aide.web.config.mvc;

import org.dayup.fun.aide.common.enums.MvcEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class UniversalEnumConverterFactory implements ConverterFactory<String, MvcEnum> {

    private static final Map<Class, Converter> converterMap = new WeakHashMap<>();

    @Override
    public <T extends MvcEnum> Converter<String, T> getConverter(Class<T> targetType) {
        Converter result = converterMap.get(targetType);
        if (result == null) {
            result = new StringToEnum<T>(targetType);
            converterMap.put(targetType, result);
        }
        return result;
    }

    class StringToEnum<T extends MvcEnum> implements Converter<String, T> {
        private final Class<T> enumType;
        private Map<String, T> enumMap = new HashMap<>();

        public StringToEnum(Class<T> enumType) {
            this.enumType = enumType;
            T[] enums = enumType.getEnumConstants();
            for (T e : enums) {
                enumMap.put(e.value() + "", e);
            }
        }


        @Override
        public T convert(String source) {
            T result = enumMap.get(source);
            if (result == null) {
                throw new IllegalArgumentException("No element matches " + source);
            }
            return result;
        }
    }
}
