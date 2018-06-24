
package org.dayup.avatar.base.plugins.fool.model;

import java.util.Collection;

public class FoolPredicate {


    private String name;

    public FoolPredicate(String name) {
        this.name = name;
    }

    public FoolCondition eq(Object value) {
        return new FoolCondition(Operate.eq, name, value);
    }

    public FoolCondition like(String value) {
        return new FoolCondition(Operate.like, name, value);

    }

    public FoolCondition isEmpty(String name) {
        return new FoolCondition(Operate.isEmpty, name);

    }

    public FoolCondition isNotEmpty(String name) {
        return new FoolCondition(Operate.isNotEmpty, name);

    }

    public FoolCondition lessThan(Comparable value) {
        return new FoolCondition(Operate.lessThan, name, value);
    }

    public FoolCondition lessThanEq(Comparable value) {
        return new FoolCondition(Operate.lessThanEq, name, value);

    }

    public FoolCondition greaterThan(Comparable value) {
        return new FoolCondition(Operate.greaterThan, name, value);

    }

    public FoolCondition greaterThanEq(Comparable value) {
        return new FoolCondition(Operate.greaterThanEq, name, value);

    }

    public FoolCondition between(Comparable from, Comparable to) {
        return new FoolCondition(Operate.between, name, from, to);

    }

    public FoolCondition in(Collection values) {
        return new FoolCondition(Operate.between, name, values.toArray());

    }

}
