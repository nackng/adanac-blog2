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

import com.adanac.module.blog.dao.CityDao;
import com.adanac.module.blog.dao.DictionaryDao;
import com.adanac.module.blog.dao.ProvinceDao;
import com.adanac.module.blog.dao.UserDao;
import com.adanac.module.blog.mvc.DataMap;
import com.adanac.module.blog.mvc.Namespace;
import com.adanac.module.blog.orm.DaoFactory;
import com.adanac.module.blog.servlet.AbstractServlet;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author adanac
 * @since 2015年6月16日 上午1:35:04
 */
@Namespace("common")
public class UserCenter implements DataMap {

	@Override
	public void putCustomData(Map<String, Object> data, HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> user = AbstractServlet.getUser(request);
		if (user == null) {
			throw new RuntimeException();
		}
		if (StringUtils.isNotBlank(user.get("province"))) {
			Integer provinceId = DaoFactory.getDao(ProvinceDao.class).getId(user.get("province"));
			data.put("cities", DaoFactory.getDao(CityDao.class).getCities(provinceId));
		}
		data.put("provinces", DaoFactory.getDao(ProvinceDao.class).getProvinces());
		data.put("languages", DaoFactory.getDao(DictionaryDao.class).getDictionariesByType("LANG"));
	}

}
