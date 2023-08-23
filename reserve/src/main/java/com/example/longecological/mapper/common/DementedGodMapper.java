package com.example.longecological.mapper.common;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface DementedGodMapper {
    /**
     * 查询刺客订单
     * @param order_no
     * @return
     */
    Map<String, Object> getDementedGodTradeByOrderNo(@Param("out_trade_no") String order_no);

    /**
     * 更新刺客订单状态
     * @param map
     * @return
     */
    int updateOrderStatus(@Param("map") Map<String, Object> map);

    /**
     * 更新充值订单状态
     * @param map
     * @return
     */
    int updateRechargeOrderStatus(@Param("map") Map<String, Object> map);

    /**
     * 新增
     * @param map
     * @return
     */
    int insertDementedGodTrade(@Param("map") Map<String, Object> map);
}
