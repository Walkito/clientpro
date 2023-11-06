package br.com.edamatec.clientpro.service;

import br.com.edamatec.clientpro.Utils;
import br.com.edamatec.clientpro.model.dao.DAO;
import br.com.edamatec.clientpro.model.entities.Client;
import br.com.edamatec.clientpro.view.EditView;
import br.com.edamatec.clientpro.view.RegisterView;
import br.com.edamatec.clientpro.view.SearchView;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class ClientService {
    DAO<Client> dao = new DAO<>(Client.class);
    
    Font titleFont = new Font("Arial", Font.BOLD, 24);
    Font formLabelFont = new Font("Arial", Font.BOLD, 16);
    
    public void editConfigs(JPanel panel, Object id, JTable table){
        setPanelColor(panel);
        
        JLabel title = new JLabel("Atualizar Cliente");
        title.setFont(titleFont);
        panel.add(title, setConstraints(0, 0, 2, 1));
        panel.add(createEditForm(id, table), setConstraints(1, 1, 2, 1));        
    }
    
    public void searchConfigs(JPanel panel){
        setPanelColor(panel);
        
        JLabel title = new JLabel("Buscar Clientes");
        title.setFont(titleFont);
        panel.add(title, setConstraints(0, 0, 7, 1));
        panel.add(createClientList(panel), setConstraints(1 , 1, 5, 1));
    }
    
    public void registerConfigs(JPanel panel){
        setPanelColor(panel);
        
        JLabel title = new JLabel("Cadastrar Cliente");
        title.setFont(titleFont);
        panel.add(title, setConstraints(0, 0, 2, 1));
        panel.add(createRegisterForm(), setConstraints(1, 1, 2, 1));
    }
    
    public void initialConfigs(JPanel panel){
        setPanelColor(panel);
        panel.add(createMenus(), BorderLayout.NORTH );
        
        JLabel title = new JLabel("<html> Bem-Vindo ao <br> Client Pro </br></html>", SwingConstants.CENTER);
        title.setFont(titleFont);
        title.setForeground(Color.RED);
        
        panel.add(title);

        
    }
    
    private JMenuBar createMenus(){
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Clientes");
        List<JMenuItem> listItems = new ArrayList<>();
        
        listItems.add(new JMenuItem("Cadastrar"));
        listItems.add(new JMenuItem("Buscar"));
        
        for(JMenuItem item: listItems){
            addActionItem(item);
            fileMenu.add(item);
        }
        menuBar.add(fileMenu);
        return menuBar;
    }
    
    private void addActionItem(JMenuItem item){
          switch (item.getText()) {
                case "Cadastrar":
                    item.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            new RegisterView();
                        }
                    });
                    break;
                case "Buscar":
                    item.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            new SearchView();
                        }
                    });
                    break;
            }
    }
    
    private void setPanelColor(JPanel panel){
         panel.setBackground(Color.LIGHT_GRAY);
    }
    
    private GridBagConstraints setConstraints(int x, int y, int width, int height){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = width;
        constraints.gridheight = height;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.NONE;
        
        return constraints;
    }
    
    private JPanel createRegisterForm(){
        JPanel formPanel = new JPanel(new GridBagLayout());
        setPanelColor(formPanel);
        
        createFormLabels(formPanel);
        
        JButton btnCadastrar = new JButton("Cadastrar");
        registerClient(btnCadastrar,         createFormFields(formPanel));
        
        formPanel.add(btnCadastrar, setConstraints(1, 13, 1, 1));
        
        return formPanel;
    }
    
    private JPanel createEditForm(Object id, JTable table){
        int y = 1;
        
        JPanel formPanel = new JPanel(new GridBagLayout());
        setPanelColor(formPanel);
        
        createFormLabels(formPanel);
        
        List<JTextField> clientDatas = insertDataField(createFormFields(), id);
        
        for(JTextField field : clientDatas){
            formPanel.add(field, setConstraints(0, y, 2, 1));
            y +=3;
        }
        
         JButton btnAtualizar = new JButton("Atualizar");
         formPanel.add(btnAtualizar, setConstraints(1, 13, 1, 1));
         editClient(btnAtualizar, clientDatas, table, id);
         
         return formPanel;
    }
    
    private void createFormLabels(JPanel panel){
        List<String> nameLabels = new ArrayList<>(List.of("Nome", "CPF", "Telefone", "E-mail"));
        List<JLabel> listLabelsForm = new ArrayList<>();
        int y = 0;
        
        for(String name : nameLabels){
            listLabelsForm.add(new JLabel(name));
        }
        
        for(JLabel label : listLabelsForm){
            label.setFont(formLabelFont);
            
            panel.add(label, setConstraints(0, y, 2, 1));
            y += 3;
        }
    }
    
    private List<JTextField> createFormFields(JPanel panel){
        List<JTextField>  listFieldsForm = new ArrayList<>();
        int y = 1;
       
       for(int i = 0; i < 4; i++){
           listFieldsForm.add(new JTextField());
       } 
        
       for(JTextField field: listFieldsForm){
           field.setColumns(15);
           panel.add(field, setConstraints(0, y, 2, 1));
           y += 3;
        }
       
       return listFieldsForm;
    }
    
    private List<JTextField> createFormFields(){
        List<JTextField>  listFieldsForm = new ArrayList<>();
       
       for(int i = 0; i < 4; i++){
           listFieldsForm.add(new JTextField());
       } 
        
       for(JTextField field: listFieldsForm){
           field.setColumns(15);
        }
       
       return listFieldsForm;        
    }
    
    private JPanel createClientList(JPanel panel){
        JPanel tablePanel = new JPanel(new GridBagLayout());
        setPanelColor(tablePanel);

        Object[][] data = createDataObject();
        String[] columnNames = {"Id", "Nome", "CPF", "Telefone", "E-mail"};
        
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
       
        tablePanel.add(scrollPane, setConstraints(0, 0, 5, 1));
        
        createButtonsSearch(panel, table);
        return tablePanel;
    }
    
    private void createButtonsSearch(JPanel panel, JTable table){
        List<JButton> listButtons = new ArrayList<>();
        int x = 3;
        
        listButtons.add(new JButton("Alterar"));
        listButtons.add(new JButton("Excluir"));
        
        openEditView(listButtons.get(0), table);
        deleteClient(listButtons.get(1) ,table);
        
        for(JButton button : listButtons){
            panel.add(button, setConstraints(x, 2, 2, 1));
            x += 1;
        }
    }
    
    private Object[][] createDataObject(){
        List<Client> clients = dao.getAll();
        Object[][] data = new Object[clients.size()][5];
        
        for(int i = 0; i < clients.size(); i ++){
            Client client = clients.get(i);
            data[i] = new Object[]{client.getId(), 
                                                    client.getName(), 
                                                    client.getCpf(), 
                                                    client.getTelephone(), 
                                                    client.getEmail()};
        }
        return data;
    }
    
    private DefaultTableModel updateClientTable(){
        String[] columnNames = {"Id", "Nome", "CPF", "Telefone", "E-mail"};
        Object [][] data = createDataObject();
        
        return new DefaultTableModel(data, columnNames);
    }
    
    private void openEditView(JButton button, JTable table){
        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Object id = table.getValueAt(table.getSelectedRow(), 0);
                new EditView(id, table);
            }
        });
    }
    
    private List<JTextField> insertDataField(List<JTextField> editFields, Object id){
        Client client = dao.getById(id);
        
        editFields.get(0).setText(client.getName());
        editFields.get(1).setText(client.getCpf());
        editFields.get(2).setText(client.getTelephone());
        editFields.get(3).setText(client.getEmail());
        
        return editFields;
    }
    
    private void deleteClient(JButton button, JTable table){
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(null,"Tem certeza que deseja excluir o usuário selecionado?", "Confirmação",JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1){
                        Object id = table.getValueAt(selectedRow,0);
                        Client client = dao.getById(id);
                        dao.removeAtomic(client);
                        
                        JOptionPane.showMessageDialog(null,"Cliente excluído com sucesso!");
                        
                        table.setModel(updateClientTable());
                        table.revalidate();
                        table.repaint();
                    } else {
                        JOptionPane.showMessageDialog(null,"Selecione um cliente para excluir.");
                    }                    
                }
            }
    });
    }
    
    private void registerClient(JButton button, List<JTextField> listFields){        
        button.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Client client = new Client(listFields.get(0).getText(), 
                                                   listFields.get(1).getText(),
                                        listFields.get(2).getText(),
                                                listFields.get(3).getText());
            if(!Utils.validateCPF(listFields.get(1).getText())){
                JOptionPane.showMessageDialog(null,"CPF Inválido!");
            } else if (!Utils.validateEmail(listFields.get(3).getText())){
                JOptionPane.showMessageDialog(null, "E-mail Inválido!");
            } else {
                dao.includeAtomic(client);
                JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
                for(JTextField field : listFields){
                    field.setText("");
                }
            }
        }
        });
    }
    
    private void editClient(JButton button, List<JTextField> clientDatas, JTable table, Object id){
        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(null,"Tem certeza que deseja atualizar o usuário selecionado?", "Confirmação",JOptionPane.YES_NO_OPTION);
                
                if(response == JOptionPane.YES_OPTION){
                    Client client = dao.getById(id);
                    client.setName(clientDatas.get(0).getText());
                    client.setCpf(clientDatas.get(1).getText());
                    client.setTelephone(clientDatas.get(2).getText());
                    client.setEmail(clientDatas.get(3).getText());
                    
                    if(!Utils.validateCPF(clientDatas.get(1).getText())){
                        JOptionPane.showMessageDialog(null,"CPF Inválido!");
                    } else if (!Utils.validateEmail(clientDatas.get(3).getText())){
                        JOptionPane.showMessageDialog(null, "E-mail Inválido!");
                    } else {
                        dao.editAtomic(client);
                        JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!");
                    }
                    
                    table.setModel(updateClientTable());
                    table.revalidate();
                    table.repaint();
                }
            }
        });
    }
}
