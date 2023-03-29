package oop.entities;

import java.util.Objects;
import oop.exceptions.VerificationException;
import oop.utils.Validator;

abstract class ProductAbstract implements Product {

    private String articleNumber;
    private String name;
    private String brand;
    private String family;
    private int nettoPrice;
    private int taxId;
    private int quantity;
    private String amountUnits;
    private int criticalQuantity;

    ProductAbstract(String articleNumber, String name,
            String brand,
            String family, int nettoPrice,
            int taxId, int quantity,
            String amountUnits, int criticalQuantity) throws VerificationException {
        setArticleNumber(articleNumber);
        setName(name);
        setBrand(brand);
        setFamily(family);
        setNettoPrice(nettoPrice);
        setTaxId(taxId);
        setQuantity(quantity);
        setAmountUnits(amountUnits);
        setCriticalQuantity(criticalQuantity);
    }

    //<editor-fold defaultstate="collapsed" desc="getters">
    @Override
    public String getArticleNumber() {
        return articleNumber;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public String getFamily() {
        return family;
    }

    @Override
    public int getNettoPrice() {
        return nettoPrice;
    }

    @Override
    public int getTaxId() {
        return taxId;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public String getAmountUnits() {
        return amountUnits;
    }

    @Override
    public int getCriticalQuantity() {
        return criticalQuantity;
    }

    public double getGrossPrice() {
        return nettoPrice * (1.0 + (taxId / 100.0));
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="setters">
    private void setArticleNumber(String articleNumber) throws VerificationException {
        checkArticleNumber(articleNumber);
        this.articleNumber = articleNumber;
    }

    public final void setName(String name) throws VerificationException {
        Validator.checkName(name);
        this.name = name;
    }

    public final void setBrand(String brand) throws VerificationException {
        Validator.checkBrand(brand);
        this.brand = brand;
    }

    public final void setFamily(String family) throws VerificationException {
        Validator.checkFamily(family);
        this.family = family;
    }

    public final void setNettoPrice(int nettoPrice) throws VerificationException {
        Validator.checkNettoPrice(nettoPrice);
        this.nettoPrice = nettoPrice;
    }

    public final void setTaxId(int taxId) throws VerificationException {
        Validator.checkTaxId(taxId);
        this.taxId = taxId;
    }

    public final void setQuantity(int quantity) throws VerificationException {
        Validator.checkQuantity(quantity);
        this.quantity = quantity;
    }

    public final void setAmountUnits(String amountUnits) throws VerificationException {
        Validator.checkAmountUnits(amountUnits);
        this.amountUnits = amountUnits;
    }

    public final void setCriticalQuantity(int criticalQuantity) throws VerificationException {
        Validator.checkCriticalQuantity(criticalQuantity);
        this.criticalQuantity = criticalQuantity;
    }
//</editor-fold>

    private void checkArticleNumber(String articleNumber) throws VerificationException {
        String letters = articleNumber.substring(0, 2);
        String digits = articleNumber.substring(2);
        if (!(checkArticleNumberLetters(letters) && checkArticleNumberDigits(
                digits) && checkArticleNumberLength(
                        articleNumber))) {
            throw new VerificationException("wrong article number");
        }
    }

    private boolean checkArticleNumberDigits(String digits) {
        return digits.matches("[0-9]+");
    }

    private boolean checkArticleNumberLength(String articleNumber) {
        return articleNumber.length() == 10;
    }

    protected abstract boolean checkArticleNumberLetters(String letters);

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Product
                && this.hashCode() == ((ProductAbstract) obj).hashCode();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.articleNumber);
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.brand);
        hash = 53 * hash + Objects.hashCode(this.family);
        hash = 53 * hash + this.nettoPrice;
        hash = 53 * hash + this.taxId;
        hash = 53 * hash + this.quantity;
        hash = 53 * hash + Objects.hashCode(this.amountUnits);
        hash = 53 * hash + this.criticalQuantity;
        return hash;
    }

    @Override
    public String toString() {
        return "Product{" + "articleNumber=" + articleNumber + ", name=" + name + ", brand=" + brand + ", family=" + family + ", nettoPrice=" + nettoPrice + ", taxId=" + taxId + ", quantity=" + quantity + ", amountUnits=" + amountUnits + ", criticalQuantity=" + criticalQuantity + '}';
    }

}
