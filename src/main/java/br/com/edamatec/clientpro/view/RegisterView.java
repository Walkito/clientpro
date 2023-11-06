package br.com.edamatec.clientpro.view;

import br.com.edamatec.clientpro.controller.ClientController;
import br.com.edamatec.clientpro.model.components.DefaultFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

public class RegisterView {
        ClientController controller = new ClientController();
         
        public RegisterView(){
            DefaultFrame frame = new DefaultFrame("Cadastrar Cliente", 2);
            JPanel mainPanel = new JPanel(new GridBagLayout());

            controller.registerConfigs(mainPanel);

            frame.add(mainPanel);
            frame.pack();        
        } 
    
}
