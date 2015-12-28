package model;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class Power extends TwoArgOperator<ExpOrValue<BigDecimal>, ExpOrValue<BigDecimal>, BigDecimal> {

    private final static int precedence = 4;
    
    public Power(){
        super(null,null);
    }
    
    public Power(ExpOrValue<BigDecimal> left, ExpOrValue<BigDecimal> right) {
        super(left, right);
    }

    @Override
    public BigDecimal getValue() {
        return getLeft().getValue().pow(getRight().getValue().intValue());
    }

    @Override
    public String toString() {
        return "(" + getLeft() + " ^ " + getRight() + ")";
    }

    @Override
    public int getPrecedence() {
        return precedence;
    }
}
