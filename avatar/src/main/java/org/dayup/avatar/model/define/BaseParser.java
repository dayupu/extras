package org.dayup.avatar.model.define;


import java.util.ArrayList;
import java.util.List;

public abstract class BaseParser<E, V> {

    public abstract  V toVo(E entity, V vo);

    public List<V> toVoList(List<E> entitys, Class<V> clazz) {
        if (entitys == null) {
            return null;
        }
        List<V> vos = new ArrayList<>();
        try {
            for (E entity : entitys) {
                vos.add(toVo(entity, clazz.newInstance()));
            }
            return vos;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
