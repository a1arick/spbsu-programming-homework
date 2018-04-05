package sample;

public class Model {
    public long calculation(long a, long b, String operation){
        switch (operation){
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
        }
        System.out.println("I dont know this operator");

        return 0;
    }
}
