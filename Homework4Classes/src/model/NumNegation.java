/**
 * 
 */
package model;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlType;

/**
 * @author pzoli
 *
 */
@XmlType
public class NumNegation extends Unary<BigDecimal>{

    private final static int precedence = 2;
    public final static String representation = "-";
    
    @Override
    public BigDecimal getValue() {
        BigDecimal value = super.getValue();
        return value.multiply(BigDecimal.valueOf(-1));
    }

    @Override
    public int getPrecedence() {
        return precedence;
    }
    
    @Override
    public String toString() {
        return "-" + super.getValue();
    }
}
