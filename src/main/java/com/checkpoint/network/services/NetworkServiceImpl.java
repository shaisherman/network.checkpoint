package com.checkpoint.network.services;

import com.checkpoint.network.component.NetworkComponent;
import com.checkpoint.network.domain.Device;
import com.checkpoint.network.domain.DeviceConnectionRequest;
import com.checkpoint.network.domain.DeviceReportArgs;
import com.checkpoint.network.domain.NetworkItem;
import com.checkpoint.network.domain.exceptions.IncompatibleAuthenticationTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NetworkServiceImpl implements NetworkService {

    @Autowired
    NetworkComponent networkComponent;

    @Override
    public NetworkItem GetNetwork(Integer id) {
        return networkComponent.GetNetwork(id);
    }

    @Override
    public Device ConnectDevice(DeviceConnectionRequest request) throws IncompatibleAuthenticationTypeException {
        Device newDevice = networkComponent.AddDevice(request.network_id, request.device_id, request.auth);
        return newDevice;
    }

    @Override
    public void ReportNetworkThroughput(DeviceReportArgs arg) {

        networkComponent.ReportNetworkThroughput(arg.network_id, arg.device_id, arg.throughput);
    }

}