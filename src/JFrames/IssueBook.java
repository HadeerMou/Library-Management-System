/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package JFrames;

import java.sql.Connection;

/**
 *
 * @author Dell
 */
import java.lang.Override;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


// Using Design Pattern
interface book{
    void getBookDetails(); 
    void getStudentDetails();
}

public class IssueBook extends javax.swing.JFrame  implements book{

    /**
     * Creates new form IssueBook
     */
             
    public IssueBook() {
        initComponents();
    }
    
    // to fetch the book details from the database and display it to book details panel
    @Override
    public void getBookDetails(){
        int bookId = Integer.parseInt(issuebId.getText());
        
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from book_details where book_id = ?");
            pst.setInt(1,bookId);
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                bookId_lbl.setText(rs.getString("book_id"));
                bookName_lbl.setText(rs.getString("book_name"));
                author_lbl.setText(rs.getString("author"));
                quantity_lbl.setText(rs.getString("quantity"));
            }else{
                bookError_lbl.setText("Invalid Book Id");
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    // to fetch the student details from the database and display it to students details panel
    @Override
    public void getStudentDetails(){
        int studentId = Integer.parseInt(issuesId.getText());
        
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from students_details where student_id = ?");
            pst.setInt(1,studentId);
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                studentId_lbl.setText(rs.getString("student_id"));
                studentName_lbl.setText(rs.getString("student_name"));
                course_lbl.setText(rs.getString("course_name"));
                branch_lbl.setText(rs.getString("branch"));
            }else{
                studentError_lbl.setText("Invalid Student Id");
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //Method for inserting issue book details to database
    public boolean issueBook(){
        boolean isIssued = false;
        int bookId = Integer.parseInt(issuebId.getText());
        int studentId = Integer.parseInt(issuesId.getText());
        String bookName = bookName_lbl.getText();
        String studentName = studentName_lbl.getText();
        
        Date issueDate = issueDateCh.getDatoFecha();
        Date dueDate = dueDatech.getDatoFecha();
        
        long l1 = issueDate.getTime();
        long l2 = dueDate.getTime();
        
        java.sql.Date sIssueDate = new java.sql.Date(l1);
        java.sql.Date sDueDate = new java.sql.Date(l2);
        
        try{
            Connection con = DBConnection.getConnection();
            String sql = "insert into issuebook(book_id, book_name, student_id, student_name, issue_date, due_date, status) values(?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, bookId);
            pst.setString(2, bookName);
            pst.setInt(3, studentId);
            pst.setString(4, studentName);
            pst.setDate(5, sIssueDate);
            pst.setDate(6, sDueDate);
            pst.setString(7, "Pending");
            
            int rowCount = pst.executeUpdate();
            
            if(rowCount > 0){
                isIssued = true;
            }else{
                isIssued = false;
            }
  
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return isIssued;
    }
    
    //Updating counts of books after issued
    public void updateBookCount(){
        int bookId = Integer.parseInt(issuebId.getText());
        
        try{
            Connection con = DBConnection.getConnection();
            String sql = "update book_details set quantity = quantity - 1 where book_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, bookId);
            
            int rowCount = pst.executeUpdate();
            
            if(rowCount > 0){
                JOptionPane.showMessageDialog(this, "Book Count Updated");
                int initialCount = Integer.parseInt(quantity_lbl.getText());
                quantity_lbl.setText(Integer.toString(initialCount -1));
                
            }else{
                JOptionPane.showMessageDialog(this, "Book Count Can't Be Updated");
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    // checking whether book is already issued and allocated or not 
    public boolean isAllocated(){
        boolean isAlreadyIssued = false;
        int bookId = Integer.parseInt(issuebId.getText());
        int studentId = Integer.parseInt(issuesId.getText());
        
        try{
            Connection con = DBConnection.getConnection();
            String sql = "select * from issuebook where book_id =? and student_id =? and status = ?";
            
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, bookId);
            pst.setInt(2, studentId);
            pst.setString(3, "Pending");
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                isAlreadyIssued = true;
            }else{
                isAlreadyIssued = false;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return isAlreadyIssued;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_main = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        studentName_lbl = new javax.swing.JLabel();
        course_lbl = new javax.swing.JLabel();
        branch_lbl = new javax.swing.JLabel();
        studentId_lbl = new javax.swing.JLabel();
        studentError_lbl = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        bookError_lbl = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        bookId_lbl = new javax.swing.JLabel();
        author_lbl = new javax.swing.JLabel();
        bookName_lbl = new javax.swing.JLabel();
        quantity_lbl = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        issuesId = new javax.swing.JTextField();
        issuebId = new javax.swing.JTextField();
        button1 = new java.awt.Button();
        Readme5 = new javax.swing.JLabel();
        dueDatech = new rojeru_san.componentes.RSDateChooser();
        issueDateCh = new rojeru_san.componentes.RSDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(new java.awt.Dimension(1450, 970));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_main.setBackground(new java.awt.Color(255, 255, 255));
        panel_main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 51, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Student Details");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, -1, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, 250, 3));

        jLabel25.setBackground(new java.awt.Color(102, 102, 0));
        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Branch:");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 440, 80, -1));

        jLabel29.setBackground(new java.awt.Color(102, 102, 0));
        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Student Id:");
        jPanel1.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, 80, -1));

        jLabel30.setBackground(new java.awt.Color(102, 102, 0));
        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Student Name:");
        jPanel1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, 110, -1));

        jLabel31.setBackground(new java.awt.Color(102, 102, 0));
        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Course:");
        jPanel1.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 370, 100, -1));
        jPanel1.add(studentName_lbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 290, 210, 30));
        jPanel1.add(course_lbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 360, 210, 30));
        jPanel1.add(branch_lbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 430, 210, 30));
        jPanel1.add(studentId_lbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, 210, 30));

        studentError_lbl.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        studentError_lbl.setForeground(new java.awt.Color(255, 255, 102));
        jPanel1.add(studentError_lbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 540, 290, 30));

        panel_main.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 430, 830));

        jPanel2.setBackground(new java.awt.Color(102, 0, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("<< Back");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 90, 30));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Book Details");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 250, 3));

        bookError_lbl.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bookError_lbl.setForeground(new java.awt.Color(255, 255, 102));
        jPanel2.add(bookError_lbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 540, 290, 30));

        jLabel26.setBackground(new java.awt.Color(255, 204, 153));
        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Book Id:");
        jPanel2.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 70, -1));

        jLabel27.setBackground(new java.awt.Color(255, 204, 153));
        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Book Name:");
        jPanel2.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, 90, -1));

        jLabel28.setBackground(new java.awt.Color(255, 204, 153));
        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Author :");
        jPanel2.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 370, 100, -1));
        jPanel2.add(bookId_lbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, 210, 30));
        jPanel2.add(author_lbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 360, 210, 30));
        jPanel2.add(bookName_lbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 290, 210, 30));
        jPanel2.add(quantity_lbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 430, 210, 30));

        jLabel36.setBackground(new java.awt.Color(255, 204, 153));
        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Quantity:");
        jPanel2.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 440, 80, -1));

        panel_main.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 830));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setText("Issue Book:");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 120, -1, -1));

        jLabel32.setBackground(new java.awt.Color(255, 204, 153));
        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel32.setText("Book Id:");
        jPanel5.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 240, 70, -1));

        jLabel33.setBackground(new java.awt.Color(102, 102, 0));
        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel33.setText("Issue Date:");
        jPanel5.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 390, 120, -1));

        jPanel6.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 160, 250, 3));

        jLabel34.setBackground(new java.awt.Color(102, 102, 0));
        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel34.setText("Due Date:");
        jPanel5.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 470, 120, -1));

        jLabel35.setBackground(new java.awt.Color(102, 102, 0));
        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel35.setText("Student Id:");
        jPanel5.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 310, 120, -1));

        issuesId.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(51, 51, 51)));
        issuesId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                issuesIdFocusLost(evt);
            }
        });
        issuesId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                issuesIdActionPerformed(evt);
            }
        });
        jPanel5.add(issuesId, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 300, 360, 30));

        issuebId.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(51, 51, 51)));
        issuebId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                issuebIdFocusLost(evt);
            }
        });
        issuebId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                issuebIdActionPerformed(evt);
            }
        });
        jPanel5.add(issuebId, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 230, 360, 30));

        button1.setBackground(new java.awt.Color(255, 51, 51));
        button1.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setLabel("Issue Book");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        jPanel5.add(button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 610, 200, 50));

        Readme5.setFont(new java.awt.Font("Gadugi", 1, 24)); // NOI18N
        Readme5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        Readme5.setText("X");
        Readme5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Readme5MouseClicked(evt);
            }
        });
        jPanel5.add(Readme5, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 30, 20, -1));

        dueDatech.setColorBackground(new java.awt.Color(204, 51, 0));
        dueDatech.setColorButtonHover(new java.awt.Color(51, 51, 51));
        dueDatech.setColorForeground(new java.awt.Color(51, 51, 51));
        dueDatech.setPlaceholder("Choose Due Date");
        jPanel5.add(dueDatech, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 460, 360, -1));

        issueDateCh.setColorBackground(new java.awt.Color(204, 51, 0));
        issueDateCh.setColorButtonHover(new java.awt.Color(51, 51, 51));
        issueDateCh.setColorForeground(new java.awt.Color(51, 51, 51));
        issueDateCh.setPlaceholder("Choose Issue Date");
        jPanel5.add(issueDateCh, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 380, 360, -1));

        panel_main.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 0, 1623, 828));

        getContentPane().add(panel_main, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1623, 828));

        setSize(new java.awt.Dimension(1623, 828));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        HomePage home = new HomePage();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void issuesIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_issuesIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_issuesIdActionPerformed

    private void issuebIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_issuebIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_issuebIdActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        if(quantity_lbl.getText().equals("0")){
            JOptionPane.showMessageDialog(this, "Book Isn't Available!");
        }else{
           if(isAllocated() == false){
            if(issueBook() == true){
            JOptionPane.showMessageDialog(this, "Book Issued Successfully");
            updateBookCount();
            }else{
                JOptionPane.showMessageDialog(this, "Book Can't Be Issued");
            }
        }else{
            JOptionPane.showMessageDialog(this, "This Student Already Has This Book");
        } 
        }  
    }//GEN-LAST:event_button1ActionPerformed

    private void Readme5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Readme5MouseClicked
        System.exit(0);
    }//GEN-LAST:event_Readme5MouseClicked

    private void issuebIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_issuebIdFocusLost
        if(!issuebId.getText().equals("")){
            getBookDetails();
        }
        
    }//GEN-LAST:event_issuebIdFocusLost

    private void issuesIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_issuesIdFocusLost
        if(!issuesId.getText().equals("")){
            getStudentDetails();
        }
        
    }//GEN-LAST:event_issuesIdFocusLost

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
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IssueBook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Readme5;
    private javax.swing.JLabel author_lbl;
    private javax.swing.JLabel bookError_lbl;
    private javax.swing.JLabel bookId_lbl;
    private javax.swing.JLabel bookName_lbl;
    private javax.swing.JLabel branch_lbl;
    private java.awt.Button button1;
    private javax.swing.JLabel course_lbl;
    private rojeru_san.componentes.RSDateChooser dueDatech;
    private rojeru_san.componentes.RSDateChooser issueDateCh;
    private javax.swing.JTextField issuebId;
    private javax.swing.JTextField issuesId;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel panel_main;
    private javax.swing.JLabel quantity_lbl;
    private javax.swing.JLabel studentError_lbl;
    private javax.swing.JLabel studentId_lbl;
    private javax.swing.JLabel studentName_lbl;
    // End of variables declaration//GEN-END:variables

    //@Override
    public void display() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
