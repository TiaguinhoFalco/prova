/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import modelo.Prova;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Administrador
 */
public class DaoProva {
    
     public static boolean inserir(Prova objeto) {
        String sql = "INSERT INTO prova (nome, sigla_estado, nr_habitantes, data, area, distancia_capital ) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, objeto.getNome());
            ps.setString(2, objeto.getSigla_estado());
            ps.setInt(3, objeto.getNr_habitantes());
            ps.setDate(4, Date.valueOf(objeto.getData()));
            ps.setDouble(5, objeto.getArea());
            ps.setInt(6, objeto.getDistancia_capital());
            
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
     
      public static void main(String[] args) {
        Prova objeto = new Prova();
        objeto.setNome("Brasil");
        objeto.setSigla_estado("BRA");
        objeto.setNr_habitantes(4);
        objeto.setData(LocalDate.parse("11/01/1988", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setArea(1.50);
        objeto.setDistancia_capital(773);
        
        
        boolean resultado = inserir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }
      
       public static boolean alterar(Prova objeto) {
        String sql = "UPDATE prova SET nome = ?, sigla_estado = ?, nr_habitantes = ?, data = ?, Area = ?, distancia_capital = ?  WHERE codigo=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, objeto.getNome());
            ps.setString(2, objeto.getSigla_estado());
            ps.setInt(3, objeto.getNr_habitantes());
            ps.setDate(4, Date.valueOf(objeto.getData()));
            ps.setDouble(5, objeto.getArea());
            ps.setInt(6, objeto.getDistancia_capital());
            ps.setInt(7, objeto.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
       
        public static boolean excluir(Prova objeto) {
        String sql = "DELETE FROM prova WHERE codigo=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, objeto.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
        
        public static List<Prova> consultar() {
        List<Prova> resultados = new ArrayList<>();
        //editar o SQL conforme a entidade
        String sql = "SELECT codigo, nome, sigla_estado, nr_habitantes, data, area, distancia_capital FROM prova";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Prova objeto = new Prova();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setCodigo(rs.getInt("codigo"));
                objeto.setNome(rs.getString("nome"));
                objeto.setSigla_estado(rs.getString("sigla_estado"));
                objeto.setNr_habitantes(rs.getInt("nr_habitantes"));
                objeto.setData(rs.getDate("data").toLocalDate());
                objeto.setArea(rs.getDouble("area"));
                objeto.setDistancia_capital(rs.getInt("distancia_capital"));
                
                resultados.add(objeto);//não mexa nesse, ele adiciona o objeto na lista
            }
            return resultados;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
}
        
         public static Prova consultar(int primaryKey) {
        //editar o SQL conforme a entidade
        String sql = "SELECT codigo, nome, sigla_estado, nr_habitantes, data, area, distancia_capital FROM prova WHERE codigo=?";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, primaryKey);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Prova objeto = new Prova();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setCodigo(rs.getInt("codigo"));
                objeto.setNome(rs.getString("nome"));
                objeto.setSigla_estado(rs.getString("sigla_estado"));
                objeto.setNr_habitantes(rs.getInt("nr_habitantes"));
                objeto.setData(rs.getDate("data").toLocalDate());
                objeto.setArea(rs.getDouble("area"));
                objeto.setDistancia_capital(rs.getInt("distancia_capital"));
                return objeto;//não mexa nesse, ele adiciona o objeto na lista
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
