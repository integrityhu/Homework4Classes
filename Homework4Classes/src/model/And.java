package model;

import javax.xml.bind.annotation.XmlType;

@XmlType
public class And extends TwoArgOperator<ExpOrValue<Boolean>, ExpOrValue<Boolean>, Boolean> {

    private final static int precedence = 2;
    
    public And() {
        super(null,null);
    }
    
    public And(ExpOrValue<Boolean> left, ExpOrValue<Boolean> right) {
        super(left, right);
    }

    @Override
    public Boolean getValue() {
        return getLeft().getValue() && getRight().getValue();
    }
    
    @Override
    public String toString() {
        return "(" + getLeft() + " && " + getRight() + ")";
    }

    /* (non-Javadoc)
     * @see model.TwoArgBooleanOperator#getPrecedence()
     */
    @Override
    public int getPrecedence() {
        return precedence;
    }
}
