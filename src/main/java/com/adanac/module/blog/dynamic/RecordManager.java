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

import com.adanac.module.blog.dao.ArticleDao;
import com.adanac.module.blog.dao.RecordDao;
import com.adanac.module.blog.model.Status;
import com.adanac.module.blog.model.ViewMode;
import com.adanac.module.blog.mvc.DataMap;
import com.adanac.module.blog.mvc.Namespace;
import com.adanac.module.blog.orm.DaoFactory;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author adanac
 * @since 2015年5月27日 上午2:13:35
 */
@Namespace("admin")
public class RecordManager implements DataMap {

	@Override
	public void putCustomData(Map<String, Object> data, HttpServletRequest request, HttpServletResponse response) {
		String currentString = request.getParameter("current");
		int current = 1;
		if (StringUtils.isNotBlank(currentString)) {
			current = Integer.valueOf(currentString);
		}
		int total = DaoFactory.getDao(RecordDao.class).getTotal();
		int page = (total % 10 == 0) ? (total / 10) : (total / 10 + 1);
		Map<String, Integer> pager = new HashMap<String, Integer>();
		pager.put("current", current);
		pager.put("total", total);
		pager.put("page", page);
		data.put("pageRecords", DaoFactory.getDao(RecordDao.class).getRecords(pager, ViewMode.DYNAMIC));
		data.put("pager", pager);
		data.put("firstArticleListUrl", "/admin/article_manager.ftl?current=1");
		data.put("preArticleListUrl", "/admin/article_manager.ftl?current=" + (current - 1));
		data.put("nextArticleListUrl", "/admin/article_manager.ftl?current=" + (current + 1));
		data.put("lastArticleListUrl", "/admin/article_manager.ftl?current=" + (page));
	}
	
}
