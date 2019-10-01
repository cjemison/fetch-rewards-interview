package com.cjemison.fetchrewards.controller;

import com.cjemison.fetchrewards.domain.RequestDO;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

/**
 * <p>This interface acts as a contract for controllers</p>
 */
public interface IFetchController {

  /**
   * <p>This method provides a rest endpoint to request which value is smaller.</p>
   */
  Mono<ResponseEntity<?>> post(final RequestDO requestDO);
}
