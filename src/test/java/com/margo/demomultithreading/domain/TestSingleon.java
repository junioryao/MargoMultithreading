package com.margo.demomultithreading.domain;

public class TestSingleon {
  RestServiceCaller restServiceCaller ;

  public TestSingleon(RestServiceCaller restServiceCaller) {
    this.restServiceCaller = restServiceCaller;
  }
}
