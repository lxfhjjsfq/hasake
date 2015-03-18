package com.tingshu.hasake.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class ImageUtil {

	// 转换圆角为图片
	public static BitmapDrawable getRoundedCornerDrawable(Drawable drawable) {

		BitmapDrawable bd = (BitmapDrawable) drawable;
		Bitmap bitmap = bd.getBitmap();

		int width = bitmap.getWidth() < bitmap.getHeight() ? bitmap.getWidth()
				: bitmap.getHeight();

		Bitmap output = Bitmap.createBitmap(width, width, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, width, width);
		final RectF rectF = new RectF(rect);
		final float roundPx = width / 2;

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return new BitmapDrawable(bitmap);
	}

	public static Bitmap getRoundCornerImage(Bitmap bitmap, int roundPixels) {
		// 创建一个和原始图片一样大小位图
		Bitmap roundConcerImage = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Bitmap.Config.ARGB_8888);
		// 创建带有位图roundConcerImage的画布
		Canvas canvas = new Canvas(roundConcerImage);
		// 创建画笔
		Paint paint = new Paint();
		// 创建一个和原始图片一样大小的矩形
		Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		RectF rectF = new RectF(rect);
		// 去锯齿
		paint.setAntiAlias(true);
		// 画一个和原始图片一样大小的圆角矩形
		canvas.drawRoundRect(rectF, roundPixels, roundPixels, paint);
		// 设置相交模式
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		// 把图片画到矩形去
		canvas.drawBitmap(bitmap, null, rect, paint);
		return roundConcerImage;
	}
}
