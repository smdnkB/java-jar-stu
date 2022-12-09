package com.liu.事务;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName(value = "sys_user_table")
@Data
public class User {
    @TableId(value = "user_id",type = IdType.NONE)
    private Integer user_id;
    private String user_name;
    private String user_account;
    private String user_pwd;
    private String salt;
    private Integer role_id;
    private Integer create_time;
    private Integer status;
    private Long user_uuid;
}
