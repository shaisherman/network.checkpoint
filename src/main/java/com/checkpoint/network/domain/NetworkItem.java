package com.checkpoint.network.domain;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class NetworkItem {

    @Id
    public Integer id;

    public AuthType auth;

    @ElementCollection
    @CollectionTable(name = "NetworkDevice")
    @MapKeyColumn(name = "id")
    @OneToMany(fetch = FetchType.EAGER)
    public Map<String, Device> devices = new HashMap<>();

    private float average = 0;

    public float getAvgThroughput() {
        return average;
    }

    public void AddDevice(Device device) {
        int numberOfDevices = devices.size();
        average = (average * (numberOfDevices) + device.throughput) / (numberOfDevices + 1);
        devices.put(device.id, device);
    }

    public void UpdateDeviceThroughput(String deviceId, float deviceNewThroughput) {
        Device currDevice = devices.get(deviceId);
        float preThroughput = currDevice.throughput;
        average = (average * devices.size() - preThroughput + deviceNewThroughput) / devices.size();
        currDevice.throughput = deviceNewThroughput;
    }
}
