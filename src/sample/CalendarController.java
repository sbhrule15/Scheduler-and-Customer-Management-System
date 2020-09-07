package sample;

import com.sun.codemodel.internal.JForEach;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.*;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.Date;

public class CalendarController implements Initializable {

    //Current User
    private User currentUser;

    // Passing of UserObject
    void initUser(User user) {
        currentUser = user;
    }

    // Getting current date and time
    LocalDate currentDate = LocalDate.now();
    LocalTime currentTime = LocalTime.now();
    Instant instant = Instant.now();

    // LINKED NODES
    @FXML
    private Label errorMessage;
    @FXML
    private Button addAppointment;
    @FXML
    private Button editAppointment;
    @FXML
    private Button deleteAppointment;
    @FXML
    private RadioButton weekView;
    @FXML
    private RadioButton monthView;
    @FXML
    private DatePicker datePicker;

    // Weekly View Tables and Columns

    @FXML
    private TableView sundayTable;
    @FXML
    private TableView mondayTable;
    @FXML
    private TableView tuesdayTable;
    @FXML
    private TableView wednesdayTable;
    @FXML
    private TableView thursdayTable;
    @FXML
    private TableView fridayTable;
    @FXML
    private TableView saturdayTable;
    @FXML
    private TableColumn <Appointment, String> sundayColumn;
    @FXML
    private TableColumn <Appointment, String> mondayColumn;
    @FXML
    private TableColumn <Appointment, String> tuesdayColumn;
    @FXML
    private TableColumn <Appointment, String> wednesdayColumn;
    @FXML
    private TableColumn <Appointment, String> thursdayColumn;
    @FXML
    private TableColumn <Appointment, String> fridayColumn;
    @FXML
    private TableColumn <Appointment, String> saturdayColumn;

    // Monthly View Tables and Columns
    @FXML
    private TableView day1;
    @FXML
    private TableView day2;
    @FXML
    private TableView day3;
    @FXML
    private TableView day4;
    @FXML
    private TableView day5;
    @FXML
    private TableView day6;
    @FXML
    private TableView day7;
    @FXML
    private TableView day8;
    @FXML
    private TableView day9;
    @FXML
    private TableView day10;
    @FXML
    private TableView day11;
    @FXML
    private TableView day12;
    @FXML
    private TableView day13;
    @FXML
    private TableView day14;
    @FXML
    private TableView day15;
    @FXML
    private TableView day16;
    @FXML
    private TableView day17;
    @FXML
    private TableView day18;
    @FXML
    private TableView day19;
    @FXML
    private TableView day20;
    @FXML
    private TableView day21;
    @FXML
    private TableView day22;
    @FXML
    private TableView day23;
    @FXML
    private TableView day24;
    @FXML
    private TableView day25;
    @FXML
    private TableView day26;
    @FXML
    private TableView day27;
    @FXML
    private TableView day28;
    @FXML
    private TableView day29;
    @FXML
    private TableView day30;
    @FXML
    private TableView day31;
    @FXML
    private TableView day32;
    @FXML
    private TableView day33;
    @FXML
    private TableView day34;
    @FXML
    private TableView day35;
    @FXML
    private TableView day36;
    @FXML
    private TableView day37;
    @FXML
    private TableView day38;
    @FXML
    private TableView day39;
    @FXML
    private TableView day40;
    @FXML
    private TableView day41;
    @FXML
    private TableView day42;

//    @FXML
//    private TableView[] dayTables = new TableView[]{day1, day2, day3, day4, day5, day6, day7, day8, day9, day10, day11, day12, day13, day14, day15, day16, day17, day18, day19, day20, day21, day22, day23, day24, day25, day26, day27, day28, day29, day30, day31, day32, day33, day34, day35, day36, day37, day38, day39, day40, day41, day42};

    @FXML
    private TableColumn <Appointment, String> day1col;
    @FXML
    private TableColumn <Appointment, String> day2col;
    @FXML
    private TableColumn <Appointment, String> day3col;
    @FXML
    private TableColumn <Appointment, String> day4col;
    @FXML
    private TableColumn <Appointment, String> day5col;
    @FXML
    private TableColumn <Appointment, String> day6col;
    @FXML
    private TableColumn <Appointment, String> day7col;
    @FXML
    private TableColumn <Appointment, String> day8col;
    @FXML
    private TableColumn <Appointment, String> day9col;
    @FXML
    private TableColumn <Appointment, String> day10col;
    @FXML
    private TableColumn <Appointment, String> day11col;
    @FXML
    private TableColumn <Appointment, String> day12col;
    @FXML
    private TableColumn <Appointment, String> day13col;
    @FXML
    private TableColumn <Appointment, String> day14col;
    @FXML
    private TableColumn <Appointment, String> day15col;
    @FXML
    private TableColumn <Appointment, String> day16col;
    @FXML
    private TableColumn <Appointment, String> day17col;
    @FXML
    private TableColumn <Appointment, String> day18col;
    @FXML
    private TableColumn <Appointment, String> day19col;
    @FXML
    private TableColumn <Appointment, String> day20col;
    @FXML
    private TableColumn <Appointment, String> day21col;
    @FXML
    private TableColumn <Appointment, String> day22col;
    @FXML
    private TableColumn <Appointment, String> day23col;
    @FXML
    private TableColumn <Appointment, String> day24col;
    @FXML
    private TableColumn <Appointment, String> day25col;
    @FXML
    private TableColumn <Appointment, String> day26col;
    @FXML
    private TableColumn <Appointment, String> day27col;
    @FXML
    private TableColumn <Appointment, String> day28col;
    @FXML
    private TableColumn <Appointment, String> day29col;
    @FXML
    private TableColumn <Appointment, String> day30col;
    @FXML
    private TableColumn <Appointment, String> day31col;
    @FXML
    private TableColumn <Appointment, String> day32col;
    @FXML
    private TableColumn <Appointment, String> day33col;
    @FXML
    private TableColumn <Appointment, String> day34col;
    @FXML
    private TableColumn <Appointment, String> day35col;
    @FXML
    private TableColumn <Appointment, String> day36col;
    @FXML
    private TableColumn <Appointment, String> day37col;
    @FXML
    private TableColumn <Appointment, String> day38col;
    @FXML
    private TableColumn <Appointment, String> day39col;
    @FXML
    private TableColumn <Appointment, String> day40col;
    @FXML
    private TableColumn <Appointment, String> day41col;
    @FXML
    private TableColumn <Appointment, String> day42col;

//    private TableColumn [] dayColumns = {day1col, day2col, day3col, day4col, day5col, day6col, day7col, day8col, day9col, day10col, day11col, day12col, day13col, day14col, day15col, day16col, day17col, day18col, day19col, day20col, day21col, day22col, day23col, day24col, day25col, day26col, day27col, day28col, day29col, day30col, day31col, day32col, day33col, day34col, day35col, day36col, day37col, day38col, day39col, day40col, day41col, day42col};


