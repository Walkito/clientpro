package br.com.edamatec.clientpro.view;

import br.com.edamatec.clientpro.controller.ClientController;
import br.com.edamatec.clientpro.model.components.DefaultFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JTable;

public class FilterView {
    ClientController controller = new ClientController();
    
    public FilterView(JTable table){
            DefaultFrame frame = new DefaultFrame("Filtrar Cliente", 2);
            JPanel mainPanel = new JPanel(new GridBagLayout());

            controller.filterConfigs(mainPanel, table);

            frame.add(mainPanel);
            frame.pack();        
}
}
