
package org.dayup.avatar.base.plugins.fool.model;

public class FoolCondition {

    private Operate operate;

    private String name;

    private FoolPredicate predicate;

    private Object[] value;

    public FoolCondition(String name) {

        this.name = name;
    }

    public FoolCondition(Operate operate, String name) {
        this.operate = operate;
        this.name = name;
    }

    public FoolCondition(Operate operate, String name, Object... value) {
        this.name = name;
        this.value = value;
        this.operate = operate;
    }

    public Operate operate() {
        return operate;
    }

    public String name() {
        return name;
    }

    public Object[] value() {
        return value;
    }

}
