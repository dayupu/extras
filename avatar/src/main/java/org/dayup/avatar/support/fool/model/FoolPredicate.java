/*
 * *************************************************************************************
 *
 *   Project:        ZXQ
 *
 *   Copyright ©     2014-2017 Banma Technologies Co.,Ltd
 *                   All rights reserved.
 *
 *   This software is supplied only under the terms of a license agreement,
 *   nondisclosure agreement or other written agreement with Banma Technologies
 *   Co.,Ltd. Use, redistribution or other disclosure of any parts of this
 *   software is prohibited except in accordance with the terms of such written
 *   agreement with Banma Technologies Co.,Ltd. This software is confidential
 *   and proprietary information of Banma Technologies Co.,Ltd.
 *
 * *************************************************************************************
 *
 *   Class Name: org.dayup.business.jpa.fool.model.FoolPredicate
 *
 *   General Description:
 *
 *   Revision History:
 *                            Modification
 *    Author                Date(MM/DD/YYYY)   JiraID           Description of Changes
 *    *********************   ************    **********     *****************************
 *    chengsong.lcs             2018-06-13
 *
 * **************************************************************************************
 */

package org.dayup.avatar.support.fool.model;

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
