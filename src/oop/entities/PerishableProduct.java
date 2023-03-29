package oop.entities;

import java.sql.Date;
import oop.exceptions.VerificationException;
import oop.utils.Util;

public class PerishableProduct extends ProductAbstract {

    private static final String ARTICLE_NUMBER_LETTERS = "PP";
    private Date expirationDate;
    private Date productionDate;

    public PerishableProduct(String articleNumber, String name,
            String brand, String family, int nettoPrice, int taxId, int quantity,
            String amountUnits, int criticalQuantity, Date expirationDate,
            Date productionDate) throws VerificationException {
        super(articleNumber, name, brand, family,
                nettoPrice, taxId, quantity,
                amountUnits, criticalQuantity);
        this.expirationDate = expirationDate;
        this.productionDate = productionDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) throws VerificationException {
        if (Util.isNotNull(expirationDate)) {
            this.expirationDate = expirationDate;
        } else {
            throw new VerificationException("wrong expiration date");
        }
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) throws VerificationException {
        if (Util.isNotNull(productionDate)) {
            this.productionDate = productionDate;
        } else {
            throw new VerificationException("wrong production date");
        }
    }

    public long getHowManyDaysUntilExpiration() {
        Date currentDate = new Date(System.currentTimeMillis());
        long milliDiff = expirationDate.getTime() - currentDate.
                getTime();
        int daysDiff = (int) Math.ceil(
                (double) milliDiff / (24 * 60 * 60 * 1000));
        return daysDiff <= 0 ? 0 : daysDiff;
    }

    @Override
    protected boolean checkArticleNumberLetters(String letters) {
        return letters.equals(ARTICLE_NUMBER_LETTERS);
    }

}
