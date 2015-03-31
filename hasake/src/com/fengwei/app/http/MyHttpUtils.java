package com.fengwei.app.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;

import android.os.Handler;
import android.util.Log;

import com.lidroid.xutils.http.client.multipart.MultipartEntity;
import com.lidroid.xutils.http.client.multipart.content.FileBody;
import com.lidroid.xutils.http.client.multipart.content.StringBody;

/**
 * @author tongxiaoyun
 * @note 自定义网络请求 aaa
 */
public class MyHttpUtils {
	public static boolean isConnect = false;
	static Handler handler = new Handler();

	public MyHttpUtils() {
		super();

	}

	/**
	 * @param head
	 *            上传的地址
	 * @param json
	 *            要上传的json数据
	 * @param pjo
	 *            对外开放的接口
	 */
	public static void postJson(final String head, final String json, final PostJsonOK pjo) {

		new Thread() {
			public void run() {
				boolean isNet = false;
				BufferedReader reader = null;
				String url = head;
				HttpClient client = new DefaultHttpClient();
				client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
				client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 10000);
				HttpPost post = new HttpPost(url);
				final StringBuffer result = new StringBuffer();
				try {
					StringEntity s = new StringEntity(json, "utf-8");
					s.setContentEncoding("UTF-8");
					s.setContentType("application/json");
					post.setEntity(s);
					HttpResponse res = client.execute(post);
					int code = res.getStatusLine().getStatusCode();
					Log.i("MyHttpUtils", "" + code);
					if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
						reader = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));

						String inputLine = null;
						while ((inputLine = reader.readLine()) != null) {
							result.append(inputLine);
						}
						isNet = true;
						handler.post(new Runnable() {

							@Override
							public void run() {
								// 当请求网络成功时
								pjo.ok(result);
							}
						});
					}

				} catch (Exception e) {

				} finally {
					if (reader != null) {
						try {
							reader.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if (!isNet) {
						handler.post(new Runnable() {

							@Override
							public void run() {

								pjo.error("网络连接失败，请检查网络");

							}
						});
					}
				}

			};
		}.start();

	}

	/**
	 * @param head
	 *            上传的地址
	 * @param json
	 *            要上传的json数据
	 * @param pjo
	 *            对外开放的接口
	 */
	static ExecutorService jsonPool = Executors.newSingleThreadExecutor(); // 开启线程池

	public static void postOneJson(final String head, final String json, final PostJsonOK pjo) {

		isConnect = false;
		jsonPool.execute(new Runnable() {

			@Override
			public void run() {
				BufferedReader reader = null;
				String url = head;
				HttpClient client = new DefaultHttpClient();
				client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
				client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 10000);
				HttpPost post = new HttpPost(url);
				final StringBuffer result = new StringBuffer();
				try {
					StringEntity s = new StringEntity(json, "utf-8");
					s.setContentEncoding("UTF-8");
					s.setContentType("application/json");
					post.setEntity(s);
					HttpResponse res = client.execute(post);
					int code = res.getStatusLine().getStatusCode();
					Log.i("MyHttpUtils", "" + code);
					if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
						reader = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));

						String inputLine = null;
						while ((inputLine = reader.readLine()) != null) {
							result.append(inputLine);
						}
						isConnect = true;
						handler.post(new Runnable() {

							@Override
							public void run() {
								// 当请求网络成功时
								pjo.ok(result);
							}
						});
					}

				} catch (Exception e) {

				} finally {
					if (reader != null) {
						try {
							reader.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					handler.post(new Runnable() {

						@Override
						public void run() {
							if (!isConnect) {
								pjo.error("网络连接失败，请检查网络");
							}
						}
					});

				}

			}
		});
	}

	/**
	 * 同步上传
	 * 
	 * @param head
	 *            上传的地址
	 * @param json
	 *            要上传的json数据
	 * @param pjo
	 *            对外开放的接口
	 */
	public static void postJson(final String head, final String json) {

		BufferedReader reader = null;
		String url = head;
		HttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
		client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 10000);
		HttpPost post = new HttpPost(url);
		final StringBuffer result = new StringBuffer();
		try {
			StringEntity s = new StringEntity(json);
			s.setContentEncoding("UTF-8");
			s.setContentType("application/json");
			post.setEntity(s);
			HttpResponse res = client.execute(post);
			int code = res.getStatusLine().getStatusCode();
			Log.i("MyHttpUtils", "" + code);
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				reader = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));

				String inputLine = null;
				while ((inputLine = reader.readLine()) != null) {
					result.append(inputLine);
				}

			}

		} catch (Exception e) {

		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	}

	/**
	 * @param head
	 *            上传的地址
	 * @param json
	 *            要上传的json数据
	 * @param pjo
	 *            对外开放的接口
	 */
	public static void upLoadUserHead(final String head, final String uid, final File file, final PostJsonOK pjo) {

		isConnect = false;
		new Thread() {
			public void run() {
				BufferedReader reader = null;
				String url = head;
				HttpClient client = new DefaultHttpClient();
				client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
				client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 5000);
				HttpPost post = new HttpPost(url);
				final StringBuffer result = new StringBuffer();
				try {

					MultipartEntity mpEntity = new MultipartEntity();
					FileBody fb = new FileBody(file);
					mpEntity.addPart("myfile", fb);
					StringBody sb = new StringBody(uid);
					mpEntity.addPart("userId", sb);
					post.setEntity(mpEntity);
					HttpResponse res = client.execute(post);
					int code = res.getStatusLine().getStatusCode();
					Log.i("MyHttpUtils", "" + code);
					if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
						reader = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));

						String inputLine = null;
						while ((inputLine = reader.readLine()) != null) {
							result.append(inputLine);
						}
						isConnect = true;
						handler.post(new Runnable() {

							@Override
							public void run() {
								// 当请求网络成功时
								pjo.ok(result);
							}
						});
					}

				} catch (Exception e) {

				} finally {
					if (reader != null) {
						try {
							reader.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					handler.post(new Runnable() {

						@Override
						public void run() {
							if (!isConnect) {
								pjo.error("网络连接失败，请检查网络");
							}
						}
					});

				}

			};
		}.start();

	}

	public interface PostJsonOK {
		/**
		 * 当下载成功时
		 */
		public void ok(StringBuffer json);

		/**
		 * 当下载失败时
		 */
		public void error(String error);
	}

	static ExecutorService pool = Executors.newSingleThreadExecutor(); // 开启线程池

	public static void getVideo(final String url, final String path, final String nextPath, final int tag, final VideoCallBack videoCallBack) {
		pool.execute(new Runnable() {

			@Override
			public void run() {
				File file = null;
				HttpClient client = new DefaultHttpClient();
				client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
				client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 10000);
				HttpGet httpGet = new HttpGet(url);
				InputStream in = null;
				FileOutputStream out = null;
				try {
					HttpResponse response = client.execute(httpGet);
					if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
						file = new File(path);
						in = response.getEntity().getContent();
						out = new FileOutputStream(file);
						byte[] b = new byte[4096 * 2];
						int len = 0;
						while ((len = in.read(b)) != -1) {
							out.write(b, 0, len);
						}
						new Thread(new Runnable() {
							@Override
							public void run() {
								videoCallBack.ok(tag);
							}
						}).start();
					}
				} catch (IOException e) {
					if (file != null && file.exists()) {
						file.delete();
					}
					new Thread(new Runnable() {
						@Override
						public void run() {
							videoCallBack.error(tag, true);
						}
					}).start();
					e.printStackTrace();
				} finally {
					if (out != null) {
						try {
							out.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if (in != null) {
						try {
							in.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				}

			}
		});
	}

	static ExecutorService imagePool = Executors.newSingleThreadExecutor(); // 开启线程池

	public static void getImage(final String url, final String path, final String nextPath, final int tag, final VideoCallBack videoCallBack) {

		imagePool.execute(new Runnable() {

			@Override
			public void run() {
				HttpClient client = new DefaultHttpClient();
				File file = null;
				client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
				client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 10000);
				HttpGet httpGet = new HttpGet(url);
				InputStream in = null;
				FileOutputStream out = null;
				try {
					HttpResponse response = client.execute(httpGet);
					if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
						file = new File(path);
						in = response.getEntity().getContent();
						out = new FileOutputStream(file);
						byte[] b = new byte[4096 * 2];
						int len = 0;
						while ((len = in.read(b)) != -1) {
							out.write(b, 0, len);
						}
						new Thread(new Runnable() {
							@Override
							public void run() {
								videoCallBack.ok(tag);
							}
						}).start();
					}
				} catch (IOException e) {
					file.delete();
					new Thread(new Runnable() {
						@Override
						public void run() {
							videoCallBack.error(14, true);
						}
					}).start();
					e.printStackTrace();
				} finally {
					if (out != null) {
						try {
							out.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if (in != null) {
						try {
							in.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				}

			}
		});
	}

	public interface VideoCallBack {
		/**
		 * 当下载成功时
		 */
		public void ok(int tag);

		/**
		 * 当下载失败时
		 */
		public void error(int tag, boolean isBreak);
	}

}
