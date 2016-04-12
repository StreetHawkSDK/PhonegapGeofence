package com.streethawk.geofence;

import android.app.Activity;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import android.app.Service;
import android.content.Intent;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;
import android.os.IBinder;
import android.content.Context;
import com.streethawk.library.geofence.INotifyGeofenceTransition;
import INotifyGeofenceTransition.GeofenceData;

public class GeofenceWrapper extends Service implements INotifyGeofenceTransition{
    
    private final String TAG               = "StreetHawk";
    private final String SUBTAG            = "GeofenceWrapper ";
    private final String TRANSITION_TYPE   = "transition";
    private final String GEOFENCE_ID       = "id";
    private final String LAT               = "latitude";
    private final String LNG               = "longitude";
    private final String RADIUS            = "radius";
    private final String ENTER             = "enter";
    private final String EXIT              = "exit";

    private static GeofenceWrapper mInstance=null;
    private static CallbackContext shGeofenceEventCallBack;

    public static GeofenceWrapper getInstance(){
		if(null==mInstance){
			mInstance = new GeofenceWrapper();
		}
		return mInstance;
	}
 
    public void setNotifyGeofenceEventCallback(Activity activity,CallbackContext cb){
		 if(null==activity)
            return;
        Intent intent = new Intent(activity,GeofenceWrapper.class);
        activity.getApplicationContext().startService(intent);
        SHGeofence.getInstance(activity.getApplicationContext()).registerForGoefenceTransition(this);
        shGeofenceEventCallBack = cb;    
	}


    private sendGeofenceTxNotification(ArrayList<GeofenceData> geofences,String tx_type){
        if(null!=shGeofenceEventCallBack){
            JSONArray geofenceArray = new JSONArray();
            for(BeaconData data : beacons){
                JSONObject obj = new JSONObject();
                try {
                    obj.put(TRANSITION_TYPE,tx_type);
                    obj.put(GEOFENCE_ID, data.getGeofenceID());
                    obj.put(LAT, data.getLatitude());
                    obj.put(LNG, data.getLongitude());
                    obj.put(RADIUS, data.getRadius());
                }catch(JSONException e){
                    e.printStackTrace();
                }
                geofenceArray.put(obj);
            }
            PluginResult presult = new PluginResult(PluginResult.Status.OK,beaconArray);
            presult.setKeepCallback(true);
            shGeofenceEventCallBack.sendPluginResult(presult);
        }
    }

    @Override
    public void onDeviceEnteringGeofence() {
        ArrayList<GeofenceData> geofence = SHGeofence.getInstance(this).getGeofenceEnteredList();
        sendGeofenceTxNotification(geofence,ENTER);
    }

    @Override
    public void onDeviceLeavingGeofence() {
        ArrayList<GeofenceData> geofences = SHGeofence.getInstance(this).getGeofenceExitList();
        sendGeofenceTxNotification(geofence,EXIT);
    }
    @Override
	public IBinder onBind(Intent intent) {
		return null;
	}  
}
