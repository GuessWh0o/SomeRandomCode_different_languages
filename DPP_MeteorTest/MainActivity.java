package com.maks.ddpmeteortest;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.util.concurrent.ExecutionException;

import im.delight.android.ddp.Meteor;
import im.delight.android.ddp.MeteorCallback;
import im.delight.android.ddp.SubscribeListener;
import im.delight.android.ddp.db.Document;
import im.delight.android.ddp.db.memory.InMemoryDatabase;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import android.provider.Settings.Secure;
import android.widget.Toast;

public class MainActivity extends Activity implements MeteorCallback{

    private static final String TAG = MainActivity.class.getSimpleName();
    private Meteor meteor;
    private String activaireRegisterUrl = "http://192.168.1.114:4200/device/register/";
    private String activaireWebsocketUrl = "ws://192.168.1.114:4200/websocket";
    private String documentID;

    private String deviceID;
    private String macAddress;
    private String deviceSerial;
    private String deviceModel;

    private static String publicIpAddress = null;
    private static String internalIpAddress = null;
    private Double freeDeviceMemory;

    private Call call = null;


    //TODO: Put it in the Constants class
    public static final String IPIFY_HTTPS_URL = "https://api.ipify.org";
    public static final String IPIFY_HTTP_URL = "http://api.ipify.org";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDeviceInfo(getApplicationContext());
        showCollectedInfo();

        if (isNetworkAvailable()) {
            try {
                requestDocumentId();
                while (null != call && !call.isExecuted());

                Log.d(TAG, "documentID = " + documentID);
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
            initializeMeteor();
            subscribe();
        }
        else {
            String errMessage = "Network Unavailable";
            Toast.makeText(this, errMessage, Toast.LENGTH_LONG);
        }
    }



    private void initDeviceInfo(Context context){
        Log.d(TAG, "initDeviceInfo: ");

        deviceID = obtainDeviceID();
        internalIpAddress = obtainInternalIp(context);
        publicIpAddress = obtainPublicIp(true);
        macAddress = obtainMacAddress(context);
        deviceSerial = obtainSerial();
        deviceModel = obtainDeviceModel();
        freeDeviceMemory = obtainDeviceMemory();
    }
    void showCollectedInfo(){
        Log.d(TAG, "deviceID: "+ deviceID);
        Log.d(TAG, "deviceModel: "+ deviceModel);
        Log.d(TAG, "inernalIpAddress: "+ internalIpAddress );
        Log.d(TAG, "publicIpAddress: " + publicIpAddress);
        Log.d(TAG, "macAddress: "+ macAddress);
        Log.d(TAG, "deviceSerial: " + deviceSerial);
        Log.d(TAG, "freeMemory: " + freeDeviceMemory);

    }


    private String obtainDeviceID() {
        Log.d(TAG, "initializeDeviceID: deviceID");
        return Secure.getString(getApplicationContext().getContentResolver(),
                Secure.ANDROID_ID);
    }
    private static String obtainSerial(){
        return Build.SERIAL;
    }

    private static String obtainDeviceModel(){
        return Build.MANUFACTURER + " " + Build.MODEL;
    }

    private static String obtainInternalIp(Context context){
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        return String.format("%d.%d.%d.%d", (ipAddress & 0xff),(ipAddress >> 8 & 0xff),(ipAddress >> 16 & 0xff),(ipAddress >> 24 & 0xff));
    }

