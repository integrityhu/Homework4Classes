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
public class SubResult<ExpOrValue> extends Unary<ExpOrValue>{

    private final static int precedence = 1;
    
    public void setValue(ExpOrValue value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public int getPrecedence() {
        return precedence;
    }
}
