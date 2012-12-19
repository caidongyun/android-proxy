package com.lechucksoftware.proxy.proxysettings.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.SimpleAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.lechucksoftware.proxy.proxysettings.R;
import com.lechucksoftware.proxy.proxysettings.fragments.APListPrefsFragment;
import com.lechucksoftware.proxy.proxysettings.preferences.AccessPoint;
import com.shouldit.proxy.lib.ProxyConfiguration;
import com.shouldit.proxy.lib.ProxySettings;

public class ProxyPreferencesActivity extends PreferenceActivity implements OnNavigationListener
{
	public static ProxyPreferencesActivity instance;

	// declare the dialog as a member field of your activity
	private ProgressDialog mProgressDialog;

	private SpinnerAdapter mSpinnerAdapter;

	private OnNavigationListener mNavigationCallback;

	// static Preference appsFeedbackPref;

	public void showProgressDialog()
	{
		if (mProgressDialog != null)
			mProgressDialog.show();
	}

	public void dismissProgressDialog()
	{
		if (mProgressDialog != null && mProgressDialog.isShowing())
			mProgressDialog.dismiss();
	}

	public void setProgressDialogMessage(String message)
	{
		if (mProgressDialog != null)
			mProgressDialog.setMessage(message);
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		actionBar.setTitle("");
		
		SpinnerAdapter mSpinnerAdapter = new AccessPointListAdapter();
		
		actionBar.setListNavigationCallbacks(mSpinnerAdapter, this);
		

//		final List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
//		
//		List<ProxyConfiguration> confs = ProxySettings.getProxiesConfigurations(getApplicationContext());
//		
//		for (ProxyConfiguration conf : confs)
//		{
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("title", conf.wifiConfiguration.SSID);
//			map.put("pconf", conf);
//			data.add(map);
//		}	
//		
//		SimpleAdapter adapter = new SimpleAdapter(this, data, android.R.layout.simple_spinner_dropdown_item, new String[] { "title" }, new int[] { android.R.id.text1 });
//
//		actionBar.setListNavigationCallbacks(adapter, new OnNavigationListener()
//		{
//			public boolean onNavigationItemSelected(int itemPosition, long itemId)
//			{
//				Map<String, Object> map = data.get(itemPosition);
//				Object o = map.get("fragment");
//				if (o instanceof Fragment)
//				{
//					// FragmentTransaction tx =
//					// getFragmentManager().beginTransaction();
//					// tx.replace( android.R.id.content,
//					// (Fragment )o );
//					// tx.commit();
//				}
//				return true;
//			}
//		});
	}

	@Override
	public void onBuildHeaders(List<Header> target)
	{
		loadHeadersFromResource(R.xml.preferences_header, target);
	}

	private class AccessPointListAdapter extends BaseAdapter implements SpinnerAdapter
	{
		public int getCount()
		{
			return 3;
		}

		public Object getItem(int position)
		{
			return "item" + position;
		}

		public long getItemId(int position)
		{
			return position;
		}

		public View getView(int position, View view, ViewGroup parent)
		{
			TextView text = new TextView(getApplicationContext());
			text.setText(getItem(position).toString());

			return text;
		}
	}

	public boolean onNavigationItemSelected(int itemPosition, long itemId)
	{
		// TODO Auto-generated method stub
		return false;
	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu)
	// {
	// MenuInflater inflater = getMenuInflater();
	// inflater.inflate(R.menu.proxy_prefs_activity, menu);
	// return true;
	// }
}
