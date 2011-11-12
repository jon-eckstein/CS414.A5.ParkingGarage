/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ParkingGarageUI.java
 *
 * Created on Nov 1, 2011, 9:31:39 PM
 */
package cs414.a5.client;

import cs414.a5.common.EntryEvent;
import cs414.a5.common.ExitEvent;
import cs414.a5.common.ParkingGarage;
import cs414.a5.common.ParkingGarageException;
import cs414.a5.common.Utilities;
import java.lang.Void;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

/**
 *
 * @author jeckstein
 */
public class ParkingGarageUI extends javax.swing.JFrame {
    
    private ParkingGarageClientImpl parkingGarageService;
    private PaymentFormFactory paymentFormFactory = new PaymentFormFactory();
    private String gateId = "1";
    private Boolean isUserViewVisible = true;
    private String enterStatus = "";
    private String exitStatus = "";
    private int currentOpenSpots = 0;
    private Printer printer = new PrinterFakeImpl();

    /** Creates new form ParkingGarageUI */
    public ParkingGarageUI() {
        initComponents();
        initServiceClient();
        initBackgroundJob();
                
       
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jPnlHeader = new javax.swing.JPanel();
        jLblGateId = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanlMain = new javax.swing.JPanel();
        jPanelUserView = new javax.swing.JPanel();
        jBtnSwitchToAdminView = new javax.swing.JButton();
        jBtnExit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPnlEnter = new javax.swing.JPanel();
        jBtnOpenGate = new javax.swing.JButton();
        jBtnGetTicket = new javax.swing.JButton();
        jLblEnterStatus = new javax.swing.JLabel();
        jBtnEnter = new javax.swing.JButton();
        jPanelExit = new javax.swing.JPanel();
        jTxtExitTicketNumber = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jBtnGetInvoice = new javax.swing.JButton();
        jLblExitStatus = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboBoxPaymentMethod = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLblOpenSpots = new javax.swing.JLabel();
        jPnlPaymentForm = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLblGateId.setText(getGateId());

        jLabel2.setText("Welcome to the Parking Garage.  You are at gate ");

        javax.swing.GroupLayout jPnlHeaderLayout = new javax.swing.GroupLayout(jPnlHeader);
        jPnlHeader.setLayout(jPnlHeaderLayout);
        jPnlHeaderLayout.setHorizontalGroup(
            jPnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnlHeaderLayout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jLblGateId, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 631, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(279, 279, 279))
        );
        jPnlHeaderLayout.setVerticalGroup(
            jPnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLblGateId, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanlMain.setLayout(new java.awt.CardLayout());

        jPanelUserView.setName("UserView"); // NOI18N

        jBtnSwitchToAdminView.setText("Switch To Admin View");
        jBtnSwitchToAdminView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnSwitchToAdminViewMouseClicked(evt);
            }
        });
        jBtnSwitchToAdminView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSwitchToAdminViewActionPerformed(evt);
            }
        });

        jBtnExit.setText("Exit");
        jBtnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnExitActionPerformed(evt);
            }
        });

        jLabel1.setText("Customer View:");

        jLabel3.setText("What would you like to do?");

        jBtnOpenGate.setText("Open Gate");
        jBtnOpenGate.setEnabled(false);
        jBtnOpenGate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnOpenGateActionPerformed(evt);
            }
        });

        jBtnGetTicket.setText("Get Ticket");
        jBtnGetTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnGetTicketActionPerformed(evt);
            }
        });

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${enterStatus}"), jLblEnterStatus, org.jdesktop.beansbinding.BeanProperty.create("text"), "");
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout jPnlEnterLayout = new javax.swing.GroupLayout(jPnlEnter);
        jPnlEnter.setLayout(jPnlEnterLayout);
        jPnlEnterLayout.setHorizontalGroup(
            jPnlEnterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlEnterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPnlEnterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPnlEnterLayout.createSequentialGroup()
                        .addComponent(jLblEnterStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPnlEnterLayout.createSequentialGroup()
                        .addComponent(jBtnGetTicket, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                        .addGap(51, 51, 51))
                    .addGroup(jPnlEnterLayout.createSequentialGroup()
                        .addComponent(jBtnOpenGate, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(51, Short.MAX_VALUE))))
        );
        jPnlEnterLayout.setVerticalGroup(
            jPnlEnterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlEnterLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jBtnGetTicket)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnOpenGate)
                .addGap(19, 19, 19)
                .addComponent(jLblEnterStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addGap(13, 13, 13))
        );

        jBtnEnter.setText("Enter");
        jBtnEnter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEnterActionPerformed(evt);
            }
        });

        jTxtExitTicketNumber.setToolTipText("");

        jLabel4.setText("Enter Ticket Number:");

        jBtnGetInvoice.setText("Get Invoice Amount");
        jBtnGetInvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnGetInvoiceActionPerformed(evt);
            }
        });

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${exitStatus}"), jLblExitStatus, org.jdesktop.beansbinding.BeanProperty.create("text"), "exitStatusTextBinding");
        bindingGroup.addBinding(binding);

        jLabel6.setText("Payment  Method:");

        jComboBoxPaymentMethod.setModel(new DefaultComboBoxModel( getPaymentMethodsModel() ));
        jComboBoxPaymentMethod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPaymentMethodActionPerformed(evt);
            }
        });

        jLabel7.setText("(enter the word \"LOST\" if ticket is lost or damaged)");

        javax.swing.GroupLayout jPanelExitLayout = new javax.swing.GroupLayout(jPanelExit);
        jPanelExit.setLayout(jPanelExitLayout);
        jPanelExitLayout.setHorizontalGroup(
            jPanelExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLblExitStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
            .addGroup(jPanelExitLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTxtExitTicketNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanelExitLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(jPanelExitLayout.createSequentialGroup()
                .addGroup(jPanelExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelExitLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxPaymentMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelExitLayout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(jBtnGetInvoice, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanelExitLayout.setVerticalGroup(
            jPanelExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelExitLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTxtExitTicketNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(13, 13, 13)
                .addComponent(jBtnGetInvoice)
                .addGap(18, 18, 18)
                .addGroup(jPanelExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBoxPaymentMethod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addComponent(jLblExitStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel5.setText("Open Spots:");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, this, org.jdesktop.beansbinding.ELProperty.create("${currentOpenSpots}"), jLblOpenSpots, org.jdesktop.beansbinding.BeanProperty.create("text"), "bindingCurrentOpen");
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout jPnlPaymentFormLayout = new javax.swing.GroupLayout(jPnlPaymentForm);
        jPnlPaymentForm.setLayout(jPnlPaymentFormLayout);
        jPnlPaymentFormLayout.setHorizontalGroup(
            jPnlPaymentFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 221, Short.MAX_VALUE)
        );
        jPnlPaymentFormLayout.setVerticalGroup(
            jPnlPaymentFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 222, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelUserViewLayout = new javax.swing.GroupLayout(jPanelUserView);
        jPanelUserView.setLayout(jPanelUserViewLayout);
        jPanelUserViewLayout.setHorizontalGroup(
            jPanelUserViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUserViewLayout.createSequentialGroup()
                .addGap(1511, 1511, 1511)
                .addGroup(jPanelUserViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelUserViewLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel1)
                        .addGap(605, 605, 605)
                        .addComponent(jBtnSwitchToAdminView))
                    .addGroup(jPanelUserViewLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jLblOpenSpots, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelUserViewLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelUserViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelUserViewLayout.createSequentialGroup()
                                .addGroup(jPanelUserViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jBtnExit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jBtnEnter, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addComponent(jPnlEnter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPnlPaymentForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelUserViewLayout.setVerticalGroup(
            jPanelUserViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUserViewLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelUserViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelUserViewLayout.createSequentialGroup()
                        .addGroup(jPanelUserViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jBtnSwitchToAdminView))
                        .addGroup(jPanelUserViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelUserViewLayout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(jPanelUserViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(jLblOpenSpots, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelUserViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelUserViewLayout.createSequentialGroup()
                                        .addComponent(jBtnEnter)
                                        .addGap(2, 2, 2)
                                        .addComponent(jBtnExit))
                                    .addComponent(jPnlEnter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanelUserViewLayout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(jPnlPaymentForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jPanelExit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(230, Short.MAX_VALUE))
        );

        jPanlMain.add(jPanelUserView, "cardUserView");
        jPanelUserView.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanlMain, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPnlHeader, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPnlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanlMain, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1048, Short.MAX_VALUE))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnSwitchToAdminViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnSwitchToAdminViewMouseClicked
    }//GEN-LAST:event_jBtnSwitchToAdminViewMouseClicked

    private void jBtnGetTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnGetTicketActionPerformed
        try {
            Date entryDate = new Date();
            EntryEvent entry = parkingGarageService.createEntryEvent(entryDate, getGateId());
            printer.printEntryTicket(entry);
            jBtnOpenGate.setEnabled(true);
            jBtnGetTicket.setEnabled(false);
            setEnterStatus("<html>The ticket has been printed. <br/> Your ticket number is: " + entry.getTicketId() + ". <br/> Please get ticket from printer. <br/> Please press Open Gate.</html>");      
        } catch (ServiceCommunicationException ex) {
            HandleException(ex);
        } catch (ParkingGarageException ex) {
            HandleException(ex);
        }
    }//GEN-LAST:event_jBtnGetTicketActionPerformed

    private void jBtnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnExitActionPerformed
    }//GEN-LAST:event_jBtnExitActionPerformed

    private void jBtnSwitchToAdminViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSwitchToAdminViewActionPerformed
        jPanelAdminView.setVisible(true);
        jPanelUserView.setVisible(false);
    }//GEN-LAST:event_jBtnSwitchToAdminViewActionPerformed

    private void jBtnEnterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEnterActionPerformed
        jPnlEnter.setVisible(true);
    }//GEN-LAST:event_jBtnEnterActionPerformed

    private void jBtnOpenGateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnOpenGateActionPerformed
        setEnterStatus("Gate opening...");
        try {
            parkingGarageService.openGate(gateId);
            setEnterStatus("Gate opened, you have 5 seconds to enter garage. Park safely.");

            SwingWorker sw = new SwingWorker() {
                @Override
                protected Void doInBackground() throws Exception {
                    Thread.currentThread().sleep(5000);
                    setEnterStatus("Gate closing...");
                    parkingGarageService.closeGate(gateId);
                    setEnterStatus("Gate closed.");
                    return null;
                }

                @Override
                protected void done() {
                    jPnlEnter.setVisible(false);
                }
            };
            sw.execute();
        } catch (ParkingGarageException ex) {
            HandleException(ex);
        } catch (ServiceCommunicationException ex) {
            HandleException(ex);
        }
    }//GEN-LAST:event_jBtnOpenGateActionPerformed

    private void jBtnGetInvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnGetInvoiceActionPerformed
        String ticketNum = jTxtExitTicketNumber.getText();
        if (!Utilities.isNullOrEmpty(ticketNum)) {
            try {
                ExitEvent exit = parkingGarageService.createExitEvent(ticketNum, new Date());
                setExitStatus("The total is " + exit.getTotalInvoiceAmount().toPlainString() + ". How would you like to pay?");
            } catch (ServiceCommunicationException ex) {
                HandleException(ex);
            } catch (ParkingGarageException ex) {
                HandleException(ex);
            }
        }

    }//GEN-LAST:event_jBtnGetInvoiceActionPerformed

    private void jComboBoxPaymentMethodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPaymentMethodActionPerformed
        Object selectedItem = jComboBoxPaymentMethod.getSelectedItem();
        if (selectedItem instanceof PaymentMethods) {
            PaymentMethods payMethod = (PaymentMethods)selectedItem;
            JPanel paymentForm = paymentFormFactory.createPaymentForm(payMethod);
            //jPnlPaymentForm.setLayout(new java.awt.BorderLayout());
            //jPnlPaymentForm.add(paymentForm);
            //paymentForm.setVisible(true);
            //jPnlPaymentForm.revalidate();
            //jPnlPaymentForm.repaint();
            //this.revalidate();
            //this.repaint();
            
            //jPnlPaymentForm.add(new JButton("Dynamic Button"));
            //jPnlPaymentForm.revalidate(); 
            //jPanelUserView.revalidate();
            this.add(new JButton("Dynamic Button"));
            revalidate();
            validate();
            repaint();
        }

    }//GEN-LAST:event_jComboBoxPaymentMethodActionPerformed

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
            java.util.logging.Logger.getLogger(ParkingGarageUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ParkingGarageUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ParkingGarageUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ParkingGarageUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ParkingGarageUI().setVisible(true);
            }
        });
        
        
    }

    

    private void HandleException(Exception ex) {
        String prefix = "";
        if (ex instanceof ServiceCommunicationException) {
            prefix = "Network problem: ";
        }

        this.jLblStatus.setText(prefix + ex.getMessage());
        Logger.getLogger(ParkingGarageUI.class.getName()).log(Level.SEVERE, null, ex);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnEnter;
    private javax.swing.JButton jBtnExit;
    private javax.swing.JButton jBtnGetInvoice;
    private javax.swing.JButton jBtnGetTicket;
    private javax.swing.JButton jBtnOpenGate;
    private javax.swing.JButton jBtnSwitchToAdminView;
    private javax.swing.JComboBox jComboBoxPaymentMethod;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLblEnterStatus;
    private javax.swing.JLabel jLblExitStatus;
    private javax.swing.JLabel jLblGateId;
    private javax.swing.JLabel jLblOpenSpots;
    private javax.swing.JPanel jPanelExit;
    private javax.swing.JPanel jPanelUserView;
    private javax.swing.JPanel jPanlMain;
    private javax.swing.JPanel jPnlEnter;
    private javax.swing.JPanel jPnlHeader;
    private javax.swing.JPanel jPnlPaymentForm;
    private javax.swing.JTextField jTxtExitTicketNumber;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the gateId
     */
    public String getGateId() {
        return gateId;
    }

    /**
     * @param gateId the gateId to set
     */
    public void setGateId(String gateId) {
        this.gateId = gateId;
    }

    /**
     * @return the enterStatus
     */
    public String getEnterStatus() {
        return enterStatus;
    }

    /**
     * @param enterStatus the enterStatus to set
     */
    public void setEnterStatus(String enterStatus) {
        this.enterStatus = enterStatus;
        firePropertyChange("enterStatus", null, null);
    }

    /**
     * @return the exitStatus
     */
    public String getExitStatus() {
        return exitStatus;
    }

    /**
     * @param exitStatus the exitStatus to set
     */
    public void setExitStatus(String exitStatus) {
        this.exitStatus = exitStatus;
        firePropertyChange("exitStatus", null, null);
    }

    private void initBackgroundJob() {
        SwingWorker sw = new SwingWorker() {

            @Override
            protected Object doInBackground() throws Exception {
                while (true) {
                    setCurrentOpenSpots(parkingGarageService.getAvailableSpotCount());
                    Thread.sleep(500);
                }
            }
        };

        sw.execute();
    }

    /**
     * @return the currentOpenSpots
     */
    public int getCurrentOpenSpots() {
        return currentOpenSpots;
    }

    /**
     * @param currentOpenSpots the currentOpenSpots to set
     */
    public void setCurrentOpenSpots(int currentOpenSpots) {
        this.currentOpenSpots = currentOpenSpots;
        firePropertyChange("currentOpenSpots", null, null);
    }

    private Object[] getPaymentMethodsModel() {
        ArrayList<Object> model = new ArrayList<Object>();
        model.add("Select Payment Method");
        for (PaymentMethods pm : PaymentMethods.values()) {
            model.add(pm);
        }

        return model.toArray();
    }

    private void initServiceClient() {
        parkingGarageService = ParkingGarageClientImpl.getInstance();
        if(parkingGarageService == null){
            HandleException(new ServiceCommunicationException("Could not connect to parking garage service."));
        }
    }
}
