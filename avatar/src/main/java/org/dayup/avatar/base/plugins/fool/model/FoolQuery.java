
package org.dayup.avatar.base.plugins.fool.model;

public class FoolQuery {

    public static FoolPredicate with(String name) {
        return new FoolPredicate(name);
    }

}
