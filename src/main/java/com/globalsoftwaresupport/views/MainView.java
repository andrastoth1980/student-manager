package com.globalsoftwaresupport.views;

import java.util.ArrayList;
import java.util.List;

import com.globalsoftwaresupport.model.Status;
import com.globalsoftwaresupport.model.Student;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle(value = "Home")
@Route(value = "")

public class MainView extends VerticalLayout{
	
	private Grid<Student> grid;

	public MainView() {
		
		setSizeFull();
		setAlignItems(Alignment.CENTER);
		
		List<Student> students = new ArrayList<>();
		students.add(new Student("Adam",23,1627, "UK",new Status("ACTIVE")));
		grid = new Grid<>(Student.class);
		grid.setItems(students);
		
		add(grid);
	}
}
