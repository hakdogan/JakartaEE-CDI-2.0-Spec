package org.jugistanbul.stereotype.crawl;

import org.jugistanbul.stereotype.annotation.Mock;

@Mock
public class Pseudo implements Crawler
{
    @Override
    public String content() {
        return "pseudo content";
    }
}
