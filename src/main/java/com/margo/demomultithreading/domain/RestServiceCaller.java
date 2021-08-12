package com.margo.demomultithreading.domain;

import org.springframework.web.client.RestTemplate;

public class RestServiceCaller {
  private static RestTemplate restServiceCaller = null;

  public RestServiceCaller() {
  }

  public static RestTemplate getObjectSingle() {
    if (restServiceCaller == null) {
      restServiceCaller = new RestTemplate();
    }
    return restServiceCaller;
  }

}
