package com.globalsoftwaresupport.views;

import java.util.ArrayList;
import java.util.List;

import com.globalsoftwaresupport.model.Status;
import com.globalsoftwaresupport.model.Student;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle(value = "Home")
@Route(value = "")

public class MainView extends VerticalLayout{
	
	private LogoLayout logoLayout;
	private Grid<Student> grid;

	public MainView() {
		
		setSizeFull();
		setAlignItems(Alignment.CENTER);
		
		
		createFieldVariables();
		configureGrid();
		
		
		
		
		add(logoLayout, grid);
		
		loadStudents();
	}

	private void configureGrid() {
		grid.setSizeFull();
		grid.setColumns("country" , "zipCode");
		grid.addColumn(s -> s.getName()).setHeader("Name");
		grid.addColumn(s -> s.getAge()).setHeader("Age");
		/*grid.addComponentColumn(s -> {
			Icon icon;
			
			if(s.getStatus().getName().equals("ACTIVE")) {
				icon = VaadinIcon.CIRCLE.create();
						icon.setColor("green");
				} else if(s.getStatus().getName().equals("PASSIVE")) {
					icon = VaadinIcon.CLOSE_CIRCLE.create();
							icon.setColor("red");
				} else {
					icon = VaadinIcon.CHECK_CIRCLE.create();
							icon.setColor("orange");
				}
			return icon;
		}).setHeader("Status");
		*/
		grid.getColumns().forEach(col -> col.setAutoWidth(true));
		
	}

	private void loadStudents() {
		List<Student> students = new ArrayList<>();
		//students.add(new Student("Adam",23,1627, "UK",new Status("ACTIVE")));
		//students.add(new Student("Kevin",23,1627, "UK",new Status("PASSIVE")));
		//students.add(new Student("Emily",23,1627, "UK",new Status("ABSOLVED")));
		
		grid.setItems(students);
		
		
	}

	private void createFieldVariables() {
		logoLayout = new LogoLayout();
		grid = new Grid<>(Student.class);
}
}