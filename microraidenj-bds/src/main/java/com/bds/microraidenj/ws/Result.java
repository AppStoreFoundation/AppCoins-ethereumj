package com.bds.microraidenj.ws;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigInteger;

public class Result {

  @JsonProperty("TxID") private String txID;
  @JsonProperty("amount") private BigInteger amount;
  @JsonProperty("block") private BigInteger block;
  @JsonProperty("receiver") private String receiver;
  @JsonProperty("sender") private String sender;
  @JsonProperty("ts") private String ts;
  @JsonProperty("type") private String type;

  public String getTxID() {
    return txID;
  }

  public void setTxID(String txID) {
    this.txID = txID;
  }

  public BigInteger getAmount() {
    return amount;
  }

  public void setAmount(BigInteger amount) {
    this.amount = amount;
  }

  public BigInteger getBlock() {
    return block;
  }

  public void setBlock(BigInteger block) {
    this.block = block;
  }

  public String getReceiver() {
    return receiver;
  }

  public void setReceiver(String receiver) {
    this.receiver = receiver;
  }

  public String getSender() {
    return sender;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }

  public String getTs() {
    return ts;
  }

  public void setTs(String ts) {
    this.ts = ts;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override public String toString() {
    return "Result{"
        + "txID='"
        + txID
        + '\''
        + ", amount="
        + amount
        + ", block="
        + block
        + ", receiver='"
        + receiver
        + '\''
        + ", sender='"
        + sender
        + '\''
        + ", ts='"
        + ts
        + '\''
        + ", type='"
        + type
        + '\''
        + '}';
  }
}