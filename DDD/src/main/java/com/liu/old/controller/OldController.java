package com.liu.old.controller;

import com.liu.old.entity.Result;
import com.liu.old.service.OldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

/**
 * 业务需求：用户购买商品后支付
 * 1. 从数据库查询 用户和商家的账户信息
 * 2. 调用风控系统的微服务，进行风险评估
 * 3. 实现转入转出操作，计算双方金额变化，保存到数据库
 * 4. 发送交易情况给kafka，进行后续审计和风控
 */
public class OldController {
    private OldService oldService;
    public Result pay(String merchantAccount, BigDecimal amount, HttpSession session) throws Exception {
        Long userTd = (Long) session.getAttribute("userId");
        return oldService.pay(userTd,merchantAccount,amount);
    }

}
