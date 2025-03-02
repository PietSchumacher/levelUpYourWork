import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SkillCreationDialog extends Stage {

    public SkillCreationDialog(MainApp mainApp) {
        setTitle("Neuen Skill anlegen");

        Label nameLabel = new Label("Skill Name:");
        TextField nameField = new TextField();
        Button saveButton = new Button("Speichern");

        saveButton.setOnAction(e -> {
            String skillName = nameField.getText().trim();
            mainApp.addSkill(skillName);
            close(); // Fenster schließen
        });

        VBox layout = new VBox(10, nameLabel, nameField, saveButton);
        layout.getStylesheets().add("styles.css");

        Scene scene = new Scene(layout, 300, 150);

        setScene(scene);
        initModality(Modality.APPLICATION_MODAL); // Blockiert Hauptfenster, solange es offen ist
    }
}
