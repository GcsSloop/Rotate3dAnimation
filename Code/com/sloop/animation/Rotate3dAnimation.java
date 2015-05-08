/**
 * @Title: Rotate3dAnimation.java
 * @Package com.sloop.animation
 * Copyright: Copyright (c) 2015
 * 
 * @author sloop
 * @date 2015年3月10日 上午11:20:10
 * @version V1.0
 */

package com.sloop.animation;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * 3D翻转特效
 * @ClassName: Rotate3dAnimation
 * @author sloop
 * @date 2015年3月10日 上午11:20:10
 */

public class Rotate3dAnimation extends Animation {

	// 开始角度
	private final float mFromDegrees;
	// 结束角度
	private final float mToDegrees;
	// 中心点
	private final float mCenterX;
	private final float mCenterY;
	private final float mDepthZ;	//深度
	// 是否需要扭曲
	private final boolean mReverse;
	// 摄像头
	private Camera mCamera;
	ContextThemeWrapper context;
	//新增--像素比例（默认值为1）
	float scale = 1;

	/**
	 * 创建一个新的实例 Rotate3dAnimation. 
	 * @param fromDegrees	开始角度
	 * @param toDegrees		结束角度
	 * @param centerX		中心点x坐标
	 * @param centerY		中心点y坐标
	 * @param depthZ		深度
	 * @param reverse		是否扭曲
	 */
	public Rotate3dAnimation(ContextThemeWrapper context, float fromDegrees, float toDegrees, float centerX, float centerY, float depthZ, boolean reverse) {
		this.context = context;
		mFromDegrees = fromDegrees;
		mToDegrees = toDegrees;
		mCenterX = centerX;
		mCenterY = centerY;
		mDepthZ = depthZ;
		mReverse = reverse;
		//获取手机像素比 （即dp与px的比例）
		scale = context.getResources().getDisplayMetrics().density;
		Log.e("scale",""+scale);
	}

	@Override
	public void initialize(int width, int height, int parentWidth, int parentHeight) {
		
		super.initialize(width, height, parentWidth, parentHeight);
		mCamera = new Camera();
	}

	// 生成Transformation
	@Override
	protected void applyTransformation(float interpolatedTime, Transformation t) {
		final float fromDegrees = mFromDegrees;
		// 生成中间角度
		float degrees = fromDegrees + ((mToDegrees - fromDegrees) * interpolatedTime);

		
		final float centerX = mCenterX;
		final float centerY = mCenterY;
		final Camera camera = mCamera;

		final Matrix matrix = t.getMatrix();

		camera.save();
		if (mReverse) {
			camera.translate(0.0f, 0.0f, mDepthZ * interpolatedTime);
		} else {
			camera.translate(0.0f, 0.0f, mDepthZ * (1.0f - interpolatedTime));
		}
		camera.rotateY(degrees);
		// 取得变换后的矩阵
		camera.getMatrix(matrix);
		camera.restore();

//----------------------------------------------------------------------------
		/** 
		 * 修复打脸问题		(￣ε(#￣)☆╰╮(￣▽￣///)
		 * 简要介绍：
		 * 原来的3D翻转会由于屏幕像素密度问题而出现效果相差很大
		 * 例如在屏幕像素比为1,5的手机上显示效果基本正常，
		 * 而在像素比3,0的手机上面感觉翻转感觉要超出屏幕边缘，
		 * 有种迎面打脸的感觉、
		 * 
		 * 解决方案
		 * 利用屏幕像素密度对变换矩阵进行校正，
		 * 保证了在所有清晰度的手机上显示的效果基本相同。
		 *  
		 */
		float[] mValues = {0,0,0,0,0,0,0,0,0};
		matrix.getValues(mValues);			//获取数值
		mValues[6] = mValues[6]/scale;		//数值修正
		matrix.setValues(mValues);			//重新赋值
		
//		Log.e("TAG", "mValues["+0+"]="+mValues[0]+"------------\t"+"mValues["+6+"]="+mValues[6]);
//----------------------------------------------------------------------------

		matrix.preTranslate(-centerX, -centerY);
		matrix.postTranslate(centerX, centerY);
	}
	


}
