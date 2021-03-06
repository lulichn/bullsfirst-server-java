/**
 * Copyright 2012 Archfirst
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.archfirst.bfoms.restservice.order;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import org.archfirst.bfoms.domain.account.brokerage.BrokerageAccountService;
import org.archfirst.bfoms.domain.account.brokerage.order.OrderEstimate;
import org.archfirst.bfoms.domain.account.brokerage.order.OrderParams;

/**
 * OrderEstimateResource
 *
 * @author Naresh Bhatia
 */
@Stateless
@Path("/secure/order_estimates")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrderEstimateResource {

    @POST
    public OrderEstimate createOrderEstimate(
            @Context SecurityContext sc,
            CreateOrderEstimateRequest request) {
        
            return brokerageAccountService.getOrderEstimate(
                    getUsername(sc), request.getBrokerageAccountId(), request.getOrderParams());
    }

    private String getUsername(SecurityContext sc) {
        return sc.getUserPrincipal().getName();
    }

    // ----- Helper Classes -----
    private static class CreateOrderEstimateRequest {
        private Long brokerageAccountId;
        private OrderParams  orderParams;
        public Long getBrokerageAccountId() {
            return brokerageAccountId;
        }
        public void setBrokerageAccountId(Long brokerageAccountId) {
            this.brokerageAccountId = brokerageAccountId;
        }
        public OrderParams getOrderParams() {
            return orderParams;
        }
        public void setOrderParams(OrderParams orderParams) {
            this.orderParams = orderParams;
        }
    }

    // ----- Attributes -----
    @Inject
    private BrokerageAccountService brokerageAccountService;
}