package com.tingshu.hasake;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.entity.StringEntity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fengwei.app.http.HaskHttpUtils;
import com.fengwei.app.http.HaskHttpUtils.HttpRequestCallBack;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.tingshu.hasake.bean.RequestLoginBean;
import com.tingshu.hasake.bean.UserBean;
import com.tingshu.hasake.utils.Constans;
import com.tingshu.hasake.utils.SfpUtils;

import android.content.Intent;
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
//		setTitleContent(getResources().getString(R.string.title_denglu));
		setTitleContent("登录");
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
		HashMap<String, Object> parms = new HashMap<String, Object>();
		parms.put("phone", name);
		parms.put("password", pwd);
		HaskHttpUtils.sendGet(Constans.loginUlr, parms,
				new HttpRequestCallBack() {

					@Override
					public void onSuccess(String result) {
						JSONObject jsonObject = (JSONObject) JSONObject
								.parse(result);
						if (jsonObject.containsKey("Result")) {
							UserBean bean = JSON.parseObject(jsonObject
									.getJSONObject("Result").toJSONString(),
									UserBean.class);
							SfpUtils.saveIntDataToSp(context, SfpUtils.USER_ID,
									bean.getId());
							startActivity(new Intent(context,
									MainActivity.class));
							finish();
						} else {

						}

					}

					@Override
					public void onStart() {

					}

					@Override
					public void onFailure(String error) {
						log(error);

					}
				});
	}
}
