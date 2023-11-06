package br.com.edamatec.clientpro.view;

import br.com.edamatec.clientpro.controller.ClientController;
import br.com.edamatec.clientpro.model.components.DefaultFrame;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

public class MainView {
    
    ClientController controller = new ClientController();
    
    public MainView(){
        DefaultFrame frame = new DefaultFrame("Clientes", 3);
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        controller.initialConfigs(mainPanel);
       
        frame.add(mainPanel);
        frame.setSize(300, 300);
    }
}
