/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import workshopapp.Koneksi;

/**
 *
 * @author Sultan
 */
public class FormUtama extends javax.swing.JFrame {

    public void getResetTabel(){
        String kolom[] = {"ID","Materi"};
        DefaultTableModel dtm = new DefaultTableModel(null, kolom);
        String data[] = {null, null};
        dtm.addRow(data);
        tbMateriNarasumber.setModel(dtm);
    }
    
    public void getDataMateriDilatih(){
        int baris = tbNarasumber.getSelectedRow();
        if (baris != -1) {
            String idNarasumber = tbNarasumber.getValueAt(baris, 0).toString();
            String kolom[] = {"ID","Materi"};
            DefaultTableModel dtm = new DefaultTableModel(null, kolom);
            String SQL = "SELECT m.id_materi, m.nama_materi FROM tb_materi m JOIN tb_narasumber_materi nm ON nm.id_materi = m.id_materi WHERE nm.id_narasumber = '"+idNarasumber+"'";
            ResultSet rs = Koneksi.executeQuery(SQL);
            try {
                while(rs.next()) {
                    String kolID = rs.getString(1);
                    String kolMateri = rs.getString(2);
                    String data[] = {kolID, kolMateri};
                    dtm.addRow(data);
                }
            } catch (SQLException ex) {
                Logger.getLogger(FormUtama.class.getName()).log(Level.SEVERE, null, ex);
            }
            tbMateriNarasumber.setModel(dtm);
        }
    }
    
