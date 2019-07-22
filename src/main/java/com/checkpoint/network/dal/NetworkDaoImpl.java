package com.checkpoint.network.dal;

import com.checkpoint.network.domain.Device;
import com.checkpoint.network.domain.NetworkItem;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Component
public class NetworkDaoImpl implements NetworkDao {

    @PersistenceContext
    private EntityManager em;

    public void SaveNewDevice(Device device) {
        em.persist(device);
    }

    public void SaveNetwork(NetworkItem networkItem) {
        em.persist(networkItem);
    }

    public void AddDeviceToNetwork(NetworkItem networkItem, Device device) {
        networkItem.AddDevice(device);
        NetworkItem item = em.merge(networkItem);
        em.persist(item);
    }

    public NetworkItem GetNetwork(Integer networkId) {
        NetworkItem networkItem = em.find(NetworkItem.class, networkId);
        return networkItem;
    }

    public List<NetworkItem> GetAllNetworks() {
        String query = new String("select n from NetworkItem n");
        List<NetworkItem> items = em.createQuery(query, NetworkItem.class).getResultList();
        return items;
    }

}
