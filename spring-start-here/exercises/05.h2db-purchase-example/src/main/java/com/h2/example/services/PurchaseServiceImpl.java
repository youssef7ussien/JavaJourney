package com.h2.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.h2.example.exceptions.RepositoryException;
import com.h2.example.exceptions.ServiceException;
import com.h2.example.models.Purchase;
import com.h2.example.repositories.PurchaseRepository;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public void savePurchase(Purchase purchase) throws ServiceException {
        try {
            purchaseRepository.save(purchase);
        } catch (RepositoryException e) {
            throw new ServiceException("Failed to save purchase", e);

        }
    }

    @Override
    public void deletePurchaseById(Long id) throws ServiceException {
        try {
            purchaseRepository.deleteById(id);
        } catch (RepositoryException e) {
            throw new ServiceException("Failed to delete purchase with id: " + id, e);

        }
    }

    @Override
    public Purchase findPurchaseById(Long id) throws ServiceException {
        try {
            return purchaseRepository.findById(id);
        } catch (RepositoryException e) {
            throw new ServiceException("Failed to find purchase with id: " + id, e);
        }
    }

    @Override
    public List<Purchase> findAllPurchases() throws ServiceException {
        try {
            return purchaseRepository.findAll();
        } catch (RepositoryException e) {
            throw new ServiceException("Failed to retrieve purchases", e);
        }
    }

}