    @FXML
    private Button customerView;
    @FXML
    private Button exitChange;

    // DAY OF WEEK LISTS
    private ObservableList<Appointment> sundayList = FXCollections.observableArrayList();
    private ObservableList<Appointment> mondayList = FXCollections.observableArrayList();
    private ObservableList<Appointment> tuesdayList = FXCollections.observableArrayList();
    private ObservableList<Appointment> wednesdayList = FXCollections.observableArrayList();
    private ObservableList<Appointment> thursdayList = FXCollections.observableArrayList();
    private ObservableList<Appointment> fridayList = FXCollections.observableArrayList();
    private ObservableList<Appointment> saturdayList = FXCollections.observableArrayList();

    private ObservableList<ObservableList<Appointment>> daytables = FXCollections.observableArrayList();


    // METHODS

    @FXML
    void handleWeekView(ActionEvent event){
        sundayTable.setVisible(true);
        mondayTable.setVisible(true);
        tuesdayTable.setVisible(true);
        wednesdayTable.setVisible(true);
        thursdayTable.setVisible(true);
        fridayTable.setVisible(true);
        saturdayTable.setVisible(true);

        day1.setVisible(false);
        day2.setVisible(false);
        day3.setVisible(false);
        day4.setVisible(false);
        day5.setVisible(false);
        day6.setVisible(false);
        day7.setVisible(false);
        day8.setVisible(false);
        day9.setVisible(false);
        day10.setVisible(false);
        day11.setVisible(false);
        day12.setVisible(false);
        day13.setVisible(false);
        day14.setVisible(false);
        day15.setVisible(false);
        day16.setVisible(false);
        day17.setVisible(false);
        day18.setVisible(false);
        day19.setVisible(false);
        day20.setVisible(false);
        day21.setVisible(false);
        day22.setVisible(false);
        day23.setVisible(false);
        day24.setVisible(false);
        day25.setVisible(false);
        day26.setVisible(false);
        day27.setVisible(false);
        day28.setVisible(false);
        day29.setVisible(false);
        day30.setVisible(false);
        day31.setVisible(false);
        day32.setVisible(false);
        day33.setVisible(false);
        day34.setVisible(false);
        day35.setVisible(false);
        day36.setVisible(false);
        day37.setVisible(false);
        day38.setVisible(false);
        day39.setVisible(false);
        day40.setVisible(false);
        day41.setVisible(false);
        day42.setVisible(false);

    }

    @FXML
    void handleMonthView(ActionEvent event){
        sundayTable.setVisible(false);
        mondayTable.setVisible(false);
        tuesdayTable.setVisible(false);
        wednesdayTable.setVisible(false);
        thursdayTable.setVisible(false);
        fridayTable.setVisible(false);
        saturdayTable.setVisible(false);

        day1.setVisible(true);
        day2.setVisible(true);
        day3.setVisible(true);
        day4.setVisible(true);
        day5.setVisible(true);
        day6.setVisible(true);
        day7.setVisible(true);
        day8.setVisible(true);
        day9.setVisible(true);
        day10.setVisible(true);
        day11.setVisible(true);
        day12.setVisible(true);
        day13.setVisible(true);
        day14.setVisible(true);
        day15.setVisible(true);
        day16.setVisible(true);
        day17.setVisible(true);
        day18.setVisible(true);
        day19.setVisible(true);
        day20.setVisible(true);
        day21.setVisible(true);
        day22.setVisible(true);
        day23.setVisible(true);
        day24.setVisible(true);
        day25.setVisible(true);
        day26.setVisible(true);
        day27.setVisible(true);
        day28.setVisible(true);
        day29.setVisible(true);
        day30.setVisible(true);
        day31.setVisible(true);
        day32.setVisible(true);
        day33.setVisible(true);
        day34.setVisible(true);
        day35.setVisible(true);
        day36.setVisible(true);
        day37.setVisible(true);
        day38.setVisible(true);
        day39.setVisible(true);
        day40.setVisible(true);
        day41.setVisible(true);
        day42.setVisible(true);
    }

    @FXML
    void handleAddAppointment(ActionEvent event) {
        System.out.println(currentUser.getUserName());
        handleSceneChange("Appointment.fxml", false, null);
    }

