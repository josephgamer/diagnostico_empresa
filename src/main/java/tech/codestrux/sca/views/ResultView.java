package tech.codestrux.sca.views;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
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

    @PostConstruct
    public void init() {
        correctAnswers.put(1, "b) Confidencialidad");
        correctAnswers.put(2, "Usar más de un método de verificación para acceder al sistema");
        correctAnswers.put(3, "b) Restringir a los usuarios únicamente a las funciones necesarias para realizar su trabajo");
        correctAnswers.put(4, "d) Bases de datos mal configuradas");
        correctAnswers.put(5, "b) Codificar correctamente las entradas del usuario al renderizarlas");
        correctAnswers.put(6, "d) Implementación de tokens de sesión");
        correctAnswers.put(7, "b) Usar un algoritmo hash con sal (salted hashing)");
        correctAnswers.put(8, "");
    }

    public ResultView(Prueba prueba) {
        add(new H3("Resultados"));
        add(new H4(prueba.getRespuestas().size() + " respuestas"));
        add(new H4(prueba.getRespuestas().get(1)));
        
        Button button = new Button("Finalizar", e -> {
            UI.getCurrent().navigate("");
        });
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button.addClickShortcut(Key.ENTER);
        addClassName("wider-content");
    }
}
