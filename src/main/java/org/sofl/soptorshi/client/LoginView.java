package org.sofl.soptorshi.client;

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
        Image logoImage = new Image();
        logoImage.setSrc("/icons/seven-oceans.jpg");
        loginOverlay.setTitle(logoImage);
        loginOverlay.setDescription("Insert Employee Id and Password");
        loginOverlay.setAction("login");
        loginOverlay.addForgotPasswordListener(e->{
            Notification.show("Forgot password not yet handled", 30, Notification.Position.TOP_CENTER);
        });
        add(loginOverlay);
    }
}
