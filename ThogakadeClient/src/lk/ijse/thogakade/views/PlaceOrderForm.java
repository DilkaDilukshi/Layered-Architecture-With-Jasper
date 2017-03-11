/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.views;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import lk.ijse.thogakade.controller.ControllerFactory;
import lk.ijse.thogakade.controller.custom.impl.PlaceOrderControllerImpl;
import lk.ijse.thogakade.dto.CustomerDTO;
import lk.ijse.thogakade.dto.ItemDTO;
import lk.ijse.thogakade.dto.OrderDTO;
import lk.ijse.thogakade.dto.OrderDetailDTO;
import lk.ijse.thogakade.jasper.JasperReportViewer;
import lk.ijse.thogakade.jasper.ReportTester;
import lk.ijse.thogakade.observers.Observer;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author Dil
 */
public class PlaceOrderForm extends javax.swing.JFrame implements Observer {

    private PlaceOrderControllerImpl controller;

    /**
     * Creates new form PlaceOrderForm
     */
    public PlaceOrderForm() {
        initComponents();

        try {
            UnicastRemoteObject.exportObject(this, 0);
            
        } catch (RemoteException ex) {
            Logger.getLogger(PlaceOrderForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(new Date());
        txtDate.setText(formattedDate);

        try {
            ControllerFactory.getInstance().getRegisterd(this);

            controller = (PlaceOrderControllerImpl) ControllerFactory.getInstance().getController(ControllerFactory.ControllerTypes.PLACE_ORDER);

            controller.registorObserver(this);
            
        } catch (Exception ex) {
            Logger.getLogger(PlaceOrderForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadDataInCombos();
        setOrderId();

    }

    private void setOrderId() {
        try {
            String orderId = controller.getOrderId();

            txtOrderId.setText(orderId);
        } catch (RemoteException ex) {
            Logger.getLogger(PlaceOrderForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PlaceOrderForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadDataInCombos() {
        ArrayList<CustomerDTO> alCustomer;
        ArrayList<ItemDTO> alItem;
        try {
            alCustomer = controller.getAllCustomer();
            cmbCustId.removeAllItems();
            for (CustomerDTO cdto : alCustomer) {
                
                cmbCustId.addItem(cdto.getId());
            }

            alItem = controller.getAllItem();
            for (ItemDTO idto : alItem) {
                cmbItemCode.addItem(idto.getCode());
            }

        } catch (RemoteException | SQLException ex) {
            Logger.getLogger(PlaceOrderForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setTotalAmount() {
        DefaultTableModel dtm = (DefaultTableModel) tblItem.getModel();

        double total = 0;
        for (int i = 0; i < dtm.getRowCount(); i++) {
            total += Double.parseDouble(dtm.getValueAt(i, 4).toString());
        }
        txtTotalAmount.setText(String.valueOf(total));
    }

    private Date parseDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(PlaceOrderForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void cmbItemAction() {
        if (cmbItemCode.getSelectedIndex() != -1) {
            try {
                ItemDTO itemDTO = new ItemDTO(cmbItemCode.getSelectedItem().toString());
                itemDTO = (ItemDTO) controller.get(itemDTO);
                txtDescription.setText(itemDTO.getDescription());
                txtQtyOnHand.setText("" + itemDTO.getQtyOnHand());
                txtUnitPrice.setText("" + itemDTO.getUnitPrice());

            } catch (Exception ex) {
                Logger.getLogger(PlaceOrderForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void printInvoice(){
        try {
            HashMap<String,Object> params = new HashMap<>();
            params.put("name",txtName.getText());
            params.put("orderId", txtOrderId.getText());
            params.put("total", txtTotalAmount.getText());
            
            
            JasperReport jasperreport = (JasperReport) JRLoader.loadObject(ReportTester.class.getResourceAsStream("/lk/ijse/thogakade/jasper/reports/PlaceOrderInvoice.jasper"));
            JasperPrint filledreport = JasperFillManager.fillReport(jasperreport, params, new JRTableModelDataSource(tblItem.getModel()));

            JasperReportViewer reportViewer = new JasperReportViewer(filledreport);
            reportViewer.setVisible(true);
            reportViewer.setExtendedState(JFrame.MAXIMIZED_BOTH);
        } catch (JRException ex) {
            Logger.getLogger(CustomerForm.class.getName()).log(Level.SEVERE, null, ex);
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
        jLabel2 = new javax.swing.JLabel();
        txtOrderId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        cmbCustId = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtDescription = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtUnitPrice = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtQtyOnHand = new javax.swing.JTextField();
        txtQty = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cmbItemCode = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblItem = new javax.swing.JTable();
        removeButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        txtTotalAmount = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("DejaVu Serif", 1, 14)); // NOI18N
        jLabel2.setText("Order ID : ");

        txtOrderId.setEditable(false);
        txtOrderId.setFont(new java.awt.Font("DejaVu Serif", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("DejaVu Serif", 1, 14)); // NOI18N
        jLabel3.setText("Date : ");

        txtDate.setEditable(false);
        txtDate.setFont(new java.awt.Font("DejaVu Serif", 0, 14)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)), "Customer Info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Serif", 1, 14), new java.awt.Color(0, 204, 204))); // NOI18N

        jLabel4.setFont(new java.awt.Font("DejaVu Serif", 1, 14)); // NOI18N
        jLabel4.setText("ID : ");

        jLabel5.setFont(new java.awt.Font("DejaVu Serif", 1, 14)); // NOI18N
        jLabel5.setText("Name : ");

        txtName.setEditable(false);
        txtName.setFont(new java.awt.Font("DejaVu Serif", 1, 14)); // NOI18N

        cmbCustId.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbCustIdItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(41, 41, 41)
                .addComponent(cmbCustId, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbCustId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)), "Item Info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Serif", 1, 14), new java.awt.Color(0, 204, 204))); // NOI18N

        jLabel6.setFont(new java.awt.Font("DejaVu Serif", 1, 14)); // NOI18N
        jLabel6.setText("Code : ");

        jLabel7.setFont(new java.awt.Font("DejaVu Serif", 1, 14)); // NOI18N
        jLabel7.setText("Description : ");

        txtDescription.setEditable(false);
        txtDescription.setFont(new java.awt.Font("DejaVu Serif", 1, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("DejaVu Serif", 1, 14)); // NOI18N
        jLabel8.setText("Unit Price : ");

        txtUnitPrice.setEditable(false);
        txtUnitPrice.setFont(new java.awt.Font("DejaVu Serif", 1, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("DejaVu Serif", 1, 14)); // NOI18N
        jLabel9.setText("Qty On Hand : ");

        txtQtyOnHand.setEditable(false);
        txtQtyOnHand.setFont(new java.awt.Font("DejaVu Serif", 1, 14)); // NOI18N

        txtQty.setFont(new java.awt.Font("DejaVu Serif", 0, 14)); // NOI18N
        txtQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQtyActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("DejaVu Serif", 1, 14)); // NOI18N
        jLabel10.setText("Qty : ");

        cmbItemCode.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbItemCodeItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(28, 28, 28)
                        .addComponent(txtUnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbItemCode, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescription))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtQtyOnHand, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                                .addGap(32, 32, 32))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbItemCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtUnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtQtyOnHand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblItem.setFont(new java.awt.Font("DejaVu Serif", 0, 12)); // NOI18N
        tblItem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Description", "Unit Price", "Qty", "Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblItem);

        removeButton.setFont(new java.awt.Font("DejaVu Serif", 1, 14)); // NOI18N
        removeButton.setText("Remove");
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });

        addButton.setFont(new java.awt.Font("DejaVu Serif", 1, 14)); // NOI18N
        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        cancelButton.setFont(new java.awt.Font("DejaVu Serif", 1, 14)); // NOI18N
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        saveButton.setFont(new java.awt.Font("DejaVu Serif", 1, 14)); // NOI18N
        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        txtTotalAmount.setEditable(false);
        txtTotalAmount.setFont(new java.awt.Font("DejaVu Serif", 1, 16)); // NOI18N
        txtTotalAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotalAmount.setText("0.0");

        jLabel11.setFont(new java.awt.Font("DejaVu Serif", 1, 14)); // NOI18N
        jLabel11.setText("Total Amount : ");

        jPanel4.setBackground(new java.awt.Color(0, 0, 102));

        jLabel12.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Place Order");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtOrderId, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(44, 44, 44)
                        .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(addButton)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(removeButton))
                                .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTotalAmount, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(saveButton)
                                        .addGap(18, 18, 18)
                                        .addComponent(cancelButton)))))
                        .addGap(20, 20, 20))))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtOrderId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(removeButton)
                    .addComponent(addButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(saveButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQtyActionPerformed

    }//GEN-LAST:event_txtQtyActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        if (cmbItemCode.getSelectedIndex() != -1) {

            int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());
            if (txtQty.getText().trim().isEmpty()) {
                return;
            }
            int qty = Integer.parseInt(txtQty.getText());
            DefaultTableModel model = (DefaultTableModel) tblItem.getModel();
            if (qty <= qtyOnHand && qty > 0) {
                double amount = qty * Double.parseDouble(txtUnitPrice.getText());
                Object[] row = {
                    cmbItemCode.getSelectedItem().toString(),
                    txtDescription.getText(),
                    txtUnitPrice.getText(),
                    txtQty.getText(),
                    String.valueOf(amount)
                };
                model.addRow(row);
                setTotalAmount();
            }

        }

    }//GEN-LAST:event_addButtonActionPerformed

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        DefaultTableModel dtm = (DefaultTableModel) tblItem.getModel();
        if (tblItem.getSelectedRow() != -1) {
            dtm.removeRow(tblItem.getSelectedRow());
        }
        setTotalAmount();
    }//GEN-LAST:event_removeButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed

        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed

        OrderDTO orderDTO;
        orderDTO = new OrderDTO(
                txtOrderId.getText(),
                parseDate(txtDate.getText()),
                cmbCustId.getSelectedItem().toString()
        );

        DefaultTableModel dtm = (DefaultTableModel) tblItem.getModel();
        ArrayList<OrderDetailDTO> all = new ArrayList<>();
        for (int i = 0; i < dtm.getRowCount(); i++) {
            OrderDetailDTO detailDTO = new OrderDetailDTO(
                    txtOrderId.getText(),
                    dtm.getValueAt(i, 0).toString(),
                    Integer.parseInt(dtm.getValueAt(i, 3).toString()),
                    Double.parseDouble(dtm.getValueAt(i, 2).toString())
            );
            all.add(detailDTO);
        }
        try {

            boolean add = controller.add(orderDTO, all);
            if (add) {
                setOrderId();
                JOptionPane.showMessageDialog(this, "Succesfully added");
                printInvoice();
            }
        } catch (Exception ex) {
            System.out.println("Some thing went wrong");
            Logger.getLogger(PlaceOrderForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void cmbCustIdItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCustIdItemStateChanged
        if (cmbCustId.getSelectedIndex() != -1) {
            try {
                CustomerDTO customerDTO = new CustomerDTO(cmbCustId.getSelectedItem().toString());
                customerDTO = (CustomerDTO) controller.get(customerDTO);
                txtName.setText(customerDTO.getName());
            } catch (Exception ex) {
                Logger.getLogger(PlaceOrderForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_cmbCustIdItemStateChanged

    private void cmbItemCodeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbItemCodeItemStateChanged
        cmbItemAction();

    }//GEN-LAST:event_cmbItemCodeItemStateChanged

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        try {
            controller.unRegistorObserver(this);
        } catch (Exception ex) {
            Logger.getLogger(PlaceOrderForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowClosed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PlaceOrderForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JComboBox cmbCustId;
    private javax.swing.JComboBox cmbItemCode;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton removeButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JTable tblItem;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtOrderId;
    private javax.swing.JTextField txtQty;
    private javax.swing.JTextField txtQtyOnHand;
    private javax.swing.JTextField txtTotalAmount;
    private javax.swing.JTextField txtUnitPrice;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update() throws RemoteException {
        JOptionPane.showMessageDialog(this, "Updated");
        loadDataInCombos();
        setOrderId();
    }

}
