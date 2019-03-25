package com.own.order.infrastructure.basetypes;

import com.own.order.infrastructure.util.ThreadLocalVar;
import org.springframework.transaction.annotation.Transactional;

/**
 * 所有 Service 的基类
 */
@Transactional
public abstract class BaseService {
    /**
     * 获取当前登录用户所属医院编码
     */
    public String getHospitalCode() {
        return ThreadLocalVar.getHospitalCode();
    }
}