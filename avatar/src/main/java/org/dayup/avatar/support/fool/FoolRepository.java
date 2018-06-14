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
 *   Class Name: org.dayup.business.jpa.fool.FoolRepository
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

package org.dayup.avatar.support.fool;

import org.dayup.avatar.support.fool.model.FoolCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import java.util.Collection;
import java.util.List;


@NoRepositoryBean
public interface FoolRepository<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    List<T> search(Collection<FoolCondition> conditions);

    Page<T> search(Collection<FoolCondition> conditions, Pageable pageable);

}
