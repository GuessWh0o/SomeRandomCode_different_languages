import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Maks on 10/26/2017.
 */

public class SaveLoadDifferentTypesToSharedPrefs {

    //TO USE GSON -> ADD:     compile 'com.google.code.gson:gson:2.7'  in bulid.gradle

    //HASHMAP SAVING

    public static void saveMap(Context context, Map<String, String> inputMap) {
        SharedPreferences pSharedPref = context.getSharedPreferences("MyVariables", Context.MODE_PRIVATE);
        if (pSharedPref != null) {
            JSONObject jsonObject = new JSONObject(inputMap);
            String jsonString = jsonObject.toString();
            SharedPreferences.Editor editor = pSharedPref.edit();
            editor.remove("My_map").commit();
            editor.putString("My_map", jsonString);
            editor.commit();
        }
    }


    //HASHMAP LOADING

    public static HashMap<String, String> loadMap(Context context) {
        HashMap<String, String> outputMap = new HashMap<>();
        SharedPreferences pSharedPref = context.getSharedPreferences("MyVariables", Context.MODE_PRIVATE);
        try {
            if (pSharedPref != null) {
                String jsonString = pSharedPref.getString("My_map", (new JSONObject()).toString());
                JSONObject jsonObject = new JSONObject(jsonString);
                Iterator<String> keysItr = jsonObject.keys();
                while (keysItr.hasNext()) {
                    String key = keysItr.next();
                    String value = (String) jsonObject.get(key);
                    outputMap.put(key, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outputMap;
    }


    //SAVING OBJECT


    public void saveObjectToHashMap(Object objectToSave) {
        SharedPreferences pSharedPref = ASIGApp.getAppContext().getSharedPreferences("MyVariables",
                Context.MODE_PRIVATE);
        if (pSharedPref != null) {

            Gson gson = new Gson();
            String json = gson.toJson(objectToSave);
            SharedPreferences.Editor editor = pSharedPref.edit();
            editor.remove("My KEY").commit();
            editor.putString("My KEY", json);
            editor.commit();
        }
    }


    //LOADING OBJECT

    public Object loadObjectFromSharedPrefs() {
        SharedPreferences pSharedPref = ASIGApp.getAppContext().getSharedPreferences("MyVariables", Context.MODE_PRIVATE);
        if (pSharedPref != null) {
            Gson gson = new Gson();
            String json = pSharedPref.getString("MY KEY", "");
            return gson.fromJson(json, Object.class);
        }
        return null;
    }
}
