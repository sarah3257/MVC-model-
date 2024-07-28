package controller;
import model.Product;
import model.PurchaseOrder;
import model.Customer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public interface Backend {
    void AddCustomer(Customer c) throws  Exception;
    void AddProduct(Product c) throws  Exception;
    HashMap<Long, Customer> getAllCustomers()throws Exception;
    HashSet<Product> getAllProducts()throws  Exception;
    // הוספה של הזמנה למערך ההזמנות (בדומה להוספת לקוח ומוצר.)
    void PlaceOrder(PurchaseOrder po)throws  Exception;
    // מחיקה של מוצר ממערך המוצרים, (אין צורך בלולאה.)
    void RemoveProduct(Product c) throws  Exception;
    ArrayList<Product> getCustomersOrders(Customer c)throws  Exception;
    Float CalcProductsTotalCost(Product[] products)throws  Exception;


}
