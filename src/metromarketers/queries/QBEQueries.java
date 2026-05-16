package metromarketers.queries;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import metromarketers.db.DatabaseManager;
import metromarketers.model.*;

public class QBEQueries {

    public static void runQueries() {
        ObjectContainer db = DatabaseManager.getDB();
        try {
            System.out.println("\n============================================");
            System.out.println("          QBE QUERIES - 6 QUERIES");
            System.out.println("============================================\n");

            // QBE-1: SELECT - Mencari Customer bernama spesifik
            System.out.println("-- QBE-1: SELECT Customer bernama 'Budi Santoso' --");
            IndividualCustomer protoCustomer = new IndividualCustomer(null, "Budi Santoso", null, null, null, 0, null, null);
            ObjectSet<IndividualCustomer> result1 = db.queryByExample(protoCustomer);
            while (result1.hasNext()) {
                IndividualCustomer c = result1.next();
                System.out.println("  > Ditemukan: " + c.getName() + " | Alamat: " + c.getAddress());
            }

            // QBE-2: SELECT - Mencari Produk berdasarkan nama
            System.out.println("\n-- QBE-2: SELECT Product bernama 'Mouse Wireless' --");
            Product protoProduct = new Product(null, "Mouse Wireless", null, 0, 0);
            ObjectSet<Product> result2 = db.queryByExample(protoProduct);
            while (result2.hasNext()) {
                Product p = result2.next();
                System.out.println("  > Ditemukan: " + p.getProductName() + " | Kategori: " + p.getCategory() + " | Rp" + p.getPrice());
            }

            // QBE-3: SELECT - Mencari Transaksi menggunakan metode pembayaran Cash
            System.out.println("\n-- QBE-3: SELECT Transaction by Payment='Cash' --");
            Transaction protoTrx = new Transaction(null, 0, "Cash", null);
            ObjectSet<Transaction> result3 = db.queryByExample(protoTrx);
            while (result3.hasNext()) {
                Transaction t = result3.next();
                System.out.println("  > Trx ID: " + t.getTransactionId() + " | Rp" + t.getTotalAmount());
            }

            // QBE-4: UPDATE - Menambah stok produk (Laptop ThinkPad)
            System.out.println("\n-- QBE-4: UPDATE Product Stock --");
            Product protoUpdate = new Product(null, "Laptop ThinkPad", null, 0, 0);
            ObjectSet<Product> result4 = db.queryByExample(protoUpdate);
            if (result4.hasNext()) {
                Product pToUpdate = result4.next();
                System.out.println("  > Stok Lama: " + pToUpdate.getStock());
                pToUpdate.setStock(20);
                db.store(pToUpdate);
                System.out.println("  > Stok Baru: 20 (Berhasil di-update)");
            }

            // QBE-5: UPDATE - Mengubah harga produk (Keyboard Mekanikal)
            System.out.println("\n-- QBE-5: UPDATE Product Price --");
            Product protoUpdate2 = new Product(null, "Keyboard Mekanikal", null, 0, 0);
            ObjectSet<Product> result5 = db.queryByExample(protoUpdate2);
            if (result5.hasNext()) {
                Product pToUpdate2 = result5.next();
                pToUpdate2.setPrice(800000);
                db.store(pToUpdate2);
                System.out.println("  > Harga baru: Rp800000.0 (Berhasil di-update)");
            }

            // QBE-6: DELETE - Menghapus transaksi spesifik (T03)
            System.out.println("\n-- QBE-6: DELETE Transaction --");
            Transaction protoDelete = new Transaction("T03", 0, null, null);
            ObjectSet<Transaction> result6 = db.queryByExample(protoDelete);
            if (result6.hasNext()) {
                Transaction tToDelete = result6.next();
                db.delete(tToDelete);
                System.out.println("  > Transaksi T03 berhasil Dihapus dari database!");
            }

        } finally {
            DatabaseManager.close();
        }
    }
}