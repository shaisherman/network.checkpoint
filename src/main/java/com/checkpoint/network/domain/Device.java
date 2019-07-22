package com.checkpoint.network.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "devices")
public class Device {

    @Id
    public String id;

    public float throughput;
}