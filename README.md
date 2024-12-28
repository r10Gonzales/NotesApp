# TravelNote

TravelNote adalah aplikasi mobile yang membantu pengguna mencatat dan mengelola catatan perjalanan mereka. Aplikasi ini menyediakan fitur CRUD (Create, Read, Update, Delete) untuk catatan perjalanan dan menggunakan Firebase Realtime Database untuk menyimpan data.

## Fitur

- **Tambah Catatan Perjalanan**: Pengguna dapat menambahkan catatan perjalanan baru dengan informasi seperti judul, deskripsi, lokasi, dan tanggal perjalanan.
- **Lihat Daftar Catatan**: Pengguna dapat melihat daftar semua catatan perjalanan yang telah dibuat.
- **Edit Catatan**: Pengguna dapat mengedit informasi catatan perjalanan yang sudah ada.
- **Hapus Catatan**: Pengguna dapat menghapus catatan perjalanan yang tidak lagi diperlukan.
- **Profil Pengguna**: Menampilkan informasi pengguna dan menyediakan opsi untuk logout.

## Teknologi yang Digunakan

- **Kotlin**: Bahasa pemrograman utama.
- **Firebase Realtime Database**: Untuk penyimpanan dan pengambilan data catatan perjalanan secara real-time.
- **Firebase Authentication**: Untuk autentikasi pengguna.
- **Material Design**: Untuk desain antarmuka pengguna yang menarik dan intuitif.
- **Jetpack Navigation**: Untuk mengelola navigasi antar halaman dalam aplikasi.
- **View Binding**: Untuk mengakses komponen UI secara lebih aman dan efisien.

## Struktur Direktori Proyek
com.rio.responsi_mobile/
  activity/
    MainActivity
    AddTravelNoteActivity
    TravelNoteDetailActivity
  fragment/
    HomeFragment
    ProfileFragment
  adapter/
    TravelNoteAdapter
  model/
    TravelNote
  utils/
    FirebaseHelper
  res/
    layout/
      activity_main.xml
      item_travel_note.xml
      fragment_home.xml
      fragment_profile.xml
      activity_add_travel_note.xml
      activity_travel_note_detail.xml
      navigation/
      nav_graph.xml
    menu/
      bottom_nav_menu.xml

## Instalasi dan Menjalankan Proyek

1. **Clone Repository**:
    ```bash
    git clone https://github.com/username/travelnote.git
    cd travelnote
    ```

2. **Buka Proyek di Android Studio**:
    - Buka Android Studio.
    - Pilih `Open an existing project`.
    - Navigasi ke direktori `travelnote` dan pilih `Open`.

3. **Konfigurasi Firebase**:
    - Buat proyek baru di [Firebase Console](https://console.firebase.google.com/).
    - Tambahkan aplikasi Android ke proyek Firebase Anda.
    - Unduh file `google-services.json` dan letakkan di direktori `app` proyek Anda.
    - Tambahkan dependensi Firebase di `build.gradle` proyek dan modul.

4. **Jalankan Aplikasi**:
    - Hubungkan perangkat Android atau gunakan emulator.
    - Klik tombol `Run` di Android Studio atau gunakan shortcut Shift+F10.

## Screenshot

### Halaman Utama
![Halaman Utama](screenshots/home.png)

### Tambah Catatan
![Tambah Catatan](screenshots/add_note.png)

### Detail Catatan
![Detail Catatan](screenshots/detail_note.png)

### Profil Pengguna
![Profil Pengguna](screenshots/profile.png)

## Kontribusi

Kontribusi sangat dihargai! Jika Anda ingin berkontribusi, silakan fork repository ini dan buat pull request dengan perubahan Anda.

## Lisensi

Proyek ini dilisensikan di bawah lisensi MIT - lihat file [LICENSE](LICENSE) untuk detail lebih lanjut.
