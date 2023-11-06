package br.com.edamatec.clientpro.model.components;

import javax.swing.JFrame;

public class DefaultFrame extends JFrame {
    
    public DefaultFrame(String title, int operation){
        setTitle(title);
        setDefaultCloseOperation(operation);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
