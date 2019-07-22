package com.checkpoint.network.services;

import com.checkpoint.network.domain.Device;
import com.checkpoint.network.domain.DeviceConnectionRequest;
import com.checkpoint.network.domain.DeviceReportArgs;
import com.checkpoint.network.domain.NetworkItem;
import org.springframework.stereotype.Service;

@Service
public interface NetworkService {
    NetworkItem GetNetwork(Integer id);

    Device ConnectDevice(DeviceConnectionRequest request);

    void ReportNetworkThroughput(DeviceReportArgs arg);
}