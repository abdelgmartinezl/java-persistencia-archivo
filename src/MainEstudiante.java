import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MainEstudiante {
    private static List<Estudiante> listaEstudiantes = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        precargarEstudiante();

        int opcion;

        do {
            System.out.println("MENU");
            System.out.println("1. Ver estudiantes");
            System.out.println("2. Agregar estudiante");
            System.out.println("3. Salir");
            System.out.print("\nOPCION: ");
            try {
                opcion = scanner.nextInt();
            } catch (InputMismatchException e) {
                opcion = 100;
                scanner.next();
            }

            switch (opcion) {
                case 1:
                    verEstudiante();
                    break;
                case 2:
                    agregarEstudiante(scanner);
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("ERR::Opcion invalida");
            }
        } while (opcion != 3);
    }

    private static void verEstudiante() {
        for (Estudiante estudiante : listaEstudiantes) {
            System.out.println("\n" + estudiante);
            System.out.println("Nombre: " + estudiante.getNombre());
            System.out.println("Edad: " + estudiante.getEdad());
        }
    }

    private static void agregarEstudiante(Scanner scanner) {
        System.out.print("Nombre del estudiante: ");
        String nombre = scanner.next();
        System.out.print("Edad del estudiante: ");
        int edad;
        try {
            edad = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("ERR::Entrada invalida. Edad debe ser numero.");
            scanner.next();
            return;
        }

        listaEstudiantes.add(new Estudiante(nombre, edad));
    }

    private static void precargarEstudiante() {
        try {
            BufferedReader archivo = new BufferedReader(new FileReader("estudiantes.dat"));
            String linea;
            while ((linea = archivo.readLine()) != null) {
                List<String> datosEstudiante = Arrays.asList(linea.split(","));
                String nombre = datosEstudiante.get(0);
                int edad = Integer.parseInt(datosEstudiante.get(1));
                listaEstudiantes.add(new Estudiante(nombre, edad));
            }
            archivo.close();
        } catch (IOException e) {
            System.err.println("No existe archivo: e.getMessage()");
        }
    }
}
