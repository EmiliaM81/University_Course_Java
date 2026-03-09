import java.lang.reflect.Method;

public class ObjectReflectionAnalyser {
    public ObjectReflectionAnalyser()
    {

    }

    public static void analyzeObject(Object text)
    {
        System.out.println("Analizuję obiekt: " + text);
        System.out.println("Nazwa klasy: " + text.getClass().getName());
        System.out.println("Klasa nadrzędna: " + text.getClass().getSuperclass().getName());
        System.out.println("Czy to interfejs? " + text.getClass().isInterface());
        System.out.println("Dostępne metody:");
        Method[] methods = text.getClass().getDeclaredMethods();
        System.out.println(" - " + methods[0])
        System.out.println(" - " + methods[1]);
        System.out.println(" - " + methods[2]);
        System.out.println(" - ... i " + (methods.length - 3 )+ " więcej metod");
        System.out.println("Czy to TextEntity? " + (text instanceof TextEntity));
        System.out.println("Czy to String? " + (text instanceof String));
        System.out.println("Czy to Cloneable? " + (text instanceof Cloneable));
        System.out.println("Czy to Comparable? " + (text instanceof Comparable));




    }
}
