package io.github.nilankamanoj.util;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RuleLoader {
    private String url;

    public RuleLoader(String url) {
        this.url = url;

    }

    public JSONObject load() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(sendGet(this.url));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    private String sendGet(String url) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        // add request header
        con.setRequestProperty("User-Agent", "Mozilla/4.76");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

}
