package com.ijala.controller;

import com.ijala.model.buy.BuyProductDAO;
import com.ijala.model.buy.BuyProduct;
import com.ijala.model.sell.SellProductDAO;
import com.ijala.model.sell.SellProduct;

public class MovementController {

    public void makeSale(SellProduct sellProduct) {
        SellProductDAO sellProductDAO = new SellProductDAO();
        sellProductDAO.makeSale(sellProduct);
    }

    public void makeBuy(BuyProduct buyProduct) {
        BuyProductDAO buyProductDAO = new BuyProductDAO();
        buyProductDAO.makeBuy(buyProduct);
    }
    
}
