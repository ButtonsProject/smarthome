package com.buttons.smarthome.services.deviceControl;

import com.buttons.smarthome.models.Apartment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class DeviceController {


    public boolean deviceOn(String deviceName, String controlAddress, String token) throws IOException, InterruptedException {

        var client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();

        var request = HttpRequest.newBuilder()
                .uri(URI.create(controlAddress + deviceName))
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "text/plain")
                .POST(HttpRequest.BodyPublishers.ofString("ON"))
                .build();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response);
        return true;
    }

    public boolean updateDevices(long apartmentId) {
        return true;
    }
}
