package com.fengwei.app.http;

import java.io.File;
import java.net.URLEncoder;
import java.util.Map;

import android.util.Log;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class HaskHttpUtils {

	public static void sendGet(String url, Map<String, Object> parms,
			final HttpRequestCallBack callBack) {
		try {

			HttpUtils httpUtils = new HttpUtils();
			StringBuilder sb = new StringBuilder(url);
			sb.append('?');
			// ?method=save&title=12345678&timelength=26&
			// 迭代Map拼接请求参数
			for (Map.Entry<String, Object> entry : parms.entrySet()) {
				sb.append(entry.getKey())
						.append('=')
						.append(URLEncoder.encode(entry.getValue().toString(),
								"UTF-8")).append('&');
			}
			sb.deleteCharAt(sb.length() - 1);// 删除最后一个"&"
			Log.d("hask", "url " + sb.toString());
			httpUtils.send(HttpMethod.GET, sb.toString(),
					new RequestCallBack<String>() {
						@Override
						public void onStart() {
							super.onStart();
							if(callBack!=null){
								callBack.onStart();
							}
						}

						@Override
						public void onFailure(HttpException arg0, String arg1) {
							if (callBack != null) {
								callBack.onFailure(arg1);

							}
						}

						@Override
						public void onSuccess(ResponseInfo<String> arg0) {
							if (callBack != null) {
								callBack.onSuccess(arg0.result);

							}
							Log.d("hask", "httpresult " + arg0.result);

						}
					});
		} catch (Exception e) {
			if (callBack != null) {
				callBack.onFailure("网络连接失败！");
			}
		}
	}
	public static void post(String url, Map<String, Object> parms,
			final HttpRequestCallBack callBack) {
		try {

			HttpUtils httpUtils = new HttpUtils();
			RequestParams params = new RequestParams();
			for (Map.Entry<String, Object> entry : parms.entrySet()) {
				params.addBodyParameter(entry.getKey(), entry.getValue().toString());
			}
			Log.d("hask", "url "+url);
			httpUtils.send(HttpMethod.POST, url,params,
					new RequestCallBack<String>() {

						@Override
						public void onFailure(HttpException arg0, String arg1) {
							if (callBack != null) {
								callBack.onFailure(arg1);

							}
						}

						@Override
						public void onSuccess(ResponseInfo<String> arg0) {
							if (callBack != null) {
								callBack.onSuccess(arg0.result);
								
							}
							Log.d("hask", "httpresult "+arg0.result);

						}
					});
		} catch (Exception e) {
			if (callBack != null) {
				callBack.onFailure("网络连接失败！");
			}
		}
	}
	
	public static void upload(String url, Map<String, Object> parms,String filePath,
			final UploadCallback callBack) {
		try {

			HttpUtils httpUtils = new HttpUtils();
			Log.d("hask", "url "+ url);
			
			RequestParams params = new RequestParams();
			for (Map.Entry<String, Object> entry : parms.entrySet()) {
				params.addBodyParameter(entry.getKey(), entry.getValue().toString());
			}
			params.addBodyParameter("ViderUrl", new File(filePath));
			httpUtils.send(HttpMethod.POST, url,params,
					new RequestCallBack<String>() {
						@Override
						public void onStart() {
							callBack.onStart();
						}
						public void onFailure(HttpException arg0, String arg1) {
							if (callBack != null) {
								callBack.onFailure(arg1);
							}
						}
						public void onSuccess(ResponseInfo<String> arg0) {
							if (callBack != null) {
								callBack.onSuccess(arg0.result);
							}
							Log.d("hask", "httpresult "+arg0.result);

						}
						public void onLoading(long total, long current,
								boolean isUploading) {
							callBack.onLoading(total, current, isUploading);
						}
					});
		} catch (Exception e) {
			if (callBack != null) {
				callBack.onFailure("网络连接失败！");
			}
		}
	}
	
	public interface HttpRequestCallBack {
		void onSuccess(String result);

		void onFailure(String error);

		void onStart();
	}

	public interface UploadCallback{
		void onSuccess(String result);

		void onFailure(String error);

		void onStart();
		
		void onLoading(long total, long current, boolean isUploading);
	}
}
