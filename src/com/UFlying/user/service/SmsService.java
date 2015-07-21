package com.UFlying.user.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.spy.memcached.MemcachedClient;
import org.apache.log4j.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UFlying.user.entity.request.RequestGetVerifyCode;
import com.UFlying.user.entity.response.ResponseBasic;
import com.UFlying.user.util.MemcacheUtil;
import com.UFlying.user.util.RandomKeyUtil;

@Service
public class SmsService {

	private static final String URI_SEND_SMS = "http://yunpian.com/v1/sms/send.json";
	private static final String ENCODING = "UTF-8";
	private static final String API_KEY = "552620b7996882dcb7e63279b9a2325c";

	@Autowired
	private MemcachedClient memcachedClient;

	public ResponseBasic sendVerifyCode(RequestGetVerifyCode requestGetVerifyCode) {
		ResponseBasic responseBasic = new ResponseBasic();
		String phoneNumber = requestGetVerifyCode.getPhoneNumber();
		String verifyCode = RandomKeyUtil.getRandomKeyForPhoneRegister();
		// 验证码10分钟过期
		String key = MemcacheUtil.getVerifyCodeKey(phoneNumber);
		memcachedClient.set(key, 600, verifyCode);
		// 发送短信
		try {
			String response = this.sendSms(API_KEY, "【UFlying无人机联盟】您的验证码是" + verifyCode + "。如非本人操作，请忽略本短信。", phoneNumber);
			System.out.println(response);
			Logger logger=Logger.getLogger(SmsService.class.getName()); 
			logger.info("验证码:" + verifyCode);
			System.out.println(verifyCode);
		} catch (IOException e) {
			e.printStackTrace();
			responseBasic.setStatus(-1);
			responseBasic.setReason("短信发送失败！");
			return responseBasic;
		}
		responseBasic.setStatus(0);
		responseBasic.setReason("短信已发送，请查看注册手机。");
		return responseBasic;
	}

	public String sendSms(String apikey, String text, String mobile) throws IOException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("apikey", apikey);
		params.put("text", text);
		params.put("mobile", mobile);
		return post(URI_SEND_SMS, params);
	}

	public String post(String url, Map<String, String> paramsMap) {
		CloseableHttpClient client = HttpClients.createDefault();
		String responseText = "";
		CloseableHttpResponse response = null;
		try {
			HttpPost method = new HttpPost(url);
			if (paramsMap != null) {
				List<NameValuePair> paramList = new ArrayList<NameValuePair>();
				for (Map.Entry<String, String> param : paramsMap.entrySet()) {
					NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
					paramList.add(pair);
				}
				method.setEntity(new UrlEncodedFormEntity(paramList, ENCODING));
			}
			response = client.execute(method);
			client.execute(method);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				responseText = EntityUtils.toString(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return responseText;
	}
}
