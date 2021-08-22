package com.margo.demomultithreading.domain;

import org.springframework.web.client.RestTemplate;

public class RestServiceCaller {
  private static RestTemplate restServiceCaller = new RestTemplate();

  public RestServiceCaller() {}

  public static RestTemplate getObjectSingle() {
    return restServiceCaller;
  }
}
