package com.liu.ddd;

import java.math.BigDecimal;

/**
 * 充血模型：将实体属性和会引起属性变化的方法写在一起
 * 贫血模型：只有属性和 get set方法
 */
public class AccountDo {

    private Long id;
    private Long accountNumber;
    private BigDecimal available;

    public void withdraw(BigDecimal money){
        // 转入操作
        available = available.add(money);
    }

    public void deposit(BigDecimal money) throws Exception {
        if (money.intValue()<available.intValue()) throw new Exception("余额不足");
        // 转出操作
        available = available.subtract(money);
    }
}
