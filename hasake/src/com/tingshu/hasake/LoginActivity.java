package com.tingshu.hasake;

import java.net.URLEncoder;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.entity.StringEntity;

import com.alibaba.fastjson.JSONObject;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.tingshu.hasake.bean.RequestLoginBean;
import com.tingshu.hasake.utils.Constans;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends BaseActivity {
	private EditText et_name;
	private EditText et_pwd;
	private Button btn_login;

	@Override
	protected void setcontentView() {
		setContentView(R.layout.act_login);

	}

	@Override
	protected void initView() {
		et_name = (EditText) findViewById(R.id.et_name);
		et_pwd = (EditText) findViewById(R.id.et_pwd);
		btn_login = (Button) findViewById(R.id.btn_login);
	}

	@Override
	protected void initData() {

	}

	@Override
	protected void initListener() {
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String name = et_name.getText().toString();
				String pwd = et_pwd.getText().toString();
				if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
					toast("用户名或密码不能为空");
					return;
				}
				login(name, pwd);
			}
		});
	}

	/**
	 * 登录
	 */
	private void login(String name, String pwd) {
		try {
			RequestParams params=new RequestParams();
			
			RequestLoginBean loginBean = new RequestLoginBean();
			loginBean.setPhone(name);
			loginBean.setPassword(pwd);
			String json = JSONObject.toJSONString(loginBean);
			params.setBodyEntity(new StringEntity(json,"UTF-8"));
			HttpUtils httpUtils = new HttpUtils();
			 
			httpUtils.send(HttpMethod.POST, Constans.loginUlr,params,
					new RequestCallBack<String>() {

						@Override
						public void onFailure(HttpException arg0, String arg1) {
							log(arg1);
						}

						@Override
						public void onSuccess(ResponseInfo<String> arg0) {
							log(arg0.result);
						}
					});
		} catch (Exception e) {
			
		}
	}
}
