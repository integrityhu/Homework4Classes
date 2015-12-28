package tasks;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.math.BigDecimal;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.sun.org.apache.xpath.internal.operations.And;
import com.sun.org.apache.xpath.internal.operations.Div;
import com.sun.org.apache.xpath.internal.operations.Gt;

import model.BoolNegation;
import model.GTorEQ;
import model.LTorEQ;
import model.Multiple;
import model.NumNegation;
import model.Or;
import model.Parentheses;
import model.Plus;
import model.Conclusion;
import model.ExpOrValue;
import model.EQ;
import model.LT;
import model.Plus;
import model.Reference;
import model.Rule;
import model.SetterConclusion;
import model.SubResult;
import model.TwoArgOperator;
import model.Unary;
import model.UnnamedConstant;
import model.Value;
import model.Workspace;
import model.WorkspaceObjects;

public class ExpressionTask {

    private Workspace wp = new Workspace();
    private ExpOrValue<Boolean> byte1LTint3;
    private ExpOrValue<Boolean> int3EQint4;
    private ExpOrValue<Boolean> int3EQint5;
    private ExpOrValue<Boolean> int6LTint7;
    private ExpOrValue<Boolean> int3LTint5;

    private void generateRules() {
        ExpOrValue<BigDecimal> byte1 = new Value<BigDecimal>("byte1", new BigDecimal(100));
        wp.getObjects().put("byte1", byte1);
        ExpOrValue<BigDecimal> byte2 = new Value<BigDecimal>("byte2", new BigDecimal(127));
        wp.getObjects().put("byte2", byte2);
        ExpOrValue<BigDecimal> int3 = new Plus(new Reference<BigDecimal>("this.byte1"), new Reference<BigDecimal>("this.byte2"));
        wp.getObjects().put("int3", int3);
        ExpOrValue<BigDecimal> int4 = new Value<BigDecimal>("int4", new BigDecimal(227));
        wp.getObjects().put("int4", int4);

        byte1LTint3 = new LT(new Reference<BigDecimal>("this.byte1"), new Reference<BigDecimal>("this.int3"));
        int3EQint4 = new EQ(new Reference<BigDecimal>("this.int3"), new Reference<BigDecimal>("this.int4"));

        ExpOrValue<BigDecimal> int5 = new Plus(new Reference<BigDecimal>("this.int3"), new Reference<BigDecimal>("this.int4"));
        wp.getObjects().put("int5", int5);
        int3EQint5 = new EQ(new Reference<BigDecimal>("this.int3"), new Reference<BigDecimal>("this.int5"));

        int3LTint5 = new LT(new Reference<BigDecimal>("this.int3"), new Reference<BigDecimal>("this.int5"));

        Value<BigDecimal> int6 = new Value<BigDecimal>("int6", new BigDecimal(0));
        wp.getObjects().put("int6", int6);
        int6LTint7 = new LT(new Reference<BigDecimal>("this.int6"), new UnnamedConstant<BigDecimal>(new BigDecimal(1000)));
        Conclusion conclusion1 = new SetterConclusion<BigDecimal>(new Reference<BigDecimal>("this.int6"), new UnnamedConstant<BigDecimal>(new BigDecimal(100)));

        Rule rule1 = new Rule("rule1", int3LTint5, conclusion1);
        ExpOrValue<BigDecimal> int6PlusInt7 = new Plus(new Reference<BigDecimal>("this.int6"), new UnnamedConstant<BigDecimal>(new BigDecimal(100)));
        Conclusion conclusion2 = new SetterConclusion<BigDecimal>(new Reference<BigDecimal>("this.int6"), int6PlusInt7);
        Rule rule2 = new Rule("rule2", int6LTint7, conclusion2);

        List<String> ref1 = new ArrayList<String>();
        ref1.add("int6");

        rule1.setConclusionReferences(ref1);
        rule2.setConclusionReferences(ref1);
        rule2.setConditionReferences(ref1);
        wp.getObjects().put("rule1", rule1);
        wp.getObjects().put("rule2", rule2);
    }

