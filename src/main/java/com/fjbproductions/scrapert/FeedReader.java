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
    private boolean sourceIsUTF8;

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

        if (sourceIsUTF8) result = decodeUTF8(result);

        return result;
    }

    /**
     * In case a UTF-8 encoded String is loaded from a data source,
     * We want to convert this tot the simpler ISO_8859_1 which is what the hardware of the
     * news tickers often uses, in order to avoid missing characters in the hardware displays.
     * @param input a UTF-8 String to be re-encoded into ISO_8859_1
     * @return a ISO_8859_1 encoded String based on the provided input
     */
    private String decodeUTF8(String input) {

        byte[] bytes = input.getBytes(StandardCharsets.UTF_8);
        return new String(bytes, StandardCharsets.ISO_8859_1);
    }

    public boolean isSourceIsUTF8() { return sourceIsUTF8; }
    public void setSourceIsUTF8(boolean sourceIsUTF8) { this.sourceIsUTF8 = sourceIsUTF8; }

    public String getFeedURL() {
        return feedURL;
    }
    public void setFeedURL(String feedURL) {
        this.feedURL = feedURL;
    }
}
