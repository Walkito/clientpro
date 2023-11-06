package br.com.edamatec.clientpro.controller;

import br.com.edamatec.clientpro.service.ClientService;
import javax.swing.JPanel;
import javax.swing.JTable;

public class ClientController {

    ClientService service = new ClientService();
    
    public void initialConfigs(JPanel panel){
        service.initialConfigs(panel);
    }
    
    public void registerConfigs(JPanel panel){
        service.registerConfigs(panel);
    }
    
    public void searchConfigs(JPanel panel){
        service.searchConfigs(panel);
    }
    
    public void editConfigs(JPanel panel, Object id, JTable table){
        service.editConfigs(panel, id, table);
    }
    
    public void deleteConfigs(JPanel panel){
        
    }
}
