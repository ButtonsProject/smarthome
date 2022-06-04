package com.buttons.smarthome;

import com.buttons.smarthome.deviceControl.DeviceController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SmarthomeApplication {

    public static void main(String[] args) throws IOException, InterruptedException {

        var context = SpringApplication.run(SmarthomeApplication.class, args);
        //var deviceController = context.getBean(DeviceController.class);
        //deviceController.deviceOn("MagicLightOnOff_Switch", "http://192.168.50.131:8080/rest/items/", "oh.switch.R3caaLQFsf3nlkbkIIvwP1KdpxUbmRhoSLXCDmk6wEtpYk0rzcFN9uaDQsngAvwZawn0XBvGk14LvOhgaLMg");

    }
}