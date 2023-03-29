package oop.views;

import java.awt.Frame;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import oop.entities.DurableProduct;
import oop.entities.DurableProductHandler;
import oop.entities.PerishableProduct;
import oop.entities.PerishableProductHandler;
import oop.entities.Product;
import oop.entities.ProductHandler;
import oop.exceptions.VerificationException;
import oop.utils.ProductType;

/**
 *
 * @author Marci
 */
public class ProductEditorForm extends javax.swing.JDialog {

    private ProductHandler handler;
    private ProductType type;
    private List<ProductEventListener> listeners;
    private ProductHandlingStrategy strategy;

    /**
     * Creates new form ProductEditorForm
     *
     * @param parent
     * @param type
     */
    public ProductEditorForm(java.awt.Frame parent, ProductType type) {
        super(parent, false);
        initComponents();
        setLocationRelativeTo(null);
        listeners = new ArrayList();
        this.type = type;
        setHandler();
        setTextFields();
    }

    public ProductEditorForm(Frame frame, ProductType type, Product p) {
        this(frame, type);
        fillFormWithData(p);
    }

    public void setProductHandlingStrategy(ProductHandlingStrategy s) {
        this.strategy = s;
    }

    public void addProductEventListener(ProductEventListener l) {
        listeners.add(l);
    }

    public void removeProductEventListener(ProductEventListener l) {
        listeners.remove(l);
    }

