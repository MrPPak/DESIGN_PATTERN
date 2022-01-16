package proxy._02_after;

public class Client {

    public static void main(String[] args) {

        GameService gameService1 = new GameServiceInterfaceProxy();
        gameService1.startGame();

        GameService gameService2 = new GameServiceInheritanceProxy();
        gameService2.startGame();
    }
}
