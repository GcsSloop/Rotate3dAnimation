#安卓3D翻转效果  
作者微博：  [@安卓攻城师sloop](http://weibo.com/5459430586/profile?topnav=1&wvr=6)
=====

#基于谷歌官方提供的3D翻转示例进行修改，修复了在不同像素密度的设备上显示效果差异过大的问题。

## 修正前后对比

<b>修正前</b> | <b>修正后</b>
--- | ---
![image](https://github.com/GcsSloop/Rotate3dAnimation/blob/master/Pic/%E4%BF%AE%E6%AD%A3%E5%89%8D.gif) | ![image](https://github.com/GcsSloop/Rotate3dAnimation/blob/master/Pic/%E4%BF%AE%E6%AD%A3%E5%90%8E.gif)


- - - - 
#调用示例：
    
        // 计算中心点（这里是使用view的中心作为旋转的中心点）
		final float centerX = view.getWidth() / 2.0f;
		final float centerY = view.getHeight() / 2.0f;

        //括号内参数分别为（上下文，开始角度，结束角度，x轴中心点，y轴中心点，深度，是否扭曲）
		final Rotate3dAnimation rotation = new Rotate3dAnimation(this, start, end, centerX, centerY, 1.0f, true);
		rotation.setDuration(1500);                               //设置动画时长
		rotation.setFillAfter(true);                              //保持旋转后效果
		rotation.setInterpolator(new AccelerateInterpolator());   //设置插值器

		rotation.setAnimationListener(new AnimationListener() {   //设置监听器

			@Override
			public void onAnimationStart(Animation animation) {
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
			}
		});
		view.startAnimation(rotation);                            //开始动画
		
		
