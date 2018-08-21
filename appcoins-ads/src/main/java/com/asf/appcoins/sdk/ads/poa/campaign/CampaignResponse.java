package com.asf.appcoins.sdk.ads.poa.campaign;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

public class CampaignResponse {
  private List<CampaignEntry> result;

  public CampaignResponse() {
  }

  public List<CampaignEntry> getResult() {
    return result;
  }

  public void setResult(List<CampaignEntry> result) {
    this.result = result;
  }

  @JsonIgnoreProperties(ignoreUnknown = true) public static class CampaignEntry {
    private int bidId;

    public CampaignEntry() {
    }

    public int getBidId() {
      return bidId;
    }

    public void setBidId(int bidId) {
      this.bidId = bidId;
    }
  }
}
