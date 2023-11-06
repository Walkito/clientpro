package br.com.edamatec.clientpro.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes")
public class Client {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(length = 75, nullable = false)
    private String name;
    
    @Column(length = 11, nullable = false, unique = true)
    private String cpf;
    
    @Column(length = 11, unique = true)
    private String telephone;
    
    @Column(length = 30, unique = true)
    private String email;

    public Client(String name, String cpf, String telephone, String email) {
        this.name = name;
        this.cpf = cpf;
        this.telephone = telephone;
        this.email = email;
    }

   public Client(){
       
   } 
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
