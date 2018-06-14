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
 *   Class Name: org.dayup.business.jpa.fool.model.FoolCondition
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
