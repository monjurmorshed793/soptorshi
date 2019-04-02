package org.sofl.soptorshi.client;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.sofl.soptorshi.MainView;

@Route(value = "profile", layout = MainView.class)
public class ProfileView extends VerticalLayout {
    public ProfileView(){
        add(new Label("View 1"));
    }
}
