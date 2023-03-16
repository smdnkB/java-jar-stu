package com.liu.old.service;

import com.liu.old.dao.AccountDao;
import com.liu.old.dao.KafkaTemplate;
import com.liu.old.entity.AccountDo;
import com.liu.old.entity.Result;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OldService {

    private AccountDao accountDao; // 操作数据库
    private KafkaTemplate<String,String> kafkaTemplate; // 操作kafka
    private RisCheckService risCheckService; // 风控微服务接口

    /**
     * 分析这个支付方法有什么缺陷
     * 1. 多个流程在一起，修改一个流程，需要整个测试一遍
     * 2. 不易扩展，换了kafka 换了数据库 怎么办
     * @param userId
     * @param merchantAccount
     * @param amount
     * @return
     * @throws Exception
     */
    public Result pay(Long userId, String merchantAccount, BigDecimal amount) throws Exception {
        // 1. 从数据库读取数据
        AccountDo clientDo = accountDao.selectByUserId(userId);
        AccountDo merchantDo = accountDao.selectByAccountNumber(merchantAccount);
        // 2. 业务参数校验
        if (amount.intValue()>(clientDo.getAvailable().intValue())){
            throw new Exception("No Money");
        }
        // 3. 调用风控微服务
        String code = RisCheckService.checkPayment();
        // 4. 检查交易合法性
        if ("0000".equals(code)){
            throw new Exception("非法");
        }

        // 5. 计算新值，更新字段
        BigDecimal newSource = clientDo.getAvailable().subtract(amount);
        BigDecimal newTarget = clientDo.getAvailable().add(amount);

        // 6. 更新到数据库
        accountDao.update(clientDo);
        accountDao.update(merchantDo);

        // 7. 发送审计消息
        String message = newSource+","+newTarget;
        kafkaTemplate.send("MM-TOPIC",message);

        return Result.success;

    }
}
