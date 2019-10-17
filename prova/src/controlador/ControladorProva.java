/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import tela.manutencao.ManutencaoProva;


import dao.DaoProva;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import modelo.Prova;
import tela.manutencao.ManutencaoProva;
import java.util.List;

import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Administrador
 */
public class ControladorProva {

    public static void inserir(ManutencaoProva man){
        Prova objeto = new Prova();
        objeto.setNome(man.jtfNome.getText());
        objeto.setSigla_estado(man.jtfSigla_estado.getText());
        objeto.setNr_habitantes(Integer.parseInt(man.jtfNr_habitantes.getText()));
        objeto.setData(LocalDate.parse(man.jtfData.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setArea(Double.parseDouble(man.jtfArea.getText()));
        objeto.setDistancia_capital(Integer.parseInt(man.jtfDistancia_capital.getText()));

        
        
        boolean resultado = DaoProva.inserir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
            if (man.listagem != null) {
     atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}
man.dispose();//fechar a tela da manutenção
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
}

    public static void alterar(ManutencaoProva man){
        Prova objeto = new Prova();
        //definir todos os atributos
        objeto.setCodigo(Integer.parseInt(man.jtfCodigo.getText()));
        objeto.setNome(man.jtfNome.getText());
        objeto.setSigla_estado(man.jtfSigla_estado.getText());
        objeto.setNr_habitantes(Integer.parseInt(man.jtfNr_habitantes.getText()));
        objeto.setData(LocalDate.parse(man.jtfData.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setArea(Double.parseDouble(man.jtfArea.getText()));
        objeto.setDistancia_capital(Integer.parseInt(man.jtfDistancia_capital.getText()));
        
        boolean resultado = DaoProva.alterar(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
            if (man.listagem != null) {
     atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}
man.dispose();//fechar a tela da manutenção
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }

     public static void excluir(ManutencaoProva man){
        Prova objeto = new Prova();
        objeto.setCodigo(Integer.parseInt(man.jtfCodigo.getText())); //só precisa definir a chave primeira
        
        boolean resultado = DaoProva.excluir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
            if (man.listagem != null) {
     atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}
man.dispose();//fechar a tela da manutenção
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }
    
     public static void atualizarTabela(JTable tabela) {
        DefaultTableModel modelo = new DefaultTableModel();
        //definindo o cabeçalho da tabela
        modelo.addColumn("Codigo");
        modelo.addColumn("Nome");
        modelo.addColumn("Sigla");
        modelo.addColumn("Número de habitantes");
        modelo.addColumn("Data emancipação");
        modelo.addColumn("Área total");
        modelo.addColumn("Distância da capital");
        List<Prova> resultados = DaoProva.consultar();
        for (Prova objeto : resultados) {
            Vector linha = new Vector();
            
            //definindo o conteúdo da tabela
            linha.add(objeto.getCodigo());
            linha.add(objeto.getNome());
            linha.add(objeto.getSigla_estado());
            linha.add(objeto.getNr_habitantes());
            linha.add(objeto.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            linha.add(objeto.getArea());
            linha.add(objeto.getDistancia_capital());
            
            modelo.addRow(linha); //adicionando a linha na tabela
        }
        tabela.setModel(modelo);
    }
     public static void atualizaCampos(ManutencaoProva man, int pk){ 
        Prova objeto = DaoProva.consultar(pk);
        //Definindo os valores do campo na tela (um para cada atributo/campo)
        man.jtfCodigo.setText(objeto.getCodigo().toString());
        man.jtfNome.setText(objeto.getNome());
        man.jtfSigla_estado.setText(objeto.getSigla_estado());
       man.jtfNr_habitantes.setText(objeto.getNr_habitantes().toString());
        man.jtfData.setText(objeto.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
       man.jtfArea.setText(objeto.getArea().toString());
       man.jtfDistancia_capital.setText(objeto.getDistancia_capital().toString());
        
        man.jtfCodigo.setEnabled(false); //desabilitando o campo código
        man.btnAdicionar.setEnabled(false); //desabilitando o botão adicionar
    }
}
