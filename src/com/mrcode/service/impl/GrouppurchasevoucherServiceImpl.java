package com.mrcode.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mrcode.base.BaseDaoImpl;
import com.mrcode.base.BaseServiceImpl;
import com.mrcode.model.Customer;
import com.mrcode.model.Grouppurchasevoucher;
import com.mrcode.service.GrouppurchasevoucherService;


@Service
@Transactional
public class GrouppurchasevoucherServiceImpl extends BaseServiceImpl<Grouppurchasevoucher> 
	implements GrouppurchasevoucherService{

	@Resource(name="grouppurchasevoucherDaoImpl")
	public void setBaseDao(BaseDaoImpl<Grouppurchasevoucher> baseDao){
		super.setBaseDao(baseDao);
	}

	public List<Grouppurchasevoucher> getByCust(Customer customer)
			throws Exception {
		// TODO 查询本消费者的所有团购券
		String hql = "from Grouppurchasevoucher gp left join fetch gp.roomtype tp " +
				" left join fetch tp.pictures left join fetch tp.hotel where gp.customer=:customer and gp.used=0";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("customer", customer);
		List<Grouppurchasevoucher> gps = this.getBaseDao().findByHql(
				hql, param, null, "gp.roomtype, gp.createTime");
		
		return gps;
	}
	
	
}