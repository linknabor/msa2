package com.eshequ.msa.reconciliation.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.eshequ.msa.util.ObjectUtil;
import com.eshequ.msa.util.RSAUtil;

@Component
public class UnionPayUtil {

	private static String DEFAULT_CHARSET = "UTF-8";
	
	@Value("${key_store_file}")
	public String KEY_STORE_FILE;
	
	@Value("${trust_store_file}")
	public String TRUST_STORE_FILE;
	
	/**
	 *创建签名
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String createSign(Map<String, String> map) {
		String signData = "";
		try {
			String charset = (String)map.get("input_charset");
			if (ObjectUtil.isEmpty(charset)) {
				charset = DEFAULT_CHARSET;
			}
			StringBuffer sb = new StringBuffer();
			Set es = map.entrySet();
			Iterator it = es.iterator();
			while(it.hasNext()) {
				Map.Entry entry = (Map.Entry)it.next();
				String k = (String)entry.getKey();
				String v = (String)entry.getValue();
				if(null != v && !"".equals(v) 
						&& !"sign".equals(k) && !"key".equals(k) && !"csp_id".equals(k)) {
					sb.append(k + "=" + v + "&");
				}
			}
			String reqContext = sb.toString();
			if (sb.length() >1) {
				reqContext = sb.substring(0,sb.length()-1);
			}
			signData = RSAUtil.signByPrivate(reqContext, RSAUtil.readFile(KEY_STORE_FILE, DEFAULT_CHARSET), DEFAULT_CHARSET);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return signData;
	}
	
	public boolean verferSignData(String str) {
        String data[] = str.split("&");
        StringBuffer buf = new StringBuffer();
        String signature = "";
        for (int i = 0; i < data.length; i++) {
            String tmp[] = data[i].split("=", 2);
            if ("signature".equals(tmp[0])) {
                signature = tmp[1];
            } else {
                buf.append(tmp[0]).append("=").append(tmp[1]).append("&");
            }
        }
        String signatureStr = buf.substring(0, buf.length() - 1);
        return RSAUtil.verifyByKeyPath(signatureStr, signature, TRUST_STORE_FILE, DEFAULT_CHARSET);
    }
	
	public static Map<String, String> pullRespToMap(String str) {
		Map<String, String> map = new HashMap<String, String>();
		String data[] = str.split("&");
        for (int i = 0; i < data.length; i++) {
            String tmp[] = data[i].split("=", 2);
            map.put(tmp[0], tmp[1]);
        }
        return map;
	}
	
}
