package com.zhht.project.snow;

import java.util.List;

/**
 * 主键生成策略
 * 
 * @author ningquan
 *
 */
public interface PrimaryStrategy {
    /**
     * 获取主键ID
     * 
     * @return
     */
    public abstract Long getPrimaryId();

    public abstract List<Long> getPrimaryIds(int num);
}
