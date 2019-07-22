package com.checkpoint.network.dal;

import com.checkpoint.network.domain.Device;
import com.checkpoint.network.domain.NetworkItem;

import java.util.List;

public interface NetworkDao {
    void SaveNewDevice(Device device);

    void SaveNetwork(NetworkItem networkItem);

    void AddDeviceToNetwork(NetworkItem networkItem, Device device);

    NetworkItem GetNetwork(Integer networkId);

    List<NetworkItem> GetAllNetworks();
}
