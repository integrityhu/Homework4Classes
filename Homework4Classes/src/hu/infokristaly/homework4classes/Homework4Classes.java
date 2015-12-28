package hu.infokristaly.homework4classes;
import model.ExpOrValue;
import tasks.ExpressionTask;
import tasks.ReflectionTask;

public class Homework4Classes {

    public static void main(String[] args) {
        ExpressionTask expTask = new ExpressionTask();
        //expTask.startExpression();
        //expTask.processExpression();
        //expTask.buildExpOrValue();
        //ReflectionTask.getReflection();
        ExpressionTask.createExpOrValueFromString();
    }

}
