package metromarketers.queries;

import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;
import com.db4o.query.QueryComparator;
import metromarketers.db.DatabaseManager;
import metromarketers.model.*;
import java.util.List;

public class NativeQueries {

    public static void runQueries() {
        ObjectContainer db = DatabaseManager.getDB();

        try {
            System.out.println("\n============================================");
            System.out.println("         NATIVE QUERIES - 6 QUERIES");
            System.out.println("============================================\n");

            // NQ-1
            System.out.println("-- NQ-1: SELECT IndividualCustomer WHERE age >= 25 --");
            List<IndividualCustomer> adultCustomers =
                db.query(new Predicate<IndividualCustomer>() {
                    public boolean match(IndividualCustomer c) {
                        return c.getAge() >= 25;
                    }
                });

            for (IndividualCustomer c : adultCustomers) {
                System.out.println("  > " + c.getName() + " (" + c.getAge() + " tahun)");
            }

            // NQ-2
            System.out.println(
                "\n-- NQ-2: SELECT Transaction WHERE payment='Credit Card' AND amount > 500.000 --"
            );
            List<Transaction> bigCCTrx =
                db.query(new Predicate<Transaction>() {
                    public boolean match(Transaction t) {
                        return "Credit Card".equals(t.getPaymentMethod())
                                && t.getTotalAmount() > 500000;
                    }
                });

            for (Transaction t : bigCCTrx) {
                System.out.println(
                    "  > Trx ID: " + t.getTransactionId()
                    + " | Rp" + t.getTotalAmount()
                );
            }

            // NQ-3
            System.out.println(
                "\n-- NQ-3: SELECT Product WHERE price BETWEEN 50.000 AND 1.000.000 --"
            );
            List<Product> midRangeProducts =
                db.query(new Predicate<Product>() {
                    public boolean match(Product p) {
                        return p.getPrice() >= 50000.0
                                && p.getPrice() <= 1000000.0;
                    }
                });

            for (Product p : midRangeProducts) {
                System.out.println(
                    "  > " + p.getProductName()
                    + " - Rp" + p.getPrice()
                );
            }

            // NQ-4
            System.out.println("\n-- NQ-4: SELECT Product WHERE stock < 10 --");
            List<Product> lowStockProducts =
                db.query(new Predicate<Product>() {
                    public boolean match(Product p) {
                        return p.getStock() < 10;
                    }
                });

            for (Product p : lowStockProducts) {
                System.out.println(
                    "  > " + p.getProductName()
                    + " (Sisa: " + p.getStock() + ")"
                );
            }

            // NQ-5
            System.out.println("\n-- NQ-5: SORTING Product BY price DESC --");
            List<Product> sortedProducts =
                db.query(
                    new Predicate<Product>() {
                        public boolean match(Product p) {
                            return true;
                        }
                    },
                    new QueryComparator<Product>() {
                        public int compare(Product p1, Product p2) {
                            return Double.compare(
                                p2.getPrice(),
                                p1.getPrice()
                            );
                        }
                    }
                );

            for (Product p : sortedProducts) {
                System.out.println(
                    "  > " + p.getProductName()
                    + " - Rp" + p.getPrice()
                );
            }

            // NQ-6
            System.out.println(
                "\n-- NQ-6: SORTING Transaction BY totalAmount DESC --"
            );
            List<Transaction> sortedTrx =
                db.query(
                    new Predicate<Transaction>() {
                        public boolean match(Transaction t) {
                            return true;
                        }
                    },
                    new QueryComparator<Transaction>() {
                        public int compare(Transaction t1, Transaction t2) {
                            return Double.compare(
                                t2.getTotalAmount(),
                                t1.getTotalAmount()
                            );
                        }
                    }
                );

            for (Transaction t : sortedTrx) {
                System.out.println(
                    "  > Trx ID: " + t.getTransactionId()
                    + " | Rp" + t.getTotalAmount()
                );
            }

        } finally {
            DatabaseManager.close();
        }
    }
}