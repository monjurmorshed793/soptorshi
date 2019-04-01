package org.sofl.soptorshi.client;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("forgot-password")
public class ForgotPasswordView extends HorizontalLayout {
    public ForgotPasswordView() {
        Div panel = new Div();
        panel.setTitle("Password Recovery");
        VerticalLayout content = new VerticalLayout();
        Label labelForEmployeeId = new Label("Insert your employee id");
        content.add(labelForEmployeeId);
        TextField employeeId = new TextField("Employee Id");
        content.add(employeeId);
        panel.add(content);
        add(panel);
        setAlignSelf(Alignment.CENTER, panel);
    }
}
