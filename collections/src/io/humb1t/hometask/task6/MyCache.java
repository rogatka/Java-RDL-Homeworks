package io.humb1t.hometask.task6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

public class MyCache {
    private static Cache cache = new Cache();

    public static void main(String[] args) {
        System.out.println(getContent("https://www.habr.com/ru/"));
        System.out.println(getContent("https://habr.com/ru/company/jugru/blog/490250/"));
        System.out.println(getContent("https://habr.com/ru/post/488144/"));
        System.out.println(getContent("https://habr.com/ru/post/488144/"));
        System.out.println(getContent("https://www.habr.com/en/"));
        System.out.println(getContent("https://www.habr.com/ru/"));
    }


    public static String getContent(String url) {
        String content = cache.get(url);
        if (content == null) {
            content = getContentFromUrl(url);
            cache.put(url, content);
        } else {
            System.out.println("Getting content from the cache:");
        }
        return content;
    }

    private static String getContentFromUrl(String link) {
        StringBuilder content = new StringBuilder();
        try {
            URL url = new URL(link);
            InputStream inputStream = url.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.append("\r\n").toString();
    }


    private static class Cache extends LinkedHashMap<String, String> {
        private final static int MAX_SIZE = 3;

        @Override
        protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
            return this.size() > MAX_SIZE;
        }
    }
}