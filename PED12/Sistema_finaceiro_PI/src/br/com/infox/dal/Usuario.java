/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infox.dal;

/**
 *
 * @author pc
 */

    
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class Usuario {
    
    private String nome;
    private int id_conta;

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the id_conta
     */
    public int getId_conta() {
        return id_conta;
    }

    /**
     * @param id_conta the id_conta to set
     */
    public void setId_conta(int id_conta) {
        this.id_conta = id_conta;
    }
     
}
