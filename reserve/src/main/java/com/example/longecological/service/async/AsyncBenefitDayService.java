package com.example.longecological.service.async;

import java.util.Map;

public interface AsyncBenefitDayService {

	void dealUserOrderDayBenefit(Map<String, Object> order, String date, String time) throws Exception;
}
