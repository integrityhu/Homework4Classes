package model;

import javax.xml.bind.annotation.XmlType;

@XmlType
public class EQ extends TwoArgOperator<ExpOrValue<?>, ExpOrValue<?>,Boolean> {
    private final static int precedence = 8;
    
    public EQ() {
        super(null,null);
    }
    
    public EQ(ExpOrValue<?> left, ExpOrValue<?> right) {
        super(left, right);
    }

    @Override
    public Boolean getValue() {
        return getLeft().getValue().equals(getRight().getValue());
    }

    @Override
    public String toString() {
        return "(" + getLeft() + " = " + getRight() + ")";
    }

    @Override
    public int getPrecedence() {
        return precedence;
    }
}
