package com.buttons.smarthome;

import com.buttons.smarthome.deviceControl.DeviceController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SmarthomeApplication {

    public static void main(String[] args) throws IOException, InterruptedException {

        var context = SpringApplication.run(SmarthomeApplication.class, args);
//        var deviceController = context.getBean(DeviceController.class);
//        deviceController.deviceOn("MagicLightOnOff_Switch", "http://192.168.88.233:8080/rest/items/", "oh.buttonsCloud.GC8CdX5jtKI1JkVkQ1ofJpp0P6nHMxi3eUTvuzNr37qTs6vS5rJVP0sLdVQByLpAIGaSVltRARm1nrkQ");

    }
}