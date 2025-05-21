/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.AventuraMagica.exception;

/**
 * Excepción lanzada cuando se selecciona una dificultad no válida
 *  * 
 * @author Gilary
 * @since 13-05-2025
 * @version 1.0
 */
public class DificultadNoValidaException extends IllegalArgumentException {
    private String dificultad;

    public DificultadNoValidaException(String dificultad) {
        super("Dificultad '" + dificultad + "' no válida. Opciones: Fácil, Normal, Difícil");
        this.dificultad = dificultad;
    }

    public String getDificultad() {
        return dificultad;
    }
}