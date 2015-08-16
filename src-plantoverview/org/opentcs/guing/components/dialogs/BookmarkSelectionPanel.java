/*
 * openTCS copyright information:
 * Copyright (c) 2005-2011 ifak e.V.
 * Copyright (c) 2012 Fraunhofer IML
 *
 * This program is free software and subject to the MIT license. (For details,
 * see the licensing information (LICENSE.txt) you should have received with
 * this copy of the software.)
 */

package org.opentcs.guing.components.dialogs;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import org.opentcs.data.model.visualization.ViewBookmark;

/**
 *
 * @author Heinz Huber (Fraunhofer IML)
 */
public class BookmarkSelectionPanel
    extends DialogContent {

  /**
   * Die zur Auswahl stehenden Namen.
   */
  private final List<ViewBookmark> fItems;

  /**
   * Creates new form PointPanel
   *
   * @param items
   * @param showButtonAdd
   */
  public BookmarkSelectionPanel(List<ViewBookmark> items,
                                boolean showButtonAdd) {
    initComponents();
    fItems = new LinkedList<>(items);
    Collections.sort(fItems, new BookmarkNameComparator());

    List<String> names = new LinkedList<>();
    for (ViewBookmark curBookmark : fItems) {
      names.add(curBookmark.getLabel());
    }

    comboBoxItems.setModel(new DefaultComboBoxModel(names.toArray()));
    buttonAddItem.setVisible(showButtonAdd);
  }

  /**
   * Liefert das ausgew�hlte Ziel, zu dem das Fahrzeug hinbeordert werden soll.
   *
   * @return das Ziel oder
   * <code>null</code>, falls kein Ziel ausgew�hlt wurde
   */
  public ViewBookmark getSelectedItem() {
    int index = comboBoxItems.getSelectedIndex();

    if (index == -1) {
      return null;
    }

    return fItems.get(index);
  }

  @Override
  public void update() {
    // wird nicht ben�tigt
  }

  @Override
  public void initFields() {
    // wird nicht ben�tigt
  }

  // CHECKSTYLE:OFF
  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        labelItems = new javax.swing.JLabel();
        comboBoxItems = new javax.swing.JComboBox();
        buttonAddItem = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        labelItems.setFont(labelItems.getFont());
        labelItems.setText("Name:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 0, 4);
        add(labelItems, gridBagConstraints);

        comboBoxItems.setFont(comboBoxItems.getFont());
        comboBoxItems.setMinimumSize(new java.awt.Dimension(100, 22));
        comboBoxItems.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        add(comboBoxItems, gridBagConstraints);

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/opentcs/guing/res/labels"); // NOI18N
        buttonAddItem.setText(bundle.getString("BookmarkSelectionPanel.add")); // NOI18N
        buttonAddItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddItemActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        add(buttonAddItem, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

	private void buttonAddItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddItemActionPerformed

	}//GEN-LAST:event_buttonAddItemActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAddItem;
    private javax.swing.JComboBox comboBoxItems;
    private javax.swing.JLabel labelItems;
    // End of variables declaration//GEN-END:variables
  // CHECKSTYLE:ON

  /**
   * Compares ViewBookmarks by their names.
   */
  private static class BookmarkNameComparator
      implements Comparator<ViewBookmark> {

    /**
     * Creates a new instance.
     */
    public BookmarkNameComparator() {
      // Do nada.
    }

    @Override
    public int compare(ViewBookmark item1, ViewBookmark item2) {
      String s1 = item1.getLabel();
      String s2 = item2.getLabel();
      s1 = s1.toLowerCase();
      s2 = s2.toLowerCase();

      return s1.compareTo(s2);
    }
  }
}
