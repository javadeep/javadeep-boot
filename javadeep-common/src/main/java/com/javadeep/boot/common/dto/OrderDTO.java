package com.javadeep.boot.common.dto;

/**
 * 排序DTO
 *
 * @author javadeep
 * @since 1.0.0
 */
public class OrderDTO {

    private boolean asc = true;
    private String property;

    public boolean isAsc() {
        return asc;
    }

    public void setAsc(boolean asc) {
        this.asc = asc;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }
}
