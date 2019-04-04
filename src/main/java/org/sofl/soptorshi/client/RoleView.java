package org.sofl.soptorshi.client;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import org.sofl.soptorshi.MainView;
import org.sofl.soptorshi.model.Role;
import org.sofl.soptorshi.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.WeakHashMap;

@Route(value = "role", layout = MainView.class)
public class RoleView extends VerticalLayout  {

    private RoleRepository roleRepository;

    public RoleView(@Autowired RoleRepository roleRepository){
        this.roleRepository = roleRepository;

        setSizeFull();

        Grid<Role> grid = new Grid<>();
        List<Role> roleList = roleRepository.findAll();

        Grid.Column<Role> nameColumn = grid.addColumn(Role::getName).setHeader("Name");
        Grid.Column<Role> descriptionColumn = grid.addColumn(Role::getDescription).setHeader("Description");

        Binder<Role> binder = new Binder<>(Role.class);

        TextField field = new TextField();
        binder.bind(field,"description");
        descriptionColumn.setEditorComponent(field);

        Editor<Role> roleEditor = grid.getEditor();
        roleEditor.setBinder(binder);
        roleEditor.setBuffered(true);


        Collection<Button> editButtons = Collections.newSetFromMap(new WeakHashMap<>());


        Grid.Column<Role> editorColumn = grid.addComponentColumn(role->{
           Button edit = new Button("Edit");
           edit.addClassName("edit");
           edit.addClickListener(e->{
               roleEditor.editItem(role);
               field.focus();
           });
           edit.setEnabled(!roleEditor.isOpen());
           editButtons.add(edit);
           return edit;
        });

        roleEditor.addOpenListener(e->editButtons.stream().forEach(button -> button.setEnabled(!roleEditor.isOpen())));
        roleEditor.addCloseListener(e->editButtons.stream().forEach(button->button.setEnabled(!roleEditor.isOpen())));

        Button save = new Button("Save", e->{
            roleEditor.save();
        });
        save.addClassName("save");

        Button cancel = new Button("Cancel", e-> roleEditor.cancel());
        cancel.addClassName("cancel");

        grid.getElement().addEventListener("keyup", event -> roleEditor.cancel())
                .setFilter("event.key === 'Escape' || event.key === 'Esc'");

        grid.setItems(roleList);

        Div buttons = new Div(save, cancel);
        editorColumn.setEditorComponent(buttons);

        roleEditor.addSaveListener(e->{
            System.out.println(e.getGrid().getEditor().getItem().getDescription());
            roleRepository.save(e.getItem());
        });

        add(grid);
    }
}
