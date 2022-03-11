package com.rtmap.airport.group.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;


@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@TableName(value = "user")//指定表名，也可以不指定，该bean继承Model后，可做curd操作
public class User extends Model<User> {
    private static final long serialVersionUID = -75834325638879548L;
    //value与数据库主键列名一致，若实体类属性名与表主键列名一致可省略value
    @TableId(value = "id", type = IdType.AUTO)//指定自增策略
    private Integer id;
    private String name;
    private String sex;
    private String pwd;
    @NotBlank(message = "邮箱不能为空")
    private String email;
    private Integer age;
    private int flag;

    @Version
    private Long version;

    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    //上次修改时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    public void println() {
        System.out.println(this);
    }

    @Override
    public Serializable pkVal() {
        return id;
    }

}
