package com.rtmap.airport.group;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rtmap.airport.group.entity.User;
import com.rtmap.airport.group.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class UserToolTest {
    @Resource
    UserMapper userMapper;

    @Test
    public void Test1() {
        IPage<User> userIPage = userMapper.selectPage(new Page<>(1, 10), null);
        System.out.println(userIPage.getRecords().toString());
    }

    @Test
    public void Test2() {
        //使用selectpage方法，sql语句中有limit了
        Page<User> page = new Page<>(1, 5);
        IPage<User> selectPage = userMapper.selectPage(page, null);
        System.out.println(selectPage);
        System.out.println("================= 相关的分页信息 ==================");
        System.out.println("总条数:" + selectPage.getTotal());
        System.out.println("当前页码:" + selectPage.getCurrent());
        System.out.println("总页数:" + selectPage.getPages());
        System.out.println("每页显示条数:" + selectPage.getSize());
        System.out.println("是否有上一页:" + page.hasPrevious());
        System.out.println("是否有下一页:" + page.hasNext());
        System.out.println("查询结果:");
        List<User> list = selectPage.getRecords();
        list.forEach(o -> System.out.println(o));
        page.setRecords(list);
    }

    @Test
    public void Test3() {
        Page<User> page = new Page<>(2, 10);
        IPage<User> selectPage = userMapper.selectPageBySex(page, "男");
        System.out.println("================= 相关的分页信息 ==================");
        System.out.println("总条数:" + selectPage.getTotal());
        System.out.println("当前页码:" + selectPage.getCurrent());
        System.out.println("总页数:" + selectPage.getPages());
        System.out.println("每页显示条数:" + selectPage.getSize());
        System.out.println("是否有上一页:" + page.hasPrevious());
        System.out.println("是否有下一页:" + page.hasNext());
        System.out.println("分页结果");
        selectPage.getRecords().forEach(System.out::println);
    }

    @Test
    public void Test4() {
        //条件为null，就是删除全表，执行分析插件会终止该操作
        userMapper.delete(null);
    }

    @Test
    //正常测试，更新数据时会校验版本号，新旧版本号一致时，表示当前数据未被其他事务更新过，更新通过，版本号自动+1；
    public void Test5() {
        // 查询一条记录
        User user = userMapper.selectById(2);
        //更新
        user.setName("version1");
        userMapper.updateById(user);
        System.out.println("over");
//        或者
//        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
//        updateWrapper.set("name", "version");
//        updateWrapper.eq("id",2);
//        userMapper.update(user, updateWrapper);
    }
}
