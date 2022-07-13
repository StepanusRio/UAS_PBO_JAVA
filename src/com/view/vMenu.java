package com.view;

// import com.view.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
// import javax.swing.event.*;

public class vMenu extends JFrame {
    private JTextArea m_editArea = new JTextArea();

    private JPopupMenu m_popup = new JPopupMenu();
    private JLabel lNama = new JLabel("Stepanus Rio Defa Ardiantoro || A12.2020.06337"),
            lAlamat = new JLabel("Jl. Nuri 293 SRG");
    // Nama Ganti dengan Nama dan NIM anda.......... !

    // Constructor
    public vMenu() {
        setPreferredSize(new Dimension(800, 600));
        setTitle("MENU PENJUALAN");

        JDesktopPane JDPMenu = new JDesktopPane();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lNama.setBounds(15, 20, 700, 50);
        lAlamat.setBounds(15, 55, 200, 25);
        lNama.setFont(new Font("Arial", Font.BOLD, 24));
        lNama.setForeground(new Color(220, 10, 10));
        lAlamat.setFont(new Font("Verdana", Font.PLAIN, 15));
        lAlamat.setForeground(new Color(10, 10, 220));

        // Mnemonic : keyboard mnemonic berfungsi untuk mempercepat pemilihan menu tanpa
        // menggunakan mouse,

        // membuat sub menu (M0) Pegawai.....................................
        JMenuItem M01Pegawai = new JMenuItem("Pegawai");
        M01Pegawai.setMnemonic('P');
        M01Pegawai.setAccelerator(KeyStroke.getKeyStroke("control P"));

        JMenuItem M02Password = new JMenuItem("PaSsword");
        M02Password.setMnemonic('S');
        M02Password.setEnabled(false);
        M02Password.setAccelerator(KeyStroke.getKeyStroke("control S"));

        JMenuItem M03Quit = new JMenuItem("Quit");
        M03Quit.setMnemonic('Q');
        M03Quit.setAccelerator(KeyStroke.getKeyStroke("control Q"));

        // membuat sub menu (M1) Barang.....................................
        JMenuItem M11Barang = new JMenuItem("Barang");
        M11Barang.setMnemonic('B');
        M11Barang.setAccelerator(KeyStroke.getKeyStroke("control B"));

        JMenuItem M12Stok = new JMenuItem("Stock Minim");
        M12Stok.setMnemonic('M');
        M12Stok.setEnabled(false);
        M12Stok.setAccelerator(KeyStroke.getKeyStroke("control M"));

        // membuat sub menu (M2) Transaksi.....................................
        JMenuItem M21Pengadaan = new JMenuItem("PengaDaan");
        M21Pengadaan.setMnemonic('D');
        M21Pengadaan.setEnabled(false);
        M21Pengadaan.setAccelerator(KeyStroke.getKeyStroke("control D"));

        JMenuItem M22Penjualan = new JMenuItem("PenJualan");
        M22Penjualan.setMnemonic('J');
        M22Penjualan.setEnabled(true);
        M22Penjualan.setAccelerator(KeyStroke.getKeyStroke("control J"));

        // membuat sub menu (M3) Laporan...
        JMenuItem M31LaporanPerStok = new JMenuItem("Laporan Per Stok");
        M31LaporanPerStok.setMnemonic('L');
        M31LaporanPerStok.setEnabled(false);
        M31LaporanPerStok.setAccelerator(KeyStroke.getKeyStroke("control S"));

        JMenuItem M32LaporanSemuaBarang = new JMenuItem("Laporan Semua Barang");
        M32LaporanSemuaBarang.setMnemonic('L');
        M32LaporanSemuaBarang.setEnabled(true);
        M32LaporanSemuaBarang.setAccelerator(KeyStroke.getKeyStroke("control S"));

        // (2) Build menubar, menus, and add menuitems....................
        JMenuBar menubar = new JMenuBar(); // Create new menu bar.........
        JMenu MenuPegawai = new JMenu("Pegawai"); // Create new menu Pegawai
        MenuPegawai.setMnemonic('P');
        menubar.add(MenuPegawai);
        MenuPegawai.add(M01Pegawai);
        MenuPegawai.add(M02Password);
        MenuPegawai.addSeparator();
        MenuPegawai.add(M03Quit);

        JMenu MenuBarang = new JMenu("Barang"); // Create new menu Barang
        MenuBarang.setMnemonic('B');
        menubar.add(MenuBarang);
        MenuBarang.add(M11Barang);
        MenuBarang.add(M12Stok);

        JMenu MenuTransaksi = new JMenu("Transaksi"); // Create new menu Transaksi
        MenuTransaksi.setMnemonic('T');
        menubar.add(MenuTransaksi);
        MenuTransaksi.add(M21Pengadaan);
        MenuTransaksi.add(M22Penjualan);

        // MenuLaporan.add(M3Laporan);
        JMenu MenuLaporan = new JMenu("Laporan"); // Create new menu Laporan
        MenuLaporan.setMnemonic('L');
        menubar.add(MenuLaporan);
        MenuLaporan.add(M31LaporanPerStok);
        MenuLaporan.add(M32LaporanSemuaBarang);

        // menjalankan pilihan.....................
        M01Pegawai.addActionListener(new BukaPegawai());
        M03Quit.addActionListener(new QuitAction());

        M11Barang.addActionListener(new BukaBarang());

        M22Penjualan.addActionListener(new BukaJual());

        M31LaporanPerStok.addActionListener(new LaporanBarangPerStok());

        M32LaporanSemuaBarang.addActionListener(new LaporanBarangSemua());

        // Add the (unused) text area to the content pane............
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        content.add(m_editArea, BorderLayout.CENTER);

        // ... Add menu items to popup menu, add popup menu to text area.
        m_popup.add(new JMenuItem("Testing"));
        m_editArea.setComponentPopupMenu(m_popup);

        // ... Set the JFrame's content pane and menu bar.
        setContentPane(content);
        setJMenuBar(menubar);

        JDPMenu.add(lNama);
        JDPMenu.add(lAlamat);
        getContentPane().add(JDPMenu).setBackground(Color.getHSBColor(180, 100, 200));
        pack();
        setLocationRelativeTo(null);
    }

    class OpenAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(vMenu.this, "Can't Open.");
        }
    }

    class BukaPegawai implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            vPegawai xPegawai = new vPegawai();
            xPegawai.setVisible(true);

        }
    }

    class BukaBarang implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            vBarang xBarang = new vBarang();
            xBarang.setVisible(true);
        }
    }

    class BukaJual implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            vJual xJual = new vJual();
            xJual.setVisible(true);
        }
    }

    class LaporanBarangPerStok implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // cetakBarangPerStok xLaporanPerStok = new cetakBarangPerStok();
            // xLaporanPerStok.setVisible(true);
        }

    }

    class LaporanBarangSemua implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Run Class CetakBarangSemua
            CetakBarangSemua xLaporanSemua = new CetakBarangSemua();
            xLaporanSemua.setVisible(true);
        }

    }

    class QuitAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0); // Terminate the program..........
        }
    }
    
}
