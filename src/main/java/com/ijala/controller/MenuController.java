package com.ijala.controller;

import com.ijala.view.stock.StockManageFrame;
import com.ijala.view.product.TableProductsFrame;
import com.ijala.view.finance.FinanceManageFrame;
import com.ijala.view.MenuFrame;

import java.awt.event.ActionEvent;

public class MenuController {
    protected MenuFrame menuFrame;

    public MenuController(MenuFrame menuFrame) {
        this.menuFrame = menuFrame;
    }

    public void showStockManageFrame(ActionEvent e) {
        StockManageFrame stock = new StockManageFrame();
        stock.setVisible(true);
    }

    public void showTableProductsFrame(ActionEvent e) {
        TableProductsFrame tableProducts = new TableProductsFrame();
        tableProducts.setVisible(true);
    }

    public void showFinanceManageFrame(ActionEvent e) {
        FinanceManageFrame financeManage = new FinanceManageFrame();
        financeManage.setVisible(true);
    }
}
