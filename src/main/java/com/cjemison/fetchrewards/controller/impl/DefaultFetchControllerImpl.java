package com.cjemison.fetchrewards.controller.impl;

import com.cjemison.fetchrewards.controller.IFetchController;
import com.cjemison.fetchrewards.domain.RequestDO;
import com.cjemison.fetchrewards.domain.ResponseDO;
import com.cjemison.fetchrewards.service.IQueryService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import java.util.concurrent.Executor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api("Endpoints for querying which numeric value is larger.")
public class DefaultFetchControllerImpl implements IFetchController {

  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultFetchControllerImpl.class);
  private static final String REQUEST_TIMER = "com.cjemison.fetchrewards.controller.timer.query";
  private final IQueryService queryService;
  private static final String SUCCESS_QUERY = "com.cjemison.fetchrewards.controller.count.success";
  private static final String FAILED_QUERY = "com.cjemison.fetchrewards.controller.count.failed";
  private final Executor executor;
  private final Counter SUCCESS_QUERY_COUNTER;
  private final Counter FAILED_QUERY_COUNTER;


  @Autowired
  public DefaultFetchControllerImpl(
      @Qualifier("threadPoolTaskExecutor") final Executor executor,
      final IQueryService queryService,
      final MeterRegistry meterRegistry) {
    this.executor = executor;
    this.queryService = queryService;
    this.SUCCESS_QUERY_COUNTER = Counter.builder(SUCCESS_QUERY).register(meterRegistry);
    this.FAILED_QUERY_COUNTER = Counter.builder(FAILED_QUERY).register(meterRegistry);
  }

  @Override
  @ApiOperation("An endpoint for querying which numeric value is larger.")
  @ApiResponse(code = 200, message = "Querying which numeric value is larger.",
      response = ResponseDO.class)
  @RequestMapping(value = "/query", method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Mono<ResponseEntity<?>> post(@RequestBody final RequestDO requestDO) {
    LOGGER.debug("RequestDO: {}", requestDO);
    return queryService.query(requestDO)
        .name(REQUEST_TIMER)
        .subscribeOn(Schedulers.fromExecutor(executor))
        .flatMap(responseDO -> {
          Mono<ResponseEntity<?>> mono;
          if (responseDO.getError().equals(StringUtils.EMPTY)) {
            SUCCESS_QUERY_COUNTER.increment();
            mono = Mono.just(ResponseEntity.ok(responseDO));
          } else {
            FAILED_QUERY_COUNTER.increment();
            mono = Mono.just(ResponseEntity.badRequest().body(responseDO));
          }
          return mono;
        }).metrics();
  }
}
