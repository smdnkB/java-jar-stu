package com.liu.ddd;

import com.liu.old.entity.Result;
import com.liu.old.service.RisCheckService;

import java.math.BigDecimal;

/**
 * 防腐层 隔离业务
 * 优化第三步 3. 调用风控微服务
 */
public class BusSafeService {

    public Result checkBusi(Long userId, Long mechantAccount, BigDecimal money) throws Exception {
        // 3. 调用风控微服务
        String code = RisCheckService.checkPayment();
        // 4. 检查交易合法性
        if ("0000".equals(code)){
            return Result.success;
        }

        return Result.fail;
    }
}
