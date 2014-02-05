package com.example.customrotationanimation;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.webkit.WebView;

public class MainActivity extends Activity implements OnTouchListener {
	WebView frontWeb, backWeb;
	private GestureDetector gesture;
	private SimpleOnGestureListener simpleOnGestureListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		frontWeb = (WebView) findViewById(R.id.face_web_view);
		backWeb = (WebView) findViewById(R.id.back_web_view);
		frontWeb.loadUrl("http://google.com");
		backWeb.loadUrl("http://yahoo.com");

		simpleOnGestureListener = new SimpleOnGestureListener() {
			public boolean onDoubleTap(MotionEvent e) {
				flipCard();
				return false;

			};

			@Override
			public boolean onSingleTapConfirmed(MotionEvent e) {
				//flipCard();
				return false;
			}

		};
		gesture = new GestureDetector(this, simpleOnGestureListener);
		frontWeb.setOnTouchListener(this);
		backWeb.setOnTouchListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onCardClick(View view) {
		flipCard();
	}

	private void flipCard() {
		View rootLayout = (View) findViewById(R.id.main_activity_root);
		View cardFace = (View) findViewById(R.id.main_activity_card_face);
		View cardBack = (View) findViewById(R.id.main_activity_card_back);

		CardFlipAnimation flipAnimation = new CardFlipAnimation(cardFace, cardBack);

		if (cardFace.getVisibility() == View.GONE) {
			flipAnimation.reverse();
		}
		rootLayout.startAnimation(flipAnimation);
	}

	@Override
	public boolean onTouch(View arg0, MotionEvent event) {
		return gesture.onTouchEvent(event);
	}
}