    @FXML
    void handleEditAppointment(ActionEvent event) {
        // grab appointment from selected appointment
        Appointment a1 = (Appointment) sundayTable.getSelectionModel().getSelectedItem();
        Appointment a2 = (Appointment) mondayTable.getSelectionModel().getSelectedItem();
        Appointment a3 = (Appointment) tuesdayTable.getSelectionModel().getSelectedItem();
        Appointment a4 = (Appointment) wednesdayTable.getSelectionModel().getSelectedItem();
        Appointment a5 = (Appointment) thursdayTable.getSelectionModel().getSelectedItem();
        Appointment a6 = (Appointment) fridayTable.getSelectionModel().getSelectedItem();
        Appointment a7 = (Appointment) saturdayTable.getSelectionModel().getSelectedItem();
        Appointment a8 = (Appointment) day1.getSelectionModel().getSelectedItem();
        Appointment a9 = (Appointment) day2.getSelectionModel().getSelectedItem();
        Appointment a10 = (Appointment) day3.getSelectionModel().getSelectedItem();
        Appointment a11 = (Appointment) day4.getSelectionModel().getSelectedItem();
        Appointment a12 = (Appointment) day5.getSelectionModel().getSelectedItem();
        Appointment a13 = (Appointment) day6.getSelectionModel().getSelectedItem();
        Appointment a14 = (Appointment) day7.getSelectionModel().getSelectedItem();
        Appointment a15 = (Appointment) day8.getSelectionModel().getSelectedItem();
        Appointment a16 = (Appointment) day9.getSelectionModel().getSelectedItem();
        Appointment a17 = (Appointment) day10.getSelectionModel().getSelectedItem();
        Appointment a18 = (Appointment) day11.getSelectionModel().getSelectedItem();
        Appointment a19 = (Appointment) day12.getSelectionModel().getSelectedItem();
        Appointment a20 = (Appointment) day14.getSelectionModel().getSelectedItem();
        Appointment a21 = (Appointment) day15.getSelectionModel().getSelectedItem();
        Appointment a22 = (Appointment) day16.getSelectionModel().getSelectedItem();
        Appointment a23 = (Appointment) day17.getSelectionModel().getSelectedItem();
        Appointment a24 = (Appointment) day18.getSelectionModel().getSelectedItem();
        Appointment a25 = (Appointment) day19.getSelectionModel().getSelectedItem();
        Appointment a26 = (Appointment) day20.getSelectionModel().getSelectedItem();
        Appointment a27 = (Appointment) day21.getSelectionModel().getSelectedItem();
        Appointment a28 = (Appointment) day22.getSelectionModel().getSelectedItem();
        Appointment a29 = (Appointment) day23.getSelectionModel().getSelectedItem();
        Appointment a30 = (Appointment) day24.getSelectionModel().getSelectedItem();
        Appointment a31 = (Appointment) day25.getSelectionModel().getSelectedItem();
        Appointment a32 = (Appointment) day26.getSelectionModel().getSelectedItem();
        Appointment a33 = (Appointment) day27.getSelectionModel().getSelectedItem();
        Appointment a34 = (Appointment) day28.getSelectionModel().getSelectedItem();
        Appointment a35 = (Appointment) day29.getSelectionModel().getSelectedItem();
        Appointment a36 = (Appointment) day30.getSelectionModel().getSelectedItem();
        Appointment a37 = (Appointment) day31.getSelectionModel().getSelectedItem();
        Appointment a38 = (Appointment) day32.getSelectionModel().getSelectedItem();
        Appointment a39 = (Appointment) day33.getSelectionModel().getSelectedItem();
        Appointment a40 = (Appointment) day34.getSelectionModel().getSelectedItem();
        Appointment a41 = (Appointment) day35.getSelectionModel().getSelectedItem();
        Appointment a42 = (Appointment) day36.getSelectionModel().getSelectedItem();
        Appointment a43 = (Appointment) day37.getSelectionModel().getSelectedItem();
        Appointment a44 = (Appointment) day38.getSelectionModel().getSelectedItem();
        Appointment a45 = (Appointment) day39.getSelectionModel().getSelectedItem();
        Appointment a46 = (Appointment) day40.getSelectionModel().getSelectedItem();
        Appointment a47 = (Appointment) day41.getSelectionModel().getSelectedItem();
        Appointment a48 = (Appointment) day42.getSelectionModel().getSelectedItem();


        if (a1 != null){
            handleSceneChange("Appointment.fxml", true, a1);
        } else if (a2 != null){
            handleSceneChange("Appointment.fxml", true, a2);
        } else if (a3 != null){
            handleSceneChange("Appointment.fxml", true, a3);
        } else if (a4 != null){
            handleSceneChange("Appointment.fxml", true, a4);
        } else if (a5 != null){
            handleSceneChange("Appointment.fxml", true, a5);
        } else if (a6 != null){
            handleSceneChange("Appointment.fxml", true, a6);
        } else if (a7 != null){
            handleSceneChange("Appointment.fxml", true, a7);
        } else if (a8 != null){
            handleSceneChange("Appointment.fxml", true, a8);
        } else if (a9 != null){
            handleSceneChange("Appointment.fxml", true, a9);
        } else if (a10 != null){
            handleSceneChange("Appointment.fxml", true, a10);
        } else if (a11 != null){
            handleSceneChange("Appointment.fxml", true, a11);
        } else if (a12 != null){
            handleSceneChange("Appointment.fxml", true, a12);
        } else if (a13 != null){
            handleSceneChange("Appointment.fxml", true, a13);
        } else if (a14 != null){
            handleSceneChange("Appointment.fxml", true, a14);
        } else if (a15 != null){
            handleSceneChange("Appointment.fxml", true, a15);
        } else if (a16 != null){
            handleSceneChange("Appointment.fxml", true, a16);
        } else if (a17 != null){
            handleSceneChange("Appointment.fxml", true, a17);
        } else if (a18 != null){
            handleSceneChange("Appointment.fxml", true, a18);
        } else if (a19 != null){
            handleSceneChange("Appointment.fxml", true, a19);
        } else if (a20 != null){
            handleSceneChange("Appointment.fxml", true, a20);
        } else if (a21 != null){
            handleSceneChange("Appointment.fxml", true, a21);
        } else if (a22 != null){
            handleSceneChange("Appointment.fxml", true, a22);
        } else if (a23 != null){
            handleSceneChange("Appointment.fxml", true, a23);
        } else if (a24 != null){
            handleSceneChange("Appointment.fxml", true, a24);
        } else if (a25 != null){
            handleSceneChange("Appointment.fxml", true, a25);
        } else if (a26 != null){
            handleSceneChange("Appointment.fxml", true, a26);
        } else if (a27 != null){
            handleSceneChange("Appointment.fxml", true, a27);
        } else if (a28 != null){
            handleSceneChange("Appointment.fxml", true, a28);
        } else if (a29 != null){
            handleSceneChange("Appointment.fxml", true, a29);
        } else if (a30 != null){
            handleSceneChange("Appointment.fxml", true, a30);
        } else if (a31 != null){
            handleSceneChange("Appointment.fxml", true, a31);
        } else if (a32 != null){
            handleSceneChange("Appointment.fxml", true, a32);
        } else if (a33 != null){
            handleSceneChange("Appointment.fxml", true, a33);
        } else if (a34 != null){
            handleSceneChange("Appointment.fxml", true, a34);
        } else if (a35 != null){
            handleSceneChange("Appointment.fxml", true, a35);
        } else if (a36 != null){
            handleSceneChange("Appointment.fxml", true, a36);
        } else if (a37 != null){
            handleSceneChange("Appointment.fxml", true, a37);
        } else if (a38 != null){
            handleSceneChange("Appointment.fxml", true, a38);
        } else if (a39 != null){
            handleSceneChange("Appointment.fxml", true, a39);
        } else if (a40 != null){
            handleSceneChange("Appointment.fxml", true, a40);
        } else if (a41 != null){
            handleSceneChange("Appointment.fxml", true, a41);
        } else if (a42 != null){
            handleSceneChange("Appointment.fxml", true, a42);
        } else if (a43 != null){
            handleSceneChange("Appointment.fxml", true, a43);
        } else if (a44 != null){
            handleSceneChange("Appointment.fxml", true, a44);
        } else if (a45 != null){
            handleSceneChange("Appointment.fxml", true, a45);
        } else if (a46 != null){
            handleSceneChange("Appointment.fxml", true, a46);
        } else if (a47 != null){
            handleSceneChange("Appointment.fxml", true, a47);
        } else if (a48 != null){
            handleSceneChange("Appointment.fxml", true, a48);
        } else {
            errorMessage.setText("No appointment selected: Please select an appointment");
        }
    }

