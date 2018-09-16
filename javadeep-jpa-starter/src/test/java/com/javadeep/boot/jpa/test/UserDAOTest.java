package com.javadeep.boot.jpa.test;

import com.javadeep.boot.jpa.BaseTest;
import com.javadeep.boot.jpa.bo.UserBO;
import com.javadeep.boot.jpa.dao.UserDAO;
import com.javadeep.boot.jpa.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class UserDAOTest extends BaseTest {

    @Autowired
    private UserDAO userDAO;

    @Test
    public void testGetEm() {
        EntityManager em = userDAO.getEm();
        Assert.assertNotNull(em);
    }

    @Test
    public void testGetDomainClass() {
        Class<User> domainClass = userDAO.getDomainClass();
        Assert.assertNotNull(domainClass);
    }

    @Test
    public void testInsertAndFlush() {
        User user = new User();
        user.setId(99L);
        user.setUsername("Deep");
        user.setCreateBy(1L);
        user.setUpdateBy(1L);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        User newUser = userDAO.insertAndFlush(user);
        userDAO.delete(newUser);
        Optional<User> deleteUser = userDAO.findById(99L);
        Assert.assertFalse(deleteUser.isPresent());
    }

    @Test
    public void testInsertAll() {
        User user = new User();
        user.setId(99L);
        user.setUsername("Deep");
        user.setCreateBy(1L);
        user.setUpdateBy(1L);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        List<User> users = userDAO.insertAll(new ArrayList<User>(){{add(user);}});
        User newUser = users.get(0);
        userDAO.delete(newUser);
        Optional<User> deleteUser = userDAO.findById(99L);
        Assert.assertFalse(deleteUser.isPresent());
    }

    @Test
    public void testFindAll() {
        List<User> userList = userDAO.findAll();
        Assert.assertEquals(5, userList.size());
        User user = userList.get(0);
        Assert.assertEquals(Long.valueOf(1), user.getId());
        Assert.assertEquals("JAVADEEP", user.getUsername());
        Assert.assertEquals(Long.valueOf(0), user.getCreateBy());
        Assert.assertEquals(Long.valueOf(0), user.getUpdateBy());
        Assert.assertNotNull(user.getCreateTime());
        Assert.assertNotNull(user.getUpdateTime());
    }

    @Test
    public void testSaveAndFlush() {
        User user = new User();
        user.setId(99L);
        user.setUsername("Deep");
        user.setCreateBy(1L);
        user.setUpdateBy(1L);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userDAO.saveAndFlush(user);
        Optional<User> newUser = userDAO.findById(99L);
        Assert.assertTrue(newUser.isPresent());
        userDAO.delete(newUser.get());
        Optional<User> deleteUser = userDAO.findById(99L);
        Assert.assertFalse(deleteUser.isPresent());
    }

    @Test
    public void testFindAllBO() {

        List<UserBO> userBOList = userDAO.findAllBO(UserBO.class, (root, query, cb) -> {
            query.groupBy(root.get("groupId"));
            query.multiselect(root.get("groupId"), cb.count(root).alias("abc"));
            return query.getRestriction();
        });
        Assert.assertEquals(3, userBOList.size());
    }

    @Test
    public void testFindAllBOSort() {

        List<UserBO> userBOList = userDAO.findAllBO(UserBO.class, (root, query, cb) ->
            query.groupBy(root.get("groupId"))
                .multiselect(root.get("groupId"), cb.count(root))
                .orderBy(cb.desc(cb.count(root)))
                .where(cb.le(root.get("id"), 4))
                .getRestriction());
        Assert.assertEquals(3, userBOList.size());
    }
}
