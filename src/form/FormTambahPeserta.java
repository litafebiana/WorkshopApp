/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import workshopapp.Koneksi;

/**
 *
 * @author Sultan
 */
public class FormTambahPeserta extends javax.swing.JFrame {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
    /**
     * Creates new form FormTambahPeserta
     */
    public FormTambahPeserta() {
        initComponents();
        lIdPeserta.setVisible(false);
    }

    // rbPeserta
    public void roleButtonPeserta(String role){
        if (role.equals("simpan")){
            bSimpanPeserta.setEnabled(true);
            bUbahPeserta.setEnabled(false);
            bHapusPeserta.setEnabled(false);
            lJudulPeserta.setText("PESERTA BARU");
            this.setTitle("Tambah Peserta Baru");
        }else if (role.equals("ubahhapus")){
            bSimpanPeserta.setEnabled(false);
            bUbahPeserta.setEnabled(true);
            bHapusPeserta.setEnabled(true);
            lJudulPeserta.setText("UBAH/HAPUS PESERTA");
            this.setTitle("Ubah/Hapus Peserta");
        }
    }
    
    // giPeserta
    public int genIdPeserta() {
        Random r = new Random(System.currentTimeMillis());
        int number = 30000;
        for(int counter=1; counter<=1;counter++){
            number = 30000+r.nextInt(60000);
        }
        return number;
    }
    
    //gtfPeserta
    public void getTextFieldPeserta(String id) throws ParseException{
        String SQL = "SELECT id_peserta, nama_peserta, no_telp, jenis_kelamin, tempat_lahir, tanggal_lahir, email, alamat FROM peserta where id_peserta = '"+id+"'";
        ResultSet rs = Koneksi.executeQuery(SQL);
        try {
            while(rs.next()) {
                if(rs.getString(4).equals("Perempuan")){
                    rbPerempuan.setSelected(true);
                }else{
                    rbLakiLaki.setSelected(true);
                }
                lIdPeserta.setText(rs.getString(1));
                tNamaPeserta.setText(rs.getString(2));
                tNoTelpPeserta.setText(rs.getString(3));
                tTempatLahir.setText(rs.getString(5));
                dcTanggal.setDate(sdf.parse(rs.getString(6)));
                tEmailPeserta.setText(rs.getString(7));
                taAlamatPeserta.setText(rs.getString(8));
            }
            System.out.println("getTextFieldPeserta() berhasil...");
        } catch (SQLException ex) {
            Logger.getLogger(FormUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // gsPeserta
    public void getSimpanPeserta(){
        String jenisKelamin = "jeniskelamin";
        if (rbLakiLaki.isSelected()){
            jenisKelamin = "Laki-Laki";
        }else if (rbPerempuan.isSelected()){
            jenisKelamin = "Perempuan";
        }
        
        if("".equals(tNamaPeserta.getText()) || "".equals(tNoTelpPeserta.getText()) || "".equals(tTempatLahir.getText()) || "".equals(dcTanggal.getDate()) || "".equals(tEmailPeserta.getText()) || "".equals(taAlamatPeserta.getText()) || jenisKelamin.equals("jeniskelamin")) {
            JOptionPane.showMessageDialog(this, "Harap lengkapi data !", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }else{
            String SQL = "INSERT INTO peserta (id_peserta, nama_peserta, no_telp, jenis_kelamin, tempat_lahir, tanggal_lahir, email, alamat) "
                    + "VALUES('PES-2018"+genIdPeserta()+"','"+tNamaPeserta.getText()+"','"+tNoTelpPeserta.getText()+"','"+jenisKelamin+"','"+tTempatLahir.getText()+"','"+sdf.format(dcTanggal.getDate())+"','"+tEmailPeserta.getText()+"','"+taAlamatPeserta.getText()+"')";
            int status = Koneksi.execute(SQL);
            if (status == 1){
                JOptionPane.showMessageDialog(this, "Peserta baru berhasil ditambahkan", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("getSimpanPeserta() berhasil...");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Peserta gagal ditambahkan", "Gagal", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    // guPeserta
    public void getUbahPeserta(){
        String jenisKelamin = "";
        if (rbLakiLaki.isSelected()){
            jenisKelamin = "Laki-Laki";
        }else if (rbPerempuan.isSelected()){
            jenisKelamin = "Perempuan";
        }
        
        if ("".equals(tNamaPeserta.getText()) || "".equals(tNoTelpPeserta.getText()) || "".equals(tTempatLahir.getText()) || "".equals(dcTanggal.getDate()) || "".equals(tEmailPeserta.getText()) || "".equals(taAlamatPeserta.getText()) || jenisKelamin.equals("jeniskelamin")) {
            JOptionPane.showMessageDialog(this, "Harap lengkapi data !", "Peringatan", JOptionPane.WARNING_MESSAGE);
        } else {
            String SQL = "UPDATE peserta SET "
                    + "nama_peserta = '"+tNamaPeserta.getText()+"',"
                    + "no_telp = '"+tNoTelpPeserta.getText()+"',"
                    + "jenis_kelamin = '"+jenisKelamin+"',"
                    + "tempat_lahir = '"+tTempatLahir.getText()+"',"
                    + "tanggal_lahir = '"+sdf.format(dcTanggal.getDate())+"',"
                    + "email = '"+tEmailPeserta.getText()+"',"
                    + "alamat = '"+taAlamatPeserta.getText()+"' "
                    + "WHERE id_peserta='"+lIdPeserta.getText()+"'";
            int status = Koneksi.execute(SQL);
            if (status == 1) {
                JOptionPane.showMessageDialog(this, "Peserta berhasil diubah", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("getUbahPeserta() berhasil...");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Peserta gagal diubah", "Gagal", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    // ghPeserta
    public void getHapusPeserta(){
        int reply = JOptionPane.showConfirmDialog(this, "Apakah anda yakin akan menghapus data Peserta '"+tNamaPeserta.getText()+"'", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            String SQL = "DELETE FROM peserta WHERE id_peserta = '"+lIdPeserta.getText()+"'";
            int status = Koneksi.execute(SQL);
            if (status == 1) {
                JOptionPane.showMessageDialog(this, "Peserta '"+tNamaPeserta.getText()+"' berhasil dihapus", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("getHapusPeserta() berhasil...");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Peserta '"+tNamaPeserta.getText()+"' gagal dihapus", "Gagal", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Hmmm.!!! -___-");
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rbJenisKelamin = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tNamaPeserta = new javax.swing.JTextField();
        tNoTelpPeserta = new javax.swing.JTextField();
        tTempatLahir = new javax.swing.JTextField();
        tEmailPeserta = new javax.swing.JTextField();
        rbLakiLaki = new javax.swing.JRadioButton();
        rbPerempuan = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        taAlamatPeserta = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        bSimpanPeserta = new javax.swing.JButton();
        bUbahPeserta = new javax.swing.JButton();
        bHapusPeserta = new javax.swing.JButton();
        lIdPeserta = new javax.swing.JLabel();
        dcTanggal = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        lJudulPeserta = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Peserta Baru");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Nama");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel2.setText("No. Telepon");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jLabel3.setText("Jenis Kelamin");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jLabel4.setText("Tempat Lahir");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        jLabel5.setText("Tanggal Lahir");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jLabel6.setText("Email");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        jLabel7.setText("Alamat");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));
        jPanel1.add(tNamaPeserta, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 290, -1));
        jPanel1.add(tNoTelpPeserta, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 290, -1));
        jPanel1.add(tTempatLahir, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 290, -1));
        jPanel1.add(tEmailPeserta, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 290, -1));

        rbLakiLaki.setBackground(new java.awt.Color(204, 204, 204));
        rbJenisKelamin.add(rbLakiLaki);
        rbLakiLaki.setSelected(true);
        rbLakiLaki.setText("Laki-Laki");
        jPanel1.add(rbLakiLaki, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 70, -1));

        rbPerempuan.setBackground(new java.awt.Color(204, 204, 204));
        rbJenisKelamin.add(rbPerempuan);
        rbPerempuan.setText("Perempuan");
        jPanel1.add(rbPerempuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, 90, -1));

        taAlamatPeserta.setColumns(20);
        taAlamatPeserta.setRows(5);
        jScrollPane1.setViewportView(taAlamatPeserta);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 170, 100));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bSimpanPeserta.setText("Simpan");
        bSimpanPeserta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSimpanPesertaActionPerformed(evt);
            }
        });
        jPanel2.add(bSimpanPeserta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, 20));

