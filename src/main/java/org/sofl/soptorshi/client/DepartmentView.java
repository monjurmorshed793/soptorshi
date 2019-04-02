package org.sofl.soptorshi.client;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.sofl.soptorshi.MainView;
import org.springframework.security.access.annotation.Secured;

@Secured({"ROLE_USER"})
@Route(value = "department", layout = MainView.class)
public class DepartmentView extends VerticalLayout {
    public DepartmentView() {
        add(new Label("Department view"));
    }
}
