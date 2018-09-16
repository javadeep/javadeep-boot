package com.javadeep.boot.jpa.util;

import com.javadeep.boot.common.dto.OrderDTO;
import com.javadeep.boot.common.dto.PageDTO;
import com.javadeep.boot.common.dto.PageableDTO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public class PageUtilTest {

    @Test
    public void testPageableDTO2Pageable()  {
        PageableDTO dto = new PageableDTO();
        dto.setPageNo(1);
        dto.setPageSize(10);
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setAsc(false);
        orderDTO.setProperty("id");
        dto.setOrders(new ArrayList<OrderDTO>(){{add(orderDTO);}});

        Pageable pageable = PageUtil.pageableDTO2Pageable(dto);
        Assert.assertEquals(0, pageable.getPageNumber());
        Assert.assertEquals(10, pageable.getPageSize());
        Assert.assertEquals(Sort.Order.desc("id"), pageable.getSort().getOrderFor("id"));
    }

    @Test
    public void testPage2PageDTO() {
        List<Integer> datas = new ArrayList<>();
        datas.add(1);
        datas.add(2);
        datas.add(3);
        Page<Integer> pages = new PageImpl<>(datas, PageRequest.of(2, 3), 100);

        PageDTO<String> pageDTO = PageUtil.page2PageDTO(pages, String::valueOf);
        Assert.assertEquals(new ArrayList<String>(){{add("1"); add("2"); add("3");}}, pageDTO.getDatas());
        Assert.assertEquals(3, pageDTO.getPageNo());
        Assert.assertEquals(3, pageDTO.getPageSize());
        Assert.assertEquals(100, pageDTO.getTotalRecords());
        Assert.assertEquals(34, pageDTO.getTotalPages());
    }
}
