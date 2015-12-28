package model;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlType;

@XmlType
public class Minus extends TwoArgOperator<ExpOrValue<BigDecimal>, ExpOrValue<BigDecimal>, BigDecimal> {

    private final static int precedence = 5;
    
    public Minus() {
        super(null,null);
    }
    
    public Minus(ExpOrValue<BigDecimal> left, ExpOrValue<BigDecimal> right) {
        super(left, right);
    }

    @Override
    public BigDecimal getValue() {
        return getLeft().getValue().subtract(getRight().getValue());
    }

    @Override
    public String toString() {
        return "(" + getLeft() + " - " + getRight() + ")";
    }

    /* (non-Javadoc)
     * @see model.TwoArgBigDecimalOperator#getPrecedence()
     */
    @Override
    public int getPrecedence() {
        return precedence;
    }
}
