package org.wx.res.constant;

public class Constant {
	//--微信TOKEN
	public static final String TOKEN = "hello1234";
	
	//--微信开发者ID
	public static final String APP_ID_STR = "wx493f7129c97b7ec7";
	
	//--微信开发者密钥
	public static final String APP_SECRET_STR = "af7d6109ce69a3dc5b15d057f3ed50da";
	
	public static final String APP_ID = "APPID";
	
	public static final String APPSECRET = "APPSECRET";
	
	public static final String ACCESS_TOKEN = "ACCESS_TOKEN";
	
	public static final String NEXT_OPENID  = "NEXT_OPENID";
	
	public static final String WX_TAG_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/tags/create?access_token=ACCESS_TOKEN";
	
	public static final String WX_TAG_GET_URL = "https://api.weixin.qq.com/cgi-bin/tags/get?access_token=ACCESS_TOKEN";
	
	public static final String WX_USER_GET_URL = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
	
	public static final String WX_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	//-- 消息体字段
	public static final String TO_USER_NAME = "ToUserName";
	public static final String FROM_USER_NAME = "FromUserName";
	public static final String CREATE_TIME = "CreateTime";
	public static final String MSG_TYPE = "MsgType";
	public static final String CONTENT = "Content";
	public static final String MSG_ID = "MsgId";
	
	//-- 消息体类型
	public static final String TEXT = "TEXT";
	public static final String IMAGE = "IMAGE";
	public static final String VOICE = "VOICE";
	public static final String VIDEO = "VIDEO";
	public static final String SHORTVIDEO = "SHORTVIDEO";
	public static final String LOCALTION = "LOCALTION";
	public static final String LINK = "LINK";
	public static final String EVENT="EVENT";
	public static final String EVENT_SUB="EVENT_SUBSCRIBE";
	public static final String EVENT_UNSUB="EVENT_UNSUBSCRIBE";
	public static final String EVENT_LOCATION="LOCATION";
	public static final String EVENT_CLICK="CLICK";
	public static final String EVENT_VIEW="VIEW";
}
