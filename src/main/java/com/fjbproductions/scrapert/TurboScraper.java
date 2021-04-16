package com.fjbproductions.scrapert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Vector;

public class TurboScraper {

    private String scrapeURL;
    private String[] cuttingPoints;
    private boolean sourceIsUTF8;

    public String[] scrape() {

        int numResults = (cuttingPoints.length / 2);
        Vector<String> result = new Vector<>();

        String source = load();
        if (sourceIsUTF8) source = decodeUTF8(source);

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

        } catch (Exception e){

            e.printStackTrace();
            return "";
        }
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

    public boolean isSourceIsUTF8() {
        return sourceIsUTF8;
    }

    public void setSourceIsUTF8(boolean sourceIsUTF8) {
        this.sourceIsUTF8 = sourceIsUTF8;
    }
}
