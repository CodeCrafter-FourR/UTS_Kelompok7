package metromarketersU;

import java.util.Scanner;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import metromarketers.db.DatabaseManager;
import metromarketers.model.IndividualCustomer;
import metromarketers.model.Product;
import metromarketers.queries.NativeQueries;
import metromarketers.queries.QBEQueries;
import metromarketers.queries.SODAQueries;

public class MainCLI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int pilihan = -1;

        while(pilihan != 0) {
            System.out.println("\n=========================================");
            System.out.println("   SYSTEM METRO MARKETERS DATABASE (CLI)");
            System.out.println("=========================================");
            System.out.println("1. Tambah Data Customer Baru (Individual)");
            System.out.println("2. Tambah Data Produk Baru");
            System.out.println("3. Tampilkan Semua Data Customer");
            System.out.println("4. Tampilkan Semua Data Produk");
            System.out.println("5. Hapus Data Customer (Berdasarkan ID)");
            System.out.println("6. Hapus Data Produk (Berdasarkan ID)");
            System.out.println("7. Jalankan Native Queries (6 Query)");
            System.out.println("8. Jalankan QBE Queries    (6 Query)");
            System.out.println("9. Jalankan SODA Queries   (6 Query)");
            System.out.println("0. Keluar Aplikasi");
            System.out.print("Pilih Menu: ");
            
            try {
                pilihan = Integer.parseInt(scanner.nextLine());
                
                if (pilihan == 1) {
                    ObjectContainer db = DatabaseManager.getDB();
                    System.out.println("\n--- INPUT DATA CUSTOMER BARU ---");
                    System.out.print("Masukkan ID Customer (contoh: C04): ");
                    String id = scanner.nextLine();
                    System.out.print("Masukkan Nama Lengkap: ");
                    String name = scanner.nextLine();
                    System.out.print("Masukkan Alamat: ");
                    String address = scanner.nextLine();
                    System.out.print("Masukkan No HP: ");
                    String phone = scanner.nextLine();
                    System.out.print("Masukkan Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Masukkan Umur: ");
                    int age = Integer.parseInt(scanner.nextLine());
                    System.out.print("Masukkan Gender (Male/Female): ");
                    String gender = scanner.nextLine();
                    System.out.print("Masukkan Pekerjaan: ");
                    String occupation = scanner.nextLine();

                    IndividualCustomer newCust = new IndividualCustomer(id, name, address, phone, email, age, gender, occupation);
                    db.store(newCust);
                    db.commit();
                    System.out.println("✓ Berhasil! Data Customer '" + name + "' disimpan ke db4o.");
                    DatabaseManager.close();

                } else if (pilihan == 2) {
                    ObjectContainer db = DatabaseManager.getDB();
                    System.out.println("\n--- INPUT DATA PRODUK BARU ---");
                    System.out.print("Masukkan ID Produk (contoh: P04): ");
                    String id = scanner.nextLine();
                    System.out.print("Masukkan Nama Produk: ");
                    String name = scanner.nextLine();
                    System.out.print("Masukkan Kategori Produk: ");
                    String category = scanner.nextLine();
                    System.out.print("Masukkan Harga (Rp): ");
                    double price = Double.parseDouble(scanner.nextLine());
                    System.out.print("Masukkan Jumlah Stok: ");
                    int stock = Integer.parseInt(scanner.nextLine());

                    Product newProd = new Product(id, name, category, price, stock);
                    db.store(newProd);
                    db.commit();
                    System.out.println(" Berhasil! Data Produk '" + name + "' disimpan ke db4o.");
                    DatabaseManager.close();

                } else if (pilihan == 3) {
                    ObjectContainer db = DatabaseManager.getDB();
                    System.out.println("\n--- DAFTAR SEMUA CUSTOMER DI DATABASE ---");
                    
                    com.db4o.query.Query q = db.query();
                    q.constrain(IndividualCustomer.class);
                    ObjectSet<IndividualCustomer> customers = q.execute();
                    
                    if (customers.isEmpty()) {
                        System.out.println("Database kosong atau belum di-generate.");
                    } else {
                        int no = 1;
                        for (IndividualCustomer c : customers) {
                            System.out.println(no++ + ". [" + c.getCustomerId() + "] " + c.getName() + " | " + c.getPhone() + " | " + c.getAge() + " tahun | " + c.getOccupation());
                        }
                    }
                    DatabaseManager.close();

                } else if (pilihan == 4) {
                    ObjectContainer db = DatabaseManager.getDB();
                    System.out.println("\n--- DAFTAR SEMUA PRODUK DI DATABASE ---");
                    
                    com.db4o.query.Query q = db.query();
                    q.constrain(Product.class);
                    ObjectSet<Product> products = q.execute();
                    
                    if (products.isEmpty()) {
                        System.out.println("Database kosong atau belum di-generate.");
                    } else {
                        int no = 1;
                        for (Product p : products) {
                            System.out.println(no++ + ". [" + p.getProductId() + "] " + p.getProductName() + " (" + p.getCategory() + ") | Harga: Rp" + p.getPrice() + " | Stok: " + p.getStock());
                        }
                    }
                    DatabaseManager.close();

                } else if (pilihan == 5) {
                    ObjectContainer db = DatabaseManager.getDB();
                    System.out.println("\n--- HAPUS DATA CUSTOMER ---");
                    System.out.print("Masukkan ID Customer yang ingin dihapus (contoh: C01): ");
                    String targetId = scanner.nextLine();
                    
                    com.db4o.query.Query query = db.query();
                    query.constrain(IndividualCustomer.class);
                    query.descend("customerId").constrain(targetId);
                    ObjectSet<IndividualCustomer> result = query.execute();
                    
                    if (result.hasNext()) {
                        IndividualCustomer custToDelete = result.next();
                        db.delete(custToDelete);
                        db.commit();
                        System.out.println("Berhasil! Customer dengan ID '" + targetId + "' (" + custToDelete.getName() + ") telah dihapus.");
                    } else {
                        System.out.println("Data tidak ditemukan! Periksa kembali ID Customer.");
                    }
                    DatabaseManager.close();

                } else if (pilihan == 6) {
                    ObjectContainer db = DatabaseManager.getDB();
                    System.out.println("\n--- HAPUS DATA PRODUK ---");
                    System.out.print("Masukkan ID Produk yang ingin dihapus (contoh: P01): ");
                    String targetId = scanner.nextLine();
                    
                    com.db4o.query.Query query = db.query();
                    query.constrain(Product.class);
                    query.descend("productId").constrain(targetId);
                    ObjectSet<Product> result = query.execute();
                    
                    if (result.hasNext()) {
                        Product prodToDelete = result.next();
                        db.delete(prodToDelete);
                        db.commit();
                        System.out.println("Berhasil! Produk dengan ID '" + targetId + "' (" + prodToDelete.getProductName() + ") telah dihapus.");
                    } else {
                        System.out.println("Data tidak ditemukan! Periksa kembali ID Produk.");
                    }
                    DatabaseManager.close();

                } else if (pilihan == 7) {
                    NativeQueries.runQueries();
                } else if (pilihan == 8) {
                    QBEQueries.runQueries();
                } else if (pilihan == 9) {
                    SODAQueries.runQueries();
                } else if (pilihan == 0) {
                    System.out.println("Menutup database... Terima kasih!");
                    DatabaseManager.close();
                } else {
                    System.out.println("Pilihan tidak valid!");
                }
            } catch (Exception e) {
                System.out.println("⚠ Terjadi Kesalahan: " + e.getMessage());
                e.printStackTrace();
                DatabaseManager.close();
            }
        }
        scanner.close();
    }
}