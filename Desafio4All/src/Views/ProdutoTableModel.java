/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Dao.ProdutoDao;
import Model.Produto;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author vellinholeo
 */
public class ProdutoTableModel extends AbstractTableModel {

    private static List<Produto> linhas;
    private String[] pColunas = new String[]{"Imagem", "Nome", "Preco"};

    public ProdutoTableModel() {
        linhas = new ArrayList<Produto>();       
    }

    public ProdutoTableModel(List<Produto> listaProdutos) {
        linhas = new ArrayList<Produto>(listaProdutos);
    }

    @Override
    public int getColumnCount() {
        return pColunas.length;
    }

    @Override
    public int getRowCount() {
        return linhas.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return pColunas[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return Double.class;
            default:
                throw new IndexOutOfBoundsException("ColumnIndex out of bounds");
        }
    }

    public static List<Produto> getAllExits() {
        return linhas;
    }

    public List<Produto> fillingRows() {
        try {
            ProdutoDao dao = new ProdutoDao();
            linhas = new ArrayList();
            linhas = dao.lista();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao encher o table:" + e);
        }
        return linhas;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Produto cProduto = linhas.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return cProduto.getImagem();
            case 1:
                return cProduto.getNome();
            case 2:
                return cProduto.getPreco();
            default:
                throw new IndexOutOfBoundsException("Column out of bounds");
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            
        fireTableCellUpdated(rowIndex, columnIndex);
    }
    
   @Override
    public boolean isCellEditable(int row, int col) {
        if (col == 0) {
            return false;
        } else {
            return true;
        }
    }
}
