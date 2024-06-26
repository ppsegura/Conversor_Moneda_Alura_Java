import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        int selector = 0;
        double amount = 0.00;

        CurrencyConverter converter = new CurrencyConverter();
        PairConversion pairConversion;

        while (selector != 8) {
            printMenu();
            selector = sc.nextInt();

            if (selector >= 1 && selector <= 6) {
                String conversionPair = getConversionPair(selector);
                System.out.println("Ingrese el valor a convertir:");
                amount = sc.nextDouble();
                pairConversion = converter.pairConversion(conversionPair,amount);
                System.out.println(converter.getMessage(amount,
                        conversionPair.substring(0, 3),
                        pairConversion.conversion_result(),
                        conversionPair.substring(4, 7)));
                JSONFileManager generator = new JSONFileManager();
                generator.saveJSONFile(pairConversion);
            } else if (selector == 7) {
                String sourceCurrency = converter.getCurrency(sc, "Ingrese la moneda fuente (ejemplo: USD): ");
                String targetCurrency = converter.getCurrency(sc, "Ingrese la moneda destino (ejemplo: PEN): ");
                System.out.println("Ingrese el valor a convertir:");
                amount = sc.nextDouble();
                pairConversion = converter.pairConversion(sourceCurrency + "/" + targetCurrency + "/", amount);
                System.out.println(converter.getMessage(amount,sourceCurrency,pairConversion.conversion_result(),targetCurrency));
                JSONFileManager generator = new JSONFileManager();
                generator.saveJSONFile(pairConversion);
            } else if (selector == 8) {
                System.out.println("Saliendo del programa");
            } else {
                System.out.println("Elija una opción válida:");
            }
        }
        sc.close();
    }

    private static void printMenu() {
        System.out.println("""
                ********************************************
                Sea bienvenido/a al conversor de moneda\n
                1) Dólar ==> Peso argentino
                2) Peso argentino ==> Dólar
                3) Dólar ==> Real Brasileño
                4) Real brasileño ==> Dólar
                5) Dólar ==> Peso colombiano
                6) Peso colombiano ==> Dólar
                7) Convierta otro tipo de monedas
                8) Salir
                ********************************************
            """);
    }

    private static String getConversionPair(int selector) {
        return switch (selector) {
            case 1 -> "USD/ARS/";
            case 2 -> "ARS/USD/";
            case 3 -> "USD/BRL/";
            case 4 -> "BRL/USD/";
            case 5 -> "USD/COP/";
            case 6 -> "COP/USD/";
            default -> "";
        };
    }
}