    public void getDataPeserta(){
        String kolom[] = {"ID","Nama","Telepon","Email", "Alamat"};
        DefaultTableModel dtm = new DefaultTableModel(null, kolom);
        String SQL = "SELECT id_peserta, nama_peserta, no_telp, email, alamat FROM peserta";
        ResultSet rs = Koneksi.executeQuery(SQL);
        try {
            while(rs.next()) {
                String kolID = rs.getString(1);
                String kolNama = rs.getString(2);
                String kolTelp = rs.getString(3);
                String kolEmail = rs.getString(4);
                String kolAlamat = rs.getString(5);
                String data[] = {kolID, kolNama, kolTelp, kolEmail, kolAlamat};
                dtm.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
        tbPeserta.setModel(dtm);
        bUbahHapusPeserta.setEnabled(false);
    }
    
    public void getDataNarasumber(){
        String kolom[] = {"ID","Nama","Telepon","Email"};
        DefaultTableModel dtm = new DefaultTableModel(null, kolom);
        String SQL = "SELECT * FROM tb_narasumber";
        ResultSet rs = Koneksi.executeQuery(SQL);
        try {
            while(rs.next()) {
                String kolID = rs.getString(1);
                String kolNama = rs.getString(2);
                String kolTelp = rs.getString(3);
                String kolEmail = rs.getString(4);
                String data[] = {kolID, kolNama, kolTelp, kolEmail};
                dtm.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
        tbNarasumber.setModel(dtm);
        bUbahHapusNarasumber.setEnabled(false);
    }
    
    public void getDataMateri(){
        String kolom[] = {"ID","Nama Materi"};
        DefaultTableModel dtm = new DefaultTableModel(null, kolom);
        String SQL = "SELECT * FROM tb_materi";
        ResultSet rs = Koneksi.executeQuery(SQL);
        try {
            while(rs.next()) {
                String kolID = rs.getString(1);
                String kolNamaMateri = rs.getString(2);
                String data[] = {kolID, kolNamaMateri};
                dtm.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
        tbMateri.setModel(dtm);
        bUbahHapusMateri.setEnabled(false);
    }
    
    public void getDataRuangan(){
        String kolom[] = {"ID","Nama Ruangan","Kapasitas"};
        DefaultTableModel dtm = new DefaultTableModel(null, kolom);
        String SQL = "SELECT * FROM tb_ruangan";
        ResultSet rs = Koneksi.executeQuery(SQL);
        try {
            while(rs.next()) {
                String kolID = rs.getString(1);
                String kolNamaMateri = rs.getString(2);
                String kolKapasitas = rs.getString(3);
                String data[] = {kolID, kolNamaMateri, kolKapasitas};
                dtm.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
        tbRuangan.setModel(dtm);
        bUbahHapusRuangan.setEnabled(false);
    }
    
    public void getCariPeserta(){
        String kolom[] = {"ID","Nama","Telepon","Email", "Alamat"};
        DefaultTableModel dtm = new DefaultTableModel(null, kolom);
        String SQL = "";
        if(rbCariByIDPeserta.isSelected()){
            SQL = "SELECT id_peserta, nama_peserta, no_telp, email, alamat FROM peserta WHERE id_peserta like '%"+tCariPeserta.getText()+"%'";
        }else if(rbCariByNamaPeserta.isSelected()){
            SQL = "SELECT id_peserta, nama_peserta, no_telp, email, alamat FROM peserta WHERE nama_peserta like '%"+tCariPeserta.getText()+"%'";
        }
        ResultSet rs = Koneksi.executeQuery(SQL);
        try {
            while(rs.next()) {
                String kolID = rs.getString(1);
                String kolNama = rs.getString(2);
                String kolTelp = rs.getString(3);
                String kolEmail = rs.getString(4);
                String kolAlamat = rs.getString(5);
                String data[] = {kolID, kolNama, kolTelp, kolEmail, kolAlamat};
                dtm.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
        tbPeserta.setModel(dtm);
        bUbahHapusPeserta.setEnabled(false);
    }
    
    public void getCariNarasumber(){
        String kolom[] = {"ID","Nama","Telepon","Email"};
        DefaultTableModel dtm = new DefaultTableModel(null, kolom);
        String SQL = "";
        if(rbCariByIDNarasumber.isSelected()){
            SQL = "SELECT * FROM tb_narasumber WHERE id_narasumber like '%"+tCariNarasumber.getText()+"%'";
        }else if(rbCariByNamaNarasumber.isSelected()){
            SQL = "SELECT * FROM tb_narasumber WHERE nama_narasumber like '%"+tCariNarasumber.getText()+"%'";
        }
        ResultSet rs = Koneksi.executeQuery(SQL);
        try {
            while(rs.next()) {
                String kolID = rs.getString(1);
                String kolNama = rs.getString(2);
                String kolTelp = rs.getString(3);
                String kolEmail = rs.getString(4);
                String data[] = {kolID, kolNama, kolTelp, kolEmail};
                dtm.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
        tbNarasumber.setModel(dtm);
        bUbahHapusNarasumber.setEnabled(false);
    }
    
    public void getCariMateri(){
        String kolom[] = {"ID","Nama Materi"};
        DefaultTableModel dtm = new DefaultTableModel(null, kolom);
        String SQL = "";
        if(rbCariByIDMateri.isSelected()){
            SQL = "SELECT * FROM tb_materi WHERE id_materi like '%"+tCariMateri.getText()+"%'";
        }else if(rbCariByNamaMateri.isSelected()){
            SQL = "SELECT * FROM tb_materi WHERE nama_materi like '%"+tCariMateri.getText()+"%'";
        }
        ResultSet rs = Koneksi.executeQuery(SQL);
        try {
            while(rs.next()) {
                String kolID = rs.getString(1);
                String kolNamaMateri = rs.getString(2);
                String data[] = {kolID, kolNamaMateri};
                dtm.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
        tbMateri.setModel(dtm);
        bUbahHapusMateri.setEnabled(false);
    }
    
    public void getCariRuangan(){
        String kolom[] = {"ID","Nama Ruangan","Kapasitas"};
        DefaultTableModel dtm = new DefaultTableModel(null, kolom);
        String SQL = "";
        if(rbCariByIDRuangan.isSelected()){
            SQL = "SELECT * FROM tb_ruangan WHERE id_ruangan like '%"+tCariRuangan.getText()+"%'";
        }else if(rbCariByNamaRuangan.isSelected()){
            SQL = "SELECT * FROM tb_ruangan WHERE nama_ruangan like '%"+tCariRuangan.getText()+"%'";
        }
        ResultSet rs = Koneksi.executeQuery(SQL);
        try {
            while(rs.next()) {
                String kolID = rs.getString(1);
                String kolNama = rs.getString(2);
                String kolKapasitas = rs.getString(3);
                String data[] = {kolID, kolNama, kolKapasitas};
                dtm.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
        tbRuangan.setModel(dtm);
        bUbahHapusRuangan.setEnabled(false);
    }
    
    /**
     * Creates new form FormUtama
     */
    public FormUtama() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgCariPeserta = new javax.swing.ButtonGroup();
        bgCariNarasumber = new javax.swing.ButtonGroup();
        bgCariMateri = new javax.swing.ButtonGroup();
        bgCariRuangan = new javax.swing.ButtonGroup();
        pDasar = new javax.swing.JPanel();
        pJudulProgram = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jButton17 = new javax.swing.JButton();
        tpMenuUtama = new javax.swing.JTabbedPane();
        pSesi = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jButton16 = new javax.swing.JButton();
        pRuangan = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbRuangan = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        bTambahRuangan = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        tCariRuangan = new javax.swing.JTextField();
        bUbahHapusRuangan = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbRiwayatRuangan = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        rbCariByIDRuangan = new javax.swing.JRadioButton();
        rbCariByNamaRuangan = new javax.swing.JRadioButton();
        pPeserta = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        bTambahPeserta = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbPeserta = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        tCariPeserta = new javax.swing.JTextField();
        bUbahHapusPeserta = new javax.swing.JButton();
        rbCariByIDPeserta = new javax.swing.JRadioButton();
        rbCariByNamaPeserta = new javax.swing.JRadioButton();
        pNarasumber = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        bTambahNarasumber = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        tCariNarasumber = new javax.swing.JTextField();
        bUbahHapusNarasumber = new javax.swing.JButton();
        rbCariByIDNarasumber = new javax.swing.JRadioButton();
        rbCariByNamaNarasumber = new javax.swing.JRadioButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbNarasumber = new javax.swing.JTable();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbMateriNarasumber = new javax.swing.JTable();
        jLabel27 = new javax.swing.JLabel();
        pMateri = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        bTambahMateri = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        tCariMateri = new javax.swing.JTextField();
        bUbahHapusMateri = new javax.swing.JButton();
        rbCariByIDMateri = new javax.swing.JRadioButton();
        rbCariByNamaMateri = new javax.swing.JRadioButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbMateri = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pDasar.setBackground(new java.awt.Color(204, 204, 204));
        pDasar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pJudulProgram.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setText("JUDUL PROGRAM");
        pJudulProgram.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 350, 50));

        jButton17.setText("Keluar");
        pJudulProgram.add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(701, 40, 70, 20));

        pDasar.add(pJudulProgram, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 780, 70));

        tpMenuUtama.setBackground(new java.awt.Color(255, 255, 255));
        tpMenuUtama.setEnabled(false);

        pSesi.setBackground(new java.awt.Color(204, 255, 255));
        pSesi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel13.setBackground(new java.awt.Color(204, 204, 204));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(jTable6);

        jPanel13.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 620, 190));

        jLabel15.setText("Daftar Sesi");
        jPanel13.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 20));

        jLabel16.setText("Tambah Sesi Baru");
        jPanel13.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, -1, 20));

        jButton13.setText("+");
        jPanel13.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, 50, 20));
        jPanel13.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 750, 10));

        jLabel17.setText("Cari Sesi");
        jPanel13.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, -1, 20));
        jPanel13.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 260, -1));

        jButton14.setText("Pendaftaran");
        jPanel13.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 240, 100, 20));

        jButton15.setText("Ubah / Hapus");
        jPanel13.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 240, -1, 20));

        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane7.setViewportView(jTable7);

        jPanel13.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 620, 140));

        jLabel18.setText("0");
        jPanel13.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 280, 10, 20));

        jLabel19.setText("Daftar Peserta Sesi");
        jPanel13.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, -1, 20));

        jLabel20.setText("Jumlah Peserta : ");
        jPanel13.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 280, -1, 20));

        jButton16.setText("Cari");
        jPanel13.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 240, 80, 20));

        pSesi.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 780, 460));

        tpMenuUtama.addTab("SESI", pSesi);

        pRuangan.setBackground(new java.awt.Color(204, 255, 204));
        pRuangan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel10.setBackground(new java.awt.Color(204, 204, 204));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbRuangan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbRuangan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRuanganMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbRuangan);

        jPanel10.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 620, 190));

        jLabel1.setText("Daftar Ruangan");
        jPanel10.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 20));

        jLabel2.setText("Tambah Ruangan Baru");
        jPanel10.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, -1, 20));

        bTambahRuangan.setText("+");
        bTambahRuangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTambahRuanganActionPerformed(evt);
            }
        });
        jPanel10.add(bTambahRuangan, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, 50, 20));
        jPanel10.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 750, 10));

        jLabel3.setText("Cari Ruangan");
        jPanel10.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, -1, 20));

        tCariRuangan.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tCariRuanganCaretUpdate(evt);
            }
        });
        jPanel10.add(tCariRuangan, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 300, -1));

        bUbahHapusRuangan.setText("Ubah / Hapus");
        bUbahHapusRuangan.setEnabled(false);
        bUbahHapusRuangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUbahHapusRuanganActionPerformed(evt);
            }
        });
        jPanel10.add(bUbahHapusRuangan, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 240, 100, 20));

        tbRiwayatRuangan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tbRiwayatRuangan);

        jPanel10.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 620, 140));

        jLabel4.setText("Riwayat Penggunaan Ruangan");
        jPanel10.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, -1, 20));

        rbCariByIDRuangan.setBackground(new java.awt.Color(204, 204, 204));
        bgCariRuangan.add(rbCariByIDRuangan);
        rbCariByIDRuangan.setSelected(true);
        rbCariByIDRuangan.setText("ID");
        jPanel10.add(rbCariByIDRuangan, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 240, -1, -1));

        rbCariByNamaRuangan.setBackground(new java.awt.Color(204, 204, 204));
        bgCariRuangan.add(rbCariByNamaRuangan);
        rbCariByNamaRuangan.setText("Nama");
        jPanel10.add(rbCariByNamaRuangan, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 240, -1, -1));

        pRuangan.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 780, 460));

        tpMenuUtama.addTab("RUANGAN", pRuangan);

        pPeserta.setBackground(new java.awt.Color(0, 0, 102));
        pPeserta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setText("Daftar Peserta");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 20));

        jLabel7.setText("Tambah Peserta Baru");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, -1, 20));

        bTambahPeserta.setText("+");
        bTambahPeserta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTambahPesertaActionPerformed(evt);
            }
        });
        jPanel3.add(bTambahPeserta, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, 50, 20));

        tbPeserta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbPeserta.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbPeserta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPesertaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbPeserta);

        jPanel3.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 620, 380));

        jLabel8.setText("Cari Peserta");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, -1, 20));

        tCariPeserta.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tCariPesertaCaretUpdate(evt);
            }
        });
        jPanel3.add(tCariPeserta, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 430, 300, -1));

        bUbahHapusPeserta.setText("Ubah / Hapus");
        bUbahHapusPeserta.setEnabled(false);
        bUbahHapusPeserta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUbahHapusPesertaActionPerformed(evt);
            }
        });
        jPanel3.add(bUbahHapusPeserta, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 430, 100, 20));

        rbCariByIDPeserta.setBackground(new java.awt.Color(204, 204, 204));
        bgCariPeserta.add(rbCariByIDPeserta);
        rbCariByIDPeserta.setText("ID");
        jPanel3.add(rbCariByIDPeserta, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 430, -1, -1));

        rbCariByNamaPeserta.setBackground(new java.awt.Color(204, 204, 204));
        bgCariPeserta.add(rbCariByNamaPeserta);
        rbCariByNamaPeserta.setText("Nama");
        jPanel3.add(rbCariByNamaPeserta, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 430, -1, -1));

        pPeserta.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 780, 460));

        tpMenuUtama.addTab("PESERTA", pPeserta);

        pNarasumber.setBackground(new java.awt.Color(255, 204, 204));
        pNarasumber.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBackground(new java.awt.Color(204, 204, 204));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setText("Daftar Narasumber");
        jPanel9.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 20));

        jLabel22.setText("Tambah Narasumber Baru");
        jPanel9.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, -1, 20));

        bTambahNarasumber.setText("+");
        bTambahNarasumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTambahNarasumberActionPerformed(evt);
            }
        });
        jPanel9.add(bTambahNarasumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, 50, 20));

        jLabel23.setText("Materi yang dilatih :");
        jPanel9.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, -1, 20));

        tCariNarasumber.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tCariNarasumberCaretUpdate(evt);
            }
        });
        jPanel9.add(tCariNarasumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, 280, -1));

        bUbahHapusNarasumber.setText("Ubah / Hapus");
        bUbahHapusNarasumber.setEnabled(false);
        bUbahHapusNarasumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUbahHapusNarasumberActionPerformed(evt);
            }
        });
        jPanel9.add(bUbahHapusNarasumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 240, 100, 20));

        rbCariByIDNarasumber.setBackground(new java.awt.Color(204, 204, 204));
        bgCariNarasumber.add(rbCariByIDNarasumber);
        rbCariByIDNarasumber.setSelected(true);
        rbCariByIDNarasumber.setText("ID");
        jPanel9.add(rbCariByIDNarasumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 240, -1, -1));

        rbCariByNamaNarasumber.setBackground(new java.awt.Color(204, 204, 204));
        bgCariNarasumber.add(rbCariByNamaNarasumber);
        rbCariByNamaNarasumber.setText("Nama");
        jPanel9.add(rbCariByNamaNarasumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 240, -1, -1));

        tbNarasumber.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbNarasumber.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbNarasumberMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbNarasumber);

        jPanel9.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 620, 190));
        jPanel9.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 750, 10));

        tbMateriNarasumber.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "ID", "Materi"
            }
        ));
        jScrollPane5.setViewportView(tbMateriNarasumber);

        jPanel9.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 620, 140));

        jLabel27.setText("Cari Narasumber");
        jPanel9.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, -1, 20));

        pNarasumber.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 780, 460));

        tpMenuUtama.addTab("NARASUMBER", pNarasumber);

        pMateri.setBackground(new java.awt.Color(255, 255, 204));
        pMateri.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel11.setBackground(new java.awt.Color(204, 204, 204));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel24.setText("Daftar Materi");
        jPanel11.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 20));

        jLabel25.setText("Tambah Materi Baru");
        jPanel11.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, -1, 20));

        bTambahMateri.setText("+");
        bTambahMateri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTambahMateriActionPerformed(evt);
            }
        });
        jPanel11.add(bTambahMateri, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, 50, 20));

        jLabel26.setText("Cari Materi");
        jPanel11.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, -1, 20));

        tCariMateri.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tCariMateriCaretUpdate(evt);
            }
        });
        jPanel11.add(tCariMateri, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 430, 310, -1));

        bUbahHapusMateri.setText("Ubah / Hapus");
        bUbahHapusMateri.setEnabled(false);
        bUbahHapusMateri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUbahHapusMateriActionPerformed(evt);
            }
        });
        jPanel11.add(bUbahHapusMateri, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 430, 100, 20));

        rbCariByIDMateri.setBackground(new java.awt.Color(204, 204, 204));
        bgCariMateri.add(rbCariByIDMateri);
        rbCariByIDMateri.setSelected(true);
        rbCariByIDMateri.setText("ID");
        jPanel11.add(rbCariByIDMateri, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 430, -1, -1));

        rbCariByNamaMateri.setBackground(new java.awt.Color(204, 204, 204));
        bgCariMateri.add(rbCariByNamaMateri);
        rbCariByNamaMateri.setText("Nama");
        jPanel11.add(rbCariByNamaMateri, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 430, -1, -1));

        tbMateri.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbMateri.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMateriMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tbMateri);

        jPanel11.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 620, 380));

        pMateri.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 780, 460));

        tpMenuUtama.addTab("MATERI", pMateri);

        pDasar.add(tpMenuUtama, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 650, 510));

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Menu");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jButton2.setText("SESI");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 110, 20));

        jButton3.setText("RUANGAN");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 110, 20));

        jButton1.setText("MATERI");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 110, 20));

        jButton4.setText("PESERTA");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 110, 20));

        jButton5.setText("NARASUMBER");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 110, 20));

        pDasar.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 130, 490));

        getContentPane().add(pDasar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        getDataPeserta();
        getDataNarasumber();
        getDataMateri();
        getResetTabel();
        getDataRuangan();
    }//GEN-LAST:event_formWindowActivated

    private void bTambahPesertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTambahPesertaActionPerformed
        FormTambahPeserta tp = new FormTambahPeserta();
        tp.setVisible(true);
        tp.roleButton("simpan");
    }//GEN-LAST:event_bTambahPesertaActionPerformed

    private void bUbahHapusPesertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUbahHapusPesertaActionPerformed
        int baris = tbPeserta.getSelectedRow();
        FormTambahPeserta tp = new FormTambahPeserta();
        tp.setVisible(true);
        tp.roleButton("ubahhapus");
        tp.getTextField(tbPeserta.getValueAt(baris, 0).toString());
    }//GEN-LAST:event_bUbahHapusPesertaActionPerformed

    private void tbPesertaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPesertaMouseClicked
        int baris = tbPeserta.getSelectedRow();
        if (baris != -1) {
            bUbahHapusPeserta.setEnabled(true);
        }
    }//GEN-LAST:event_tbPesertaMouseClicked

    private void tCariPesertaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tCariPesertaCaretUpdate
        getCariPeserta();
    }//GEN-LAST:event_tCariPesertaCaretUpdate

    private void bTambahNarasumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTambahNarasumberActionPerformed
        FormTambahNarasumber tp = new FormTambahNarasumber();
        tp.setVisible(true);
        tp.roleButton("simpan");
    }//GEN-LAST:event_bTambahNarasumberActionPerformed

    private void tCariNarasumberCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tCariNarasumberCaretUpdate
        getCariNarasumber();
        getResetTabel();
    }//GEN-LAST:event_tCariNarasumberCaretUpdate

    private void bUbahHapusNarasumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUbahHapusNarasumberActionPerformed
        int baris = tbNarasumber.getSelectedRow();
        FormTambahNarasumber tp = new FormTambahNarasumber();
        tp.setVisible(true);
        tp.roleButton("ubahhapus");
        tp.getTextField(tbNarasumber.getValueAt(baris, 0).toString());
        tp.getDataMateriDilatih();
        tp.getDataMateriTersedia();
    }//GEN-LAST:event_bUbahHapusNarasumberActionPerformed

    private void tbNarasumberMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNarasumberMouseClicked
        int baris = tbNarasumber.getSelectedRow();
        if (baris != -1) {
            bUbahHapusNarasumber.setEnabled(true);
            getDataMateriDilatih();
        }
    }//GEN-LAST:event_tbNarasumberMouseClicked

    private void bTambahMateriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTambahMateriActionPerformed
        FormTambahMateri tm = new FormTambahMateri();
        tm.setVisible(true);
        tm.roleButton("simpan");
    }//GEN-LAST:event_bTambahMateriActionPerformed

    private void tCariMateriCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tCariMateriCaretUpdate
        getCariMateri();
    }//GEN-LAST:event_tCariMateriCaretUpdate

    private void bUbahHapusMateriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUbahHapusMateriActionPerformed
        int baris = tbMateri.getSelectedRow();
        FormTambahMateri tm = new FormTambahMateri();
        tm.setVisible(true);
        tm.roleButton("ubahhapus");
        tm.getTextField(tbMateri.getValueAt(baris, 0).toString());
    }//GEN-LAST:event_bUbahHapusMateriActionPerformed

    private void tbMateriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMateriMouseClicked
        int baris = tbMateri.getSelectedRow();
        if (baris != -1) {
            bUbahHapusMateri.setEnabled(true);
        }
    }//GEN-LAST:event_tbMateriMouseClicked

    private void bTambahRuanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTambahRuanganActionPerformed
        FormTambahRuangan tr = new FormTambahRuangan();
        tr.setVisible(true);
        tr.roleButton("simpan");
    }//GEN-LAST:event_bTambahRuanganActionPerformed

    private void bUbahHapusRuanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUbahHapusRuanganActionPerformed
        int baris = tbRuangan.getSelectedRow();
        FormTambahRuangan tr = new FormTambahRuangan();
        tr.setVisible(true);
        tr.roleButton("ubahhapus");
        tr.getTextField(tbRuangan.getValueAt(baris, 0).toString());
    }//GEN-LAST:event_bUbahHapusRuanganActionPerformed

    private void tbRuanganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRuanganMouseClicked
        int baris = tbRuangan.getSelectedRow();
        if (baris != -1) {
            bUbahHapusRuangan.setEnabled(true);
        }
    }//GEN-LAST:event_tbRuanganMouseClicked

    private void tCariRuanganCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tCariRuanganCaretUpdate
        getCariRuangan();
    }//GEN-LAST:event_tCariRuanganCaretUpdate

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        tpMenuUtama.setSelectedIndex(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        tpMenuUtama.setSelectedIndex(1);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        tpMenuUtama.setSelectedIndex(4);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        tpMenuUtama.setSelectedIndex(2);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        tpMenuUtama.setSelectedIndex(3);
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows Classic".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormUtama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bTambahMateri;
    private javax.swing.JButton bTambahNarasumber;
    private javax.swing.JButton bTambahPeserta;
    private javax.swing.JButton bTambahRuangan;
    private javax.swing.JButton bUbahHapusMateri;
    private javax.swing.JButton bUbahHapusNarasumber;
    private javax.swing.JButton bUbahHapusPeserta;
    private javax.swing.JButton bUbahHapusRuangan;
    private javax.swing.ButtonGroup bgCariMateri;
    private javax.swing.ButtonGroup bgCariNarasumber;
    private javax.swing.ButtonGroup bgCariPeserta;
    private javax.swing.ButtonGroup bgCariRuangan;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JPanel pDasar;
    private javax.swing.JPanel pJudulProgram;
    private javax.swing.JPanel pMateri;
    private javax.swing.JPanel pNarasumber;
    private javax.swing.JPanel pPeserta;
    private javax.swing.JPanel pRuangan;
    private javax.swing.JPanel pSesi;
    private javax.swing.JRadioButton rbCariByIDMateri;
    private javax.swing.JRadioButton rbCariByIDNarasumber;
    private javax.swing.JRadioButton rbCariByIDPeserta;
    private javax.swing.JRadioButton rbCariByIDRuangan;
    private javax.swing.JRadioButton rbCariByNamaMateri;
    private javax.swing.JRadioButton rbCariByNamaNarasumber;
    private javax.swing.JRadioButton rbCariByNamaPeserta;
    private javax.swing.JRadioButton rbCariByNamaRuangan;
    private javax.swing.JTextField tCariMateri;
    private javax.swing.JTextField tCariNarasumber;
    private javax.swing.JTextField tCariPeserta;
    private javax.swing.JTextField tCariRuangan;
    private javax.swing.JTable tbMateri;
    private javax.swing.JTable tbMateriNarasumber;
    private javax.swing.JTable tbNarasumber;
    private javax.swing.JTable tbPeserta;
    private javax.swing.JTable tbRiwayatRuangan;
    private javax.swing.JTable tbRuangan;
    private javax.swing.JTabbedPane tpMenuUtama;
    // End of variables declaration//GEN-END:variables
}
