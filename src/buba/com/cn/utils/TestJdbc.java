package buba.com.cn.utils;

import buba.com.cn.entity.CartItem;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Random;

/**
 * @program: firstJavaWeb
 * @ClassName TestJdbc
 * @description:
 * @author: MSY
 * @create: 2022-09-20 15:21
 * @Version 1.0
 **/
public class TestJdbc {
    public static void main(String[] args) {
//        //创建JDBCTemplate对象
//        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDateSource());
//        //3.调用方法
//        String sql = "select count(*) from t_soldier";
//        int count = template.queryForObject(sql, Integer.class);
//        System.out.println(count);


        // 给图书随机分配类型
//        String sql = "update t_book set type_id = ? where book_id = ?";
//        Random random = new Random();
//        for (int i = 0; i < 290; i++) {
//            int r = random.nextInt(119-31+1)+31;
//            System.out.println(r);
//            template.update(sql,r,i+364);
//        }

        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDateSource());
        String sql = "select * from t_cart_item where user = ?";
        List<CartItem> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CartItem.class), 45);
        System.out.println(query);
    }

}
