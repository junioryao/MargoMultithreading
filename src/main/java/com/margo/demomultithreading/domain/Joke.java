package com.margo.demomultithreading.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Joke  implements Comparable<Joke> {
  private int id;
  private String general;
  private String punchline;

  @Override
  public String toString() {
    return "" + "id=" + id + ", general='" + general + '\'' + ", punchline='" + punchline ;
  }

  @Override
  public int compareTo(Joke o) {
    if (this.getId() == o.getId()) return 0;
    else
      if (this.getId() > o.getId()) return 1;
      else return -1;
  }
}
