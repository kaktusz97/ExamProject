package oop.utils;

import oop.exceptions.VerificationException;

public class Validator {

    private Validator() {
    }

    public static final void checkName(String name) throws VerificationException {
        if (!(Util.isBetween(0, 150, name.length())
                && Util.isNotNull(name))) {
            throw new VerificationException("unacceptable name");
        }
    }

    public static final void checkBrand(String brand) throws VerificationException {
        if (!(Util.isBetween(0, 50, brand.length())
                || !Util.isNotNull(brand))) {
            throw new VerificationException("unacceptable brand");
        }
    }

    public static final void checkFamily(String family) throws VerificationException {
        if (!(Util.isBetween(0, 50, family.length())
                && Util.isNotNull(family))) {
            throw new VerificationException("unacceptable family");
        }
    }

    public static final void checkNettoPrice(int nettoPrice) throws VerificationException {
        if (!(Util.isNotNull(nettoPrice) && nettoPrice > 0)) {
            throw new VerificationException("unacceptable netto price");
        }
    }

    public static final void checkTaxId(int taxId) throws VerificationException {
        if (!(Util.isNotNull(taxId))) {
            throw new VerificationException("unacceptable tax id");
        }
    }

    public static final void checkQuantity(int quantity) throws VerificationException {
        if (!(Util.isNotNull(quantity) && quantity >= 0)) {
            throw new VerificationException("unacceptable quantity");
        }
    }

    public static final void checkAmountUnits(String amountUnits) throws VerificationException {
        if (!Util.isBetween(0, 10, amountUnits.length())
                && Util.isNotNull(amountUnits)) {
            throw new VerificationException("unacceptable amount units");
        }
    }

    public static final void checkCriticalQuantity(int criticalQuantity) throws VerificationException {
        if (!(Util.isNotNull(criticalQuantity))) {
            throw new VerificationException("unacceptable critical quantity");
        }
    }

}
