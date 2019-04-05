package org.sofl.soptorshi.client;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.sofl.soptorshi.MainView;
import org.springframework.security.core.context.SecurityContextHolder;

import java.awt.*;

@Route(value = "logout", layout = MainView.class)
public class LogoutView extends VerticalLayout {
    public LogoutView() {
        Button logoutButton = new Button("Logout?");
        Button cancelLogout = new Button("Cancel");

        logoutButton.addClickListener(event->{
            SecurityContextHolder.clearContext();
           getUI().ifPresent(ui->ui.navigate("login"));
        });

        cancelLogout.addClickListener(event->{
//           getUI().ifPresent(ui-> ui.na);
        });
        HorizontalLayout buttonHolders = new HorizontalLayout();
        buttonHolders.add(logoutButton);

        add(buttonHolders);
        setAlignSelf(Alignment.CENTER, buttonHolders);
    }
}
