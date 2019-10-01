package com.cjemison.fetchrewards.controller.impl;

import com.cjemison.fetchrewards.controller.IFetchController;
import com.cjemison.fetchrewards.domain.RequestDO;
import com.cjemison.fetchrewards.domain.ResponseDO;
import com.cjemison.fetchrewards.service.IQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api("Endpoints for querying which numeric value is larger.")
public class DefaultFetchControllerImpl implements IFetchController {

  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultFetchControllerImpl.class);
  private final IQueryService queryService;

  @Autowired
  public DefaultFetchControllerImpl(final IQueryService queryService) {
    this.queryService = queryService;
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
        .flatMap(responseDO -> {
          if (responseDO.getError().equals(StringUtils.EMPTY)) {
            return Mono.just(ResponseEntity.ok(responseDO));
          }
          return Mono.just(ResponseEntity.badRequest().body(responseDO));
        });
  }
}
