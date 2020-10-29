package org.jugistanbul.stereotype.crawl;

import org.apache.commons.lang3.RandomStringUtils;

public class Generic implements Crawler
{
    @Override
    public String content() {
        return RandomStringUtils.randomAlphabetic(10);
    }
}
