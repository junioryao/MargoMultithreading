package com.margo.demomultithreading.domain;

enum Timer {
  WAIT_FOR_16MIN(960000);
  long dataRessult;

  Timer(long i) {
    this.dataRessult = i;
  }

  public long getValue() {
    return dataRessult;
  }
}
