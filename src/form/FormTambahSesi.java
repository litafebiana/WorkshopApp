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
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import workshopapp.Koneksi;

/**
 *
 * @author Sultan
 */
public class FormTambahSesi extends javax.swing.JFrame {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * Creates new form FormTambahSesi
     */
    public FormTambahSesi() {
        initComponents();
    }

    public int genID() {
        Random r = new Random(System.currentTimeMillis());
        int number = 1000;
        for(int counter=1; counter<=1;counter++){
            number = 1000+r.nextInt(6000);
        }
        return number;
    }
    
    public void getComboNarasumber(){
        String SQL = "SELECT nama_narasumber FROM tb_narasumber";
        ResultSet rs = Koneksi.executeQuery(SQL);
        cbNarasumberSesi.removeAllItems();
        cbNarasumberSesi.addItem("-Pilih-");
        try {
            while(rs.next()) {
                cbNarasumberSesi.addItem(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormTambahSesi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getComboMateri(){
        String SQL = "SELECT m.nama_materi FROM tb_materi m JOIN tb_narasumber_materi nm ON nm.id_materi = m.id_materi WHERE nm.id_narasumber = (SELECT id_narasumber FROM tb_narasumber WHERE nama_narasumber = '"+cbNarasumberSesi.getSelectedItem()+"')";
        ResultSet rs = Koneksi.executeQuery(SQL);
        cbMateriSesi.removeAllItems();
            try {
                while(rs.next()) {
                    cbMateriSesi.addItem(rs.getString(1));
                }
            } catch (SQLException ex) {
                Logger.getLogger(FormTambahSesi.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void getDataRuangan(){
        String kolom[] = {"ID","Nama Ruangan","Kapasitas","Status"};
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
            Logger.getLogger(FormTambahSesi.class.getName()).log(Level.SEVERE, null, ex);
        }
        tbCekRuangan.setModel(dtm);
    }
    
    public void getSimpanSesi(){
        int baris = tbCekRuangan.getSelectedRow();
        //***************************************************
        int lama = Integer.parseInt(tLamaSesi.getText()) - 1;
        Calendar c = Calendar.getInstance();
        c.setTime(dcTanggalMulai.getDate());
        c.add(Calendar.DATE, lama);
        Date newDate = c.getTime();
        //***************************************************
        if ("".equals(tTemaSesi.getText()) || "".equals(dcTanggalMulai.getDate()) || "".equals(tLamaSesi.getText()) || "".equals(tKuotaSesi.getText())) {
            JOptionPane.showMessageDialog(this, "Harap Lengkapi Data", "Error", JOptionPane.WARNING_MESSAGE);
        } else {
            String SQL = "INSERT INTO tb_sesi (id_sesi, id_narasumber, id_materi, id_ruangan, tema,  tanggal_mulai, tanggal_selesai, kuota) VALUES ("
                    + "'SESI-2018"+genID()+"',"
                    + "(SELECT id_narasumber FROM tb_narasumber WHERE nama_narasumber = '"+cbNarasumberSesi.getSelectedItem()+"'),"
                    + "(SELECT id_materi FROM tb_materi WHERE nama_materi = '"+cbMateriSesi.getSelectedItem()+"'),"
                    + "'"+tbCekRuangan.getValueAt(baris, 0).toString()+"',"
                    + "'"+tTemaSesi.getText()+"',"
                    + "'"+String.valueOf(sdf.format(dcTanggalMulai.getDate()))+"',"
                    + "'"+String.valueOf(sdf.format(newDate))+"',"
                    + "'"+tKuotaSesi.getText()+"'"
                    + ");";
            int status = Koneksi.execute(SQL);
            if (status == 1) {
                JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                FormUtama fu = new FormUtama();
                fu.getDataRuangan();
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Data gagal ditambahkan", "Sukses", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    public void getCekRuangan(){
        
        int lama = Integer.parseInt(tLamaSesi.getText()) - 1;
        Calendar c = Calendar.getInstance();
        c.setTime(dcTanggalMulai.getDate());
        c.add(Calendar.DATE, lama);
        Date newDate = c.getTime();
        
        String kolom[] = {"ID","Nama Ruangan","Kapasitas"};
        DefaultTableModel dtm = new DefaultTableModel(null, kolom);
        String SQL = "SELECT * FROM tb_ruangan WHERE id_ruangan NOT IN (SELECT m.id_ruangan FROM tb_ruangan m JOIN tb_sesi nm ON nm.id_ruangan = m.id_ruangan WHERE nm.tanggal_mulai BETWEEN '"+String.valueOf(sdf.format(dcTanggalMulai.getDate()))+"' AND '"+String.valueOf(sdf.format(newDate))+"' OR '"+String.valueOf(sdf.format(dcTanggalMulai.getDate()))+"' BETWEEN nm.tanggal_mulai AND nm.tanggal_selesai)";
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
        tbCekRuangan.setModel(dtm);
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
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tTemaSesi = new javax.swing.JTextField();
        tKuotaSesi = new javax.swing.JTextField();
        cbMateriSesi = new javax.swing.JComboBox<>();
        cbNarasumberSesi = new javax.swing.JComboBox<>();
        dcTanggalMulai = new com.toedter.calendar.JDateChooser();
        jSeparator2 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbCekRuangan = new javax.swing.JTable();
        jSeparator4 = new javax.swing.JSeparator();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        tLamaSesi = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel8.setText("SESI BARU");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 350, 50));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 70));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Tema");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel2.setText("Tanggal Mulai");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jLabel4.setText("Lama");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, -1, -1));

        jLabel6.setText("Materi");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jLabel7.setText("Narasumber");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        jLabel9.setText("Kursi");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 80, -1, -1));

        jLabel10.setText("Cek Ruangan Tersedia");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, -1, -1));
        jPanel1.add(tTemaSesi, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 320, -1));
        jPanel1.add(tKuotaSesi, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, 80, -1));

        jPanel1.add(cbMateriSesi, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 120, -1));

        cbNarasumberSesi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbNarasumberSesi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNarasumberSesiActionPerformed(evt);
            }
        });
        jPanel1.add(cbNarasumberSesi, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 120, -1));
        jPanel1.add(dcTanggalMulai, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 120, -1));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 320, 10));

        jButton1.setText("Simpan Sesi");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 390, 120, 20));

        jLabel11.setText("Kuota");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, -1, -1));

        tbCekRuangan.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbCekRuangan);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 410, 210));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 160, 10));

        jButton2.setText("Cek Ruangan");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 140, 120, 20));

        jButton3.setText("Cek Penggunaan");
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 390, 120, 20));
        jPanel1.add(tLamaSesi, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, 80, -1));

        jLabel12.setText("Hari");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 40, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 430, 420));

        setSize(new java.awt.Dimension(446, 531));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        getComboNarasumber();
    }//GEN-LAST:event_formWindowActivated

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        getSimpanSesi();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbNarasumberSesiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNarasumberSesiActionPerformed
        getComboMateri();
    }//GEN-LAST:event_cbNarasumberSesiActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        getCekRuangan();
    }//GEN-LAST:event_jButton2ActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormTambahSesi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormTambahSesi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormTambahSesi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormTambahSesi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormTambahSesi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbMateriSesi;
    private javax.swing.JComboBox<String> cbNarasumberSesi;
    private com.toedter.calendar.JDateChooser dcTanggalMulai;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextField tKuotaSesi;
    private javax.swing.JTextField tLamaSesi;
    private javax.swing.JTextField tTemaSesi;
    private javax.swing.JTable tbCekRuangan;
    // End of variables declaration//GEN-END:variables
}
