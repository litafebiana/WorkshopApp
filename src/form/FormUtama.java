/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import workshopapp.Koneksi;

/**
 *
 * @author Sultan
 */
public class FormUtama extends javax.swing.JFrame {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
    /**
     * Creates new form FormUtama
     */
    public FormUtama() {
        initComponents();
        setJam();
        getWarnaTabelSesi();
    }
    
    // gwtSesi
    public void getWarnaTabelSesi(){
        tbSesi.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

                try {
                    Date tglMulai = sdf.parse((String)table.getModel().getValueAt(row, 5));
                    Date tglSelesai = sdf.parse((String)table.getModel().getValueAt(row, 6));
                    String x = sdf.format(new Date());
                    Date sekarang = sdf.parse(x);
                    if (tglMulai.compareTo(sekarang) > 0) {
                        if(tglSelesai.compareTo(sekarang) > 0){
                            tbSesi.setSelectionBackground(Color.LIGHT_GRAY);
                            setForeground(Color.BLUE);
                        }else if(tglSelesai.compareTo(sekarang) < 0){
                            tbSesi.setSelectionBackground(Color.LIGHT_GRAY);
                            setForeground(new Color(0,138,0));
                        }else if(tglSelesai.compareTo(sekarang) == 0){
                            tbSesi.setSelectionBackground(Color.LIGHT_GRAY);
                            setForeground(new Color(0,138,0));
                        }else{
                            setForeground(Color.BLACK);
                        }
                    } else if (tglMulai.compareTo(sekarang) < 0) {
                        if(tglSelesai.compareTo(sekarang) > 0){
                            tbSesi.setSelectionBackground(Color.LIGHT_GRAY);
                            setForeground(new Color(0,138,0));
                        }else if(tglSelesai.compareTo(sekarang) < 0){
                            tbSesi.setSelectionBackground(Color.LIGHT_GRAY);
                            setForeground(Color.RED);
                        }else if(tglSelesai.compareTo(sekarang) == 0){
                            tbSesi.setSelectionBackground(Color.LIGHT_GRAY);
                            setForeground(new Color(0,138,0));
                        }else{
                            setForeground(Color.BLACK);
                        }
                    } else if (tglMulai.compareTo(sekarang) == 0) {
                        if(tglSelesai.compareTo(sekarang) > 0){
                            tbSesi.setSelectionBackground(Color.LIGHT_GRAY);
                            setForeground(new Color(0,138,0));
                        }else if(tglSelesai.compareTo(sekarang) < 0){
                            tbSesi.setSelectionBackground(Color.LIGHT_GRAY);
                            setForeground(new Color(0,138,0));
                        }else if(tglSelesai.compareTo(sekarang) == 0){
                            tbSesi.setSelectionBackground(Color.LIGHT_GRAY);
                            setForeground(new Color(0,138,0));
                        }else{
                            setForeground(Color.BLACK);
                        }
                        tbSesi.setSelectionBackground(Color.LIGHT_GRAY);
                        setForeground(new Color(0,138,0));
                    } else {
                        setForeground(Color.BLACK);
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(FormUtama.class.getName()).log(Level.SEVERE, null, ex);
                }
                return this;
            }   
        });
    }
    
    // setJam
    public final void setJam(){
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String nol_jam = "", nol_menit = "",nol_detik = "";

                java.util.Date dateTime = new java.util.Date();
                int nilai_jam = dateTime.getHours();
                int nilai_menit = dateTime.getMinutes();
                int nilai_detik = dateTime.getSeconds();

                if(nilai_jam <= 9) nol_jam= "0";
                if(nilai_menit <= 9) nol_menit= "0";
                if(nilai_detik <= 9) nol_detik= "0";

                String jam = nol_jam + Integer.toString(nilai_jam);
                String menit = nol_menit + Integer.toString(nilai_menit);
                String detik = nol_detik + Integer.toString(nilai_detik);

                lJam.setText(jam+":"+menit+":"+detik);
            }
        };
        new Timer(1000, taskPerformer).start();
    }
    
    // gdSesi
    public void getDataSesi(){
        String kolom[] = {"ID","Narasumber","Materi","Ruangan","Tema","Mulai","Selesai" ,"Kuota"};
        DefaultTableModel dtm = new DefaultTableModel(null, kolom);
        String SQL = "SELECT id_sesi, (SELECT nama_narasumber FROM tb_narasumber n WHERE n.id_narasumber = s.id_narasumber) AS nama_narasumber, (SELECT nama_materi FROM tb_materi m WHERE m.id_materi = s.id_materi) AS nama_materi, (SELECT nama_ruangan FROM tb_ruangan r WHERE r.id_ruangan = s.id_ruangan) AS nama_ruangan, tema, tanggal_mulai, tanggal_selesai, kuota FROM tb_sesi s ORDER BY tanggal_mulai DESC";
        ResultSet rs = Koneksi.executeQuery(SQL);
        try {
            while(rs.next()) {
                String kolID = rs.getString(1);
                String kolNamaNarasumber = rs.getString(2);
                String kolMateri = rs.getString(3);
                String kolRuangan = rs.getString(4);
                String koltema = rs.getString(5);
                String kolTanggalMulai = rs.getString(6);
                String kolTanggalSelesai = rs.getString(7);
                String kolKuota = rs.getString(8);
                String data[] = {kolID,kolNamaNarasumber,kolMateri,kolRuangan,koltema,kolTanggalMulai,kolTanggalSelesai,kolKuota};
                dtm.addRow(data);
            }
            System.out.println("getDataSesi() berhasil...");
        } catch (SQLException ex) {
            Logger.getLogger(FormUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
        tbSesi.setModel(dtm);
        getResetTabelPesertaSesi();
    }
    
    // gcSesi
    public void getCariSesi(){
        String kolom[] = {"ID","Narasumber","Materi","Ruangan","Tema","Mulai","Selesai"  ,"Kuota"};
        DefaultTableModel dtm = new DefaultTableModel(null, kolom);
        String SQL = "";
        if(rbCariByIDSesi.isSelected()){
            SQL = "SELECT id_sesi, (SELECT nama_narasumber FROM tb_narasumber n WHERE n.id_narasumber = s.id_narasumber) AS nama_narasumber, (SELECT nama_materi FROM tb_materi m WHERE m.id_materi = s.id_materi) AS nama_materi, (SELECT nama_ruangan FROM tb_ruangan r WHERE r.id_ruangan = s.id_ruangan) AS nama_ruangan, tema, tanggal_mulai, tanggal_selesai, kuota FROM tb_sesi s WHERE s.id_sesi like '%"+tCariSesi.getText()+"%' ORDER BY s.tanggal_mulai DESC";
        }else if(rbCariByTemaSesi.isSelected()){
            SQL = "SELECT id_sesi, (SELECT nama_narasumber FROM tb_narasumber n WHERE n.id_narasumber = s.id_narasumber) AS nama_narasumber, (SELECT nama_materi FROM tb_materi m WHERE m.id_materi = s.id_materi) AS nama_materi, (SELECT nama_ruangan FROM tb_ruangan r WHERE r.id_ruangan = s.id_ruangan) AS nama_ruangan, tema, tanggal_mulai, tanggal_selesai, kuota FROM tb_sesi s WHERE s.tema like '%"+tCariSesi.getText()+"%' ORDER BY s.tanggal_mulai DESC";
        }
        ResultSet rs = Koneksi.executeQuery(SQL);
        try {
            while(rs.next()) {
                String kolID = rs.getString(1);
                String kolNamaNarasumber = rs.getString(2);
                String kolMateri = rs.getString(3);
                String kolRuangan = rs.getString(4);
                String koltema = rs.getString(5);
                String kolTanggalMulai = rs.getString(6);
                String kolTanggalSelesai = rs.getString(7);
                String kolKuota = rs.getString(8);
                String data[] = {kolID,kolNamaNarasumber,kolMateri,kolRuangan,koltema,kolTanggalMulai,kolTanggalSelesai,kolKuota};
                dtm.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
        tbSesi.setModel(dtm);
        bUbahHapusSesi.setEnabled(false);
        getResetTabelPesertaSesi();
    }
    
    // grtPesertaSesi
    public void getResetTabelPesertaSesi(){
        String kolom[] = {"ID Daftar","ID Peserta","Nama Peserta"};
        DefaultTableModel dtm = new DefaultTableModel(null, kolom);
        String data[] = {null};
        dtm.addRow(data);
        tbPesertaSesi.setModel(dtm);
        lJumlahPeserta.setText("0");
    }
    
    // gdPesertaSesi
    public void getDataPesertaSesi(){
        int baris = tbSesi.getSelectedRow();
        if (baris != -1) {
            String idSesi = tbSesi.getValueAt(baris, 0).toString();
            String kolom[] = {"ID Daftar","ID Peserta","Nama Peserta"};
            DefaultTableModel dtm = new DefaultTableModel(null, kolom);
            String SQL = "SELECT id_daftar, id_peserta, (SELECT nama_peserta FROM peserta p WHERE p.id_peserta = s.id_peserta) AS nama_peserta FROM tb_peserta_sesi s WHERE id_sesi = '"+idSesi+"'";
            String SQLCount = "SELECT count(*) FROM tb_peserta_sesi WHERE id_sesi = '"+idSesi+"'";
            ResultSet rs = Koneksi.executeQuery(SQL);
            try {
                while(rs.next()) {
                    String kolIdDaftar = rs.getString(1);
                    String kolIdPeserta = rs.getString(2);
                    String kolNamaPeserta = rs.getString(3);
                    String data[] = {kolIdDaftar,kolIdPeserta,kolNamaPeserta};
                    dtm.addRow(data);
                }
                System.out.println("getDataPesertaSesi() berhasil...");
            } catch (SQLException ex) {
                Logger.getLogger(FormUtama.class.getName()).log(Level.SEVERE, null, ex);
            }
            ResultSet rs2 = Koneksi.executeQuery(SQLCount);
            try {
                if(rs2.next()){
                    lJumlahPeserta.setText(rs2.getString(1));
                }
                System.out.println("getDataPesertaSesiCount() berhasil...");
            } catch (SQLException ex) {
                Logger.getLogger(FormUtama.class.getName()).log(Level.SEVERE, null, ex);
            }
            tbPesertaSesi.setModel(dtm);
        }
    }
    
    // gdRuangan
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
            System.out.println("getDataRuangan() berhasil...");
        } catch (SQLException ex) {
            Logger.getLogger(FormUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
        tbRuangan.setModel(dtm);
        getResetTabelRiwayat();
    }
    
    // gcRuangan
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
        getResetTabelRiwayat();
    }
    
    // grtRiwayat
    public void getResetTabelRiwayat(){
        String kolom[] = {"ID","Nama Narasumber","Nama Materi","Tema","Tanggal Mulai","Tanggal Selesai"  ,"Kuota"};
        DefaultTableModel dtm = new DefaultTableModel(null, kolom);
        String data[] = {null};
        dtm.addRow(data);
        tbRiwayatRuangan.setModel(dtm);
    }
    
    // gdrRuangan
    public void getDataRiwayatRuangan(){
        int baris = tbRuangan.getSelectedRow();
        if (baris != -1) {
            String idRuangan = tbRuangan.getValueAt(baris, 0).toString();
            String kolom[] = {"ID","Nama Narasumber","Nama Materi","Tema","Tanggal Mulai","Tanggal Selesai"  ,"Kuota"};
            DefaultTableModel dtm = new DefaultTableModel(null, kolom);
            String SQL = "SELECT id_sesi, (SELECT nama_narasumber FROM tb_narasumber n WHERE n.id_narasumber = s.id_narasumber) AS nama_narasumber, (SELECT nama_materi FROM tb_materi m WHERE m.id_materi = s.id_materi) AS nama_materi, tema, tanggal_mulai, tanggal_selesai, kuota FROM tb_sesi s WHERE id_ruangan = '"+idRuangan+"'";
            ResultSet rs = Koneksi.executeQuery(SQL);
            try {
                while(rs.next()) {
                    String kolID = rs.getString(1);
                    String kolNamaNarasumber = rs.getString(2);
                    String kolMateri = rs.getString(3);
                    String koltema = rs.getString(4);
                    String kolTanggalMulai = rs.getString(5);
                    String kolTanggalSelesai = rs.getString(6);
                    String kolKuota = rs.getString(7);
                    String data[] = {kolID,kolNamaNarasumber,kolMateri,koltema,kolTanggalMulai,kolTanggalSelesai,kolKuota};
                    dtm.addRow(data);
                }
                System.out.println("getDataRiwayatRuangan() berhasil...");
            } catch (SQLException ex) {
                Logger.getLogger(FormUtama.class.getName()).log(Level.SEVERE, null, ex);
            }
            tbRiwayatRuangan.setModel(dtm);
        }
    }
    
    // gdPeserta
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
            System.out.println("getDataPeserta() berhasil...");
        } catch (SQLException ex) {
            Logger.getLogger(FormUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
        tbPeserta.setModel(dtm);
    }
    
    // gcPeserta
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
        try{
            while(rs.next()){
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
    
    // gdNarasumber
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
            System.out.println("getDataNarasumber() berhasil...");
        } catch (SQLException ex) {
            Logger.getLogger(FormUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
        tbNarasumber.setModel(dtm);
        getResetTabelNarasumber();
    }
    
    // gcNarasumber
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
        getResetTabelNarasumber();
    }
    
    // grtNarasumber
    public void getResetTabelNarasumber(){
        String kolom[] = {"ID","Materi"};
        DefaultTableModel dtm = new DefaultTableModel(null, kolom);
        String data[] = {null};
        dtm.addRow(data);
        tbMateriNarasumber.setModel(dtm);
    }
    
    // gdmDilatih
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
                System.out.println("getDataMateriDilatih() berhasil...");
            } catch (SQLException ex) {
                Logger.getLogger(FormUtama.class.getName()).log(Level.SEVERE, null, ex);
            }
            tbMateriNarasumber.setModel(dtm);
        }
    }
    
    // gdMateri
    public void getDataMateri(){
        String kolom[] = {"ID","Materi"};
        DefaultTableModel dtm = new DefaultTableModel(null, kolom);
        String SQL = "SELECT * FROM tb_materi";
        ResultSet rs = Koneksi.executeQuery(SQL);
        try{
            while(rs.next()){
                String kolID = rs.getString(1);
                String kolNamaMateri = rs.getString(2);
                String data[] = {kolID, kolNamaMateri};
                dtm.addRow(data);
            }
            System.out.println("getDataMateri() berhasil...");
        }catch(SQLException ex){
            Logger.getLogger(FormUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
        tbMateri.setModel(dtm);
    }
    
    // gcMateri
    public void getCariMateri(){
        String kolom[] = {"ID","Materi"};
        DefaultTableModel dtm = new DefaultTableModel(null, kolom);
        String SQL = "";
        if(rbCariByIDMateri.isSelected()){
            SQL = "SELECT * FROM tb_materi WHERE id_materi like '%"+tCariMateri.getText()+"%'";
        }else if(rbCariByNamaMateri.isSelected()){
            SQL = "SELECT * FROM tb_materi WHERE nama_materi like '%"+tCariMateri.getText()+"%'";
        }
        ResultSet rs = Koneksi.executeQuery(SQL);
        try{
            while(rs.next()){
                String kolID = rs.getString(1);
                String kolNamaMateri = rs.getString(2);
                String data[] = {kolID, kolNamaMateri};
                dtm.addRow(data);
            }
        }catch(SQLException ex){
            Logger.getLogger(FormUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
        tbMateri.setModel(dtm);
        bUbahHapusMateri.setEnabled(false);
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
        bgCariSesi = new javax.swing.ButtonGroup();
        pDasar = new javax.swing.JPanel();
        pJudulProgram = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        tpMenuUtama = new javax.swing.JTabbedPane();
        pSesi = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbSesi = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        bTambahSesi = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        tCariSesi = new javax.swing.JTextField();
        bPendaftaran = new javax.swing.JButton();
        bUbahHapusSesi = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbPesertaSesi = new javax.swing.JTable();
        lJumlahPeserta = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        rbCariByIDSesi = new javax.swing.JRadioButton();
        rbCariByTemaSesi = new javax.swing.JRadioButton();
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
        pMenu = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        bMenuSesi = new javax.swing.JButton();
        bMenuRuangan = new javax.swing.JButton();
        bMenuMateri = new javax.swing.JButton();
        bMenuPeserta = new javax.swing.JButton();
        bMenuNarasumber = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        lJam = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Aplikasi Workshop");
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

        pDasar.add(pJudulProgram, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 780, 60));

        tpMenuUtama.setBackground(new java.awt.Color(255, 255, 255));
        tpMenuUtama.setEnabled(false);

        pSesi.setBackground(new java.awt.Color(204, 255, 255));
        pSesi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel13.setBackground(new java.awt.Color(204, 204, 204));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbSesi.setModel(new javax.swing.table.DefaultTableModel(
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
        tbSesi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSesiMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tbSesi);

        jPanel13.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 620, 190));

        jLabel15.setText("Daftar Sesi");
        jPanel13.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 20));

        jLabel16.setText("Tambah Sesi Baru");
        jPanel13.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, -1, 20));

        bTambahSesi.setText("+");
        bTambahSesi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTambahSesiActionPerformed(evt);
            }
        });
        jPanel13.add(bTambahSesi, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, 50, 20));
        jPanel13.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 750, 10));

        jLabel17.setText("Cari Sesi");
        jPanel13.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, -1, 20));

        tCariSesi.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tCariSesiCaretUpdate(evt);
            }
        });
        jPanel13.add(tCariSesi, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 230, -1));

        bPendaftaran.setText("Pendaftaran");
        bPendaftaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPendaftaranActionPerformed(evt);
            }
        });
        jPanel13.add(bPendaftaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 240, 100, 20));

        bUbahHapusSesi.setText("Ubah / Hapus");
        bUbahHapusSesi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUbahHapusSesiActionPerformed(evt);
            }
        });
        jPanel13.add(bUbahHapusSesi, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 240, -1, 20));

        tbPesertaSesi.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane7.setViewportView(tbPesertaSesi);

        jPanel13.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 620, 160));

        lJumlahPeserta.setText("0");
        jPanel13.add(lJumlahPeserta, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 280, 10, 20));

        jLabel19.setText("Daftar Peserta Sesi");
        jPanel13.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, -1, 20));

        jLabel20.setText("Jumlah Peserta : ");
        jPanel13.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 280, -1, 20));

        rbCariByIDSesi.setBackground(new java.awt.Color(204, 204, 204));
        bgCariSesi.add(rbCariByIDSesi);
        rbCariByIDSesi.setSelected(true);
        rbCariByIDSesi.setText("ID");
        jPanel13.add(rbCariByIDSesi, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 240, -1, -1));

        rbCariByTemaSesi.setBackground(new java.awt.Color(204, 204, 204));
        bgCariSesi.add(rbCariByTemaSesi);
        rbCariByTemaSesi.setText("Tema");
        jPanel13.add(rbCariByTemaSesi, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 240, -1, -1));

        pSesi.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 650, 480));

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

        jPanel10.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 620, 160));

        jLabel4.setText("Riwayat Penggunaan Ruangan");
        jPanel10.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, -1, 20));

        rbCariByIDRuangan.setBackground(new java.awt.Color(204, 204, 204));
        bgCariRuangan.add(rbCariByIDRuangan);
        rbCariByIDRuangan.setText("ID");
        jPanel10.add(rbCariByIDRuangan, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 240, -1, -1));

        rbCariByNamaRuangan.setBackground(new java.awt.Color(204, 204, 204));
        bgCariRuangan.add(rbCariByNamaRuangan);
        rbCariByNamaRuangan.setText("Nama");
        jPanel10.add(rbCariByNamaRuangan, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 240, -1, -1));

        pRuangan.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 650, 480));

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

        jPanel3.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 620, 400));

        jLabel8.setText("Cari Peserta");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, -1, 20));

        tCariPeserta.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tCariPesertaCaretUpdate(evt);
            }
        });
        jPanel3.add(tCariPeserta, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 450, 300, -1));

        bUbahHapusPeserta.setText("Ubah / Hapus");
        bUbahHapusPeserta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUbahHapusPesertaActionPerformed(evt);
            }
        });
        jPanel3.add(bUbahHapusPeserta, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 450, 100, 20));

        rbCariByIDPeserta.setBackground(new java.awt.Color(204, 204, 204));
        bgCariPeserta.add(rbCariByIDPeserta);
        rbCariByIDPeserta.setSelected(true);
        rbCariByIDPeserta.setText("ID");
        jPanel3.add(rbCariByIDPeserta, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 450, -1, -1));

        rbCariByNamaPeserta.setBackground(new java.awt.Color(204, 204, 204));
        bgCariPeserta.add(rbCariByNamaPeserta);
        rbCariByNamaPeserta.setText("Nama");
        jPanel3.add(rbCariByNamaPeserta, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 450, -1, -1));

        pPeserta.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 650, 480));

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
        jPanel9.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 620, 10));

        tbMateriNarasumber.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "ID", "Materi"
            }
        ));
        jScrollPane5.setViewportView(tbMateriNarasumber);

        jPanel9.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 620, 160));

        jLabel27.setText("Cari Narasumber");
        jPanel9.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, -1, 20));

        pNarasumber.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 650, 480));

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
        jPanel11.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, -1, 20));

        tCariMateri.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tCariMateriCaretUpdate(evt);
            }
        });
        jPanel11.add(tCariMateri, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 450, 310, -1));

        bUbahHapusMateri.setText("Ubah / Hapus");
        bUbahHapusMateri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUbahHapusMateriActionPerformed(evt);
            }
        });
        jPanel11.add(bUbahHapusMateri, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 450, 100, 20));

        rbCariByIDMateri.setBackground(new java.awt.Color(204, 204, 204));
        bgCariMateri.add(rbCariByIDMateri);
        rbCariByIDMateri.setSelected(true);
        rbCariByIDMateri.setText("ID");
        jPanel11.add(rbCariByIDMateri, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 450, -1, -1));

        rbCariByNamaMateri.setBackground(new java.awt.Color(204, 204, 204));
        bgCariMateri.add(rbCariByNamaMateri);
        rbCariByNamaMateri.setText("Nama");
        jPanel11.add(rbCariByNamaMateri, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 450, -1, -1));

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

        jPanel11.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 620, 400));

        pMateri.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 650, 480));

        tpMenuUtama.addTab("MATERI", pMateri);

        pDasar.add(tpMenuUtama, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 650, 530));

        pMenu.setBackground(new java.awt.Color(51, 51, 51));
        pMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("MENU");
        pMenu.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, -1));

        bMenuSesi.setText("SESI");
        bMenuSesi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bMenuSesiActionPerformed(evt);
            }
        });
        pMenu.add(bMenuSesi, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 110, 20));

        bMenuRuangan.setText("RUANGAN");
        bMenuRuangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bMenuRuanganActionPerformed(evt);
            }
        });
        pMenu.add(bMenuRuangan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 110, 20));

        bMenuMateri.setText("MATERI");
        bMenuMateri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bMenuMateriActionPerformed(evt);
            }
        });
        pMenu.add(bMenuMateri, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 110, 20));

        bMenuPeserta.setText("PESERTA");
        bMenuPeserta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bMenuPesertaActionPerformed(evt);
            }
        });
        pMenu.add(bMenuPeserta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 110, 20));

        bMenuNarasumber.setText("NARASUMBER");
        bMenuNarasumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bMenuNarasumberActionPerformed(evt);
            }
        });
        pMenu.add(bMenuNarasumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 110, 20));

        jButton17.setText("KELUAR");
        pMenu.add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, 110, 20));

        lJam.setForeground(new java.awt.Color(204, 204, 204));
        lJam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lJam.setText("00:00:00");
        pMenu.add(lJam, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, 110, 20));

        pDasar.add(pMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 130, 510));

        getContentPane().add(pDasar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // activeWindows
    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        getDataSesi(); bUbahHapusSesi.setEnabled(false); bPendaftaran.setEnabled(false); bPendaftaran.setText("Pendaftaran");
        getDataRuangan(); bUbahHapusRuangan.setEnabled(false);
        getDataPeserta(); bUbahHapusPeserta.setEnabled(false);
        getDataNarasumber(); bUbahHapusNarasumber.setEnabled(false);
        getDataMateri(); bUbahHapusMateri.setEnabled(false);
    }//GEN-LAST:event_formWindowActivated

    private void bTambahPesertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTambahPesertaActionPerformed
        FormTambahPeserta tp = new FormTambahPeserta();
        tp.setVisible(true);
        tp.roleButtonPeserta("simpan");
    }//GEN-LAST:event_bTambahPesertaActionPerformed

    private void bUbahHapusPesertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUbahHapusPesertaActionPerformed
        int baris = tbPeserta.getSelectedRow();
        FormTambahPeserta tp = new FormTambahPeserta();
        tp.setVisible(true);
        tp.roleButtonPeserta("ubahhapus");
        try {
            tp.getTextFieldPeserta(tbPeserta.getValueAt(baris, 0).toString());
        } catch (ParseException ex) {
            Logger.getLogger(FormUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        tp.roleButtonNarasumber("simpan");
    }//GEN-LAST:event_bTambahNarasumberActionPerformed

    private void tCariNarasumberCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tCariNarasumberCaretUpdate
        getCariNarasumber();
    }//GEN-LAST:event_tCariNarasumberCaretUpdate

    private void bUbahHapusNarasumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUbahHapusNarasumberActionPerformed
        int baris = tbNarasumber.getSelectedRow();
        FormTambahNarasumber tp = new FormTambahNarasumber();
        tp.setVisible(true);
        tp.roleButtonNarasumber("ubahhapus");
        tp.getTextFieldNarasumber(tbNarasumber.getValueAt(baris, 0).toString());
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
        tm.roleButtonMateri("simpan");
    }//GEN-LAST:event_bTambahMateriActionPerformed

    private void tCariMateriCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tCariMateriCaretUpdate
        getCariMateri();
    }//GEN-LAST:event_tCariMateriCaretUpdate

    private void bUbahHapusMateriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUbahHapusMateriActionPerformed
        int baris = tbMateri.getSelectedRow();
        FormTambahMateri tm = new FormTambahMateri();
        tm.setVisible(true);
        tm.roleButtonMateri("ubahhapus");
        tm.getTextFieldMateri(tbMateri.getValueAt(baris, 0).toString());
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
        tr.roleButtonRuangan("simpan");
    }//GEN-LAST:event_bTambahRuanganActionPerformed

    private void bUbahHapusRuanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUbahHapusRuanganActionPerformed
        int baris = tbRuangan.getSelectedRow();
        FormTambahRuangan tr = new FormTambahRuangan();
        tr.setVisible(true);
        tr.roleButtonRuangan("ubahhapus");
        tr.getTextFieldRuangan(tbRuangan.getValueAt(baris, 0).toString());
    }//GEN-LAST:event_bUbahHapusRuanganActionPerformed

    private void tbRuanganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRuanganMouseClicked
        int baris = tbRuangan.getSelectedRow();
        if (baris != -1) {
            bUbahHapusRuangan.setEnabled(true);
            getDataRiwayatRuangan();
        }
    }//GEN-LAST:event_tbRuanganMouseClicked

    private void tCariRuanganCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tCariRuanganCaretUpdate
        getCariRuangan();
    }//GEN-LAST:event_tCariRuanganCaretUpdate

    private void bMenuSesiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bMenuSesiActionPerformed
         tpMenuUtama.setSelectedIndex(0);
    }//GEN-LAST:event_bMenuSesiActionPerformed

    private void bMenuRuanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bMenuRuanganActionPerformed
        tpMenuUtama.setSelectedIndex(1);
    }//GEN-LAST:event_bMenuRuanganActionPerformed

    private void bMenuMateriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bMenuMateriActionPerformed
        tpMenuUtama.setSelectedIndex(4);
    }//GEN-LAST:event_bMenuMateriActionPerformed

    private void bMenuPesertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bMenuPesertaActionPerformed
        tpMenuUtama.setSelectedIndex(2);
    }//GEN-LAST:event_bMenuPesertaActionPerformed

    private void bMenuNarasumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bMenuNarasumberActionPerformed
        tpMenuUtama.setSelectedIndex(3);
    }//GEN-LAST:event_bMenuNarasumberActionPerformed

    private void bTambahSesiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTambahSesiActionPerformed
        FormTambahSesi fts = new FormTambahSesi();
        fts.setVisible(true);
        fts.getDataRuangan();
        fts.roleButtonSesi("simpan");
    }//GEN-LAST:event_bTambahSesiActionPerformed

    private void bPendaftaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPendaftaranActionPerformed
        FormPendaftaran fp = new FormPendaftaran();
        int baris = tbSesi.getSelectedRow();
        if (baris != -1) {
            fp.setVisible(true);
            fp.getDataSesi(tbSesi.getValueAt(baris, 0).toString());
            fp.getHideLabel();
        }
    }//GEN-LAST:event_bPendaftaranActionPerformed

    private void tbSesiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSesiMouseClicked
        int baris = tbSesi.getSelectedRow();
        if (baris != -1) {
            getDataPesertaSesi();
            try{
                Date tglMulai = sdf.parse(tbSesi.getValueAt(baris, 5).toString());
                Date tglSelesai = sdf.parse(tbSesi.getValueAt(baris, 6).toString());
                String x = sdf.format(new Date());
                Date sekarang = sdf.parse(x);
                int kuotaSesi = Integer.valueOf(tbSesi.getValueAt(baris, 7).toString());
                int pesertaIkut = Integer.valueOf(lJumlahPeserta.getText());
                //**************************************************************
                if(tglMulai.compareTo(sekarang) < 0 && tglSelesai.compareTo(sekarang) < 0){
                    bUbahHapusSesi.setEnabled(false);
                    bPendaftaran.setEnabled(false);
                    bPendaftaran.setText("Tutup");
                }else if(kuotaSesi <= pesertaIkut){
                    bUbahHapusSesi.setEnabled(true);
                    bPendaftaran.setEnabled(false);
                    bPendaftaran.setText("Penuh");
                }else{
                    bUbahHapusSesi.setEnabled(true);
                    bPendaftaran.setEnabled(true);
                    bPendaftaran.setText("Pendaftaran");
                }
            }catch(ParseException ex){
                Logger.getLogger(FormUtama.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tbSesiMouseClicked

    private void tCariSesiCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tCariSesiCaretUpdate
        getCariSesi();
    }//GEN-LAST:event_tCariSesiCaretUpdate

    private void bUbahHapusSesiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUbahHapusSesiActionPerformed
        int baris = tbSesi.getSelectedRow();
        FormTambahSesi fts = new FormTambahSesi();
        fts.setVisible(true);
        fts.getComboNarasumber();
        fts.getDataRuangan();
        fts.roleButtonSesi("ubahhapus");
        try {
            fts.getTextFieldSesi(tbSesi.getValueAt(baris, 0).toString());
        } catch (ParseException ex) {
            Logger.getLogger(FormUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bUbahHapusSesiActionPerformed

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
    private javax.swing.JButton bMenuMateri;
    private javax.swing.JButton bMenuNarasumber;
    private javax.swing.JButton bMenuPeserta;
    private javax.swing.JButton bMenuRuangan;
    private javax.swing.JButton bMenuSesi;
    private javax.swing.JButton bPendaftaran;
    private javax.swing.JButton bTambahMateri;
    private javax.swing.JButton bTambahNarasumber;
    private javax.swing.JButton bTambahPeserta;
    private javax.swing.JButton bTambahRuangan;
    private javax.swing.JButton bTambahSesi;
    private javax.swing.JButton bUbahHapusMateri;
    private javax.swing.JButton bUbahHapusNarasumber;
    private javax.swing.JButton bUbahHapusPeserta;
    private javax.swing.JButton bUbahHapusRuangan;
    private javax.swing.JButton bUbahHapusSesi;
    private javax.swing.ButtonGroup bgCariMateri;
    private javax.swing.ButtonGroup bgCariNarasumber;
    private javax.swing.ButtonGroup bgCariPeserta;
    private javax.swing.ButtonGroup bgCariRuangan;
    private javax.swing.ButtonGroup bgCariSesi;
    private javax.swing.JButton jButton17;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JLabel lJam;
    private javax.swing.JLabel lJumlahPeserta;
    private javax.swing.JPanel pDasar;
    private javax.swing.JPanel pJudulProgram;
    private javax.swing.JPanel pMateri;
    private javax.swing.JPanel pMenu;
    private javax.swing.JPanel pNarasumber;
    private javax.swing.JPanel pPeserta;
    private javax.swing.JPanel pRuangan;
    private javax.swing.JPanel pSesi;
    private javax.swing.JRadioButton rbCariByIDMateri;
    private javax.swing.JRadioButton rbCariByIDNarasumber;
    private javax.swing.JRadioButton rbCariByIDPeserta;
    private javax.swing.JRadioButton rbCariByIDRuangan;
    private javax.swing.JRadioButton rbCariByIDSesi;
    private javax.swing.JRadioButton rbCariByNamaMateri;
    private javax.swing.JRadioButton rbCariByNamaNarasumber;
    private javax.swing.JRadioButton rbCariByNamaPeserta;
    private javax.swing.JRadioButton rbCariByNamaRuangan;
    private javax.swing.JRadioButton rbCariByTemaSesi;
    private javax.swing.JTextField tCariMateri;
    private javax.swing.JTextField tCariNarasumber;
    private javax.swing.JTextField tCariPeserta;
    private javax.swing.JTextField tCariRuangan;
    private javax.swing.JTextField tCariSesi;
    private javax.swing.JTable tbMateri;
    private javax.swing.JTable tbMateriNarasumber;
    private javax.swing.JTable tbNarasumber;
    private javax.swing.JTable tbPeserta;
    private javax.swing.JTable tbPesertaSesi;
    private javax.swing.JTable tbRiwayatRuangan;
    private javax.swing.JTable tbRuangan;
    private javax.swing.JTable tbSesi;
    private javax.swing.JTabbedPane tpMenuUtama;
    // End of variables declaration//GEN-END:variables
}
