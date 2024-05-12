/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package JFrames;

import java.sql.PreparedStatement;
import java.util.Date;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dell
 */
public class ViewRecords extends javax.swing.JFrame {

    /**
     * Creates new form ViewRecords
     */
    
    DefaultTableModel model;
    
    public ViewRecords() {
        initComponents();
        setIssuedBooks();
    }
    
    public void setIssuedBooks(){
        
        try{
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from issuebook");
            
            while(rs.next()){
                String id = rs.getString("id");
                String bookName = rs.getString("book_name");
                String studentName = rs.getString("student_name");
                String issueDate = rs.getString("issue_date");
                String dueDate = rs.getString("due_date");
                String status = rs.getString("status");
                
                Object[] obj = {id, bookName, studentName, issueDate, dueDate, status};
                model = (DefaultTableModel) issueBook.getModel();
                model.addRow(obj);
            
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void clear(){
        DefaultTableModel model = (DefaultTableModel) issueBook.getModel();
        model.setRowCount(0);
    }
    
    // to fetch the record using data fields
    public void search(){
        Date uFromDate = fromDate.getDatoFecha();
        Date uToDate = toDate.getDatoFecha();
        
        long l1 = uFromDate.getTime();
        long l2 = uToDate.getTime();
        
        java.sql.Date sfromDate = new java.sql.Date(l1);
        java.sql.Date stoDate = new java.sql.Date(l2);
        
        try{
            Connection con = DBConnection.getConnection();
            String sql = "select * from issuebook where issue_date between ? and ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setDate(1, sfromDate);
            pst.setDate(2, stoDate);
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next() == false){
                JOptionPane.showMessageDialog(this, "No Record Found");
            }else{
                while(rs.next()){
                    String id = rs.getString("id");
                    String bookName = rs.getString("book_name");
                    String studentName = rs.getString("student_name");
                    String issueDate = rs.getString("issue_date");
                    String dueDate = rs.getString("due_date");
                    String status = rs.getString("status");

                    Object[] obj = {id, bookName, studentName, issueDate, dueDate, status};
                    model = (DefaultTableModel) issueBook.getModel();
                    model.addRow(obj);
                }
            }
            
        }catch (Exception e){
            e.printStackTrace();
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        fromDate = new rojeru_san.componentes.RSDateChooser();
        jLabel2 = new javax.swing.JLabel();
        toDate = new rojeru_san.componentes.RSDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        Readme5 = new javax.swing.JLabel();
        panelTable = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        issueBook = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel1.setText("Records");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 60, 110, -1));

        fromDate.setColorBackground(new java.awt.Color(255, 204, 204));
        fromDate.setColorButtonHover(new java.awt.Color(51, 51, 51));
        fromDate.setColorForeground(new java.awt.Color(51, 51, 51));
        fromDate.setPlaceholder("Select Issue Date");
        jPanel1.add(fromDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 180, 360, -1));

        jLabel2.setFont(new java.awt.Font("Sitka Text", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Issue Date:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, -1, -1));

        toDate.setColorBackground(new java.awt.Color(255, 204, 204));
        toDate.setColorButtonHover(new java.awt.Color(51, 51, 51));
        toDate.setColorForeground(new java.awt.Color(51, 51, 51));
        toDate.setPlaceholder("Select Due Date");
        jPanel1.add(toDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 180, 360, -1));

        jLabel3.setFont(new java.awt.Font("Sitka Text", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Due Date:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 200, -1, -1));

        jButton1.setBackground(new java.awt.Color(204, 204, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1390, 180, 120, 40));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("<< Back");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 90, 30));

        Readme5.setFont(new java.awt.Font("Gadugi", 1, 24)); // NOI18N
        Readme5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        Readme5.setText("X");
        Readme5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Readme5MouseClicked(evt);
            }
        });
        jPanel1.add(Readme5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1470, 20, 20, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1620, 280));

        panelTable.setBackground(new java.awt.Color(255, 255, 255));
        panelTable.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        issueBook.setBackground(new java.awt.Color(153, 153, 255));
        issueBook.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Book Name", "Student Name", "Issue Date", "Due Date", "Status"
            }
        ));
        issueBook.setRowHeight(30);
        issueBook.setShowGrid(true);
        issueBook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                issueBookMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(issueBook);

        panelTable.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 140, 660, 180));

        getContentPane().add(panelTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 1620, 828));

        setSize(new java.awt.Dimension(1620, 828));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void issueBookMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_issueBookMouseClicked
        
    }//GEN-LAST:event_issueBookMouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        HomePage home = new HomePage();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void Readme5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Readme5MouseClicked
        System.exit(0);
    }//GEN-LAST:event_Readme5MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(fromDate.getDatoFecha() != null && toDate.getDatoFecha() != null){
            clear();
            search(); 
        }else{
            JOptionPane.showMessageDialog(this, "Please select a date!");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(ViewRecords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewRecords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewRecords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewRecords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewRecords().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Readme5;
    private rojeru_san.componentes.RSDateChooser fromDate;
    private javax.swing.JTable issueBook;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelTable;
    private rojeru_san.componentes.RSDateChooser toDate;
    // End of variables declaration//GEN-END:variables
}
