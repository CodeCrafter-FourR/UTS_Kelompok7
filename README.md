
```markdown
# Metro Marketers Database System (Case 1)
> **Advanced Database Systems — Kelompok 7**
> *Implementasi Basis Data Objek (OODBMS) Menggunakan Java dan db4o Engine*

---

##  1. Informasi Project

Aplikasi **Metro Marketers Database System** ini adlah sistem manajemen data pemasaran interaktif berbasis **Command Line Interface (CLI)** yang dibuat pake bahasa pemrograman **Java** dan engine **db4o (Database for Objects)**. Project ini mendemonstrasikan implementasi nyata dari konsep *Object-Oriented Database Management System* (OODBMS) dalam menangani objek bisnis yang saling berelasi: **Customer (Individual), Product, dan Transaction** tanpa melalui proses pemetaan relasional (jadi gausah pake ORM atau SQL sama sekali).

### Kenapa Project Ini Berbeda dengan SQL Tradisional?
* **Penyimpanan Objek Utuh:** db4o menyimpan struktur objek Java secara utuh ke dalam file database lokal (`.db4o`), jadi konsep enkapsulasi dan pewarisan (*inheritance*) objek tetap terjaga murni.
* **Bebas Impedance Mismatch:** Ga perlu tabel, baris, kolom, ato framework perantara (kayak Hibernate/JPA). Data disimpan persis kayak struktur kelasnya di memori RAM.
* **Object Identity (OID):** Validasi keunikan data dikendalikan sama alamat referensi memori objek, bukan berdasarkan teks unik kayak *Primary Key* di SQL biasa.

---

## 🛠️ 2. Fitur Utama & Menu Dasbor CLI

Aplikasi ini udah dilengkapi sama sistem manajemen data dinamis (*CRUD*) dan mesin eksekusi buat **3 jenis rumpun query (Total 18 Query)**. Berikut adalah tampilan antarmuka interaktif pas program dijalankan di console:

```text
=========================================
   SYSTEM METRO MARKETERS DATABASE (CLI)
=========================================
1. Tambah Data Customer Baru (Individual)
2. Tambah Data Produk Baru
3. Tampilkan Semua Data Customer
4. Tampilkan Semua Data Produk
5. Hapus Data Customer (Berdasarkan ID)
6. Hapus Data Produk (Berdasarkan ID)
7. Jalankan Native Queries (6 Query)
8. Jalankan QBE Queries    (6 Query)
9. Jalankan SODA Queries   (6 Query)
0. Keluar Aplikasi

```

### Ringkasan 18 Query yang Diimplementasikan

| Jenis Query | Jumlah | Karakteristik Utama | Metode yang Digunakan |
| --- | --- | --- | --- |
| **Native Queries (NQ)** | 6 | *Type-safe*, pake sintaks Java murni, dievaluasi pas kompilasi. | `SELECT..WHERE`, `BETWEEN RANGE`, `SORTING DESC` |
| **Query-By-Example (QBE)** | 6 | Pake objek tiruan (*prototype*) kosongan sebagai template pencarian. | `SELECT`, `UPDATE Stock/Price`, `DELETE` |
| **SODA Queries** | 6 | API query grafis tingkat rendah, performa tinggi, mendukung logika kompleks. | `WHERE`, `NOT`, `AND`, `OR`, `SORTING ASC/DESC` |

---

## 📁 3. Struktur Arsitektur & Package Project

Project ini dirancang pake pola arsitektur **Boundary-Controller-Entity (BCE)** biar kodenya bersih (*clean code*) dan modular:

```text
UTS_Kelompok7/
│
├── src/
│   ├── metromarketersU/
│   │   └── MainCLI.java                 # Boundary: Antarmuka utama & Input Keyboard (Scanner)
│   │
│   ├── metromarketers/
│   │   ├── db/
│   │   │   ├── DatabaseManager.java     # Lifecycle Engine: Manajemen koneksi buka/tutup file DB
│   │   │   └── CreateInstances.java     # Seeder: Utility pengisi data awal database (Mock Data Case 1)
│   │   │
│   │   ├── model/                       # Entities: Cetak biru Objek Berorientasi Kelas
│   │   │   ├── Customer.java            # Abstract class (Superclass) blueprint data customer
│   │   │   ├── IndividualCustomer.java  # Subclass customer dengan atribut spesifik (Age, Occupation)
│   │   │   ├── Product.java             # Kelas model penampung komoditas produk dan stok barang
│   │   │   └── Transaction.java         # Kelas model transaksi finansial (Asosiasi ke Customer)
│   │   │
│   │   └── queries/                     # Controllers: Logika pengeksekusi query engine db4o
│   │       ├── NativeQueries.java       # Implementasi 6 kueri asli Java murni
│   │       ├── QBEQueries.java          # Implementasi 6 kueri cetak contoh (Siklus data Update/Delete)
│   │       └── SODAQueries.java         # Implementasi 6 kueri grafis node tingkat lanjut (AND, OR, NOT)
│   │
│   └── Referenced Libraries/
│       └── db4o-8.0.276.16149-all-java5.jar # Library inti Object Database Engine
│
└── metromarketers_case1.db4o            # File fisik database (Binary Object Container Store)

```

---


## 👥 4. Tim Kontributor (Kelompok 7)

* **Rehan Rizki Rahmat Ramadhan** (NIM: 1242001034) — *Informatics Engineering, Bakrie University*
* **Sakhila Yunara Devras** (1242001003)— *Informatics Engineering, Bakrie University*
* **Gissa Nandyka Putri Katjong**(1242001021)— *Informatics Engineering, Bakrie University*
* **Dinda Bestari**(1242001039)— *Informatics Engineering, Bakrie University*

---

*Kalo repositori project tugas ini membantu kamu memahami arsitektur Object Database dengan lebih mudah, boleh dong kasih Star pada repositori ini!*

```

```
