package cs1302.gallery;

import java.net.http.HttpClient;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Represents an iTunes Gallery App.
 */
public class GalleryApp extends Application {

    /** HTTP client. */
    public static final HttpClient HTTP_CLIENT = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_2)           // uses HTTP protocol version 2 where possible
        .followRedirects(HttpClient.Redirect.NORMAL)  // always redirects, except from HTTPS to HTTP
        .build();                                     // builds and returns a HttpClient object

    /** Google {@code Gson} object for parsing JSON-formatted strings. */
    public static Gson GSON = new GsonBuilder()
        .setPrettyPrinting()                          // enable nice output when printing
        .create();                                    // builds and returns a Gson object

    private static final String ITUNES_API = "https://itunes.apple.com/search";
    private static final int DEFAULT_LIMIT = 200;    

    private Stage stage;
    private Scene scene;
    private VBox root;

    private HBox box1;
    private Separator sep;
    private Button playpauseButton;
    private TextField searchField;
    private ComboBox<String> optionType;
    private Button getImagesButton;

    private HBox box2;
    private Label topLabel;

    private HBox box3;
    private GridPane imagesBox;

    private HBox box4;
    private ProgressBar progressBar;
    private Label bottomLabel;

    /**
     * Constructs a {@code GalleryApp} object}.
     */
    public GalleryApp() {
        this.stage = null;
        this.scene = null;
        this.root = new VBox();

        this.box1 = new HBox();
        this.sep = new Separator();
        this.box1.setSpacing(10);
        this.playpauseButton = new Button("Play");
        this.searchField = new TextField("daft punk");
        this.searchField.setPrefWidth(200);
        this.optionType = new ComboBox<String>();
        this.optionType.getItems().addAll("movie", "podcast", "music", 
                         "musicVideo", "audiobook", "shortFilm", 
                         "tvShow", "software", "ebook", "all");
        this.optionType.setValue("music");
        this.getImagesButton = new Button("Get Images");
        this.getImagesButton.setPrefWidth(80);

        // adding children, orientation, and spacing to box1
        this.sep.setOrientation(javafx.geometry.Orientation.VERTICAL);
        this.box1.getChildren().addAll(playpauseButton, this.sep, searchField, optionType, getImagesButton);
        HBox.setHgrow(box1,Priority.ALWAYS);
        this.box1.setPadding(new Insets(10, 10, 10, 10));

        this.box2 = new HBox();
        this.topLabel = new Label(" Type in a term, select a media type, then click the button.");

        // adding children and priority to box2
        this.box2.getChildren().addAll(topLabel); 
        HBox.setHgrow(box2,Priority.ALWAYS);

        this.box3 = new HBox();
        this.imagesBox = new GridPane();

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 5; col++) {
                javafx.scene.image.ImageView imageView = new javafx.scene.image.ImageView(new Image("file:resources/default.png"));
                imageView.setFitHeight(100);
                imageView.setFitWidth(100);
                GridPane.setConstraints(imageView, col, row); // Set the position in the grid
                this.imagesBox.getChildren().add(imageView);
            } // for
        } // for

        // adding children to box3
        this.box3.getChildren().addAll(imagesBox);

        this.box4 = new HBox();
        this.progressBar = new ProgressBar();
        this.progressBar.setPrefWidth(265);
        this.bottomLabel = new Label(" Images provided by iTunes Search API.");

        // adding children and padding to box4
        this.box4.getChildren().addAll(progressBar, bottomLabel);
        this.box4.setPadding(new Insets(10, 10, 10, 10));
    } // GalleryApp

    /** {@inheritDoc} */
    @Override
    public void init() {
        // feel free to modify this method
        System.out.println("init() called");
    } // init

    /** {@inheritDoc} */
    @Override
    public void start(Stage stage) {
        this.stage = stage;
        this.scene = new Scene(this.root);
        this.root.getChildren().addAll(box1, box2, box3, box4);
        this.stage.setOnCloseRequest(event -> Platform.exit());
        this.stage.setTitle("GalleryApp!");
        this.stage.setScene(this.scene);
        this.stage.sizeToScene();
        this.stage.show();
        Platform.runLater(() -> this.stage.setResizable(false));
    } // start

    /** {@inheritDoc} */
    @Override
    public void stop() {
        // feel free to modify this method
        System.out.println("stop() called");
    } // stop

} // GalleryApp
