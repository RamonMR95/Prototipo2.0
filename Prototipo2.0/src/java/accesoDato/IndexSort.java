package accesoDato;

import java.util.List;

import modelo.Identificable;

public abstract class IndexSort {
	
	
	public int indexSort(String id, List<Identificable> datos) {
		
		int size = datos.size();
		int puntoMedio;
		int limiteInferior = 0;
		int limiteSuperior = size - 1;

		while (limiteInferior <= limiteSuperior) {
			puntoMedio = (limiteSuperior + limiteInferior) / 2;
			int comparacion = id.compareTo(datos.get(puntoMedio).getId());

			if (comparacion == 0) {
				return puntoMedio + 1;
			}

			if (comparacion > 0) {
				limiteInferior = puntoMedio + 1;
			} 
			else {
				limiteSuperior = puntoMedio - 1;
			}

		}
		return -(limiteInferior + 1);
	}
}
