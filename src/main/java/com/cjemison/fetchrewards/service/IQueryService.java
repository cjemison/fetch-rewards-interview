package com.cjemison.fetchrewards.service;

import com.cjemison.fetchrewards.domain.RequestDO;
import com.cjemison.fetchrewards.domain.ResponseDO;
import reactor.core.publisher.Mono;

/**
 * <p>This is a contract handle requests.</p>
 */
public interface IQueryService {

  Mono<ResponseDO> query(final RequestDO requestDO);
}
