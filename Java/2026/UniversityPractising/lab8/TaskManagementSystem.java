import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

//Programujemy system do zarządzania zadaniami zgodnie z załączonym diagramem klas z wykorzystaniem klas wyliczeniowych, klas wewnętrznych
// i Date and Time API.
//Wymagania:
//1. Wszystkie klasy są klasami wewnętrznymi klasy TaskManegementSystem - fakt ten należy wykorzystywać w kodzie.
//2. Do zarządzania zadaniami służy klasa TaskManager.
//3. Do stworzenia zestawu zdań wykorzystujemy metodę generateRandomTasks(...), która losowo wybranym zadaniom z tablicy sampleTaskNames przydziela
// losowe daty od dnia bieżącego do tygodnia w przód.
//4. Dla tak wygenerowanych zdarzeń tworzymy zestawienia.

public class TaskManagementSystem {
    // Nazwy przykładowych zadań do losowego generowania
    private static final String[] sampleTaskNames = {
            "Przygotować raport",
            "Sprawdzić maile",
            "Zaktualizować dokumentację",
            "Napisać test",
            "Opracować prezentację",
            "Zorganizować spotkanie",
            "Przejrzeć kod",
            "Naprawić błąd",
            "Wykonać backup"
    };

    // Typ wyliczeniowy dla priorytetów zadań
    public enum Priority {
//      
        HIGH,
        LOW,
        MEDIUM

    }

    // Typ wyliczeniowy dla statusów zadań
    public enum TaskStatus {
//        ???
        NEW,
        IN_PROGRESS,
        COMPLETED
    }

    // Statyczna klasa wewnętrzna reprezentująca zadanie
    public static class Task {
//        ???
        private LocalDateTime deadline;
        private String name;
        private Priority priority;
        private TaskStatus status;

        public Task(String name, LocalDateTime deadline, Priority priority)
        {
            this.name = name;
            this.deadline = deadline;
            this.priority = priority;
            this.status = TaskStatus.NEW;
        }

        public LocalDateTime getDeadline() {
            return deadline;
        }

        public String getName() {
            return name;
        }

        public TaskStatus getStatus() {
            return status;
        }

        public void setStatus(TaskStatus status) {
            this.status = status;
        }

        public Priority getPriority() {
            return priority;
        }

        @Override
        public String toString() {
            // TODO Auto-generated method stub
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyy HH:mm");
            return "Zadanie: " + getName()+", Deadline: "+getDeadline().format(formatter)+", Priorytet: "+getPriority()+", Status: "+getStatus();
        }

    }

    // Główna klasa zarządzająca zadaniami
    public static class TaskManager {
//        ???

        private List<Task> tasks;
        private Random random;

        public static interface DeadlineChecker {
            boolean isOverdue(Task task, LocalDateTime localDateTime);
        }

        public TaskManager()
        {
            tasks = new ArrayList<>();
            random = new Random();
        }

        List<Task> fillByPriority(Priority priority) {
            return tasks.stream().filter( task -> task.getPriority() == priority).toList();

        }

        void generateRandomTasks(int ile, LocalDate startDate, int naIleDni)
        {
            for (int i = 0; i<ile; i++)
            {
                String name = sampleTaskNames[random.nextInt(sampleTaskNames.length)];
                LocalDate randomDay = startDate.plusDays(random.nextInt(naIleDni+1));
                int hour = 10 + random.nextInt(8);
                int minute = 15*random.nextInt(4);

                LocalTime randomTime = LocalTime.of(hour, minute);

                LocalDateTime newDeadline = LocalDateTime.of(randomDay, randomTime);

                Priority randomPriority = Priority.values()[random.nextInt(Priority.values().length)];

                tasks.add(new Task(name, newDeadline, randomPriority));
            }
        }

        List<Task> getTasks()
        {
            return tasks;
        }

        DeadlineChecker getDeadlineChecker()
        {
            return (task, localDateTime) -> task.getDeadline().isBefore(localDateTime);
        }

        void sortTasksByDeadline()
        {
            tasks.sort(Comparator.comparing(Task::getDeadline));
        }
    }

    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        // Generowanie losowych zadań na najbliższe 7 dni
        LocalDate today = LocalDate.now();
        manager.generateRandomTasks(10, today, 7);

        // Sortowanie zadań
        manager.sortTasksByDeadline();

        // Wyświetlanie wszystkich zadań
        System.out.println("=== Wszystkie zadania ===");
        List<Task> allTasks = manager.getTasks();
//        ???
        allTasks.forEach( task -> System.out.println(task));

        // Zmiana statusu kilku zadań
        if (!allTasks.isEmpty()) {
            allTasks.get(0).setStatus(TaskStatus.IN_PROGRESS);
            if (allTasks.size() > 1) {
                allTasks.get(1).setStatus(TaskStatus.COMPLETED);
            }
        }

        // Filtrowanie zadań według priorytetu
        System.out.println("\n=== Zadania o wysokim priorytecie ===");
//        ???
        manager.fillByPriority(Priority.HIGH).forEach(System.out::println);

        // Sprawdzanie przekroczenia terminu przy użyciu interfejsu funkcyjnego
        TaskManager.DeadlineChecker checker = manager.getDeadlineChecker();
        LocalDateTime currentTime = LocalDateTime.now();

        System.out.println("\n=== Sprawdzanie przekroczenia terminów ===");
//        ???
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyy HH:mm");
        allTasks.forEach( task -> {if (checker.isOverdue(task, currentTime)) System.out.println("Zadanie przeterminowane: " + task.getName()+" - Deadline: "+ task.getDeadline().format(formatter));});

    }
}

//Uwaga: Z racji losowości problemu, rozwiązanie jest orientacyjne.
//
//        === Wszystkie zadania ===
//Zadanie: Zorganizować spotkanie, Deadline: 20.05.2025 12:15, Priorytet: MEDIUM, Status: NEW
//Zadanie: Zorganizować spotkanie, Deadline: 20.05.2025 14:45, Priorytet: MEDIUM, Status: NEW
//Zadanie: Przygotować raport, Deadline: 21.05.2025 11:00, Priorytet: LOW, Status: NEW
//Zadanie: Sprawdzić maile, Deadline: 22.05.2025 14:15, Priorytet: HIGH, Status: NEW
//Zadanie: Opracować prezentację, Deadline: 23.05.2025 12:45, Priorytet: MEDIUM, Status: NEW
//Zadanie: Zorganizować spotkanie, Deadline: 23.05.2025 15:00, Priorytet: MEDIUM, Status: NEW
//Zadanie: Zorganizować spotkanie, Deadline: 23.05.2025 15:00, Priorytet: LOW, Status: NEW
//Zadanie: Zaktualizować dokumentację, Deadline: 24.05.2025 15:15, Priorytet: LOW, Status: NEW
//Zadanie: Zorganizować spotkanie, Deadline: 25.05.2025 17:15, Priorytet: MEDIUM, Status: NEW
//Zadanie: Przejrzeć kod, Deadline: 26.05.2025 15:30, Priorytet: HIGH, Status: NEW
//
//=== Zadania o wysokim priorytecie ===
//Zadanie: Sprawdzić maile, Deadline: 22.05.2025 14:15, Priorytet: HIGH, Status: NEW
//Zadanie: Przejrzeć kod, Deadline: 26.05.2025 15:30, Priorytet: HIGH, Status: NEW
//
//=== Sprawdzanie przekroczenia terminów ===
//Zadanie przeterminowane: Zorganizować spotkanie - Deadline: 20.05.2025 12:15
