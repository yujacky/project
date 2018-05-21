package org.wx.res.msg.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.wx.res.model.HeFengWeatherH5;

@Repository
public class HeFengWeatherDao extends SqlSessionDaoSupport{

	public HeFengWeatherH5 getWeather(String cityid){
		HeFengWeatherH5 model = (HeFengWeatherH5) this.getSqlSession()
				.selectOne("org.wx.res.model.HeFengWeatherH5.HeFengWeatherH5Mapper.getLocationWeather",cityid);
		return model;
	}
}