    private static Class<?>[] classes = { Rule.class, ExpOrValue.class, SubResult.class, Plus.class, LT.class, LTorEQ.class, Gt.class, GTorEQ.class, EQ.class, Div.class, And.class, Or.class, SetterConclusion.class, TwoArgOperator.class, UnnamedConstant.class, Workspace.class, WorkspaceObjects.class, Value.class, Reference.class, Parentheses.class, NumNegation.class, BoolNegation.class };

    public void generateXML() {
        generateRules();

        StringWriter writer = new StringWriter();

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(classes, null);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            marshaller.marshal(wp, writer);
            System.out.println(writer.toString());
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        wp.resolveReferences();
        testExpressions();
    }
    
    public static void createExpOrValueFromString() {
        ExpOrValue result = null;
        try {
            Class classValue = Class.forName("model.Value");
            TypeVariable[] typeVars = classValue.getTypeParameters();
            Constructor<?>[] construcotrs = classValue.getConstructors();
            System.out.println(construcotrs);
            System.out.println(typeVars);
            Method m = classValue.getDeclaredMethod("getValue");  
            Type returnType = m.getGenericReturnType();
            System.out.println(returnType);
            
            Object obj = classValue.newInstance();
            Method mSetName = classValue.getMethod("setName", Class.forName("java.lang.String"));
            mSetName.invoke(obj, "int1");
            Method mSetVal = classValue.getMethod("setValue", Class.forName("java.lang.Object"));
            mSetVal.invoke(obj, BigDecimal.valueOf(10));
            System.out.println(obj.toString());
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        result = new Value<BigDecimal>();
        ((Value<BigDecimal>)result).setValue(BigDecimal.valueOf(10));

    }

    public static ExpOrValue buildExpOrValue() {
        ExpOrValue result = null;        
        String expStr = "this.int2 + this.int4 * 3 * 2 + 1 + 9";
        StringReader reader = new StringReader(expStr);
        Stack<ExpOrValue> processStack = new Stack<ExpOrValue>();
        boolean stringIndicator = false;
        Stack<String> phStack = new Stack<String>();
        int pos = 0;
        while (pos < expStr.length()) {
            try {
                reader.mark(pos);
                char[] pairChars = new char[2];
                reader.read(pairChars);
                pos+=2;
                reader.reset();
                char[] singleChar = new char[1];
                reader.read(singleChar);
                pos-=1;
                System.out.println("char["+pos+"]:"+ new String(singleChar) + "; pairs: " + new String(pairChars));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        reader.close();
        return result;
    }

    public static void processExpression() {
        Stack<ExpOrValue> stack = new Stack<ExpOrValue>();

        Workspace wp = new Workspace();
        String expStr = "this.int2 + this.int4 * 3 * 2 + 1 + 9";

        ExpOrValue<BigDecimal> int2 = new Value<BigDecimal>("int2", BigDecimal.valueOf(15));
        ExpOrValue<BigDecimal> int4 = new Value<BigDecimal>("int4", BigDecimal.valueOf(5));
        ExpOrValue<BigDecimal> c1 = new UnnamedConstant<BigDecimal>(BigDecimal.valueOf(3));
        ExpOrValue<BigDecimal> c2 = new UnnamedConstant<BigDecimal>(BigDecimal.valueOf(1));
        wp.getObjects().put("int2", int2);
        wp.getObjects().put("int4", int4);
        wp.getObjects().put("c1", c1);
        wp.getObjects().put("c2", c2);

        stack.push(new Reference("this.int2"));

        ExpOrValue left = null;
        if (isUnary(stack.peek())) {
            left = stack.pop();
        }

        Plus plus1 = new Plus();
        plus1.setLeft(left);
        stack.push(plus1);

        TwoArgOperator<ExpOrValue, ExpOrValue, BigDecimal> plus11 = null;
        if (stack.peek() instanceof TwoArgOperator) {
            plus11 = (TwoArgOperator) stack.peek();
            plus11.setRight(new Reference("this.int4"));
        }

        Multiple multiple1 = new Multiple();
        ExpOrValue right = plus11.getRight();
        multiple1.setLeft(right);
        plus11.setRight(multiple1);
        stack.push(multiple1);

        TwoArgOperator<ExpOrValue, ExpOrValue, BigDecimal> multiple11 = null;
        if (stack.peek() instanceof TwoArgOperator) {
            multiple11 = (TwoArgOperator) stack.pop();
            multiple11.setRight(new UnnamedConstant<BigDecimal>(BigDecimal.valueOf(3)));
        }

        Multiple multiple2 = new Multiple();
        ExpOrValue right2 = plus11.getRight();
        plus11.setRight(multiple2);
        multiple2.setLeft(right2);
        stack.push(multiple2);

        TwoArgOperator<ExpOrValue, ExpOrValue, BigDecimal> multiple22 = null;
        if (stack.peek() instanceof TwoArgOperator) {
            multiple22 = (TwoArgOperator) stack.pop();
            multiple22.setRight(new UnnamedConstant<BigDecimal>(BigDecimal.valueOf(2)));
        }

        Plus plus2 = new Plus();
        plus2.setLeft(plus1);
        stack.push(plus2);

        TwoArgOperator<ExpOrValue, ExpOrValue, BigDecimal> plus22 = null;
        if (stack.peek() instanceof TwoArgOperator) {
            plus22 = (TwoArgOperator) stack.peek();
            plus22.setRight(new UnnamedConstant<BigDecimal>(BigDecimal.valueOf(1)));
        }

        Plus plus3 = new Plus();
        plus3.setLeft(plus2);
        stack.push(plus3);

        TwoArgOperator<ExpOrValue, ExpOrValue, BigDecimal> plus33 = null;
        if (stack.peek() instanceof TwoArgOperator) {
            plus33 = (TwoArgOperator) stack.peek();
            plus33.setRight(new UnnamedConstant<BigDecimal>(BigDecimal.valueOf(9)));
        }

        ExpOrValue last = stack.lastElement();

        wp.getObjects().put("fn1", last);
        wp.resolveReferences();

        System.out.println(last.toString() + " = " + last.getValue());
    }

    public void testExpressions() {

        wp.getObjects().put("int3LTint5", int3LTint5);
        wp.getObjects().put("byte1LTint3", byte1LTint3);
        wp.getObjects().put("int3EQint4", int3EQint4);
        wp.getObjects().put("int3EQint5", int3EQint5);
        wp.getObjects().put("int3LTint5", int3LTint5);
        wp.resolveReferences();
        System.out.println(byte1LTint3 + " ? " + byte1LTint3.getValue());
        System.out.println(int3EQint4 + " ? " + int3EQint4.getValue());
        System.out.println(int3EQint5 + " ? " + int3EQint5.getValue());
        System.out.println(int3LTint5 + " ? " + int3LTint5.getValue());
    }

    /**
     * @param peek
     * @return
     */
    private static boolean isUnary(ExpOrValue peek) {
        Boolean result = (peek instanceof Unary) || (peek instanceof Reference);
        return result;
    }

    public void runWorkspace() {
        Rule rule1 = (Rule) wp.getObjects().get("rule1");
        wp.getRuleQueue().add(rule1);
        wp.start();
    }

    public void startExpression() {
        // readBackWorkspace();
        generateXML();
        runWorkspace();
    }

    public void readBackWorkspace() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(classes, null);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            wp = (Workspace) unmarshaller.unmarshal(ExpressionTask.class.getResourceAsStream("/workspace.xml"));
            wp.resolveReferences();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

}
