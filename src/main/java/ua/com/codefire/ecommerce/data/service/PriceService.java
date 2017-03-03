package ua.com.codefire.ecommerce.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.codefire.ecommerce.data.entity.Currency;
import ua.com.codefire.ecommerce.data.entity.Price;
import ua.com.codefire.ecommerce.data.repo.CurrencyRepo;
import ua.com.codefire.ecommerce.data.repo.PriceRepo;

import java.util.List;

/**
 * Created by Katya on 03.02.2017.
 */
@Service
public class PriceService {

    @Autowired
    private PriceRepo priceRepo;
    @Autowired
    private CurrencyRepo currencyRepo;

    public Price getTopicalPrice(Integer id){
        return priceRepo.findTopicalPrice(id);
    }

    public List<Price> getAllPrices() {
        return priceRepo.findAll();
    }
    public List<Currency> getAllCurrencies() {
        return currencyRepo.findAll();
    }

    public void createPrice(Price price){
        priceRepo.add(price);
    }
    public void createCurrency(Currency currency){
        currencyRepo.add(currency);
    }

    public Currency getCurrency(Integer id){
        return currencyRepo.getById(id);
    }

    public void updatePrice(Price price){
        priceRepo.update(price);
    }
    public void updateCurrency(Currency currency){
        currencyRepo.update(currency);
    }

    public void deletePrice(Price price){
        priceRepo.delete(price);
    }
    public void deleteCurrency(Currency currency){
        currencyRepo.delete(currency);
    }

    public String getPriceValueById(int id) {
        return priceRepo.getValueById(id);
    }
    public String getCurrencyNameById(int id) {
        return currencyRepo.getNameById(id);
    }
}
