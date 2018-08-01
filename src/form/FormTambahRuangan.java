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
public class FormTambahRuangan extends javax.swing.JFrame {

    /**
     * Creates new form FormTambahRuangan
     */
    public FormTambahRuangan() {
        initComponents();
        lIdRuangan.setVisible(false);
    }

    // rbRuangan
    public void roleButtonRuangan(String role){
        if (role.equals("simpan")){
            bSimpanRuangan.setEnabled(true);
            bUbahRuangan.setEnabled(false);
            bHapusRuangan.setEnabled(false);
            lJudulRuangan.setText("RUANGAN BARU");
            this.setTitle("Tambah Ruangan Baru");
        }else if (role.equals("ubahhapus")){
            bSimpanRuangan.setEnabled(false);
            bUbahRuangan.setEnabled(true);
            bHapusRuangan.setEnabled(true);
            lJudulRuangan.setText("UBAH/HAPUS RUANGAN");
            this.setTitle("Ubah/Hapus Ruangan");
        }
    }
    
    // giRuangan
    public int genIdRuangan() {
        Random r = new Random(System.currentTimeMillis());
        int number = 100;
        for(int counter=1; counter<=1;counter++){
            number = 100+r.nextInt(600);
        }
        return number;
    }
    
    //gtfRuangan
    public void getTextFieldRuangan(String id){
        String SQL = "SELECT * FROM tb_ruangan where id_ruangan = '"+id+"'";
        ResultSet rs = Koneksi.executeQuery(SQL);
        try {
            while(rs.next()) {
                lIdRuangan.setText(rs.getString(1));
                tNamaRuangan.setText(rs.getString(2));
                tKapasitas.setText(rs.getString(3));
            }
            System.out.println("getTextFieldRuangan() berhasil...");
        } catch (SQLException ex) {
            Logger.getLogger(FormTambahRuangan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // gsRuangan
    public void getSimpanRuangan(){
        if ("".equals(tNamaRuangan.getText()) || "".equals(tKapasitas.getText())) {
            JOptionPane.showMessageDialog(this, "Harap lengkapi data !", "Peringatan", JOptionPane.WARNING_MESSAGE);
        } else {
            String SQL = "INSERT INTO tb_ruangan (id_ruangan, nama_ruangan, kapasitas) "
                    + "VALUES('RUANG-2018"+genIdRuangan()+"','"+tNamaRuangan.getText()+"','"+tKapasitas.getText()+"')";
            int status = Koneksi.execute(SQL);
            if (status == 1) {
                JOptionPane.showMessageDialog(this, "Ruangan baru berhasil ditambahkan", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("getSimpanRuangan() berhasil...");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Ruangan gagal ditambahkan", "Gagal", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    // guRuangan
    public void getUbahRuangan(){
        if ("".equals(tNamaRuangan.getText()) || "".equals(tKapasitas.getText())) {
            JOptionPane.showMessageDialog(this, "Harap lengkapi data !", "Peringatan", JOptionPane.WARNING_MESSAGE);
        } else {
            String SQL = "UPDATE tb_ruangan SET "
                    + "nama_ruangan = '"+tNamaRuangan.getText()+"',"
                    + "kapasitas = '"+tKapasitas.getText()+"' "
                    + "WHERE id_ruangan='"+lIdRuangan.getText()+"'";
            int status = Koneksi.execute(SQL);
            if (status == 1) {
                JOptionPane.showMessageDialog(this, "Ruangan berhasil diubah", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("getUbahRuangan() berhasil...");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Ruangan gagal diubah", "Gagal", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    // ghRuangan
    public void getHapusRuangan(){
        int reply = JOptionPane.showConfirmDialog(this, "Apakah anda yakin akan menghapus data Ruangan '"+tNamaRuangan.getText()+"'", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            String SQL = "DELETE FROM tb_ruangan WHERE id_ruangan = '"+lIdRuangan.getText()+"'";
            int status = Koneksi.execute(SQL);
            if (status == 1) {
                JOptionPane.showMessageDialog(this, "Ruangan '"+tNamaRuangan.getText()+"' berhasil dihapus", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("getHapusRuangan() berhasil...");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Ruangan '"+tNamaRuangan.getText()+"' gagal dihapus", "Gagal", JOptionPane.ERROR_MESSAGE);
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
        lJudulRuangan = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tNamaRuangan = new javax.swing.JTextField();
        tKapasitas = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        bSimpanRuangan = new javax.swing.JButton();
        bUbahRuangan = new javax.swing.JButton();
        bHapusRuangan = new javax.swing.JButton();
        lIdRuangan = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lJudulRuangan.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lJudulRuangan.setText("JUDUL");
        jPanel3.add(lJudulRuangan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 350, 50));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 70));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Kapasitas");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));
        jPanel1.add(tNamaRuangan, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 310, -1));
        jPanel1.add(tKapasitas, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 310, -1));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bSimpanRuangan.setText("Simpan");
        bSimpanRuangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSimpanRuanganActionPerformed(evt);
            }
        });
        jPanel2.add(bSimpanRuangan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, 20));

        bUbahRuangan.setText("Ubah");
        bUbahRuangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUbahRuanganActionPerformed(evt);
            }
        });
        jPanel2.add(bUbahRuangan, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 90, 20));

        bHapusRuangan.setText("Hapus");
        bHapusRuangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bHapusRuanganActionPerformed(evt);
            }
        });
        jPanel2.add(bHapusRuangan, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 90, 20));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 310, 40));

        lIdRuangan.setText("id_ruangan");
        jPanel1.add(lIdRuangan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        jLabel3.setText("Nama Ruangan");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 430, 120));

        setSize(new java.awt.Dimension(447, 229));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bSimpanRuanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSimpanRuanganActionPerformed
        getSimpanRuangan();
    }//GEN-LAST:event_bSimpanRuanganActionPerformed

    private void bUbahRuanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUbahRuanganActionPerformed
        getUbahRuangan();
    }//GEN-LAST:event_bUbahRuanganActionPerformed

    private void bHapusRuanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bHapusRuanganActionPerformed
        getHapusRuangan();
    }//GEN-LAST:event_bHapusRuanganActionPerformed

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
            java.util.logging.Logger.getLogger(FormTambahRuangan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormTambahRuangan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormTambahRuangan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormTambahRuangan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormTambahRuangan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bHapusRuangan;
    private javax.swing.JButton bSimpanRuangan;
    private javax.swing.JButton bUbahRuangan;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lIdRuangan;
    private javax.swing.JLabel lJudulRuangan;
    private javax.swing.JTextField tKapasitas;
    private javax.swing.JTextField tNamaRuangan;
    // End of variables declaration//GEN-END:variables
}
