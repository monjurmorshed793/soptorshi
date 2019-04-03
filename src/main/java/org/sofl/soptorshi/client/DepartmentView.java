package org.sofl.soptorshi.client;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.Route;
import org.sofl.soptorshi.MainView;
import org.sofl.soptorshi.model.Department;
import org.sofl.soptorshi.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.vaadin.crudui.crud.CrudListener;
import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.impl.field.provider.ComboBoxProvider;
import org.vaadin.crudui.form.impl.form.factory.DefaultCrudFormFactory;
import org.vaadin.crudui.layout.impl.HorizontalSplitCrudLayout;

import java.util.Collection;
import java.util.List;

@Route(value = "department", layout = MainView.class)
public class DepartmentView extends VerticalLayout implements CrudListener<Department> {
    private DepartmentRepository departmentRepository;
    private Tabs tabSheet = new Tabs();
    private VerticalLayout container = new VerticalLayout();
    private TextField nameFilter = new TextField();

    public DepartmentView(@Autowired DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
        GridCrud<Department> departmentGridCrud = new GridCrud<>(Department.class);

        tabSheet.setWidth("100%");
        container.setSizeFull();
        container.setMargin(false);
        container.setPadding(false);
        add(tabSheet, container);
        setSizeFull();
        setPadding(false);
        setSpacing(false);
        addCrud(getCrud(), "Department");
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
        GridCrud<Department> crud = new GridCrud<>(Department.class, new HorizontalSplitCrudLayout());
        crud.setCrudListener(this);
        List<Department> departmentList = departmentRepository.findAll();
        crud.setFindAllOperation(()->departmentList);
        DefaultCrudFormFactory<Department> formFactory = new DefaultCrudFormFactory<>(Department.class);
        crud.setCrudFormFactory(formFactory);
        formFactory.setUseBeanValidation(true);
        formFactory.setErrorListener(e->{
            Notification.show("Error in data processing");
            e.printStackTrace();
        });
        formFactory.setVisibleProperties("name", "shortName", "description");
        formFactory.setVisibleProperties(CrudOperation.ADD, "name", "shortName", "description");
        formFactory.setVisibleProperties(CrudOperation.UPDATE, "name", "shortName", "description");
        formFactory.setVisibleProperties(CrudOperation.READ, "name", "shortName", "description");
        formFactory.setVisibleProperties(CrudOperation.DELETE, "name", "shortName", "description");

        crud.getGrid().setColumns("name", "shortName", "description");
        crud.getGrid().setColumnReorderingAllowed(true);

        formFactory.setButtonCaption(CrudOperation.ADD, "Add new department");
        crud.setRowCountCaption("%d department(s) found");

        crud.setClickRowToUpdate(true);
        crud.setUpdateOperationVisible(true);

        nameFilter.setPlaceholder("Filter by department name ...");
        nameFilter.addValueChangeListener(e->crud.refreshGrid());
        crud.getCrudLayout().addFilterComponent(nameFilter);

        Button clearFilters = new Button(null, VaadinIcon.ERASER.create());
        clearFilters.addAttachListener(event-> nameFilter.clear());

        crud.setFindAllOperation(() -> {
            return departmentRepository.findByNameLike(nameFilter.getValue());
        });

        return crud;
    }

    @Override
    public Collection<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department add(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department update(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public void delete(Department department) {
        departmentRepository.delete(department);
    }
}
