package com.margo.demomultithreading.domain;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RestServiceCallerTest {

  @Test
  void getObjectSingle() throws NoSuchMethodException{
    assertThat(RestServiceCaller.getObjectSingle()).isInstanceOf(RestTemplate.class);
    Constructor<RestServiceCaller> constructor =  RestServiceCaller.class.getDeclaredConstructor();
    assertThat(Modifier.isPrivate(constructor.getModifiers())).isTrue();
  }
}