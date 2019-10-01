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

public class ResponseDOTest {

  @Test
  public void builder() {

    assertThat(ResponseDO.builder(), is(notNullValue()));
  }

  @Test
  public void getInput1() {
    final var expected = UUID.randomUUID().toString();
    final var responseDO = ResponseDO.builder().input1(expected).build();
    assertThat(responseDO, is(notNullValue()));
    assertThat(responseDO.getInput1(), is(equalTo(expected)));
  }

  @Test
  public void getInput1a() {
    final var responseDO = ResponseDO.builder().input1(null).build();
    assertThat(responseDO, is(notNullValue()));
    assertThat(responseDO.getInput1(), is(equalTo(StringUtils.EMPTY)));
  }

  @Test
  public void getInput2() {
    final var expected = UUID.randomUUID().toString();
    final var requestDO = ResponseDO.builder().input2(expected).build();
    assertThat(requestDO, is(notNullValue()));
    assertThat(requestDO.getInput2(), is(equalTo(expected)));
  }

  @Test
  public void getInput2a() {
    final ResponseDO requestDO = ResponseDO.builder().input2(null).build();
    assertThat(requestDO, is(notNullValue()));
    assertThat(requestDO.getInput2(), is(equalTo(StringUtils.EMPTY)));
  }

  @Test
  public void getAnswer() {
    final var expected = UUID.randomUUID().toString();
    final var requestDO = ResponseDO.builder().answer(expected).build();
    assertThat(requestDO, is(notNullValue()));
    assertThat(requestDO.getAnswer(), is(equalTo(expected)));
  }

  @Test
  public void getAnswer1() {
    final var requestDO = ResponseDO.builder().answer(null).build();
    assertThat(requestDO, is(notNullValue()));
    assertThat(requestDO.getAnswer(), is(equalTo(StringUtils.EMPTY)));
  }

  @Test
  public void getError() {
    final var expected = UUID.randomUUID().toString();
    final var requestDO = ResponseDO.builder().error(expected).build();
    assertThat(requestDO, is(notNullValue()));
    assertThat(requestDO.getError(), is(equalTo(expected)));
  }

  @Test
  public void getError1() {
    final var requestDO = ResponseDO.builder().error(null).build();
    assertThat(requestDO, is(notNullValue()));
    assertThat(requestDO.getError(), is(equalTo(StringUtils.EMPTY)));
  }

  @Test
  public void testEquals() {
    final var expected1 = UUID.randomUUID().toString();
    final var expected2 = UUID.randomUUID().toString();
    final var expected3 = UUID.randomUUID().toString();
    final var expected4 = UUID.randomUUID().toString();

    final var requestDO = ResponseDO.builder()
        .input1(expected1)
        .input2(expected2)
        .answer(expected3)
        .error(expected4).build();
    assertThat(requestDO, is(notNullValue()));

    final var requestDO1 = ResponseDO.builder()
        .input1(expected1)
        .input2(expected2)
        .answer(expected3)
        .error(expected4).build();
    assertThat(requestDO1, is(notNullValue()));

    assertThat(requestDO, is(equalTo(requestDO1)));
  }

  @Test
  public void testEquals1() {
    final var expected1 = UUID.randomUUID().toString();
    final var expected2 = UUID.randomUUID().toString();
    final var expected3 = UUID.randomUUID().toString();
    final var expected4 = UUID.randomUUID().toString();

    final var requestDO = ResponseDO.builder()
        .input1(expected1)
        .input2(expected2)
        .answer(expected3)
        .error(expected4).build();
    assertThat(requestDO, is(notNullValue()));

    final var requestDO1 = ResponseDO.builder()
        .input1(expected1)
        .input2(expected2)
        .answer(expected3)
        .error(UUID.randomUUID().toString()).build();
    assertThat(requestDO1, is(notNullValue()));

    assertThat(requestDO, is(not(equalTo(requestDO1))));
  }

  @Test
  public void testHashCode() {
    final var expected1 = UUID.randomUUID().toString();
    final var expected2 = UUID.randomUUID().toString();
    final var expected3 = UUID.randomUUID().toString();
    final var expected4 = UUID.randomUUID().toString();

    final var requestDO = ResponseDO.builder()
        .input1(expected1)
        .input2(expected2)
        .answer(expected3)
        .error(expected4).build();
    assertThat(requestDO, is(notNullValue()));

    final var requestDO1 = ResponseDO.builder()
        .input1(expected1)
        .input2(expected2)
        .answer(expected3)
        .error(expected4).build();
    assertThat(requestDO1, is(notNullValue()));
    assertThat(requestDO.hashCode() == requestDO1.hashCode(), is(equalTo(true)));
  }

  @Test
  public void testEquals3() {
    final var expected1 = UUID.randomUUID().toString();
    final var expected2 = UUID.randomUUID().toString();
    final var expected3 = UUID.randomUUID().toString();
    final var expected4 = UUID.randomUUID().toString();

    final var requestDO = ResponseDO.builder()
        .input1(expected1)
        .input2(expected2)
        .answer(expected3)
        .error(expected4).build();
    assertThat(requestDO, is(notNullValue()));

    final ResponseDO requestDO1 = null;
    assertThat(requestDO1, is(nullValue()));

    assertThat(requestDO, is(not(equalTo(requestDO1))));
  }

  @Test
  public void testEqual4() {
    final var expected1 = UUID.randomUUID().toString();
    final var expected2 = UUID.randomUUID().toString();
    final var expected3 = UUID.randomUUID().toString();
    final var expected4 = UUID.randomUUID().toString();

    final var requestDO = ResponseDO.builder()
        .input1(expected1)
        .input2(expected2)
        .answer(expected3)
        .error(expected4).build();
    assertThat(requestDO, is(notNullValue()));

    final String requestDO1 = null;
    assertThat(requestDO1, is(nullValue()));

    assertThat(requestDO.equals(requestDO1), is(equalTo(false)));
  }

  @Test
  public void testToString() {
    final var expected1 = UUID.randomUUID().toString();
    final var expected2 = UUID.randomUUID().toString();
    final var expected3 = UUID.randomUUID().toString();
    final var expected4 = UUID.randomUUID().toString();

    final var requestDO = ResponseDO.builder()
        .input1(expected1)
        .input2(expected2)
        .answer(expected3)
        .error(expected4).build();
    assertThat(requestDO, is(notNullValue()));
    assertThat(requestDO.toString(), is(not(equalTo(StringUtils.EMPTY))));
  }
}