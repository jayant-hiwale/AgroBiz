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

    private static final double WIDTH = 1368;
    private static final double HEIGHT = 768;

    // Palette & Tokens
    private static final Color VOID_BLACK   = Color.web("#010904");
    private static final Color FLORA_GREEN  = Color.web("#7bed58");
    private static final Color BUD_LUMEN    = Color.web("#baff94");
    private static final Color PURE_WHITE   = Color.web("#FFFFFF");
    private static final Color MIST_TEXT    = Color.web("#809789");

    private Scene splashScene;
    private AnimationTimer engineTimer;
    private final List<AmbientSpore> spores = new ArrayList<>();
    private final List<EnergyRipple> ripples = new ArrayList<>();
    private final Random rng = new Random();

    // Parallax Interpolation
    private double targetX = 0, targetY = 0;
    private double currentX = 0, currentY = 0;

    // Geometry handles
    private CubicCurve stemCurve;
    private Group leftLeaf;
    private Group rightLeaf;
    private Group budApex;
    private Label statusLabel;
    private Rectangle progressFill;
    private Rectangle shimmerSweep;

    public Scene getSplashScene(Runnable onComplete) {
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: #010904;");

        // 1. Atmosphere Layers
        Region backdrop = buildBackdrop();
        Pane volumetric = buildVolumetricGlow();

        // 2. High-Performance Spore/Aura Canvas
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        canvas.setCache(true);
        canvas.setCacheHint(CacheHint.SPEED);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // 3. Hero Visual Layout
        VBox hero = buildHeroScaffold();

        // 4. Parallax Rigging
        setupParallax(root, hero, volumetric);

        root.getChildren().addAll(backdrop, volumetric, canvas, hero);

        splashScene = new Scene(root, WIDTH, HEIGHT);
        splashScene.setFill(VOID_BLACK);

        initParticles();
        startLoop(gc);
        runChoreography(root, hero, onComplete);

        return splashScene;
    }

    private Region buildBackdrop() {
        Region bg = new Region();
        bg.setPrefSize(WIDTH, HEIGHT);
        RadialGradient grad = new RadialGradient(
                0, 0, 0.5, 0.42, 0.7, true, CycleMethod.NO_CYCLE,
                new Stop(0.00, Color.web("#061c10")),
                new Stop(0.45, Color.web("#031209")),
                new Stop(0.85, Color.web("#010904")),
                new Stop(1.00, Color.web("#000402"))
        );
        bg.setBackground(new Background(new BackgroundFill(grad, CornerRadii.EMPTY, null)));
        return bg;
    }

    private Pane buildVolumetricGlow() {
        Pane pane = new Pane();
        pane.setMouseTransparent(true);

        Circle coreHalo = new Circle(WIDTH / 2.0, HEIGHT * 0.38, 220);
        coreHalo.setFill(new RadialGradient(
                0, 0, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE,
                new Stop(0.0, Color.rgb(123, 237, 88, 0.16)),
                new Stop(0.4, Color.rgb(123, 237, 88, 0.05)),
                new Stop(1.0, Color.TRANSPARENT)
        ));
        coreHalo.setEffect(new GaussianBlur(70));

        pane.getChildren().add(coreHalo);
        return pane;
    }

    private VBox buildHeroScaffold() {
        VBox hero = new VBox();
        hero.setAlignment(Pos.CENTER);
        hero.setSpacing(0);
        hero.setPickOnBounds(false);

        // 1. Top Subtitle
        Label topBadge = new Label("S M A R T   A G R I C U L T U R E");
        topBadge.setTextFill(FLORA_GREEN);
        topBadge.setFont(Font.font("Segoe UI", FontWeight.BOLD, 12.5));
        topBadge.setStyle("-fx-letter-spacing: 4.5px;");
        topBadge.setOpacity(0);
        topBadge.setEffect(new DropShadow(15, Color.rgb(123, 237, 88, 0.5)));

        // 2. Procedural Sprout Icon
        Group sprout = buildSprout();

        // 3. Brand Text
        Label brandTitle = new Label("Agro Biz");
        brandTitle.setTextFill(PURE_WHITE);
        brandTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 50));
        brandTitle.setOpacity(0);
        brandTitle.setTranslateY(18);
        brandTitle.setEffect(new DropShadow(25, Color.rgb(0, 0, 0, 0.85)));

        Label slogan = new Label("Grow Better. Live Better.");
        slogan.setTextFill(MIST_TEXT);
        slogan.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 15.5));
        slogan.setOpacity(0);
        slogan.setTranslateY(12);

        // 4. Loading Bar HUD
        VBox hud = buildMinimalHUD();
        hud.setOpacity(0);

        hero.getChildren().addAll(
                topBadge,
                createSpacer(24),
                sprout,
                createSpacer(26),
                brandTitle,
                createSpacer(8),
                slogan,
                createSpacer(36),
                hud
        );

        hero.getProperties().put("topBadge", topBadge);
        hero.getProperties().put("sprout", sprout);
        hero.getProperties().put("brandTitle", brandTitle);
        hero.getProperties().put("slogan", slogan);
        hero.getProperties().put("hud", hud);

        return hero;
    }

    private Group buildSprout() {
        Group root = new Group();

        Ellipse base = new Ellipse(0, 80, 50, 6.5);
        base.setFill(Color.web("#062012"));
        base.setEffect(new GaussianBlur(4));

        stemCurve = new CubicCurve(0, 80, 0, 80, 0, 80, 0, 80);
        stemCurve.setStroke(FLORA_GREEN);
        stemCurve.setStrokeWidth(3.8);
        stemCurve.setStrokeLineCap(StrokeLineCap.ROUND);
        stemCurve.setFill(null);

        leftLeaf = new Group(new Ellipse(0, 0, 18, 9));
        ((Ellipse) leftLeaf.getChildren().get(0)).setFill(FLORA_GREEN);
        leftLeaf.setLayoutX(-27);
        leftLeaf.setLayoutY(16);
        leftLeaf.setRotate(-32);
        leftLeaf.setScaleX(0);
        leftLeaf.setScaleY(0);

        rightLeaf = new Group(new Ellipse(0, 0, 17, 8.5));
        ((Ellipse) rightLeaf.getChildren().get(0)).setFill(FLORA_GREEN);
        rightLeaf.setLayoutX(25);
        rightLeaf.setLayoutY(-22);
        rightLeaf.setRotate(26);
        rightLeaf.setScaleX(0);
        rightLeaf.setScaleY(0);

        budApex = new Group();
        Circle aura = new Circle(0, 0, 24, Color.rgb(186, 255, 148, 0.25));
        aura.setEffect(new GaussianBlur(16));
        Circle budCore = new Circle(0, 0, 7.8, BUD_LUMEN);

        DropShadow budGlow = new DropShadow(30, Color.rgb(186, 255, 148, 0.9));
        budGlow.setInput(new Glow(0.5));
        budCore.setEffect(budGlow);

        budApex.getChildren().addAll(aura, budCore);
        budApex.setLayoutY(80);
        budApex.setOpacity(0);

        root.getChildren().addAll(base, stemCurve, leftLeaf, rightLeaf, budApex);
        return root;
    }

    private VBox buildMinimalHUD() {
        VBox box = new VBox(10);
        box.setAlignment(Pos.CENTER);

        StackPane track = new StackPane();
        track.setAlignment(Pos.CENTER_LEFT);
        track.setPrefSize(160, 2.5);
        track.setMaxWidth(160);

        Rectangle trackBg = new Rectangle(160, 2.5);
        trackBg.setArcWidth(2);
        trackBg.setArcHeight(2);
        trackBg.setFill(Color.rgb(12, 34, 22, 0.8));

        progressFill = new Rectangle(0, 2.5);
        progressFill.setArcWidth(2);
        progressFill.setArcHeight(2);
        progressFill.setFill(new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#389b25")),
                new Stop(1, BUD_LUMEN)));
        progressFill.setEffect(new Glow(0.6));

        shimmerSweep = new Rectangle(20, 2.5);
        shimmerSweep.setFill(Color.rgb(255, 255, 255, 0.6));
        shimmerSweep.setBlendMode(BlendMode.SCREEN);
        shimmerSweep.setEffect(new GaussianBlur(2.5));
        shimmerSweep.setTranslateX(-20);

        track.getChildren().addAll(trackBg, progressFill, shimmerSweep);

        statusLabel = new Label("INITIALIZING ENVIRONMENT");
        statusLabel.setTextFill(Color.rgb(120, 155, 135, 0.75));
        statusLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 9));
        statusLabel.setStyle("-fx-letter-spacing: 2px;");

        box.getChildren().addAll(track, statusLabel);
        return box;
    }

    private Region createSpacer(double height) {
        Region r = new Region();
        r.setPrefHeight(height);
        return r;
    }

    private void initParticles() {
        for (int i = 0; i < 45; i++) {
            spores.add(new AmbientSpore(
                    rng.nextDouble() * WIDTH,
                    rng.nextDouble() * HEIGHT,
                    rng.nextDouble() * 2.0 + 0.8,
                    rng.nextDouble() * 0.5 + 0.2
            ));
        }
    }

    private void startLoop(GraphicsContext gc) {
        engineTimer = new AnimationTimer() {
            private long last = 0;

            @Override
            public void handle(long now) {
                if (last == 0) { last = now; return; }
                double dt = (now - last) / 1e9;
                last = now;

                currentX += (targetX - currentX) * 0.06;
                currentY += (targetY - currentY) * 0.06;

                gc.clearRect(0, 0, WIDTH, HEIGHT);

                ripples.removeIf(EnergyRipple::isDead);
                for (EnergyRipple r : ripples) {
                    r.update(dt);
                    r.render(gc);
                }

                for (AmbientSpore s : spores) {
                    s.update(dt, currentX, currentY);
                    s.render(gc);
                }
            }
        };
        engineTimer.start();
    }

    private void setupParallax(StackPane root, VBox hero, Pane bloom) {
        root.setOnMouseMoved(e -> {
            double nx = (e.getSceneX() / WIDTH) - 0.5;
            double ny = (e.getSceneY() / HEIGHT) - 0.5;
            targetX = nx * 30.0;
            targetY = ny * 20.0;

            hero.setTranslateX(currentX * 0.4);
            hero.setTranslateY(currentY * 0.4);

            bloom.setTranslateX(currentX * 0.15);
            bloom.setTranslateY(currentY * 0.15);
        });
    }

    private void runChoreography(StackPane root, VBox hero, Runnable onComplete) {
        Label topBadge = (Label) hero.getProperties().get("topBadge");
        Group sprout = (Group) hero.getProperties().get("sprout");
        Label brandTitle = (Label) hero.getProperties().get("brandTitle");
        Label slogan = (Label) hero.getProperties().get("slogan");
        VBox hud = (VBox) hero.getProperties().get("hud");

        FadeTransition rootIn = new FadeTransition(Duration.millis(600), root);
        rootIn.setFromValue(0);
        rootIn.setToValue(1);

        // Procedural Stem Morph
        budApex.setOpacity(1);
        Timeline sproutGrow = new Timeline(
                new KeyFrame(Duration.millis(1100),
                        new KeyValue(stemCurve.controlX1Property(), -5, Interpolator.EASE_OUT),
                        new KeyValue(stemCurve.controlY1Property(), 20, Interpolator.EASE_OUT),
                        new KeyValue(stemCurve.controlX2Property(), 5, Interpolator.EASE_OUT),
                        new KeyValue(stemCurve.controlY2Property(), -30, Interpolator.EASE_OUT),
                        new KeyValue(stemCurve.endXProperty(), 0, Interpolator.EASE_OUT),
                        new KeyValue(stemCurve.endYProperty(), -80, Interpolator.EASE_OUT),
                        new KeyValue(budApex.layoutYProperty(), -80, Interpolator.EASE_OUT)
                )
        );

        ScaleTransition leftLeafGrow = new ScaleTransition(Duration.millis(500), leftLeaf);
        leftLeafGrow.setToX(1); leftLeafGrow.setToY(1);
        leftLeafGrow.setInterpolator(Interpolator.SPLINE(0.1, 0.8, 0.2, 1));
        leftLeafGrow.setDelay(Duration.millis(450));

        ScaleTransition rightLeafGrow = new ScaleTransition(Duration.millis(500), rightLeaf);
        rightLeafGrow.setToX(1); rightLeafGrow.setToY(1);
        rightLeafGrow.setInterpolator(Interpolator.SPLINE(0.1, 0.8, 0.2, 1));
        rightLeafGrow.setDelay(Duration.millis(650));

        sproutGrow.setOnFinished(e -> {
            ripples.add(new EnergyRipple(WIDTH / 2.0, HEIGHT * 0.38 - 80, FLORA_GREEN));
            startBreathing(budApex);
        });

        FadeTransition badgeFade = new FadeTransition(Duration.millis(500), topBadge);
        badgeFade.setToValue(1);

        FadeTransition brandFade = new FadeTransition(Duration.millis(600), brandTitle);
        brandFade.setToValue(1);
        TranslateTransition brandRise = new TranslateTransition(Duration.millis(600), brandTitle);
        brandRise.setToY(0);
        brandRise.setInterpolator(Interpolator.EASE_OUT);

        FadeTransition sloganFade = new FadeTransition(Duration.millis(500), slogan);
        sloganFade.setToValue(1);
        TranslateTransition sloganRise = new TranslateTransition(Duration.millis(500), slogan);
        sloganRise.setToY(0);
        sloganRise.setInterpolator(Interpolator.EASE_OUT);

        FadeTransition hudFade = new FadeTransition(Duration.millis(400), hud);
        hudFade.setToValue(1);

        Timeline progressRun = createProgressTimeline();

        FadeTransition rootOut = new FadeTransition(Duration.millis(600), root);
        rootOut.setToValue(0);
        rootOut.setOnFinished(e -> {
            if (engineTimer != null) engineTimer.stop();
            if (onComplete != null) onComplete.run();
        });

        SequentialTransition master = new SequentialTransition(
                rootIn,
                new ParallelTransition(sproutGrow, leftLeafGrow, rightLeafGrow),
                badgeFade,
                new ParallelTransition(brandFade, brandRise),
                new ParallelTransition(sloganFade, sloganRise),
                hudFade,
                progressRun,
                new PauseTransition(Duration.millis(350)),
                rootOut
        );

        master.play();
    }

    private void startBreathing(Group bud) {
        ScaleTransition pulse = new ScaleTransition(Duration.millis(1600), bud);
        pulse.setFromX(1.0); pulse.setFromY(1.0);
        pulse.setToX(1.18); pulse.setToY(1.18);
        pulse.setAutoReverse(true);
        pulse.setCycleCount(Animation.INDEFINITE);
        pulse.setInterpolator(Interpolator.EASE_BOTH);
        pulse.play();
    }

    private Timeline createProgressTimeline() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(progressFill.widthProperty(), 0)),
                new KeyFrame(Duration.millis(2600), new KeyValue(progressFill.widthProperty(), 160, Interpolator.SPLINE(0.1, 0.7, 0.1, 1))),
                new KeyFrame(Duration.millis(700), e -> statusLabel.setText("CALIBRATING SENSORS")),
                new KeyFrame(Duration.millis(1500), e -> statusLabel.setText("SYNCING DATA ENGINE")),
                new KeyFrame(Duration.millis(2200), e -> statusLabel.setText("READY"))
        );

        TranslateTransition sweep = new TranslateTransition(Duration.millis(900), shimmerSweep);
        sweep.setFromX(-20);
        sweep.setToX(165);
        sweep.setCycleCount(3);
        sweep.setInterpolator(Interpolator.EASE_IN);
        sweep.play();

        return timeline;
    }

    private static class AmbientSpore {
        double x, y, r, depth, alpha, vx, vy, phase;

        AmbientSpore(double x, double y, double r, double depth) {
            this.x = x; this.y = y; this.r = r; this.depth = depth;
            this.alpha = 0.2 + depth * 0.5;
            this.vx = (Math.random() - 0.5) * 10;
            this.vy = -14 - depth * 18;
            this.phase = Math.random() * Math.PI * 2;
        }

        void update(double dt, double px, double py) {
            phase += dt * 2;
            x += (vx + Math.sin(phase) * 6 + px * depth * 0.15) * dt;
            y += (vy + py * depth * 0.15) * dt;
            if (y < -10) { y = HEIGHT + 10; x = Math.random() * WIDTH; }
            if (x < -10) x = WIDTH + 10;
            if (x > WIDTH + 10) x = -10;
        }

        void render(GraphicsContext gc) {
            double a = alpha * (0.6 + 0.4 * Math.sin(phase));
            gc.setFill(Color.rgb(186, 255, 148, a));
            gc.fillOval(x - r, y - r, r * 2, r * 2);
        }
    }

    private static class EnergyRipple {
        double x, y, radius = 5, alpha = 0.6;
        Color color;

        EnergyRipple(double x, double y, Color color) {
            this.x = x; this.y = y; this.color = color;
        }

        void update(double dt) {
            radius += dt * 110;
            alpha -= dt * 0.55;
        }

        boolean isDead() { return alpha <= 0; }

        void render(GraphicsContext gc) {
            if (alpha <= 0) return;
            gc.setStroke(Color.rgb((int)(color.getRed()*255), (int)(color.getGreen()*255), (int)(color.getBlue()*255), Math.max(0, alpha)));
            gc.setLineWidth(2.0);
            gc.strokeOval(x - radius, y - radius, radius * 2, radius * 2);
        }
    }
}