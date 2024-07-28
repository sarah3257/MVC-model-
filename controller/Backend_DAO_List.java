package controller;
import model.*;

import java.io.*;
import java.util.*;

public class Backend_DAO_List implements Backend  {

    private Map<Long, Customer> _Customers;
    private Set<Product> _Products;
    private List<PurchaseOrder> _PurchaseOrders;
    private  static  Backend_DAO_List bdl_singleton;

    public static Backend_DAO_List get_bdl_singleton(){
            if(bdl_singleton==null)
            bdl_singleton=new Backend_DAO_List();
            return  bdl_singleton;


    }

    public Backend_DAO_List() {
        Customer c1=new Customer(1,"tamar","bne brak");
        Customer c2=new Customer(2,"sarah","bne brak");





        _Customers = new HashMap< >();
        _Customers.put(c1.getId(),c1);
        _Customers.put(c2.getId(),c2);

        _Products = new HashSet< >();
        _Products.add(new HardwareProduct(1,"Product1","a",10,1));
        _Products.add(new SoftwareProduct(2,"Product2","b",5,1));
        //  _Products.add(product2);
        _PurchaseOrders = new ArrayList< >();
    }


    /**
     * מוססיף לקוח לאוסף
     * @param c
     * @throws Exception
     */
    @Override
    public void AddCustomer(Customer c) throws Exception {
      _Customers.put(c.getId(),c);
    }

    /**
     * מוסיף מוצר לאוסף
     * @param c
     * @throws Exception
     */
    @Override
    public void AddProduct(Product c) throws Exception {
        _Products.add(c);

    }

    /**
     *
     * @return  מחזיר- את כל רשימת הלקוחות
     * @throws Exception
     */

    @Override
    public HashMap<Long, Customer> getAllCustomers() throws Exception {
        return (HashMap<Long, Customer>) _Customers;
    }

    /**
     *
     * @return  מחזיר את רשימת כל המוצרחם
     * @throws Exception
     */
    @Override
    public HashSet<Product> getAllProducts() throws Exception {
    return (HashSet<Product>) _Products;
    }

    /**
     * מוסיף את ההזמנה לאוסף
     * @param po
     * @throws Exception
     */
    @Override
    public void PlaceOrder(PurchaseOrder po) throws Exception {
        _PurchaseOrders.add(po);
    }

    /**
     * מוחק את המוצר
     * @param c
     * @throws Exception
     */
    @Override
    public void RemoveProduct(Product c) throws Exception {
_Products.remove(c);
    }

    /**
     *
     * @param c
     * @return מחזיר- את כל מוצרי הלקוח המבוקש
     * @throws Exception
     */
    @Override
    public ArrayList<Product> getCustomersOrders(Customer c) throws Exception {
        return _PurchaseOrders.stream().filter(i -> i.getOrderingCustomer().getId()==c.getId()).map(i -> i.getProductsList()).collect(() -> new ArrayList<Product>(), (summer, i) -> summer.addAll(i), (i, j) -> i.add(j.get(0)));

    }

    /**
     *
     * @param products
     * @return מחזיר- סכום כל מחירי המוצרים במערך המתקבל
     * @throws Exception
     */
    @Override
    public Float CalcProductsTotalCost(Product[] products) throws Exception {
        float sum=0;
        for (int i=0; i<products.length;i++)
            sum+=products[i].getPrice();
        return sum;
    }
    public  void savaDataToFilel(){

        ObjectOutputStream oos= null;
        try {
         oos = new ObjectOutputStream(new FileOutputStream("data"));
        oos.writeObject(_Customers);
        oos.writeObject(_Products);
        oos.writeObject(_PurchaseOrders);
        oos.close();
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    }
    public  void loadDataFromFile(){
        ObjectInputStream ois= null;
        try {
            ois = new ObjectInputStream(new FileInputStream("data"));
            try {
                _Customers=(HashMap<Long,Customer>) ois.readObject();
            _Products =(Set<Product>) ois.readObject();
            _PurchaseOrders  =(List<PurchaseOrder>) ois.readObject();
            ois.close();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }




        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
