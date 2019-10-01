package com.cjemison.fetchrewards.service.impl;

import com.cjemison.fetchrewards.domain.RequestDO;
import com.cjemison.fetchrewards.domain.ResponseDO;
import com.cjemison.fetchrewards.service.IQueryService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DefaultQueryServiceImpl implements IQueryService {

  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultQueryServiceImpl.class);

  @Override
  public Mono<ResponseDO> query(final RequestDO requestDO) {
    LOGGER.debug("RequestDO: {}", requestDO);
    return Mono.just(requestDO)
        .flatMap(requestDO1 -> {
          ResponseDO.Builder builder = ResponseDO.builder()
              .input1(requestDO.getInput1())
              .input2(requestDO.getInput2());
          try {
            if (StringUtils.isBlank(requestDO.getInput1())) {
              throw new RuntimeException("input_1 is null or empty.");
            }

            if (StringUtils.isBlank(requestDO.getInput2())) {
              throw new RuntimeException("input_2 is null or empty.");
            }

            if (!NumberUtils.isCreatable(requestDO.getInput1())) {
              throw new RuntimeException(
                  String.format("input_1 is not numeric: %s.", requestDO.getInput1()));
            }

            if (!NumberUtils.isCreatable(requestDO.getInput2())) {
              throw new RuntimeException(
                  String.format("input_2 is not numeric: %s.", requestDO.getInput2()));
            }

            final Float f1 = Float.parseFloat(requestDO.getInput1());
            final Float f2 = Float.parseFloat(requestDO.getInput2());

            if (f1 < f2) {
              LOGGER.debug("Answer: {}", requestDO.getInput1());
              builder = builder.answer(
                  String.format("%s is before %s", requestDO1.getInput1(), requestDO1.getInput2()));
            } else if (f1 > f2) {
              LOGGER.debug("Answer: {}", requestDO.getInput1());
              builder = builder.answer(
                  String.format("%s is after %s", requestDO1.getInput1(), requestDO1.getInput2()));
            } else {
              throw new RuntimeException(String
                  .format("%s is equal to %s", requestDO1.getInput1(), requestDO1.getInput2()));
            }
          } catch (Exception e) {
            builder = builder.error(e.getMessage());
          }
          return Mono.just(builder.build());
        });
  }
}
