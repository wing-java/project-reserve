package com.example.longecological.service.async;

import java.math.BigDecimal;

public interface AsyncUserPerformanceService {

	void dealPersonPerformance(String user_id, BigDecimal performance, String date, String time) throws Exception;
	
	void dealTeamPerformance(String parent_chain, BigDecimal performance, String date, String time) throws Exception;
}
