package com.chris.ok;

import com.couchbase.android.CouchbaseMobile;
import com.couchbase.android.ICouchbaseDelegate;
import com.phonegap.*;
import android.content.ServiceConnection;
import android.os.Bundle;

public class OkActivity extends DroidGap {
	private ServiceConnection couchServiceConnection;
	private DroidGap me;
	
	private final ICouchbaseDelegate couchCallbackHandler = new ICouchbaseDelegate() {
	    @Override
	    public void couchbaseStarted(String host, int port) {
	        me.loadUrl("http://127.0.0.1:"+port+"/photodemo-mom/_design/photodemo/index.html#mom");
	    }

	    @Override
	    public void exit(String error) {}
	};
	
	public void startCouchbase() {
        CouchbaseMobile couch = new CouchbaseMobile(getBaseContext(), couchCallbackHandler);
        couchServiceConnection = couch.startCouchbase();
	}
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        me = this;
        startCouchbase();
//        setContentView(R.layout.main);
    }
}