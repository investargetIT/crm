package com.cyanrocks.dashboard.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cyanrocks.common.vo.api.ApiData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author wjq
 * @Date 2024/7/19 14:59
 */
@RestController
@RequestMapping("/bi")
public class BiController {

    @Autowired
    EntityManager entityManager;

    @GetMapping("/call-proc")
    public ApiData getCellProc(HttpServletRequest request){
        String procName = request.getParameter("name");
        String parametersList = request.getParameter("params");
        String flush = request.getParameter("flush");

        StoredProcedureQuery result = this.entityManager.createNamedStoredProcedureQuery("存储过程名称");
        JSONArray paramList = JSONUtil.parseArray(parametersList);
        paramList.forEach(param->{
            result.setParameter("param", param);
        });
        result.execute();

        Integer nums = (Integer)result.getOutputParameterValue("num");
        List<Object> resultList = result.getResultList();
        System.out.println(resultList.toString());
        System.out.println(nums);
        return new ApiData(444, "接口临时关闭,等候开放..");
    }

}
