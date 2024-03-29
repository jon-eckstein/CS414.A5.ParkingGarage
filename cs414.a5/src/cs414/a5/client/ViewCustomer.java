/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ViewCustomer.java
 *
 * Created on Nov 13, 2011, 8:55:11 AM
 */
package cs414.a5.client;

import javax.swing.SwingWorker;

/**
 *
 * @author jeckstein
 */
public class ViewCustomer extends AbstractView {

    private ViewLocator viewLocator = new ViewLocator();
    private int currentOpenSpots;
    
    
    /** Creates new form ViewCustomer */
    public ViewCustomer() {
        initComponents();
        initBackgroundJob();
        eventAggregator.subscribe(GateClosedEvent.class, this);
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

        jLabel1 = new javax.swing.JLabel();
        txtOpenSpaces = new javax.swing.JTextField();
        btnEnter = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        pnlEnterExit = new javax.swing.JPanel();

        jLabel1.setText("Open Spaces:");

        txtOpenSpaces.setEnabled(false);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${currentOpenSpots}"), txtOpenSpaces, org.jdesktop.beansbinding.BeanProperty.create("text"), "");
        bindingGroup.addBinding(binding);

        btnEnter.setText("Enter");
        btnEnter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnterActionPerformed(evt);
            }
        });

        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        pnlEnterExit.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlEnterExit, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtOpenSpaces, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEnter, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtOpenSpaces, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEnter)
                    .addComponent(btnExit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlEnterExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnterActionPerformed
        // TODO add your handling code here:
        viewLocator.showView(pnlEnterExit, "Entry");
        btnExit.setEnabled(false);
    }//GEN-LAST:event_btnEnterActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        viewLocator.showView(pnlEnterExit, "Exit");
        btnEnter.setEnabled(false);
    }//GEN-LAST:event_btnExitActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnter;
    private javax.swing.JButton btnExit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel pnlEnterExit;
    private javax.swing.JTextField txtOpenSpaces;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

     private void initBackgroundJob() {
        SwingWorker sw = new SwingWorker() {

            @Override
            protected Object doInBackground() throws Exception {
                while (true) {
                    try{
                        int openSpots = getServiceClient().getAvailableSpotCount();
                        setCurrentOpenSpots(openSpots);
                    }catch(Exception ex){
                        handleException(ex);
                    }
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
    
    @Override
    public <T> void notifyOnEvent(T payload) {
        if(payload.getClass() == GateClosedEvent.class)        
            handleGateClosed();
        
    }

    private void handleGateClosed() {
        viewLocator.showView(pnlEnterExit, "Blank");
        btnEnter.setEnabled(true);
        btnExit.setEnabled(true);
    }
}
