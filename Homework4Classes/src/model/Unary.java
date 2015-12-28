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
public abstract class Unary<T> extends ExpOrValue<T>{

    protected T value;
    
    @Override
    public T getValue() {
        return value;
    }

    public abstract int getPrecedence();
}
