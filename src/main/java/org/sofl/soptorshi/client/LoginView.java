package org.sofl.soptorshi.client;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;
import org.sofl.soptorshi.MessageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

@Route("login")
public class LoginView extends Div {
    private LoginOverlay loginOverlay;

    public LoginView() {
        loginOverlay = new LoginOverlay();
        loginOverlay.setOpened(true);
        SecurityContextHolder.clearContext();
//        loginOverlay.setTitle("SEVEN OCEANS FISH PRODUCTION LTD");
        Component titleComponent = new Image();
        ((Image) titleComponent).setSrc("img/seven-oceans.jpg");
        loginOverlay.setTitle(titleComponent);
        loginOverlay.setDescription("Insert Employee Id and Password");
        loginOverlay.setAction("login");
        loginOverlay.addForgotPasswordListener(e->{
            Notification.show("Forgot password not yet handled", 2000, Notification.Position.TOP_CENTER);
        });
        add(loginOverlay);
    }
}
