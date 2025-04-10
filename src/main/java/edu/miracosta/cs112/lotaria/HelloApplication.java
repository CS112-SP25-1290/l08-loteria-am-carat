package edu.miracosta.cs112.lotaria;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.Font;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.Random;
import javafx.scene.control.ProgressBar;

public class HelloApplication extends Application{
    private static final LoteriaCard[] LOTERIA_CARDS = {
            new LoteriaCard("Las matematicas", "1.png", 1),
            new LoteriaCard("Las ciencias", "2.png", 2),
            new LoteriaCard("La Tecnología", "8.png", 8),
            new LoteriaCard("La ingeniería", "9.png", 9),
    };

    private Label messageLabel, titleLabel;
    private ImageView cardImageView;
    private Button drawCardButton;
    private ProgressBar gameProgressBar;



    @Override
    public void start(Stage stage) throws Exception{

        titleLabel = new Label("Welcome to EchALE STEM Loteria!");
        cardImageView = new ImageView();
        messageLabel = new Label("Click the button to randomly draw a card. The progress bar will indicate how far we're into the game.");
        drawCardButton = new Button("Draw Random Card");
        gameProgressBar = new ProgressBar();
        titleLabel.setFont(new Font(18.0));

        LoteriaCard tempCard = new LoteriaCard();
        cardImageView.setImage(tempCard.getImage());
        cardImageView.setFitWidth(300);
        cardImageView.setPreserveRatio(true);

        messageLabel.setWrapText(true);
        messageLabel.setTextAlignment(TextAlignment.CENTER);

        drawCardButton.setOnAction(e -> drawCard());

        VBox mainLayout = new VBox();
        mainLayout.getChildren().addAll(titleLabel, cardImageView, messageLabel, drawCardButton);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setSpacing(5);
        mainLayout.getChildren().add(gameProgressBar);

        //StackPane root = new StackPane();
        Scene scene = new Scene(mainLayout, 350, 500);
        stage.setTitle("Loteria!");
        stage.setScene(scene);
        stage.show();

        drawn = new boolean[LOTERIA_CARDS.length];

    }

    public static void main(String[] args) {
        launch();
    }

    private boolean[] drawn;
    private Random random = new Random();
    private int progress = 0;

    void drawCard()
    {
        int next = random.nextInt(LOTERIA_CARDS.length);
        if(progress == LOTERIA_CARDS.length)
        {
            return;
        }
        while (drawn[next] != false)
        {
            next = random.nextInt(LOTERIA_CARDS.length);
        }

        progress++;
        LoteriaCard card = LOTERIA_CARDS[next];
        drawn[next] = true;
        messageLabel.setText(card.getCardName());
        cardImageView.setImage(card.getImage());
        double progressDouble = (progress/(double) LOTERIA_CARDS.length);
        gameProgressBar.setProgress(progressDouble);

    }


}