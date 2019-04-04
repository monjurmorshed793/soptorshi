package org.sofl.soptorshi.client.configuration.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.sofl.soptorshi.MainView;
import org.sofl.soptorshi.model.Department;
import org.sofl.soptorshi.model.Location;
import org.sofl.soptorshi.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.crudui.crud.CrudListener;
import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.impl.form.factory.DefaultCrudFormFactory;
import org.vaadin.crudui.layout.impl.HorizontalSplitCrudLayout;

import java.util.Collection;
import java.util.List;

@Route(value = "location", layout = MainView.class)
public class LocationView extends VerticalLayout implements CrudListener<Location> {

    private LocationRepository locationRepository;
    private Tabs tabSheet = new Tabs();
    private VerticalLayout container = new VerticalLayout();
    private TextField nameFilter = new TextField();


    public LocationView(@Autowired LocationRepository locationRepository){
        this.locationRepository = locationRepository;
        tabSheet.setWidth("100%");
        container.setSizeFull();
        container.setMargin(false);
        container.setPadding(false);
        add(tabSheet, container);
        setSizeFull();
        setPadding(false);
        setSpacing(false);
        addCrud(getCrud(), "Location");
    }


    private void addCrud(Component crud, String caption){
        VerticalLayout layout = new VerticalLayout(crud);
        layout.setSizeFull();
        layout.setMargin(true);
        Tab tab = new Tab(caption);
        tabSheet.add(tab);
        container.add(crud);
        crud.setVisible(true);
        tabSheet.addSelectedChangeListener(e->crud.setVisible(tabSheet.getSelectedTab()==tab));
    }

    private Component getCrud(){
        GridCrud<Location> crud = new GridCrud<>(Location.class, new HorizontalSplitCrudLayout());
        crud.setCrudListener(this);
        DefaultCrudFormFactory<Location> formFactory = new DefaultCrudFormFactory<>(Location.class);
        crud.setCrudFormFactory(formFactory);
        formFactory.setUseBeanValidation(true);
        formFactory.setErrorListener(e->{
            Notification.show("Error in data processing");
            e.printStackTrace();
        });
        formFactory.setVisibleProperties("name","description");
        formFactory.setVisibleProperties(CrudOperation.ADD, "name",  "description");
        formFactory.setVisibleProperties(CrudOperation.UPDATE, "name",  "description");
        formFactory.setVisibleProperties(CrudOperation.READ, "name","description");
        formFactory.setVisibleProperties(CrudOperation.DELETE, "name", "description");

        crud.getGrid().setColumns("name", "description");
        crud.getGrid().setColumnReorderingAllowed(true);

        formFactory.setButtonCaption(CrudOperation.ADD, "Add new location");
        crud.setRowCountCaption("%d department(s) found");

        crud.setClickRowToUpdate(true);
        crud.setUpdateOperationVisible(true);

        nameFilter.setPlaceholder("Filter by locationName name ...");
        nameFilter.addValueChangeListener(e->crud.refreshGrid());
        crud.getCrudLayout().addFilterComponent(nameFilter);

        Button clearFilters = new Button(null, VaadinIcon.ERASER.create());
        clearFilters.addAttachListener(event-> nameFilter.clear());
        crud.getCrudLayout().addFilterComponent(clearFilters);
//        crud.setFindAllOperation(
//                DataProvider.fromCallbacks(
//                 query-> nameFilter.getValue().length()==0? departmentRepository.findAll().stream(): departmentRepository.findByNameLike(nameFilter.getValue()).stream(),
//                 query -> nameFilter.getValue().length()==0? departmentRepository.findAll().size(): departmentRepository.findByNameLike(nameFilter.getValue()).size()
//                )
//        );

        crud.setFindAllOperation(()->{
            return locationRepository.findAll();
        });

        return crud;
    }

    @Override
    public Collection<Location> findAll() {
        return locationRepository.findAll();
    }

    @Override
    public Location add(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public Location update(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public void delete(Location location) {
        locationRepository.delete(location);
    }
}
