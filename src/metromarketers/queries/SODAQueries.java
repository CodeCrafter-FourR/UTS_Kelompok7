package metromarketers.queries;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Constraint;
import com.db4o.query.Query;
import metromarketers.db.DatabaseManager;
import metromarketers.model.*;

public class SODAQueries {

    public static void runQueries() {
        ObjectContainer db = DatabaseManager.getDB();
        try {
            System.out.println("\n============================================");
            System.out.println("          SODA QUERIES - 6 QUERIES");
            System.out.println("============================================\n");

            System.out.println("-- SODA-1: SELECT..WHERE age = 28 --");
            Query q1 = db.query();
            q1.constrain(IndividualCustomer.class);
            q1.descend("age").constrain(28);
            ObjectSet<IndividualCustomer> res1 = q1.execute();
            while (res1.hasNext()) {
                IndividualCustomer c = res1.next();
                System.out.println("  > " + c.getName() + " (Umur: " + c.getAge() + ")");
            }

            System.out.println("\n-- SODA-2: SELECT..WHERE NOT productName = 'Mouse Wireless' --");
            Query q2 = db.query();
            q2.constrain(Product.class);
            q2.descend("productName").constrain("Mouse Wireless").not();
            ObjectSet<Product> res2 = q2.execute();
            while (res2.hasNext()) {
                Product p = res2.next();
                System.out.println("  > " + p.getProductName() + " [" + p.getCategory() + "]");
            }

            System.out.println("\n-- SODA-3: SELECT..WITH AND (Credit Card & > 15.000.000) --");
            Query q3 = db.query();
            q3.constrain(Transaction.class);
            Constraint c1 = q3.descend("paymentMethod").constrain("Credit Card");
            q3.descend("totalAmount").constrain(15000000.0).greater().and(c1);
            ObjectSet<Transaction> res3 = q3.execute();
            while (res3.hasNext()) {
                Transaction t = res3.next();
                System.out.println("  > " + t.getTransactionId() + " | Rp" + t.getTotalAmount() + " | " + t.getPaymentMethod());
            }

            System.out.println("\n-- SODA-4: SELECT..WITH OR (Age < 25 OR Occupation = 'Manager') --");
            Query q4 = db.query();
            q4.constrain(IndividualCustomer.class);
            Constraint cA = q4.descend("age").constrain(25).smaller();
            q4.descend("occupation").constrain("Manager").or(cA);
            ObjectSet<IndividualCustomer> res4 = q4.execute();
            while (res4.hasNext()) {
                IndividualCustomer c = res4.next();
                System.out.println("  > " + c.getName() + " | Umur: " + c.getAge() + " | Kerja: " + c.getOccupation());
            }

            System.out.println("\n-- SODA-5: SORTING ASCENDING (Product by Stock) --");
            Query q5 = db.query();
            q5.constrain(Product.class);
            q5.descend("stock").orderAscending();
            ObjectSet<Product> res5 = q5.execute();
            while (res5.hasNext()) {
                Product p = res5.next();
                System.out.println("  > " + p.getProductName() + " - Stok: " + p.getStock());
            }

            System.out.println("\n-- SODA-6: SORTING DESCENDING (Transaction by Amount) --");
            Query q6 = db.query();
            q6.constrain(Transaction.class);
            q6.descend("totalAmount").orderDescending();
            ObjectSet<Transaction> res6 = q6.execute();
            while (res6.hasNext()) {
                Transaction t = res6.next();
                System.out.println("  > Trx ID: " + t.getTransactionId() + " | Rp" + t.getTotalAmount());
            }

        } finally {
            DatabaseManager.close();
        }
    }
}