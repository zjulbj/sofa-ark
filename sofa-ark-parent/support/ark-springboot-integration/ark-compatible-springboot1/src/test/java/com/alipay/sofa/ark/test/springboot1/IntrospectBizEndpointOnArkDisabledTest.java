/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alipay.sofa.ark.test.springboot1;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;
import java.net.URL;

/**
 * @author qilong.zql
 * @since 0.6.0
 */
public class IntrospectBizEndpointOnArkDisabledTest {

    @After
    public void removeTomcatInit() {
        try {
            Field urlFactory = URL.class.getDeclaredField("factory");
            urlFactory.setAccessible(true);
            urlFactory.set(null, null);
        } catch (Throwable t) {
            // ignore
        }
    }

    @Test
    public void testIntrospectBizEndpoint() {
        SpringApplication springApplication = new SpringApplication(EmptyConfiguration.class);
        ConfigurableApplicationContext applicationContext = springApplication.run(new String[] {});
        Assert.assertFalse(applicationContext.containsBean("introspectBizEndpoint"));
        Assert.assertFalse(applicationContext.containsBean("introspectBizEndpointMvcAdapter"));
        applicationContext.close();
    }

    @Configuration
    @EnableAutoConfiguration
    static class EmptyConfiguration {
    }
}