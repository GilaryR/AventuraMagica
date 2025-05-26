package autonoma.AventuraMagica.elements;

import autonoma.AventuraMagica.elements.Artefacto;
import autonoma.AventuraMagica.elements.Enemigo;
import autonoma.AventuraMagica.elements.SimboloPregunta;
import java.util.List;

public interface Nivel {
    List<Enemigo> getEnemigos();
    List<Artefacto> getArtefactos();
    List<SimboloPregunta> getSimbolosPregunta();

    public int getArtefactosRequeridos();

    public int size();
}