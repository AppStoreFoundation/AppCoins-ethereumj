package com.appcoins.sdk.android_appcoins_billing;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.util.Log;
import com.appcoins.sdk.android_appcoins_billing.helpers.WalletUtils;
import com.appcoins.sdk.billing.AppCoinsBillingStateListener;
import java.util.List;

public class RepositoryServiceConnection implements ServiceConnection, RepositoryConnection {
  private static final String TAG = RepositoryServiceConnection.class.getSimpleName();
  private final Context context;
  private final ConnectionLifeCycle connectionLifeCycle;
  private AppCoinsBillingStateListener listener;

  public RepositoryServiceConnection(Context context, ConnectionLifeCycle connectionLifeCycle) {
    this.context = context;
    this.connectionLifeCycle = connectionLifeCycle;
  }

  @Override public void onServiceConnected(ComponentName name, IBinder service) {
    Log.d(TAG,
        "onServiceConnected() called with: name = [" + name + "], service = [" + service + "]");
    connectionLifeCycle.onConnect(service, listener);
  }

  @Override public void onServiceDisconnected(ComponentName name) {
    Log.d(TAG, "onServiceDisconnected() called with: name = [" + name + "]");
    connectionLifeCycle.onDisconnect(listener);
  }

  @Override public void onBindingDied(ComponentName name) {
    connectionLifeCycle.onDisconnect(listener);
  }

  @Override public void onNullBinding(ComponentName name) {
    connectionLifeCycle.onDisconnect(listener);
  }

  @Override public void startConnection(final AppCoinsBillingStateListener listener) {
    if(!WalletUtils.hasWalletInstalled(context)){
      connectionLifeCycle.onConnect(null, listener);
    }
    else{
      Intent serviceIntent = new Intent(BuildConfig.IAB_BIND_ACTION);
      serviceIntent.setPackage(BuildConfig.IAB_BIND_PACKAGE);

      this.listener = listener;

      List<ResolveInfo> intentServices = context.getPackageManager()
          .queryIntentServices(serviceIntent, 0);
      if (intentServices != null && !intentServices.isEmpty()) {
        context.bindService(serviceIntent, this, Context.BIND_AUTO_CREATE);
      }
    }
  }

  @Override public void endConnection() {
    context.unbindService(this);
    connectionLifeCycle.onDisconnect(listener);
  }
}
