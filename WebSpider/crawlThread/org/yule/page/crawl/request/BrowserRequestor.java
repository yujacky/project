package org.yule.page.crawl.request;

import java.net.Proxy;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.edu.hfut.dmic.webcollector.conf.Configuration;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.Requester;

public class BrowserRequestor implements Requester{

	public static final Logger LOG = LoggerFactory.getLogger(BrowserRequestor.class);
    protected Configuration defaultConf = Configuration.getDefault();
    protected int MAX_REDIRECT = defaultConf.getMaxRedirect();
    protected int MAX_RECEIVE_SIZE = defaultConf.getMaxReceiveSize();
    protected int timeoutForConnect = defaultConf.getConnectTimeout();
    protected int timeoutForRead = defaultConf.getReadTimeout();
    protected String userAgent = defaultConf.getDefaultUserAgent();
    
    protected String method = "GET";
    protected boolean doinput = true;
    protected boolean dooutput = true;
    protected boolean followRedirects = false;

    protected byte[] outputData=null;
    Proxy proxy = null;

    protected Map<String, List<String>> headerMap = null;

    protected CrawlDatum crawlDatum = null;
    
	@Override
	public Page getResponse(CrawlDatum crawlDatum) throws Exception {
		return null;
	}

}
