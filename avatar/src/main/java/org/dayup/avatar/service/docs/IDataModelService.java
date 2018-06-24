
package org.dayup.avatar.service.docs;

import org.dayup.avatar.web.model.vo.DataClassVo;
import org.dayup.avatar.web.model.vo.DataTypeVo;

import java.util.List;

public interface IDataModelService {

    List<DataClassVo> getDataClassListWithLibId(Long libId);

    List<DataTypeVo> getDataTypesWithLibId(Long libId);

    DataClassVo saveClass(DataClassVo dataClassVo);

    DataClassVo getClass(Long classId);

    void drop(Long classId);
}
