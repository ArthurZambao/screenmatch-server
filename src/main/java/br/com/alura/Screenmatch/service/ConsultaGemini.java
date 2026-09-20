package br.com.alura.Screenmatch.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConsultaGemini {

    // Tenta na ordem: se um modelo estiver sobrecarregado ou aposentado, vai pro próximo
    private static final String[] MODELOS = {
            "gemini-3.6-flash",
            "gemini-3.5-flash-lite"
    };
    private static final int TENTATIVAS_POR_MODELO = 3;

    private static final String BASE_URL =
            "https://generativelanguage.googleapis.com/v1beta/models/";

    private static final String API_KEY = System.getenv("GEMINI_API_KEY");
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();

    // Cache: texto original -> tradução (só guarda traduções que deram certo)
    private static final Map<String, String> cache = new ConcurrentHashMap<>();

    public static String obterTraducao(String texto) {
        if (texto == null || texto.isBlank() || texto.equals("N/A")) {
            return "";
        }

        String emCache = cache.get(texto);
        if (emCache != null) {
            return emCache;
        }

        try {
            String prompt = "Traduza para o português brasileiro o texto abaixo. "
                    + "Responda somente com a tradução, sem comentários:\n\n" + texto;

            ObjectNode body = mapper.createObjectNode();
            ArrayNode contents = body.putArray("contents");
            ArrayNode parts = contents.addObject().putArray("parts");
            parts.addObject().put("text", prompt);
            String corpo = mapper.writeValueAsString(body);

            for (String modelo : MODELOS) {
                for (int tentativa = 1; tentativa <= TENTATIVAS_POR_MODELO; tentativa++) {
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(BASE_URL + modelo + ":generateContent"))
                            .header("Content-Type", "application/json")
                            .header("x-goog-api-key", API_KEY)
                            .POST(HttpRequest.BodyPublishers.ofString(corpo))
                            .build();

                    HttpResponse<String> response =
                            client.send(request, HttpResponse.BodyHandlers.ofString());

                    int status = response.statusCode();

                    if (status == 200) {
                        JsonNode json = mapper.readTree(response.body());
                        String traducao = json.path("candidates").path(0)
                                .path("content").path("parts").path(0)
                                .path("text").asText("").trim();

                        if (traducao.isEmpty()) {
                            return texto;
                        }
                        cache.put(texto, traducao);
                        return traducao;
                    }

                    // 404 = modelo não existe/aposentado: pula pro próximo modelo
                    if (status == 404) {
                        System.out.println("Modelo " + modelo + " indisponível (404), tentando o próximo...");
                        break;
                    }

                    // 429 (limite) e 5xx (sobrecarga) valem retry; o resto é erro de verdade
                    boolean temporario = status == 429 || status >= 500;
                    if (!temporario) {
                        System.out.println("Erro na API do Gemini (" + status + "): " + response.body());
                        return texto;
                    }

                    System.out.println("Gemini (" + modelo + ") erro " + status + ", tentativa "
                            + tentativa + "/" + TENTATIVAS_POR_MODELO + ": " + response.body());
                    Thread.sleep(2000L * tentativa); // espera 2s, 4s, 6s
                }
            }

            System.out.println("Não consegui traduzir agora, devolvendo o texto original.");
            return texto;

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return texto;
        } catch (Exception e) {
            System.out.println("Erro ao traduzir: " + e.getMessage());
            return texto;
        }
    }
}