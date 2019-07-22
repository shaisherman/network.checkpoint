package com.checkpoint.network.repositories;

import com.checkpoint.network.domain.NetworkItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NetworkRepository extends JpaRepository<NetworkItem, Integer> {
}
