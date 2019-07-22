package com.checkpoint.network.bootstrap;

import com.checkpoint.network.dal.NetworkDao;
import com.checkpoint.network.domain.AuthType;
import com.checkpoint.network.domain.Device;
import com.checkpoint.network.domain.NetworkItem;
import com.checkpoint.network.repositories.DeviceRepository;
import com.checkpoint.network.repositories.NetworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapDataInjector implements CommandLineRunner {

    @Autowired
    NetworkRepository networkRepository;

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    NetworkDao networkDao;

    @Override
    public void run(String... args) {

        NetworkItem ni1 = new NetworkItem();
        ni1.id = 123;
        ni1.auth = AuthType.Public;
        //networkDao.SaveNetwork(ni1);

        for (int i = 0; i < 10; i++) {
            Device dev = new Device();
            dev.id = "dev" + i;
            dev.throughput = i * 10;
            networkDao.SaveNewDevice(dev);
            networkDao.AddDeviceToNetwork(ni1, dev);
        }

    }
}
