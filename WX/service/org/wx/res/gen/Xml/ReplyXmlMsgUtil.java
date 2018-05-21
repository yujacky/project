package org.wx.res.gen.Xml;

import java.util.List;
import java.util.Map;

import org.wx.res.model.ReplyMsgItem;

public class ReplyXmlMsgUtil {

	/**
	 * 回复文本消息
	 * 
	 * @param toUserName
	 * @param fromUserName
	 * @param createTime
	 * @param content
	 * @return
	 */
	public static String genTextMsg(String toUserName, String fromUserName,
			String createTime, String content) {
		StringBuffer sb = new StringBuffer();
		//sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
		sb.append("<xml>");
		sb.append("<ToUserName><![CDATA[").append(toUserName)
				.append("]]></ToUserName>");
		sb.append("<FromUserName><![CDATA[").append(fromUserName)
				.append("]]></FromUserName>");
		sb.append("<CreateTime>").append(createTime).append("</CreateTime>");
		sb.append("<MsgType><![CDATA[text]]></MsgType>");
		sb.append("<Content><![CDATA[").append(content).append("]]></Content>");
		sb.append("</xml>");

		return sb.toString();
	}

	/**
	 * 回复图片消息
	 * 
	 * @param toUserName
	 * @param fromUserName
	 * @param createTime
	 * @param meadiaId
	 * @return
	 */
	public static String genImageMsg(String toUserName, String fromUserName,
			String createTime, String meadiaId) {
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		sb.append("<ToUserName><![CDATA[").append(toUserName)
				.append("]]></ToUserName>");
		sb.append("<FromUserName><![CDATA[").append(fromUserName)
				.append("]]></FromUserName>");
		sb.append("<CreateTime>").append(createTime).append("</CreateTime>");
		sb.append("<MsgType><![CDATA[image]]></MsgType>");
		sb.append("<Image>");
		sb.append("<MediaId><![CDATA[").append(meadiaId)
				.append("]]></MediaId>");
		sb.append("</Image>");
		sb.append("</xml>");
		return sb.toString();
	}

	/**
	 * 回复声音信息
	 * 
	 * @param toUserName
	 * @param fromUserName
	 * @param createTime
	 * @param meadiaId
	 * @return
	 */
	public static String genVoiceMsg(String toUserName, String fromUserName,
			String createTime, String meadiaId) {
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		sb.append("<ToUserName><![CDATA[").append(toUserName)
				.append("]]></ToUserName>");
		sb.append("<FromUserName><![CDATA[").append(fromUserName)
				.append("]]></FromUserName>");
		sb.append("<CreateTime>").append(createTime).append("</CreateTime>");
		sb.append("<MsgType><![CDATA[voice]]></MsgType>");
		sb.append("<Voice>");
		sb.append("<MediaId><![CDATA[").append(meadiaId)
				.append("]]></MediaId>");
		sb.append("</Voice>");
		sb.append("</xml>");
		return sb.toString();
	}

	/**
	 * 生成视频消息
	 * 
	 * @param toUserName
	 * @param fromUserName
	 * @param createTime
	 * @param meadiaId
	 * @param title
	 * @param description
	 * @return
	 */
	public static String genVideoMsg(String toUserName, String fromUserName,
			String createTime, String meadiaId, String title, String description) {
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		sb.append("<ToUserName><![CDATA[").append(toUserName)
				.append("]]></ToUserName>");
		sb.append("<FromUserName><![CDATA[").append(fromUserName)
				.append("]]></FromUserName>");
		sb.append("<CreateTime>").append(createTime).append("</CreateTime>");
		sb.append("<MsgType><![CDATA[video]]></MsgType>");
		sb.append("<Video>");
		sb.append("<MediaId><![CDATA[").append(meadiaId)
				.append("]]></MediaId>");
		sb.append("<Title><![CDATA[").append(title).append("]]></Title>");
		sb.append("<Description><![CDATA[").append(description)
				.append("]]></Description>");
		sb.append("</Video>");
		sb.append("</xml>");
		return sb.toString();
	}

	/**
	 * 生成音乐消息
	 * 
	 * @param toUserName
	 * @param fromUserName
	 * @param createTime
	 * @param title
	 * @param description
	 * @param musicUrl
	 * @param HQMusicUrl
	 * @param meadiaId
	 * @return
	 */
	public static String genMusicMsg(String toUserName, String fromUserName,
			String createTime, String title, String description,
			String musicUrl, String HQMusicUrl, String meadiaId) {
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		sb.append("<ToUserName><![CDATA[").append(toUserName)
				.append("]]></ToUserName>");
		sb.append("<FromUserName><![CDATA[").append(fromUserName)
				.append("]]></FromUserName>");
		sb.append("<CreateTime>").append(createTime).append("</CreateTime>");
		sb.append("<MsgType><![CDATA[music]]></MsgType>");
		sb.append("<Music>");
		sb.append("<Title><![CDATA[").append(title).append("]]></Title>");
		sb.append("<Description><![CDATA[").append(description)
				.append("]]></Description>");
		sb.append("<MusicUrl><![CDATA[").append(musicUrl)
				.append("]]></MusicUrl>");
		sb.append("<HQMusicUrl><![CDATA[").append(HQMusicUrl)
				.append("]]></HQMusicUrl>");
		sb.append("<ThumbMediaId><![CDATA[").append(meadiaId)
				.append("]]></ThumbMediaId>");
		sb.append("</Music>");
		sb.append("</xml>");
		return sb.toString();
	}

	/**
	 * 生成图文消息
	 * @param toUserName
	 * @param fromUserName
	 * @param createTime
	 * @param articleCount limit (0-8)
	 * @param title
	 * @param description
	 * @param picUrl
	 * @param url
	 * @return
	 * @throws Exception 
	 */
	public static String genImageTextMsg(String toUserName,
			String fromUserName, String createTime, String articleCount,
			List<ReplyMsgItem> list) throws Exception {
		StringBuffer sb = new StringBuffer();
		int count = Integer.parseInt(articleCount);
		
		if (count < 1 || count > 8){
			throw new Exception("articleCount must limit in 1~8");
		}
		
		sb.append("<xml>");
		sb.append("<ToUserName><![CDATA[").append(toUserName).append("]]></ToUserName>");
		sb.append("<FromUserName><![CDATA[").append(fromUserName).append("]]></FromUserName>");
		sb.append("<CreateTime>").append(createTime).append("</CreateTime>");
		sb.append("<MsgType><![CDATA[news]]></MsgType>");
		sb.append("<ArticleCount>").append(articleCount).append("</ArticleCount>");
		sb.append("<Articles>");
		
		for(int i=0;i < count; i++){
			StringBuffer item = new StringBuffer();
			ReplyMsgItem m = list.get(i);
			item.append("<item>");
			item.append("<Title><![CDATA[").append(m.getTitle()).append("]]></Title> ");
			item.append("<Description><![CDATA[").append(m.getDescription()).append("]]></Description>");
			item.append("<PicUrl><![CDATA[").append(m.getPicUrl()).append("]]></PicUrl>");
			item.append("<Url><![CDATA[").append(m.getUrl()).append("]]></Url>");
			item.append("</item>");
			sb.append(item);
		}
		
		sb.append("</Articles>");
		
		return sb.toString();
	}
}
