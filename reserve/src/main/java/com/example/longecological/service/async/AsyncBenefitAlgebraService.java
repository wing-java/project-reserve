package com.example.longecological.service.async;

import java.util.Map;

public interface AsyncBenefitAlgebraService {

	void dealUserOrderAlgebraBenefit(Map<String, Object> order, Map<String, Object> user, String date, String time) throws Exception;
}
