package com.checkpoint.network.repositories;

import com.checkpoint.network.domain.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, String> {

}
