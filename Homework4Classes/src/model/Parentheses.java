/**
 * 
 */
package model;

import javax.xml.bind.annotation.XmlType;

/**
 * @author pzoli
 *
 */
@XmlType
public class Parentheses<ExpOrValue> extends Unary<ExpOrValue>{

    private final static int precedence = 1;
    public final static char openChar = '(';
    public final static char closeChar =')';
    
    public void setValue(ExpOrValue value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return "("+value.toString()+")";
    }

    @Override
    public int getPrecedence() {
        return precedence;
    }
}
