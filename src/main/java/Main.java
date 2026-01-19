import java.io.IOException;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        try {
            Service s = new Service();
            Scanner scanner = new Scanner(System.in);
            boolean running = true;

            while (running) {
                System.out.println("\n Wybierz opcję:");
                System.out.println("1. Dodaj studenta (nowy format)");
                System.out.println("2. Wypisz studentów");
                System.out.println("3. Wyszukaj studenta po imieniu");
                System.out.println("0. Wyjście");
                System.out.print("Twój wybór: ");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Podaj imię: ");
                        String name = scanner.next();

                        System.out.print("Podaj wiek: ");
                        int age = scanner.nextInt();

                        System.out.print("Podaj datę urodzenia (np. 2000-01-01): ");
                        String dob = scanner.next(); 

                        s.addStudent(new Student(name, age, dob)); 
                        break;

                    case 2:
                        var students = s.getStudents();
                        System.out.println("Lista studentów:");
                        for (Student current : students) {
                            System.out.println(current.ToString());
                        }
                        break;
                        
                    case 3: 
                        System.out.print("Podaj imię do wyszukania: ");
                        String searchName = scanner.next();
                        Student foundStudent = s.findStudentByName(searchName);
                        if (foundStudent != null)
                            System.out.println("Znaleziono studenta: " + foundStudent.ToString());
                        else
                            System.out.println("Nie znaleziono studenta o podanym imieniu.");
                        break;
                    


                    case 0:
                        running = false;
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("Błąd IO: " + e.getMessage());
        }
    }
}