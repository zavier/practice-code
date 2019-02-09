package com.zavier.mybatis;

import com.zavier.mybatis.dao.BlogMapper;
import com.zavier.mybatis.pojo.Blog;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws Exception {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession session = sqlSessionFactory.openSession();
        BlogMapper mapper = session.getMapper(BlogMapper.class);

        Blog blog = mapper.selectByPrimaryKey(1);
        System.out.println(blog);
    }
}
