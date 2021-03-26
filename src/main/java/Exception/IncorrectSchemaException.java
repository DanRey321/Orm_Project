package Exception;

public class IncorrectSchemaException extends Exception {
    public IncorrectSchemaException(){
        //System.out.println("Incorrect File Schema");
        super("Incorrect File Schema");
    }

    public void message(){
        System.out.println("Incorrect File Schema");
    }
}
