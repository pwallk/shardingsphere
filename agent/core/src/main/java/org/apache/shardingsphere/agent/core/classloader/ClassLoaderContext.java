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

package org.apache.shardingsphere.agent.core.classloader;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.shardingsphere.agent.core.plugin.classloader.AgentPluginClassLoader;
import org.apache.shardingsphere.agent.core.plugin.jar.PluginJar;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class loader context.
 */
@RequiredArgsConstructor
public final class ClassLoaderContext {
    
    private static final Map<ClassLoader, AgentExtraClassLoader> AGENT_CLASS_LOADERS = new ConcurrentHashMap<>();
    
    @Getter
    private final ClassLoader appClassLoader;
    
    private final Collection<PluginJar> pluginJars;
    
    /**
     * Get plugin class loader.
     *
     * @return plugin class loader
     */
    public AgentExtraClassLoader getPluginClassLoader() {
        return AGENT_CLASS_LOADERS.computeIfAbsent(appClassLoader, key -> new AgentPluginClassLoader(key, pluginJars));
    }
}
