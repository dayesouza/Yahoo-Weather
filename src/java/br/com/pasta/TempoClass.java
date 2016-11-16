/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pasta;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;

/**
 *
 * @author dayennesouza
 */
public class TempoClass {

    private TempoBean meuBean;

    public TempoClass(TempoBean tempoBean) {
        this.meuBean = tempoBean;
    }
    private final String USER_AGENT = "Mozilla/5.0";
    private String cor_temp;

    public TempoBean buscar() throws MalformedURLException {

        if (meuBean.getLocal() != null) {
            try {
                String url_sem_formatacao = "https://query.yahooapis.com/v1/public/yql?q=select item.condition, yweather:condition, yweather:units, title from weather.forecast where woeid in (select woeid from geo.places(1) where text='" + meuBean.getLocal() + "')and u='" + meuBean.getUnit() + "'&format=json";
                //Coloca o codigo na maneira que o browser entende, sem espaços
                String url = url_sem_formatacao.replaceAll(" ", "%20");

                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                // optional default is GET
                con.setRequestMethod("GET");
                con.setDoOutput(true);
                //add request header
                con.setRequestProperty("User-Agent", USER_AGENT);

                int responseCode = con.getResponseCode();
                System.out.println("\nSending 'GET' request to URL : " + url);
                System.out.println("Response Code : " + responseCode);

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                    JSONObject my_obj = new JSONObject(inputLine);
//                    for (int i = 0; i < my_obj.length(); i++) {
                    Object query = my_obj.get("query");
                    String obj1 = query.toString();
                    JSONObject obj1A = new JSONObject(obj1);
//
                    Object data = obj1A.get("created");

                    Object results = obj1A.get("results");
                    String obj2 = results.toString();
                    JSONObject obj2A = new JSONObject(obj2);

                    Object channel = obj2A.get("channel");

                    String obj3 = channel.toString();
                    JSONObject obj3A = new JSONObject(obj3);

                    Object title = obj3A.get("title");

                    Object item = obj3A.get("item");

                    String obj4 = item.toString();
                    JSONObject obj4A = new JSONObject(obj4);

                    Object condition = obj4A.get("condition");

                    String obj5 = condition.toString();
                    JSONObject obj5A = new JSONObject(obj5);

                    Object temp = obj5A.get("temp");
                    Object date = obj5A.get("date");
                    Object code = obj5A.get("code");
                    Object text = obj5A.get("text");
                    //ARRUMAR AQ OS INT DE NOVO
                    String tempo = temp.toString();
                    int tempa = Integer.parseInt(tempo);

                    if (tempa < 0 && !"F".equals(meuBean.getUnit())) {
                        cor_temp = "blue";
                    } else if (tempa < 32 & !"C".equals(meuBean.getUnit())) {
                        cor_temp = "blue";

                    } else {
                        cor_temp = "red";

                    }

                    meuBean.setData(date.toString());
                    meuBean.setTemperatura(temp.toString());
                    meuBean.setCod_situacao(code.toString());
                    meuBean.setTexto_situacao(text.toString());
                    meuBean.setCor_temp(cor_temp);
                    System.out.println(meuBean.getUnit());
                }
//                System.out.println(response.toString());
                in.close();
                //print result

            } catch (Exception e) {

            }
        }

        return meuBean;
    }
}
