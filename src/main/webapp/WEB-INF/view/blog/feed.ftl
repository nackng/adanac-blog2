<?xml version="1.0" encoding="UTF-8"?>
<rss version="2.0" xmlns:atom="http://www.w3.org/2005/Atom">
    <channel>
        <title>adanac个人博客</title>
        <atom:link href="http://www.adanac.module.blog.com/feed.xml" rel="self" type="application/rss+xml"/>
        <link>http://www.adanac.module.blog.com</link>
        <description>一起走在编程的路上</description>
        <lastBuildDate>${lastBuildDate}</lastBuildDate>
        <language>zh-CN</language>
        <#list articles as article>
            <#if article_index gt 9>
                <#break />
            </#if>
            <item>
                <title>${article.subject}</title>
                <link>${contextPath}${article.url}</link>
                <pubDate>${article.us_create_date}</pubDate>
                <description>${article.summary}...</description>
            </item>
        </#list>
    </channel>
</rss>
