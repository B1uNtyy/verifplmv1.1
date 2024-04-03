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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Collections;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Request {

    private String json;
    private String idTete;
    private List<Map<String, Object>> components;
    private boolean received;
    private boolean proceed;
    private List<String> RegleOff;
    private List<String> RegleParent;

    public Request() {
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getidTete() {
        return idTete;
    }

    public void setidTete(String idTete) {
        this.idTete = idTete;
    }

    public boolean getReceived() {
        return received;
    }

    public List<Map<String, Object>> getComponents() {
        return components;
    }

    public void setComponents(List<Map<String, Object>> components) {
        this.components = components;
    }

    public boolean getProceed() {
        return proceed;
    }

    public void setReceived(boolean received) {
        this.received = received;
    }

    public void setProceed(boolean proceed) {
        this.proceed = proceed;
    }

    public void setRegleOff(List<String> RegleOff) {
        this.RegleOff = RegleOff;
    }

    public void setRegleParent(List<String> RegleParent) {
        this.RegleParent = RegleParent;
    }

    public List<String> getRegleParent() {
        return RegleParent;
    }

    public void addRegleOff(String element) {
        RegleOff.add(element);
    }

    public List<String> getRegleOff() {
        return RegleOff;
    }

    public void MapJson(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(json);
    
        // Initialize the list to hold components
        List<Map<String, Object>> components = new ArrayList<>();
    
        // Process the root node to identify the main component "Système"
        Map<String, Object> systemComponent = new HashMap<>();
        processComponent(rootNode, systemComponent);
    
        // Check if the main component has a "components" field at the first level of nesting
        if (systemComponent.containsKey("components")) {
            // If it does, move the components up one level and add them to the main component
            List<Map<String, Object>> systemComponents = (List<Map<String, Object>>) systemComponent.get("components");
            systemComponent.remove("components");
            components.addAll(systemComponents);
        }
    
        // If the system component is not empty, add it to the list of components
        if (!systemComponent.isEmpty()) {
            components.add(systemComponent);
        }
    
        // If there are components, set the list of components in the MyClass object
        if (!components.isEmpty()) {
            this.components = components;
        }
    }
    
    private void processComponent(JsonNode node, Map<String, Object> componentMap) {
        // Extract the properties of the current node
        Iterator<String> fieldNames = node.fieldNames();
        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            JsonNode fieldValue = node.get(fieldName);
            if (fieldValue.isArray()) {
                // If the field value is an array, recursively process each element
                List<Map<String, Object>> nestedComponents = new ArrayList<>();
                for (JsonNode arrayItem : fieldValue) {
                    Map<String, Object> nestedComponent = new HashMap<>();
                    processComponent(arrayItem, nestedComponent);
                    nestedComponents.add(nestedComponent);
                }
                // Add the array of components under the main component map
                componentMap.put(fieldName, nestedComponents);
            } else if (fieldValue.isObject()) {
                // If the field value is an object, recursively process it
                Map<String, Object> nestedComponent = new HashMap<>();
                processComponent(fieldValue, nestedComponent);
                // Add the nested component map under the main component map
                componentMap.put(fieldName, nestedComponent);
            } else {
                // Add other fields directly to the component map
                componentMap.put(fieldName, fieldValue.asText());
            }
        }
    }
    
    
    private void processSystemComponent(JsonNode node, Map<String, Object> componentMap) {
        // Extract the properties of the current node
        Iterator<String> fieldNames = node.fieldNames();
        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            JsonNode fieldValue = node.get(fieldName);
            if (fieldValue.isArray()) {
                // If the field value is an array, recursively process each element
                List<Map<String, Object>> nestedComponents = new ArrayList<>();
                for (JsonNode arrayItem : fieldValue) {
                    Map<String, Object> nestedComponent = new HashMap<>();
                    processComponent(arrayItem, nestedComponent);
                    nestedComponents.add(nestedComponent);
                }
                // Add the components array under the main component map
                componentMap.put("components", nestedComponents);
            } else if (fieldValue.isObject()) {
                // If the field value is an object, recursively process it
                Map<String, Object> nestedComponent = new HashMap<>();
                processComponent(fieldValue, nestedComponent);
                // Add the nested component map under the main component map
                componentMap.put(fieldName, nestedComponent);
            } else {
                // Add other fields directly to the component map
                componentMap.put(fieldName, fieldValue.asText());
            }
        }
    }
    
    
    
    
    
    
    
    
}
