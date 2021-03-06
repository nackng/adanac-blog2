package com.adanac.module.blog.filter;

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

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.adanac.module.blog.dao.UserDao;
import com.adanac.module.blog.orm.DaoFactory;
import net.sf.json.JSONObject;

import com.adanac.module.blog.dao.AccessLogDao;
import com.adanac.module.blog.util.HttpUtil;

/**
 * @author adanac
 * @since 5/14/2015 11:52 AM
 */
public class AccessLogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            String visitorIp = HttpUtil.getVisitorIp(httpServletRequest);
            String url = httpServletRequest.getRequestURI();
            String params = httpServletRequest.getParameterMap() == null ? "" : JSONObject.fromObject(httpServletRequest.getParameterMap()).toString();
            DaoFactory.getDao(AccessLogDao.class).save(visitorIp, url, params);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }

}
