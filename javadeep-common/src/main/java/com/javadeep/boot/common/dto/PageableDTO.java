package com.javadeep.boot.common.dto;

import java.util.List;

/**
 * 分页查询DTO
 *
 * @author javadeep
 * @since 1.0.0
 */
public class PageableDTO {

    /**
     * 当前页
     */
    private int pageNo;

    /**
     * 每页记录数
     */
    private int pageSize;

    /**
     * 排序
     */
    private List<OrderDTO> orders;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }
}
