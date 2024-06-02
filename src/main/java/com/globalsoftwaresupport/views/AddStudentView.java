package com.globalsoftwaresupport.views;


import com.globalsoftwaresupport.model.Status;
import com.globalsoftwaresupport.model.Student;
import com.globalsoftwaresupport.services.StatusService;
import com.globalsoftwaresupport.services.StudentService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Add Student")
@Route(value = "add-student")
public class AddStudentView extends VerticalLayout {

	private final StatusService statusService;
	private final StudentService studentService;

	private TextField age;
	private TextField zipCode;
	private TextField name;
	private TextField country;
	private ComboBox<Status> status;
	private LogoLayout image;
	private Button save;
	private Button close;

	private Student student = new Student();
	private Binder<Student> binder;

	public AddStudentView(StatusService statusService, StudentService studentService) {
		this.statusService = statusService;
		this.studentService = studentService;
		
		createFieldVariables();
		createBinder();
		createStatus();
		setAlignItems(Alignment.CENTER);

		add(image);
		add(createFormLayout());
	}

	private void createFieldVariables() {
		this.age = new TextField("AGE");
		this.name = new TextField("NAME");
		this.zipCode = new TextField("ZIP_CODE");
		this.country = new TextField("COUNTRY");
		this.status = new ComboBox<Status>("STATUS");
		this.image = new LogoLayout();
		this.save = new Button("SAVE");
		this.close = new Button("CANCEL");
	}

	private void createBinder() {
		this.binder = new BeanValidationBinder<>(Student.class);
		this.binder.bindInstanceFields(this);
	}

	private void createStatus() {
		status.setItems(statusService.findAll());
		status.setValue(statusService.findAll().get(0));
		status.setItemLabelGenerator(Status::getName);
	}

	private Component createFormLayout() {
		FormLayout formLayout = new FormLayout();
		formLayout.add(name, age, zipCode, country, status, createButtonsLayout());
		formLayout.setColspan(image, 2);
		formLayout.setColspan(name, 2);
		return formLayout;
	}

	private HorizontalLayout createButtonsLayout() {
		save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

		save.addClickShortcut(Key.ENTER);
		close.addClickShortcut(Key.ESCAPE);

		save.addClickListener(event -> validateAndSave());
		close.addClickListener(event -> closeAddStudentView());
		
		return new HorizontalLayout(save, close);
	}

	private void closeAddStudentView() {
		getUI().ifPresent(ui -> ui.navigate(""));
	}

	private void validateAndSave() {
		try {
			binder.isValid();
			binder.writeBean(student);
			studentService.save(student);
			Notification notification = Notification.show("STUDENT_SAVED");
			notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
			clearFields();
		} catch (ValidationException e) {
			e.printStackTrace();
		}
	}

	private void clearFields() {
		binder.getFields().forEach(HasValue::clear);
		status.setValue(statusService.findAll().get(0));
		student = new Student();
	}
} 