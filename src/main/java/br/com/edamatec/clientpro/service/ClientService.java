package br.com.edamatec.clientpro.service;

import br.com.edamatec.clientpro.utils.Utils;
import br.com.edamatec.clientpro.model.dao.DAO;
import br.com.edamatec.clientpro.model.entities.Client;
import br.com.edamatec.clientpro.view.EditView;
import br.com.edamatec.clientpro.view.FilterView;
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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

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
        
    public void filterConfigs(JPanel panel, JTable table){
        setPanelColor(panel);
        
        JLabel title = new JLabel("Buscar Cliente");
        title.setFont(titleFont);
        panel.add(title, setConstraints(0, 0, 2, 1));
        panel.add(createFilterForm(table), setConstraints(1, 1, 2, 1));    
    }
    
    public void initialConfigs(JPanel panel){
        setPanelColor(panel);
        panel.add(createMenus(), BorderLayout.NORTH );
        
        JLabel title = new JLabel("<html> Bem-Vindo ao <br> Client Pro </br></html>", SwingConstants.CENTER);
        title.setFont(titleFont);
        title.setForeground(Color.RED);
        
        panel.add(title);
    }
    
    public void registerConfigs(JPanel panel){
        setPanelColor(panel);
        
        JLabel title = new JLabel("Cadastrar Cliente");
        title.setFont(titleFont);
        panel.add(title, setConstraints(0, 0, 2, 1));
        panel.add(createRegisterForm(), setConstraints(1, 1, 2, 1));
    }
    
    public void searchConfigs(JPanel panel){
        setPanelColor(panel);
        
        JLabel title = new JLabel("Buscar Clientes");
        title.setFont(titleFont);
        panel.add(title, setConstraints(0, 0, 7, 1));
        panel.add(createClientList(panel), setConstraints(1 , 1, 5, 1));
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
    
    private JPanel createFilterForm(JTable table){
        JPanel formPanel = new JPanel(new GridBagLayout());
        setPanelColor(formPanel);
        
        createFormLabels(formPanel);
        
        JButton btnBuscar = new JButton("Buscar");
        filterClient(btnBuscar, createFormFields(formPanel), table);
        
        formPanel.add(btnBuscar, setConstraints(1, 13, 1, 1));
        
        return formPanel;
    }
    
    private JPanel createEditForm(Object id, JTable table){
        int y = 1;
        
        JPanel formPanel = new JPanel(new GridBagLayout());
        setPanelColor(formPanel);
        
        createFormLabels(formPanel);
        
        List<JFormattedTextField> clientDatas = insertDataField(createFormFields(), id);
        
        for(JFormattedTextField field : clientDatas){
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
    
    private List<MaskFormatter> createMasks(){
        List<MaskFormatter> masks = new ArrayList<>();
        try{
            masks.add(new MaskFormatter("###.###.###-##"));
            masks.add(new MaskFormatter("(##) #####-####"));
        } catch (ParseException e){
            e.printStackTrace();
        }
        return masks;
    }
    
    private List<JFormattedTextField> createFormFields(JPanel panel){
        List<MaskFormatter> masks = createMasks();
        
        List<JFormattedTextField>  listFieldsForm = new ArrayList<>();
        int y = 1;
       
       for(int i = 0; i < 4; i++){
           if(i == 1){
                listFieldsForm.add(new JFormattedTextField(masks.get(0)));
           } else if (i == 2) {
               listFieldsForm.add(new JFormattedTextField(masks.get(1)));
           } else {
               listFieldsForm.add(new JFormattedTextField());
           }
       } 
        
       for(JFormattedTextField field: listFieldsForm){
           setEmptyTextField(field);
           field.setColumns(15);
           panel.add(field, setConstraints(0, y, 2, 1));
           y += 3;
        }
       
       return listFieldsForm;
    }
    
    private List<JFormattedTextField> createFormFields(){
        List<MaskFormatter> masks = createMasks();
        List<JFormattedTextField>  listFieldsForm = new ArrayList<>();
       
       for(int i = 0; i < 4; i++){
           if(i == 1){
                listFieldsForm.add(new JFormattedTextField(masks.get(0)));
           } else if (i == 2) {
               listFieldsForm.add(new JFormattedTextField(masks.get(1)));
           } else {
               listFieldsForm.add(new JFormattedTextField());
           }
       } 
        
       for(JFormattedTextField field: listFieldsForm){
           setEmptyTextField(field);
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
        int x = 1;
        
        listButtons.add(new JButton("Alterar"));
        listButtons.add(new JButton("Excluir"));
        listButtons.add(new JButton("Filtrar"));
        
        openEditView(listButtons.get(0), table);
        deleteClient(listButtons.get(1) ,table);
        openFilterView(listButtons.get(2), table);
        
        for(JButton button : listButtons){
            panel.add(button, setConstraints(x, 2, 1, 1));
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
    
    private Object[][] createDataObject(List<Client> filterClients){
        Object[][] data = new Object[filterClients.size()][5];
        
        for(int i = 0; i < filterClients.size(); i ++){
            Client client = filterClients.get(i);
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
    
    private DefaultTableModel updateClientTable(List<Client> filterClients){
        String[] columnNames = {"Id", "Nome", "CPF", "Telefone", "E-mail"};
        Object [][] data = createDataObject(filterClients);
        
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
    
    private List<JFormattedTextField> insertDataField(List<JFormattedTextField> editFields, Object id){
        Client client = dao.getById(id);
        
        editFields.get(0).setText(client.getName());
        editFields.get(1).setText(client.getCpf());
        editFields.get(2).setText(client.getTelephone());
        editFields.get(3).setText(client.getEmail());
        
        return editFields;
    }
    
    private void openFilterView(JButton button, JTable table){
        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new FilterView(table);
            }
        });
    }
    
    private void setEmptyTextField(JFormattedTextField textField){
        textField.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e) {

            }
            
            @Override
            public void focusLost(FocusEvent e) {
                String value = textField.getText().replaceAll("[().\\s-]+", "");
                if(value.isEmpty()) {
                    textField.setValue(null);
                }
            }
            
        });
    }
    
    
    private void filterClient(JButton button, List<JFormattedTextField> listFields, JTable table){
        button.addActionListener(new  ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            String cpfValue = listFields.get(1).getText().replaceAll("[.()\\s-]+", "");
            String  telephoneValue = listFields.get(2).getText().replaceAll("[.()\\s-]+", "");
            
                List<Client> filterClients = dao.searchByFilters(
                                                                                                    listFields.get(0).getText(),
                                                                                                    cpfValue,
                                                                                                    telephoneValue,
                                                                                                    listFields.get(3).getText()
                );
                table.setModel(updateClientTable(filterClients));
                table.revalidate();
                table.repaint();
            }
        });
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
    
    private void registerClient(JButton button, List<JFormattedTextField> listFields){        
        button.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
           String cpfValue = listFields.get(1).getText().replaceAll("[.()\\s-]+", "");
           String telephoneValue = listFields.get(2).getText().replaceAll("[.()\\s-]+", "");
            
            Client client = new Client(listFields.get(0).getText(), 
                                                   cpfValue,
                                        telephoneValue,
                                                listFields.get(3).getText());
            if(!Utils.validateCPF(cpfValue)){
                JOptionPane.showMessageDialog(null,"CPF Inválido!");
            } else if (!Utils.validateEmail(listFields.get(3).getText())){
                JOptionPane.showMessageDialog(null, "E-mail Inválido!");
            } else {
                dao.includeAtomic(client);
                JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
                for(JFormattedTextField field : listFields){
                    field.setText("");
                }
            }
        }
        });
    }
    
    private void editClient(JButton button, List<JFormattedTextField> clientDatas, JTable table, Object id){
        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(null,"Tem certeza que deseja atualizar o usuário selecionado?", "Confirmação",JOptionPane.YES_NO_OPTION);
                
                if(response == JOptionPane.YES_OPTION){
                    String cpfValue = clientDatas.get(1).getText().replaceAll("[.()\\s-]+", "");;
                    String telephoneValue = clientDatas.get(2).getText().replaceAll("[.()\\s-]+", "");;
                    
                    Client client = dao.getById(id);
                    client.setName(clientDatas.get(0).getText());
                    client.setCpf(cpfValue);
                    client.setTelephone(telephoneValue);
                    client.setEmail(clientDatas.get(3).getText());
                    
                    if(!Utils.validateCPF(cpfValue.toString())){
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
