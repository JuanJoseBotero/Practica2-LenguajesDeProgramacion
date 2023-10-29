import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.text.DecimalFormat;

public class Main {
    static List<Estudiante> estudiantes;

    public static void main(String[] args) throws IOException {
        cargarArchivo();
        mostrarEstudiantesPorCarrera();
        cantEstudiantesPorGenero();
        mostrarEstudianteNotaPorCarrera();
        mostrarEstudianteSacaron100();
        mostrarEstudiantePromedioPorCarrera();
    }
    static void cargarArchivo() throws IOException {
        Pattern pattern = Pattern.compile(",");
        String archivo = "student-scores.csv";
        Stream<String> lineas = Files.lines(Path.of(archivo));

        try {
            estudiantes = lineas.skip(1L).map((linea) -> {
                String[] campos = pattern.split(linea);
                return new Estudiante(Integer.parseInt(campos[0]), campos[1], campos[2], campos[4], campos[9], Integer.parseInt(campos[10]));
            }).collect(Collectors.toList());
            estudiantes.forEach(System.out::println);
        } catch (Throwable exception) {
            if (lineas != null) {
                try {
                    lineas.close();
                } catch (Throwable suppressed) {
                    exception.addSuppressed(suppressed);
                }
            }

            throw exception;
        }

        if (lineas != null) {
            lineas.close();
        }

    }

    static void mostrarEstudiantesPorCarrera() {
        Map<String, List<Estudiante>> estudiantesPorCarrera = estudiantes.stream()
                .collect(Collectors.groupingBy(Estudiante::getCareer_aspiration));

        System.out.println("\nEstudiantes por carrera:");

        estudiantesPorCarrera.forEach((carrera, listaEstudiantes) -> {
            System.out.println(carrera);
            listaEstudiantes.forEach(estudiante -> {
                System.out.printf(" %s%n", estudiante);
            });
        });
    }

    static void cantEstudiantesPorGenero() {
        System.out.printf("%nConteo de estudiantes por género:%n");
        Map<String, Long> conteoPorGenero = estudiantes.stream()
                .collect(Collectors.groupingBy(Estudiante::getGender, Collectors.counting()));

        conteoPorGenero.forEach((genero, conteo) -> {
            System.out.printf("Hay %d estudiantes de género %s%n", conteo, genero);
        });
    }

    static void mostrarEstudianteNotaPorCarrera() {
        System.out.println("\nEstudiantes que sacaron 100 por carrera:");
        Map<String, List<Estudiante>> estudiantesPorCarrera = estudiantes.stream()
                .collect(Collectors.groupingBy(Estudiante::getCareer_aspiration));

        estudiantesPorCarrera.forEach((carrera, listaEstudiantes) -> {
            System.out.println(carrera);
            listaEstudiantes.stream()
                    .filter(estudiante -> estudiante.getMath_score() == 100)
                    .forEach(System.out::println);
        });
    }

    static void mostrarEstudianteSacaron100() {
        System.out.println("\nEstudiantes que sacaron 100:");
        estudiantes.stream()
                .filter(estudiante -> estudiante.getMath_score() == 100)
                .forEach(System.out::println);
    }

    static void mostrarEstudiantePromedioPorCarrera() {
        Map<String, List<Estudiante>> agrupadoPorCarrera = estudiantes.stream()
                .collect(Collectors.groupingBy(Estudiante::getCareer_aspiration));
        System.out.println("\nPromedio de notas de los estudiantes por carrera:");
        DecimalFormat df = new DecimalFormat("#.##");
        agrupadoPorCarrera.forEach((carrer_aspiration, estudiantesPorCarrera) -> {
            System.out.print(carrer_aspiration + ": ");
            double average = estudiantesPorCarrera.stream()
                    .mapToDouble(Estudiante::getMath_score)
                    .average()
                    .orElse(0.0);
            String promedioFormateado = df.format(average);
            System.out.println(promedioFormateado);
        });
    }
}
    /*static void SumSalariosTotal() {
        Map var0 = (Map)empleados.stream().collect(Collectors.groupingBy(Empleado::getDepartamento));
        System.out.printf("%nNomina de cada departamento  %n");
        var0.forEach((var0x, var1) -> {
            System.out.print(var0x + ": ");
            System.out.println(var1.stream().mapToDouble(Empleado::getSalario).sum());
        });
    }

    static void EmpleadoGanaMasPorDept() {
        Function var0 = Empleado::getSalario;
        Comparator var1 = Comparator.comparing(var0);
        System.out.printf("%nEmpleados por departamento: %n");
        Map var2 = (Map)empleados.stream().collect(Collectors.groupingBy(Empleado::getDepartamento));
        var2.forEach((var1x, var2x) -> {
            System.out.print(var1x + ": ");
            Empleado var3 = (Empleado)var2x.stream().sorted(var1.reversed()).findFirst().get();
            PrintStream var10000 = System.out;
            String var10001 = var3.getPrimerNombre();
            var10000.println(var10001 + " " + var3.getApellidoPaterno() + " ///Cuanto money? ==> money: " + var3.getSalario() + " $$$");
        });
    }

    static void EmpleadoGanaMas() {
        Function var0 = Empleado::getSalario;
        Comparator var1 = Comparator.comparing(var0);
        Empleado var2 = (Empleado)empleados.stream().sorted(var1.reversed()).findFirst().get();
        System.out.printf("%nEmpleado que gana mas: %s %s %s %s%s%n", var2.getPrimerNombre(), var2.getApellidoPaterno(), " ///Cuanto money? ==> money: ", var2.getSalario(), " $$$");
    }

    static void EmpleadoGanaMenos() {
        Function var0 = Empleado::getSalario;
        Comparator var1 = Comparator.comparing(var0);
        Empleado var2 = (Empleado)empleados.stream().sorted(var1).findFirst().get();
        System.out.printf("%nEmpleado que gana menos: %s %s %s %s%s%n", var2.getPrimerNombre(), var2.getApellidoPaterno(), " ///Cuanto money? ==> money: ", var2.getSalario(), "$$$");
    }

    static void promSalaryDepa() {
        Map var0 = (Map)empleados.stream().collect(Collectors.groupingBy(Empleado::getDepartamento));
        System.out.println("\nPromedio de salarios de los empleados por Depa:");
        var0.forEach((var0x, var1) -> {
            System.out.print(var0x + ": ");
            System.out.println(var1.stream().mapToDouble(Empleado::getSalario).average().getAsDouble());
        });
    }

    static void promSalaryTotal() {
        System.out.printf("\nPromedio de salarios de todos los depas: %.2f%n", empleados.stream().mapToDouble(Empleado::getSalario).average().getAsDouble());
    }

    static void SumSalarios() {
        System.out.printf("%nSuma de todos los salarios: %.2f%n", empleados.stream().mapToDouble(Empleado::getSalario).sum());
    }
}

     */