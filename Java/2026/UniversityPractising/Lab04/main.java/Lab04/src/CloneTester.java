public class CloneTester {
    public CloneTester()
    {

    }

    public static void testCloning(TextEntity textEntity)
    {
        System.out.println("Oryginalny obiekt: "+textEntity);
        TextEntity clone = (TextEntity) textEntity.clone();
        System.out.println("Sklonowany obiekt: "+clone);
        System.out.printf("Czy to ten sam obiekt? %b\n", textEntity == clone);
        System.out.printf("Czy obiekty są równe? %b\n", textEntity.equals(clone));
        System.out.printf("HashCode oryginalnego: %d\n", textEntity.hashCode());
        System.out.printf("HashCode sklonowanego: %d\n", clone.hashCode());

    }
}
