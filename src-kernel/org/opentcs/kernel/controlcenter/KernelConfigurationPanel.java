/*
 * openTCS copyright information:
 * Copyright (c) 2012 Fraunhofer IML
 *
 * This program is free software and subject to the MIT license. (For details,
 * see the licensing information (LICENSE.txt) you should have received with
 * this copy of the software.)
 */
package org.opentcs.kernel.controlcenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;
import javax.swing.JPanel;
import org.opentcs.access.ConfigurationItemTO;
import org.opentcs.access.Kernel;

/**
 * A kernel configuration panel.
 * 
 * @author Philipp Seifert (Fraunhofer IML)
 */
final class KernelConfigurationPanel
    extends JPanel {

  /**
   * This class's Logger.
   */
  private static final Logger log =
      Logger.getLogger(KernelConfigurationPanel.class.getName());
  /**
   * A flag indicating whether this KernelExtension has been plugged in already.
   */
  private boolean pluggedIn;
  /**
   * The proxy kernel this client has to communicate with.
   */
  private final Kernel kernel;
  /**
   * List containing all configuration items.
   */
  private List<ConfigurationItemTO> configList;

  /**
   * Creates new form KernelConfigurationPanel.
   *
   * @param kernel The kernel.
   */
  public KernelConfigurationPanel(Kernel kernel) {
    this.kernel = Objects.requireNonNull(kernel, "kernel is null");

    Set<ConfigurationItemTO> configs = kernel.getConfigurationItems();
    configList = new ArrayList<>(configs);
    Collections.sort(configList);
    initComponents();
  }

  /**
   * Checks whether this panel should currently be shown or not.
   *
   * @return <code>true</code> if, and only if, this panel should currently be
   * shown.
   */
  public boolean isPluggedIn() {
    return pluggedIn;
  }

  /**
   * Enables this panel.
   */
  public void plugIn() {
    if (pluggedIn) {
      throw new IllegalStateException("Already plugged in.");
    }
    switch (kernel.getState()) {
      case OPERATING:
        setSimulationConfigEnabled(true);
        break;
      default:
        setSimulationConfigEnabled(false);
    }
    pluggedIn = true;
  }

  /**
   * Disables this panel.
   */
  public void plugOut() {
    if (!pluggedIn) {
      throw new IllegalStateException("Not plugged in.");
    }
    pluggedIn = false;
  }

  /**
   * Enables or disables configuration elements concerning simulation.
   *
   * @param enabled Whether or not to enable the elements.
   */
  private void setSimulationConfigEnabled(boolean enabled) {
    timeFactorNewSpinner.setEnabled(enabled);
    timeFactorSetBtn.setEnabled(enabled);
  }

  /**
   * Updates this panel's contents.
   */
  private void updateContents() {
    double simTimeFactor = kernel.getSimulationTimeFactor();
    timeFactorCurrentTxt.setText(String.format("%.0f", simTimeFactor));
    displayItemData((ConfigurationItemTO)configJList.getSelectedValue());
  }

  /**
   * Show the details of the specified ConfigurationItem in the panel.
   * If <code>null</code>, the panel's content is cleared.
   * @param item the configuration item
   */
  private void displayItemData(ConfigurationItemTO item) {
    if(item == null) {
      namespaceTextField.setText(null);
      keyTextfield.setText(null);
      descriptionTxtArea.setText(null);
      valuetextField.setText(null);
      dataTypeTextField.setText(null);
    }
    else {
      namespaceTextField.setText(item.getNamespace());
      keyTextfield.setText(item.getKey());
      descriptionTxtArea.setText(item.getDescription());
      valuetextField.setText(item.getValue());
      dataTypeTextField.setText(item.getConstraint().getType().toString());
    }
  }

  // CHECKSTYLE:OFF
  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {
    java.awt.GridBagConstraints gridBagConstraints;

    specificConfigPanel = new javax.swing.JPanel();
    simulationConfigPanel = new javax.swing.JPanel();
    timeFactorCurrentLbl = new javax.swing.JLabel();
    timeFactorCurrentTxt = new javax.swing.JTextField();
    timeFactorNewLbl = new javax.swing.JLabel();
    timeFactorNewSpinner = new javax.swing.JSpinner();
    timeFactorSetBtn = new javax.swing.JButton();
    genericConfigPanel = new javax.swing.JPanel();
    configsPanel = new javax.swing.JPanel();
    configsScrollPane = new javax.swing.JScrollPane();
    configJList = new javax.swing.JList(configList.toArray());
    buttonPanel = new javax.swing.JPanel();
    configureButton = new javax.swing.JButton();
    refreshButton = new javax.swing.JButton();
    selectedItemPanel = new javax.swing.JPanel();
    namespaceLabel = new javax.swing.JLabel();
    namespaceTextField = new javax.swing.JTextField();
    keyLabel = new javax.swing.JLabel();
    keyTextfield = new javax.swing.JTextField();
    descriptionLabel = new javax.swing.JLabel();
    jScrollPane1 = new javax.swing.JScrollPane();
    descriptionTxtArea = new javax.swing.JTextArea();
    valuetextField = new javax.swing.JTextField();
    valueLabel = new javax.swing.JLabel();
    dataType = new javax.swing.JLabel();
    dataTypeTextField = new javax.swing.JTextField();

    setName("kernelconfigPanel"); // NOI18N
    setLayout(new java.awt.GridLayout(1, 2));

    specificConfigPanel.setName("specificConfigPanel"); // NOI18N
    specificConfigPanel.setLayout(new java.awt.BorderLayout());

    java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/opentcs/kernel/controlcenter/Bundle"); // NOI18N
    simulationConfigPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("simulationProperties"))); // NOI18N
    simulationConfigPanel.setLayout(new java.awt.GridBagLayout());

    timeFactorCurrentLbl.setText(bundle.getString("simulationTimeFactorCurrent")); // NOI18N
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
    simulationConfigPanel.add(timeFactorCurrentLbl, gridBagConstraints);

    timeFactorCurrentTxt.setEditable(false);
    timeFactorCurrentTxt.setColumns(4);
    timeFactorCurrentTxt.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
    timeFactorCurrentTxt.setText("1");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
    simulationConfigPanel.add(timeFactorCurrentTxt, gridBagConstraints);

    timeFactorNewLbl.setText(bundle.getString("simulationTimeFactorNew")); // NOI18N
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
    simulationConfigPanel.add(timeFactorNewLbl, gridBagConstraints);

    timeFactorNewSpinner.setModel(new javax.swing.SpinnerNumberModel(1.0d, 1.0d, 10.0d, 1.0d));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
    simulationConfigPanel.add(timeFactorNewSpinner, gridBagConstraints);

    timeFactorSetBtn.setText(bundle.getString("setTimeFactor")); // NOI18N
    timeFactorSetBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        timeFactorSetBtnActionPerformed(evt);
      }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 4;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    gridBagConstraints.weightx = 1.0;
    simulationConfigPanel.add(timeFactorSetBtn, gridBagConstraints);

    specificConfigPanel.add(simulationConfigPanel, java.awt.BorderLayout.NORTH);

    add(specificConfigPanel);

    genericConfigPanel.setName("genericConfigPanel"); // NOI18N
    genericConfigPanel.setLayout(new java.awt.GridBagLayout());

    configsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("ConfigurationItems"))); // NOI18N
    configsPanel.setName("configsPanel"); // NOI18N
    configsPanel.setLayout(new java.awt.BorderLayout());

    configJList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    configJList.setName("configJList"); // NOI18N
    configJList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
      public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
        configJListValueChanged(evt);
      }
    });
    configsScrollPane.setViewportView(configJList);

    configsPanel.add(configsScrollPane, java.awt.BorderLayout.CENTER);

    buttonPanel.setName("buttonPanel"); // NOI18N
    buttonPanel.setLayout(new java.awt.GridLayout(1, 0));

    configureButton.setText("Configure");
    configureButton.setEnabled(false);
    configureButton.setName("configureButton"); // NOI18N
    configureButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        configureButtonActionPerformed(evt);
      }
    });
    buttonPanel.add(configureButton);

    refreshButton.setText(bundle.getString("Refresh")); // NOI18N
    refreshButton.setName("refreshButton"); // NOI18N
    refreshButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        refreshButtonActionPerformed(evt);
      }
    });
    buttonPanel.add(refreshButton);

    configsPanel.add(buttonPanel, java.awt.BorderLayout.SOUTH);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weighty = 1.0;
    genericConfigPanel.add(configsPanel, gridBagConstraints);

    selectedItemPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("SelectedConfigItem"))); // NOI18N
    selectedItemPanel.setName("selectedItemPanel"); // NOI18N
    selectedItemPanel.setLayout(new java.awt.GridBagLayout());

    namespaceLabel.setText(bundle.getString("SelectedItemNamespace")); // NOI18N
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
    selectedItemPanel.add(namespaceLabel, gridBagConstraints);

    namespaceTextField.setEditable(false);
    namespaceTextField.setName("namespaceTextField"); // NOI18N
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.ipadx = 144;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
    selectedItemPanel.add(namespaceTextField, gridBagConstraints);

    keyLabel.setText(bundle.getString("SelectedItemKey")); // NOI18N
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
    selectedItemPanel.add(keyLabel, gridBagConstraints);

    keyTextfield.setEditable(false);
    keyTextfield.setName("keyTextfield"); // NOI18N
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.ipadx = 144;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
    selectedItemPanel.add(keyTextfield, gridBagConstraints);

    descriptionLabel.setText(bundle.getString("SelectedItemDescription")); // NOI18N
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
    selectedItemPanel.add(descriptionLabel, gridBagConstraints);

    descriptionTxtArea.setEditable(false);
    descriptionTxtArea.setColumns(20);
    descriptionTxtArea.setFont(keyTextfield.getFont());
    descriptionTxtArea.setLineWrap(true);
    descriptionTxtArea.setRows(4);
    descriptionTxtArea.setWrapStyleWord(true);
    descriptionTxtArea.setName("descriptionTextArea"); // NOI18N
    jScrollPane1.setViewportView(descriptionTxtArea);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    selectedItemPanel.add(jScrollPane1, gridBagConstraints);

    valuetextField.setEditable(false);
    valuetextField.setName("valueTextField"); // NOI18N
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.ipadx = 144;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
    selectedItemPanel.add(valuetextField, gridBagConstraints);

    valueLabel.setText(bundle.getString("SelectedItemValue")); // NOI18N
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    selectedItemPanel.add(valueLabel, gridBagConstraints);

    dataType.setText(bundle.getString("SelectedItemDataType")); // NOI18N
    dataType.setMaximumSize(new java.awt.Dimension(60, 14));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 4;
    gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 0);
    selectedItemPanel.add(dataType, gridBagConstraints);
    dataType.getAccessibleContext().setAccessibleName("dataType");

    dataTypeTextField.setEditable(false);
    dataTypeTextField.setName("datatypeTextField"); // NOI18N
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 4;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    selectedItemPanel.add(dataTypeTextField, gridBagConstraints);
    dataTypeTextField.getAccessibleContext().setAccessibleName("dataTypeTextField");

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    genericConfigPanel.add(selectedItemPanel, gridBagConstraints);

    add(genericConfigPanel);

    getAccessibleContext().setAccessibleName(bundle.getString("Configuration")); // NOI18N
  }// </editor-fold>//GEN-END:initComponents

  private void configJListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_configJListValueChanged
    if (!configJList.isSelectionEmpty()) {
      ConfigurationItemTO selectedItem =
          (ConfigurationItemTO) configJList.getSelectedValue();
      displayItemData(selectedItem);
      configureButton.setEnabled(true);
    }
    else {
      displayItemData(null);
      configureButton.setEnabled(false);
    }

  }//GEN-LAST:event_configJListValueChanged

  private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
    ConfigurationItemTO selected = configJList.getSelectedValue();

    Set<ConfigurationItemTO> configs = kernel.getConfigurationItems();
    configList = new ArrayList<>(configs);
    Collections.sort(configList);
    ConfigurationItemTO[] data = new ConfigurationItemTO[configList.size()];
    int i = 0;
    Iterator<ConfigurationItemTO> it = configList.iterator();
    while (it.hasNext()) {
      data[i] = it.next();
      i++;
    }
    configJList.setListData(data);

    configJList.setSelectedValue(selected, false);
    displayItemData(selected);
  }//GEN-LAST:event_refreshButtonActionPerformed

  private void timeFactorSetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeFactorSetBtnActionPerformed
    kernel.setSimulationTimeFactor((Double) timeFactorNewSpinner.getValue());
    updateContents();
  }//GEN-LAST:event_timeFactorSetBtnActionPerformed

  private void configureButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_configureButtonActionPerformed

    ConfigurationItemDialog configDialog;
    ConfigurationItemTO configItem =
        (ConfigurationItemTO) configJList.getSelectedValue();
    configDialog = new ConfigurationItemDialog(kernel, configItem);
    configDialog.setVisible(true);

    displayItemData(configItem);
  }//GEN-LAST:event_configureButtonActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JPanel buttonPanel;
  private javax.swing.JList<ConfigurationItemTO> configJList;
  private javax.swing.JPanel configsPanel;
  private javax.swing.JScrollPane configsScrollPane;
  private javax.swing.JButton configureButton;
  private javax.swing.JLabel dataType;
  private javax.swing.JTextField dataTypeTextField;
  private javax.swing.JLabel descriptionLabel;
  private javax.swing.JTextArea descriptionTxtArea;
  private javax.swing.JPanel genericConfigPanel;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JLabel keyLabel;
  private javax.swing.JTextField keyTextfield;
  private javax.swing.JLabel namespaceLabel;
  private javax.swing.JTextField namespaceTextField;
  private javax.swing.JButton refreshButton;
  private javax.swing.JPanel selectedItemPanel;
  private javax.swing.JPanel simulationConfigPanel;
  private javax.swing.JPanel specificConfigPanel;
  private javax.swing.JLabel timeFactorCurrentLbl;
  private javax.swing.JTextField timeFactorCurrentTxt;
  private javax.swing.JLabel timeFactorNewLbl;
  private javax.swing.JSpinner timeFactorNewSpinner;
  private javax.swing.JButton timeFactorSetBtn;
  private javax.swing.JLabel valueLabel;
  private javax.swing.JTextField valuetextField;
  // End of variables declaration//GEN-END:variables
  // CHECKSTYLE:ON
}
