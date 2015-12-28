package model;

import javax.xml.bind.annotation.XmlType;

import com.sun.xml.internal.txw2.annotation.XmlElement;

@XmlType
public class UnnamedConstant<T> extends Unnamed<T> {

    public UnnamedConstant() {
        super();
    }

    public UnnamedConstant(T value) {
        this.value = value;
    }

    @XmlElement
    @Override
    public T getValue() {
        return value;
    }

    public void setValue(T newValue) {
        this.value = newValue;
    }
    
    @Override
    public String toString() {
        return " " + getValue() + " ";
    }

}
