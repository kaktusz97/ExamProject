package oop.views.tablemodels;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import oop.entities.PerishableProduct;

public class PerishableProductTableModel extends AbstractTableModel {

    private List<PerishableProduct> data;
    private String[] columnNames;

    public PerishableProductTableModel(List<PerishableProduct> data) {
        this.data = data;
        columnNames
                = new String[]{"article_number", "name", "brand", "family", "netto_price", "gross_price", "tax_id",
                    "quantity", "amount_units", "critical_quantity", "expiration_date",
                    "production_date", "days_until_expiration"};
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
        PerishableProduct temp = data.get(rowIndex);
        String result = temp.getArticleNumber();
        switch (columnIndex) {
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
                result = formatPrice(temp.getNettoPrice());
                break;
            case 5:
                result = formatPrice(temp.getGrossPrice());
                break;
            case 6:
                result = String.valueOf(temp.getTaxId());
                break;
            case 7:
                result = String.valueOf(temp.getQuantity());
                break;
            case 8:
                result = String.valueOf(temp.getAmountUnits());
                break;
            case 9:
                result = String.valueOf(temp.getCriticalQuantity());
                break;
            case 10:
                result = String.valueOf(temp.getExpirationDate());
                break;
            case 11:
                result = String.valueOf(temp.getProductionDate());
                break;
            case 12:
                result = String.valueOf(temp.getHowManyDaysUntilExpiration());
        }
        return result;
    }

    private String formatPrice(double amount) {
        return String.format("%.2f Ft", amount);
    }
}
