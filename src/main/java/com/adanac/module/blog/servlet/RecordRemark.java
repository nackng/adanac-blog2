package com.adanac.module.blog.servlet;

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

import com.adanac.module.blog.dao.*;
import com.adanac.module.blog.generator.Generators;
import com.adanac.module.blog.orm.DaoFactory;
import com.adanac.module.blog.util.HttpUtil;
import com.adanac.module.blog.util.JsoupUtil;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @author adanac
 * @since 6/19/2015 6:18 PM
 */
public class RecordRemark extends AbstractServlet {

    @Override
    protected void service() throws ServletException, IOException {
        Integer recordId = Integer.valueOf(getRequest().getParameter("recordId"));
        String ip = HttpUtil.getVisitorIp(getRequest());
        String username = getUsername();
        if (DaoFactory.getDao(RecordIdVisitorIpDao.class).exists(recordId, ip, username)) {
            writeText("exists");
            if (logger.isInfoEnabled()) {
                logger.info(ip + " has remarked...");
            }
            return ;
        } else {
            DaoFactory.getDao(RecordIdVisitorIpDao.class).save(recordId, ip, username);
        }
        boolean result = DaoFactory.getDao(RecordDao.class).updateGoodTimes(recordId);
        if (!result) {
            logger.error("updateCount error!");
            return;
        }
        if (result && logger.isInfoEnabled()) {
            logger.info("updateCount success!");
        }
        Generators.generateRecord(recordId);
        writeText("success");
    }

}
