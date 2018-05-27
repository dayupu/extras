package org.dayup.kits.avatar.web.controller;

import org.dayup.kits.avatar.model.vo.ApiClassVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/vue/apidoc/")
@RestController
public class ApiDocController {


    @GetMapping("/typeList")
    public List<ApiClassVo> getTypeList() {
        List<ApiClassVo> classVos = new ArrayList<>();
        classVos.add(new ApiClassVo(){{
            setId(1L);
            setName("String");
            setDescription("字符串字符串字符串字字符串字符串字符串字符串字符串字符串字符串字符串字符串字符串字符串字符串符串字符串字符串字符串字符串字符串字符串字符串字符串");
        }});
        return classVos;
    }
}
