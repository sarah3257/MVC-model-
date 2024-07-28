package gui;

import controller.Backend_DAO_List;
import model.Customer;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewPurchasesForm {
    private JComboBox comboBox1;
    private JTextField textField1;
    private JList list1;
     JPanel panel;
    DefaultComboBoxModel CustomerModel;
    DefaultListModel SelectedProductsListModel = new DefaultListModel();
    public ViewPurchasesForm(){
        try {
            CustomerModel=new DefaultComboBoxModel(Backend_DAO_List.get_bdl_singleton().getAllCustomers().values().toArray(new Customer[0]));

            comboBox1.setModel(CustomerModel) ;

            list1.setModel(SelectedProductsListModel);
            SelectedProductsListModel.addAll(Backend_DAO_List.get_bdl_singleton().getCustomersOrders((Customer) comboBox1.getSelectedItem()));
            Product[]products=new  Product[SelectedProductsListModel.size()];
            SelectedProductsListModel.copyInto(products);
            Float total=Backend_DAO_List.get_bdl_singleton().CalcProductsTotalCost(products);
            textField1.setText(total.toString());
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectedProductsListModel.clear();

                try {
                    SelectedProductsListModel.addAll(Backend_DAO_List.get_bdl_singleton().getCustomersOrders((Customer) comboBox1.getSelectedItem()));
                 Product[] products = new Product[SelectedProductsListModel.size()];
                    SelectedProductsListModel.copyInto(products);
                    Float total =Backend_DAO_List.get_bdl_singleton().CalcProductsTotalCost(products);
                    textField1.setText(total.toString());
                }
                 catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
   }
}
