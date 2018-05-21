package org.wx.res.msg.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.wx.res.model.WxMsg;
import org.wx.res.util.WxUtil;

@Repository
public class WxMsgDao extends SqlSessionDaoSupport{

	public boolean save(WxMsg model){
		int ret = this.getSqlSession().insert("org.wx.res.model.WxMsg.WxMsgMapper.save", model);
		return true;
	}
	
	public WxMsg getMsg(String id){
		WxMsg msg = (WxMsg) this.getSqlSession().selectOne("org.wx.res.model.WxMsg.WxMsgMapper.get",id);
		return msg;
	}
}
