package homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class MyCache {
    private static Cache cache = new Cache();

    public static void main(String[] args) {
        System.out.println(getContent(null));
        System.out.println(getContent("non_existing_url"));
        System.out.println(getContent("https://www.habr.com/ru/"));
        System.out.println(getContent("https://habr.com/ru/company/jugru/blog/490250/"));
        System.out.println(getContent("https://habr.com/ru/post/488144/"));
        System.out.println(getContent("https://habr.com/ru/post/488144/"));
        System.out.println(getContent("https://www.habr.com/en/"));
        System.out.println(getContent("https://www.habr.com/ru/"));
    }


    public static String getContent(String url) {
        return Optional.ofNullable(cache.get(url))
                .orElseGet(() -> {
                    Optional<String> stringOptional = getContentFromUrl(url);
                    stringOptional.ifPresent((v) -> cache.put(url, v));
                    return stringOptional.orElse("There is no content for that url");
                });
    }

    // It's not recommended using Optional as argument in method because then we'll need additional checks inside
    // this method because Optional can be empty or have a value.
    private static Optional<String> getContentFromUrl(String link) {
        StringBuilder content = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            URL url = new URL(link);
            inputStream = url.openStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            content.append("\r\n");
        } catch (MalformedURLException e) {
            return Optional.empty();
        } catch (IOException e) {
            return Optional.empty();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //DO NOTHING
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    //DO NOTHING
                }
            }
        }
        return Optional.of(content.toString());
    }


    private static class Cache extends LinkedHashMap<String, String> {
        private final static int MAX_SIZE = 3;

        @Override
        protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
            return this.size() > MAX_SIZE;
        }
    }
}