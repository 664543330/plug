package com.plug.scan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class BaseActivity extends Activity {

	protected Activity mProxyActivity;

	public static final String FROM = "extra.from";


	public static final String EXTRA_DEX_PATH = "extra.dex.path";
	public static final String EXTRA_CLASS = "extra.class";

	private String PROXY_VIEW_ACTION;
	private String DEX_PATH;

	protected String KEY_SCAN_MSG;

	public static final int FROM_EXTERNAL = 0;
	public static final int FROM_INTERNAL = 1;

	protected int mFrom = FROM_INTERNAL;

	protected boolean isProxy=true;

	public void setProxy(Activity proxyActivity) {
		mProxyActivity = proxyActivity;
	}

	public void setDexPath(String DEX_PATH){this.DEX_PATH=DEX_PATH;}

	public void setProxyViewAction(String PROXY_VIEW_ACTION){this.PROXY_VIEW_ACTION=PROXY_VIEW_ACTION;}

	public void setKeyScanMsg(String KEY_SCAN_MSG){this.KEY_SCAN_MSG=KEY_SCAN_MSG;}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
//		if (savedInstanceState != null) {
//			mFrom = savedInstanceState.getInt(FROM, FROM_INTERNAL);
//		}
//		if (mFrom != FROM_EXTERNAL) {
//			super.onCreate(savedInstanceState);
//			mProxyActivity = this;
//			isProxy=false;
//		}
		if (!isProxy){
			mProxyActivity=this;
			super.onCreate(savedInstanceState);
		}

	}

	@Override
	public void setContentView(int layoutResID) {
		if (!isProxy){
			super.setContentView(layoutResID);
		}else {
			if (mProxyActivity != null && mProxyActivity instanceof Activity)
				mProxyActivity.setContentView(layoutResID);
		}

	}

	protected void startActivityByProxy(String className) {
		if (mProxyActivity == this) {
			Intent intent = new Intent();
			intent.setClassName(this, className);
			this.startActivity(intent);
		} else {
			Intent intent = new Intent(PROXY_VIEW_ACTION);
			intent.putExtra(EXTRA_DEX_PATH, DEX_PATH);
			intent.putExtra(EXTRA_CLASS, className);
			mProxyActivity.startActivity(intent);
		}
	}

	@Override
	protected void onResume() {
		if (!isProxy)
			super.onResume();
	}

	@Override
	protected void onStart() {
		if (!isProxy)
			super.onStart();
	}

	@Override
	protected void onPause() {
		if (!isProxy)
			super.onPause();
	}

	@Override
	protected void onRestart() {
		if (!isProxy)
			super.onRestart();
	}

	@Override
	protected void onStop() {
		if (!isProxy)
			super.onStop();
	}

	@Override
	protected void onDestroy() {
		if (!isProxy)
			super.onDestroy();
	}

}
