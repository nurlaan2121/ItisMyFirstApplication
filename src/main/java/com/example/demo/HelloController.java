package com.example.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.Formatter;
import java.util.ResourceBundle;


import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.std.JsonValueSerializer;
import com.fasterxml.jackson.databind.util.JSONPObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.json.JSONObject;


public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label fifie;

    @FXML
    private Label four;

    @FXML
    private Label one;

    @FXML
    private Label sefen;

    @FXML
    private Label six;

    @FXML
    private Button tree;

    @FXML
    private TextField two;

    @FXML
    void btnClick(ActionEvent event) {

    }

    @FXML
    void initialize() {
        tree.setOnAction(actionEvent -> {
            String getUSerCity = two.getText().trim();
            if (!getUSerCity.isEmpty()) {
                String output = geturlCuntent("https://api.openweathermap.org/data/2.5/weather?q=" + getUSerCity + "&appid=d91c2589af32ec2147c770dbafe8681a");
                System.out.println(output);
                if (!output.isEmpty()) {
                    JSONObject object = new JSONObject(output);
                    double gradus = object.getJSONObject("main").getDouble("temp");
                    double res =gradus - 273.15;
                    long round = Math.round(res);

                    BigDecimal temperatureCelsius = BigDecimal.valueOf(res).setScale(2, BigDecimal.ROUND_HALF_UP);
                    four.setText("Градус:      " + temperatureCelsius + "℃");


                    fifie.setText("Шамал:        " + object.getJSONObject("wind").getInt("speed"));
                    six.setText("Олко:        " + object.getJSONObject("sys").getString("country"));
                    String s = ": " + object.getJSONArray("weather").getJSONObject(0).getString("description");
                    System.out.println(s);
                    if (s.equalsIgnoreCase(": smoke")) {
                        sefen.setText("Описание:         Tуман");
                    } else if (s.equalsIgnoreCase(": overcast clouds")) {
                        sefen.setText("Описание:        Туманырак");
                    } else if (s.equalsIgnoreCase(": scattered clouds")) {
                        sefen.setText("Описание:        Кун бурко");
                    } else if (s.equalsIgnoreCase(": ачык асман")) {
                        sefen.setText("Описание:       Ачык асман");
                    }

                }
            }
        });

    }

    private static String geturlCuntent(String urlAdress) {
        StringBuffer content = new StringBuffer();
        try {
            URL url = new URL(urlAdress);
            URLConnection urlConn = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("Mundai shaar jok");

        }
        return content.toString();
    }

}
