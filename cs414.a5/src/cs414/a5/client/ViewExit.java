/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ViewExit.java
 *
 * Created on Nov 12, 2011, 12:44:04 PM
 */
package cs414.a5.client;

import cs414.a5.common.ExitEvent;
import cs414.a5.common.ParkingGarageException;
import cs414.a5.common.Utilities;
import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author jeckstein
 */
public class ViewExit extends AbstractView {

    private ViewLocator viewLocator = new ViewLocator();
    private ExitEvent currentExit;
    
    /** Creates new form ViewExit */
    public ViewExit() {        
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtTicketNumber = new javax.swing.JTextField();
        btnInvoiceAmount = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        cmbPaymentMethod = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        pnlPaymentMethod = new javax.swing.JPanel();

        jLabel1.setText("Enter Ticket Number:");

        btnInvoiceAmount.setText("Get Invoice Amount");
        btnInvoiceAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInvoiceAmountActionPerformed(evt);
            }
        });

        jLabel7.setText("(enter the word \"LOST\" if ticket is lost or damaged)");

        cmbPaymentMethod.setModel(new DefaultComboBoxModel( getPaymentMethodsModel() ));
        cmbPaymentMethod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPaymentMethodActionPerformed(evt);
            }
        });

        jLabel2.setText("Payment Method:");

        pnlPaymentMethod.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbPaymentMethod, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel7)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnInvoiceAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtTicketNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(pnlPaymentMethod, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtTicketNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnInvoiceAmount)
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cmbPaymentMethod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlPaymentMethod, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnInvoiceAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInvoiceAmountActionPerformed
        String ticketNum = txtTicketNumber.getText();
        if (!Utilities.isNullOrEmpty(ticketNum)) {
            try {
                currentExit = getServiceClient().createExitEvent(ticketNum, new Date());
                eventAggregator.publish(new StatusEvent("The total is " + currentExit.getTotalInvoiceAmount().toPlainString() + ". Please select payment method."));
                //setExitStatus("The total is " + exit.getTotalInvoiceAmount().toPlainString() + ". How would you like to pay?");
            } catch (Exception ex) {
                HandleException(ex);
            } 
        }
    }//GEN-LAST:event_btnInvoiceAmountActionPerformed

    private void cmbPaymentMethodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPaymentMethodActionPerformed
        
        Object selectedItem = cmbPaymentMethod.getSelectedItem();
        if (selectedItem instanceof PaymentMethods) {
            PaymentMethods payMethod = (PaymentMethods)selectedItem;
            viewLocator.showView(pnlPaymentMethod, "Payment" + payMethod);                        
            //eventAggregator.publish(new InvoiceEvent(currentExit.getTotalInvoiceAmount(), currentExit.getTicketId()));
        }
    }//GEN-LAST:event_cmbPaymentMethodActionPerformed
    
    private Object[] getPaymentMethodsModel() {
        ArrayList<Object> model = new ArrayList<Object>();
        model.add("Select Payment Method");
        for (PaymentMethods pm : PaymentMethods.values()) {
            model.add(pm);
        }

        return model.toArray();
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInvoiceAmount;
    private javax.swing.JComboBox cmbPaymentMethod;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel pnlPaymentMethod;
    private javax.swing.JTextField txtTicketNumber;
    // End of variables declaration//GEN-END:variables
}
