package oop.entities;

/**
 *
 * @author Marci
 */
public interface Product {

    public String getArticleNumber();

    public String getName();

    public String getBrand();

    public String getFamily();

    public int getNettoPrice();

    public int getTaxId();

    public int getQuantity();

    public String getAmountUnits();

    public int getCriticalQuantity();

}
