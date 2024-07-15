package com.cyanrock.oms.common.vo.api;

import javax.validation.constraints.NotNull;

/**
 * @author stevenqin
 * @version V2.0
 * @create 2022/4/8 15:58
 * @description 分页查询公共请求VO
 */
public class PageReqVO {

    /**
     * 当前页
     */
    @NotNull(message = "分页参数不能为空")
    private Integer pageNumber = 1;
    /**
     * 当前分页大小
     */
    @NotNull(message = "分页参数不能为空")
    private Integer pageSize = 20;

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
