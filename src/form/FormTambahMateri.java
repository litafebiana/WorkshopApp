/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import workshopapp.Koneksi;

/**
 *
 * @author Sultan
 */
public class FormTambahMateri extends javax.swing.JFrame{
    
    /**
     * Creates new form FormTambahMateri
     */
    public FormTambahMateri(){
        initComponents();
        lIdMateri.setVisible(false);
    }

    // rbMateri
    public void roleButtonMateri(String role){
        if (role.equals("simpan")){
            bSimpanMateri.setEnabled(true);
            bUbahMateri.setEnabled(false);
            bHapusMateri.setEnabled(false);
            lJudulMateri.setText("MATERI BARU");
            this.setTitle("Tambah Materi Baru");
        }else if (role.equals("ubahhapus")){
            bSimpanMateri.setEnabled(false);
            bUbahMateri.setEnabled(true);
            bHapusMateri.setEnabled(true);
            lJudulMateri.setText("UBAH/HAPUS MATERI");
            this.setTitle("Ubah/Hapus Materi");
        }
    }
    
    //giMateri
    public int genIdMateri() {
        Random r = new Random(System.currentTimeMillis());
        int number = 10000;
        for(int counter=1; counter<=1;counter++){
            number = 10000+r.nextInt(60000);
        }
        return number;
    }
    
    // gtfMateri
    public void getTextFieldMateri(String id){
        String SQL = "SELECT * FROM tb_materi where id_materi = '"+id+"'";
        ResultSet rs = Koneksi.executeQuery(SQL);
        try {
            while(rs.next()) {
                lIdMateri.setText(rs.getString(1));
                tNamaMateri.setText(rs.getString(2));
            }
            System.out.println("getTextFieldMateri() berhasil...");
        } catch (SQLException ex) {
            Logger.getLogger(FormUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //gsMateri
    public void getSimpanMateri(){
        if ("".equals(tNamaMateri.getText())) {
            JOptionPane.showMessageDialog(this, "Harap lengkapi data !", "Peringatan", JOptionPane.WARNING_MESSAGE);
        } else {
            String SQL = "INSERT INTO tb_materi (id_materi, nama_materi) "
                    + "VALUES('MAT-2018"+genIdMateri()+"','"+tNamaMateri.getText()+"')";
            int status = Koneksi.execute(SQL);
            if (status == 1) {
                JOptionPane.showMessageDialog(this, "Materi baru berhasil ditambahkan", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("getSimpanMateri() berhasil...");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Materi gagal ditambahkan", "Gagal", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    // guMateri
    public void getUbahMateri(){
        if ("".equals(tNamaMateri.getText())) {
            JOptionPane.showMessageDialog(this, "Harap lengkapi data !", "Peringatan", JOptionPane.WARNING_MESSAGE);
        } else {
            String SQL = "UPDATE tb_materi SET "
                    + "nama_materi = '"+tNamaMateri.getText()+"' "
                    + "WHERE id_materi='"+lIdMateri.getText()+"'";
            int status = Koneksi.execute(SQL);
            if (status == 1) {
                JOptionPane.showMessageDialog(this, "Materi berhasil diubah", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("getUbahMateri() berhasil...");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Materi gagal diubah", "Gagal", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    //ghMateri
    public void getHapusMateri(){
        int reply = JOptionPane.showConfirmDialog(this, "Apakah anda yakin akan menghapus data Materi '"+tNamaMateri.getText()+"'", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            String SQL = "DELETE FROM tb_materi WHERE id_materi = '"+lIdMateri.getText()+"'";
            int status = Koneksi.execute(SQL);
            if (status == 1) {
                JOptionPane.showMessageDialog(this, "Materi '"+tNamaMateri.getText()+"' berhasil dihapus", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("getHapusMateri() berhasil...");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Materi '"+tNamaMateri.getText()+"' gagal dihapus", "Gagal", JOptionPane.ERROR_MESSAGE);
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

        jPanel3 = new javax.swing.JPanel();
        lJudulMateri = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tNamaMateri = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        bSimpanMateri = new javax.swing.JButton();
        bUbahMateri = new javax.swing.JButton();
        bHapusMateri = new javax.swing.JButton();
        lIdMateri = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lJudulMateri.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lJudulMateri.setText("JUDUL");
        jPanel3.add(lJudulMateri, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 350, 50));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 70));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Nama Materi");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));
        jPanel1.add(tNamaMateri, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 310, -1));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bSimpanMateri.setText("Simpan");
        bSimpanMateri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSimpanMateriActionPerformed(evt);
            }
        });
        jPanel2.add(bSimpanMateri, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, 20));

        bUbahMateri.setText("Ubah");
        bUbahMateri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUbahMateriActionPerformed(evt);
            }
        });
        jPanel2.add(bUbahMateri, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 90, 20));

        bHapusMateri.setText("Hapus");
        bHapusMateri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bHapusMateriActionPerformed(evt);
            }
        });
        jPanel2.add(bHapusMateri, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 90, 20));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 310, 40));

        lIdMateri.setText("id_materi");
        jPanel1.add(lIdMateri, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 430, 90));

        setSize(new java.awt.Dimension(446, 199));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bSimpanMateriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSimpanMateriActionPerformed
        getSimpanMateri();
    }//GEN-LAST:event_bSimpanMateriActionPerformed

    private void bUbahMateriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUbahMateriActionPerformed
        getUbahMateri();
    }//GEN-LAST:event_bUbahMateriActionPerformed

    private void bHapusMateriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bHapusMateriActionPerformed
        getHapusMateri();
    }//GEN-LAST:event_bHapusMateriActionPerformed

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
            java.util.logging.Logger.getLogger(FormTambahMateri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormTambahMateri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormTambahMateri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormTambahMateri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormTambahMateri().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bHapusMateri;
    private javax.swing.JButton bSimpanMateri;
    private javax.swing.JButton bUbahMateri;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lIdMateri;
    private javax.swing.JLabel lJudulMateri;
    private javax.swing.JTextField tNamaMateri;
    // End of variables declaration//GEN-END:variables
}
