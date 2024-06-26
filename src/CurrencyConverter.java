import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class CurrencyConverter {

    PairConversion pairConversion(String par_conversion, double amount){

        URI url = URI.create("https://v6.exchangerate-api.com/v6/10111fd44883f04eeb57fef0/pair/"+par_conversion+amount);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), PairConversion.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public String getCurrency(Scanner scanner, String prompt) {
        System.out.println(prompt);
        String numberChar = scanner.next();
        while (numberChar.length() > 3) {
            System.out.println("Error: La entrada debe tener como máximo 3 caracteres.");
            System.out.println(prompt);
            numberChar = scanner.next();
        }
        return numberChar.toUpperCase();
    }

    public String getMessage(double amount, String source, double result, String target) {
        String message = """
                El valor %.2f [%s] corresponde al valor final de ==> %.2f [%s]
                """.formatted(amount, source, result,target);
        return message;
    }

}
