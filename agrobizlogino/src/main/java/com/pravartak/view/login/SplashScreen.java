package com.pravartak.view.login;

import javafx.animation.*;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SplashScreen {

        // =========================================================
        // SIZE
        // =========================================================

        private static final double WIDTH = 1368;
        private static final double HEIGHT = 768;

        // =========================================================
        // AGROBIZ COLORS
        // =========================================================

        private static final Color DARK_GREEN = Color.web("#032B16");

        private static final Color DEEP_GREEN = Color.web("#011A0D");

        private static final Color AGRO_GREEN = Color.web("#78D83E");

        private static final Color LIME_GREEN = Color.web("#B6F45A");

        private static final Color SOFT_GREEN = Color.web("#D8FFAE");

        private static final Color GOLD = Color.web("#D9E83F");

        private static final Color WHITE = Color.WHITE;

        private static final Color MUTED_WHITE = Color.web("#C7D8CC");

        private static final Color MUTED_GREEN = Color.web("#789989");

        // =========================================================
        // SCENE
        // =========================================================

        private Scene splashScene;

        private AnimationTimer engineTimer;

        private final List<AmbientSpore> spores = new ArrayList<>();

        private final List<EnergyRipple> ripples = new ArrayList<>();

        private final Random rng = new Random();

        // =========================================================
        // PARALLAX
        // =========================================================

        private double targetX = 0;
        private double targetY = 0;

        private double currentX = 0;
        private double currentY = 0;

        // =========================================================
        // HERO ELEMENTS
        // =========================================================

        private Group logoGroup;

        private Label topBadge;
        private Label brandTitle;
        private Label slogan;

        private VBox categoryContainer;

        private Label statusLabel;

        private Rectangle progressFill;
        private Rectangle shimmerSweep;

        // =========================================================
        // CATEGORY ICONS
        // =========================================================

        private StackPane poultryIcon;
        private StackPane dairyIcon;
        private StackPane mushroomIcon;
        private StackPane cropsIcon;
        private StackPane fishIcon;

        // =========================================================
        // MAIN PUBLIC METHOD
        //
        // DO NOT CHANGE THIS METHOD SIGNATURE.
        // EXISTING CALLS WILL CONTINUE TO WORK.
        // =========================================================

        public Scene getSplashScene(Runnable onComplete) {

                StackPane root = new StackPane();

                root.setStyle(
                                "-fx-background-color: #011A0D;");

                // -----------------------------------------------------
                // BACKGROUND
                // -----------------------------------------------------

                Region backdrop = buildBackdrop();

                Pane atmosphere = buildAtmosphere();

                Pane landscape = buildAgricultureLandscape();

                // -----------------------------------------------------
                // PARTICLE CANVAS
                // -----------------------------------------------------

                Canvas canvas = new Canvas(
                                WIDTH,
                                HEIGHT);

                canvas.setCache(true);
                canvas.setCacheHint(
                                CacheHint.SPEED);

                GraphicsContext gc = canvas.getGraphicsContext2D();

                // -----------------------------------------------------
                // HERO
                // -----------------------------------------------------

                VBox hero = buildHeroScaffold();

                // -----------------------------------------------------
                // PARALLAX
                // -----------------------------------------------------

                setupParallax(
                                root,
                                hero,
                                atmosphere,
                                landscape);

                // -----------------------------------------------------
                // LAYER ORDER
                // -----------------------------------------------------

                root.getChildren().addAll(
                                backdrop,
                                atmosphere,
                                landscape,
                                canvas,
                                hero);

                // -----------------------------------------------------
                // SCENE
                // -----------------------------------------------------

                splashScene = new Scene(
                                root,
                                WIDTH,
                                HEIGHT);

                splashScene.setFill(
                                DEEP_GREEN);

                // -----------------------------------------------------
                // PARTICLES
                // -----------------------------------------------------

                initParticles();

                startLoop(gc);

                // -----------------------------------------------------
                // ANIMATION
                // -----------------------------------------------------

                runChoreography(
                                root,
                                hero,
                                onComplete);

                return splashScene;
        }

        // =========================================================
        // BACKGROUND
        // =========================================================

        private Region buildBackdrop() {

                Region bg = new Region();

                bg.setPrefSize(
                                WIDTH,
                                HEIGHT);

                RadialGradient gradient = new RadialGradient(
                                0,
                                0,
                                0.5,
                                0.34,
                                0.85,
                                true,
                                CycleMethod.NO_CYCLE,

                                new Stop(
                                                0.00,
                                                Color.web("#0A4222")),

                                new Stop(
                                                0.28,
                                                Color.web("#07361D")),

                                new Stop(
                                                0.55,
                                                Color.web("#032414")),

                                new Stop(
                                                0.82,
                                                Color.web("#01170B")),

                                new Stop(
                                                1.00,
                                                Color.web("#000804")));

                bg.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                gradient,
                                                                CornerRadii.EMPTY,
                                                                null)));

                return bg;
        }

        // =========================================================
        // ATMOSPHERE
        // =========================================================

        private Pane buildAtmosphere() {

                Pane pane = new Pane();

                pane.setMouseTransparent(true);

                Circle centerGlow = new Circle(
                                WIDTH / 2,
                                HEIGHT * 0.34,
                                270);

                centerGlow.setFill(
                                new RadialGradient(
                                                0,
                                                0,
                                                0.5,
                                                0.5,
                                                0.5,
                                                true,
                                                CycleMethod.NO_CYCLE,

                                                new Stop(
                                                                0,
                                                                Color.rgb(
                                                                                120,
                                                                                216,
                                                                                62,
                                                                                0.18)),

                                                new Stop(
                                                                0.4,
                                                                Color.rgb(
                                                                                120,
                                                                                216,
                                                                                62,
                                                                                0.07)),

                                                new Stop(
                                                                1,
                                                                Color.TRANSPARENT)));

                centerGlow.setEffect(
                                new GaussianBlur(80));

                Circle leftGlow = new Circle(
                                160,
                                260,
                                140);

                leftGlow.setFill(
                                new RadialGradient(
                                                0,
                                                0,
                                                0.5,
                                                0.5,
                                                0.5,
                                                true,
                                                CycleMethod.NO_CYCLE,

                                                new Stop(
                                                                0,
                                                                Color.rgb(
                                                                                120,
                                                                                216,
                                                                                62,
                                                                                0.06)),

                                                new Stop(
                                                                1,
                                                                Color.TRANSPARENT)));

                leftGlow.setEffect(
                                new GaussianBlur(65));

                Circle rightGlow = new Circle(
                                WIDTH - 160,
                                260,
                                140);

                rightGlow.setFill(
                                new RadialGradient(
                                                0,
                                                0,
                                                0.5,
                                                0.5,
                                                0.5,
                                                true,
                                                CycleMethod.NO_CYCLE,

                                                new Stop(
                                                                0,
                                                                Color.rgb(
                                                                                217,
                                                                                232,
                                                                                63,
                                                                                0.05)),

                                                new Stop(
                                                                1,
                                                                Color.TRANSPARENT)));

                rightGlow.setEffect(
                                new GaussianBlur(65));

                pane.getChildren().addAll(
                                centerGlow,
                                leftGlow,
                                rightGlow);

                return pane;
        }

        // =========================================================
        // AGRICULTURAL LANDSCAPE
        // =========================================================

        private Pane buildAgricultureLandscape() {

                Pane pane = new Pane();

                pane.setMouseTransparent(true);

                // -----------------------------------------------------
                // BACK HILL
                // -----------------------------------------------------

                Path backHill = new Path();

                backHill.getElements().addAll(
                                new MoveTo(
                                                0,
                                                515),

                                new CubicCurveTo(
                                                180,
                                                435,
                                                320,
                                                475,
                                                480,
                                                495),

                                new CubicCurveTo(
                                                660,
                                                515,
                                                780,
                                                435,
                                                940,
                                                480),

                                new CubicCurveTo(
                                                1110,
                                                520,
                                                1230,
                                                440,
                                                WIDTH,
                                                475),

                                new LineTo(
                                                WIDTH,
                                                HEIGHT),

                                new LineTo(
                                                0,
                                                HEIGHT),

                                new ClosePath());

                backHill.setFill(
                                Color.web("#062516"));

                // -----------------------------------------------------
                // FRONT FIELD
                // -----------------------------------------------------

                Path field = new Path();

                field.getElements().addAll(
                                new MoveTo(
                                                0,
                                                600),

                                new CubicCurveTo(
                                                240,
                                                540,
                                                430,
                                                565,
                                                680,
                                                580),

                                new CubicCurveTo(
                                                900,
                                                595,
                                                1110,
                                                545,
                                                WIDTH,
                                                575),

                                new LineTo(
                                                WIDTH,
                                                HEIGHT),

                                new LineTo(
                                                0,
                                                HEIGHT),

                                new ClosePath());

                field.setFill(
                                Color.web("#07321A"));

                pane.getChildren().addAll(
                                backHill,
                                field);

                // -----------------------------------------------------
                // FIELD ROWS
                // -----------------------------------------------------

                for (int i = 0; i < 11; i++) {

                        Path row = new Path();

                        double x = 430 + i * 62;

                        row.getElements().addAll(
                                        new MoveTo(
                                                        x,
                                                        610),

                                        new LineTo(
                                                        x - 220,
                                                        HEIGHT));

                        row.setStroke(
                                        Color.rgb(
                                                        90,
                                                        170,
                                                        55,
                                                        0.14));

                        row.setStrokeWidth(2);

                        pane.getChildren().add(
                                        row);
                }

                // -----------------------------------------------------
                // BARN
                // -----------------------------------------------------

                Rectangle barn = new Rectangle(
                                1080,
                                462,
                                108,
                                70);

                barn.setFill(
                                Color.web("#0A3B20"));

                Polygon roof = new Polygon(
                                1058,
                                462,
                                1134,
                                410,
                                1208,
                                462);

                roof.setFill(
                                Color.web("#0E4826"));

                Rectangle door = new Rectangle(
                                1116,
                                486,
                                36,
                                46);

                door.setFill(
                                Color.web("#02170C"));

                pane.getChildren().addAll(
                                barn,
                                roof,
                                door);

                // -----------------------------------------------------
                // TREES
                // -----------------------------------------------------

                pane.getChildren().addAll(
                                createSmallTree(
                                                150,
                                                480,
                                                0.85),

                                createSmallTree(
                                                245,
                                                500,
                                                0.65),

                                createSmallTree(
                                                1260,
                                                475,
                                                0.75));

                return pane;
        }

        // =========================================================
        // SMALL TREE
        // =========================================================

        private Group createSmallTree(
                        double x,
                        double y,
                        double scale) {

                Group tree = new Group();

                Rectangle trunk = new Rectangle(
                                -3,
                                0,
                                6,
                                35);

                trunk.setFill(
                                Color.web("#0A2515"));

                Circle c1 = new Circle(
                                0,
                                -15,
                                22);

                Circle c2 = new Circle(
                                -15,
                                0,
                                17);

                Circle c3 = new Circle(
                                15,
                                0,
                                17);

                c1.setFill(
                                Color.web("#092E19"));

                c2.setFill(
                                Color.web("#092E19"));

                c3.setFill(
                                Color.web("#092E19"));

                tree.getChildren().addAll(
                                trunk,
                                c1,
                                c2,
                                c3);

                tree.setLayoutX(x);
                tree.setLayoutY(y);

                tree.setScaleX(scale);
                tree.setScaleY(scale);

                return tree;
        }

        // =========================================================
        // HERO
        // =========================================================

        private VBox buildHeroScaffold() {

                VBox hero = new VBox();

                hero.setAlignment(
                                Pos.CENTER);

                hero.setSpacing(0);

                hero.setPickOnBounds(false);

                // -----------------------------------------------------
                // BADGE
                // -----------------------------------------------------

                topBadge = new Label(
                                "SMART FARMING • ONE COMPLETE PLATFORM");

                topBadge.setTextFill(
                                LIME_GREEN);

                topBadge.setFont(
                                Font.font(
                                                "Segoe UI",
                                                FontWeight.BOLD,
                                                12));

                topBadge.setStyle(
                                "-fx-letter-spacing: 2.5px;");

                topBadge.setOpacity(0);

                // -----------------------------------------------------
                // LOGO
                // -----------------------------------------------------

                logoGroup = buildAgroBizLogo();

                logoGroup.setOpacity(0);

                // -----------------------------------------------------
                // BRAND
                // -----------------------------------------------------

                brandTitle = new Label(
                                "AgroBiz");

                brandTitle.setTextFill(
                                WHITE);

                brandTitle.setFont(
                                Font.font(
                                                "Segoe UI",
                                                FontWeight.BOLD,
                                                54));

                brandTitle.setOpacity(0);

                brandTitle.setTranslateY(18);

                brandTitle.setEffect(
                                new DropShadow(
                                                25,
                                                Color.rgb(
                                                                0,
                                                                0,
                                                                0,
                                                                0.8)));

                // -----------------------------------------------------
                // SLOGAN
                // -----------------------------------------------------

                slogan = new Label(
                                "Learn. Plan. Grow. Prosper.");

                slogan.setTextFill(
                                MUTED_WHITE);

                slogan.setFont(
                                Font.font(
                                                "Segoe UI",
                                                FontWeight.NORMAL,
                                                17));

                slogan.setOpacity(0);

                slogan.setTranslateY(12);

                // -----------------------------------------------------
                // CATEGORY
                // -----------------------------------------------------

                categoryContainer = buildCategoryContainer();

                categoryContainer.setOpacity(0);

                categoryContainer.setTranslateY(12);

                // -----------------------------------------------------
                // HUD
                // -----------------------------------------------------

                VBox hud = buildLoadingHUD();

                hud.setOpacity(0);

                // -----------------------------------------------------
                // HERO CONTENT
                // -----------------------------------------------------

                hero.getChildren().addAll(

                                topBadge,

                                createSpacer(16),

                                logoGroup,

                                createSpacer(12),

                                brandTitle,

                                createSpacer(4),

                                slogan,

                                createSpacer(20),

                                categoryContainer,

                                createSpacer(26),

                                hud);

                hero.getProperties().put(
                                "topBadge",
                                topBadge);

                hero.getProperties().put(
                                "logoGroup",
                                logoGroup);

                hero.getProperties().put(
                                "brandTitle",
                                brandTitle);

                hero.getProperties().put(
                                "slogan",
                                slogan);

                hero.getProperties().put(
                                "categoryContainer",
                                categoryContainer);

                hero.getProperties().put(
                                "hud",
                                hud);

                return hero;
        }

        // =========================================================
        // AGROBIZ LOGO
        // =========================================================

        private Group buildAgroBizLogo() {

                Group root = new Group();

                Circle outer = new Circle(
                                0,
                                0,
                                43);

                outer.setFill(
                                Color.TRANSPARENT);

                outer.setStroke(
                                LIME_GREEN);

                outer.setStrokeWidth(2);

                Circle inner = new Circle(
                                0,
                                0,
                                34);

                inner.setFill(
                                Color.rgb(
                                                120,
                                                216,
                                                62,
                                                0.07));

                inner.setStroke(
                                Color.rgb(
                                                182,
                                                244,
                                                90,
                                                0.45));

                inner.setStrokeWidth(1);

                // Main stem
                CubicCurve stem = new CubicCurve(
                                0,
                                24,
                                -2,
                                8,
                                2,
                                -7,
                                0,
                                -24);

                stem.setFill(null);

                stem.setStroke(
                                LIME_GREEN);

                stem.setStrokeWidth(3);

                stem.setStrokeLineCap(
                                StrokeLineCap.ROUND);

                // Left leaf
                Ellipse leftLeaf = new Ellipse(
                                -13,
                                -5,
                                14,
                                7);

                leftLeaf.setFill(
                                AGRO_GREEN);

                leftLeaf.setRotate(-35);

                // Right leaf
                Ellipse rightLeaf = new Ellipse(
                                13,
                                -13,
                                15,
                                7);

                rightLeaf.setFill(
                                LIME_GREEN);

                rightLeaf.setRotate(35);

                // Bottom leaf
                Ellipse bottomLeaf = new Ellipse(
                                -10,
                                13,
                                15,
                                7);

                bottomLeaf.setFill(
                                AGRO_GREEN);

                bottomLeaf.setRotate(-15);

                // Glow
                Circle glow = new Circle(
                                0,
                                0,
                                47);

                glow.setFill(
                                Color.TRANSPARENT);

                glow.setStroke(
                                Color.rgb(
                                                182,
                                                244,
                                                90,
                                                0.22));

                glow.setStrokeWidth(4);

                glow.setEffect(
                                new GaussianBlur(8));

                root.getChildren().addAll(
                                glow,
                                outer,
                                inner,
                                stem,
                                leftLeaf,
                                rightLeaf,
                                bottomLeaf);

                root.setScaleX(0.55);
                root.setScaleY(0.55);

                return root;
        }

        // =========================================================
        // CATEGORY CONTAINER
        // =========================================================

        private VBox buildCategoryContainer() {

                VBox wrapper = new VBox(8);

                wrapper.setAlignment(
                                Pos.CENTER);

                Label heading = new Label(
                                "FARMING • LEARNING • MARKET • AI");

                heading.setTextFill(
                                Color.rgb(
                                                190,
                                                220,
                                                198,
                                                0.72));

                heading.setFont(
                                Font.font(
                                                "Segoe UI",
                                                FontWeight.BOLD,
                                                9));

                heading.setStyle(
                                "-fx-letter-spacing: 2px;");

                HBox categories = new HBox(26);

                categories.setAlignment(
                                Pos.CENTER);

                // -----------------------------------------------------
                // POULTRY
                // -----------------------------------------------------

                VBox poultry = createCategory(
                                createPoultryIcon(),
                                "Poultry");

                poultryIcon = (StackPane) poultry.getProperties()
                                .get("icon");

                // -----------------------------------------------------
                // DAIRY
                // -----------------------------------------------------

                VBox dairy = createCategory(
                                createDairyIcon(),
                                "Dairy");

                dairyIcon = (StackPane) dairy.getProperties()
                                .get("icon");

                // -----------------------------------------------------
                // MUSHROOM
                // -----------------------------------------------------

                VBox mushroom = createCategory(
                                createMushroomIcon(),
                                "Mushroom");

                mushroomIcon = (StackPane) mushroom.getProperties()
                                .get("icon");

                // -----------------------------------------------------
                // CROPS
                // -----------------------------------------------------

                VBox crops = createCategory(
                                createCropIcon(),
                                "Crops");

                cropsIcon = (StackPane) crops.getProperties()
                                .get("icon");

                // -----------------------------------------------------
                // FISHERY
                // -----------------------------------------------------

                VBox fish = createCategory(
                                createFishIcon(),
                                "Fishery");

                fishIcon = (StackPane) fish.getProperties()
                                .get("icon");

                categories.getChildren().addAll(
                                poultry,
                                dairy,
                                mushroom,
                                crops,
                                fish);

                wrapper.getChildren().addAll(
                                heading,
                                categories);

                return wrapper;
        }

        // =========================================================
        // CATEGORY BOX
        // =========================================================

        private VBox createCategory(
                        StackPane icon,
                        String name) {

                VBox box = new VBox(5);

                box.setAlignment(
                                Pos.CENTER);

                Label label = new Label(
                                name);

                label.setTextFill(
                                MUTED_WHITE);

                label.setFont(
                                Font.font(
                                                "Segoe UI",
                                                FontWeight.NORMAL,
                                                10.5));

                box.getChildren().addAll(
                                icon,
                                label);

                box.getProperties().put(
                                "icon",
                                icon);

                return box;
        }

        // =========================================================
        // ICON HOLDER
        // =========================================================

        private StackPane iconHolder(
                        Group icon) {

                StackPane holder = new StackPane();

                Circle background = new Circle(
                                27);

                background.setFill(
                                Color.rgb(
                                                3,
                                                45,
                                                22,
                                                0.85));

                background.setStroke(
                                Color.rgb(
                                                120,
                                                216,
                                                62,
                                                0.52));

                background.setStrokeWidth(
                                1.2);

                holder.getChildren().addAll(
                                background,
                                icon);

                holder.setPrefSize(
                                54,
                                54);

                holder.setMinSize(
                                54,
                                54);

                holder.setMaxSize(
                                54,
                                54);

                return holder;
        }

        // =========================================================
        // POULTRY VECTOR
        // =========================================================

        private StackPane createPoultryIcon() {

                Group icon = new Group();

                // Body
                Ellipse body = new Ellipse(
                                0,
                                5,
                                13,
                                10);

                body.setFill(
                                Color.web("#F2D3A2"));

                // Head
                Circle head = new Circle(
                                9,
                                -9,
                                8);

                head.setFill(
                                Color.web("#FFF0C7"));

                // Beak
                Polygon beak = new Polygon(
                                17,
                                -9,
                                24,
                                -6,
                                17,
                                -3);

                beak.setFill(
                                GOLD);

                // Comb
                Circle comb = new Circle(
                                7,
                                -17,
                                3);

                comb.setFill(
                                GOLD);

                // Wing
                Ellipse wing = new Ellipse(
                                -3,
                                3,
                                7,
                                5);

                wing.setFill(
                                Color.web("#52B92C"));

                // Legs
                Line leg1 = new Line(
                                -4,
                                13,
                                -5,
                                20);

                Line leg2 = new Line(
                                3,
                                13,
                                4,
                                20);

                leg1.setStroke(
                                GOLD);

                leg2.setStroke(
                                GOLD);

                leg1.setStrokeWidth(2);
                leg2.setStrokeWidth(2);

                icon.getChildren().addAll(
                                body,
                                head,
                                beak,
                                comb,
                                wing,
                                leg1,
                                leg2);

                icon.setScaleX(1.0);
                icon.setScaleY(1.0);
                ;

                icon.setEffect(
                                new Glow(0.35));

                return iconHolder(icon);
        }

        // =========================================================
        // DAIRY VECTOR
        // =========================================================

        private StackPane createDairyIcon() {

                Group icon = new Group();

                // Cow head
                Ellipse head = new Ellipse(
                                0,
                                1,
                                15,
                                12);

                head.setFill(
                                Color.web("#F4F0E6"));

                // Ears
                Ellipse earLeft = new Ellipse(
                                -15,
                                -5,
                                7,
                                4);

                Ellipse earRight = new Ellipse(
                                15,
                                -5,
                                7,
                                4);

                earLeft.setFill(
                                Color.web("#8B5E3C"));

                earRight.setFill(
                                Color.web("#8B5E3C"));

                // Horns
                Line hornLeft = new Line(
                                -9,
                                -9,
                                -12,
                                -16);

                Line hornRight = new Line(
                                9,
                                -9,
                                12,
                                -16);

                hornLeft.setStroke(
                                GOLD);

                hornRight.setStroke(
                                GOLD);

                hornLeft.setStrokeWidth(2);
                hornRight.setStrokeWidth(2);

                // Eyes
                Circle eyeLeft = new Circle(
                                -6,
                                -1,
                                1.5);

                Circle eyeRight = new Circle(
                                6,
                                -1,
                                1.5);

                eyeLeft.setFill(
                                DEEP_GREEN);

                eyeRight.setFill(
                                DEEP_GREEN);

                // Nose
                Ellipse nose = new Ellipse(
                                0,
                                7,
                                7,
                                4);

                nose.setFill(
                                Color.web("#9BE86B"));

                icon.getChildren().addAll(
                                head,
                                earLeft,
                                earRight,
                                hornLeft,
                                hornRight,
                                eyeLeft,
                                eyeRight,
                                nose);

                icon.setScaleX(1.0);
                icon.setScaleY(1.0);

                icon.setEffect(
                                new Glow(0.35));

                return iconHolder(icon);
        }

        // =========================================================
        // MUSHROOM VECTOR
        // =========================================================

        private StackPane createMushroomIcon() {

                Group icon = new Group();

                Arc cap = new Arc(
                                0,
                                -2,
                                18,
                                13,
                                0,
                                180);

                cap.setType(
                                ArcType.ROUND);

                cap.setFill(
                                Color.web("#E76F51"));

                Rectangle stem = new Rectangle(
                                -8,
                                -2,
                                16,
                                19);

                stem.setArcWidth(8);
                stem.setArcHeight(8);

                stem.setFill(
                                Color.web("#F5E6C8"));

                Circle spot1 = new Circle(
                                -8,
                                -7,
                                2);

                Circle spot2 = new Circle(
                                5,
                                -8,
                                2);

                Circle spot3 = new Circle(
                                0,
                                -1,
                                1.8);

                spot1.setFill(
                                GOLD);

                spot2.setFill(
                                GOLD);

                spot3.setFill(
                                GOLD);

                icon.getChildren().addAll(
                                cap,
                                stem,
                                spot1,
                                spot2,
                                spot3);

                icon.setScaleX(1.0);
                icon.setScaleY(1.0);

                icon.setEffect(
                                new Glow(0.35));

                return iconHolder(icon);
        }

        // =========================================================
        // CROP / WHEAT VECTOR
        // =========================================================

        private StackPane createCropIcon() {

                Group icon = new Group();

                Line stem = new Line(
                                0,
                                20,
                                0,
                                -17);

                stem.setStroke(
                                Color.web("#66C93A"));

                stem.setStrokeWidth(2.5);

                Ellipse grain1 = createGrain(
                                -7,
                                -13,
                                -35);

                Ellipse grain2 = createGrain(
                                7,
                                -8,
                                35);

                Ellipse grain3 = createGrain(
                                -7,
                                -3,
                                -35);

                Ellipse grain4 = createGrain(
                                7,
                                2,
                                35);

                Ellipse grain5 = createGrain(
                                -6,
                                7,
                                -35);

                icon.getChildren().addAll(
                                stem,
                                grain1,
                                grain2,
                                grain3,
                                grain4,
                                grain5);

                icon.setScaleX(1.0);
                icon.setScaleY(1.0);

                icon.setEffect(
                                new Glow(0.4));

                return iconHolder(icon);
        }

        // =========================================================
        // GRAIN
        // =========================================================

        private Ellipse createGrain(
                        double x,
                        double y,
                        double rotate) {

                Ellipse grain = new Ellipse(
                                x,
                                y,
                                6,
                                3);

                grain.setFill(
                                Color.web("#E8C547"));

                grain.setRotate(
                                rotate);

                return grain;
        }

        // =========================================================
        // FISH VECTOR
        // =========================================================

        private StackPane createFishIcon() {

                Group icon = new Group();

                Ellipse body = new Ellipse(
                                0,
                                0,
                                17,
                                10);

                body.setFill(
                                Color.web("#4DB6D6"));

                Polygon tail = new Polygon(
                                -15,
                                0,
                                -26,
                                -9,
                                -26,
                                9);

                tail.setFill(
                                Color.web("#2E8FA8"));

                Circle eye = new Circle(
                                9,
                                -3,
                                2);

                eye.setFill(
                                DEEP_GREEN);

                Path fin = new Path();

                fin.getElements().addAll(
                                new MoveTo(
                                                -2,
                                                -7),

                                new LineTo(
                                                3,
                                                -16),

                                new LineTo(
                                                8,
                                                -5),

                                new ClosePath());

                fin.setFill(
                                GOLD);

                icon.getChildren().addAll(
                                body,
                                tail,
                                eye,
                                fin);

                icon.setScaleX(1.0);
                icon.setScaleY(1.0);

                icon.setEffect(
                                new Glow(0.35));

                return iconHolder(icon);
        }

        // =========================================================
        // LOADING HUD
        // =========================================================

        private VBox buildLoadingHUD() {

                VBox box = new VBox(10);

                box.setAlignment(
                                Pos.CENTER);

                StackPane track = new StackPane();

                track.setAlignment(
                                Pos.CENTER_LEFT);

                track.setPrefSize(
                                250,
                                4);

                track.setMaxWidth(
                                250);

                Rectangle trackBg = new Rectangle(
                                250,
                                4);

                trackBg.setArcWidth(4);
                trackBg.setArcHeight(4);

                trackBg.setFill(
                                Color.rgb(
                                                15,
                                                50,
                                                30,
                                                0.9));

                progressFill = new Rectangle(
                                0,
                                4);

                progressFill.setArcWidth(4);
                progressFill.setArcHeight(4);

                progressFill.setFill(
                                new LinearGradient(
                                                0,
                                                0,
                                                1,
                                                0,
                                                true,
                                                CycleMethod.NO_CYCLE,

                                                new Stop(
                                                                0,
                                                                Color.web("#5DBD2C")),

                                                new Stop(
                                                                0.65,
                                                                AGRO_GREEN),

                                                new Stop(
                                                                1,
                                                                LIME_GREEN)));

                progressFill.setEffect(
                                new Glow(0.7));

                shimmerSweep = new Rectangle(
                                28,
                                4);

                shimmerSweep.setFill(
                                Color.rgb(
                                                255,
                                                255,
                                                255,
                                                0.55));

                shimmerSweep.setBlendMode(
                                BlendMode.SCREEN);

                shimmerSweep.setEffect(
                                new GaussianBlur(3));

                shimmerSweep.setTranslateX(
                                -28);

                track.getChildren().addAll(
                                trackBg,
                                progressFill,
                                shimmerSweep);

                statusLabel = new Label(
                                "PREPARING AGROBIZ");

                statusLabel.setTextFill(
                                Color.rgb(
                                                160,
                                                190,
                                                170,
                                                0.82));

                statusLabel.setFont(
                                Font.font(
                                                "Segoe UI",
                                                FontWeight.BOLD,
                                                9));

                statusLabel.setStyle(
                                "-fx-letter-spacing: 1.8px;");

                box.getChildren().addAll(
                                track,
                                statusLabel);

                return box;
        }

        // =========================================================
        // SPACER
        // =========================================================

        private Region createSpacer(
                        double height) {

                Region r = new Region();

                r.setPrefHeight(
                                height);

                return r;
        }

        // =========================================================
        // PARTICLES
        // =========================================================

        private void initParticles() {

                spores.clear();

                for (int i = 0; i < 55; i++) {

                        spores.add(
                                        new AmbientSpore(
                                                        rng.nextDouble()
                                                                        * WIDTH,

                                                        rng.nextDouble()
                                                                        * HEIGHT,

                                                        rng.nextDouble()
                                                                        * 2
                                                                        + 0.6,

                                                        rng.nextDouble()
                                                                        * 0.6
                                                                        + 0.15));
                }
        }

        // =========================================================
        // PARTICLE ENGINE
        // =========================================================

        private void startLoop(
                        GraphicsContext gc) {

                engineTimer = new AnimationTimer() {

                        private long last = 0;

                        @Override
                        public void handle(
                                        long now) {

                                if (last == 0) {

                                        last = now;

                                        return;
                                }

                                double dt = (now - last)
                                                / 1e9;

                                last = now;

                                currentX += (targetX - currentX)
                                                * 0.06;

                                currentY += (targetY - currentY)
                                                * 0.06;

                                gc.clearRect(
                                                0,
                                                0,
                                                WIDTH,
                                                HEIGHT);

                                ripples.removeIf(
                                                EnergyRipple::isDead);

                                for (EnergyRipple ripple : ripples) {

                                        ripple.update(dt);

                                        ripple.render(gc);
                                }

                                for (AmbientSpore spore : spores) {

                                        spore.update(
                                                        dt,
                                                        currentX,
                                                        currentY);

                                        spore.render(gc);
                                }
                        }
                };

                engineTimer.start();
        }

        // =========================================================
        // PARALLAX
        // =========================================================

        private void setupParallax(
                        StackPane root,
                        VBox hero,
                        Pane atmosphere,
                        Pane landscape) {

                root.setOnMouseMoved(
                                event -> {

                                        double nx = event.getSceneX()
                                                        / WIDTH
                                                        - 0.5;

                                        double ny = event.getSceneY()
                                                        / HEIGHT
                                                        - 0.5;

                                        targetX = nx * 25;

                                        targetY = ny * 16;

                                        hero.setTranslateX(
                                                        currentX * 0.35);

                                        hero.setTranslateY(
                                                        currentY * 0.25);

                                        atmosphere.setTranslateX(
                                                        currentX * 0.12);

                                        atmosphere.setTranslateY(
                                                        currentY * 0.12);

                                        landscape.setTranslateX(
                                                        currentX * 0.08);

                                        landscape.setTranslateY(
                                                        currentY * 0.05);
                                });
        }

        // =========================================================
        // CHOREOGRAPHY
        // =========================================================

        private void runChoreography(
                        StackPane root,
                        VBox hero,
                        Runnable onComplete) {

                Label badge = (Label) hero.getProperties()
                                .get("topBadge");

                Group logo = (Group) hero.getProperties()
                                .get("logoGroup");

                Label title = (Label) hero.getProperties()
                                .get("brandTitle");

                Label sloganLabel = (Label) hero.getProperties()
                                .get("slogan");

                VBox categories = (VBox) hero.getProperties()
                                .get("categoryContainer");

                VBox hud = (VBox) hero.getProperties()
                                .get("hud");

                // -----------------------------------------------------
                // ROOT
                // -----------------------------------------------------

                FadeTransition rootIn = new FadeTransition(
                                Duration.millis(650),
                                root);

                rootIn.setFromValue(0);
                rootIn.setToValue(1);

                // -----------------------------------------------------
                // BADGE
                // -----------------------------------------------------

                FadeTransition badgeFade = new FadeTransition(
                                Duration.millis(450),
                                badge);

                badgeFade.setToValue(1);

                // -----------------------------------------------------
                // LOGO
                // -----------------------------------------------------

                FadeTransition logoFade = new FadeTransition(
                                Duration.millis(650),
                                logo);

                logoFade.setToValue(1);

                ScaleTransition logoScale = new ScaleTransition(
                                Duration.millis(750),
                                logo);

                logoScale.setFromX(0.55);
                logoScale.setFromY(0.55);

                logoScale.setToX(1);
                logoScale.setToY(1);

                logoScale.setInterpolator(
                                Interpolator.SPLINE(
                                                0.2,
                                                0.85,
                                                0.25,
                                                1));

                // -----------------------------------------------------
                // TITLE
                // -----------------------------------------------------

                FadeTransition titleFade = new FadeTransition(
                                Duration.millis(550),
                                title);

                titleFade.setToValue(1);

                TranslateTransition titleRise = new TranslateTransition(
                                Duration.millis(550),
                                title);

                titleRise.setToY(0);

                titleRise.setInterpolator(
                                Interpolator.EASE_OUT);

                // -----------------------------------------------------
                // SLOGAN
                // -----------------------------------------------------

                FadeTransition sloganFade = new FadeTransition(
                                Duration.millis(450),
                                sloganLabel);

                sloganFade.setToValue(1);

                TranslateTransition sloganRise = new TranslateTransition(
                                Duration.millis(450),
                                sloganLabel);

                sloganRise.setToY(0);

                sloganRise.setInterpolator(
                                Interpolator.EASE_OUT);

                // -----------------------------------------------------
                // CATEGORY CONTAINER
                // -----------------------------------------------------

                FadeTransition categoryFade = new FadeTransition(
                                Duration.millis(500),
                                categories);

                categoryFade.setToValue(1);

                TranslateTransition categoryRise = new TranslateTransition(
                                Duration.millis(500),
                                categories);

                categoryRise.setToY(0);

                categoryRise.setInterpolator(
                                Interpolator.EASE_OUT);

                // -----------------------------------------------------
                // HUD
                // -----------------------------------------------------

                FadeTransition hudFade = new FadeTransition(
                                Duration.millis(400),
                                hud);

                hudFade.setToValue(1);

                // -----------------------------------------------------
                // PROGRESS
                // -----------------------------------------------------

                Timeline progress = createProgressTimeline();

                // -----------------------------------------------------
                // OUT
                // -----------------------------------------------------

                FadeTransition rootOut = new FadeTransition(
                                Duration.millis(600),
                                root);

                rootOut.setToValue(0);

                rootOut.setOnFinished(
                                event -> {

                                        if (engineTimer != null) {

                                                engineTimer.stop();
                                        }

                                        if (onComplete != null) {

                                                onComplete.run();
                                        }
                                });

                // -----------------------------------------------------
                // MASTER
                // -----------------------------------------------------

                SequentialTransition master = new SequentialTransition(

                                rootIn,

                                new PauseTransition(
                                                Duration.millis(100)),

                                badgeFade,

                                new ParallelTransition(
                                                logoFade,
                                                logoScale),

                                new ParallelTransition(
                                                titleFade,
                                                titleRise),

                                new ParallelTransition(
                                                sloganFade,
                                                sloganRise),

                                new ParallelTransition(
                                                categoryFade,
                                                categoryRise),

                                hudFade,

                                progress,

                                new PauseTransition(
                                                Duration.millis(300)),

                                rootOut);

                master.play();
        }
        // =========================================================
        // CATEGORY ANIMATIONS
        // =========================================================

        private void playCategoryAnimations() {

                animateCategory(poultryIcon, 0);
                animateCategory(dairyIcon, 180);
                animateCategory(mushroomIcon, 360);
                animateCategory(cropsIcon, 540);
                animateCategory(fishIcon, 720);
        }

        // =========================================================
        // SINGLE CATEGORY ANIMATION
        // =========================================================

        private void animateCategory(
                        StackPane icon,
                        double delay) {

                if (icon == null) {
                        return;
                }

                // Start small and invisible
                icon.setOpacity(0);
                icon.setScaleX(0.25);
                icon.setScaleY(0.25);

                // -----------------------------------------------------
                // FADE IN
                // -----------------------------------------------------

                FadeTransition fade = new FadeTransition(
                                Duration.millis(500),
                                icon);

                fade.setFromValue(0);
                fade.setToValue(1);

                // -----------------------------------------------------
                // BIG GROW / POP
                // -----------------------------------------------------

                ScaleTransition scale = new ScaleTransition(
                                Duration.millis(750),
                                icon);

                scale.setFromX(0.25);
                scale.setFromY(0.25);

                scale.setToX(1.0);
                scale.setToY(1.0);

                scale.setInterpolator(
                                Interpolator.SPLINE(
                                                0.15,
                                                0.85,
                                                0.20,
                                                1.15));

                // -----------------------------------------------------
                // SMALL BOUNCE
                // -----------------------------------------------------

                ScaleTransition bounce = new ScaleTransition(
                                Duration.millis(180),
                                icon);

                bounce.setFromX(1.0);
                bounce.setFromY(1.0);

                bounce.setToX(1.08);
                bounce.setToY(1.08);

                bounce.setAutoReverse(true);

                bounce.setCycleCount(2);

                bounce.setInterpolator(
                                Interpolator.EASE_BOTH);

                // -----------------------------------------------------
                // COMPLETE ENTRANCE
                // -----------------------------------------------------

                ParallelTransition entrance = new ParallelTransition(
                                fade,
                                scale);

                SequentialTransition sequence = new SequentialTransition(
                                new PauseTransition(
                                                Duration.millis(delay)),

                                entrance,

                                new PauseTransition(
                                                Duration.millis(100)),

                                bounce);

                sequence.play();

                // -----------------------------------------------------
                // CONTINUOUS BREATHING
                // -----------------------------------------------------

                sequence.setOnFinished(
                                event -> {

                                        ScaleTransition breathing = new ScaleTransition(
                                                        Duration.millis(1500),
                                                        icon);

                                        breathing.setFromX(1.0);
                                        breathing.setFromY(1.0);

                                        breathing.setToX(1.05);
                                        breathing.setToY(1.05);

                                        breathing.setAutoReverse(true);

                                        breathing.setCycleCount(
                                                        Animation.INDEFINITE);

                                        breathing.setInterpolator(
                                                        Interpolator.EASE_BOTH);

                                        breathing.play();
                                });
        }

        // =========================================================
        // PROGRESS
        // =========================================================

        private Timeline createProgressTimeline() {

                Timeline timeline = new Timeline(

                                new KeyFrame(
                                                Duration.ZERO,

                                                new KeyValue(
                                                                progressFill
                                                                                .widthProperty(),
                                                                0)),

                                new KeyFrame(
                                                Duration.millis(450),

                                                event -> statusLabel.setText(
                                                                "LOADING FARMING KNOWLEDGE")),

                                new KeyFrame(
                                                Duration.millis(950),

                                                event -> statusLabel.setText(
                                                                "PREPARING AI ADVISOR")),

                                new KeyFrame(
                                                Duration.millis(1450),

                                                event -> statusLabel.setText(
                                                                "SYNCING AGROBIZ SERVICES")),

                                new KeyFrame(
                                                Duration.millis(2100),

                                                event -> statusLabel.setText(
                                                                "PREPARING YOUR FARMING EXPERIENCE")),

                                new KeyFrame(
                                                Duration.millis(2700),
                                                event -> statusLabel.setText(
                                                                "READY")),

                                new KeyFrame(
                                                Duration.millis(3000),

                                                new KeyValue(
                                                                progressFill.widthProperty(),
                                                                250,
                                                                Interpolator.SPLINE(
                                                                                0.1,
                                                                                0.7,
                                                                                0.1,
                                                                                1))));

                // -----------------------------------------------------
                // SHIMMER
                // -----------------------------------------------------

                TranslateTransition sweep = new TranslateTransition(
                                Duration.millis(900),
                                shimmerSweep);

                sweep.setFromX(-28);

                sweep.setToX(255);

                sweep.setCycleCount(4);

                // IMPORTANT:
                // JavaFX uses EASE_BOTH, not EASE_IN_OUT.
                sweep.setInterpolator(
                                Interpolator.EASE_BOTH);

                sweep.play();

                return timeline;
        }

        // =========================================================
        // AMBIENT PARTICLE
        // =========================================================

        private static class AmbientSpore {

                double x;
                double y;

                double r;
                double depth;

                double alpha;

                double vx;
                double vy;

                double phase;

                AmbientSpore(
                                double x,
                                double y,
                                double r,
                                double depth) {

                        this.x = x;
                        this.y = y;

                        this.r = r;
                        this.depth = depth;

                        this.alpha = 0.12
                                        + depth * 0.42;

                        this.vx = (Math.random() - 0.5)
                                        * 8;

                        this.vy = -7
                                        - depth * 13;

                        this.phase = Math.random()
                                        * Math.PI
                                        * 2;
                }

                void update(
                                double dt,
                                double px,
                                double py) {

                        phase += dt * 1.8;

                        x += (vx
                                        + Math.sin(
                                                        phase) * 4
                                        + px
                                                        * depth
                                                        * 0.12)
                                        * dt;

                        y += (vy
                                        + py
                                                        * depth
                                                        * 0.08)
                                        * dt;

                        if (y < -10) {

                                y = HEIGHT + 10;

                                x = Math.random()
                                                * WIDTH;
                        }

                        if (x < -10) {
                                x = WIDTH + 10;
                        }

                        if (x > WIDTH + 10) {
                                x = -10;
                        }
                }

                void render(
                                GraphicsContext gc) {

                        double a = alpha
                                        * (0.65
                                                        + 0.35
                                                                        * Math.sin(
                                                                                        phase));

                        gc.setFill(
                                        Color.rgb(
                                                        182,
                                                        244,
                                                        90,
                                                        Math.max(
                                                                        0,
                                                                        a)));

                        gc.fillOval(
                                        x - r,
                                        y - r,
                                        r * 2,
                                        r * 2);
                }
        }

        // =========================================================
        // ENERGY RIPPLE
        // =========================================================

        private static class EnergyRipple {

                double x;
                double y;

                double radius = 5;

                double alpha = 0.6;

                Color color;

                EnergyRipple(
                                double x,
                                double y,
                                Color color) {

                        this.x = x;
                        this.y = y;

                        this.color = color;
                }

                void update(
                                double dt) {

                        radius += dt * 100;

                        alpha -= dt * 0.5;
                }

                boolean isDead() {

                        return alpha <= 0;
                }

                void render(
                                GraphicsContext gc) {

                        if (alpha <= 0) {
                                return;
                        }

                        gc.setStroke(
                                        Color.rgb(
                                                        (int) (color.getRed()
                                                                        * 255),

                                                        (int) (color.getGreen()
                                                                        * 255),

                                                        (int) (color.getBlue()
                                                                        * 255),

                                                        Math.max(
                                                                        0,
                                                                        alpha)));

                        gc.setLineWidth(
                                        1.8);

                        gc.strokeOval(
                                        x - radius,
                                        y - radius,
                                        radius * 2,
                                        radius * 2);
                }
        }
}