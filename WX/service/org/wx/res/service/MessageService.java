package org.wx.res.service;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.wx.res.model.CookMenu;
import org.wx.res.model.WxMsg;
import org.wx.res.msg.dao.CookMenuDao;
import org.wx.res.msg.dao.WxMsgDao;
import org.wx.res.util.WxUtil;

import cn.edu.myself.study.test.Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Component
public class MessageService {

	private static final Logger log = Logger.getLogger(MessageService.class);
	
	@Resource
	private WxMsgDao msgDao;
	
	@Resource
	private CookMenuDao cookDao;
	/**
	 * 保存微信信息
	 * @param msg
	 * @return
	 */
	public boolean saveWxMsg(String msg){
		
		WxMsg model = new WxMsg();
		model.setId(WxUtil.wxGetID());
		model.setUid(WxUtil.genUUID());
		model.setContent(msg);
		
		msgDao.save(model);
		return true;
	}
	
	/**
	 * 获取消息
	 * @return
	 */
	public WxMsg selectMsg(String id){
		msgDao.getMsg(id);
		System.out.println(msgDao.getMsg(id).getContent());
		
		return null;
	}
	
	public String getAnCookMenuContent(){
		StringBuffer menu = new StringBuffer();
		StringBuffer steps = new StringBuffer();
		CookMenu m = cookDao.RamQueryItem();
		String name = m.getName();
		String stuff = m.getStuff();
		String tips = m.getTips();
		JSONObject content = JSON.parseObject(m.getContent());
		
		for(int i = 0;i<content.size();i++){
			Map<String,String>map = (Map<String, String>) content.get(i+1);
			String step = map.get("step").replace("(", "").replace(")", "");
			if(!Utils.regex(step,"[0-9]+.")){
				step = String.format("%d. %s", i+1,step);
			}
			steps.append(step).append("\n");
		}
		
		menu.append(name+"\n");
		menu.append("食材:\n");
		menu.append(stuff).append("\n");
		menu.append(steps);
		if(!Utils.isStrEmpty(tips)){
			menu.append("提示：\n").append(tips);
		}
		
		return menu.toString();
	}
	
	public String getSuprise(){
		//Random r = new Random(System.currentTimeMillis());
		StringBuffer sb = new StringBuffer();
//		for(int n=0;n<6;n++){
//			int i = r.nextInt(49);
//			sb.append(i+1).append(" ");
//		}
		sb.append("本期幸运号码: 40 21 31 16 13 37 ");
		System.out.println(sb);
		return sb.toString();
	}
	
	/**
	 * 获取
	 * @return
	 */
	public String getHelpList(){
		StringBuffer sb = new StringBuffer();
		
		sb.append("1. 天气查询请输入 \"#+任意地点\"\n");
		sb.append("2. 景点查询请输入 \"@+任意地点\"\n");
		sb.append("3. 菜谱获取请输入 \"菜谱\"\n");
		sb.append("4. 更多功能请关注我们的公众号\n");
		
		return sb.toString();
	}
}
