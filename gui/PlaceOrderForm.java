package gui;

import controller.Backend;
import controller.Backend_DAO_List;
import model.Customer;
import model.Product;
import model.PurchaseOrder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import gui.storeManagerGUIView;
public class PlaceOrderForm {
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton addToOrderButton;
    private JButton submitOrderButton;
    private JButton calculateTotalButton;
    private JButton removeSelectedProductsButton;
    private JList list1;
    private JTextField textField1;
     JPanel panel;


    DefaultListModel SelectedProductsListModel = new DefaultListModel();
    DefaultComboBoxModel CustomerModel;
    DefaultComboBoxModel ProductModel;

    public  PlaceOrderForm(){
        try {

        CustomerModel=new DefaultComboBoxModel(Backend_DAO_List.get_bdl_singleton().getAllCustomers().values().toArray(new Customer[0]));
            ProductModel=new DefaultComboBoxModel(Backend_DAO_List.get_bdl_singleton().getAllProducts().toArray(new Product[0]));
list1.setModel(SelectedProductsListModel);
            comboBox1.setModel(CustomerModel);
            comboBox2.setModel(ProductModel);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    addToOrderButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            SelectedProductsListModel.addElement((Product)comboBox2.getSelectedItem());
        }
    });
        removeSelectedProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Object o:list1.getSelectedValues()){
                    SelectedProductsListModel.removeElement(o);
                }
            }
        });
        submitOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PurchaseOrder order=  new PurchaseOrder((Customer)(comboBox1.getSelectedItem()));
                    order.setProductsList(new ArrayList(Arrays.asList(SelectedProductsListModel.toArray())));
                    Backend_DAO_List.get_bdl_singleton().PlaceOrder(order);
JOptionPane.showMessageDialog(panel,"נוסף בהצלחה");
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error Placing order", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }


            }
        });
        calculateTotalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Product[]products=new  Product[SelectedProductsListModel.size()];
                    SelectedProductsListModel.copyInto(products);
                    Float total=Backend_DAO_List.get_bdl_singleton().CalcProductsTotalCost(products);
                    textField1.setText(total.toString());
                } catch (Exception ex) {
                    Logger.getLogger(PlaceOrderForm.class.getName()).log(Level.SEVERE,null,ex);

                }
            }
        });


}

}
