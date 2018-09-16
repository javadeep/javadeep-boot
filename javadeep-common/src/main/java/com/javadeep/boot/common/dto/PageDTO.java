package com.javadeep.boot.common.dto;

import com.javadeep.boot.common.base.Preconditions;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 分页DTO，建议Builder模式构建
 *
 * @author javadeep
 * @since 1.0.0
 */
public class PageDTO<DTO> {

    /**
     * 总记录数
     */
    private long totalRecords = -1;

    /**
     * 总页数
     */
    private int totalPages = -1;

    /**
     * 当前页码(从1开始）
     */
    private int pageNo = -1;

    /**
     * 每页显示记录数
     */
    private int pageSize = -1;

    /**
     * DTO数据
     */
    private List<DTO> datas;

    /**
     * Builder构造器
     *
     * @param datas DTO数据
     * @param <DTO> 数据的类型
     * @return 返回构造器
     */
    public static <DTO> Builder<DTO> builder(List<DTO> datas) {
        return new Builder<>(datas);
    }

    /**
     * PageDTO的转换
     *
     * @param pageDTO 原PageDTO
     * @param converter 转换器
     * @param <DTO> 原DTO类型
     * @param <T> 新DTO类型
     * @return 返回转换后的PageDTO
     */
    public static <DTO, T> PageDTO<T> of(PageDTO<DTO> pageDTO, Function<? super DTO, T> converter) {
        Objects.requireNonNull(pageDTO, "page is null");
        Objects.requireNonNull(converter, "converter is null");
        return PageDTO.builder(Optional.ofNullable(pageDTO.getDatas())
                    .map(datas -> datas.stream()
                        .map(converter)
                        .collect(Collectors.toList()))
                    .orElse(Collections.emptyList()))
                .totalRecords(pageDTO.getTotalRecords())
                .totalPages(pageDTO.getTotalPages())
                .pageNo(pageDTO.getPageNo())
                .pageSize(pageDTO.getPageSize())
                .build();
    }

    /**
     * Builder构造器
     *
     * @param <DTO>
     */
    public static class Builder<DTO> {
        private long totalRecords = -1;
        private int totalPages = -1;
        private int pageNo = -1;
        private int pageSize = -1;
        private final List<DTO> datas;

        private Builder(List<DTO> datas) {
            Objects.requireNonNull(datas, "datas is null");
            this.datas = datas;
        }

        public final Builder<DTO> totalRecords(long totalRecords) {
            Preconditions.checkArgument(totalRecords >= 0, "totalRecords must >= 0");
            this.totalRecords = totalRecords;
            return this;
        }

        public final Builder<DTO> totalPages(int totalPages) {
            Preconditions.checkArgument(totalPages >= 0, "totalPages must >= 0");
            this.totalPages = totalPages;
            return this;
        }

        public final Builder<DTO> pageNo(int pageNo) {
            Preconditions.checkArgument(pageNo > 0, "pageNo must > 0");
            this.pageNo = pageNo;
            return this;
        }

        public final Builder<DTO> pageSize(int pageSize) {
            Preconditions.checkArgument(pageSize > 0, "pageSize must > 0");
            this.pageSize = pageSize;
            return this;
        }

        public final PageDTO<DTO> build() {
            Objects.requireNonNull(datas, "datas is null");
            Preconditions.checkArgument(totalRecords >= 0, "totalRecords must >= 0");
            Preconditions.checkArgument(totalPages >= 0, "totalPages must >= 0");
            Preconditions.checkArgument(pageNo > 0, "pageNo must > 0");
            Preconditions.checkArgument(pageSize > 0, "pageSize must > 0");

            PageDTO<DTO> pageDTO = new PageDTO<>();
            pageDTO.setDatas(datas);
            pageDTO.setTotalRecords(totalRecords);
            pageDTO.setTotalPages(totalPages);
            pageDTO.setPageNo(pageNo);
            pageDTO.setPageSize(pageSize);
            return pageDTO;
        }
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

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

    public List<DTO> getDatas() {
        return datas;
    }

    public void setDatas(List<DTO> datas) {
        this.datas = datas;
    }
}