    private static String obtainPublicIp(final boolean useHttps)  {
        String ipAddr = null;
        try {
            ipAddr = new AsyncTask<String, String, String>() {
                protected String doInBackground(String... params) {
                    String resultedIP = "Unset publicIpAddr";
                    URL ipify = null;
                    URLConnection conn = null;
                    BufferedReader in = null;

                    try {
                        ipify = (useHttps ? new URL(IPIFY_HTTPS_URL) : new URL(IPIFY_HTTP_URL));
                        conn = ipify.openConnection();
                        in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        resultedIP = in.readLine();
                        in.close();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    return resultedIP;
                }
            }.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return ipAddr;
    }


    private static String obtainMacAddress(Context context) {
        Log.d(TAG, "initializeMacAddress: macAddress");

        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        return wifiInfo.getMacAddress();
    }

    private Double obtainDeviceMemory(){
        MemoryInfo memoryInfo = new MemoryInfo();
        memoryInfo.checkDeviceMemory();
        long extMem = memoryInfo.getAvailableStorageBytes();
        double extMemGB = memoryInfo.convertToGb(extMem);
        return extMemGB;
    }



    boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        return (networkInfo != null && networkInfo.isConnected());
    }

    void requestDocumentId() throws JSONException {
        SharedPreferences sharedPrefs = getPreferences(Context.MODE_PRIVATE);
        String deviceId = sharedPrefs.getString("DEVICEID", null);
        if(deviceId == null) {
            OkHttpClient client = new OkHttpClient();
            MediaType mediaJson = MediaType.parse("application/json; charset=utf-8");
            JSONObject jsonObject = new JSONObject();
            String key = (new BigInteger(130, new SecureRandom())).toString();
            jsonObject.put("uid", deviceID)
                    .put("name", macAddress)
                    .put("app_key", key)
                    .put("isVideo", true);

            RequestBody requestBody = RequestBody.create(mediaJson, jsonObject.toString());
            Request request = new Request.Builder()
                    .url(activaireRegisterUrl)
                    .post(requestBody)
                    .build();
            call = client.newCall(request);
            addCall();
        } else {
            documentID = deviceId;
        }
    }

    void addCall() {

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: Sending request failed!");
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonData = response.body().string();
                Log.d(TAG, "onResponse: Received: " + jsonData);

                try {
                    if (response.isSuccessful()) {
                        initializeDocumentID(jsonData);
                    }
                    else {
                        Log.d(TAG, "onResponse: Error on getting response");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initializeDocumentID(String jsonData) throws JSONException{
        JSONObject jsonID = new JSONObject(jsonData);

        documentID = jsonID.getString("_id");
        if (documentID == null || documentID == "") {
            throw  new JSONException("Invalid documentID");
        }
        Log.d(TAG, "First time init:  documentID  = " + documentID);
        SharedPreferences sharedPrefs = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString("DEVICEID", documentID);
        editor.commit();

    }

    void initializeMeteor() {
        meteor = new Meteor(this,  activaireWebsocketUrl, new InMemoryDatabase());
        meteor.addCallback(this);
        meteor.connect();
    }

    private void subscribe() {
        String subscriptionID = meteor.subscribe("videoPlayer",
                new Object[]{documentID}, new SubscribeListener() {
            @Override
            public void onSuccess() {
                Document deviceDoc = meteor.getDatabase()
                        .getCollection("devices").getDocument(documentID);
                Log.d(TAG, "Subscription: deviceDoc = " + deviceDoc);
            }

            @Override
            public void onError(String error, String reason, String details) {
                Log.d(TAG, "onError: Error = " + error);
                Log.d(TAG, "onError: Reason = " + reason);
                Log.d(TAG, "onError: Details = " + details);
            }
        });
    }

    @Override
    public void onConnect(boolean signedInAutomatically) {
        Log.d(TAG, "onConnect: Runnning!");
    }

    @Override
    public void onDisconnect() {
        Log.d(TAG, "onDisconnect: Running!");
    }

    @Override
    public void onException(Exception e) {
        Log.e("Catched Exception", "onException: ");
    }

    @Override
    public void onDataAdded(String collectionName, String documentID, String newValuesJson) {
        Log.d(TAG, "onDataAdded: Collection Name is = " + collectionName);
        Log.d(TAG, "onDataAdded: document ID is = " + documentID);
    }

    @Override
    public void onDataChanged(String collectionName, String documentID,
                              String updatedValuesJson, String removedValuesJson) {
        Log.d(TAG, "onDataChanged: Collection Name is = " + collectionName);
        Log.d(TAG, "onDataChanged: document ID is = " + documentID);
    }

    @Override
    public void onDataRemoved(String collectionName, String documentID) {

    }

    @Override
    protected void onDestroy() {
        meteor.disconnect();
        meteor.removeCallback(this);
        super.onDestroy();
    }
}

