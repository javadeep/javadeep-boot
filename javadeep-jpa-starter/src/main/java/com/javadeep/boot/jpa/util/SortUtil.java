package com.javadeep.boot.jpa.util;

import com.javadeep.boot.common.dto.OrderDTO;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 排序相关工具类
 *
 * @author javadeep
 * @since 1.0.0
 */
public final class SortUtil {

    private SortUtil() {
        throw new UnsupportedOperationException();
    }

    /**
     * OrderDTO列表转换JPA Sort对象
     *
     * @param orderDTOS 要转换的orderDTO列表
     * @return 转换后的Sort对象
     */
    public static Sort orderDTOS2Sort(@Nullable Collection<OrderDTO> orderDTOS) {
        return Optional.ofNullable(orderDTOS).map(dtos -> Sort.by(dtos.stream()
                    .map(dto -> new Sort.Order(dto.isAsc() ? Sort.Direction.ASC : Sort.Direction.DESC,
                            dto.getProperty()))
                    .collect(Collectors.toList())))
                .orElse(Sort.unsorted());
    }
}
