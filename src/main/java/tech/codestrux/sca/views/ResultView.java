package tech.codestrux.sca.views;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import tech.codestrux.sca.models.Prueba;

import java.util.HashMap;
import java.util.Map;

/**
 * The main view contains a button and a click listener.
 */
@Route("result")
@PageTitle("Resultados")
@PreserveOnRefresh
public class ResultView extends VerticalLayout {

    @Autowired
    Prueba prueba;

    private Map<Integer, String> correctAnswers = new HashMap<>();

    private Integer totalCorrectAnswers = 0;

    public ResultView(Prueba prueba) {
        init();
        add(new H3("Resultados"));
        add(new H4(prueba.getRespuestas().size() + " respuestas contestadas"));
        for (Map.Entry<Integer, String> entry : correctAnswers.entrySet()) {
            Integer clave = entry.getKey();
            String valor = entry.getValue();
            if (prueba.getRespuestas().get(clave) != null) {
                String respuesta = prueba.getRespuestas().get(clave);
                if (respuesta.equals(valor)) {
                    totalCorrectAnswers++;
                }
            }
        }


        add(new H5("Total de respuestas correctas: " + totalCorrectAnswers));
        int divide = prueba.getRespuestas().size();
        add(new H5("Nota total: " + returnNote(totalCorrectAnswers, divide)));

        add(new H4("Respuestas del examen"));
        for (Map.Entry<Integer, String> entry : correctAnswers.entrySet()) {
            add(new H5(entry.getKey() + " - " + entry.getValue()));
        }
        
        Button button = new Button("Finalizar", e -> {
            UI.getCurrent().navigate("");
        });
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button.addClickShortcut(Key.ENTER);
        addClassName("wider-content");
    }

    public void init() {
        correctAnswers.put(1, "b) Confidencialidad");
        correctAnswers.put(2, "b) Usar más de un método de verificación para acceder al sistema");
        correctAnswers.put(3, "b) Restringir a los usuarios únicamente a las funciones necesarias para realizar su trabajo");
        correctAnswers.put(4, "d) Bases de datos mal configuradas");
        correctAnswers.put(5, "b) Codificar correctamente las entradas del usuario al renderizarlas");
        correctAnswers.put(6, "d) Implementación de tokens de sesión");
        correctAnswers.put(7, "b) Usar un algoritmo hash con sal (salted hashing)");
        correctAnswers.put(8, "c) Un protocolo para cifrar comunicaciones web");
        correctAnswers.put(9, "a) Para mejorar el rendimiento del servidor");
        correctAnswers.put(10, "b) Documentarla y solucionarla lo antes posible");
        correctAnswers.put(11, "b) Implementar un sistema de revisión periódica para identificar amenazas y actividades sospechosas en tiempo real");
        correctAnswers.put(12, "b) ISO/IEC 27001");
        correctAnswers.put(13, "b) Un ataque DoS al servidor");
        correctAnswers.put(14, "b) Ataque de fuerza bruta - Implementar MFA");
        correctAnswers.put(15, "a) Añadir autenticación multifactor");
    }

    private double returnNote(int totalCorrectAnswers, int totalAnswers) {
        double preResult = (double) totalCorrectAnswers / totalAnswers;
        return Math.ceil(preResult * 100);
        //return (double) totalCorrectAnswers / totalAnswers;
        //return (double) 9 / 15;
    }
}
