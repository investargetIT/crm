package com.cyanrocks.wms.vo.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author wjq
 * @Date 2024/7/25 16:02
 */
@Data
public class FieldsDTO implements Serializable {

    private String label;
    private String value;
}
