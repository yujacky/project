/**
 * 
 */
package org.wx.res.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wx.res.constant.Constant;
import org.wx.res.gen.Xml.ReplyXmlMsgUtil;
import org.wx.res.service.HFWeatherService;
import org.wx.res.service.MessageService;
import org.wx.res.service.ViewSpotService;
import org.wx.res.util.TimeUtil;
import org.wx.res.util.WxUtil;
import org.wx.res.util.XMLUtil;

import com.mysql.jdbc.StringUtils;

/**
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/")
public class ManageController {

	private final static Logger log = Logger.getLogger(ManageController.class);

	private final String REPLY_MESSAGE1 = "<a href='http://yujunxian.imwork.net/WX/mobile/index.do'>" +
			"欢迎光临游乐聚落部！</a>";
	private final String REPLY_MESSAGE = "欢迎光临游乐聚落部！";
	@Resource
	private MessageService msgService;
	@Resource
	private HFWeatherService hfWeatherService;
	@Resource
	private ViewSpotService vsService;
	
	@RequestMapping(value = "index.do")
	public String index() {
		System.out.println("test！");
		//msgService.getAnCookMenuContent();
		return "/index";
	}
	
	@RequestMapping(value = "wxIndex.do")
	public String wxIndex(HttpServletRequest request,
			HttpServletResponse response, ModelMap model)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = null;
		// //-- 加密签名
		// String signature = request.getParameter("signature");
		// //-- 时间戳
		// String timestamp = request.getParameter("timestamp");
		// //-- 随机数
		// String nonce = request.getParameter("nonce");
		// //-- 随机字符串
		// String echostr = request.getParameter("echostr");
		// 服务配置时使用
		// if (WxUtil.checkSignature(signature, timestamp, nonce)) {
		// out.println(echostr);
		// }else {
		// log.error("[微信服务器 校验失败] " + TimeUtil.getSystemTime());
		// System.out.println("微信服务器 校验失败");
		// return null;
		// }

		try {
			out = response.getWriter();

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(ServletInputStream) request.getInputStream()));
			String line = null;
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

			if(StringUtils.isNullOrEmpty(sb.toString().trim())){
				return null;
			}
			
			String xmlMsg = WxUtil.changeCharset(sb.toString());
			// -- 主体信息保存
			msgService.saveWxMsg(xmlMsg);

			// -- 消息解析
			String retMsg = msgAnalyse(xmlMsg);
			
			//-- 消息返回
			if (!StringUtils.isNullOrEmpty(retMsg)) {
				out.println(retMsg);
			}

		} catch (IOException e) {
			e.printStackTrace();
			out.print("error");
		} catch (DocumentException e) {
			e.printStackTrace();
			out.print("error");
		} finally {
			out.println("success");
			out.close();
			out = null;
		}

		return null;
	}

	/**
	 * 微信信息解析函数
	 * 
	 * @param msg
	 * @throws DocumentException
	 * @throws UnsupportedEncodingException
	 */
	private String msgAnalyse(String msg) throws DocumentException,
			UnsupportedEncodingException {
		StringBuffer ret = new StringBuffer();
		Document doc = XMLUtil.parse(msg);
		String msgType = XMLUtil.getInfo(doc, Constant.MSG_TYPE).toUpperCase();
		String event = XMLUtil.getInfo(doc, Constant.MSG_TYPE).toUpperCase();
		// --分类处理
		if (Constant.TEXT.equals(msgType)) {
			ret.append(textMsgDeal(doc));
			return ret.toString();
		} else if (Constant.EVENT.equals(msgType)){ //订阅 or 取消订阅
			if(Constant.EVENT_SUB.equals(event)){
				
			}else if(Constant.EVENT_UNSUB.equals(event)){
				
			}else if(Constant.LOCALTION.equals(event)){
				
			}else if(Constant.EVENT_CLICK.equals(event)){
				
			}else if(Constant.EVENT_VIEW.equals(event)){
				
			}
			
		}else if (Constant.IMAGE.equals(msgType)) {
			ret.append(imageMsgDeal(doc));
			return ret.toString();
		} else if (Constant.VOICE.equals(msgType)) {
			
		} else if (Constant.VIDEO.equals(msgType)) {
			
		} else if (Constant.SHORTVIDEO.equals(msgType)) {
			
		} else if (Constant.LOCALTION.equals(msgType)) {
			
		} else if (Constant.LINK.equals(msgType)) {
			
		}

		return ret.toString();
	}

	/**
	 * 处理文本消息
	 * 
	 * @param doc
	 */
	private String textMsgDeal(Document doc) {
		String toUserName = XMLUtil.getInfo(doc, Constant.FROM_USER_NAME);
		String fromUserName = XMLUtil.getInfo(doc, Constant.TO_USER_NAME);
		String text = XMLUtil.getInfo(doc, Constant.CONTENT);
		String retText = null;
		
		if("food".equalsIgnoreCase(text) || "菜谱".equals(text)){
			retText = msgService.getAnCookMenuContent();
		}else if(text.startsWith("#")){
			retText = hfWeatherService.getWeatherCondition(text);
		}else if(text.startsWith("@")){
			retText = vsService.genViewSpotReplyByKey(text);
		}else if("特码".equals(text)){
			retText = msgService.getSuprise();
		}else if ("帮助".equals(text) || "help".equalsIgnoreCase(text)){
			retText = msgService.getHelpList();
		}else {
			retText = REPLY_MESSAGE;
		}
		String ret = new String(ReplyXmlMsgUtil.genTextMsg(toUserName,
				fromUserName, TimeUtil.getSystemMillie(), retText));
		
		return ret;
	}

	/**
	 * 处理图片消息
	 * 
	 * @param doc
	 * @return
	 */
	private String imageMsgDeal(Document doc) {
		String toUserName = XMLUtil.getInfo(doc, Constant.FROM_USER_NAME);
		String fromUserName = XMLUtil.getInfo(doc, Constant.TO_USER_NAME);
		String ret = new String(ReplyXmlMsgUtil.genTextMsg(toUserName,
				fromUserName, TimeUtil.getSystemMillie(), REPLY_MESSAGE));
		return ret;
	}

	/**
	 * 刮刮乐页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "gglIndex.do")
	public String gglIndex(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		System.out.println(request.getRemoteAddr());
		return "/guaguale";
	}

}
