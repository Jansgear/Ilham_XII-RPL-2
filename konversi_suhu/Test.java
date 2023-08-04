import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the temperature in Celsius: ");
        double celsius = scanner.nextDouble();

        double fahrenheit = (celsius * 9 / 5) + 32;
        
        double kelvin = celsius + 273.15;

        System.out.println("Temperature in Celsius: " + celsius);

        System.out.println("Temperature in Fahrenheit: " + fahrenheit);

        System.out.println("Temperature in Kelvin: " + kelvin);

        scanner.close();
    }
}
