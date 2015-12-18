/*
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.index.mapper.attachment.test.unit;

import org.elasticsearch.Version;
import org.elasticsearch.cluster.metadata.IndexMetaData;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.mapper.attachment.AttachmentMapper;
import org.elasticsearch.indices.IndicesModule;
import org.elasticsearch.test.ESTestCase;
import org.junit.Before;
import org.junit.BeforeClass;

import static org.elasticsearch.index.mapper.attachment.test.MapperTestUtils.assumeCorrectLocale;

public class AttachmentUnitTestCase extends ESTestCase {
    /**
     * We can have issues with some JVMs and Locale
     * See https://github.com/elasticsearch/elasticsearch-mapper-attachments/issues/105
     */
    @BeforeClass
    public static void checkLocale() {
        assumeCorrectLocale();
    }
    
    protected Settings testSettings;

    public static IndicesModule getIndicesModuleWithRegisteredAttachmentMapper() {
        IndicesModule indicesModule = new IndicesModule();
        indicesModule.registerMapper(AttachmentMapper.CONTENT_TYPE, new AttachmentMapper.TypeParser());
        return indicesModule;
    }

    @Before
    public void createSettings() throws Exception {
      testSettings = Settings.builder()
                             .put("path.home", createTempDir())
                             .put(IndexMetaData.SETTING_VERSION_CREATED, Version.CURRENT.id)
                             .build();
    }
}