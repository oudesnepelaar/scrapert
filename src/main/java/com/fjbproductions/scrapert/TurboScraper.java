package com.fjbproductions.scrapert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Vector;

public class TurboScraper {

    private String scrapeURL;
    private String[] cuttingPoints;

    private final UTF8Filter filter = new UTF8Filter();

    public String[] scrape() {

        int numResults = (cuttingPoints.length / 2);
        Vector<String> result = new Vector<>();

        String source = load();
        source = filter.filter(source);

        for (int i = 0; i < numResults; i++) {

            String cut1 = cuttingPoints[2 * i];
            String cut2 = cuttingPoints[(2 * i) + 1];

            int index1 = source.indexOf(cut1) + cut1.length();
            int index2 = source.indexOf(cut2, index1);

            String snip = source.substring(index1, index2);
            result.add(snip);
        }

        return result.toArray(new String[result.size()]);
    }

    private String load() {

        String line;
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new URL(scrapeURL).openStream()))) {

            while ((line = br.readLine()) != null) sb.append(line);
            return sb.toString();

        } catch (Exception e){ e.printStackTrace();
            return "";
        }
    }

    public String getScrapeURL() {
        return scrapeURL;
    }
    public void setScrapeURL(String scrapeURL) {
        this.scrapeURL = scrapeURL;
    }
    public String[] getCuttingPoints() {
        return cuttingPoints;
    }
    public void setCuttingPoints(String[] cuttingPoints) {
        this.cuttingPoints = cuttingPoints;
    }
}
