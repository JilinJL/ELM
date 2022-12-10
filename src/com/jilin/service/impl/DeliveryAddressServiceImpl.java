package com.jilin.service.impl;

import com.jilin.dao.DeliveryAddressDao;
import com.jilin.dao.impl.DeliveryAddressDaoImpl;
import com.jilin.entity.DeliveryAddress;
import com.jilin.service.DeliveryAddressService;

import java.util.List;

public class DeliveryAddressServiceImpl implements DeliveryAddressService {

    @Override
    public int addDeliveryAddress(DeliveryAddress DA) {
        DeliveryAddressDao dao = new DeliveryAddressDaoImpl();
        return dao.addDeliveryAddress(DA);
    }

    @Override
    public int updateDeliveryAddress(DeliveryAddress DA) {
        DeliveryAddressDao dao = new DeliveryAddressDaoImpl();
        return dao.updateDeliveryAddress(DA);
    }

    @Override
    public int removeDeliveryAddress(DeliveryAddress DA) {
        DeliveryAddressDao dao = new DeliveryAddressDaoImpl();
        return dao.removeDeliveryAddress(DA);
    }

    @Override
    public DeliveryAddress getDeliveryAddressById(String daId) {
        DeliveryAddressDao dao = new DeliveryAddressDaoImpl();
        return dao.getDeliveryAddressById(daId);

    }

    @Override
    public List<DeliveryAddress> listDeliveryAddressByUserId(String userId) {
        DeliveryAddressDao dao = new DeliveryAddressDaoImpl();
        return dao.listDeliveryAddressByUserId(userId);

    }
}
