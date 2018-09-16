package com.javadeep.boot.common.dto;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DTOSupportTest {

    @Test
    public void testFillOne() {

        List<BizDTO> bizDTOList = new ArrayList<>();
        bizDTOList.add(new BizDTO(1, 1));
        bizDTOList.add(new BizDTO(2, 2));
        bizDTOList.add(new BizDTO(3, 2));

        DTOSupport.fillOne(bizDTOList, BizDTO::getUserId, this::getUserDTOList, UserDTO::getId,
                (bizDTO, userDTO) -> bizDTO.setUsername(userDTO.getName()));
        for (BizDTO bizDTO : bizDTOList) {
            int id = bizDTO.getUserId();
            Assert.assertEquals("name:" + id, bizDTO.getUsername());
        }
    }

    @Test
    public void testFillOneByDict() {
        List<BizDTO> bizDTOList = new ArrayList<>();
        bizDTOList.add(new BizDTO(1, 1));
        bizDTOList.add(new BizDTO(2, 2));
        bizDTOList.add(new BizDTO(3, 2));

        DTOSupport.fillOneByDict(bizDTOList, BizDTO::getUserId, this::createUserDTOList, UserDTO::getId,
                (bizDTO, userDTO) -> bizDTO.setUsername(userDTO.getName()));
        for (BizDTO bizDTO : bizDTOList) {
            int id = bizDTO.getUserId();
            Assert.assertEquals("name:" + id, bizDTO.getUsername());
        }
    }

    @Test
    public void testFillList() {
        List<BizDTO> bizDTOList = new ArrayList<>();
        bizDTOList.add(new BizDTO(1, 1));
        bizDTOList.add(new BizDTO(2, 2));
        bizDTOList.add(new BizDTO(3, 2));

        DTOSupport.fillList(bizDTOList, BizDTO::getUserId, this::getUserDTOList, UserDTO::getId,
                (bizDTO, userDTOs) -> bizDTO.setUsername(userDTOs.get(0).getName()));
        for (BizDTO bizDTO : bizDTOList) {
            int id = bizDTO.getUserId();
            Assert.assertEquals("name:" + id, bizDTO.getUsername());
        }
    }

    @Test
    public void testFillListByDict() {
        List<BizDTO> bizDTOList = new ArrayList<>();
        bizDTOList.add(new BizDTO(1, 1));
        bizDTOList.add(new BizDTO(2, 2));
        bizDTOList.add(new BizDTO(3, 2));

        DTOSupport.fillListByDict(bizDTOList, BizDTO::getUserId, this::createUserDTOList, UserDTO::getId,
                (bizDTO, userDTOs) -> bizDTO.setUsername(userDTOs.get(0).getName()));
        for (BizDTO bizDTO : bizDTOList) {
            int id = bizDTO.getUserId();
            Assert.assertEquals("name:" + id, bizDTO.getUsername());
        }
    }

    private List<UserDTO> getUserDTOList(Collection<Integer> ids) {

        List<UserDTO> userDTOList = new ArrayList<>();
        for (int id : ids) {
            userDTOList.add(new UserDTO(id, "name:" + id));
        }
        return userDTOList;
    }

    private List<UserDTO> createUserDTOList() {

        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        List<UserDTO> userDTOList = new ArrayList<>();
        for (int id : ids) {
            userDTOList.add(new UserDTO(id, "name:" + id));
        }
        return userDTOList;
    }


}
