package com.codepig.spy.mapper;

import com.codepig.spy.model.QueryVo;
import com.codepig.spy.model.User;

import java.util.List;

/**
 * user用户的持久层接口
 */
public interface UserMapper {
    /**
     * 查询所有用户
     * @return
     */

    List<User> findAll();
    /**
     * 插入用户
     */
    void insertUser(User user);

    /**
     *  更新用戶
     */
    void updateUser(User user);

    /**
     * 根据id删除用户
     */
    void deleteUser(Integer userId);

    /**
     * 根据id查询用户信息
     */
    User findById(Integer id);

    /**
     * 根据名称模糊查询用户信息
     */
    List<User> findByName(String name);

    /**
     * 根据queryvo中的条件查询用户
     */
    List<User> findUserByVo(QueryVo vo);
}
