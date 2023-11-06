/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package br.com.edamatec.clientpro.view;

import br.com.edamatec.clientpro.controller.ClientController;
import br.com.edamatec.clientpro.model.components.DefaultFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JTable;

public class EditView {
    ClientController controller = new ClientController();
    
    public EditView(Object id, JTable table){
            DefaultFrame frame = new DefaultFrame("Atualizar Cliente", 2);
            JPanel mainPanel = new JPanel(new GridBagLayout());

            controller.editConfigs(mainPanel, id, table);

            frame.add(mainPanel);
            frame.pack();        
    }
}
