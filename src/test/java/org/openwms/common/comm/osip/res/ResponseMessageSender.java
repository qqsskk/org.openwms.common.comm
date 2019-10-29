/*
 * Copyright 2019 Heiko Scherrer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openwms.common.comm.osip.res;

import org.openwms.common.comm.config.Driver;
import org.openwms.common.comm.spi.FieldLengthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * A ResponseMessageSender.
 *
 * @author Heiko Scherrer
 */
@Component
public class ResponseMessageSender {

    @Autowired
    private AmqpResponseMessageListener listener;
    @Autowired
    private Driver driver;
    @Autowired
    private FieldLengthProvider provider;

    public void send(String resMessage, Map<String, Object> headers) {
        ResponseTelegramDeserializer deserializer = new ResponseTelegramDeserializer(driver);
        deserializer.setProvider(provider);
        listener.handle(deserializer.deserialize(resMessage, headers).getPayload(), headers);
    }
}