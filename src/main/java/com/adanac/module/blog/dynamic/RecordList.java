package com.adanac.module.blog.dynamic;

/*
 * Copyright 2002-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.adanac.module.blog.freemarker.QuestionListHelper;
import com.adanac.module.blog.freemarker.RecordHelper;
import com.adanac.module.blog.freemarker.RecordListHelper;
import com.adanac.module.blog.model.ViewMode;
import com.adanac.module.blog.mvc.DataMap;
import com.adanac.module.blog.mvc.Namespace;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author adanac
 * @since 15/6/21 00:56
 */
@Namespace("record")
public class RecordList implements DataMap {

    @Override
    public void putCustomData(Map<String, Object> data, HttpServletRequest request, HttpServletResponse response) {
        Integer current = 1;
        if (request.getParameter("current") != null) {
            current = Integer.valueOf(request.getParameter("current"));
        }
        RecordListHelper.putDataMap(current, data, ViewMode.DYNAMIC);
    }

}
