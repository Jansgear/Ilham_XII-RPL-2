import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Aplikasi {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Kontak> daftarKontak = new ArrayList<>();

        // Membaca kontak dari file jika file sudah ada
        bacaKontakDariFile(daftarKontak);

        int pilihan;
        do {
            System.out.println("Menu Aplikasi Manajemen Kontak:");
            System.out.println("1. Tambah Kontak");
            System.out.println("2. Hapus Kontak berdasarkan Nama");
            System.out.println("3. Hapus Kontak berdasarkan Nomor Telepon");
            System.out.println("4. Tampilkan Daftar Kontak");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine(); // Membuang newline

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan Nama: ");
                    String nama = scanner.nextLine();
                    System.out.print("Masukkan Nomor Telepon: ");
                    String nomorTelepon = scanner.nextLine();
                    System.out.print("Masukkan Email: ");
                    String email = scanner.nextLine();
                    Kontak kontakBaru = new Kontak(nama, nomorTelepon, email);
                    daftarKontak.add(kontakBaru);
                    System.out.println("====== Kontak berhasil ditambahkan! =====");

                    // Menyimpan kontak yang baru ditambahkan ke dalam file
                    simpanKontakKeFile(daftarKontak);
                    break;
                case 2:
                    System.out.print("Masukkan Nama Kontak yang akan dihapus: ");
                    String namaHapus = scanner.nextLine();
                    daftarKontak.removeIf(kontak -> kontak.getNama().equalsIgnoreCase(namaHapus));
                    System.out.println("===== Kontak berhasil dihapus! =====");

                    // Menyimpan daftar kontak setelah penghapusan ke dalam file
                    simpanKontakKeFile(daftarKontak);
                    break;
                case 3:
                    System.out.print("Masukkan Nomor Telepon Kontak yang akan dihapus: ");
                    String nomorTeleponHapus = scanner.nextLine();
                    daftarKontak.removeIf(kontak -> kontak.getNomorTelepon().equals(nomorTeleponHapus));
                    System.out.println("Kontak berhasil dihapus!");

                    break;
                case 4:
                    System.out.println("==============");
                    System.out.println("Daftar Kontak:");
                    System.out.println("==============");
                    for (Kontak kontak : daftarKontak) {
                        System.out.println(kontak);
                        System.out.println("===========");
                    }
                    break;
                case 5:
                    System.out.println("Terima kasih!");
                      // Menyimpan daftar kontak setelah penghapusan ke dalam file
                    simpanKontakKeFile(daftarKontak);
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        } while (pilihan != 5);

        scanner.close();
    }

    private static void bacaKontakDariFile(ArrayList<Kontak> daftarKontak) {
        try (BufferedReader reader = new BufferedReader(new FileReader("daftar_kontak.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String nama = line;
                String nomorTelepon = reader.readLine();
                String email = reader.readLine();
                daftarKontak.add(new Kontak(nama, nomorTelepon, email));
            }
        } catch (IOException e) {
            // File belum ada atau terjadi kesalahan lain, tidak perlu diuruskan
        }
    }

    private static void simpanKontakKeFile(ArrayList<Kontak> daftarKontak) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("daftar_kontak.txt"))) {
            for (Kontak kontak : daftarKontak) {
                writer.write(kontak.getNama());
                writer.newLine();
                writer.write(kontak.getNomorTelepon());
                writer.newLine();
                writer.write(kontak.getEmail());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Kontak {
    private String nama;
    private String nomorTelepon;
    private String email;

    // Konstruktor
    public Kontak(String nama, String nomorTelepon, String email) {
        this.nama = nama;
        this.nomorTelepon = nomorTelepon;
        this.email = email;
    }

    // Getter dan setter untuk atribut-atribut
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public void setNomorTelepon(String nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Nama: " + nama + "Nomor Telepon: " + nomorTelepon + "Email: " + email;
    }
}
