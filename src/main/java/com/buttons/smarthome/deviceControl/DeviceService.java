package com.buttons.smarthome.deviceControl;

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

    HttpClient client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
                .build();

    public boolean sendToDevice(Apartment apartment, Device device, String command) throws IOException, InterruptedException {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(apartment.getControlAddress() + device.getName()))
                .header("Authorization", "Bearer " + apartment.getAuthToken())
                .header("Content-Type", "text/plain")
                .POST(HttpRequest.BodyPublishers.ofString(command))
                .build();

        client.send(request, HttpResponse.BodyHandlers.ofString());
        return true;
    }

    public List<Device> scanDevices(Apartment apartment) throws IOException, InterruptedException {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(apartment.getControlAddress() + "/items"))
                .header("Authorization", "Bearer " + apartment.getAuthToken())
                .GET()
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
                var typ = Type.Switch;
                result.add(new Device(name, state, typ));
            }
        }

        return result;
    }

}
