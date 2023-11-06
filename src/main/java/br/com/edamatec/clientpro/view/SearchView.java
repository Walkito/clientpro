package br.com.edamatec.clientpro.view;

import br.com.edamatec.clientpro.controller.ClientController;
import br.com.edamatec.clientpro.model.components.DefaultFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

public class SearchView {
        ClientController controller = new ClientController();
         
        public SearchView(){
            DefaultFrame frame = new DefaultFrame("Buscar Clientes", 2);
            JPanel mainPanel = new JPanel(new GridBagLayout());

            controller.searchConfigs(mainPanel);

            frame.add(mainPanel);
            frame.pack();        
        } 
}
