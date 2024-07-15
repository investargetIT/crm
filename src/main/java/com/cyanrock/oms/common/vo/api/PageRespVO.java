package com.cyanrock.oms.common.vo.api;

import java.util.Collections;
import java.util.List;


/**
 * @author stevenqin
 * @version V2.0
 * @create 2022/4/8 15:58
 * @description 分页查询公共返回VO
 */
public class PageRespVO<T> {

    /**
     * 分页大小
     */
    private Integer pageSize;

    /**
     * 当前页
     */
    private Integer pageNum;

    /**
     * 总记录数
     */
    private Long total;

    /**
     * 当前页结果集
     */
    private List<T> list;

    public PageRespVO() {
    }

    /**
     * 初始化一个只有分页参数的构造器
     *
     * @param pageNum  当前页
     * @param pageSize 分页大小
     */
    public PageRespVO(Integer pageNum, Integer pageSize) {

        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = 0L;
        this.list = Collections.emptyList();
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
