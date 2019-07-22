package com.checkpoint.network.controllers;

import com.checkpoint.network.domain.DeviceConnectionRequest;
import com.checkpoint.network.domain.DeviceReportArgs;
import com.checkpoint.network.domain.NetworkItem;
import com.checkpoint.network.domain.NetworkResponseType;
import com.checkpoint.network.domain.exceptions.IncompatibleAuthenticationTypeException;
import com.checkpoint.network.domain.exceptions.UnavilableDeviceException;
import com.checkpoint.network.domain.exceptions.UnavilableNetworkException;
import com.checkpoint.network.services.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(NetworkController.BASE_URL)
public class NetworkController {
    public static final String BASE_URL = "/my-service/api/network";

    @Autowired
    NetworkService networkService;


    @RequestMapping(value = "/connect", method = RequestMethod.PUT)
    @ExceptionHandler(IncompatibleAuthenticationTypeException.class)
    public void Connect(@RequestBody DeviceConnectionRequest request) {
        networkService.ConnectDevice(request);
    }


    @RequestMapping(value = "/report", method = RequestMethod.POST)
    public NetworkResponseType Report(@RequestBody DeviceReportArgs deviceReportArgs) {
        NetworkResponseType networkReportResponse;
        try {
            networkService.ReportNetworkThroughput(deviceReportArgs);
            networkReportResponse = NetworkResponseType.ok;
        } catch (UnavilableDeviceException ex) {
            System.out.println("device reporting failed because of unavailable device");
            networkReportResponse = NetworkResponseType.error;
        } catch (UnavilableNetworkException ex) {
            System.out.println("device reporting failed because of unavailable network");
            networkReportResponse = NetworkResponseType.error;
        } catch (Exception ex) {
            System.out.println("device reporting failed.");
            networkReportResponse = NetworkResponseType.error;
        }
        return networkReportResponse;
    }


    @GetMapping
    public NetworkItem Fetch(@RequestParam(value = "id") Integer networkId) {
        NetworkItem wifi = networkService.GetNetwork(networkId);
        return wifi;
    }

}
