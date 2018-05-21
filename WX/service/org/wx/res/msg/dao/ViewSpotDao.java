package org.wx.res.msg.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.wx.res.model.ViewSpot;

@Repository
public class ViewSpotDao extends SqlSessionDaoSupport {

	public List<ViewSpot> getAllData(){
		List<ViewSpot> list = this.getSqlSession().selectList("org.wx.res.model.ViewSpot.ViewSpotMapper.getAllData");
		return list;
	}
	
	public List<ViewSpot> getDataInIds(List<String> ids){
		List<ViewSpot> list = this.getSqlSession().selectList("org.wx.res.model.ViewSpot.ViewSpotMapper.getDataInIds",ids);
		return list;
	}
	
	public ViewSpot getDataById(String id){
		ViewSpot obj= (ViewSpot) this.getSqlSession().selectOne("org.wx.res.model.ViewSpot.ViewSpotMapper.getDataById",id);
		return obj;
	}
	
	public boolean addViewSpot(ViewSpot obj){
		int ret = this.getSqlSession().insert("org.wx.res.model.ViewSpot.ViewSpotMapper.addViewSpot",obj);
		return ret > 0;
	}
}
