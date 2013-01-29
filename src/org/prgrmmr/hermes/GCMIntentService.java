package org.prgrmmr.hermes;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;

public class GCMIntentService extends GCMBaseIntentService {
	
	public GCMIntentService() {
		super(MainActivity.SENDER_ID);
	}

	@Override
	protected void onError(Context context, String regId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onMessage(Context context, Intent regId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onRegistered(Context context, String regId) {
		Log.i(MainActivity.TAG, "Successfully registered?: "+ regId);
		
	}

	@Override
	protected void onUnregistered(Context context, String regId) {
		Log.i(MainActivity.TAG, "onUnregistered: "+ regId);
	}
	
	@Override
	protected boolean onRecoverableError(Context context, String errorId) {
		return false;
	}

}
