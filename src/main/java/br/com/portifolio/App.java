package br.com.portifolio;

import br.com.portifolio.form.PrincipalForm;

import javax.swing.*;


/**
 * Implementação de um CRUD com Hibernate Postgresql e Swing
 * @author Rodrigo Ferreira
 */
public class App 
{
    public static void main( String[] args )
    {
        SwingUtilities.invokeLater(() -> {
            PrincipalForm calc = new PrincipalForm();
            calc.setVisible(true);
        });
    }
}
