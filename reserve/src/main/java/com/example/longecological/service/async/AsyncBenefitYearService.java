package com.example.longecological.service.async;

import java.util.Map;

public interface AsyncBenefitYearService {

	void dealUserOrderYearBenefit(Map<String, Object> order, String date, String time) throws Exception;
}
