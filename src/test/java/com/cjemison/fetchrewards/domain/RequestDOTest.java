package com.cjemison.fetchrewards.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class RequestDOTest {

  @Test
  public void builder() {

    assertThat(RequestDO.builder(), is(notNullValue()));
  }

  @Test
  public void getInput1() {
    final var expected = UUID.randomUUID().toString();
    final RequestDO requestDO = RequestDO.builder().input1(expected).build();
    assertThat(requestDO, is(notNullValue()));
    assertThat(requestDO.getInput1(), is(equalTo(expected)));
  }

  @Test
  public void getInput1a() {
    final RequestDO requestDO = RequestDO.builder().input1(null).build();
    assertThat(requestDO, is(notNullValue()));
    assertThat(requestDO.getInput1(), is(equalTo(StringUtils.EMPTY)));
  }

  @Test
  public void getInput2() {
    final var expected = UUID.randomUUID().toString();
    final RequestDO requestDO = RequestDO.builder().input2(expected).build();
    assertThat(requestDO, is(notNullValue()));
    assertThat(requestDO.getInput2(), is(equalTo(expected)));
  }

  @Test
  public void getInput2a() {
    final RequestDO requestDO = RequestDO.builder().input2(null).build();
    assertThat(requestDO, is(notNullValue()));
    assertThat(requestDO.getInput2(), is(equalTo(StringUtils.EMPTY)));
  }

  @Test
  public void testEquals() {
    final var expected1 = UUID.randomUUID().toString();
    final var expected2 = UUID.randomUUID().toString();

    final RequestDO requestDO = RequestDO.builder().input1(expected1).input2(expected2).build();
    assertThat(requestDO, is(notNullValue()));

    final RequestDO requestDO1 = RequestDO.builder().input1(expected1).input2(expected2).build();
    assertThat(requestDO1, is(notNullValue()));

    assertThat(requestDO, is(equalTo(requestDO1)));
  }

  @Test
  public void testEquals1() {
    final var expected1 = UUID.randomUUID().toString();
    final var expected2 = UUID.randomUUID().toString();

    final RequestDO requestDO = RequestDO.builder().input1(expected1).input2(expected2).build();
    assertThat(requestDO, is(notNullValue()));

    final RequestDO requestDO1 = null;
    assertThat(requestDO1, is(nullValue()));

    assertThat(requestDO, is(not(equalTo(requestDO1))));
  }

  @Test
  public void testEquals2() {
    final var expected1 = UUID.randomUUID().toString();
    final var expected2 = UUID.randomUUID().toString();

    final RequestDO requestDO = RequestDO.builder().input1(expected1).input2(expected2).build();
    assertThat(requestDO, is(notNullValue()));
    assertThat(requestDO, is(equalTo(requestDO)));
  }

  @Test
  public void testHashCode() {
    final var expected1 = UUID.randomUUID().toString();
    final var expected2 = UUID.randomUUID().toString();

    final RequestDO requestDO = RequestDO.builder().input1(expected1).input2(expected2).build();
    assertThat(requestDO, is(notNullValue()));

    final RequestDO requestDO1 = RequestDO.builder().input1(expected1).input2(expected2).build();
    assertThat(requestDO1, is(notNullValue()));

    assertThat(requestDO.hashCode() == requestDO1.hashCode(), is(equalTo(true)));
  }

  @Test
  public void testToString() {

    final var expected1 = UUID.randomUUID().toString();
    final var expected2 = UUID.randomUUID().toString();

    final RequestDO requestDO = RequestDO.builder().input1(expected1).input2(expected2).build();
    assertThat(requestDO, is(notNullValue()));
    assertThat(requestDO.toString(), is(not(equalTo(StringUtils.EMPTY))));
  }
}