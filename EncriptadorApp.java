import java.util.*;

public class Encriptador {
    private static final String DEFAULT_SEPARATOR = " ";
    private static final String DEFAULT_TEXT_FILTER = "[^A-Z ]";

    private final Map<Character, String> characterCodes;

    public Encriptador() {
        characterCodes = new HashMap<>();
        initializeDefaultCodes();
    }

    private void initializeDefaultCodes() {
        characterCodes.put('A', ".-");
        characterCodes.put('B', "-...");
        characterCodes.put('C', "-.-.");
        // Rest of the alphabet and additional codes
    }

    public void addCode(char character, String code) {
        characterCodes.put(character, code);
    }

    public String encode(List<Character> characters) {
        StringBuilder encoded = new StringBuilder();
        for (char c : characters) {
            String code = characterCodes.getOrDefault(Character.toUpperCase(c), String.valueOf(c));
            encoded.append(code).append(DEFAULT_SEPARATOR);
        }
        return encoded.toString().trim();
    }

    public Map<String, String> encodeText(String text, List<String> codeTypes) {
        validateInputParameters(text, codeTypes);

        Map<String, String> encodings = new HashMap<>();
        for (String codeType : codeTypes) {
            String encoding = encodeByCodeType(text, codeType);
            encodings.put(codeType, encoding);
        }
        return encodings;
    }

    private void validateInputParameters(String text, List<String> codeTypes) {
        if (text == null || text.isEmpty() || codeTypes == null || codeTypes.isEmpty()) {
            throw new IllegalArgumentException("Invalid input parameters");
        }
    }

    private String encodeByCodeType(String text, String codeType) {
        switch (codeType) {
            case CodeTypes.MORSE:
                return encodeMorse(text);
            case CodeTypes.NUMERICO:
                return encodeNumerico(text);
            case CodeTypes.BINARIO:
                return encodeBinario(text);
            case CodeTypes.INVERTIDO:
                return encodeInvertido(text);
            default:
                throw new IllegalArgumentException("Unknown code type: " + codeType);
        }
    }

    private String encodeMorse(String text) {
        List<Character> characters = toCharacterList(text);
        return encode(characters);
    }

    private String encodeNumerico(String text) {
        // Implementation for Numerico encoding
        return "";
    }

    private String encodeBinario(String text) {
        // Implementation for Binario encoding
        return "";
    }

    private String encodeInvertido(String text) {
        // Implementation for Invertido encoding
        return "";
    }

    private List<Character> toCharacterList(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Input text cannot be null");
        }

        try {
            text = text.toUpperCase().replaceAll(DEFAULT_TEXT_FILTER, "");
            return text.chars().mapToObj(c -> (char) c).toList();
        } catch (NullPointerException | IllegalArgumentException e) {
            return Collections.emptyList();
        }
    }

    public static void main(String[] args) {
        System.out.println("****");
        System.out.println("*     Challenge ONE Codificador 5    *");
        System.out.println("****\n");

        Encriptador encriptador = new Encriptador();

        // Add custom codes if needed
        encriptador.addCode('Z', "--..");
        encriptador.addCode(' ', " ");

        String text = "Oracle one, grupo 5 Alejandro Moguel Uribe";
        List<String> codeTypes = List.of(CodeTypes.MORSE, CodeTypes.NUMERICO, CodeTypes.BINARIO, CodeTypes
