package com.adanac.module.blog.generator;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

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

import com.adanac.module.blog.config.Configuration;
import com.adanac.module.blog.dao.RecordDao;
import com.adanac.module.blog.freemarker.FreemarkerHelper;
import com.adanac.module.blog.freemarker.RecordHelper;
import com.adanac.module.blog.orm.DaoFactory;

/**
 * @author adanac
 * @since 15/6/29 21:34
 */
public class RecordGenerator implements Generator {

	@Override
	public int order() {
		return 3;
	}

	@Override
	public void generate() {
		List<Map<String, String>> records = DaoFactory.getDao(RecordDao.class).getAll(VIEW_MODE);
		for (int i = 0; i < records.size(); i++) {
			generateRecord(Integer.valueOf(records.get(i).get("id")));
		}
	}

	void generateRecord(Integer id) {
		Writer writer = null;
		try {
			Map<String, Object> data = FreemarkerHelper.buildCommonDataMap("record", VIEW_MODE);
			RecordHelper.putDataMap(data, VIEW_MODE, id);
			String htmlPath = Configuration.getContextPath(RecordHelper.generateStaticPath(id));
			writer = new FileWriter(htmlPath);
			FreemarkerHelper.generate("WEB-INF/view/record", "record", writer, data);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

}