    @FXML
    void handleDeleteAppointment(ActionEvent event) {
        // grab appointment from selected appointment
        Appointment a1 = (Appointment) sundayTable.getSelectionModel().getSelectedItem();
        Appointment a2 = (Appointment) mondayTable.getSelectionModel().getSelectedItem();
        Appointment a3 = (Appointment) tuesdayTable.getSelectionModel().getSelectedItem();
        Appointment a4 = (Appointment) wednesdayTable.getSelectionModel().getSelectedItem();
        Appointment a5 = (Appointment) thursdayTable.getSelectionModel().getSelectedItem();
        Appointment a6 = (Appointment) fridayTable.getSelectionModel().getSelectedItem();
        Appointment a7 = (Appointment) saturdayTable.getSelectionModel().getSelectedItem();
        Appointment a8 = (Appointment) day1.getSelectionModel().getSelectedItem();
        Appointment a9 = (Appointment) day2.getSelectionModel().getSelectedItem();
        Appointment a10 = (Appointment) day3.getSelectionModel().getSelectedItem();
        Appointment a11 = (Appointment) day4.getSelectionModel().getSelectedItem();
        Appointment a12 = (Appointment) day5.getSelectionModel().getSelectedItem();
        Appointment a13 = (Appointment) day6.getSelectionModel().getSelectedItem();
        Appointment a14 = (Appointment) day7.getSelectionModel().getSelectedItem();
        Appointment a15 = (Appointment) day8.getSelectionModel().getSelectedItem();
        Appointment a16 = (Appointment) day9.getSelectionModel().getSelectedItem();
        Appointment a17 = (Appointment) day10.getSelectionModel().getSelectedItem();
        Appointment a18 = (Appointment) day11.getSelectionModel().getSelectedItem();
        Appointment a19 = (Appointment) day12.getSelectionModel().getSelectedItem();
        Appointment a20 = (Appointment) day14.getSelectionModel().getSelectedItem();
        Appointment a21 = (Appointment) day15.getSelectionModel().getSelectedItem();
        Appointment a22 = (Appointment) day16.getSelectionModel().getSelectedItem();
        Appointment a23 = (Appointment) day17.getSelectionModel().getSelectedItem();
        Appointment a24 = (Appointment) day18.getSelectionModel().getSelectedItem();
        Appointment a25 = (Appointment) day19.getSelectionModel().getSelectedItem();
        Appointment a26 = (Appointment) day20.getSelectionModel().getSelectedItem();
        Appointment a27 = (Appointment) day21.getSelectionModel().getSelectedItem();
        Appointment a28 = (Appointment) day22.getSelectionModel().getSelectedItem();
        Appointment a29 = (Appointment) day23.getSelectionModel().getSelectedItem();
        Appointment a30 = (Appointment) day24.getSelectionModel().getSelectedItem();
        Appointment a31 = (Appointment) day25.getSelectionModel().getSelectedItem();
        Appointment a32 = (Appointment) day26.getSelectionModel().getSelectedItem();
        Appointment a33 = (Appointment) day27.getSelectionModel().getSelectedItem();
        Appointment a34 = (Appointment) day28.getSelectionModel().getSelectedItem();
        Appointment a35 = (Appointment) day29.getSelectionModel().getSelectedItem();
        Appointment a36 = (Appointment) day30.getSelectionModel().getSelectedItem();
        Appointment a37 = (Appointment) day31.getSelectionModel().getSelectedItem();
        Appointment a38 = (Appointment) day32.getSelectionModel().getSelectedItem();
        Appointment a39 = (Appointment) day33.getSelectionModel().getSelectedItem();
        Appointment a40 = (Appointment) day34.getSelectionModel().getSelectedItem();
        Appointment a41 = (Appointment) day35.getSelectionModel().getSelectedItem();
        Appointment a42 = (Appointment) day36.getSelectionModel().getSelectedItem();
        Appointment a43 = (Appointment) day37.getSelectionModel().getSelectedItem();
        Appointment a44 = (Appointment) day38.getSelectionModel().getSelectedItem();
        Appointment a45 = (Appointment) day39.getSelectionModel().getSelectedItem();
        Appointment a46 = (Appointment) day40.getSelectionModel().getSelectedItem();
        Appointment a47 = (Appointment) day41.getSelectionModel().getSelectedItem();
        Appointment a48 = (Appointment) day42.getSelectionModel().getSelectedItem();

        if (a1 != null){
            deleteSelected(a1);
            sundayList.remove(a1);
        } else if (a2 != null){
            deleteSelected(a2);
            mondayList.remove(a2);
        } else if (a3 != null){
            deleteSelected(a3);
            tuesdayList.remove(a3);
        } else if (a4 != null){
            deleteSelected(a4);
            wednesdayList.remove(a4);
        } else if (a5 != null){
            deleteSelected(a5);
            thursdayList.remove(a5);
        } else if (a6 != null){
            deleteSelected(a6);
            fridayList.remove(a6);
        } else if (a7 != null){
            deleteSelected(a7);
            saturdayList.remove(a7);
        } else if (a8 != null){
            deleteSelected(a8);
            daytables.get(0).remove(a8);
        } else if (a9 != null){
            deleteSelected(a9);
            daytables.get(1).remove(a9);
        } else if (a10 != null){
            deleteSelected(a10);
            daytables.get(2).remove(a10);
        } else if (a11 != null){
            deleteSelected(a11);
            daytables.get(3).remove(a11);
        } else if (a12 != null){
            deleteSelected(a12);
            daytables.get(4).remove(a12);
        } else if (a13 != null){
            deleteSelected(a13);
            daytables.get(5).remove(a13);
        } else if (a14 != null){
            deleteSelected(a14);
            daytables.get(6).remove(a14);
        } else if (a15 != null){
            deleteSelected(a15);
            daytables.get(7).remove(a15);
        } else if (a16 != null){
            deleteSelected(a16);
            daytables.get(8).remove(a16);
        } else if (a17 != null){
            deleteSelected(a17);
            daytables.get(9).remove(a17);
        } else if (a18 != null){
            deleteSelected(a18);
            daytables.get(10).remove(a18);
        } else if (a19 != null){
            deleteSelected(a19);
            daytables.get(11).remove(a19);
        } else if (a20 != null){
            deleteSelected(a20);
            daytables.get(12).remove(a20);
        } else if (a21 != null){
            deleteSelected(a21);
            daytables.get(13).remove(a21);
        } else if (a22 != null){
            deleteSelected(a22);
            daytables.get(14).remove(a22);
        } else if (a23 != null){
            deleteSelected(a23);
            daytables.get(15).remove(a23);
        } else if (a24 != null){
            deleteSelected(a24);
            daytables.get(16).remove(a24);
        } else if (a25 != null){
            deleteSelected(a25);
            daytables.get(17).remove(a25);
        } else if (a26 != null){
            deleteSelected(a26);
            daytables.get(18).remove(a26);
        } else if (a27 != null){
            deleteSelected(a27);
            daytables.get(19).remove(a27);
        } else if (a28 != null){
            deleteSelected(a28);
            daytables.get(20).remove(a28);
        } else if (a29 != null){
            deleteSelected(a29);
            daytables.get(21).remove(a29);
        } else if (a30 != null){
            deleteSelected(a30);
            daytables.get(22).remove(a30);
        } else if (a31 != null){
            deleteSelected(a31);
            daytables.get(23).remove(a31);
        } else if (a32 != null){
            deleteSelected(a32);
            daytables.get(24).remove(a32);
        } else if (a33 != null){
            deleteSelected(a33);
            daytables.get(25).remove(a33);
        } else if (a34 != null){
            deleteSelected(a34);
            daytables.get(26).remove(a34);
        } else if (a35 != null){
            deleteSelected(a35);
            daytables.get(27).remove(a35);
        } else if (a36 != null){
            deleteSelected(a36);
            daytables.get(28).remove(a36);
        } else if (a37 != null){
            deleteSelected(a37);
            daytables.get(29).remove(a37);
        } else if (a38 != null){
            deleteSelected(a38);
            daytables.get(30).remove(a38);
        } else if (a39 != null){
            deleteSelected(a39);
            daytables.get(31).remove(a39);
        } else if (a40 != null){
            deleteSelected(a40);
            daytables.get(32).remove(a40);
        } else if (a41 != null){
            deleteSelected(a41);
            daytables.get(33).remove(a41);
        } else if (a42 != null){
            deleteSelected(a42);
            daytables.get(34).remove(a42);
        } else if (a43 != null){
            deleteSelected(a43);
            daytables.get(35).remove(a43);
        } else if (a44 != null){
            deleteSelected(a44);
            daytables.get(36).remove(a44);
        } else if (a45 != null){
            deleteSelected(a45);
            daytables.get(37).remove(a45);
        } else if (a46 != null){
            deleteSelected(a46);
            daytables.get(38).remove(a46);
        } else if (a47 != null){
            deleteSelected(a47);
            daytables.get(39).remove(a47);
        } else if (a48 != null){
            deleteSelected(a48);
            daytables.get(40).remove(a48);
        } else {
            errorMessage.setText("No appointment selected: Please select an appointment");
        }
    }

