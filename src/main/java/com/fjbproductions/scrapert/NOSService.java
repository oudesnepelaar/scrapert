package com.fjbproductions.scrapert;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.springframework.stereotype.Service;

import java.net.URL;

@Service
public class NOSService {

    private static String feedURL = "http://feeds.nos.nl/nosnieuwsalgemeen";

    public String getCurrent() {

        String result = "nothing!";

        try {

            try (XmlReader reader = new XmlReader(new URL(feedURL))) {

                SyndFeed feed = new SyndFeedInput().build(reader);
                System.out.println(feed.getTitle());
                System.out.println("***********************************");

                result = "";
                for (SyndEntry entry : feed.getEntries()) {

                    result += entry.getTitle() + " ** ";

                    System.out.println(entry);
                    System.out.println("***********************************");
                }

                System.out.println("Done");
            }

        }  catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
