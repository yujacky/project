package org.wx.res.msg.dao;

import java.util.Random;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.wx.res.model.CookMenu;

@Repository
public class CookMenuDao extends SqlSessionDaoSupport{

	public CookMenu RamQueryItem(){
        int start = genRandomInt();
		CookMenu model = (CookMenu) this.getSqlSession().selectOne("org.wx.res.model.CookMenu.CookMenuMapper.getRamData",start);
		return model;
	}
	
	public int getCount(){
		int cnt = (Integer) this.getSqlSession().selectOne("org.wx.res.model.CookMenu.CookMenuMapper.getDataCount");
		return cnt;
	}
	
	private int genRandomInt(){
        int max=this.getCount();
        int min=1;
        Random random = new Random();
        int start = random.nextInt(max)%(max-min+1) + min;
        
        return start;
	}
}
