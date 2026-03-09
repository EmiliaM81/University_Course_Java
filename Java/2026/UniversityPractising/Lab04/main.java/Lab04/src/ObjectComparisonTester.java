import java.util.HashSet;

public class ObjectComparisonTester {
    public ObjectComparisonTester()
    {

    }

    public static void compareObjects(TextEntity t1, TextEntity t2, TextEntity t3)
    {
        System.out.println("t1.equals(t2): " + t1.equals(t2));
        System.out.println("t1.equals(t3): " + t1.equals(t3));
        System.out.println("t1.hashCode(): " + t1.hashCode());
        System.out.println("t2.hashCode(): " + t2.hashCode());
        System.out.println("t3.hashCode(): " + t3.hashCode());
        HashSet <TextEntity>hs = new HashSet<>();
        hs.add(t1);
        hs.add(t2);
        hs.add(t3);
        System.out.println("Rozmiar HashSet: " + hs.size());
        System.out.println("Zawartość HashSet: " + hs);
        

    }
}
