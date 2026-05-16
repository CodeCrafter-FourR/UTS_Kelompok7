package metromarketers.db;

import com.db4o.ObjectContainer;
import metromarketers.model.IndividualCustomer;
import metromarketers.model.Product;
import metromarketers.model.Transaction;

public class CreateInstances {

    public static void main(String[] args) {
        ObjectContainer db = DatabaseManager.getDB();

        try {

            IndividualCustomer c1 = new IndividualCustomer(
                "C01", "Budi Santoso", "Jl. Merdeka No. 1", "08123456", 
                "budi@gmail.com", 28, "Male", "Programmer"
            );

            IndividualCustomer c2 = new IndividualCustomer(
                "C02", "Siti Aminah", "Jl. Sudirman No. 10", "08987654", 
                "siti@gmail.com", 35, "Female", "Manager"
            );

            IndividualCustomer c3 = new IndividualCustomer(
                "C03", "Andi Wijaya", "Jl. Gatot Subroto No. 5", "08112233", 
                "andi@gmail.com", 22, "Male", "Student"
            );


            Product p1 = new Product("P01", "Laptop ThinkPad", "Electronics", 15000000, 5);
            Product p2 = new Product("P02", "Mouse Wireless", "Accessories", 150000, 50);
            Product p3 = new Product("P03", "Keyboard Mekanikal", "Accessories", 850000, 8);


            Transaction t1 = new Transaction("T01", 15150000, "Credit Card", c1);
            Transaction t2 = new Transaction("T02", 150000, "Cash", c2);
            Transaction t3 = new Transaction("T03", 850000, "Debit Card", c3);
            Transaction t4 = new Transaction("T04", 30000000, "Credit Card", c2);


            db.store(c1); db.store(c2); db.store(c3);
            db.store(p1); db.store(p2); db.store(p3);
            db.store(t1); db.store(t2); db.store(t3); db.store(t4);

            db.commit();
            System.out.println("Data  berhasil di-generate dan disimpan ke database db4o!");

        } finally {
            DatabaseManager.close();
        }
    }
}