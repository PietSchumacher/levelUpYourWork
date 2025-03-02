import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Character;
import model.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainApp extends Application {

    private List<Skill> skills = new ArrayList<>();
    private List<Character> characters = new ArrayList<>();
    private VBox skillListBox;
    private TextField searchField;
    private VBox characterListBox;

    @Override
    public void start(Stage stage) {
        Label titleLabel = new Label("Wähle einen Charakter:");
        searchField = new TextField();
        searchField.setPromptText("Suche Charakter...");

        characterListBox = new VBox(5); // Container für gefilterte Charakterliste

        loadCharacters(); // Initial Charaktere laden
        updateCharacterList(""); // Zeigt alle an

        searchField.textProperty().addListener((obs, oldVal, newVal) -> updateCharacterList(newVal));

        VBox root = new VBox(10, titleLabel, searchField, characterListBox);
        root.getStylesheets().add("styles.css");

        Scene scene = new Scene(root, 400, 300);
        stage.setTitle("Charakter Auswahl");
        stage.setScene(scene);
        stage.show();
    }

    private void loadCharacters() {
        characters.add(createCharacter("Aragorn"));
        characters.add(createCharacter("Legolas"));
        characters.add(createCharacter("Gandalf"));
        characters.add(createCharacter("Frodo"));
        characters.add(createCharacter("Gimli"));
    }

    private void updateCharacterList(String filter) {
        characterListBox.getChildren().clear();

        List<Character> filtered = characters.stream()
                .filter(c -> c.getName().toLowerCase().contains(filter.toLowerCase()))
                .collect(Collectors.toList());

        for (Character character : filtered) {
            Button charButton = new Button(character.getName());
            charButton.setOnAction(e -> selectCharacter(character));
            characterListBox.getChildren().add(charButton);
        }
    }

    private void selectCharacter(Character character) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Charakter ausgewählt");
        alert.setHeaderText(null);
        alert.setContentText("Du hast " + character.getName() + " ausgewählt!");
        alert.showAndWait();
    }

    private Character createCharacter(String name) {
        Character character = new Character();
        character.setName(name);
        return character;
    }


    public void second(Stage stage) {
        Label titleLabel = new Label("Willkommen! Erstelle ein neues Skill:");
        Button newSkillButton = new Button("Neuen Skill anlegen");
        skillListBox = new VBox(5); // Container für Skill-Liste

        newSkillButton.setOnAction(e -> openSkillCreationWindow());

        VBox root = new VBox(10, titleLabel, newSkillButton, skillListBox);
        root.getStylesheets().add("styles.css");

        Scene scene = new Scene(root, 400, 300);
        stage.setTitle("Skill Manager");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    private void openSkillCreationWindow() {
        SkillCreationDialog skillDialog = new SkillCreationDialog(this);
        skillDialog.show();
    }

    public void addSkill(String skillName) {
        if (!skillName.isEmpty() && !skills.contains(skillName)) {
            Skill newSkill = new Skill();
            newSkill.setName(skillName);
            skills.add(newSkill);
            skillListBox.getChildren().add(new Label("- " + skillName)); // Fügt Skill zur Liste hinzu
        }
    }
    

    public static void main(String[] args) {
        launch();
    }
}
