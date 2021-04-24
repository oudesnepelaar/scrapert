package com.fjbproductions.scrapert;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Vector;

public class FeedReader {

    private String feedURL;
    private final UTF8Filter filter = new UTF8Filter();

    public String getCurrent() {

        return top(Integer.MAX_VALUE);
    }

    public String top (int amount) {

        List<SyndEntry> result = new Vector<>();

        try {

            try (XmlReader reader = new XmlReader(new URL(feedURL))) {

                SyndFeed feed = new SyndFeedInput().build(reader);
                List<SyndEntry> entries = feed.getEntries();

                int minimum = Integer.min(amount, entries.size());

                for (int i = 0; i < minimum; i++) {

                    result.add(entries.get(i));
                }
            }

        }  catch (Exception e) {
            e.printStackTrace();
        }

        return squish(result);
    }

    private String squish (List<SyndEntry> entries) {

        String result = "";
        for (SyndEntry entry: entries) result += entry.getTitle() + " ** ";
        result = filter.filter(result);

        return result;
    }

    public String getFeedURL() {
        return feedURL;
    }
    public void setFeedURL(String feedURL) {
        this.feedURL = feedURL;
    }
}
