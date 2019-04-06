package org.sofl.soptorshi;

import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementContainer;
import com.github.appreciated.app.layout.component.appbar.AppBarBuilder;
import com.github.appreciated.app.layout.component.appmenu.left.LeftNavigationComponent;
import com.github.appreciated.app.layout.component.appmenu.left.builder.LeftAppMenuBuilder;
import com.github.appreciated.app.layout.component.appmenu.left.builder.LeftSubMenuBuilder;
import com.github.appreciated.app.layout.entity.DefaultBadgeHolder;
import com.github.appreciated.app.layout.notification.DefaultNotificationHolder;
import com.github.appreciated.app.layout.notification.component.AppBarNotificationButton;
import com.github.appreciated.app.layout.router.AppLayoutRouterLayout;
import com.vaadin.flow.component.icon.VaadinIcon;
import org.sofl.soptorshi.client.*;
import org.sofl.soptorshi.client.configuration.view.DepartmentView;
import org.sofl.soptorshi.client.configuration.view.DesignationView;
import org.sofl.soptorshi.client.configuration.view.LocationView;
import org.sofl.soptorshi.client.configuration.view.RoleView;
import org.sofl.soptorshi.client.employee.management.EmployeeDetailsView;
import org.sofl.soptorshi.client.employee.management.EmployeeView;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

@Route
@PWA(name = "Soptorshi", shortName = "Soptorshi")
public class MainView extends AppLayoutRouterLayout {

    private DefaultNotificationHolder notifications;
    private DefaultBadgeHolder badge;


    public MainView(@Autowired MessageBean bean) {
        notifications = new DefaultNotificationHolder(newStatus->{

        });
        badge = new DefaultBadgeHolder(5);
        LeftNavigationComponent menuEntry = new LeftNavigationComponent("Menu", VaadinIcon.MENU.create(), ProfileView.class);
        badge.bind(menuEntry.getBadge());


        LeftSubMenuBuilder configurationSubMenu = LeftSubMenuBuilder
                .get("Configuration", VaadinIcon.COG_O.create());

        configurationSubMenu.add(new LeftNavigationComponent("Role", VaadinIcon.ACCESSIBILITY.create(), RoleView.class));
        configurationSubMenu.add(new LeftNavigationComponent("Department", VaadinIcon.FACTORY.create(), DepartmentView.class));
        configurationSubMenu.add(new LeftNavigationComponent("Designation", VaadinIcon.SPECIALIST.create(), DesignationView.class));
        configurationSubMenu.add(new LeftNavigationComponent("Location", VaadinIcon.ABSOLUTE_POSITION.create(), LocationView.class));



        LeftAppMenuBuilder leftMenuBar = LeftAppMenuBuilder
                .get()
                .add(configurationSubMenu.build());



        LeftSubMenuBuilder employeeManagementSubMenu = LeftSubMenuBuilder
                .get("Employee Management", VaadinIcon.GROUP.create());

        employeeManagementSubMenu.add(new LeftNavigationComponent("Employee List", VaadinIcon.BULLETS.create(), EmployeeView.class));

        leftMenuBar.add(employeeManagementSubMenu.build());
        leftMenuBar.add("Logout", VaadinIcon.SIGN_OUT.create(), LogoutView.class);


        AppLayoutBuilder appLayoutBuilder = AppLayoutBuilder
                .get(Behaviour.LEFT_RESPONSIVE_HYBRID)
                .withTitle("Soptorshi")
                .withAppBar(AppBarBuilder.get()
                        .add(new AppBarNotificationButton(VaadinIcon.BELL , notifications))
                        .build());
        appLayoutBuilder.withAppMenu(leftMenuBar.build());



               /* .withAppMenu(LeftAppMenuBuilder.get()
//                        .addToSection(new MenuHeaderComponent("Menu-Header", "APP_LAYOUT_VERSION", "/img/seven-oceans.jpg"), Section.HEADER)
//                        .addToSection(new LeftClickableComponent("Clickable Entry", VaadinIcon.COG.create(), clickEvent -> Notification.show("onClick ...")), Section.HEADER)
//                        .add(new LeftNavigationComponent("Home", VaadinIcon.HOME.create(), DepartmentView.class))
                        .add(LeftSubMenuBuilder.get("Configuration", VaadinIcon.PLUS.create())
//                                .add(LeftSubMenuBuilder.get("My Submenu", VaadinIcon.PLUS.create())
//                                        .add(new LeftNavigationComponent("Charts", VaadinIcon.SPLINE_CHART.create(), RoleView.class))
//                                        .add(new LeftNavigationComponent("Contact", VaadinIcon.CONNECT.create(), ProfileView.class))
//                                        .add(new LeftNavigationComponent("More", VaadinIcon.COG.create(), ProfileView.class))
//                                        .build())
                                .add(new LeftNavigationComponent("Profile", VaadinIcon.CONNECT.create(), ProfileView.class))
                                .add(new LeftNavigationComponent("Profile", VaadinIcon.CONNECT.create(), DepartmentView.class))
//                                .add(new LeftNavigationComponent("More1", VaadinIcon.COG.create(), ProfileView.class))
                                .build())
//                        .add(menuEntry)
//                        .addToSection(new LeftClickableComponent("Clickable Entry", VaadinIcon.COG.create(), clickEvent -> Notification.show("onClick ...")), Section.FOOTER)
                        .build());*/
        init(appLayoutBuilder.build());
    }

}
