package analiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Analizador {

    public int[] acha_sequencia(double[] probs, int[] valores) {
        int tamanho = valores.length;

        ArrayList<Integer> ordem = new ArrayList<>();
        ArrayList<Integer> usados = new ArrayList<>();

        while (ordem.size() < tamanho) {
            int indiceSelecionado = seleciona(probs, valores, usados);
            ordem.add(indiceSelecionado);
            usados.add(indiceSelecionado);
        }

        return ordem.stream().mapToInt(Integer::intValue).toArray();
    }

    public int seleciona(double[] probs, int[] valores, ArrayList<Integer> usados) {
        ArrayList<Integer> naoUsados = new ArrayList<>();
        
        // Identifica os índices não utilizados
        for (int i = 0; i < probs.length; i++) {
            if (!usados.contains(i)) {
                naoUsados.add(i);
            }
        }
        
        // Ordena os índices não utilizados com base no valor calculado
        Collections.sort(naoUsados, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                double valor1 = valores[o1] * probs[o1] * probs[o1];
                double valor2 = valores[o2] * probs[o2] * probs[o2];
                return Double.compare(valor2, valor1);
            }
        });

        // Retorna o primeiro índice da lista ordenada
        return naoUsados.get(0);
    }
}
