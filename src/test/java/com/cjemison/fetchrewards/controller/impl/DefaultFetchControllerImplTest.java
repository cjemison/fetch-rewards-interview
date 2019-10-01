package com.cjemison.fetchrewards.controller.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import com.cjemison.fetchrewards.config.WebConfig;
import com.cjemison.fetchrewards.domain.RequestDO;
import java.util.Random;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {WebConfig.class})
public class DefaultFetchControllerImplTest {

  @Autowired
  private WebTestClient webTestClient;

  @Before
  public void setUp() throws Exception {

  }

  @Test
  public void postIntegers() {

    final Integer value1 = Math.abs(new Random().nextInt() % 1000);
    final Integer value2 = value1 + 1;

    final var request = RequestDO.builder()
        .input1(value1.toString())
        .input2(value2.toString())
        .build();
    assertThat(request, is(notNullValue()));

    webTestClient.post()
        .uri("/v1/query")
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .body(BodyInserters.fromObject(request))
        .exchange()
        .expectStatus().is2xxSuccessful()
        .expectBody()
        .jsonPath("input_1").isEqualTo(value1.toString())
        .jsonPath("input_2").isEqualTo(value2.toString())
        .jsonPath("answer")
        .isEqualTo(String.format("%s is before %s", request.getInput1(), request.getInput2()))
        .jsonPath("error").isEmpty();
  }


  @Test
  public void postDecimals() {

    final Double value1 = Math.abs(new Random().nextDouble() % 1000);
    final Double value2 = value1 + 1;

    final var request = RequestDO.builder()
        .input1(value1.toString())
        .input2(value2.toString())
        .build();
    assertThat(request, is(notNullValue()));

    webTestClient.post()
        .uri("/v1/query")
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .body(BodyInserters.fromObject(request))
        .exchange()
        .expectStatus().is2xxSuccessful()
        .expectBody()
        .jsonPath("input_1").isEqualTo(value1.toString())
        .jsonPath("input_2").isEqualTo(value2.toString())
        .jsonPath("answer")
        .isEqualTo(String.format("%s is before %s", request.getInput1(), request.getInput2()))
        .jsonPath("error").isEmpty();
  }

  @Test
  public void postFloats() {

    final Float value1 = Math.abs(new Random().nextFloat() % 1000);
    final Float value2 = value1 + 1L;

    final var request = RequestDO.builder()
        .input1(value1.toString())
        .input2(value2.toString())
        .build();
    assertThat(request, is(notNullValue()));

    webTestClient.post()
        .uri("/v1/query")
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .body(BodyInserters.fromObject(request))
        .exchange()
        .expectStatus().is2xxSuccessful()
        .expectBody()
        .jsonPath("input_1").isEqualTo(value1.toString())
        .jsonPath("input_2").isEqualTo(value2.toString())
        .jsonPath("answer")
        .isEqualTo(String.format("%s is before %s", request.getInput1(), request.getInput2()))
        .jsonPath("error").isEmpty();
  }

  @Test
  public void postLongs() {

    final Long value1 = Math.abs(new Random().nextLong() % 1000);
    final Long value2 = value1 + 1L;

    final var request = RequestDO.builder()
        .input1(value1.toString())
        .input2(value2.toString())
        .build();
    assertThat(request, is(notNullValue()));

    webTestClient.post()
        .uri("/v1/query")
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .body(BodyInserters.fromObject(request))
        .exchange()
        .expectStatus().is2xxSuccessful()
        .expectBody()
        .jsonPath("input_1").isEqualTo(value1.toString())
        .jsonPath("input_2").isEqualTo(value2.toString())
        .jsonPath("answer")
        .isEqualTo(String.format("%s is before %s", request.getInput1(), request.getInput2()))
        .jsonPath("error").isEmpty();
  }

  @Test
  public void postMixed() {

    final Integer value1 = Math.abs(new Random().nextInt() % 1000);
    final Double value2 = Math.abs(new Random().nextDouble() % 1000) + value1;

    final var request = RequestDO.builder()
        .input1(value1.toString())
        .input2(value2.toString())
        .build();
    assertThat(request, is(notNullValue()));

    webTestClient.post()
        .uri("/v1/query")
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .body(BodyInserters.fromObject(request))
        .exchange()
        .expectStatus().is2xxSuccessful()
        .expectBody()
        .jsonPath("input_1").isEqualTo(value1.toString())
        .jsonPath("input_2").isEqualTo(value2.toString())
        .jsonPath("answer")
        .isEqualTo(String.format("%s is before %s", request.getInput1(), request.getInput2()))
        .jsonPath("error").isEmpty();
  }

  @Test
  public void postMixed1() {

    final Long value1 = Math.abs(new Random().nextLong() % 1000);
    final Double value2 = Math.abs(new Random().nextDouble() % 1000) + value1;

    final var request = RequestDO.builder()
        .input1(value1.toString())
        .input2(value2.toString())
        .build();
    assertThat(request, is(notNullValue()));

    webTestClient.post()
        .uri("/v1/query")
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .body(BodyInserters.fromObject(request))
        .exchange()
        .expectStatus().is2xxSuccessful()
        .expectBody()
        .jsonPath("input_1").isEqualTo(value1.toString())
        .jsonPath("input_2").isEqualTo(value2.toString())
        .jsonPath("answer")
        .isEqualTo(String.format("%s is before %s", request.getInput1(), request.getInput2()))
        .jsonPath("error").isEmpty();
  }


  @Test
  public void postBad() {

    final Long value1 = Math.abs(new Random().nextLong() % 1000);
    final String value2 = UUID.randomUUID().toString();

    final var request = RequestDO.builder()
        .input1(value1.toString())
        .input2(value2)
        .build();
    assertThat(request, is(notNullValue()));

    webTestClient.post()
        .uri("/v1/query")
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .body(BodyInserters.fromObject(request))
        .exchange()
        .expectStatus().isBadRequest()
        .expectBody()
        .jsonPath("input_1").isEqualTo(value1.toString())
        .jsonPath("input_2").isEqualTo(value2)
        .jsonPath("answer").isEmpty()
        .jsonPath("error").isNotEmpty();
  }

  @Test
  public void postBad1() {

    final String value1 = UUID.randomUUID().toString();
    final String value2 = UUID.randomUUID().toString();

    final var request = RequestDO.builder()
        .input1(value1)
        .input2(value2)
        .build();
    assertThat(request, is(notNullValue()));

    webTestClient.post()
        .uri("/v1/query")
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .body(BodyInserters.fromObject(request))
        .exchange()
        .expectStatus().isBadRequest()
        .expectBody()
        .jsonPath("input_1").isEqualTo(value1)
        .jsonPath("input_2").isEqualTo(value2)
        .jsonPath("answer").isEmpty()
        .jsonPath("error").isNotEmpty();
  }


}