package model;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlType;

@XmlType
public class LTorEQ extends TwoArgOperator<ExpOrValue, ExpOrValue, Boolean> {
    
    private final static int precedence = 7;
    
    public LTorEQ() {
        super(null,null);
    }
    
    public LTorEQ(ExpOrValue left, ExpOrValue right) {
        super(left, right);
    }

    public int compare(Object left, Object right) {
        int result = 0;
        if ((left instanceof BigDecimal) && (right instanceof BigDecimal)) {
            result = ((BigDecimal)left).compareTo((BigDecimal) right);
        }
        return result;
    }
    
    @Override
    public Boolean getValue() {
        return compare(getLeft().getValue(), getRight().getValue()) <= 0;
    }

    @Override
    public String toString() {
        return "(" + getLeft() + " <= " + getRight() + ")";
    }

    @Override
    public int getPrecedence() {
        return precedence;
    }
}
