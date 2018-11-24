/*
 * Copyright 2018 Heiko Scherrer
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
package org.openwms.common.comm.locu;

import org.openwms.common.comm.Payload;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

import static org.openwms.common.comm.ParserUtils.asDate;

/**
 * A LocationUpdateMessage.
 *
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 */
public class LocationUpdateMessage extends Payload implements Serializable {

    /** Message identifier {@value} . */
    public static final String IDENTIFIER = "LOCU";
    private String type;
    private String location;
    private String locationGroupName;

    private LocationUpdateMessage(Builder builder) {
        setErrorCode(builder.errorCode);
        setCreated(builder.created);
        type = builder.type;
        location = builder.location;
        locationGroupName = builder.locationGroupName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessageIdentifier() {
        return IDENTIFIER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWithoutReply() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String asString() {
        return null;
    }


    public static final class Builder {
        private String errorCode;
        private Date created;
        private String type;
        private String location;
        private String locationGroupName;

        public Builder() {
        }

        public Builder withErrorCode(String val) {
            errorCode = val;
            return this;
        }

        public Builder withCreated(String val) throws ParseException {
            created = asDate(val);
            return this;
        }

        public Builder withType(String val) {
            type = val;
            return this;
        }

        public Builder withLocation(String val) {
            location = val;
            return this;
        }

        public Builder withLocationGroupName(String val) {
            locationGroupName = val;
            return this;
        }

        public LocationUpdateMessage build() {
            return new LocationUpdateMessage(this);
        }
    }
}
