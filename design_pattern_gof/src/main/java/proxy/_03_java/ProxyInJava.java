package proxy._03_java;

import proxy._02_after.DefaultGameService;
import proxy._02_after.GameService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyInJava {

    public static void main(String[] args) {

        ProxyInJava proxyInJava = new ProxyInJava();
        proxyInJava.dynamicProxy();
        proxyInJava.dynamicProxy2();
    }

    private void dynamicProxy() {
        GameService gameServiceProxy = getGameServiceProxy(new DefaultGameService());
        gameServiceProxy.startGame();
    }

    private void dynamicProxy2() {
        GameService gameServiceProxy = initGameServiceProxy();
        gameServiceProxy.startGame();
    }

    private GameService getGameServiceProxy(GameService target) {
        return (GameService) Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                new Class[]{GameService.class},
                (proxy, method, args) -> {
                    System.out.println("Hello dynamic proxy");
                    method.invoke(target, args);
                    return null;
                }
        );
    }

    private GameService initGameServiceProxy() {
        return (GameService) Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                new Class[]{GameService.class},
                new InvocationHandler() {

                    GameService target;

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if(target == null) {
                            this.target = new DefaultGameService();
                        }
                        System.out.println("Hello dynamic proxy");
                        target.startGame();
                        return null;
                    }
                }
        );
    }

}
