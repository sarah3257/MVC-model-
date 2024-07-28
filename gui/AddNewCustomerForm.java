package gui;

import model.Customer;
import controller.Backend_DAO_List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddNewCustomerForm extends JFrame {
    private JButton jButtonOK;
    private JLabel  jLabelID;
    private JLabel  jLabelName;
    private JLabel  jLabelAddress;
    private JTextField    jTextFieldID;
    private JTextField   jTextFieldName;
    private JTextField   jTextFieldAddress;

public AddNewCustomerForm(){
    jButtonOK = new JButton("OK");
    jLabelID = new JLabel("ID:");
    jLabelName = new JLabel("Name:");
    jLabelAddress = new JLabel("Address:");
    jTextFieldID = new JTextField();
    jTextFieldName = new JTextField();
    jTextFieldAddress = new JTextField();

    getContentPane().add(jLabelID);



    getContentPane().add(jTextFieldID);getContentPane().add(jLabelName);
    getContentPane().add(jTextFieldName);getContentPane().add(jLabelAddress);
    getContentPane().add(jTextFieldAddress); getContentPane().add(jButtonOK);

this.setPreferredSize(new Dimension(400,200));
getContentPane().setLayout((new GridLayout(0,2,10,10)));
this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
this.pack();

jTextFieldID.addKeyListener(new KeyAdapter() {
    @Override
    public void keyTyped(KeyEvent e) {
        if(!Character.isDigit(e.getKeyChar()))
            e.consume();
    }


});
jButtonOK.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(jTextFieldName.getText().isBlank()){
                throw new Exception("שדה השם חובה");
            }
            if(jTextFieldAddress.getText().isBlank()){
                throw new Exception("שדה כתובות חובה");
            }
            Customer customer = new Customer();
            customer.setId(Long.parseLong(jTextFieldID.getText()));
            customer.setName(jTextFieldName.getText());
            customer.setAddress(jTextFieldAddress.getText());
            Backend_DAO_List.get_bdl_singleton().AddCustomer(customer);
            JOptionPane.showMessageDialog(AddNewCustomerForm.this,"הלקוח התווסף בהצלחה");

        }
        catch (Exception ex){
            JOptionPane.showMessageDialog(AddNewCustomerForm.this,ex.getMessage());

        }
    }
});
}
}
