package com.fjbproductions.scrapert;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.net.URL;

public class FeedReader {

    public String feedURL;

    public String getCurrent() {

        String result = "nothing!";

        try {

            try (XmlReader reader = new XmlReader(new URL(feedURL))) {

                SyndFeed feed = new SyndFeedInput().build(reader);

                result = "Dit is 'm echt!: ";
                for (SyndEntry entry : feed.getEntries()) {

                    result += entry.getTitle() + " ** ";
                }

                System.out.println("Done");
            }

        }  catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
