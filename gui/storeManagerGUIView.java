package gui;

import controller.Backend_DAO_List;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class storeManagerGUIView {
    private JButton btnAddNewCustomer;
    private JButton btnProducts;
    private JButton btnActOrder;
    private JButton btnShowOrder;
     JPanel panel;

    public storeManagerGUIView(){
        btnAddNewCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              new  AddNewCustomerForm().setVisible(true);
            }
        });
btnProducts.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = new JFrame("ManageCatalogForm");
        frame.setContentPane(new ManageCatalogForm().Panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
});
        btnActOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("PlaceOrderForm");
                frame.setContentPane(new PlaceOrderForm().panel);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
        btnShowOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("ViewPurchasesForm");
                frame.setContentPane(new ViewPurchasesForm().panel);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("storeManagerGUIView");
        frame.setContentPane(new storeManagerGUIView().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
              Backend_DAO_List.get_bdl_singleton().savaDataToFilel();
            }

            @Override
            public void windowClosing(WindowEvent e) {
                Backend_DAO_List.get_bdl_singleton().loadDataFromFile();
            }
        });
    }

}