    @FXML
    void getWeekDates(ActionEvent event) {

        // Clear out array lists
        sundayList.clear();
        mondayList.clear();
        tuesdayList.clear();
        wednesdayList.clear();
        thursdayList.clear();
        fridayList.clear();
        saturdayList.clear();

        for (int i = 0; i < daytables.size(); i++) {
            daytables.get(i).clear();
        }

        // Set array for dates
        ArrayList<Date> datesInWeek = new ArrayList<>();

        // Get selected Date from Date Picker
        LocalDate dateSelect = datePicker.getValue();
        Date date = Date.from(dateSelect.atStartOfDay(ZoneId.systemDefault()).toInstant());

        // Set Calendar object to picked date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // Load month appointments
        loadMonthAppointments(date);

        // Loop through week and store values into Array
        for(int i = Calendar.SUNDAY; i <= Calendar.SATURDAY; i++) {
            calendar.set(Calendar.DAY_OF_WEEK, i);
            datesInWeek.add(calendar.getTime());
        }

        // Query each day of the week and load into correct column
        for(int i = 0; i < datesInWeek.size(); i++) {
            try {
                // Get connection
                Connection connection = DriverManager.getConnection("jdbc:mysql://wgudb.ucertify.com:3306/U07Vgt", "U07Vgt", "53689140721");

                // Prepare statement using loop value
                PreparedStatement dayQuery = connection.prepareStatement("SELECT * FROM appointment WHERE DATE(start)=?");
                dayQuery.setObject(1, datesInWeek.get(i));

                // execute Statement
                ResultSet rs = dayQuery.executeQuery();

                // construct Appointment object with result set
                while (rs.next()){
                    Integer appointmentid =  rs.getInt("appointmentId");
                    Integer customerid = rs.getInt("customerId");
                    Integer userid = rs.getInt("userId");
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    String location = rs.getString("location");
                    String contact = rs.getString("contact");
                    String type = rs.getString("type");

                    // Date Conversions
                    LocalDateTime returnedDate = rs.getTimestamp("start").toLocalDateTime();
                    ZonedDateTime zdt = returnedDate.atZone(ZoneOffset.UTC);
                    ZonedDateTime fdt = zdt.withZoneSameInstant(ZoneId.systemDefault());

                    System.out.println(fdt);


                    LocalDateTime returnedDateEnd = rs.getTimestamp("end").toLocalDateTime();
                    ZonedDateTime zdt2 = returnedDateEnd.atZone(ZoneOffset.UTC);
                    ZonedDateTime fdt2 = zdt2.withZoneSameInstant(ZoneId.systemDefault());

                    // Calculate duration between start and end
                    Duration d = Duration.between(fdt, fdt2);

                    // Cast to Integer value
                    Integer dminutes = Math.toIntExact(d.toMinutes());

                    // Construct Appointment Object
                    Appointment a = new Appointment(appointmentid, customerid, userid, title, description, location, contact, type, fdt, dminutes);

                    switch (i){
                        case 0: sundayList.add(a);
                                break;
                        case 1: mondayList.add(a);
                                break;
                        case 2: tuesdayList.add(a);
                                break;
                        case 3: wednesdayList.add(a);
                                break;
                        case 4: thursdayList.add(a);
                                break;
                        case 5: fridayList.add(a);
                                break;
                        case 6: saturdayList.add(a);
                                break;
                    }
                }
                // close out connections
                dayQuery.close();
                connection.close();

            } catch (SQLException e){
                System.out.println("There was an error connecting to the database: " + e.getMessage());
            }
        }

        // set items to tables
        sundayTable.setItems(sundayList);
        mondayTable.setItems(mondayList);
        tuesdayTable.setItems(tuesdayList);
        wednesdayTable.setItems(wednesdayList);
        thursdayTable.setItems(thursdayList);
        fridayTable.setItems(fridayList);
        saturdayTable.setItems(saturdayList);

        // set cell value factories with lambda functions
        sundayColumn.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        mondayColumn.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        tuesdayColumn.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        wednesdayColumn.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        thursdayColumn.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        fridayColumn.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        saturdayColumn.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));

    }


    @FXML
    void handleCustomerSceneChange(ActionEvent event) {
        handleSceneChange("CustomerView.fxml", false, null);
    }

    @FXML
    void handleTypeByMonth(ActionEvent event){
        handleSceneChange("Report.fxml", false, null);
    }


    @FXML
    void handleExit(ActionEvent event) {
        Stage stage = (Stage) exitChange.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleSceneChange(String destination, Boolean edit, Appointment appointment) {
        Parent main = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(destination));
            main = loader.load();
            Scene scene = new Scene(main);

            Stage stage = Main.getStage();
            stage.setScene(scene);

            // Pass User Object to appropriate Controller
            if (destination == "CustomerView.fxml"){
                cusViewControllerLoad(loader);
            } else if (destination == "Customer.fxml"){
                cusControllerLoad(loader);
            } else if (destination == "Appointment.fxml" && edit){
                appEditControllerLoad(loader, appointment);
            } else if (destination == "Appointment.fxml" && !(edit)){
                appControllerLoad(loader);
            } else if (destination == "Calendar.fxml") {
                calControllerLoad(loader);
            } else if (destination == "Report.fxml") {
                repControllerLoad(loader);
            }

            // Show scene
            stage.show();

        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for (int i = 0; i < 42; i++){
            daytables.add(FXCollections.observableArrayList());
        }

        // Check for appointments in 15 minutes
        appointmentCheck();
    }

    // Controller User Passthrough Methods

    private void cusViewControllerLoad(FXMLLoader loader){
        CustomerViewController controller = loader.getController();
        controller.initUser(currentUser);
    }

    private void cusControllerLoad(FXMLLoader loader){
        CustomerController controller = loader.getController();
        controller.initUser(currentUser);
    }

    private void appControllerLoad(FXMLLoader loader){
        AppointmentController controller = loader.getController();
        controller.initUser(currentUser);
    }

    private void appEditControllerLoad(FXMLLoader loader, Appointment a){
        AppointmentController controller = loader.getController();
        controller.initUser(currentUser);
        controller.loadAppointment(a);
    }

    private void calControllerLoad(FXMLLoader loader){
        CalendarController controller = loader.getController();
        controller.initUser(currentUser);
    }

    private void repControllerLoad(FXMLLoader loader){
        ReportController controller = loader.getController();
        controller.monthTypeReport();
    }

    private void deleteSelected(Appointment a){
        Integer aid = a.getAppointmentId();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://wgudb.ucertify.com:3306/U07Vgt", "U07Vgt", "53689140721");
            PreparedStatement ps = connection.prepareStatement("DELETE FROM appointment WHERE appointmentId = ?");
            ps.setInt(1, aid);

            ps.execute();

            ps.close();
            connection.close();

        } catch (SQLException e){
            System.out.println("There was an error connecting to the database: " + e.getMessage());
        }
    }

    private void loadMonthAppointments(Date d){
        // Set array for dates
        ArrayList<Date> datesInMonth = new ArrayList<>();

        // Create first, and last calendar instances
        Calendar first = Calendar.getInstance();
        first.setTime(d);

        Calendar last = Calendar.getInstance();
        last.setTime(d);

        // Define minimum week number
        Integer lastWeek = last.getActualMaximum(Calendar.WEEK_OF_MONTH);

        // Define maximum week number

        for (int i = 1; i <= lastWeek; i++ ) {
            // set week
            first.set(Calendar.WEEK_OF_MONTH, i);

            // Loop through week and store values into Array
            for(int c = Calendar.SUNDAY; c <= Calendar.SATURDAY; c++) {
                first.set(Calendar.DAY_OF_WEEK, c);
                datesInMonth.add(first.getTime());
            }
        }

        // check
        for (int i = 0; i < datesInMonth.size(); i++) {
            System.out.print(datesInMonth.get(i) + "\n");
        }

        first.setTime(d);

        // grab all appointments from month in sql

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://wgudb.ucertify.com:3306/U07Vgt", "U07Vgt", "53689140721");
            PreparedStatement monthQuery = connection.prepareStatement("SELECT * FROM appointment WHERE MONTH(start) = ?");
            monthQuery.setInt(1, first.get(Calendar.MONTH) + 1);

            ResultSet rs = monthQuery.executeQuery();

            while (rs.next()){
                Integer appointmentid =  rs.getInt("appointmentId");
                Integer customerid = rs.getInt("customerId");
                Integer userid = rs.getInt("userId");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String location = rs.getString("location");
                String contact = rs.getString("contact");
                String type = rs.getString("type");

                // Date Conversions
                LocalDateTime returnedDate = rs.getTimestamp("start").toLocalDateTime();
                ZonedDateTime zdt = returnedDate.atZone(ZoneOffset.UTC);
                ZonedDateTime fdt = zdt.withZoneSameInstant(ZoneId.systemDefault());

                System.out.println(fdt);


                LocalDateTime returnedDateEnd = rs.getTimestamp("end").toLocalDateTime();
                ZonedDateTime zdt2 = returnedDateEnd.atZone(ZoneOffset.UTC);
                ZonedDateTime fdt2 = zdt2.withZoneSameInstant(ZoneId.systemDefault());

                // Calculate duration between start and end
                Duration dur = Duration.between(fdt, fdt2);

                // Cast to Integer value
                Integer dminutes = Math.toIntExact(dur.toMinutes());

                // Construct Appointment Object
                Appointment a = new Appointment(appointmentid, customerid, userid, title, description, location, contact, type, fdt, dminutes);

                Integer monthValue = a.getDate().getMonthValue();

                for (int i = 0; i < datesInMonth.size(); i++){
                    if (datesInMonth.get(i).toInstant().atZone(ZoneId.systemDefault()).getMonthValue() == monthValue) {
                        LocalDate app = a.getDate();
                        LocalDate arr = datesInMonth.get(i).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                        if (app.equals(arr)){
                            daytables.get(i).add(a);
                        }
                    }
                }
            }
            monthQuery.close();
            connection.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        // set items to tables
        day1.setItems(daytables.get(0));
        day2.setItems(daytables.get(1));
        day3.setItems(daytables.get(2));
        day4.setItems(daytables.get(3));
        day5.setItems(daytables.get(4));
        day6.setItems(daytables.get(5));
        day7.setItems(daytables.get(6));
        day8.setItems(daytables.get(7));
        day9.setItems(daytables.get(8));
        day10.setItems(daytables.get(9));
        day11.setItems(daytables.get(10));
        day12.setItems(daytables.get(11));
        day13.setItems(daytables.get(12));
        day14.setItems(daytables.get(13));
        day15.setItems(daytables.get(14));
        day16.setItems(daytables.get(15));
        day17.setItems(daytables.get(16));
        day18.setItems(daytables.get(17));
        day19.setItems(daytables.get(18));
        day20.setItems(daytables.get(19));
        day21.setItems(daytables.get(20));
        day22.setItems(daytables.get(21));
        day23.setItems(daytables.get(22));
        day24.setItems(daytables.get(23));
        day25.setItems(daytables.get(24));
        day26.setItems(daytables.get(25));
        day27.setItems(daytables.get(26));
        day28.setItems(daytables.get(27));
        day29.setItems(daytables.get(28));
        day30.setItems(daytables.get(29));
        day31.setItems(daytables.get(30));
        day32.setItems(daytables.get(31));
        day33.setItems(daytables.get(32));
        day34.setItems(daytables.get(33));
        day35.setItems(daytables.get(34));
        day36.setItems(daytables.get(35));
        day37.setItems(daytables.get(36));
        day38.setItems(daytables.get(37));
        day39.setItems(daytables.get(38));
        day40.setItems(daytables.get(39));
        day41.setItems(daytables.get(40));
        day42.setItems(daytables.get(41));

        try {
            //set headers
            day1col.setText(String.valueOf(datesInMonth.get(0).toInstant().atZone(ZoneId.systemDefault()).getDayOfMonth()));
            day2col.setText(String.valueOf(datesInMonth.get(1).toInstant().atZone(ZoneId.systemDefault()).getDayOfMonth()));
            day3col.setText(String.valueOf(datesInMonth.get(2).toInstant().atZone(ZoneId.systemDefault()).getDayOfMonth()));
            day4col.setText(String.valueOf(datesInMonth.get(3).toInstant().atZone(ZoneId.systemDefault()).getDayOfMonth()));
            day5col.setText(String.valueOf(datesInMonth.get(4).toInstant().atZone(ZoneId.systemDefault()).getDayOfMonth()));
            day6col.setText(String.valueOf(datesInMonth.get(5).toInstant().atZone(ZoneId.systemDefault()).getDayOfMonth()));
            day7col.setText(String.valueOf(datesInMonth.get(6).toInstant().atZone(ZoneId.systemDefault()).getDayOfMonth()));
            day8col.setText(String.valueOf(datesInMonth.get(7).toInstant().atZone(ZoneId.systemDefault()).getDayOfMonth()));
            day9col.setText(String.valueOf(datesInMonth.get(8).toInstant().atZone(ZoneId.systemDefault()).getDayOfMonth()));
            day10col.setText(String.valueOf(datesInMonth.get(9).toInstant().atZone(ZoneId.systemDefault()).getDayOfMonth()));
            day11col.setText(String.valueOf(datesInMonth.get(10).toInstant().atZone(ZoneId.systemDefault()).getDayOfMonth()));
            day12col.setText(String.valueOf(datesInMonth.get(11).toInstant().atZone(ZoneId.systemDefault()).getDayOfMonth()));
            day13col.setText(String.valueOf(datesInMonth.get(12).toInstant().atZone(ZoneId.systemDefault()).getDayOfMonth()));
            day14col.setText(String.valueOf(datesInMonth.get(13).toInstant().atZone(ZoneId.systemDefault()).getDayOfMonth()));
            day15col.setText(String.valueOf(datesInMonth.get(14).toInstant().atZone(ZoneId.systemDefault()).getDayOfMonth()));
            day16col.setText(String.valueOf(datesInMonth.get(15).toInstant().atZone(ZoneId.systemDefault()).getDayOfMonth()));
            day17col.setText(String.valueOf(datesInMonth.get(16).toInstant().atZone(ZoneId.systemDefault()).getDayOfMonth()));
            day18col.setText(String.valueOf(datesInMonth.get(17).toInstant().atZone(ZoneId.systemDefault()).getDayOfMonth()));
            day19col.setText(String.valueOf(datesInMonth.get(18).toInstant().atZone(ZoneId.systemDefault()).getDayOfMonth()));
            day20col.setText(String.valueOf(datesInMonth.get(19).toInstant().atZone(ZoneId.systemDefault()).getDayOfMonth()));
            day21col.setText(String.valueOf(datesInMonth.get(20).toInstant().atZone(ZoneId.systemDefault()).getDayOfMonth()));
            day22col.setText(String.valueOf(datesInMonth.get(21).toInstant().atZone(ZoneId.systemDefault()).getDayOfMonth()));
            day23col.setText(String.valueOf(datesInMonth.get(22).toInstant().atZone(ZoneId.systemDefault()).getDayOfMonth()));
            day24col.setText(String.valueOf(datesInMonth.get(23).toInstant().atZone(ZoneId.systemDefault()).getDayOfMonth()));
            day25col.setText(String.valueOf(datesInMonth.get(24).toInstant().atZone(ZoneId.systemDefault()).getDayOfMonth()));
            day26col.setText(String.valueOf(datesInMonth.get(25).toInstant().atZone(ZoneId.systemDefault()).getDayOfMonth()));
            day27col.setText(String.valueOf(datesInMonth.get(26).toInstant().atZone(ZoneId.systemDefault()).getDayOfMonth()));
            day28col.setText(String.valueOf(datesInMonth.get(27).toInstant().atZone(ZoneId.systemDefault()).getDayOfMonth()));
            day29col.setText(String.valueOf(datesInMonth.get(28).toInstant().atZone(ZoneId.systemDefault()).getDayOfMonth()));
            day30col.setText(String.valueOf(datesInMonth.get(29).toInstant().atZone(ZoneId.systemDefault()).getDayOfMonth()));
            day31col.setText(String.valueOf(datesInMonth.get(30).toInstant().atZone(ZoneId.systemDefault()).getDayOfMonth()));
            day32col.setText(String.valueOf(datesInMonth.get(31).toInstant().atZone(ZoneId.systemDefault()).getDayOfMonth()));
            day33col.setText(String.valueOf(datesInMonth.get(32).toInstant().atZone(ZoneId.systemDefault()).getDayOfMonth()));
            day34col.setText(String.valueOf(datesInMonth.get(33).toInstant().atZone(ZoneId.systemDefault()).getDayOfMonth()));
            day35col.setText(String.valueOf(datesInMonth.get(34).toInstant().atZone(ZoneId.systemDefault()).getDayOfMonth()));
            day36col.setText(String.valueOf(datesInMonth.get(35).toInstant().atZone(ZoneId.systemDefault()).getDayOfMonth()));
            day37col.setText(String.valueOf(datesInMonth.get(36).toInstant().atZone(ZoneId.systemDefault()).getDayOfMonth()));
            day38col.setText(String.valueOf(datesInMonth.get(37).toInstant().atZone(ZoneId.systemDefault()).getDayOfMonth()));
            day39col.setText(String.valueOf(datesInMonth.get(38).toInstant().atZone(ZoneId.systemDefault()).getDayOfMonth()));
            day40col.setText(String.valueOf(datesInMonth.get(39).toInstant().atZone(ZoneId.systemDefault()).getDayOfMonth()));
            day41col.setText(String.valueOf(datesInMonth.get(40).toInstant().atZone(ZoneId.systemDefault()).getDayOfMonth()));
            day42col.setText(String.valueOf(datesInMonth.get(41).toInstant().atZone(ZoneId.systemDefault()).getDayOfMonth()));
        } catch (IndexOutOfBoundsException e){

        }



        // set cell value factories with lambda functions
        day1col.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        day2col.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        day3col.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        day4col.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        day5col.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        day6col.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        day7col.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        day8col.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        day9col.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        day10col.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        day11col.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        day12col.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        day13col.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        day14col.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        day15col.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        day16col.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        day17col.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        day18col.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        day19col.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        day20col.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        day21col.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        day22col.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        day23col.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        day24col.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        day25col.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        day26col.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        day27col.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        day28col.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        day29col.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        day30col.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        day31col.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        day32col.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        day33col.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        day34col.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        day35col.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        day36col.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        day37col.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        day38col.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        day39col.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        day40col.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        day41col.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        day42col.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));

    }

    void appointmentCheck(){
        try {
            // get date of now
            LocalDateTime now = LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC);
            // get date of 15 minutes ahead
            LocalDateTime after15Minutes = now.plusMinutes(15);

            // connect to database and prepare statement
            Connection connection = DriverManager.getConnection("jdbc:mysql://wgudb.ucertify.com:3306/U07Vgt", "U07Vgt", "53689140721");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM appointment WHERE start BETWEEN ? AND ?");
            statement.setObject(1, now);
            statement.setObject(2, after15Minutes);

            ResultSet rs = statement.executeQuery();
            // execute statement in if
            if (rs.next()){
                errorMessage.setText("You have an appointment within 15 minutes");
            }

            statement.close();
            connection.close();

        } catch (SQLException e){
            System.out.println("There was an error: " + e);
        }
    }

//    void monthTypeReport(){
//        try{
//
//            Connection connection = DriverManager.getConnection("jdbc:mysql://wgudb.ucertify.com:3306/U07Vgt", "U07Vgt", "53689140721");
//
//            for (int i = 1; i<)
//
//
//        } catch (SQLException e){
//            System.out.println("There was an error: " + e);
//        }
//    }
}
