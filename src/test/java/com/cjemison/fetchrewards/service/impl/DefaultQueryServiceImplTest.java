package com.cjemison.fetchrewards.service.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import com.cjemison.fetchrewards.domain.RequestDO;
import com.cjemison.fetchrewards.domain.ResponseDO;
import java.util.Random;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import reactor.core.publisher.Mono;

public class DefaultQueryServiceImplTest {

  private DefaultQueryServiceImpl defaultQueryService;

  @Before
  public void setup() {
    defaultQueryService = new DefaultQueryServiceImpl();
  }

  @Test
  public void query() {
    final Integer value1 = Math.abs(new Random().nextInt() % 1000);
    final Integer value2 = value1 + 1;

    final var request = RequestDO.builder()
        .input1(value1.toString())
        .input2(value2.toString())
        .build();
    assertThat(request, is(notNullValue()));

    final Mono<ResponseDO> mono = defaultQueryService.query(request);
    assertThat(mono, is(notNullValue()));

    mono.subscribe(responseDO -> {
      assertThat(responseDO.getInput1(), is(equalTo(request.getInput1())));
      assertThat(responseDO.getInput2(), is(equalTo(request.getInput2())));
      assertThat(responseDO.getAnswer(),
          is(equalTo(String.format("%s is before %s", request.getInput1(), request.getInput2()))));
      assertThat(responseDO.getError(), is(equalTo(StringUtils.EMPTY)));
    });
  }

  @Test
  public void query2() {

    final Integer value2 = Math.abs(new Random().nextInt() % 1000);
    final Integer value1 = value2 + 1000;

    final var request = RequestDO.builder()
        .input1(value1.toString())
        .input2(value2.toString())
        .build();
    assertThat(request, is(notNullValue()));

    final Mono<ResponseDO> mono = defaultQueryService.query(request);
    assertThat(mono, is(notNullValue()));

    mono.subscribe(responseDO -> {
      assertThat(responseDO.getInput1(), is(equalTo(request.getInput1())));
      assertThat(responseDO.getInput2(), is(equalTo(request.getInput2())));
      assertThat(responseDO.getAnswer(),
          is(equalTo(String.format("%s is after %s", request.getInput1(), request.getInput2()))));
      assertThat(responseDO.getError(), is(equalTo(StringUtils.EMPTY)));
    });
  }

  @Test
  public void badQuery() {
    final Integer value1 = Math.abs(new Random().nextInt() % 1000);
    final Integer value2 = value1 + 1;

    final var request = RequestDO.builder()
        .input2(value2.toString())
        .build();
    assertThat(request, is(notNullValue()));

    final Mono<ResponseDO> mono = defaultQueryService.query(request);
    assertThat(mono, is(notNullValue()));

    mono.subscribe(responseDO -> {
      assertThat(responseDO.getInput1(), is(equalTo(StringUtils.EMPTY)));
      assertThat(responseDO.getInput2(), is(equalTo(request.getInput2())));
      assertThat(responseDO.getAnswer(), is(equalTo(StringUtils.EMPTY)));
      assertThat(responseDO.getError(), is(not(equalTo(StringUtils.EMPTY))));
    });
  }

  @Test
  public void badQuery2() {
    final Integer value1 = Math.abs(new Random().nextInt() % 1000);
    final var request = RequestDO.builder()
        .input1(value1.toString())
        .build();
    assertThat(request, is(notNullValue()));

    final Mono<ResponseDO> mono = defaultQueryService.query(request);
    assertThat(mono, is(notNullValue()));

    mono.subscribe(responseDO -> {
      assertThat(responseDO.getInput1(), is(equalTo(value1.toString())));
      assertThat(responseDO.getInput2(), is(equalTo(StringUtils.EMPTY)));
      assertThat(responseDO.getAnswer(), is(equalTo(StringUtils.EMPTY)));
      assertThat(responseDO.getError(), is(not(equalTo(StringUtils.EMPTY))));
    });
  }

  @Test
  public void badQuery3() {

    final var request = RequestDO.builder()
        .input1(UUID.randomUUID().toString())
        .input2(UUID.randomUUID().toString())
        .build();
    assertThat(request, is(notNullValue()));

    final Mono<ResponseDO> mono = defaultQueryService.query(request);
    assertThat(mono, is(notNullValue()));

    mono.subscribe(responseDO -> {
      assertThat(responseDO.getInput1(), is(equalTo(request.getInput1())));
      assertThat(responseDO.getInput2(), is(equalTo(request.getInput2())));
      assertThat(responseDO.getAnswer(), is(equalTo(StringUtils.EMPTY)));
      assertThat(responseDO.getError(), is(not(equalTo(StringUtils.EMPTY))));
    });
  }

  @Test
  public void badQuery4() {

    final var request = RequestDO.builder()
        .input1("1")
        .input2(UUID.randomUUID().toString())
        .build();
    assertThat(request, is(notNullValue()));

    final Mono<ResponseDO> mono = defaultQueryService.query(request);
    assertThat(mono, is(notNullValue()));

    mono.subscribe(responseDO -> {
      assertThat(responseDO.getInput1(), is(equalTo(request.getInput1())));
      assertThat(responseDO.getInput2(), is(equalTo(request.getInput2())));
      assertThat(responseDO.getAnswer(), is(equalTo(StringUtils.EMPTY)));
      assertThat(responseDO.getError(), is(not(equalTo(StringUtils.EMPTY))));
    });
  }

  @Test
  public void badQuery5() {

    final var request = RequestDO.builder()
        .input1(UUID.randomUUID().toString())
        .input2("2")
        .build();
    assertThat(request, is(notNullValue()));

    final Mono<ResponseDO> mono = defaultQueryService.query(request);
    assertThat(mono, is(notNullValue()));

    mono.subscribe(responseDO -> {
      assertThat(responseDO.getInput1(), is(equalTo(request.getInput1())));
      assertThat(responseDO.getInput2(), is(equalTo(request.getInput2())));
      assertThat(responseDO.getAnswer(), is(equalTo(StringUtils.EMPTY)));
      assertThat(responseDO.getError(), is(not(equalTo(StringUtils.EMPTY))));
    });
  }

  @Test
  public void badQuery6() {

    final var request = RequestDO.builder()
        .input1("2")
        .input2("2")
        .build();
    assertThat(request, is(notNullValue()));

    final Mono<ResponseDO> mono = defaultQueryService.query(request);
    assertThat(mono, is(notNullValue()));

    mono.subscribe(responseDO -> {
      assertThat(responseDO.getInput1(), is(equalTo(request.getInput1())));
      assertThat(responseDO.getInput2(), is(equalTo(request.getInput2())));
      assertThat(responseDO.getAnswer(), is(equalTo(StringUtils.EMPTY)));
      assertThat(responseDO.getError(), is(not(equalTo(StringUtils.EMPTY))));
    });
  }
}