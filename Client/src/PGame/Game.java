package PGame;

import Gui.BackToMenu;
import Gui.LevelWindow;
import Gui.MainWindow;
import PServer.Client;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Klasa odpowiedzialna za grę
 */

public class Game extends Canvas implements Runnable {

    /**
     * Wątek odpowiedzialny za game-loop
     */

    private Thread thread;

    /**
     * Deklaracja obiektu GameManager
     */

    static private ObjectManager manager;

    /**
     * Zmienna przechowująca czy gra jest aktywna
     */

    private boolean active = false;

    /**
     * Deklaracja obiektu Terrain
     */

    Terrain terrain;

    /**
     * Deklaracja obiektu LandingPlatform
     */

    LandingPlatform landingPlatformGreen;

    /**
     * Deklaracja obiektu LandingPlatform
     */

    LandingPlatform landingPlatformRed;

    /**
     * Shape ktory bedzie przechowywal nasz teren do wykrywania kolizji
     */

    Shape terrainShape;

    /**
     * Shape ktory bedzie przechowywal ladowisko do wykrywania kolizji
     */

    Shape landingShapeGreen;

    /**
     * Shape ktory bedzie przechowywal ladowisko do wykrywania kolizji
     */

    Shape landingShapeRed;

    /**
     * Rectangle2D ktory bedzie przechowywal statek do wyrkywania kolizji
     */

    Rectangle2D player;

    /**
     * Rectangle2D ktory bedzie przechowywal kanister do wykrywania kolizji
     */

    Rectangle2D fuelShape;

    /**
     * Index przechowujący aktualny numer levelu
     */

    int index = 1;

    /**
     * Całkowita liczba zdobytych punktów
     */

    int totalScore = 0;


    /**
     * Zmienna przechowująca czy pauza jest aktywna
     */

    public boolean paused = false;

    /**
     * Deklaracja backWindow
     */

    BackToMenu backWindow;

    /**
     * Deklaracja mainWindow
     */

    MainWindow mainWindow;

    /**
     * Flaga mówiąca o końcu gry
     */

    boolean over = false;

    /**
     * Konstruktor klasy Game
     */

    public Game(MainWindow mainWindow) {

        this.mainWindow = mainWindow;

        manager = new ObjectManager();
        this.addKeyListener(new KeyInput(manager, this));
        this.mainWindow = mainWindow;


    }

    /**
     * Metoda odpowiedzialna za uruchamianie gry na oddzielnym wątku
     */

    public void start() {
        if (active)
            return;

        active = true;
        thread = new Thread(this);
        thread.start();

    }

    /**
     * Metoda odpowiedzialna za inicjalizację gry
     */

    private void init() throws IOException {
        if (Client.isOnline) {
            FileManager.loadLevelFromServer(index);
           // FileManager.loadLevel(index);
        } else {
            FileManager.loadLevel(index);
            FileManager.loadConfig();

        }

        Lander lander = new Lander(FileManager.landerX, FileManager.landerY, this);
        Fuel fuel = new Fuel(FileManager.fuelX, FileManager.fuelY, this);


        terrain = new Terrain();
        landingPlatformGreen = new LandingPlatform(FileManager.landing1X, FileManager.landing1Y, FileManager.landing1Width, FileManager.landing1Height, FileManager.landing1Multiplier, "Client/data/landing pad/Landing pad green.png");
        landingPlatformRed = new LandingPlatform(FileManager.landing2X, FileManager.landing2Y, FileManager.landing2Width, FileManager.landing2Height, FileManager.landing2Multiplier, "Client/data/landing pad/Landing pad red.png");

        landingShapeGreen = new Rectangle(FileManager.landingShape1X, FileManager.landingShape1Y, FileManager.landingShape1Width, FileManager.landingShape1Height);
        landingShapeRed = new Rectangle(FileManager.landingShape2X, FileManager.landingShape2Y, FileManager.landingShape2Width, FileManager.landingShape2Height);

        manager.addObject(lander, fuel);

    }

//Game loop + frame control system

    /**
     * Metoda game-loop
     */

