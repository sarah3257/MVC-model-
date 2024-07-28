package gui;

import controller.Backend_DAO_List;
import model.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;

public class ManageCatalogForm {
    private JButton btndeletion;
    private JButton btnAdd;
    private JList list1;
    DefaultListModel<Product> AllProductsListModel;
    JPanel Panel;
    private JTextField textID;

    public ManageCatalogForm(){
        AllProductsListModel=new DefaultListModel<>();
     list1.setModel(AllProductsListModel);
    RefreshProductList();

    btnAdd.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame frame = new JFrame("AddNewProductForm");
            frame.setContentPane(new AddNewProductForm(ManageCatalogForm.this).panel);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        }
    });
        btndeletion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Product> selectedValuesList = list1.getSelectedValuesList();
                for (Product p :selectedValuesList) {
                    AllProductsListModel.removeElement(p);
                    try {
                        Backend_DAO_List.get_bdl_singleton().RemoveProduct(p);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

}
    public void RefreshProductList() {
    AllProductsListModel.clear();
        try {
            AllProductsListModel.addAll(Backend_DAO_List.get_bdl_singleton().getAllProducts());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("ManageCatalogForm");
        frame.setContentPane(new ManageCatalogForm().Panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
