package proxy._02_after;

public class GameServiceInterfaceProxy implements GameService{

    private GameService gameService;

    @Override
    public void startGame() {

        long before = System.currentTimeMillis();
        if(gameService == null) {
            this.gameService = new DefaultGameService();
        }
        gameService.startGame();
        System.out.println(System.currentTimeMillis() - before);
    }
}
