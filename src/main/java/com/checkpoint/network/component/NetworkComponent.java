package com.checkpoint.network.component;

import com.checkpoint.network.dal.NetworkDao;
import com.checkpoint.network.domain.AuthType;
import com.checkpoint.network.domain.Device;
import com.checkpoint.network.domain.NetworkItem;
import com.checkpoint.network.domain.exceptions.IncompatibleAuthenticationTypeException;
import com.checkpoint.network.domain.exceptions.UnavilableDeviceException;
import com.checkpoint.network.domain.exceptions.UnavilableNetworkException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class NetworkComponent {

    @Autowired
    NetworkDao networkDao;
    private HashMap<Integer, NetworkItem> networks;

    @EventListener(ApplicationReadyEvent.class)
    public void Init() {
        networks = new HashMap<>();
        for (NetworkItem rep : networkDao.GetAllNetworks()) {

            networks.put(rep.id, rep);
        }
    }

    public Device AddDevice(Integer networkId, String deviceId, AuthType authType) {
        NetworkItem network = CreateOrGetNetwork(networkId, authType);
        Device device;
        if (network.devices.containsKey(deviceId) == false) {
            device = new Device();
            device.id = deviceId;
            networkDao.SaveNewDevice(device);
            networkDao.AddDeviceToNetwork(network, device);
        } else {
            device = network.devices.get(deviceId);
        }

        return device;
    }

    public NetworkItem GetNetwork(Integer id) {
        if (networks.containsKey(id)) {
            return networks.get(id);
        } else {
            return null;
        }
    }

    public NetworkItem CreateOrGetNetwork(Integer id, AuthType authType) throws IncompatibleAuthenticationTypeException {
        NetworkItem network;

        if (networks.containsKey(id)) {
            network = networks.get(id);
            if (network.auth != authType) {
                throw new IncompatibleAuthenticationTypeException("Not a valid authentication type for this network.");
            }
        } else {
            network = new NetworkItem();
            network.id = id;
            network.auth = authType;
            networks.put(network.id, network);
        }
        return network;
    }

    public void ReportNetworkThroughput(Integer networkId, String deviceId, float throughput) {

        if (networks.containsKey(networkId)) {
            NetworkItem wifi = networks.get(networkId);
            if (wifi.devices.containsKey(deviceId)) {
                wifi.UpdateDeviceThroughput(deviceId, throughput);
            } else {
                throw new UnavilableDeviceException("Invalid device id", networkId, deviceId);
            }
        } else {
            throw new UnavilableNetworkException("Invalid network id", networkId);
        }
    }

}
