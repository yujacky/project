package org.wx.res.msg.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.wx.res.model.HFCity;

@Repository
public class HFCityDao extends SqlSessionDaoSupport {

	public List<HFCity> getAllData(){
		List<HFCity> list = this.getSqlSession().selectList("org.wx.res.model.HFCity.HFCityMapper.getAllCityInfo");
		return list;
	}
	
}
