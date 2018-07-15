
package org.dayup.fun.aide.jpa.extend.fool.supplier;

public class FoolQuery {

    public static FoolPredicate with(String name) {
        return new FoolPredicate(name);
    }

}
