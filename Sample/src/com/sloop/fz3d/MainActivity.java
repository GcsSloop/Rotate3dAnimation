package com.sloop.fz3d;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

import com.sloop.animation.Rotate3dAnimation;

public class MainActivity extends Activity {
	private View view;
	boolean retuens;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		view = findViewById(R.id.iv_sloop);

		view.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				applyRotation(0, 180);
				retuens = true;
			}
		});

	}

	private void applyRotation(float start, float end) {
		// 计算中心点
		final float centerX = view.getWidth() / 2.0f;
		final float centerY = view.getHeight() / 2.0f;

		final Rotate3dAnimation rotation = new Rotate3dAnimation(this, start, end, centerX, centerY, 1.0f, true);
		rotation.setDuration(1500);
		rotation.setFillAfter(true);
		rotation.setInterpolator(new AccelerateInterpolator());

		rotation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				if (retuens) {
					retuens = false;
					applyRotation(180, 0);
				}
			}
		});
		view.startAnimation(rotation);
	}

}
