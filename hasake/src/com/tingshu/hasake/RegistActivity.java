package com.tingshu.hasake;

import java.util.HashMap;
import java.util.Map;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fengwei.app.http.HaskHttpUtils;
import com.fengwei.app.http.HaskHttpUtils.HttpRequestCallBack;
import com.tingshu.hasake.bean.UserBean;
import com.tingshu.hasake.utils.Constans;
import com.tingshu.hasake.utils.SfpUtils;

public class RegistActivity extends BaseActivity {
	private EditText et_name;
	private EditText et_pwd;
	private Button btn_regist;

	@Override
	protected void setcontentView() {
		setContentView(R.layout.act_regist);

	}

	@Override
	protected void initView() {
		et_name = (EditText) findViewById(R.id.et_regist_name);
		et_pwd = (EditText) findViewById(R.id.et_regist_pwd);
		btn_regist = (Button) findViewById(R.id.btn_regist);
		setTitleContent("注册");
	}

	@Override
	protected void initData() {

	}

	@Override
	protected void initListener() {
		btn_regist.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String name = et_name.getText().toString();
				String pwd = et_name.getText().toString();
				if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
					toast("用户名或者密码不能为空");
					return;
				}
				regist(name, pwd);
			}
		});
	}

	private void regist(String name, String pwd) {
		Map<String, Object> parms = new HashMap<String, Object>();
		parms.put("Phone", name);
		parms.put("Password", pwd);
		parms.put("NickName", "sjw");
		// parms.put("", value)
		HaskHttpUtils.sendGet(Constans.registUrl, parms,
				new HttpRequestCallBack() {

					@Override
					public void onSuccess(String result) {
						if (!TextUtils.isEmpty(result)) {
							JSONObject jsonObject = (JSONObject) JSONObject
									.parse(result);
							UserBean bean = JSON.parseObject(jsonObject
									.getJSONObject("Result").toJSONString(),
									UserBean.class);
							log(bean.getId() + "");
							SfpUtils.saveIntDataToSp(context, SfpUtils.USER_ID,
									bean.getId());
						}

					}

					@Override
					public void onStart() {

					}

					@Override
					public void onFailure(String error) {

					}
				});

	}

}
