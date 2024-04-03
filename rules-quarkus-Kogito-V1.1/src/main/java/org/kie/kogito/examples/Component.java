/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.kie.kogito.examples;

import java.util.List;
import java.util.Map;

public class Component {

    String PartName;
    String Number;
    String Version;
    String LogisticStatus;
    List<Map<String, Object>> Components;

    public Component() {

    }

    public String getPartName() {
        return PartName;
    }

    public void setPartName(String PartName) {
        this.PartName = PartName;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String Number) {
        this.Number = Number;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String Version) {
        this.Version = Version;
    }

    public String getLogisticStatus() {
        return LogisticStatus;
    }

    public void setLogisticStatus(String LogisticStatus) {
        this.LogisticStatus = LogisticStatus;
    }

    public List<Map<String, Object>> getComponents() {
        return Components;
    }

    public void setComponents(List<Map<String, Object>> components) {
        this.Components = components;
    }

}
