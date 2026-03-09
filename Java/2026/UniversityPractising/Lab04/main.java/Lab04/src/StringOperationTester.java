public class StringOperationTester {
    public StringOperationTester()
    {

    }

    public static void stringOperations(String content)
    {
        System.out.println("Oryginalny tekst: " + content);
        System.out.println("Długość tekstu: " + content.length());
        System.out.println("Tekst wielkimi literami: " + content.toUpperCase());
        System.out.println("Tekst małymi literami: " + content.toLowerCase());
        System.out.println("Indeks pierwszego 'a': " + content.indexOf('a'));
        System.out.println("Podciąg od 5 do 10: " + content.substring(5, 10));
        System.out.println("Czy zaczyna się od 'Przyk': "+ content.startsWith("Przyk"));
        System.out.println("Podzielono na 2 słowa:");
        String[] text = content.split(" ");
        for (String str: text)
        {
            System.out.println(" - " + str);
        }
    }
}
