package com.adanac.module.blog.freemarker;

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

import com.adanac.module.blog.dao.AnswerDao;
import com.adanac.module.blog.dao.QuestionDao;
import com.adanac.module.blog.model.ViewMode;
import com.adanac.module.blog.orm.DaoFactory;
import com.adanac.module.blog.search.LuceneHelper;
import com.adanac.module.blog.servlet.AbstractServlet;
import com.adanac.module.blog.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author adanac
 * @since 2015年5月31日 下午5:07:46
 */
public abstract class QuestionListHelper {

	public static void putDataMap(String searchText, int current, Map<String, Object> data, ViewMode viewMode) {
        Map<String, Integer> pager = new HashMap<>();
        if (searchText == null || searchText.trim().length() == 0) {
            int total = DaoFactory.getDao(QuestionDao.class).getTotal();
            int page = (total % 10 == 0) ? (total / 10) : (total / 10 + 1);
            pager.put("current", current);
            pager.put("total", total);
            pager.put("page", page);
            data.put("pager", pager);
            data.put("questions", DaoFactory.getDao(QuestionDao.class).getQuestions(pager, viewMode));
            if (ViewMode.DYNAMIC == viewMode) {
                data.put("firstPageUrl", generateDynamicPath(1));
                data.put("prePageUrl", generateDynamicPath(current - 1));
                data.put("nextPageUrl", generateDynamicPath(current + 1));
                data.put("lastPageUrl", generateDynamicPath(page));
            } else {
                data.put("firstPageUrl", generateStaticPath(1));
                data.put("prePageUrl", generateStaticPath(current - 1));
                data.put("nextPageUrl", generateStaticPath(current + 1));
                data.put("lastPageUrl", generateStaticPath(page));
            }
        } else {
            searchText = StringUtil.urlDecode(searchText);
            List<Map<String, String>> questions = LuceneHelper.searchQuestion(searchText);
            int total = questions.size();
            int page = (total % 10 == 0) ? (total / 10) : (total / 10 + 1);
            pager.put("current", current);
            pager.put("total", total);
            pager.put("page", page);
            data.put("pager", pager);
            data.put("questions", questions);
            data.put("searchText", searchText);
            data.put("firstPageUrl", generateDynamicSearchTextPath(searchText, 1));
            data.put("prePageUrl", generateDynamicSearchTextPath(searchText, current - 1));
            data.put("nextPageUrl", generateDynamicSearchTextPath(searchText, current + 1));
            data.put("lastPageUrl", generateDynamicSearchTextPath(searchText, page));
        }
	}

    public static String generateStaticPath(int current) {
        return "/html/question_list_"+ current +".html";
    }

    public static String generateDynamicPath(int current) {
        return "/question/question_list.ftl?current=" + current;
    }

    public static String generateDynamicPath(String staticPath) {
        String name = staticPath.substring(staticPath.lastIndexOf("/") + 1 , staticPath.lastIndexOf("."));
        return generateDynamicPath(Integer.valueOf(name.split("_")[2]));
    }

    public static String generateDynamicSearchTextPath(String searchText, int current) {
        return "/question/question_list.ftl?searchText=" + StringUtil.urlEncode(searchText) + "&current=" + current;
    }

}
