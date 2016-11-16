/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pasta;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;

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

    public TempoBean buscar() throws MalformedURLException {

        if (meuBean.getLocal() != null) {
           try{
               String localizacao[] = meuBean.getLocal().split(",");
               String unidade = meuBean.getUnit();
               
            String url = "https://query.yahooapis.com/v1/public/yql?q=select%20item.condition,%20yweather:units,%20title,%20yweather:condition%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22"+localizacao[0]+"%2C%20"+localizacao[1]+"%22)and%20u='"+unidade+"'&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
//            String url = URLEncoder.encode(encoded, "UTF-8");
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
                        meuBean.setResultado(inputLine);
		}
		in.close();
		//print result
		System.out.println(response.toString());
           }catch(Exception e){
               
           }
        }
     
        return meuBean;
    }
}
