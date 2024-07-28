package gui;

import javax.swing.*;

import controller.Backend_DAO_List;
import model.HardwareProduct;
import model.SoftwareProduct;
import model.type;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AddNewProductForm {
    private JTextField textID;
    private JLabel btnID;
    private JTextField textName;
    private JTextField textdescription;
    private JTextField textPrice;
    private JTextField textVariable;
    private JLabel btnName;
    private JLabel btndescription;
    private JLabel btnPrice;
    private JLabel btnVariable;
    private JButton btnAdd;
    private JComboBox comboBox1;
     JPanel panel;

    ManageCatalogForm manageCatalogForm;

public AddNewProductForm(ManageCatalogForm catalogForm){
    manageCatalogForm=catalogForm;
    DefaultComboBoxModel model = new DefaultComboBoxModel(type.values());
    comboBox1.setModel(model);
    comboBox1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(isInHardwareMode())
                btnVariable.setText("Warrenty Period");
            else
                btnVariable.setText("Number of Users");
        }
    });
    textPrice.addKeyListener(new KeyAdapter() {
      @Override
      public void keyTyped(KeyEvent e) {
        if(!Character.isDigit(e.getKeyChar()))
            e.consume(); }
      });
    textID.addKeyListener(new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            if(!Character.isDigit(e.getKeyChar()))
                e.consume(); }
    });
            btnAdd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    try {
                        if (textID.getText().isBlank())
                            throw new Exception("חובה לכתוב קוד");

                        if (textName.getText().isBlank())
                            throw new Exception("שדה שם חובה");
                        if (textdescription.getText().isBlank())
                            throw new Exception("שדה תיאור חובה");
                        if (textPrice.getText().isBlank())
                            throw new Exception("שדה מחיר חובה");
                        if (textVariable.getText().isBlank())
                            throw new Exception("שדה זה הינו חובה");


                        if (isInHardwareMode())
                            Backend_DAO_List.get_bdl_singleton().AddProduct(new HardwareProduct(Long.parseLong(textID.getText()), textName.getText(), textdescription.getText(), (Float.parseFloat(textPrice.getText())), (Integer.parseInt(textVariable.getText()))));

                        else
                            Backend_DAO_List.get_bdl_singleton().AddProduct(new SoftwareProduct(Long.parseLong(textID.getText()), textName.getText(), textdescription.getText(), (Float.parseFloat(textPrice.getText())), (Integer.parseInt(textVariable.getText()))));

                        manageCatalogForm.RefreshProductList();

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null,ex.getMessage());
                    }


                }
            });
}
public boolean isInHardwareMode(){
    return comboBox1.getSelectedItem().equals(type.hardware);
}
}
