package com.buttons.smarthome.services;

import com.buttons.smarthome.models.Apartment;
import com.buttons.smarthome.models.Device;
import com.buttons.smarthome.models.Type;
import org.json.JSONArray;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceService {

    public boolean sendToDevice(Apartment apartment, Device device, String command) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();


        System.out.println("http://" + apartment.getControlAddress() + "rest/items/" + device.getName());

        var request = HttpRequest.newBuilder()
                .uri(URI.create("http://" + apartment.getControlAddress() + "/rest/items/" + device.getName()))
                .header("Authorization", "Bearer " + apartment.getAuthToken())
                .header("Content-Type", "text/plain")
                .POST(HttpRequest.BodyPublishers.ofString(command))
                .build();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return true;
    }

    public List<Device> scanDevices(Apartment apartment) throws IOException, InterruptedException {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(apartment.getControlAddress() + "/items"))
                .header("Authorization", "Bearer " + apartment.getAuthToken())
                .GET()
                .build();

        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();

        var json = new JSONArray(response);

        var result = new ArrayList<Device>();

        for (var i = 0; i < json.length(); i++) {
            var jObject = json.getJSONObject(i);
            var name =  jObject.getString("name");
            var state = jObject.getString("state");
            var type = jObject.getString("type");
            if (type.equals("Switch")) {
                var typ = Type.Lamp;
                result.add(new Device(name, state, typ));
            }
            if (type.equals("Color")) {
                var typ = Type.LAMP_COLOR;
                result.add(new Device(name, state, typ));
            }
//            if (type.equals("Dimmable"))
        }

        return result;
    }

}
