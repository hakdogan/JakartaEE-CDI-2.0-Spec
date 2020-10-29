package org.jugistanbul.stereotype.crawl;

import org.apache.commons.lang3.RandomStringUtils;
import org.jugistanbul.stereotype.annotation.SpecialCrawler;

@SpecialCrawler
public class Special implements Crawler
{
    @Override
    public String content() {
        return RandomStringUtils.random(255);
    }
}
