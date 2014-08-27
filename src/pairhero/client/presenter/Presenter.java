package pairhero.client.presenter;

import pairhero.PairHeroToolWindowFactory;

public class Presenter {

    private PairHeroToolWindowFactory view;

    public void onResolvedTest() {
        if(onGoingGame()) {
            view().onResolvedTest();
        }
    }

    public void onBrokenTest() {
        if(onGoingGame()) {
            view().onBrokenTest();
        }
    }

    private boolean onGoingGame() {
        //return game != null && game.isOnGoing();
        return view().isGameOngoing();
    }

    private PairHeroToolWindowFactory view() {
        if (view == null) {
            view = PairHeroToolWindowFactory.getToolWindowFactory();
        }
        return view;
    }
}
