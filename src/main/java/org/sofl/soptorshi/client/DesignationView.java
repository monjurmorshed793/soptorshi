package org.sofl.soptorshi.client;

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
import org.sofl.soptorshi.model.Designation;
import org.sofl.soptorshi.repository.DesignationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.crudui.crud.CrudListener;
import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.impl.form.factory.DefaultCrudFormFactory;
import org.vaadin.crudui.layout.impl.HorizontalSplitCrudLayout;

import java.util.Collection;
import java.util.List;

@Route(value = "designation", layout = MainView.class)
public class DesignationView extends VerticalLayout implements CrudListener<Designation> {

    private DesignationRepository designationRepository;
    private Tabs tabSheet = new Tabs();
    private VerticalLayout container = new VerticalLayout();
    private TextField nameFilter;


    public DesignationView(@Autowired DesignationRepository designationRepository) {
        this.designationRepository = designationRepository;

        tabSheet.setWidth("100%");
        container.setSizeFull();
        container.setMargin(false);
        container.setPadding(false);
        add(tabSheet, container);
        setSizeFull();
        setPadding(false);
        setSpacing(false);
        addCrud(getCrud(), "Designation");
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
        GridCrud<Designation> crud = new GridCrud<>(Designation.class, new HorizontalSplitCrudLayout());
        crud.setCrudListener(this);
        DefaultCrudFormFactory<Designation> formFactory = new DefaultCrudFormFactory<>(Designation.class);
        crud.setCrudFormFactory(formFactory);
        formFactory.setUseBeanValidation(true);
        formFactory.setErrorListener(e->{
            Notification.show("Error in data processing");
            e.printStackTrace();
        });
        formFactory.setVisibleProperties("name", "description");
        formFactory.setVisibleProperties(CrudOperation.ADD, "name", "description");
        formFactory.setVisibleProperties(CrudOperation.UPDATE, "name",  "description");
        formFactory.setVisibleProperties(CrudOperation.READ, "name",  "description");
        formFactory.setVisibleProperties(CrudOperation.DELETE, "name", "description");

        crud.getGrid().setColumns("name", "description");
        crud.getGrid().setColumnReorderingAllowed(true);

        formFactory.setButtonCaption(CrudOperation.ADD, "Add new designation");
        crud.setRowCountCaption("%d designations(s) found");

        crud.setClickRowToUpdate(true);
        crud.setUpdateOperationVisible(true);
        nameFilter = new TextField();
        nameFilter.setPlaceholder("Filter by designation ...");
        nameFilter.addValueChangeListener(e->{
            nameFilterChanged();
            crud.setFindAllOperation(()->designationRepository.findByNameLike(e.toString()));
        });
        crud.getCrudLayout().addFilterComponent(nameFilter);

        Button clearFilters = new Button(null, VaadinIcon.ERASER.create());
        clearFilters.addAttachListener(event->{
            nameFilter.clear();
            nameFilter.setValue("");
            crud.setFindAllOperation(()->designationRepository.findAll());
        });
        crud.getCrudLayout().addFilterComponent(clearFilters);

        crud.setFindAllOperation(()->{
            return designationRepository.findAll();
        });

        return crud;
    }

    private void nameFilterChanged(){
        System.out.println("Name filter was changed");
    }

    @Override
    public Collection<Designation> findAll() {
        return designationRepository.findAll();
    }

    @Override
    public Designation add(Designation designation) {
        return designationRepository.save(designation);
    }

    @Override
    public Designation update(Designation designation) {
        return designationRepository.save(designation);
    }

    @Override
    public void delete(Designation designation) {
        designationRepository.delete(designation);
    }
}