    private void notifyListeners(Product product) {
        for (ProductEventListener listener : listeners) {
            strategy.notify(product, listener);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tfArticleNumber = new javax.swing.JTextField();
        tfName = new javax.swing.JTextField();
        tfFamily = new javax.swing.JTextField();
        tfBrand = new javax.swing.JTextField();
        tfNettoPrice = new javax.swing.JTextField();
        tfQuantity = new javax.swing.JTextField();
        tfTaxId = new javax.swing.JTextField();
        tfAmountUnits = new javax.swing.JTextField();
        tfCriticalQuantity = new javax.swing.JTextField();
        tfVariable2 = new javax.swing.JTextField();
        tfVariable1 = new javax.swing.JTextField();
        lbArticleNumber = new javax.swing.JLabel();
        lbName = new javax.swing.JLabel();
        lbBrand = new javax.swing.JLabel();
        lbFamily = new javax.swing.JLabel();
        lbNettoPrice = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbAmountUnits = new javax.swing.JLabel();
        lbCriticalQuantity = new javax.swing.JLabel();
        lbTaxId = new javax.swing.JLabel();
        lbQuantity = new javax.swing.JLabel();
        lbVariable2 = new javax.swing.JLabel();
        lbVariable1 = new javax.swing.JLabel();
        btCancel = new javax.swing.JButton();
        btSave = new javax.swing.JButton();
        lbArticleFormat = new javax.swing.JLabel();
        lbDateFormat = new javax.swing.JLabel();
        lbDateFormat1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setResizable(false);

        lbArticleNumber.setText("ArticleNumber");

        lbName.setText("Name");

        lbBrand.setText("Brand");

        lbFamily.setText("Family");

        lbNettoPrice.setText("NettoPrice");

        lbAmountUnits.setText("AmountUnits");

        lbCriticalQuantity.setText("CriticalQuantity");

        lbTaxId.setText("TaxId");

        lbQuantity.setText("Quantity");

        lbVariable2.setText("---");

        lbVariable1.setText("---");

        btCancel.setText("Cancel");
        btCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelActionPerformed(evt);
            }
        });

        btSave.setText("Save");
        btSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSaveActionPerformed(evt);
            }
        });

        lbArticleFormat.setBackground(new java.awt.Color(102, 102, 102));
        lbArticleFormat.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lbArticleFormat.setText("---");

        lbDateFormat.setBackground(new java.awt.Color(102, 102, 102));
        lbDateFormat.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lbDateFormat.setText("---");

        lbDateFormat1.setBackground(new java.awt.Color(102, 102, 102));
        lbDateFormat1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lbDateFormat1.setText("---");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lbVariable2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbDateFormat1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbArticleNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                            .addComponent(lbName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbArticleFormat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCriticalQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbAmountUnits, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbNettoPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbTaxId, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btSave, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbFamily, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                                    .addComponent(lbBrand, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(85, 85, 85))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lbVariable1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbDateFormat, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tfNettoPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfTaxId, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfAmountUnits, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfCriticalQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tfVariable1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfVariable2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfBrand, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfFamily, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfArticleNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lbAmountUnits, lbArticleNumber, lbBrand, lbCriticalQuantity, lbFamily, lbName, lbNettoPrice, lbQuantity, lbTaxId, lbVariable1, lbVariable2});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {tfAmountUnits, tfBrand, tfCriticalQuantity, tfFamily, tfName, tfNettoPrice, tfQuantity, tfTaxId, tfVariable1, tfVariable2});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbArticleNumber)
                    .addComponent(tfArticleNumber)
                    .addComponent(lbArticleFormat, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfName)
                    .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfBrand)
                    .addComponent(lbBrand, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbFamily, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfFamily))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNettoPrice)
                    .addComponent(tfNettoPrice))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTaxId)
                    .addComponent(tfTaxId))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbQuantity)
                    .addComponent(tfQuantity))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbAmountUnits)
                    .addComponent(tfAmountUnits))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCriticalQuantity)
                    .addComponent(tfCriticalQuantity))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tfVariable1)
                    .addComponent(lbVariable1)
                    .addComponent(lbDateFormat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbDateFormat1)
                        .addComponent(lbVariable2))
                    .addComponent(tfVariable2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lbAmountUnits, lbArticleNumber, lbBrand, lbCriticalQuantity, lbFamily, lbName, lbNettoPrice, lbQuantity, lbTaxId, lbVariable1, lbVariable2});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaveActionPerformed
        try {
            Product p = createProductWithFormData();
            strategy.save(handler, p);
            notifyListeners(p);
            dispose();
        } catch (VerificationException ex) {
            JOptionPane.showMessageDialog(this, "ArticleNumber is already in use or the values are in wrong format.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ArticleNumber is already in use or the values are in wrong format.");
        }
    }//GEN-LAST:event_btSaveActionPerformed

    private void btCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelActionPerformed
        dispose();
    }//GEN-LAST:event_btCancelActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCancel;
    private javax.swing.JButton btSave;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lbAmountUnits;
    private javax.swing.JLabel lbArticleFormat;
    private javax.swing.JLabel lbArticleNumber;
    private javax.swing.JLabel lbBrand;
    private javax.swing.JLabel lbCriticalQuantity;
    private javax.swing.JLabel lbDateFormat;
    private javax.swing.JLabel lbDateFormat1;
    private javax.swing.JLabel lbFamily;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbNettoPrice;
    private javax.swing.JLabel lbQuantity;
    private javax.swing.JLabel lbTaxId;
    private javax.swing.JLabel lbVariable1;
    private javax.swing.JLabel lbVariable2;
    private javax.swing.JTextField tfAmountUnits;
    private javax.swing.JTextField tfArticleNumber;
    private javax.swing.JTextField tfBrand;
    private javax.swing.JTextField tfCriticalQuantity;
    private javax.swing.JTextField tfFamily;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfNettoPrice;
    private javax.swing.JTextField tfQuantity;
    private javax.swing.JTextField tfTaxId;
    private javax.swing.JTextField tfVariable1;
    private javax.swing.JTextField tfVariable2;
    // End of variables declaration//GEN-END:variables

    private void setTextFields() {
        switch (type) {
            case DURABLE_PRODUCT:
                lbVariable1.setText("WarrantyPeriod");
                lbVariable2.setText("GrossWeight");
                lbArticleFormat.setText("\"DP12345678\"");
                lbDateFormat.setText("");
                lbDateFormat1.setText("");
                break;
            case PERISHABLE_PRODUCT:
                lbVariable1.setText("ExpirationDate");
                lbVariable2.setText("ProductionDate");
                lbArticleFormat.setText("\"PP12345678\"");
                lbDateFormat.setText("\"yyyy-mm-dd\"");
                lbDateFormat1.setText("\"yyyy-mm-dd\"");
                break;
        }
    }

    private Product createProductWithFormData() throws VerificationException {
        Product result = switch (type) {
            case DURABLE_PRODUCT ->
                createDurableProduct();
            case PERISHABLE_PRODUCT ->
                createPerishableProduct();
        };
        return result;
    }

    private void setHandler() {
        switch (type) {
            case DURABLE_PRODUCT ->
                handler = new DurableProductHandler();
            case PERISHABLE_PRODUCT ->
                handler = new PerishableProductHandler();
        }
    }

    private DurableProduct createDurableProduct() throws VerificationException {
        return new DurableProduct(
                tfArticleNumber.getText(),
                tfName.getText(),
                tfBrand.getText(),
                tfFamily.getText(),
                Integer.parseInt(tfNettoPrice.getText()),
                Integer.parseInt(tfTaxId.getText()),
                Integer.parseInt(tfQuantity.getText()),
                tfAmountUnits.getText(),
                Integer.parseInt(tfCriticalQuantity.getText()),
                Integer.parseInt(tfVariable1.getText()),
                Double.parseDouble(tfVariable2.getText()));
    }

    private PerishableProduct createPerishableProduct() throws VerificationException {
        return new PerishableProduct(
                tfArticleNumber.getText(),
                tfName.getText(),
                tfBrand.getText(),
                tfFamily.getText(),
                Integer.parseInt(tfNettoPrice.getText()),
                Integer.parseInt(tfTaxId.getText()),
                Integer.parseInt(tfQuantity.getText()),
                tfAmountUnits.getText(),
                Integer.parseInt(tfCriticalQuantity.getText()),
                Date.valueOf(tfVariable1.getText()),
                Date.valueOf(tfVariable2.getText()));
    }

    private void fillFormWithData(Product p) {
        switch (type) {
            case DURABLE_PRODUCT:
                fillFromDurableProduct((DurableProduct) p);
                break;
            case PERISHABLE_PRODUCT:
                fillFromPerishableProduct((PerishableProduct) p);
                break;
        }
    }

    private void fillFromDurableProduct(DurableProduct p) {
        tfArticleNumber.setText(p.getArticleNumber());
        tfName.setText(p.getName());
        tfBrand.setText(p.getBrand());
        tfFamily.setText(p.getFamily());
        tfNettoPrice.setText(String.valueOf(p.getNettoPrice()));
        tfTaxId.setText(String.valueOf(p.getTaxId()));
        tfQuantity.setText(String.valueOf(p.getQuantity()));
        tfAmountUnits.setText(p.getAmountUnits());
        tfCriticalQuantity.setText(String.valueOf(p.getCriticalQuantity()));
        tfVariable1.setText((String.valueOf(p.getWarrantyPeriod())));
        tfVariable2.setText(String.valueOf(p.getGrossWeight()));
    }

    private void fillFromPerishableProduct(PerishableProduct p) {
        tfArticleNumber.setText(p.getArticleNumber());
        tfName.setText(p.getName());
        tfBrand.setText(p.getBrand());
        tfFamily.setText(p.getFamily());
        tfNettoPrice.setText(String.valueOf(p.getNettoPrice()));
        tfTaxId.setText(String.valueOf(p.getTaxId()));
        tfQuantity.setText(String.valueOf(p.getQuantity()));
        tfAmountUnits.setText(p.getAmountUnits());
        tfCriticalQuantity.setText(String.valueOf(p.getCriticalQuantity()));
        tfVariable1.setText((String.valueOf(p.getExpirationDate())));
        tfVariable2.setText(String.valueOf(p.getProductionDate()));
    }

}