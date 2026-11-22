package org.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Métodos auxiliares de leitura da consola, datas e segurança.
 * Baseado na classe Utils fornecida nas aulas (Paulo Maio & Dulce Mota).
 */
public class Utils {

    private static final BufferedReader IN =
            new BufferedReader(new InputStreamReader(System.in));

    /**
     * Mostra uma pergunta e lê uma linha da consola.
     *
     * @param strPrompt texto a apresentar
     * @return a linha lida (ou "0" se o input terminar)
     */
    public static String readLineFromConsole(String strPrompt) {
        try {
            System.out.println(strPrompt);
            String linha = IN.readLine();
            return linha == null ? "0" : linha.trim();
        } catch (IOException e) {
            e.printStackTrace();
            return "0";
        }
    }

    /**
     * Lê um número inteiro da consola, repetindo até ser válido.
     */
    public static int IntFromConsole(String strPrompt) {
        do {
            try {
                return Integer.parseInt(readLineFromConsole(strPrompt));
            } catch (NumberFormatException ex) {
                System.out.println("Tem de introduzir um número inteiro.");
            }
        } while (true);
    }

    /**
     * Lê um número real da consola, repetindo até ser válido.
     */
    public static double DoubleFromConsole(String strPrompt) {
        do {
            try {
                return Double.parseDouble(readLineFromConsole(strPrompt).replace(",", "."));
            } catch (NumberFormatException ex) {
                System.out.println("Tem de introduzir um número.");
            }
        } while (true);
    }

    /**
     * Pede uma confirmação (S/N) ao utilizador.
     *
     * @return true se o utilizador responder "s"
     */
    public static boolean confirma(String sMessage) {
        String strConfirma;
        do {
            strConfirma = readLineFromConsole("\n" + sMessage + "\n");
        } while (!strConfirma.equalsIgnoreCase("s") && !strConfirma.equalsIgnoreCase("n")
                && !strConfirma.equals("0"));

        return strConfirma.equalsIgnoreCase("s");
    }

    /**
     * Apresenta uma lista de objetos e pede a seleção de um.
     *
     * @return o objeto selecionado ou null se o utilizador cancelar (0)
     */
    public static Object apresentaESeleciona(List list, String sHeader) {
        apresentaLista(list, sHeader);
        return selecionaObject(list);
    }

    public static void apresentaLista(List list, String sHeader) {
        System.out.println(sHeader);

        int index = 0;
        for (Object o : list) {
            index++;
            System.out.println(index + ". " + o.toString());
        }
        System.out.println("");
        System.out.println("0 - Cancelar");
    }

    public static Object selecionaObject(List list) {
        int nOpcao;
        do {
            nOpcao = IntFromConsole("Introduza opção: ");
        } while (nOpcao < 0 || nOpcao > list.size());

        if (nOpcao == 0) {
            return null;
        }
        return list.get(nOpcao - 1);
    }

    /**
     * @return a data de hoje no formato dd-MM-yyyy
     */
    public static String getDataHoje() {
        java.time.LocalDate hoje = java.time.LocalDate.now();
        return hoje.format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    /**
     * Verifica se uma String é uma data válida no formato dd-MM-yyyy.
     */
    public static boolean dataValida(String data) {
        if (data == null || !data.matches("\\d{2}-\\d{2}-\\d{4}")) return false;
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            df.setLenient(false);
            df.parse(data);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Verifica se uma data está entre duas datas (inclusive). Formato dd-MM-yyyy.
     */
    public static boolean dataDentroDoIntervalo(String data, String inicio, String fim) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            df.setLenient(false);

            Date d = df.parse(data);
            Date di = df.parse(inicio);
            Date dfim = df.parse(fim);

            return !d.before(di) && !d.after(dfim);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Cifra uma password com SHA-256 (a password nunca é guardada em claro).
     *
     * @param password password em claro
     * @return hash da password em hexadecimal
     */
    public static String hash(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
