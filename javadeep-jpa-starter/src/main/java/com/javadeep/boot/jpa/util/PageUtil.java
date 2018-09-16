package com.javadeep.boot.jpa.util;

import com.javadeep.boot.common.dto.PageDTO;
import com.javadeep.boot.common.dto.PageableDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 分页相关工具类
 *
 * @author javadeep
 * @since 1.0.0
 */
public final class PageUtil {

    private PageUtil() {
        throw new UnsupportedOperationException();
    }

    /**
     * PageableDTO转JPA Pageable
     *
     * @param pageableDTO PageableDTO对象
     * @return 返回转换后的JPA Pageable对象
     */
    public static Pageable pageableDTO2Pageable(PageableDTO pageableDTO) {

        Objects.requireNonNull(pageableDTO, "pageableDTO is null");

        return PageRequest.of(pageableDTO.getPageNo() - 1, pageableDTO.getPageSize(),
                SortUtil.orderDTOS2Sort(pageableDTO.getOrders()));
    }

    /**
     * JPA Page Domain对象转PageDTO对象
     *
     * @param page Page对象Domain
     * @param converter Domain到DTO的转换器
     * @param <Domain> Domain对象类型
     * @param <DTO> DTO对象类型
     * @return 返回转换后的PageDTO对象
     */
    public static <Domain, DTO> PageDTO<DTO> page2PageDTO(Page<Domain> page, Function<Domain, DTO> converter) {

        Objects.requireNonNull(page, "page is null");
        Objects.requireNonNull(converter, "converter is null");

        return PageDTO.builder(Optional.ofNullable(page.getContent())
                    .map(content -> content.stream()
                            .map(converter)
                            .collect(Collectors.toList()))
                    .orElse(Collections.emptyList()))
                .totalRecords(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .pageNo(page.getNumber() + 1)
                .pageSize(page.getSize())
                .build();
    }
}
