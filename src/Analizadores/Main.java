package Analizadores;

import java.io.StringReader;
import java_cup.runtime.Symbol;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        
        while (true) {
            System.out.println("1. Ingresar cadena para análisis léxico");
            System.out.println("2. Salir");
            System.out.print("Ingrese su opción: ");
            int opcion = lectura.nextInt();
            
            switch (opcion) {
                case 1:
                    System.out.println("Ingrese su entrada:");
                    lectura.nextLine(); // Consumir el salto de línea pendiente
                    String input = lectura.nextLine(); // Leer la entrada de la consola
                    
                    // Crear un analizador léxico
                    Analizador_Lexico lexer = new Analizador_Lexico(new StringReader(input));
                    
                    try {
                        // Iterar sobre los tokens y mostrar la información
                        while (true) {
                            Symbol token = lexer.next_token();
                            if (token.sym == Symbols.EOF) {
                                System.out.println("Análisis léxico completado.");
                                break;
                            }
                            String lexema = token.value.toString();
                            int linea = token.left + 1; // Sumar 1 para hacer la numeración de línea base 1
                            int columna = token.right + 1; // Sumar 1 para hacer la numeración de columna base 1
                            System.out.println("Token: " + Symbols.terminalNames[token.sym] + 
                                               ", Lexema: " + lexema + 
                                               ", Línea: " + linea + 
                                               ", Columna: " + columna);
                        }
                    } catch (Exception e) {
                        System.err.println("Error durante el análisis léxico: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("Saliendo del programa...");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, ingrese 1 o 2.");
            }
        }
    }
}
