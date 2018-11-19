package com.exeter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game implements BaseGame {
    private List<Player> players = new ArrayList<>();
    private List<Deck> decks = new ArrayList<>();
    private BaseGameLogger logger;

    /**
     * Constructor for Game which also acts as a Mediator between {@link Player} and {@link Deck}.
     *
     * @param numPlayers    number of players determined by input in {@link CardGame}.
     * @param cards         the initial pack of cards determined by input in {@link CardGame}.
     * @param logger        the logger class that manages outputting to files.
     */
    Game (int numPlayers, List<Card> cards, BaseGameLogger logger){
        this.logger = logger;
        initialise(numPlayers);
        dealCards(cards);
    }

    public List<Player> getPlayers(){ return players; }

    public void setPlayers(List<Player> players){ this.players = players; };


    /**
     * Starts all player threads and sets up the logger.
     */
    public void start() {
        for (Player player : players){
            logger.initialPlayerState(player);
            player.start();
        }
    }

    /**
     * Global interrupt function that calls all {@link Player} interrupt functions.
     * Used for end game so also logs the final {@link Deck}.
     *
     * @param source    the player that called the interrupt (the person who's won).
     */
    public synchronized void interrupt(Player source) {
        //interrupt all players
        for (Player player : players) {
            player.interrupt(source);
        }
        logger.decks(decks);
    }

    /**
     * Regularly used function to allow {@link Player} to check their hand.
     * Synchronized to ensure Thread Safety.
     *
     * @param player    the {@link Player} that sent the call.
     * @return          whether or not the calling {@link Player} has won.
     */
    public synchronized boolean checkWin(Player player){
        List<Card> hand = player.getHand();

        if(hand.size() == 4){
            int v = hand.get(0).getValue();
            for (Card card : hand) {
                if(card.getValue() != v)
                    return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Used to allow the calling {@link Player} to take a turn.
     *
     * @param player    the calling {@link Player} taking the turn.
     */
    public synchronized void takeTurn(Player player){
        if(drawCard(player)){
            discardCard(player);
            logger.playerState(player);
        }
    }

    /**
     * Reports the last hand and the winning {@link Player}.
     *
     * @param player    the winning {@link Player}.
     */
    public void finalPlayerState(Player player){
        logger.finalise(player);
    }

    /**
     * Removes a {@link Card} from the corresponding {@link Deck} and gives it to {@link Player}.
     *
     * @param player    the {@link Player} who will gain the {@link Card}.
     * @return          whether or not the draw was successful.
     */
    private boolean drawCard(Player player){
        //draw from the left deck
        int sourceDeckId = (player.getId()-1 == 0) ? players.size()-1 : player.getId()-1;
        Deck source = decks.get(sourceDeckId);

        Card card = source.pickFromTop();

        if(card == null){
            return false;
        }
        player.getHand().add(card);
        logger.playerDraw(player, source, card);
        return true;

    }

    /**
     * Removes a given {@link Player} {@link Card} and places it in the next {@link Deck}.
     *
     * @param player    the {@link Player} losing the {@link Card}.
     */
    private void discardCard(Player player){
        //discard to the right deck
        int burnerDeckId = (player.getId() == players.size()) ? 0 : player.getId();
        Deck burner = decks.get(burnerDeckId);

        Random rand = new Random();
        boolean chosenCardWanted = true;
        Card chosen;

        while(chosenCardWanted){
            chosen = player.getHand().get(rand.nextInt(5));
            if(chosen.getValue() != player.getId()){
                chosenCardWanted = false;
                burner.discardToBottom(chosen);
                player.getHand().remove(chosen);

                logger.playerDiscard(player, burner, chosen);
            }
        }
    }

    /**
     * Main Game initialization involving ensuring the Game's {@link Player} and {@link Deck} are stored in memory.
     *
     * @param numPlayers    number of {@link Player}'s in the Game.
     */
    private void initialise(int numPlayers){
        for (int i = 1; i < numPlayers + 1; i++) {
            Player player = new Player(i, this);
            Deck deck = new Deck(i, this);

            players.add(player);
            decks.add(deck);
        }
    }

    /**
     * Deals all the {@link Card}s from the initial pile to {@link Player}s and {@link Deck}s.
     *
     * @param cards     the initial pile of cards.
     */
    private void dealCards(List<Card> cards){
        Player currentPlayer = players.get(0);

        while (currentPlayer.getHand().size() < 4){
            currentPlayer.deal(cards.remove(0));

            int i = players.indexOf(currentPlayer);
            if (i == players.size() - 1)
                currentPlayer = players.get(0);
            else
                currentPlayer = players.get(i+1);
        }

        Deck currentDeck = decks.get(0);

        while (cards.size() > 0){
            currentDeck.deal(cards.remove(0));

            int i = decks.indexOf(currentDeck);
            if (i == decks.size() - 1)
                currentDeck = decks.get(0);
            else
                currentDeck = decks.get(i+1);
        }

    }

}