    public void run() {
        int MAX_FPS = 60;
        long startTime;
        long timeMillis = 1000 / MAX_FPS;
        long waitTime;
        long totalTime = 0;
        long targetTime = 1000 / MAX_FPS;
        long averageFPS;
        int frameCount = 0;

        try {

            init();

        } catch (IOException e) {
            e.printStackTrace();
        }

        this.requestFocus();

        while (active && mainWindow.gameWindow.exists) {
            // Startowy czas
            startTime = System.nanoTime();

            try {

                synchronized (this) {

                    if (mainWindow.gameWindow.exists) {
                        render();

                        if (!paused && !manager.getTmpObject().hasLanded && !manager.getTmpObject().hasCrashed) {
                            update();
                        }
                    }

                }

            } catch (Exception | Error ignored) {
                //e.printStackTrace();

            }

            // Liczymy ile czasu trwa 1 klatka obrazu
            timeMillis = (System.nanoTime() - startTime) / 1000000;
            // Jeżeli wykona się za szybko to każemu mu poczekać
            waitTime = targetTime - timeMillis;
            try {
                if (waitTime > 0)
                    Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if (frameCount == MAX_FPS) {
                // Wyznaczamy śrędnią liczbę klatek
                averageFPS = 1000 / ((totalTime / frameCount) / 1000000);
                // System.out.println("FPS " + averageFPS);
                frameCount = 0;
                totalTime = 0;
            }
        }
    }

    /**
     * Metoda odpowiedzialna uaktualnianie parametrów
     */

    void update() {

        manager.update();

    }

    /**
     * Metoda odpowiedzialna za pauze
     */

    void displayPause(Graphics g) {


        Color c = new Color(235, 157, 155);
        g.setColor(c);
        Font f = new Font("Helvetica", Font.BOLD, 40);
        g.setFont(f);
        g.drawString("PAUZA.", 1080, 70);
    }

    /**
     * Metoda odpowiedzialna za wyświetlenie końca gry
     */

    void displayCrash(Graphics g) {


        Color c = new Color(235, 157, 155);
        g.setColor(c);
        Font f = new Font("Helvetica", Font.BOLD, 40);
        g.setFont(f);
        g.drawString("KONIEC GRY.", 600, 70);
    }

    /**
     * Metoda odpowiedzialna za wyświetlenie sukcesu
     */

    void displaySucces(Graphics g) {


        Color c = new Color(235, 157, 155);
        g.setColor(c);
        Font f = new Font("Helvetica", Font.BOLD, 40);
        g.setFont(f);
        g.drawString("SUKCES!", 680, 70);
    }

    /**
     * Metoda odpowiedzialna za renderowanie i skalowanie
     */

    void render() throws InterruptedException, IOException, IllegalStateException {

        //getBufferStrategy zwraca nam na rzecz this
        // czy aktualna strategie buforowania jesli zadnej nie stworzylismy zwroci null
        // Aby nie zwracał null musimy dać mu konkretny obszar buforowania
        BufferStrategy bs = this.getBufferStrategy();


        if (bs == null) {
            this.createBufferStrategy(FileManager.numBuffers);
            return;
        }


        // tworzymy nowa ramke obrazu zakrywajac starą
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();

        terrainShape = new Polygon(FileManager.terrainX, FileManager.terrainY, FileManager.terrainX.length);
        player = new Rectangle2D.Double(manager.getTmpObject().coords.x, manager.getTmpObject().coords.y, manager.getTmpObject().getWidth(), manager.getTmpObject().getHeight());        //wspolrzedne nieintowe dlatego .Double()     co wywołanie funkcji tworzymy nowego dlatego nie w inicie
        fuelShape = new Rectangle2D.Double(manager.getTmpFuel().coords.x, manager.getTmpFuel().coords.y, manager.getTmpFuel().getWidth(), manager.getTmpFuel().getHeight());

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        //Operacje przekształcenia afinicznego na macierzach pixeli
        AffineTransform oldTransform = g.getTransform();
        AffineTransform scaleMatrix = new AffineTransform();

        //staly stosunek elementow interfejsu
        //!rzutowanie na float elementow skalujacych
        float sx = (1f + (getSize().width - FileManager.gameWindowWidth) / (float) FileManager.gameWindowWidth);
        float sy = (1f + (getSize().height - FileManager.gameWindowHeight) / (float) FileManager.gameWindowHeight);
        scaleMatrix.scale(sx, sy);
        g.setTransform(scaleMatrix);

        landingPlatformRed.render(g);
        landingPlatformGreen.render(g);
        terrain.paint(g);
        manager.render(g);

        if (paused && !manager.getTmpObject().hasLanded && !manager.getTmpObject().hasCrashed) {
            displayPause(g);
        } else if (paused && manager.getTmpObject().hasCrashed) {
            displayCrash(g);
        } else if (manager.getTmpObject().hasLanded) {
            displaySucces(g);
        }


        if (!paused && !manager.getTmpObject().hasLanded && !manager.getTmpObject().hasCrashed) {
            collisionDetection(terrainShape, landingShapeGreen, player);
            collisionDetection(terrainShape, landingShapeRed, player);
            fuelCollected(player, fuelShape);
        }

        drawStatistics(g);
        g.setTransform(oldTransform);


        bs.show();


    }


    /**
     * Metoda odpowiedzialna za wyświtlanie informacji na panelu gry
     *
     * @param g
     */

    void drawStatistics(Graphics g) {

        Color c = new Color(227, 202, 143);
        g.setColor(c);
        Font f = new Font("Helvetica", Font.BOLD, 20);//new Font("Helvetica",Font.BOLD,Font.ITALIC,15);
        g.setFont(f);


        if (manager.getTmpObject().getFuel() > 0)
            g.drawString("Paliwo: " + manager.getTmpObject().getFuel(), 750, 690);
        else
            g.drawString("Paliwo: " + 0, 750, 690);
        g.drawString("Punkty: " + totalScore, 950, 690);
        g.drawString("Poziom: " + index, 1150, 690);


    }

    /**
     * Metoda odpowiedzialna za kolizję
     *
     * @param landingPlatform hit-box platformy do lądowania
     * @param player          hit-box gracza
     * @param terrain         hit-box terenu
     */

    void collisionDetection(Shape terrain, Shape landingPlatform, Shape player) throws InterruptedException, IOException {

        if (terrain.intersects((Rectangle2D) player) || landingPlatform.intersects((Rectangle2D) player)) {

            if (landingPlatform.intersects((Rectangle2D) player) && manager.getTmpObject().vel.y < 0.9 * manager.getTmpObject().MAX_SPEED) {

                manager.getTmpObject().hasLanded = true;
                System.out.println("Lądowanko!");
                manager.getTmpObject().points += 500;
                manager.getTmpObject().points += manager.getTmpObject().getFuel();


                if (landingShapeRed.intersects((Rectangle2D) player))
                    manager.getTmpObject().points *= FileManager.landing2Multiplier;

                newLevel();

            } else {

                if (!over) {
                    manager.getTmpObject().hasCrashed = true;
                    System.out.println("Zderzonko!");


                    paused = true;

                    if (Client.isOnline) {

                        Client.sendScoreToServer(mainWindow.playerName, totalScore);
                    } else {

                        FileManager.saveScore(mainWindow.playerName, totalScore);
                    }

                    if (backWindow == null) {
                        backWindow = new BackToMenu(640, 360, "Porażka.", mainWindow, "Client/data/background/background_lose.gif");
                        backWindow.setVisible(true);


                    }
                    over = true;
                }
            }

            manager.getTmpObject().vel.y = 0;
            manager.getTmpObject().vel.x = 0;

        }
    }

    /**
     * Metoda odpowiedzialna za zebranie paliwa
     *
     * @param player    hit-box gracza
     * @param fuelShape hit-box paliwa
     */

    void fuelCollected(Shape player, Shape fuelShape) {

        if (player.intersects((Rectangle2D) fuelShape)) {

            if (!manager.getTmpFuel().collected)
                manager.getTmpObject().addFuel(FileManager.fuelBonus);

            manager.getTmpFuel().exists = false;
            manager.getTmpFuel().collected = true;

        }
    }

    /**
     * Metoda odpowiedzialna za zmiane levelu
     */

    void newLevel() throws InterruptedException, IOException {
        over = false;
        if (index < FileManager.numberOfLevel) {
            totalScore += manager.getTmpObject().points;
            index++;
            LevelWindow levelWindow = new LevelWindow(640, 360, "Lunar Lander", this);

            init();
        } else {
            manager.getTmpObject().hasCrashed = true;


            paused = true;

            if (Client.isOnline) {
                Client.sendScoreToServer(mainWindow.playerName, totalScore);

            } else {

                FileManager.saveScore(mainWindow.playerName, totalScore);
            }


            backWindow = new BackToMenu(640, 360, "End Game", mainWindow, "Client/data/background/Congrats.gif");
            backWindow.setVisible(true);

        }

    }
}
