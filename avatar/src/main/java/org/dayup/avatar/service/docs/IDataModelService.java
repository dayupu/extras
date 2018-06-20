package org.dayup.avatar.service.docs;

import org.dayup.avatar.model.vo.DataClassVo;
import org.dayup.avatar.model.vo.DataTypeVo;

import java.util.List;

public interface IDataModelService {

    List<DataClassVo> getDataClassListWithLibId(Long libId);

    List<DataTypeVo> getDataTypesWithLibId(Long libId);
}
