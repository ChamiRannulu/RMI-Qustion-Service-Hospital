/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class API {

    private String targetURL = "https://random.justyy.workers.dev/api/random/?cached&n=10";
    private HttpURLConnection connection = null;
    private String password = "";

    public API() {
        apiCall();
    }

    public void apiCall() {

        try {
            //Create connection
            URL url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla 5.0 (Windows; U; "
                    + "Windows NT 5.1; en-US; rv:1.8.0.11) ");
            connection.setRequestMethod("GET");

            //Get Response  
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            this.password = response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            this.password = "";
        }
    }

    public String getPassword() {
        return this.password;
    }

}
