package com.kryptkode.cyberman.djtech;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class SettingsActivityFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener{
    public final String PREFS_THEME = "theme_prefs";
    private static int themeSelect;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings_preference);

        PreferenceManager.setDefaultValues(getActivity(), R.xml.settings_preference, false);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        setListSummary(PREFS_THEME, sharedPreferences);
        themeSelect = Integer.parseInt(sharedPreferences.getString(PREFS_THEME, "0"));

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals(PREFS_THEME)){
           themeSelect = setListSummary(key, sharedPreferences);
            getActivity().recreate();
        }
    }

    public static int getThemeSelect() {
        return themeSelect;
    }

    @Override
    public void onResume() {
        super.onResume();
        PreferenceManager.getDefaultSharedPreferences(getActivity()).
                registerOnSharedPreferenceChangeListener(this);
    }

    private int setListSummary(String key, SharedPreferences sharedPreferences){
        Preference preference = findPreference(key);
        int themeSel = Integer.parseInt(sharedPreferences.getString(PREFS_THEME, "0"));
        preference.setSummary(getResources().getStringArray(R.array.theme_options)[themeSel]);
        return themeSel;
    }



    @Override
    public void onPause() {
        super.onPause();
        PreferenceManager.getDefaultSharedPreferences(getActivity())
                .unregisterOnSharedPreferenceChangeListener(this);
    }
    public void setAppTheme(){

    }


}
