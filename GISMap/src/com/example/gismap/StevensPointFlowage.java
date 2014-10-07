package com.example.gismap;

import com.esri.android.map.LocationService;
import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISTiledMapServiceLayer;
import com.esri.android.map.event.OnStatusChangedListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class StevensPointFlowage extends Activity {
	
	MapView mapView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stevens_point_flowage);
		
		mapView = (MapView)findViewById(R.id.map);
		
		mapView.addLayer(new ArcGISTiledMapServiceLayer(""+
		"http://services.arcgisonline.com/ArcGIS/rest/services/World_Street_Map/MapServer"));
		
		mapView.setOnStatusChangedListener(
				new OnStatusChangedListener(){
					public void onStatusChanged(Object source, STATUS status){
						if(source == mapView && status == STATUS.INITIALIZED){
							LocationService ls = mapView.getLocationService();
							ls.setAutoPan(true);
							ls.start();
						}
					}
				});
	}
	
	protected void onPause(){
		super.onPause();
		mapView.pause();
	}
	
	protected void onResume(){
		super.onResume();
		mapView.unpause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.stevens_point_flowage, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
