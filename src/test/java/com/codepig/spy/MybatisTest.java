package com.codepig.spy;


import com.codepig.spy.mapper.UserMapper;
import com.codepig.spy.model.QueryVo;
import com.codepig.spy.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest {

    private InputStream in;
    private SqlSession sqlSession;
    private UserMapper userMapper;

    @Before
    public void init() throws IOException {
        //1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        sqlSession = factory.openSession();
        //4.获取mapper的代理对象
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    /**
     * 释放资源
     * @throws IOException
     */
    @After
    public void destory() throws IOException {
        //提交事务
        sqlSession.commit();

        sqlSession.close();
        in.close();
    }

    @Test
    public void testFindAll() {
        //执行查询所有方法
        List<User> users = userMapper.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testInsert(){
        User user = new User();

        user.setUserName("mybatis insertUser");
        user.setUserAddress("shanxi");
        user.setUserSex("男");
        user.setUserBirthday(new Date());
        System.out.println(user);

        //执行插入用户方法
        userMapper.insertUser(user);
        System.out.println(user);
    }

    /**
     * 更新操作：注意需要设置ID，因为更新操作需要指定某一个用户
     */
    @Test
    public void testUpdate() {
        User user = new User();
        user.setUserId(1);
        user.setUserName("update name");
        user.setUserAddress("shanghai");
        user.setUserSex("男");
        user.setUserBirthday(new Date());

        userMapper.updateUser(user);
    }

    /**
     * 删除操作：
     */
    @Test
    public void testDelete() {
        userMapper.deleteUser(1);
    }

    /**
     * 根据id查找用户操作：
     */
    @Test
    public void testFindById() {
        User user = userMapper.findById(1);
        System.out.println(user);
    }

    /**
     * 模糊查询
     */
    @Test
    public void testFindByName() {
        List<User> users = userMapper.findByName("%te%");
        for (User user : users) {
            System.out.println(user );
        }
    }

    /**
     * 根据user查询
     */
    @Test
    public void testFindUserByVo() {
        QueryVo queryVo = new QueryVo();
        User user = new User();
        user.setUserName("%te%");
        queryVo.setUser(user);
        List<User> users = userMapper.findUserByVo(queryVo);
        for (User r : users) {
            System.out.println(r);
        }
    }
}
