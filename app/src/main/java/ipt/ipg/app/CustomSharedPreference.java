package ipt.ipg.app;

import android.content.Context;
import android.content.SharedPreferences;

public class CustomSharedPreference {

    private SharedPreferences sharedPref;

    public CustomSharedPreference(Context context) {
        sharedPref = context.getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE);
    }

    public SharedPreferences getInstanceOfSharedPreference(){
        return sharedPref;
    }

    //Guardar informações do utilizador
    public void setUserData(String userData){
        sharedPref.edit().putString("USER", userData).apply();
    }

    public String getUserData(){
        return sharedPref.getString("USER", "");
    }
}
