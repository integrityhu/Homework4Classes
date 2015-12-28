package model;

import javax.xml.bind.annotation.XmlType;

@XmlType
public class Or extends TwoArgOperator<ExpOrValue<Boolean>, ExpOrValue<Boolean>, Boolean> {

    private final static int precedence = 13;
    
    public Or() {
        super(null,null);
    }
    
    public Or(ExpOrValue<Boolean> left, ExpOrValue<Boolean> right) {
        super(left, right);
    }

    @Override
    public Boolean getValue() {
        return getLeft().getValue() || getRight().getValue();
    }
    
    @Override
    public String toString() {
        return "(" + getLeft() + " || " + getRight() + ")";
    }

    @Override
    public int getPrecedence() {
        return precedence;
    }
}
