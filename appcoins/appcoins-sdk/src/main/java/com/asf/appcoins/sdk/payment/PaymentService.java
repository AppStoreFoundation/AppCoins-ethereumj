package com.asf.appcoins.sdk.payment;

import android.app.Activity;
import android.content.Intent;
import com.asf.appcoins.sdk.AsfWeb3j;
import com.asf.appcoins.sdk.SkuManager;
import com.asf.appcoins.sdk.entity.Transaction;
import com.asf.appcoins.sdk.util.UriBuilder;
import io.reactivex.Observable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by neuro on 08-03-2018.
 */
public final class PaymentService {

  private static final int DECIMALS = 18;
  private final int networkId;
  private final SkuManager skuManager;
  private final String developerAddress;
  private final Map<String, Transaction> transactions;
  private AsfWeb3j asfWeb3j;
  private Object payment;

  public PaymentService(int networkId, SkuManager skuManager, String developerAddress) {
    this.networkId = networkId;
    this.skuManager = skuManager;
    this.developerAddress = developerAddress;
    this.transactions = new HashMap<>(1);
  }

  public void buy(String skuId, Activity activity, int defaultRequestCode) {
    Intent intent = new Intent(Intent.ACTION_VIEW);

    BigDecimal amount = skuManager.getSkuAmount(skuId);
    BigDecimal total = amount.multiply(BigDecimal.TEN.pow(DECIMALS));

    intent.setData(
        UriBuilder.buildUri("0x8dd69259800b37aee1eb60836a18d313965278f6", skuId, networkId, total,
            developerAddress));
    activity.startActivityForResult(intent, defaultRequestCode);
  }

  public Observable<PaymentStatus> getPaymentStatus(String skuId) {
    return asfWeb3j.getTransactionByHash(getTransactionHash(skuId))
        .map(transaction -> new PaymentStatus(skuId, transaction));
  }

  private String getTransactionHash(String skuId) {
    return transactions.get(skuId)
        .getHash();
  }
}