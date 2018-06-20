package com.ztgm.mall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpStatus;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class test {

	public static void main(String[] args) throws Exception {
		testOrder();
		
		//testCommet();
	}
	/**
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @throws JSONException 
	 * @date 2017-11-14
	 */

	private static void testCommet() throws JSONException {

		String url = "http://127.0.0.1:8888/interface/order/commet";
		//http://127.0.0.1:8888/interface/order/commet?data=[{\"gid\":\"c8cb4aece819436899f4dd1eb7fa1733\",\"odid\":\"6\",\"star\":\"3\",\"iid\":\"360dcd1d3e2043978933dcc0ee58df8d\",\"isanony\":\"1\",\"oid\":\"201801017\",\"value\":\"%E9%9D%9E%E5%B8%B8%E5%A5%BD%E7%9A%84%E4%BA%A7%E5%93%81%EF%BC%81%EF%BC%81\"}]
		String commetdata="[{\"gid\":\"c8cb4aece819436899f4dd1eb7fa1733\",\"iid\":\"360dcd1d3e2043978933dcc0ee58df8d\",\"oid\":\"201801017\",\"odid\":\"6\",\"value\":\"%E9%9D%9E%E5%B8%B8%E5%A5%BD%E7%9A%84%E4%BA%A7%E5%93%81%EF%BC%81%EF%BC%81\",\"star\":\"3\",\"isanony\":\"1\"}]";
		
		//JSONArray ja=new JSONArray(commetdata);

		String d1="data="+commetdata;
		String data = "?Token=111";

		String responsedata = "";
		try {
			responsedata = sendHttpData(url+data,d1);

			
			JSONObject j=new JSONObject(responsedata);
			if(j.optInt("code")==100)
			{
				 JSONObject jdata=new JSONObject( j.optString("data"));
				 System.out.println(jdata.toString());
			}
			
			System.out.println("返回:" + responsedata);
			// System.out.println("解密:" + out);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(responsedata);
		// ***********vmInstallApp*****************

	}

	

	/**
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2017-11-14
	 */

	private static void testOrder() {

		String url = "http://127.0.0.1:8888/interface/order/showOrders";

		String json = "{\"userid\": \"t1\",\"pass\": \"123321\"}";

		String data = "?Token=111&state=1";

		String responsedata = "";
		try {
			responsedata = sendHttpData(url + data, "");

			
			JSONObject j=new JSONObject(responsedata);
			if(j.optInt("code")==100)
			{
				 JSONObject jdata=new JSONObject( j.optString("data"));
				 System.out.println(jdata.toString());
			}
			
			System.out.println("返回:" + responsedata);
			// System.out.println("解密:" + out);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(responsedata);
		// ***********vmInstallApp*****************

	}

	private static Logger logger = Logger.getLogger(test.class);

	public static String sendHttpData(String url, String str) throws Exception {

		logger.info("HTTP Request URL:" + url + ",HTTP Request PARAM:" + str);
		HttpClient client = new HttpClient();
		// client.getHostConfiguration().setProxy("10.41.70.8", 80);
		// client.getParams().setAuthenticationPreemptive(true);

		PostMethod httpPost = new PostMethod(url);
		InputStream is = new java.io.ByteArrayInputStream(str.getBytes("utf-8"));
		client.setTimeout(60000);

		//httpPost.setRequestHeader("Content-type", "application/json");
		httpPost.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
		 
		httpPost.setRequestHeader("Accept", "application/json");
		httpPost.setRequestHeader("Connection", "close");
		// httpPost.setRequestHeader("Authorization", "Basic YWRtaW46MTIz");
		httpPost.setRequestBody(is);

		String responseData = null;
		try {
			Exception exception = null;
			client.executeMethod(httpPost);
			int resStatusCode = httpPost.getStatusCode();
			if (resStatusCode == HttpStatus.SC_OK) {
				BufferedReader br = new BufferedReader(
						new InputStreamReader(httpPost.getResponseBodyAsStream(), "utf-8"));
				logger.info("HTTP Request CHARSET:" + httpPost.getResponseCharSet());
				String res = null;
				StringBuffer sb = new StringBuffer();
				while ((res = br.readLine()) != null) {
					sb.append(res);
				}
				responseData = sb.toString();
			} else {
				logger.error("http请求失败 " + resStatusCode + ":" + httpPost.getStatusText());
				exception = new Exception("[SerialHttpSender] HttpErrorCode:" + resStatusCode);
			}
			if (exception != null) {
				throw exception;
			}
		} catch (java.net.ConnectException ex) {
			ex.printStackTrace();
			throw ex;
		} catch (IOException ex) {
			ex.printStackTrace();
			// org.apache.commons.httpclient.HttpRecoverableException:
			// java.net.SocketTimeoutException: Read timed out

			String message = ex.getMessage();
			if (message != null && message.toLowerCase().indexOf("read timed") > -1) {
				throw new Exception(ex.getMessage());
			} else {
				ex.printStackTrace();
				throw ex;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;

		} finally {
			httpPost.releaseConnection();

		}

		logger.info("HTTP Request Result:" + responseData);
		return responseData;
	}
}
