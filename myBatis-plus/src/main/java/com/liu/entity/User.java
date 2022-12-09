package com.liu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "user") // value指定表名 , 不指定的话 默认把类小写当表名
public class User {

    @TableId(value = "id",type = IdType.AUTO) // auto
    private Long uid;
    private String name;
    private Integer age;

    @TableField(value = "eamil")
    private String eamil;


    public User(String name, Integer age, String eamil) {
        this.name = name;
        this.age = age;
        this.eamil = eamil;
    }
}
