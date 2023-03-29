package oop.views.tablemodels;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import oop.entities.Product;

public class CriticalQuantityTableModel extends AbstractTableModel {

    private List<Product> data;
    private String[] columnNames;

    public CriticalQuantityTableModel(List<Product> data) {
        this.data = data;
        columnNames = new String[]{"article_number", "name", "brand", "family", "quantity", "amount_units", "critical_quantity"};
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Product temp = data.get(rowIndex);
        String result = "";
        switch (columnIndex) {
            case 0:
                result = temp.getArticleNumber();
                break;
            case 1:
                result = temp.getName();
                break;
            case 2:
                result = temp.getBrand();
                break;
            case 3:
                result = temp.getFamily();
                break;
            case 4:
                result = String.valueOf(temp.getQuantity());
                break;
            case 5:
                result = String.valueOf(temp.getAmountUnits());
                break;
            case 6:
                result = String.valueOf(temp.getCriticalQuantity());
                break;
        }
        return result;

    }

}