        bUbahPeserta.setText("Ubah");
        bUbahPeserta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUbahPesertaActionPerformed(evt);
            }
        });
        jPanel2.add(bUbahPeserta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 90, 20));

        bHapusPeserta.setText("Hapus");
        bHapusPeserta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bHapusPesertaActionPerformed(evt);
            }
        });
        jPanel2.add(bHapusPeserta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 90, 20));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 190, 110, 100));

        lIdPeserta.setText("id_peserta");
        jPanel1.add(lIdPeserta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, -1, -1));
        jPanel1.add(dcTanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 170, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 430, 300));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lJudulPeserta.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lJudulPeserta.setText("JUDUL");
        jPanel3.add(lJudulPeserta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 350, 50));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 70));

        setSize(new java.awt.Dimension(445, 409));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bSimpanPesertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSimpanPesertaActionPerformed
        getSimpanPeserta();
    }//GEN-LAST:event_bSimpanPesertaActionPerformed

    private void bUbahPesertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUbahPesertaActionPerformed
        getUbahPeserta();
    }//GEN-LAST:event_bUbahPesertaActionPerformed

    private void bHapusPesertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bHapusPesertaActionPerformed
        getHapusPeserta();
    }//GEN-LAST:event_bHapusPesertaActionPerformed

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
            java.util.logging.Logger.getLogger(FormTambahPeserta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormTambahPeserta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormTambahPeserta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormTambahPeserta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormTambahPeserta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bHapusPeserta;
    private javax.swing.JButton bSimpanPeserta;
    private javax.swing.JButton bUbahPeserta;
    private com.toedter.calendar.JDateChooser dcTanggal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lIdPeserta;
    private javax.swing.JLabel lJudulPeserta;
    private javax.swing.ButtonGroup rbJenisKelamin;
    private javax.swing.JRadioButton rbLakiLaki;
    private javax.swing.JRadioButton rbPerempuan;
    private javax.swing.JTextField tEmailPeserta;
    private javax.swing.JTextField tNamaPeserta;
    private javax.swing.JTextField tNoTelpPeserta;
    private javax.swing.JTextField tTempatLahir;
    private javax.swing.JTextArea taAlamatPeserta;
    // End of variables declaration//GEN-END:variables
}
