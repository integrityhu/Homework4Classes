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
public class BoolNegation extends Unary<Boolean>{

    private final static int precedence = 2;
    public final static String representation = "!";
    
    @Override
    public Boolean getValue() {
        Boolean value = super.getValue();
        return (value != null ? !value : null);
    }

    @Override
    public int getPrecedence() {
        return precedence;
    }
    
    public String toString() {
        return "!" + super.getValue();
    }

}
