package oop.entities;

import oop.exceptions.VerificationException;
import oop.utils.Util;

public class DurableProduct extends ProductAbstract {

    private static final String ARTICLE_NUMBER_LETTERS = "DP";
    private int warrantyPeriod;
    private double grossWeight;

    public DurableProduct(String articleNumber, String name, String brand,
            String family, int nettoPrice, int taxId, int quantity,
            String amountUnits, int criticalQuantity, int warrantyPeriod,
            double grossWeight) throws VerificationException {
        super(articleNumber, name, brand, family,
                nettoPrice, taxId, quantity,
                amountUnits,
                criticalQuantity);

        this.warrantyPeriod = warrantyPeriod;
        this.grossWeight = grossWeight;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) throws VerificationException {
        if (Util.isNotNull(warrantyPeriod)) {
            this.warrantyPeriod = warrantyPeriod;
        } else {
            throw new VerificationException("unacceptable warranty period");
        }
    }

    public double getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(double grossWeight) throws VerificationException {
        if (Util.isNotNull(grossWeight)) {
            this.grossWeight = grossWeight;
        } else {
            throw new VerificationException("unacceptable gross weight");
        }
    }

    @Override
    protected boolean checkArticleNumberLetters(String letters) {
        return letters.equals(ARTICLE_NUMBER_LETTERS);
    }

}
