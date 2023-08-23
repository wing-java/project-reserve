package com.example.longecological.service.async.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.constant.BenefitRecordTypeContants;
import com.example.longecological.mapper.async.AsyncBenefitMapper;
import com.example.longecological.mapper.wallet.UserWalletMapper;
import com.example.longecological.service.async.AsyncBenefitAlgebraService;
import com.example.longecological.utils.string.StringUtil;
import com.example.longecological.utils.time.TimeUtil;

@Service
public class AsyncBenefitAlgebraServiceImpl implements AsyncBenefitAlgebraService {
	
	@Autowired
	AsyncBenefitMapper asyncBenefitMapper;
	@Autowired
	UserWalletMapper userWalletMapper;

	@Override
	public void dealUserOrderAlgebraBenefit(Map<String, Object> order, Map<String, Object> user, String date, String time) throws Exception {
		//查询层级及收益
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("parent_chain", StringUtil.getMapValue(user, "parent_chain"));
		param.put("algebra", StringUtil.getMapValue(user, "algebra"));
		List<Map<String, Object>> parentList = asyncBenefitMapper.getUserOrderAlgebraList(param);
		if(parentList!=null && parentList.size()>0) {
			for(Map<String, Object> parent : parentList) {
				//计算收益
				BigDecimal benefit = new BigDecimal(StringUtil.getMapValue(order, "cash_num")).multiply(new BigDecimal(StringUtil.getMapValue(parent, "rate"))).setScale(2, BigDecimal.ROUND_DOWN);
				if(benefit.compareTo(BigDecimal.ZERO)>0) {
					//生成订单号
					String order_no = StringUtil.getDateTimeAndRandomForID();
					//更新钱包
					this.editUserWallet(StringUtil.getMapValue(parent, "id"), benefit, order_no, Integer.parseInt(StringUtil.getMapValue(parent, "rank")));
					//保存日志
					this.addUserRewardToAlgebra(StringUtil.getMapValue(parent, "id"), StringUtil.getMapValue(order, "order_no"), StringUtil.getMapValue(order, "order_id"), 
							benefit, StringUtil.getMapValue(parent, "rank"), StringUtil.getMapValue(parent, "rate"), order_no);
				}
			}
		}
	}
	
	public void editUserWallet(String user_id, BigDecimal benefit, String order_no, int rank) throws Exception{
		System.out.println("rank:"+rank);
		Map<String, Object> edit_user = new HashMap<>();
		edit_user.put("user_id", user_id);
		edit_user.put("balance_num", benefit);
		edit_user.put("total_benefit", benefit);
		if(1==rank) {
			edit_user.put("op_type", BenefitRecordTypeContants.benefit_record_type_15);
			edit_user.put("first_benefit", benefit);
		}else if(2==rank) {
			edit_user.put("op_type", BenefitRecordTypeContants.benefit_record_type_16);
			edit_user.put("second_benefit", benefit);
		}else if(3==rank) {
			edit_user.put("op_type", BenefitRecordTypeContants.benefit_record_type_17);
			edit_user.put("third_benefit", benefit);
		}else {
			throw new Exception("分享收益更新异常");
		}
		edit_user.put("op_order_no", order_no);
		edit_user.put("up_date", TimeUtil.getDayString());
		edit_user.put("up_time", TimeUtil.getTimeString());
		int num = userWalletMapper.updateUserWalletNum(edit_user);
		if(num != 1) throw new Exception("分享收益更新异常");
	}
	
	public void addUserRewardToAlgebra(String user_id, String buy_order_no, String buy_order_id, BigDecimal benefit, String rank, String rate,
			String order_no) throws Exception{
		Map<String, Object> record = new HashMap<>();
		record.put("user_id", user_id);
		record.put("order_no", order_no);
		record.put("benefit", benefit);
		record.put("buy_order_no", buy_order_no);
		record.put("buy_order_id", buy_order_id);
		record.put("rank", rank);
		record.put("rate", rate);
		record.put("cre_date", TimeUtil.getDayString());
		record.put("cre_time", TimeUtil.getTimeString());
		int num = asyncBenefitMapper.addUserRewardToAlgebra(record);
		if(num != 1) throw new Exception("分享收益记录异常");
	}

}
