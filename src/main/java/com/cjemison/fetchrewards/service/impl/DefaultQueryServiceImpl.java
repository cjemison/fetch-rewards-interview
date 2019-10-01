package com.cjemison.fetchrewards.service.impl;

import com.cjemison.fetchrewards.domain.RequestDO;
import com.cjemison.fetchrewards.domain.ResponseDO;
import com.cjemison.fetchrewards.service.IQueryService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DefaultQueryServiceImpl implements IQueryService {

  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultQueryServiceImpl.class);
  private static final String INPUT_1_ERROR_COUNT = "com.cjemison.fetchrewards.service.impl.count.error.input1";
  private static final String INPUT_2_ERROR_COUNT = "com.cjemison.fetchrewards.service.impl.count.error.input2";

  private static final String INPUT_1_ERROR_NOT_NUMERIC_COUNT = "com.cjemison.fetchrewards.service.impl.count.error.notnumeric.input1";
  private static final String INPUT_2_ERROR_NOT_NUMERIC_COUNT = "com.cjemison.fetchrewards.service.impl.count.error.notnumeric.input2";

  private static final String ERROR_COUNT = "com.cjemison.fetchrewards.service.impl.count.error";

  private final Counter INPUT_1_ERROR_COUNTER;
  private final Counter INPUT_2_ERROR_COUNTER;

  private final Counter INPUT_1_ERROR_NON_NUMERIC_COUNTER;
  private final Counter INPUT_2_ERROR_NON_NUMERIC_COUNTER;

  private final Counter ERROR_COUNTER;


  public DefaultQueryServiceImpl(final MeterRegistry meterRegistry) {
    INPUT_1_ERROR_COUNTER = Counter.builder(INPUT_1_ERROR_COUNT).register(meterRegistry);
    INPUT_2_ERROR_COUNTER = Counter.builder(INPUT_2_ERROR_COUNT).register(meterRegistry);

    INPUT_1_ERROR_NON_NUMERIC_COUNTER = Counter.builder(INPUT_1_ERROR_NOT_NUMERIC_COUNT)
        .register(meterRegistry);
    INPUT_2_ERROR_NON_NUMERIC_COUNTER = Counter.builder(INPUT_2_ERROR_NOT_NUMERIC_COUNT)
        .register(meterRegistry);

    ERROR_COUNTER = Counter.builder(ERROR_COUNT).register(meterRegistry);
  }

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
              INPUT_1_ERROR_COUNTER.increment();
              throw new RuntimeException("input_1 is null or empty.");
            }

            if (StringUtils.isBlank(requestDO.getInput2())) {
              INPUT_2_ERROR_COUNTER.increment();
              throw new RuntimeException("input_2 is null or empty.");
            }

            if (!NumberUtils.isCreatable(requestDO.getInput1())) {
              INPUT_1_ERROR_NON_NUMERIC_COUNTER.increment();
              throw new RuntimeException(
                  String.format("input_1 is not numeric: %s.", requestDO.getInput1()));
            }

            if (!NumberUtils.isCreatable(requestDO.getInput2())) {
              INPUT_2_ERROR_NON_NUMERIC_COUNTER.increment();
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
            LOGGER.error("Query Error", e);
            ERROR_COUNTER.increment();
            builder = builder.error(e.getMessage());
          }
          return Mono.just(builder.build());
        });
  }
}
