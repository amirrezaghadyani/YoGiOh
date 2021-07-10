package controller;

import model.battle.Ai;
import model.battle.Phase;
import model.battle.Player;
import model.card.Card;
import model.card.Monster;
import model.card.Spell;
import model.card.Trap;
import model.mat.Mat;
import model.user.MainDeck;
import model.user.SideDeck;
import model.user.User;
import view.ScanInput;
import view.TerminalOutput;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DuelMenu {
    public User currentUser;
    public User secondUser;
    public Player firstPlayer;
    public Player secondPlayer;
    public int numberOfRounds;
    public int wholeNumberOfRounds;
    public int firstPlayerMaxLP = 0;
    public int secondPlayerMaxLP = 0;
    public int firstPlayerWins = 0;
    public int secondPlayerWins = 0;
    public Player currentTurnPlayer;
    public Player opponentTurnPlayer;
    public User currentTurnUser;
    public Phase phase;
    public Ai ai;
    public boolean isAi;
    public boolean hasAiMoved;
    public boolean isDoAttack;
    public boolean permissionForAttack = true;
    public Monster onAttack;
    public boolean isDuelIsOn;
    public String terminalOutput = "";
    public boolean isFirstRound;
    public Card summonedMonster;
    public User firstRoundWinner;
    public User secondRoundWinner;
    private boolean isGameEnd = false;
    public boolean canCardBeSetAfterTerratiger = true;
    public static ArrayList<Card> monsterEffect1 = new ArrayList<>();
    public static ArrayList<Card> monsterEffect2 = new ArrayList<>();
    public static ArrayList<Card> monsterEffect3 = new ArrayList<>();
    public static ArrayList<Card> monsterEffect4 = new ArrayList<>();
    public static ArrayList<Card> monsterEffect5 = new ArrayList<>();
    public static ArrayList<Card> monsterEffect6 = new ArrayList<>();
    public static ArrayList<Card> monsterEffect7 = new ArrayList<>();
    public static ArrayList<Card> monsterEffect8 = new ArrayList<>();
    public static ArrayList<Card> monsterEffect9 = new ArrayList<>();
    public static ArrayList<Card> monsterEffect10 = new ArrayList<>();
    public static ArrayList<Card> monsterEffect11 = new ArrayList<>();
    public static ArrayList<Card> monsterEffect12 = new ArrayList<>();
    public static ArrayList<Card> monsterEffect13 = new ArrayList<>();
    public static ArrayList<Card> monsterEffect14 = new ArrayList<>();
    public static ArrayList<Card> monsterEffect15 = new ArrayList<>();
    public static ArrayList<Card> monsterEffect16 = new ArrayList<>();
    public static ArrayList<Card> monsterEffect17 = new ArrayList<>();
    public static ArrayList<Card> spellTrapEffect1 = new ArrayList<>();
    public static ArrayList<Card> spellTrapEffect2 = new ArrayList<>();
    public static ArrayList<Card> spellTrapEffect3 = new ArrayList<>();
    public static ArrayList<Card> spellTrapEffect4 = new ArrayList<>();
    public static ArrayList<Card> spellTrapEffect5 = new ArrayList<>();
    public static ArrayList<Card> spellTrapEffect6 = new ArrayList<>();
    public static ArrayList<Card> spellTrapEffect7 = new ArrayList<>();
    public static ArrayList<Card> spellTrapEffect8 = new ArrayList<>();
    public static ArrayList<Card> spellTrapEffect9 = new ArrayList<>();
    public static ArrayList<Card> spellTrapEffect10 = new ArrayList<>();
    public static ArrayList<Card> spellTrapEffect11 = new ArrayList<>();
    public static ArrayList<Card> spellTrapEffect12 = new ArrayList<>();
    public static ArrayList<Card> spellTrapEffect13 = new ArrayList<>();
    public static ArrayList<Card> spellTrapEffect14 = new ArrayList<>();
    public static ArrayList<Card> spellTrapEffect15 = new ArrayList<>();
    public static ArrayList<Card> spellTrapEffect16 = new ArrayList<>();
    public static ArrayList<Card> spellTrapEffect17 = new ArrayList<>();
    public static ArrayList<Card> spellTrapEffect18 = new ArrayList<>();
    public static ArrayList<Card> spellTrapEffect19 = new ArrayList<>();
    public static ArrayList<Card> spellTrapEffect20 = new ArrayList<>();
    public static ArrayList<Card> spellTrapEffect21 = new ArrayList<>();
    public static ArrayList<Card> spellTrapEffect22 = new ArrayList<>();
    public static ArrayList<Card> spellTrapEffect23 = new ArrayList<>();
    public static ArrayList<Card> spellTrapEffect24 = new ArrayList<>();
    public static ArrayList<Card> spellTrapEffect25 = new ArrayList<>();
    public static ArrayList<Card> spellTrapEffect26 = new ArrayList<>();
    public static ArrayList<Card> spellTrapEffect27 = new ArrayList<>();
    public static ArrayList<Card> spellTrapEffect28 = new ArrayList<>();
    public static ArrayList<Card> spellTrapEffect29 = new ArrayList<>();
    public static ArrayList<Card> spellTrapEffect30 = new ArrayList<>();
    public static ArrayList<Card> spellTrapEffect31 = new ArrayList<>();
    public static ArrayList<Card> spellTrapEffect32 = new ArrayList<>();
    public static ArrayList<Card> spellTrapEffect33 = new ArrayList<>();

    public DuelMenu(String currentUser, String secondUser, int numberOfRounds, boolean isAi) {
        this.isAi = isAi;
        setCurrentUser(User.getUserByUsername(currentUser));
        setSecondUser(User.getUserByUsername(secondUser));
        firstPlayer = new Player(this.currentUser);
        if (!isAi) {
            if (!isUsernameExist(secondUser)) {
                terminalOutput = "there is no player with this username";
                this.isDuelIsOn = false;
                return;
            }
            secondPlayer = new Player(this.secondUser);
        } else {
            User user = User.getUserByUsername("AI");
            secondPlayer = new Player(user);
            ai = new Ai(firstPlayer, secondPlayer);
            ai.setDeck();
            this.isDuelIsOn = true;
        }
        if (!isPlayerHadActiveDeck(this.currentUser)) {
            terminalOutput = currentUser + " has no active deck";
            isDuelIsOn = false;
            return;
        }
        if (!isPlayerHadActiveDeck(this.secondUser)) {
            terminalOutput = secondUser + " has no active deck";
            isDuelIsOn = false;
            return;
        }
        if (!isActiveDeckValid(this.currentUser)) {
            terminalOutput = currentUser + "'s deck is invalid";
            isDuelIsOn = false;
            return;
        }
        if (!isActiveDeckValid(this.secondUser)) {
            terminalOutput = secondUser + "'s deck is invalid";
            isDuelIsOn = false;
            return;
        }
        if (numberOfRounds != 3 && numberOfRounds != 1) {
            terminalOutput = "number of rounds is not supported";
            isDuelIsOn = false;
            return;
        }
        setNumberOfRounds(numberOfRounds);
        wholeNumberOfRounds = numberOfRounds;
        isDuelIsOn = true;
        this.phase = new Phase(this);
        Random random = new Random();
        int dice = random.nextInt(2);
        if (dice == 0) {
            currentTurnPlayer = firstPlayer;
            opponentTurnPlayer = secondPlayer;
        } else {
            opponentTurnPlayer = firstPlayer;
            currentTurnPlayer = secondPlayer;
        }
        if (isAi) {
            currentTurnPlayer = firstPlayer;
            opponentTurnPlayer = secondPlayer;
        }

    }

    public static void configureEffects() {
        monsterEffect1.add(Card.getCardByName("Command Knight"));
        monsterEffect2.add(Card.getCardByName("Yomi Ship"));
        monsterEffect3.add(Card.getCardByName("Suijin"));
        monsterEffect4.add(Card.getCardByName("Beast-Warrior"));
        monsterEffect5.add(Card.getCardByName("Skull Guardian"));
        monsterEffect6.add(Card.getCardByName("Man-Eater Bug"));
        monsterEffect7.add(Card.getCardByName("Gate Guardian"));
        monsterEffect8.add(Card.getCardByName("Scanner"));
        monsterEffect9.add(Card.getCardByName("Marshmallon"));
        monsterEffect10.add(Card.getCardByName("Beast King Barbaros"));
        monsterEffect11.add(Card.getCardByName("Texchanger"));
        monsterEffect12.add(Card.getCardByName("The Calculator"));
        monsterEffect13.add(Card.getCardByName("Mirage Dragon"));
        monsterEffect14.add(Card.getCardByName("Herald of Creation"));
        monsterEffect15.add(Card.getCardByName("Exploder Dragon"));
        monsterEffect16.add(Card.getCardByName("Terratiger, the Empowered Warrior"));
        monsterEffect17.add(Card.getCardByName("The Tricky"));

        spellTrapEffect1.add(Card.getCardByName("Monster Reborn"));
        spellTrapEffect2.add(Card.getCardByName("Terraforming"));
        spellTrapEffect3.add(Card.getCardByName("Pot of Greed"));
        spellTrapEffect4.add(Card.getCardByName("Raigeki"));
        spellTrapEffect5.add(Card.getCardByName("Change of Heart"));
        spellTrapEffect6.add(Card.getCardByName("Harpie's Feather Duster"));
        spellTrapEffect7.add(Card.getCardByName("Swords of Revealing Light"));
        spellTrapEffect8.add(Card.getCardByName("Dark Hole"));
        spellTrapEffect9.add(Card.getCardByName("Supply Squad"));
        spellTrapEffect10.add(Card.getCardByName("Spell Absorption"));
        spellTrapEffect11.add(Card.getCardByName("Messenger of peace"));
        spellTrapEffect12.add(Card.getCardByName("Twin Twisters"));
        spellTrapEffect13.add(Card.getCardByName("Mystical space typhoon"));
        spellTrapEffect14.add(Card.getCardByName("Ring of Defense"));
        spellTrapEffect15.add(Card.getCardByName("Yami"));
        spellTrapEffect16.add(Card.getCardByName("Forest"));
        spellTrapEffect17.add(Card.getCardByName("Closed Forest"));
        spellTrapEffect18.add(Card.getCardByName("UMIIRUKA"));
        spellTrapEffect19.add(Card.getCardByName("Sword of Dark Destruction"));
        spellTrapEffect20.add(Card.getCardByName("Black Pendant"));
        spellTrapEffect21.add(Card.getCardByName("United We Stand"));
        spellTrapEffect22.add(Card.getCardByName("Magnum Shield"));
        spellTrapEffect23.add(Card.getCardByName("Advanced Ritual Art"));
        spellTrapEffect24.add(Card.getCardByName("Magic Cylinder"));
        spellTrapEffect25.add(Card.getCardByName("Mirror Force"));
        spellTrapEffect26.add(Card.getCardByName("Mind Crush"));
        spellTrapEffect27.add(Card.getCardByName("Trap Hole"));
        spellTrapEffect28.add(Card.getCardByName("Torrential Tribute"));
        spellTrapEffect29.add(Card.getCardByName("Time Seal"));
        spellTrapEffect30.add(Card.getCardByName("Negate Attack"));
        spellTrapEffect31.add(Card.getCardByName("Solemn Warning"));
        spellTrapEffect32.add(Card.getCardByName("Magic Jammer"));
        spellTrapEffect33.add(Card.getCardByName("Call of the Haunted"));
    }

    public static boolean isCardInArray(Card card, ArrayList<Card> arrayList) {
        for (Card card1 : arrayList) {
            if (card1 != null && card1.getName().equals(card.getName()))
                return true;
        }
        return false;
    }

    public boolean isDuelIsOn() {
        return isDuelIsOn;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void setSecondUser(User secondUser) {
        this.secondUser = secondUser;
    }

    public void setNumberOfRounds(int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
    }

    public void cardShow(String cardName) {
        terminalOutput = Card.showCard(cardName);
    }

    public boolean isPlayerHadActiveDeck(User user) {
        return user.getActiveDeck() != null;
    }

    public boolean isUsernameExist(String username) {
        return User.getUserByUsername(username) != null;
    }

    public boolean isActiveDeckValid(User user) {
        return user.getActiveDeck().isValid();
    }

    public void showBoard() {
        String opponentMat = opponentTurnPlayer.getMat().printMat(opponentTurnPlayer.getUser().getActiveDeck(), true, opponentTurnPlayer.getSizeOfDeck());
        String currentMat = currentTurnPlayer.getMat().printMat(currentTurnPlayer.getUser().getActiveDeck(), false, currentTurnPlayer.getSizeOfDeck());
        User currentTurnUser = currentTurnPlayer.getUser();
        User opponentTurnUser = opponentTurnPlayer.getUser();
        terminalOutput = opponentTurnUser.getNickname() + " : " + opponentTurnPlayer.getLifePoint() + "\n";
        terminalOutput += opponentMat;
        terminalOutput += "-----------------------------\n";
        terminalOutput += currentMat;
        terminalOutput += currentTurnUser.getNickname() + " : " + currentTurnPlayer.getLifePoint();
    }

    public void selectMonster(int number, boolean isOpponent) {
        if (!isOpponent) {
            selectMonsterCheck(number, currentTurnPlayer, 3, 1, 4, 0);
            currentTurnPlayer.setSelectedName("Monster");
            switch (number) {
                case 1:
                    currentTurnPlayer.setNumberOfMonsterZone(2);
                    break;
                case 2:
                    currentTurnPlayer.setNumberOfMonsterZone(3);
                    break;
                case 3:
                    currentTurnPlayer.setNumberOfMonsterZone(4);
                    break;
                case 4:
                    currentTurnPlayer.setNumberOfMonsterZone(0);
                    break;
                default:
                    break;
            }
            return;
        }
        selectMonsterCheck(number, opponentTurnPlayer, 1, 3, 0, 4);
        currentTurnPlayer.setSelectedName("MonsterOpponent");
    }

    private void selectMonsterCheck(int number, Player opponentTurnPlayer, int i, int i2, int i3, int i4) {
        Monster monster;
        Mat mat = opponentTurnPlayer.getMat();
        if (number == 1) {
            monster = mat.getMonsterZone(2);
            if (monster == null) {
                terminalOutput = "no card found in the given position";
                return;
            }
            currentTurnPlayer.setCurrentSelectedCard(monster);
            terminalOutput = "card selected";
        } else if (number == 2) {
            monster = mat.getMonsterZone(i);
            if (monster == null) {
                terminalOutput = "no card found in the given position";
                return;
            }
            currentTurnPlayer.setCurrentSelectedCard(monster);
            terminalOutput = "card selected";
        } else if (number == 3) {
            monster = mat.getMonsterZone(i2);
            if (monster == null) {
                terminalOutput = "no card found in the given position";
                return;
            }
            currentTurnPlayer.setCurrentSelectedCard(monster);
            terminalOutput = "card selected";
        } else if (number == 4) {
            monster = mat.getMonsterZone(i3);
            if (monster == null) {
                terminalOutput = "no card found in the given position";
                return;
            }
            currentTurnPlayer.setCurrentSelectedCard(monster);
            terminalOutput = "card selected";
        } else if (number == 5) {
            monster = mat.getMonsterZone(i4);
            if (monster == null) {
                terminalOutput = "no card found in the given position";
            }
            currentTurnPlayer.setCurrentSelectedCard(monster);
            terminalOutput = "card selected";
        } else {
            terminalOutput = "invalid selection";
        }
    }

    public void selectSpellOrTrap(int number, boolean isOpponent) {
        if (!isOpponent) {
            selectSpellOrTrapCheck(number, currentTurnPlayer, 3, 1, 4, 0);
            currentTurnPlayer.setSelectedName("Spell");
            return;
        }
        selectSpellOrTrapCheck(number, opponentTurnPlayer, 1, 3, 0, 4);
        currentTurnPlayer.setSelectedName("SpellOpponent");
    }

    private void selectSpellOrTrapCheck(int number, Player opponentTurnPlayer, int i, int i1, int i2, int i3) {
        Card card;
        Mat mat = opponentTurnPlayer.getMat();
        if (number == 1) {
            card = mat.getSpellAndTrapZone(2);
            if (card == null) {
                terminalOutput = "no card found in the given position";
                return;
            }
            currentTurnPlayer.setCurrentSelectedCard(card);
            terminalOutput = "card selected";
        } else if (number == 2) {
            card = mat.getSpellAndTrapZone(i);
            if (card == null) {
                terminalOutput = "no card found in the given position";
                return;
            }
            currentTurnPlayer.setCurrentSelectedCard(card);
            terminalOutput = "card selected";
        } else if (number == 3) {
            card = mat.getSpellAndTrapZone(i1);
            if (card == null) {
                terminalOutput = "no card found in the given position";
                return;
            }
            currentTurnPlayer.setCurrentSelectedCard(card);
            terminalOutput = "card selected";
        } else if (number == 4) {
            card = mat.getSpellAndTrapZone(i2);
            if (card == null) {
                terminalOutput = "no card found in the given position";
                return;
            }
            currentTurnPlayer.setCurrentSelectedCard(card);
            terminalOutput = "card selected";
        } else if (number == 5) {
            card = mat.getSpellAndTrapZone(i3);
            if (card == null) {
                terminalOutput = "no card found in the given position";
            }
            currentTurnPlayer.setCurrentSelectedCard(card);
            terminalOutput = "card selected";
        } else {
            terminalOutput = "invalid selection";
        }
    }

    public void selectField(int number, boolean isOpponent) {
        Mat mat;
        if (isOpponent) {
            mat = opponentTurnPlayer.getMat();
            if (mat.getFieldZone() == null) {
                terminalOutput = "no card found in the given position";
            }
            terminalOutput = "card selected";
            opponentTurnPlayer.setCurrentSelectedCard(mat.getFieldZone());
            return;
        }
        mat = currentTurnPlayer.getMat();
        if (mat.getFieldZone() == null) {
            terminalOutput = "no card found in the given position";
        }
        terminalOutput = "card selected";
        currentTurnPlayer.setSelectedName("Field");
        currentTurnPlayer.setCurrentSelectedCard(mat.getFieldZone());
    }

    public void selectHand(int number) {
        if (number > 6 || number < 1) {
            terminalOutput = "invalid selection";
            return;
        }
        Card card = currentTurnPlayer.getMat().getHandCard(number - 1);
        if (card == null) {
            terminalOutput = "no card found in the given position";
            return;
        }
        terminalOutput = "card selected";
        currentTurnPlayer.setSelectedName("Hand");
        currentTurnPlayer.setCurrentSelectedCard(card);
        currentTurnPlayer.setHandNumber(number);
    }

    public void deSelectCard() {
        if (currentTurnPlayer.getCurrentSelectedCard() == null) {
            terminalOutput = "no card selected yet";
            return;
        }
        terminalOutput = "card deselected";
        currentTurnPlayer.setCurrentSelectedCard(null);
    }

    public void nextPhase() {
        phase.nextPhase();
        System.out.print(phase.getCurrentPhase());
        if (phase.getCurrentPhase().equals("End Phase")) {
            terminalOutput = phase.endPhase(opponentTurnPlayer) + "\n" + "Draw Phase" + "\n" + phase.drawPhase(currentTurnPlayer);
            phase.nextPhase();
        }
    }

    public String phase2NextPhase() {
        String ret = "";
        phase.nextPhase();
        ret += phase.getCurrentPhase() + "\n";
        if (phase.getCurrentPhase().equals("End Phase")) {
            ret += phase.endPhase(opponentTurnPlayer) + "\n" + "Draw Phase" + "\n" + phase.drawPhase(currentTurnPlayer);
            phase.nextPhase();
        }
        return ret;
    }

    public void summon() {
        Mat mat = currentTurnPlayer.getMat();
        if (currentTurnPlayer.getCurrentSelectedCard() == null) {
            terminalOutput = "no card selected yet";
            return;
        }
        if (!(currentTurnPlayer.getCurrentSelectedCard() instanceof Monster)) {
            terminalOutput = "you can't summon this card";
            return;
        }
        if (!phase.getCurrentPhase().equals("First Main Phase") && !phase.getCurrentPhase().equals("Second Main Phase")) {
            terminalOutput = "action is not allowed in this phase";
            return;
        }
        if (mat.isMonsterZoneIsFull()) {
            terminalOutput = "monster card zone is full";
            return;
        }
        if (currentTurnPlayer.isSummoned()) {
            terminalOutput = "you already summoned/set on this turn";
            return;
        }
        if (!isEnoughCardForTribute() && ((Monster) currentTurnPlayer.getCurrentSelectedCard()).getCardType().equals("Normal")) {
            terminalOutput = "there are not enough cards for tribute";
            return;
        }
        Monster monster = (Monster) currentTurnPlayer.getCurrentSelectedCard();

        if (monster.getCardType().equals("Normal")) {
            if (monster.getLevel() > 4) {
                summonWithTribute(monster);
                return;
            }
        }
        terminalOutput = "summon successfully";
        summonedMonster = monster;
        monster.setAttack(true);
        monster.setOn(true);
        mat.addMonster(monster);
        currentTurnPlayer.setCurrentSelectedCard(null);
        currentTurnPlayer.setSelectedName(null);
        currentTurnPlayer.getMat().deleteHandCard(currentTurnPlayer.getHandNumber());
        currentTurnPlayer.setHandNumber(-1);
        currentTurnPlayer.setSummoned(true);
        effectCheckerInSummon(mat, monster);
    }

    public String phase2Summon() {
        Mat mat = currentTurnPlayer.getMat();
        if (currentTurnPlayer.getCurrentSelectedCard() == null) {
            return "no card selected yet";

        }
        if (!(currentTurnPlayer.getCurrentSelectedCard() instanceof Monster)) {
            return "you can't summon this card";

        }
        if (!phase.getCurrentPhase().equals("First Main Phase") && !phase.getCurrentPhase().equals("Second Main Phase")) {
            return "action is not allowed in this phase";

        }
        if (mat.isMonsterZoneIsFull()) {
            return "monster card zone is full";

        }
        if (currentTurnPlayer.isSummoned()) {
            return "you already summoned/set on this turn";

        }
        if (!isEnoughCardForTribute() && ((Monster) currentTurnPlayer.getCurrentSelectedCard()).getCardType().equals("Normal")) {
            return "there are not enough cards for tribute";

        }
        Monster monster = (Monster) currentTurnPlayer.getCurrentSelectedCard();

        if (monster.getCardType().equals("Normal")) {
            if (monster.getLevel() > 4) {
                summonWithTribute(monster);
                return "Summoned with tribute";
            }
        }
        summonedMonster = monster;
        monster.setAttack(true);
        monster.setOn(true);
        mat.addMonster(monster);
        currentTurnPlayer.setCurrentSelectedCard(null);
        currentTurnPlayer.setSelectedName(null);
        currentTurnPlayer.getMat().deleteHandCard(currentTurnPlayer.getHandNumber());
        currentTurnPlayer.setHandNumber(-1);
        currentTurnPlayer.setSummoned(true);
        effectCheckerInSummon(mat, monster);
        return "Summon successful";
    }

    private void effectCheckerInSummon(Mat mat, Monster monster) {
        if (isCardInArray(monster, monsterEffect1)) {
            if (monster.isOn() && monster.isFirstEffectUse()) {
                for (int i = 0; i < 5; i++) {
                    Monster monsterInMat = mat.getMonsterZone(i);
                    if (monsterInMat != null) {
                        monsterInMat.setAttack(getAttack(monsterInMat) + 400);
                    }
                }
                monster.setFirstEffectUse(false);
            }
        } else if (isCardInArray(monster, monsterEffect2)) {

        } else if (isCardInArray(monster, monsterEffect3)) {
            if (monster.isOn() && monster.isFirstEffectUse()) {
                //set opponent monster att 0
                monster.setFirstEffectUse(false);
            }

        } else if (isCardInArray(monster, monsterEffect4)) {

        } else if (isCardInArray(monster, monsterEffect5)) {

        } else if (isCardInArray(monster, monsterEffect6)) {
            //when flip summon
            if (monster.isOn() && monster.isFirstTimeFlipping()) {
                monster.setFirstTimeFlipping(false);
                Mat mat1 = opponentTurnPlayer.getMat();
                for (int i = 0; i < 5; i++) {
                    Monster monster1 = mat1.getMonsterZone(i);
                    if (monster1 != null) {
                        Card card = monster1;
                        mat1.addCardToGraveyard(card);
                        monster1 = null;
                    }
                }
            }
        } else if (isCardInArray(monster, monsterEffect7)) {
            //FOT
        } else if (isCardInArray(monster, monsterEffect8)) {
            ArrayList<Card> cards = opponentTurnPlayer.getMat().getGraveyard();
            String cardName = ScanInput.getInput();
            for (Card card : cards) {
                if (card.equals(cardName)) {
                    Monster monster1 = (Monster) card;
                    Monster monster2 = new Monster(monster1.getName(), monster1.getLevel(), monster1.getAttribute()

                            , monster1.getMonsterType(), monster1.getCardType(), monster1.getAttack(), monster1.getDefence(), monster1.getDescription(), monster1.getPrice());

                    monster = monster2;
                }
            }

        } else if (isCardInArray(monster, monsterEffect9)) {
            if (!monster.isOn()) {
                opponentTurnPlayer.changeLifePoint(-1000);
            }

        } else if (isCardInArray(monster, monsterEffect10)) {
            TerminalOutput.output("You want tribute or not\n1.Yes\n2.No");
            String input = ScanInput.getInput();
            if (input.equals("2")) {
                monster.setAttack(1900);
            } else {
                if (isEnoughCardForTribute()) {
                    summonWithTribute(monster);
                } else {
                    TerminalOutput.output("there are not enough cards for tribute");
                    monster.setAttack(1900);
                }
            }

        } else if (isCardInArray(monster, monsterEffect11)) {

        } else if (isCardInArray(monster, monsterEffect12)) {
            //FOT
        } else if (isCardInArray(monster, monsterEffect13)) {

            //FOT

        } else if (isCardInArray(monster, monsterEffect14)) {
            TerminalOutput.output("You want to bring card from graveyard or not\n1.Yes\n2.No");
            String input = ScanInput.getInput();
            if (input.equals("1")) {
                TerminalOutput.output("Select number of card from hand");
                int number = Integer.parseInt(ScanInput.getInput());
                TerminalOutput.output("Enter name of card from graveyard");
                String graveyardCard = ScanInput.getInput();
                if (number < 1 || number > 6) {
                    return;
                }
                for (Card card : mat.getGraveyard()) {
                    if (card.getName().equals(graveyardCard)) {
                        mat.setHandCard(card);
                        mat.getGraveyard().remove(card);
                        return;
                    }
                }
            }

        } else if (isCardInArray(monster, monsterEffect15)) {
            //FOT
        } else if (isCardInArray(monster, monsterEffect16)) {
            Card card = currentTurnPlayer.getCurrentSelectedCard();
            if (!(card instanceof Monster))
                return;
            if (((Monster) card).getLevel() > 4) {
                canCardBeSetAfterTerratiger = false;
            }
        } else if (isCardInArray(monster, monsterEffect17)) {
            TerminalOutput.output("You want to summon card or not\n1.Yes\n2.No");
            int input = Integer.parseInt(ScanInput.getInput());
            if (input == 1) {
                TerminalOutput.output("Select number of card from hand");
                int number = Integer.parseInt(ScanInput.getInput());
                if (number < 1 || number > 6) {
                    return;
                }
                mat.setHandCard(monster, number);
            }
        }
    }

    public void setCalculator(Card card, Mat mat) {
        int level = 0;
        for (int i = 0; i < 5; i++) {
            if (mat.getMonsterZone(i) != null && mat.getMonsterZone(i).isOn())
                level += mat.getMonsterZone(i).getLevel();
        }
        card.setAttack(level * 300);
    }

    private void effectCheckerInAttack(Mat attackerMat, Mat defenderMat, Monster attacker, Monster defender) {
        if (isCardInArray(attacker, monsterEffect1)) {
            if (attacker.isOn() && attacker.isFirstEffectUse()) {
                for (int i = 0; i < 5; i++) {
                    Monster monsterInMat = attackerMat.getMonsterZone(i);
                    if (monsterInMat != null) {
                        monsterInMat.setAttack(monsterInMat.getAttack() + 400);
                    }
                }
                attacker.setFirstEffectUse(false);
            }
        } else if (isCardInArray(attacker, monsterEffect2)) {

        } else if (isCardInArray(attacker, monsterEffect3)) {
            if (attacker.isOn() && attacker.isFirstEffectUse()) {
                defender.setAttack(0);
                attacker.setFirstEffectUse(true);
            }

        } else if (isCardInArray(attacker, monsterEffect4)) {

        } else if (isCardInArray(attacker, monsterEffect5)) {

        } else if (isCardInArray(attacker, monsterEffect6)) {
            //fot
        } else if (isCardInArray(attacker, monsterEffect7)) {
            //fot
        } else if (isCardInArray(attacker, monsterEffect8)) {
            ArrayList<Card> cards = opponentTurnPlayer.getMat().getGraveyard();
            String cardName = ScanInput.getInput();
            for (Card card : cards) {
                if (card.equals(cardName)) {
                    Monster monster1 = (Monster) card;
                    Monster monster2 = new Monster(monster1.getName(), monster1.getLevel(), monster1.getAttribute()
                            , monster1.getMonsterType(), monster1.getCardType(), monster1.getAttack(), monster1.getDefence(), monster1.getDescription(), monster1.getPrice());
                    attacker = monster2;
                }
            }

        } else if (isCardInArray(attacker, monsterEffect9)) {
            if (!attacker.isOn()) {
                opponentTurnPlayer.changeLifePoint(-1000);
            }

        } else if (isCardInArray(attacker, monsterEffect10)) {
            TerminalOutput.output("You want tribute or not\n1.Yes\n2.No");
            String input = ScanInput.getInput();
            if (input.equals("2")) {
                attacker.setAttack(1900);
            } else {
                if (isEnoughCardForTribute()) {
                    summonWithTribute(attacker);
                } else {
                    TerminalOutput.output("there are not enough cards for tribute");
                    attacker.setAttack(1900);
                }
            }

        } else if (isCardInArray(attacker, monsterEffect11)) {

        } else if (isCardInArray(attacker, monsterEffect12)) {

        } else if (isCardInArray(attacker, monsterEffect13)) {
            //fot
        } else if (isCardInArray(attacker, monsterEffect14)) {
            if (defender.isAttack()) {
                if (attacker.getAttack() < defender.getAttack()) {
                    for (int i = 0; i < 5; i++) {
                        if (defenderMat.getMonsterZone(i).getName().equals(defender.getName())) {
                            defenderMat.addCardToGraveyard(defenderMat.getMonsterZone(i));
                            defenderMat.setMonsterZone(i, null);
                            break;
                        }
                    }
                }
            } else {
                if (attacker.getAttack() < defender.getDefence()) {
                    if (attacker.getAttack() < defender.getAttack()) {
                        for (int i = 0; i < 5; i++) {
                            if (defenderMat.getMonsterZone(i).getName().equals(defender.getName())) {
                                defenderMat.addCardToGraveyard(defenderMat.getMonsterZone(i));
                                defenderMat.setMonsterZone(i, null);
                                break;
                            }
                        }
                    }
                }
            }
        } else if (isCardInArray(attacker, monsterEffect15)) {
            if (defender.isAttack()) {
                if (attacker.getAttack() > defender.getAttack()) {
                    if (attacker.getAttack() < defender.getAttack()) {
                        for (int i = 0; i < 5; i++) {
                            if (attackerMat.getMonsterZone(i).getName().equals(defender.getName())) {
                                attackerMat.addCardToGraveyard(attackerMat.getMonsterZone(i));
                                attackerMat.setMonsterZone(i, null);
                                break;
                            }
                        }
                    }
                }
            } else {
                if (attacker.getAttack() > defender.getDefence()) {
                    if (attacker.getAttack() < defender.getAttack()) {
                        for (int i = 0; i < 5; i++) {
                            if (attackerMat.getMonsterZone(i).getName().equals(defender.getName())) {
                                attackerMat.addCardToGraveyard(attackerMat.getMonsterZone(i));
                                attackerMat.setMonsterZone(i, null);
                                break;
                            }
                        }
                    }
                }
            }
        } else if (isCardInArray(attacker, monsterEffect16)) {

        } else if (isCardInArray(attacker, monsterEffect17)) {

        }
    }

    private void effectCheckerInActiveEffect(Card card) {
        Mat opponentMat = opponentTurnPlayer.getMat();
        Mat currentMat = currentTurnPlayer.getMat();
        if (isCardInArray(card, spellTrapEffect1)) {
            // retual summon
        } else if (isCardInArray(card, spellTrapEffect2)) {
            for (Card card1 : currentTurnPlayer.getMainDeckCard()) {
                if (card1 instanceof Trap || card1 instanceof Spell) {
                    if (card1.getType().equals("Field") && currentMat.getHandCard(5) == null) {
                        currentMat.addToHand(card1);
                    }
                }
            }
            boolean isDelete = false;
            for (int i = 0; i < 5; i++) {
                if (currentMat.getSpellAndTrapZone(i) != null && currentMat.getSpellAndTrapZone(i).getName().equalsIgnoreCase("Terraforming")) {
                    currentMat.addCardToGraveyard(currentMat.getSpellAndTrapZone(i));
                    currentMat.deleteSpellZone(i);
                    isDelete = true;
                }
            }
            if (isDelete = false) {
                for (int i = 0; i < 6; i++) {
                    if (currentMat.getHandCard(i) != null && currentMat.getHandCard(i).getName().equals("Terraforming")) {
                        currentMat.addCardToGraveyard(currentMat.getHandCard(i));
                        currentMat.deleteHandCard(i);
                    }
                }
            }
            if (currentTurnPlayer.isSpell) {
                currentTurnPlayer.changeLifePoint(500);
            }
        } else if (isCardInArray(card, spellTrapEffect3)) {
            if (currentMat.getHandCard(4) == null) {
                ArrayList<Card> cards = currentTurnPlayer.getMainDeckCard();
                currentTurnPlayer.getMat().addToHand(cards.get(cards.size() - 1));
                currentTurnPlayer.deleteCard();
                currentTurnPlayer.getMat().addToHand(cards.get(cards.size() - 2));
                currentTurnPlayer.deleteCard();
            }
            boolean isDelete = false;
            for (int i = 0; i < 5; i++) {
                if (currentMat.getSpellAndTrapZone(i) != null && currentMat.getSpellAndTrapZone(i).getName().equalsIgnoreCase("Pot of Greed")) {
                    currentMat.addCardToGraveyard(currentMat.getSpellAndTrapZone(i));
                    currentMat.deleteSpellZone(i);
                    isDelete = true;
                }
            }
            if (isDelete = false) {
                for (int i = 0; i < 6; i++) {
                    if (currentMat.getHandCard(i) != null && currentMat.getHandCard(i).getName().equals("Pot of Greed")) {
                        currentMat.addCardToGraveyard(currentMat.getHandCard(i));
                        currentMat.deleteHandCard(i);
                    }
                }
            }
            if (currentTurnPlayer.isSpell) {
                currentTurnPlayer.changeLifePoint(500);
            }
        } else if (isCardInArray(card, spellTrapEffect4)) {
            for (int i = 0; i < 5; i++) {
                if (opponentMat.getMonsterZone(i) != null) {
                    opponentMat.addCardToGraveyard(opponentMat.getMonsterZone(i));
                }
                opponentMat.deleteMonsterZone(i);
            }
            boolean isDelete = false;
            for (int i = 0; i < 5; i++) {
                if (currentMat.getSpellAndTrapZone(i) != null && currentMat.getSpellAndTrapZone(i).getName().equalsIgnoreCase("Reigeki")) {
                    currentMat.addCardToGraveyard(currentMat.getSpellAndTrapZone(i));
                    currentMat.deleteSpellZone(i);
                    isDelete = true;
                }
            }
            if (isDelete = false) {
                for (int i = 0; i < 6; i++) {
                    if (currentMat.getHandCard(i) != null && currentMat.getHandCard(i).getName().equals("Raigeki")) {
                        currentMat.addCardToGraveyard(currentMat.getHandCard(i));
                        currentMat.deleteHandCard(i);
                    }
                }
            }
            if (currentTurnPlayer.isSpell) {
                currentTurnPlayer.changeLifePoint(500);
            }
        } else if (isCardInArray(card, spellTrapEffect5)) {
            int input;
            Monster monster;
            while (true) {
                TerminalOutput.output("Enter monster number");
                input = Integer.parseInt(ScanInput.getInput());
                if (input < 1 || input > 5) {
                    TerminalOutput.output("Invalid number!");
                } else {
                    break;
                }
            }
            if (input == 1) {
                input = 2;
            } else if (input == 2) {
                input = 1;
            } else if (input == 3) {
                input = 4;
            } else if (input == 4) {
                input = 0;
            } else {
                input = 4;
            }
            monster = opponentMat.getMonsterZone(input);
            if (monster == null) {
                TerminalOutput.output("No Monster");
                return;
            }
            opponentMat.deleteMonsterZone(input);
            currentMat.addMonster(monster);
            for (int i = 0; i < 5; i++) {
                if (currentMat.getSpellAndTrapZone(i) != null && currentMat.getSpellAndTrapZone(i).getName().equalsIgnoreCase("Reigeki")) {
                    currentMat.addCardToGraveyard(currentMat.getSpellAndTrapZone(i));
                    currentMat.deleteSpellZone(i);
                }
            }
            if (currentTurnPlayer.isSpell) {
                currentTurnPlayer.changeLifePoint(500);
            }
        } else if (isCardInArray(card, spellTrapEffect6)) {
            for (int i = 0; i < 5; i++) {
                if (opponentMat.getSpellAndTrapZone(i) != null) {
                    opponentMat.addCardToGraveyard(opponentMat.getSpellAndTrapZone(i));
                }
                opponentMat.deleteSpellZone(i);
            }
            for (int i = 0; i < 5; i++) {
                if (currentMat.getSpellAndTrapZone(i) != null && currentMat.getSpellAndTrapZone(i).getName().equalsIgnoreCase("Harpie's Feather Duster")) {
                    currentMat.addCardToGraveyard(currentMat.getSpellAndTrapZone(i));
                    currentMat.deleteSpellZone(i);
                }
            }
            if (currentTurnPlayer.isSpell) {
                currentTurnPlayer.changeLifePoint(500);
            }
        } else if (isCardInArray(card, spellTrapEffect7)) {
            if (currentTurnPlayer.isSpell) {
                currentTurnPlayer.changeLifePoint(500);
            }
        } else if (isCardInArray(card, spellTrapEffect8)) {
            for (int i = 0; i < 5; i++) {
                if (currentMat.getSpellAndTrapZone(i) != null && currentMat.getMonsterZone(i) != null) {
                    currentMat.addCardToGraveyard(currentMat.getMonsterZone(i));
                }
                currentMat.deleteMonsterZone(i);
                if (opponentMat.getMonsterZone(i) != null) {
                    opponentMat.addCardToGraveyard(opponentMat.getMonsterZone(i));
                }
                opponentMat.deleteMonsterZone(i);
            }
            for (int i = 0; i < 5; i++) {
                if (currentMat.getSpellAndTrapZone(i) != null && currentMat.getSpellAndTrapZone(i).getName().equalsIgnoreCase("Harpie's Feather Duster")) {
                    currentMat.addCardToGraveyard(currentMat.getSpellAndTrapZone(i));
                    currentMat.deleteSpellZone(i);
                }
            }
            if (currentTurnPlayer.isSpell) {
                currentTurnPlayer.changeLifePoint(500);
            }
        } else if (isCardInArray(card, spellTrapEffect9)) {
            if (currentTurnPlayer.isSpell) {
                currentTurnPlayer.changeLifePoint(500);
            }
        } else if (isCardInArray(card, spellTrapEffect10)) {
            currentTurnPlayer.setSpell(true);
        } else if (isCardInArray(card, spellTrapEffect11)) {
            if (currentTurnPlayer.isSpell) {
                currentTurnPlayer.changeLifePoint(500);
            }
        } else if (isCardInArray(card, spellTrapEffect12)) {
            TerminalOutput.output("Select Spell or trap");
            int input = Integer.parseInt(ScanInput.getInput());
            if (input < 1 || input > 6) {
                TerminalOutput.output("Invalid number");
                return;
            }
            currentTurnPlayer.getMat().deleteHandCard(input);
            TerminalOutput.output("Enter how many spells you want to destroy");
            input = Integer.parseInt(ScanInput.getInput());
            for (int i = 0; i < input; i++) {
                input = Integer.parseInt(ScanInput.getInput());
                if (input < 1 || input > 5) {
                    TerminalOutput.output("Invalid number");
                    return;
                }
                if (input == 1) {
                    input = 2;
                } else if (input == 2) {
                    input = 1;
                } else if (input == 3) {
                    input = 4;
                } else if (input == 4) {
                    input = 0;
                } else {
                    input = 4;
                }
                Card card1 = opponentMat.getSpellAndTrapZone(input);
                opponentMat.getGraveyard().add(card1);
                opponentMat.deleteSpellZone(input);
            }
            if (currentTurnPlayer.isSpell) {
                currentTurnPlayer.changeLifePoint(500);
            }
        } else if (isCardInArray(card, spellTrapEffect13)) {
            TerminalOutput.output("Select Spell or trap");
            int input = Integer.parseInt(ScanInput.getInput());
            if (input < 1 || input > 5) {
                TerminalOutput.output("Invalid number");
                return;
            }
            if (input == 1) {
                input = 2;
            } else if (input == 2) {
                input = 1;
            } else if (input == 3) {
                input = 4;
            } else if (input == 4) {
                input = 0;
            } else {
                input = 4;
            }
            Card card1 = opponentMat.getSpellAndTrapZone(input);
            opponentMat.getGraveyard().add(card1);
            opponentMat.deleteSpellZone(input);
            if (currentTurnPlayer.isSpell) {
                currentTurnPlayer.changeLifePoint(500);
            }
        } else if (isCardInArray(card, spellTrapEffect14)) {
            if (currentTurnPlayer.isSpell) {
                currentTurnPlayer.changeLifePoint(500);
            }
        } else if (isCardInArray(card, spellTrapEffect15)) {
            for (int i = 0; i < 5; i++) {
                Monster monster = currentTurnPlayer.getMat().getMonsterZone(i);
                if (monster == null) {
                    continue;
                }
                if (monster.getType().equals("Fiend") || monster.getType().equals("Spellcaster") || monster.getType().equals("Fairy")) {
                    if (monster.isAttack())
                        monster.setAttack(monster.getAttack() + 200);
                    else
                        monster.setDefence(monster.getDefence() + 200);
                }
            }
            if (currentTurnPlayer.isSpell) {
                currentTurnPlayer.changeLifePoint(500);
            }
        } else if (isCardInArray(card, spellTrapEffect16)) {
            for (int i = 0; i < 5; i++) {
                Monster monster = currentTurnPlayer.getMat().getMonsterZone(i);
                if (monster == null) {
                    continue;
                }
                if (monster.getType().equals("Beast-Warrior") || monster.getType().equals("Beast") || monster.getType().equals("Insect")) {
                    if (monster.isAttack())
                        monster.setAttack(monster.getAttack() + 200);
                    else
                        monster.setDefence(monster.getDefence() + 200);
                }
            }
            if (currentTurnPlayer.isSpell) {
                currentTurnPlayer.changeLifePoint(500);
            }
        } else if (isCardInArray(card, spellTrapEffect17)) {
            for (int i = 0; i < 5; i++) {
                Monster monster = currentTurnPlayer.getMat().getMonsterZone(i);
                if (monster == null) {
                    continue;
                }
                if (monster.getType().equals("Beast-Type")) {
                    monster.setAttack(monster.getAttack() + 100 * currentTurnPlayer.getMat().getGraveyard().size());
                }
            }
            if (currentTurnPlayer.isSpell) {
                currentTurnPlayer.changeLifePoint(500);
            }
        } else if (isCardInArray(card, spellTrapEffect18)) {
            TerminalOutput.output("Enter monster number");
            int input = Integer.parseInt(ScanInput.getInput());
            if (input < 1 || input > 5) {
                TerminalOutput.output("Invalid number!");
                return;
            }
            if (input == 1) {
                input = 2;
            } else if (input == 2) {
                input = 1;
            } else if (input == 3) {
                input = 4;
            } else if (input == 4) {
                input = 0;
            } else {
                input = 4;
            }
            Monster monster = currentTurnPlayer.getMat().getMonsterZone(input);
            if (monster == null) {
                TerminalOutput.output("No monster!");
                return;
            }
            if (!monster.getType().equals("Aqua")) {
                TerminalOutput.output("Invalid Monster");
                return;
            }
            monster.setAttack(monster.getAttack() + 500);
            monster.setDefence(monster.getDefence() - 400);
            if (currentTurnPlayer.isSpell) {
                currentTurnPlayer.changeLifePoint(500);
            }
        } else if (isCardInArray(card, spellTrapEffect19)) {
            TerminalOutput.output("Enter monster number");
            int input = Integer.parseInt(ScanInput.getInput());
            if (input < 1 || input > 5) {
                TerminalOutput.output("Invalid number!");
                return;
            }
            if (input == 1) {
                input = 2;
            } else if (input == 2) {
                input = 1;
            } else if (input == 3) {
                input = 4;
            } else if (input == 4) {
                input = 0;
            } else {
                input = 4;
            }
            Monster monster = currentTurnPlayer.getMat().getMonsterZone(input);
            if (monster == null) {
                TerminalOutput.output("No monster!");
                return;
            }
            if (!monster.isOn()) {
                TerminalOutput.output("Invalid monster!");
                return;
            }
            if (!monster.getType().equals("Fiend") && !monster.getType().equals("Spellcaster")) {
                TerminalOutput.output("Invalid Monster");
                return;
            }
            monster.setAttack(monster.getAttack() + 400);
            monster.setDefence(monster.getDefence() - 200);
            if (currentTurnPlayer.isSpell) {
                currentTurnPlayer.changeLifePoint(500);
            }
        } else if (isCardInArray(card, spellTrapEffect20)) {
            TerminalOutput.output("Enter monster number");
            int input = Integer.parseInt(ScanInput.getInput());
            if (input < 1 || input > 5) {
                TerminalOutput.output("Invalid number!");
                return;
            }
            if (input == 1) {
                input = 2;
            } else if (input == 2) {
                input = 1;
            } else if (input == 3) {
                input = 4;
            } else if (input == 4) {
                input = 0;
            } else {
                input = 4;
            }
            Monster monster = currentTurnPlayer.getMat().getMonsterZone(input);
            if (monster == null) {
                TerminalOutput.output("No monster!");
                return;
            }
            if (!monster.isOn()) {
                TerminalOutput.output("Invalid monster!");
                return;
            }
            monster.setAttack(monster.getAttack() + 500);
            if (currentTurnPlayer.isSpell) {
                currentTurnPlayer.changeLifePoint(500);
            }
        } else if (isCardInArray(card, spellTrapEffect21)) {
            TerminalOutput.output("Enter monster number");
            int input = Integer.parseInt(ScanInput.getInput());
            if (input < 1 || input > 5) {
                TerminalOutput.output("Invalid number!");
                return;
            }
            if (input == 1) {
                input = 2;
            } else if (input == 2) {
                input = 1;
            } else if (input == 3) {
                input = 4;
            } else if (input == 4) {
                input = 0;
            } else {
                input = 4;
            }
            Monster monster = currentTurnPlayer.getMat().getMonsterZone(input);
            if (monster == null) {
                TerminalOutput.output("No monster!");
                return;
            }
            if (!monster.isOn()) {
                TerminalOutput.output("Invalid monster!");
                return;
            }
            int cnt = 0;
            for (int i = 0; i < 5; i++) {
                if (currentTurnPlayer.getMat().getMonsterZone(i).isOn())
                    cnt++;
            }
            if (monster.isAttack()) {
                monster.setAttack(monster.getAttack() + cnt * 800);
            } else {
                monster.setDefence(monster.getDefence() + cnt * 800);
            }
            if (currentTurnPlayer.isSpell) {
                currentTurnPlayer.changeLifePoint(500);
            }
        } else if (isCardInArray(card, spellTrapEffect22)) {
            TerminalOutput.output("Enter monster number");
            int input = Integer.parseInt(ScanInput.getInput());
            if (input < 1 || input > 5) {
                TerminalOutput.output("Invalid number!");
                return;
            }
            if (input == 1) {
                input = 2;
            } else if (input == 2) {
                input = 1;
            } else if (input == 3) {
                input = 4;
            } else if (input == 4) {
                input = 0;
            } else {
                input = 4;
            }
            Monster monster = currentTurnPlayer.getMat().getMonsterZone(input);
            if (monster == null) {
                TerminalOutput.output("No monster!");
                return;
            }
            if (!monster.isOn()) {
                TerminalOutput.output("Invalid monster!");
                return;
            }
            if (monster.getType().equals("Warrior")) {
                if (monster.isAttack()) {
                    monster.setAttack(monster.getAttack() + monster.getDefence());
                } else {
                    monster.setDefence(monster.getAttack() + monster.getDefence());
                }
            }
            if (currentTurnPlayer.isSpell) {
                currentTurnPlayer.changeLifePoint(500);
            }
        } else if (isCardInArray(card, spellTrapEffect23)) {

        } else if (isCardInArray(card, spellTrapEffect24)) {
            if (isDoAttack) {
                for (int i = 0; i < 5; i++) {
                    Monster monster = opponentMat.getMonsterZone(i);
                    if (monster != null && monster.getName().equals(onAttack.getName())) {
                        opponentTurnPlayer.setLifePoint(opponentTurnPlayer.getLifePoint() - onAttack.getAttack());
                        opponentMat.deleteMonsterZone(i);
                        permissionForAttack = false;
                        return;
                    }
                }
            }
        } else if (isCardInArray(card, spellTrapEffect25)) {
            if (isDoAttack) {
                for (int i = 0; i < 5; i++) {
                    Monster monster = opponentMat.getMonsterZone(i);
                    if (monster.isAttack()) {
                        opponentMat.getGraveyard().add(monster);
                        opponentMat.deleteMonsterZone(i);
                    }
                }
            }
        } else if (isCardInArray(card, spellTrapEffect26)) {
            TerminalOutput.output("Enter a card name");
            String input = ScanInput.getInput();
            boolean doesOpponentHaveThis = false;
            for (int i = 0; i < 6; i++) {
                if (opponentMat.getHandCard(i).getName().equals(input))
                    doesOpponentHaveThis = true;
            }
            if (doesOpponentHaveThis) {
                for (int i = 0; i < 6; i++) {
                    if (opponentMat.getHandCard(i).getName().equals(input)) {
                        opponentMat.deleteHandCard(i);
                    }
                }
            } else {
                for (int i = 0; i < 6; i++) {
                    if (currentMat.getHandCard(i) != null) {
                        currentMat.addCardToGraveyard(currentMat.getHandCard(i));
                        currentMat.deleteHandCard(i);
                    }
                }
            }
        } else if (isCardInArray(card, spellTrapEffect27)) {
            if (opponentTurnPlayer.isSummoned() && summonedMonster.getAttack() > 1000) {
                for (int i = 0; i < 5; i++) {
                    if (opponentMat.getMonsterZone(i) != null && opponentMat.getMonsterZone(i).getName().equals(summonedMonster.getName()))
                        opponentMat.deleteMonsterZone(i);
                }
            }
        } else if (isCardInArray(card, spellTrapEffect28)) {
            if (currentTurnPlayer.isSummoned() || opponentTurnPlayer.isSummoned()) {
                for (int i = 0; i < 5; i++) {
                    if (currentMat.getMonsterZone(i) != null)
                        currentMat.deleteMonsterZone(i);
                    if (opponentMat.getMonsterZone(i) != null)
                        opponentMat.deleteMonsterZone(i);
                }
            }
        } else if (isCardInArray(card, spellTrapEffect29)) {
            opponentTurnPlayer.setPermissionForDrawPhase(false);
        } else if (isCardInArray(card, spellTrapEffect30)) {
            if (isDoAttack) {
                permissionForAttack = false;
                nextPhase();
            }
        } else if (isCardInArray(card, spellTrapEffect31)) {

        } else if (isCardInArray(card, spellTrapEffect32)) {

        } else if (isCardInArray(card, spellTrapEffect33)) {
            Card haunted = null;
            for (Card card1 : currentTurnPlayer.getMat().getGraveyard()) {
                if (card1 instanceof Monster) {
                    haunted = card1;
                    haunted.setAttack(true);
                    break;
                }
            }
            if (haunted != null && !currentMat.isMonsterZoneIsFull()) {
                currentMat.addMonster((Monster) haunted);
            }
        }
    }


    public void summonWithTribute(Monster monster) {
        int counter = 0;
        String input;
        Mat mat = currentTurnPlayer.getMat();
        while (true) {
            input = ScanInput.getInput();
            int address = Integer.parseInt(input);
            if (mat.getMonsterZone(address) == null) {
                TerminalOutput.output("there no  monster one this address");
                continue;
            }
            if (mat.getMonsterZone(address) != null) {
                counter += 1;
                mat.deleteMonsterZone(address);
            }
            if (monster.getLevel() <= 6 && counter == 1)
                break;
            if (monster.getLevel() > 6) {
                if (monster.getLevel() > 6 && counter == 2 && !monster.getName().equals("Gate Guardian")) {
                    break;
                }
                if (monster.getLevel() > 6 && counter == 3 && monster.getName().equals("Gate Guardian")) {
                    break;
                }
            }
        }
        summonSuccessful(monster, mat);
    }

    private void summonSuccessful(Monster monster, Mat mat) {
        terminalOutput = "summon successfully";
        monster.setAttack(true);
        monster.setOn(true);
        mat.addMonster(monster);
        currentTurnPlayer.setCurrentSelectedCard(null);
        currentTurnPlayer.setSelectedName(null);
        currentTurnPlayer.getMat().deleteHandCard(currentTurnPlayer.getHandNumber());
        currentTurnPlayer.setHandNumber(-1);
        currentTurnPlayer.setSummoned(true);
    }

    public boolean isEnoughCardForTribute() {
        Mat mat = currentTurnPlayer.getMat();
        Monster monster = (Monster) currentTurnPlayer.getCurrentSelectedCard();
        if (monster.getLevel() <= 4)
            return true;
        if (monster.getLevel() <= 6)
            return mat.getNumberOfCardMonsterZone() >= 1;
        return mat.getNumberOfCardMonsterZone() >= 2;
    }

    public void set() {
        Card card = currentTurnPlayer.getCurrentSelectedCard();
        Mat mat = currentTurnPlayer.getMat();
        if (!canCardBeSetAfterTerratiger) {
            canCardBeSetAfterTerratiger = true;
            return;
        }
        if (card instanceof Monster || card == null) {
            if (card == null) {
                terminalOutput = "no card selected yet";
                return;
            }
            if (!currentTurnPlayer.getSelectedName().equals("Hand")) {
                terminalOutput = "you can't set this card";
                return;
            }
            if (card instanceof Monster && (!phase.getCurrentPhase().equals("First Main Phase") && !phase.getCurrentPhase().equals("Second Main Phase"))) {
                terminalOutput = "you can't do this action phase";
                return;
            }
            if (mat.isMonsterZoneIsFull()) {
                terminalOutput = "monster card zone is full";
                return;
            }
            if (currentTurnPlayer.isSummoned()) {
                terminalOutput = "you already summoned/set on this turn";
                return;
            }
            if (!isEnoughCardForTribute()) {
                terminalOutput = "there are not enough cards for tribute";
                return;
            }
            Monster monster = (Monster) currentTurnPlayer.getCurrentSelectedCard();
            if (monster.getLevel() > 4) {
                setWithTribute(monster);
                return;
            }
            monster.setAttack(false);
            monster.setOn(false);
            System.out.println(card.getCardType());
            mat.addMonster(monster);
            currentTurnPlayer.setCurrentSelectedCard(null);
            currentTurnPlayer.setSelectedName(null);
            currentTurnPlayer.getMat().deleteHandCard(currentTurnPlayer.getHandNumber());
            currentTurnPlayer.setHandNumber(-1);
            currentTurnPlayer.setSummoned(true);
            terminalOutput = "set successfully";
        } else if (card instanceof Spell || card instanceof Trap) {
            if (!currentTurnPlayer.getSelectedName().equals("Hand")) {
                terminalOutput = "you can't set this card";
                return;
            }
            if ((!phase.getCurrentPhase().equals("First Main Phase") && !phase.getCurrentPhase().equals("Second Main Phase"))) {
                terminalOutput = "you can't do this action phase";
                return;
            }
            if (mat.isSpellAndTrapZoneIsFull()) {
                terminalOutput = "spell card zone is full";
                return;
            }
            card.setOn(false);
            if (card.getType().equals("Field")) {
                mat.setFieldZone(null);
                mat.setFieldZone(card);
            } else {
                mat.addSpellOrTrap(card);
            }
            currentTurnPlayer.setCurrentSelectedCard(null);
            currentTurnPlayer.setSelectedName(null);
            currentTurnPlayer.getMat().deleteHandCard(currentTurnPlayer.getHandNumber());
            currentTurnPlayer.setHandNumber(-1);
            currentTurnPlayer.setSummoned(true);
            terminalOutput = "set successfully";
        }
    }

    public String phase2Set() {
        Card card = currentTurnPlayer.getCurrentSelectedCard();
        Mat mat = currentTurnPlayer.getMat();
        if (!canCardBeSetAfterTerratiger) {
            canCardBeSetAfterTerratiger = true;
            return "";
        }
        if (card instanceof Monster || card == null) {
            if (card == null) {
                return "no card selected yet";

            }
            if (!currentTurnPlayer.getSelectedName().equals("Hand")) {
                return "you can't set this card";

            }
            if (card instanceof Monster && (!phase.getCurrentPhase().equals("First Main Phase") && !phase.getCurrentPhase().equals("Second Main Phase"))) {
                return "you can't do this action phase";

            }
            if (mat.isMonsterZoneIsFull()) {
                return "monster card zone is full";

            }
            if (currentTurnPlayer.isSummoned()) {
                return "you already summoned/set on this turn";

            }
            if (!isEnoughCardForTribute()) {
                return "there are not enough cards for tribute";

            }
            Monster monster = (Monster) currentTurnPlayer.getCurrentSelectedCard();
            if (monster.getLevel() > 4) {
                setWithTribute(monster);
                return "";
            }
            monster.setAttack(false);
            monster.setOn(false);
            mat.addMonster(monster);
            currentTurnPlayer.setCurrentSelectedCard(null);
            currentTurnPlayer.setSelectedName(null);
            currentTurnPlayer.getMat().deleteHandCard(currentTurnPlayer.getHandNumber());
            currentTurnPlayer.setHandNumber(-1);
            currentTurnPlayer.setSummoned(true);
            return "set successfully";
        } else if (card instanceof Spell || card instanceof Trap) {
            if (!currentTurnPlayer.getSelectedName().equals("Hand")) {
                return "you can't set this card";

            }
            if ((!phase.getCurrentPhase().equals("First Main Phase") && !phase.getCurrentPhase().equals("Second Main Phase"))) {
                return "you can't do this action phase";

            }
            if (mat.isSpellAndTrapZoneIsFull()) {
                return "spell card zone is full";

            }
            card.setOn(false);
            if (card.getType().equals("Field")) {
                mat.setFieldZone(null);
                mat.setFieldZone(card);
            } else {
                mat.addSpellOrTrap(card);
            }
            currentTurnPlayer.setCurrentSelectedCard(null);
            currentTurnPlayer.setSelectedName(null);
            currentTurnPlayer.getMat().deleteHandCard(currentTurnPlayer.getHandNumber());
            currentTurnPlayer.setHandNumber(-1);
            int flag = 0;
            for (int i = 0; i < 5; i++) {
                if (currentTurnPlayer.getMat().getSpellAndTrapZone(i) == null){
                    flag = i;
                    break;
                }
            }
            currentTurnPlayer.getMat().setSpellAndTrapZone(flag,card);
            currentTurnPlayer.setSummoned(true);
            return "set successfully";
        }
        return "";
    }

    public void setWithTribute(Monster monster) {
        int counter = 0;
        String input;
        Mat mat = currentTurnPlayer.getMat();
        while (true) {
            input = ScanInput.getInput();
            int address = Integer.parseInt(input);
            if (mat.getMonsterZone(address) == null) {
                TerminalOutput.output("there no  monster one this address");
                continue;
            }
            if (mat.getMonsterZone(address) != null) {
                counter += 1;
                mat.deleteMonsterZone(address);
            }
            if (monster.getLevel() <= 6 && counter == 1)
                break;
            if (monster.getLevel() > 6 && counter == 2) {
                break;
            }
        }
        monster.setAttack(false);
        monster.setOn(false);
        mat.addMonster(monster);
        currentTurnPlayer.setCurrentSelectedCard(null);
        currentTurnPlayer.setSelectedName(null);
        currentTurnPlayer.getMat().deleteHandCard(currentTurnPlayer.getHandNumber());
        currentTurnPlayer.setHandNumber(-1);
        currentTurnPlayer.setSummoned(true);
        terminalOutput = "set successfully";
    }


    public void changeCardPosition(boolean isOnAttack) {
        Card card = currentTurnPlayer.getCurrentSelectedCard();
        if (currentTurnPlayer.getCurrentSelectedCard() == null) {
            terminalOutput = "no card is selected yet";
            return;
        }
        if (!currentTurnPlayer.getSelectedName().equals("Monster")) {
            terminalOutput = "you can;t change this card position";
            return;
        }
        if ((!phase.getCurrentPhase().equals("First Main Phase") && !phase.getCurrentPhase().equals("Second Main Phase"))) {
            terminalOutput = "you can't do this action phase";
            return;
        }
        if ((isOnAttack && card.isAttack()) || (!isOnAttack && !card.isAttack())) {
            terminalOutput = "this card is already in the wanted position";
            return;
        }
        if (currentTurnPlayer.getMat().isChangedCard(currentTurnPlayer.getNumberOfMonsterZone())) {
            terminalOutput = "you already changed this card position in this turn";
            return;
        }
        terminalOutput = "monster card position changed successfully";
        currentTurnPlayer.getMat().setIsChanged(true, currentTurnPlayer.getNumberOfMonsterZone());
        card.setAttack(isOnAttack);
    }

    public void flipSummon() {
        Card selectedCard = currentTurnPlayer.getCurrentSelectedCard();
        if (selectedCard == null) {
            terminalOutput = "no card is selected yet";
            return;
        }
        Mat mat = currentTurnPlayer.getMat();
        boolean isInMonsters = false;
        for (int i = 0; i < 5; i++) {
            if (mat.getMonsterZone(i) != null && mat.getMonsterZone(i).getName().equals(selectedCard.getName()))
                isInMonsters = true;
        }
        if (!isInMonsters) {
            terminalOutput = "you can’t change this card position";
            return;
        }
        if (!phase.getCurrentPhase().equals("First Main Phase") && !phase.getCurrentPhase().equals("Second Main Phase")) {
            terminalOutput = "you can’t do this action in this phase";
            return;
        }
        if (!(!selectedCard.isOn() && !selectedCard.isAttack())) {
            terminalOutput = "you can’t flip summon this card";
            return;
        }
        terminalOutput = "flip summoned successfully";
        selectedCard.setAttack(true);
        selectedCard.setOn(true);
        selectedCard.setFirstTimeFlipping(true);
    }

    public void attack(int number) {
        if (number == 1) {
            number = 2;
        } else if (number == 2) {
            number = 1;
        } else if (number == 4) {
            number = 0;
        } else if (number == 5) {
            number = 4;
        }
        isDoAttack = true;
        Card selectedCard = currentTurnPlayer.getCurrentSelectedCard();
        onAttack = (Monster) currentTurnPlayer.getCurrentSelectedCard();
        if (selectedCard == null) {
            terminalOutput = "no card is selected yet";
            return;
        }
        Mat mat = currentTurnPlayer.getMat();
        boolean isInMonsters = false;
        for (int i = 0; i < 5; i++) {
            if (mat.getMonsterZone(i) != null && mat.getMonsterZone(i).getName().equals(selectedCard.getName()))
                isInMonsters = true;
        }
        if (!isInMonsters) {
            terminalOutput = "you can’t attack with this card";
            return;
        }
        if (!phase.getCurrentPhase().equals("Battle Phase")) {
            terminalOutput = "you can’t do this action in this phase";
            return;
        }
        Mat opponentMat = opponentTurnPlayer.getMat();
        if (opponentMat.getMonsterZone(number) == null) {
            terminalOutput = "there is no card to attack here";
            return;
        }
        if (!isAi) {
            checkForQuickChangeTurn();
        }
        if (!permissionForAttack) {
            return;
        }
        Card attackedCard = opponentMat.getMonsterZone(number);
        effectCheckerInAttack(currentTurnPlayer.getMat(), opponentTurnPlayer.getMat(), (Monster) selectedCard, (Monster) attackedCard);
        if (attackedCard.isAttack()) {
            int attackDifference = getAttack(selectedCard) - getAttack(attackedCard);
            if (attackDifference > 0) {
                opponentTurnPlayer.setLifePoint(opponentTurnPlayer.getLifePoint() - attackDifference);
                terminalOutput = "your opponent’s monster is destroyed and your opponent receives " +
                        attackDifference + " battle damage";
                opponentMat.addCardToGraveyard(attackedCard);
                opponentMat.setMonsterZone(number, null);
            } else if (attackDifference == 0) {
                terminalOutput = "both you and your opponent monster cards are destroyed and no one receives damage";
                opponentMat.setMonsterZone(number, null);
                for (int i = 0; i < 5; i++) {
                    if (mat.getMonsterZone(i).getName().equals(selectedCard.getName())) {
                        mat.setMonsterZone(i, null);
                        break;
                    }
                }
            } else {
                terminalOutput = "Your monster card is destroyed and you received " +
                        (-attackDifference) + "battle damage";
                currentTurnPlayer.setLifePoint(currentTurnPlayer.getLifePoint() + attackDifference);
                for (int i = 0; i < 5; i++) {
                    if (mat.getMonsterZone(i) != null && mat.getMonsterZone(i).getName().equals(selectedCard.getName())) {
                        mat.addCardToGraveyard(mat.getMonsterZone(i));
                        mat.setMonsterZone(i, null);
                        break;
                    }
                }
            }
        } else {
            String prefix = "";
            if (attackedCard.isOn())
                prefix = "opponents monster card was " + attackedCard.getName() + " and ";
            int differenceOfAttackAndDefence = getAttack(selectedCard) - attackedCard.getDefence();
            if (differenceOfAttackAndDefence > 0) {
                terminalOutput = prefix + "the defense position monster is destroyed";
                opponentMat.setMonsterZone(number, null);
            } else if (differenceOfAttackAndDefence == 0) {
                terminalOutput = prefix + "no card is destroyed";
            } else {
                terminalOutput = prefix + "no card is destroyed and you received " + (-differenceOfAttackAndDefence) + " battle damage";
                currentTurnPlayer.setLifePoint(currentTurnPlayer.getLifePoint() + differenceOfAttackAndDefence);
            }
        }
        isDoAttack = false;
    }

    public String phase2Attack(int number) {
        if (number == 1) {
            number = 2;
        } else if (number == 2) {
            number = 1;
        } else if (number == 4) {
            number = 0;
        } else if (number == 5) {
            number = 4;
        }
        isDoAttack = true;
        Card selectedCard = currentTurnPlayer.getCurrentSelectedCard();
        onAttack = (Monster) currentTurnPlayer.getCurrentSelectedCard();
        if (selectedCard == null) {
            return "no card is selected yet";
        }
        Mat mat = currentTurnPlayer.getMat();
        boolean isInMonsters = false;
        for (int i = 0; i < 5; i++) {
            if (mat.getMonsterZone(i) != null && mat.getMonsterZone(i).getName().equals(selectedCard.getName()))
                isInMonsters = true;
        }
        if (!isInMonsters) {
            return "you cannot attack with this card";
        }
        if (!phase.getCurrentPhase().equals("Battle Phase")) {
            return "you cannot do this action in this phase";
        }
        Mat opponentMat = opponentTurnPlayer.getMat();
        if (opponentMat.getMonsterZone(number) == null) {
            return "there is no card to attack here";
        }
        // if (!isAi) {
        //     checkForQuickChangeTurn();
        // }
        if (!permissionForAttack) {
            return "No permission";
        }
        Card attackedCard = opponentMat.getMonsterZone(number);
        effectCheckerInAttack(currentTurnPlayer.getMat(), opponentTurnPlayer.getMat(), (Monster) selectedCard, (Monster) attackedCard);
        isDoAttack = false;
        if (attackedCard.isAttack()) {
            int attackDifference = getAttack(selectedCard) - getAttack(attackedCard);
            if (attackDifference > 0) {
                opponentTurnPlayer.setLifePoint(opponentTurnPlayer.getLifePoint() - attackDifference);
                opponentMat.addCardToGraveyard(attackedCard);
                opponentMat.setMonsterZone(number, null);
                return "your opponents monster is destroyed and your opponent receives " +
                        attackDifference + " battle damage";
            } else if (attackDifference == 0) {

                opponentMat.setMonsterZone(number, null);
                for (int i = 0; i < 5; i++) {
                    if (mat.getMonsterZone(i) != null && mat.getMonsterZone(i).getName().equals(selectedCard.getName())) {
                        mat.setMonsterZone(i, null);
                        break;
                    }
                }
                return "both you and your opponent monster cards are destroyed and no one receives damage";
            } else {
                currentTurnPlayer.setLifePoint(currentTurnPlayer.getLifePoint() + attackDifference);
                for (int i = 0; i < 5; i++) {
                    if (mat.getMonsterZone(i) != null && mat.getMonsterZone(i).getName().equals(selectedCard.getName())) {
                        mat.addCardToGraveyard(mat.getMonsterZone(i));
                        mat.setMonsterZone(i, null);
                        break;
                    }
                }
                return "Your monster card is destroyed and you received " +
                        (-attackDifference) + "battle damage";
            }
        } else {
            String prefix = "";
            if (attackedCard.isOn())
                prefix = "opponents monster card was " + attackedCard.getName() + " and ";
            int differenceOfAttackAndDefence = getAttack(selectedCard) - attackedCard.getDefence();
            if (differenceOfAttackAndDefence > 0) {
                opponentMat.setMonsterZone(number, null);
                return prefix + "the defense position monster is destroyed";
            } else if (differenceOfAttackAndDefence == 0) {
                return prefix + "no card is destroyed";
            } else {
                currentTurnPlayer.setLifePoint(currentTurnPlayer.getLifePoint() + differenceOfAttackAndDefence);
                return prefix + "no card is destroyed and you received " + (-differenceOfAttackAndDefence) + " battle damage";
            }
        }
    }

    public void directAttack() {
        Card selectedCard = currentTurnPlayer.getCurrentSelectedCard();
        if (selectedCard == null) {
            terminalOutput = "no card is selected yet";
            return;
        }
        Mat mat = currentTurnPlayer.getMat();
        boolean isInMonsters = false;
        for (int i = 0; i < 5; i++) {
            if (mat.getMonsterZone(i) != null && mat.getMonsterZone(i).getName().equals(selectedCard.getName()))
                isInMonsters = true;
        }
        if (!isInMonsters) {
            terminalOutput = "you can’t attack with this card";
            return;
        }
        if (!phase.getCurrentPhase().equals("Battle Phase")) {
            terminalOutput = "you can’t do this action in this phase";
            return;
        }
        for (int i = 0; i < 5; i++) {
            if (opponentTurnPlayer.getMat().getMonsterZone(i) != null) {
                terminalOutput = "opponent has monster in monster zone";
                return;
            }

        }
        terminalOutput = "you opponent receives " + getAttack(selectedCard) + " battle damage";
        opponentTurnPlayer.setLifePoint(opponentTurnPlayer.getLifePoint() - getAttack(selectedCard));
    }

    public String phase2DirectAttack() {
        Card selectedCard = currentTurnPlayer.getCurrentSelectedCard();
        if (selectedCard == null) {
            return "no card is selected yet";
        }
        Mat mat = currentTurnPlayer.getMat();
        boolean isInMonsters = false;
        for (int i = 0; i < 5; i++) {
            if (mat.getMonsterZone(i) != null && mat.getMonsterZone(i).getName().equals(selectedCard.getName()))
                isInMonsters = true;
        }
        if (!isInMonsters) {
            return "you cannot attack with this card";
        }
        if (!phase.getCurrentPhase().equals("Battle Phase")) {
            return "you can’t do this action in this phase";
        }
        for (int i = 0; i < 5; i++) {
            if (opponentTurnPlayer.getMat().getMonsterZone(i) != null) {
                return "opponent has monster in monster zone";
            }

        }
        opponentTurnPlayer.setLifePoint(opponentTurnPlayer.getLifePoint() - getAttack(selectedCard));
        return "you opponent receives " + getAttack(selectedCard) + " battle damage";
    }

    private int getAttack(Card card) {
        if (!card.getName().equals("The Calculator")) {
            return card.getAttack();
        }
        setCalculator(card, currentTurnPlayer.getMat());
        return card.getAttack();
    }

    public void activeEffect() {
        Card selectedCard = currentTurnPlayer.getCurrentSelectedCard();
        if (selectedCard == null) {
            terminalOutput = "no card is selected yet";
            return;
        }
        if (!selectedCard.getCardType().equals("Spell") && !selectedCard.getCardType().equals("Trap")) {
            terminalOutput = "activate effect is only for spell cards.";
            return;
        }
        if (!phase.getCurrentPhase().equals("First Main Phase") && !phase.getCurrentPhase().equals("Battle Phase")) {
            terminalOutput = "you can’t activate an effect on this turn";
            return;
        }
        Mat opponentMat = opponentTurnPlayer.getMat();
        for (int i = 0; i < 5; i++) {
            Monster monster = opponentMat.getMonsterZone(i);
            if (monster != null && monster.getName().equals("Mirage Dragon")) {
                terminalOutput = "opponent has mirage dragon";
                return;
            }
        }
        Mat mat = currentTurnPlayer.getMat();
        for (int i = 0; i < 5; i++) {
            if (mat.getSpellAndTrapZone(i) != null && mat.getSpellAndTrapZone(i).getName().equals(selectedCard.getName())) {
                if (mat.getActivate(i)) {
                    terminalOutput = "you have already activated this card";
                    return;
                }
                mat.setActivate(i);
            }
        }
        if (mat.isSpellAndTrapZoneIsFull() && !selectedCard.isField()) {
            terminalOutput = "spell card zone is full";
            return;
        }
        effectCheckerInActiveEffect(selectedCard);
        terminalOutput = "spell activated";
    }


    public String phase2ActiveEffect() {
        Card selectedCard = currentTurnPlayer.getCurrentSelectedCard();
        if (selectedCard == null) {
            return "no card is selected yet";

        }
        if (!selectedCard.getCardType().equals("Spell") && !selectedCard.getCardType().equals("Trap")) {
            return "activate effect is only for spell cards.";

        }
        if (!phase.getCurrentPhase().equals("First Main Phase") && !phase.getCurrentPhase().equals("Battle Phase")) {
            return "you can’t activate an effect on this turn";

        }
        Mat opponentMat = opponentTurnPlayer.getMat();
        for (int i = 0; i < 5; i++) {
            Monster monster = opponentMat.getMonsterZone(i);
            if (monster != null && monster.getName().equals("Mirage Dragon")) {

                return "opponent has mirage dragon";

            }
        }
        Mat mat = currentTurnPlayer.getMat();
        for (int i = 0; i < 5; i++) {
            if (mat.getSpellAndTrapZone(i) != null && mat.getSpellAndTrapZone(i).getName().equals(selectedCard.getName())) {
                if (mat.getActivate(i)) {
                    return "you have already activated this card";

                }
                mat.setActivate(i);
            }
        }
        if (mat.isSpellAndTrapZoneIsFull() && !selectedCard.isField()) {
            terminalOutput = "spell card zone is full";
            return terminalOutput;
        }
        effectCheckerInActiveEffect(selectedCard);
        currentTurnPlayer.getMat().deleteHandCard(currentTurnPlayer.getHandNumber());
        terminalOutput = "spell activated";
        return terminalOutput;
    }

    public void checkForQuickChangeTurn() {
        isDoAttack = true;
        Player player = currentTurnPlayer;
        currentTurnPlayer = opponentTurnPlayer;
        opponentTurnPlayer = player;
        TerminalOutput.output("now it will be " + currentTurnPlayer.getUser().getUsername() + "'s turn");
        showBoard();
        TerminalOutput.output("do you want to active your trap and spell?");
        String input = ScanInput.getInput();
        if (input.equals("no")) {
            TerminalOutput.output("now it will be " + opponentTurnPlayer.getUser().getUsername() + "'s turn");
            terminalOutput = "";
            showBoard();
            TerminalOutput.output(terminalOutput);
            player = currentTurnPlayer;
            currentTurnPlayer = opponentTurnPlayer;
            opponentTurnPlayer = player;
            return;
        }
        while (true) {
            terminalOutput = "";
            Matcher matcher;
            input = ScanInput.getInput();
            if ((input.matches("activate effect"))) {
                activeEffect();
                break;
            } else if ((matcher = getMatcher(input, "select (--spell|-s) (?<number>[\\d]+)(?<opponent> --opponent| -o|)")).matches()) {
                int number = Integer.parseInt(matcher.group("number"));
                String opponent = matcher.group("opponent");
                boolean isOpponent = opponent.equals(" --opponent") || opponent.equals(" -o");
                selectSpellOrTrap(number, isOpponent);
            } else if ((matcher = getMatcher(input, "select (--spell|-s)(?<opponent> --opponent| -o|) (?<number>[\\d]+)")).matches()) {
                int number = Integer.parseInt(matcher.group("number"));
                String opponent = matcher.group("opponent");
                boolean isOpponent = opponent.equals(" --opponent") || opponent.equals(" -o");
                selectSpellOrTrap(number, isOpponent);
            } else if ((matcher = getMatcher(input, "select (?<number>[\\d]+) (--spell|-s)(?<opponent> --opponent| -o|)")).matches()) {
                int number = Integer.parseInt(matcher.group("number"));
                String opponent = matcher.group("opponent");
                boolean isOpponent = opponent.equals(" --opponent") || opponent.equals(" -o");
                selectSpellOrTrap(number, isOpponent);
            } else if ((matcher = getMatcher(input, "select (?<number>[\\d]+)(?<opponent> --opponent| -o|) (--spell|-s)")).matches()) {
                int number = Integer.parseInt(matcher.group("number"));
                String opponent = matcher.group("opponent");
                boolean isOpponent = opponent.equals(" --opponent") || opponent.equals(" -o");
                selectSpellOrTrap(number, isOpponent);
            } else if ((matcher = getMatcher(input, "select(?<opponent> --opponent| -o|) (--spell|-s) (?<number>[\\d]+)")).matches()) {
                int number = Integer.parseInt(matcher.group("number"));
                String opponent = matcher.group("opponent");
                boolean isOpponent = opponent.equals(" --opponent") || opponent.equals(" -o");
                selectSpellOrTrap(number, isOpponent);
            } else if ((matcher = getMatcher(input, "select(?<opponent> --opponent| -o|) (?<number>[\\d]+) (--spell|-s)")).matches()) {
                int number = Integer.parseInt(matcher.group("number"));
                String opponent = matcher.group("opponent");
                boolean isOpponent = opponent.equals(" --opponent") || opponent.equals(" -o");
                selectSpellOrTrap(number, isOpponent);
            } else if (input.equals("exit")) {
                break;
            } else if (input.equals("card show -s")) {
                selectedCardShow();
            } else {
                TerminalOutput.output("it's noy your turn to play this kind of moves");
            }
            TerminalOutput.output(terminalOutput);
        }
        player = currentTurnPlayer;
        currentTurnPlayer = opponentTurnPlayer;
        opponentTurnPlayer = player;
        TerminalOutput.output("now it will be " + currentTurnPlayer.getUser().getUsername() + "'s turn");
        isDoAttack = false;
    }

    public void selectSpellOrTrap(Matcher matcher) {
        int number = Integer.parseInt(matcher.group("number"));
        String opponent = matcher.group("opponent");
        boolean isOpponent = opponent.equals(" --opponent") || opponent.equals(" -o");
        selectSpellOrTrap(number, isOpponent);
    }

    public void ritualSummon() {
        //TODO
    }

    public void specialSummon() {
        //TODO
    }

    public void showGraveyard() {
        ArrayList<Card> graveyard = currentTurnPlayer.getMat().getGraveyard();
        if (graveyard.size() == 0) {
            terminalOutput += "graveyard empty";
        }
        for (Card card : graveyard)
            terminalOutput = card.getName() + ":" + card.getDescription();
    }

    public void selectedCardShow() {
        Card card = currentTurnPlayer.getCurrentSelectedCard();
        if (card == null) {
            terminalOutput = "no card selected yet";
            return;
        }
        if (currentTurnPlayer.getSelectedName().equals("MonsterOpponent") ||
                currentTurnPlayer.getSelectedName().equals("SpellOpponent") ||
                currentTurnPlayer.getSelectedName().equals("TrapOpponent")) {
            if (!card.isOn()) {
                terminalOutput = "card is not visible";
                return;
            }
        }
        String cardName = card.getName();
        terminalOutput = Card.showCard(cardName);
    }

    public void surrender() {
        numberOfRounds = 1;
        currentTurnPlayer.setLifePoint(0);
        if (currentTurnPlayer.getUser().getUsername().equals(currentUser.getUsername())) {
            secondPlayerWins = wholeNumberOfRounds;
        }

        if (currentTurnPlayer.getUser().getUsername().equals(secondUser.getUsername())) {
            firstPlayerWins = wholeNumberOfRounds;
        }
    }

    public void increaseMoney(int amount) {
        int currentMoney = currentTurnUser.getCredit();
        currentTurnUser.setCredit(currentMoney + amount);
    }

    public void increaseLifePoint(int amount) {
        int currentLP = currentTurnPlayer.getLifePoint();
        currentTurnPlayer.setLifePoint(currentLP + amount);
    }

    public void selectForcedCard(String cardName) {
        Card card = Card.getCardByName(cardName);
        currentTurnPlayer.addSelectedCard(card);
    }

    public void setWinner(String nickname) {
        String firstPlayerName = currentUser.getNickname();
        String secondPlayerName = secondUser.getNickname();
        numberOfRounds = 1;
        if (firstPlayerName.equals(nickname)) {
            secondPlayer.setLifePoint(0);
            firstPlayerWins = wholeNumberOfRounds;
        }
        if (secondPlayerName.equals(nickname)) {
            firstPlayer.setLifePoint(0);
            secondPlayerWins = wholeNumberOfRounds;
        }
    }

    public boolean isGameEnd() {
        return isGameEnd;
    }

    public String hasGameEndedPhase2() {
        int firstPlayerHealth = firstPlayer.getLifePoint();
        int secondPlayerHealth = secondPlayer.getLifePoint();
        terminalOutput = "";
        if (firstPlayerHealth <= 0 ||
                (currentTurnPlayer.isEqual(firstPlayer) && phase.getCurrentPhase().equals("Draw Phase") && isEndCard())) {
            secondPlayerWins++;
            secondPlayerMaxLP = Math.max(secondPlayerMaxLP, secondPlayer.getLifePoint());
            String username = secondUser.getUsername();
            terminalOutput += "\n" + username + " won the game and the score is: " + firstPlayerWins + "-" + secondPlayerWins + "\n";
            if (numberOfRounds == 1 || (numberOfRounds == 2 && firstRoundWinner == secondPlayer.getUser())) {
                terminalOutput += username + " won the whole match and the score is: " + firstPlayerWins + "-" + secondPlayerWins + "\n";
                int firstPlayerCredit = 100;
                int secondPlayerCredit = 1000 + secondPlayerMaxLP;
                if (wholeNumberOfRounds == 3) {
                    firstPlayerCredit = 300;
                    secondPlayerCredit = 3000 + secondPlayerMaxLP;
                }
                currentUser.setCredit(currentUser.getCredit() + firstPlayerCredit);
                secondUser.setCredit(secondUser.getCredit() + secondPlayerCredit);
                secondUser.setScore(secondUser.getScore() + 1000 * wholeNumberOfRounds);
                isGameEnd = true;
                return terminalOutput;
            }
            //changeCardPhase2();
            firstPlayer = new Player(this.currentUser);
            secondPlayer = new Player(this.secondUser);
            currentTurnPlayer = firstPlayer;
            opponentTurnPlayer = secondPlayer;
            this.phase = new Phase(this);
            numberOfRounds--;
        }
        if (secondPlayerHealth <= 0 ||
                (currentTurnPlayer.isEqual(firstPlayer) && phase.getCurrentPhase().equals("Draw Phase") && isEndCard())) {
            firstPlayerWins++;
            firstPlayerMaxLP = Math.max(firstPlayerMaxLP, firstPlayer.getLifePoint());
            String username = currentUser.getUsername();
            terminalOutput += "\n" + username + " won the game and the score is: " + firstPlayerWins + "-" + secondPlayerWins + "\n";
            if (numberOfRounds == 1 || (numberOfRounds == 2 && firstRoundWinner == firstPlayer.getUser())) {
                terminalOutput += username + " won the whole match and the score is: " + firstPlayerWins + "-" + secondPlayerWins + "\n";
                int secondPlayerCredit = 100;
                int firstPlayerCredit = 1000 + firstPlayerMaxLP;
                if (wholeNumberOfRounds == 3) {
                    secondPlayerCredit = 300;
                    firstPlayerCredit = 3000 + firstPlayerMaxLP;
                }
                currentUser.setCredit(currentUser.getCredit() + firstPlayerCredit);
                secondUser.setCredit(secondUser.getCredit() + secondPlayerCredit);
                currentUser.setScore(currentUser.getScore() + 1000 * wholeNumberOfRounds);
                isGameEnd = true;
                return terminalOutput;
            }
            //changeCardPhase2();
            firstPlayer = new Player(this.currentUser);
            secondPlayer = new Player(this.secondUser);
            currentTurnPlayer = firstPlayer;
            opponentTurnPlayer = secondPlayer;
            this.phase = new Phase(this);
            numberOfRounds--;
        }
        return terminalOutput;
    }

    public void changeCardPhase2() {
        if (wholeNumberOfRounds == 3) {
            System.out.println("Do you want to switch cards from side deck?(yes1/no1/yes2/no2)");
            String string = ScanInput.getInput();
            MainDeck mainDeck = null;
            SideDeck sideDeck = null;
            if (string.equals("yes1")) {
                mainDeck = currentUser.getActiveDeck().getMainDeck();
                sideDeck = currentUser.getActiveDeck().getSideDeck();
                System.out.println("Input name of main card");
                String mainName = ScanInput.getInput();
                System.out.println("Input name of side card");
                String sideName = ScanInput.getInput();
                Card mainCard = null;
                Card sideCard = null;
                boolean isValid = false;
                for (Card card : mainDeck.getMainDeckCards()) {
                    if (card.getName().equals(mainName)) {
                        isValid = true;
                        mainCard = card;
                    }
                }
                boolean isSideValid = false;
                for (Card card : sideDeck.getSideDeckCards()) {
                    if (card.getName().equals(sideName)) {
                        isSideValid = true;
                        sideCard = card;
                    }
                }
                if (isValid && isSideValid) {
                    mainDeck.removeCard(mainCard);
                    sideDeck.removeCard(sideCard);
                    mainDeck.addCard(sideCard);
                    sideDeck.addCard(mainCard);
                }
            } else if (string.equals("yes2")) {
                mainDeck = secondUser.getActiveDeck().getMainDeck();
                sideDeck = secondUser.getActiveDeck().getSideDeck();
                System.out.println("Input name of main card");
                String mainName = ScanInput.getInput();
                System.out.println("Input name of side card");
                String sideName = ScanInput.getInput();
                Card mainCard = null;
                Card sideCard = null;
                boolean isValid = false;
                for (Card card : mainDeck.getMainDeckCards()) {
                    if (card.getName().equals(mainName)) {
                        isValid = true;
                        mainCard = card;
                    }
                }
                boolean isSideValid = false;
                for (Card card : sideDeck.getSideDeckCards()) {
                    if (card.getName().equals(sideName)) {
                        isSideValid = true;
                        sideCard = card;
                    }
                }
                if (isValid && isSideValid) {
                    mainDeck.removeCard(mainCard);
                    sideDeck.removeCard(sideCard);
                    mainDeck.addCard(sideCard);
                    sideDeck.addCard(mainCard);
                }
            }
            System.out.println("Do you want to switch cards from side deck?(yes1/no1/yes2/no2)");
            string = ScanInput.getInput();
            if (string.equals("yes1")) {
                mainDeck = currentUser.getActiveDeck().getMainDeck();
                sideDeck = currentUser.getActiveDeck().getSideDeck();
                System.out.println("Input name of main card");
                String mainName = ScanInput.getInput();
                System.out.println("Input name of side card");
                String sideName = ScanInput.getInput();
                Card mainCard = null;
                Card sideCard = null;
                boolean isValid = false;
                for (Card card : mainDeck.getMainDeckCards()) {
                    if (card.getName().equals(mainName)) {
                        isValid = true;
                        mainCard = card;
                    }
                }
                boolean isSideValid = false;
                for (Card card : sideDeck.getSideDeckCards()) {
                    if (card.getName().equals(sideName)) {
                        isSideValid = true;
                        sideCard = card;
                    }
                }
                if (isValid && isSideValid) {
                    mainDeck.removeCard(mainCard);
                    sideDeck.removeCard(sideCard);
                    mainDeck.addCard(sideCard);
                    sideDeck.addCard(mainCard);
                }
            } else if (string.equals("yes2")) {
                mainDeck = secondUser.getActiveDeck().getMainDeck();
                sideDeck = secondUser.getActiveDeck().getSideDeck();
                System.out.println("Input name of main card");
                String mainName = ScanInput.getInput();
                System.out.println("Input name of side card");
                String sideName = ScanInput.getInput();
                Card mainCard = null;
                Card sideCard = null;
                boolean isValid = false;
                for (Card card : mainDeck.getMainDeckCards()) {
                    if (card.getName().equals(mainName)) {
                        isValid = true;
                        mainCard = card;
                    }
                }
                boolean isSideValid = false;
                for (Card card : sideDeck.getSideDeckCards()) {
                    if (card.getName().equals(sideName)) {
                        isSideValid = true;
                        sideCard = card;
                    }
                }
                if (isValid && isSideValid) {
                    mainDeck.removeCard(mainCard);
                    sideDeck.removeCard(sideCard);
                    mainDeck.addCard(sideCard);
                    sideDeck.addCard(mainCard);
                }
            }
        }
    }

    public boolean hasGameEnded() {
        int firstPlayerHealth = firstPlayer.getLifePoint();
        int secondPlayerHealth = secondPlayer.getLifePoint();
        if (firstPlayerHealth <= 0 ||
                (currentTurnPlayer.isEqual(firstPlayer) && phase.getCurrentPhase().equals("Draw Phase") && isEndCard())) {
            secondPlayerWins++;
            secondPlayerMaxLP = Math.max(secondPlayerMaxLP, secondPlayer.getLifePoint());
            String username = secondUser.getUsername();
            terminalOutput += "\n" + username + " won the game and the score is: " + firstPlayerWins + "-" + secondPlayerWins + "\n";
            if (numberOfRounds == 1 || (numberOfRounds == 2 && firstRoundWinner == secondPlayer.getUser())) {
                terminalOutput += username + " won the whole match and the score is: " + firstPlayerWins + "-" + secondPlayerWins + "\n";
                int firstPlayerCredit = 100;
                int secondPlayerCredit = 1000 + secondPlayerMaxLP;
                if (wholeNumberOfRounds == 3) {
                    firstPlayerCredit = 300;
                    secondPlayerCredit = 3000 + secondPlayerMaxLP;
                }
                currentUser.setCredit(currentUser.getCredit() + firstPlayerCredit);
                secondUser.setCredit(secondUser.getCredit() + secondPlayerCredit);
                secondUser.setScore(secondUser.getScore() + 1000 * wholeNumberOfRounds);
                return true;
            }
            changeCardPhase2();
            firstPlayer = new Player(this.currentUser);
            secondPlayer = new Player(this.secondUser);
            currentTurnPlayer = firstPlayer;
            opponentTurnPlayer = secondPlayer;
            this.phase = new Phase(this);
            numberOfRounds--;
        }
        if (secondPlayerHealth <= 0 ||
                (currentTurnPlayer.isEqual(firstPlayer) && phase.getCurrentPhase().equals("Draw Phase") && isEndCard())) {
            firstPlayerWins++;
            firstPlayerMaxLP = Math.max(firstPlayerMaxLP, firstPlayer.getLifePoint());
            String username = currentUser.getUsername();
            terminalOutput += "\n" + username + " won the game and the score is: " + firstPlayerWins + "-" + secondPlayerWins + "\n";
            if (numberOfRounds == 1 || (numberOfRounds == 2 && firstRoundWinner == firstPlayer.getUser())) {
                terminalOutput += username + " won the whole match and the score is: " + firstPlayerWins + "-" + secondPlayerWins + "\n";
                int secondPlayerCredit = 100;
                int firstPlayerCredit = 1000 + firstPlayerMaxLP;
                if (wholeNumberOfRounds == 3) {
                    secondPlayerCredit = 300;
                    firstPlayerCredit = 3000 + firstPlayerMaxLP;
                }
                currentUser.setCredit(currentUser.getCredit() + firstPlayerCredit);
                secondUser.setCredit(secondUser.getCredit() + secondPlayerCredit);
                currentUser.setScore(currentUser.getScore() + 1000 * wholeNumberOfRounds);
                return true;
            }
            changeCardPhase2();
            firstPlayer = new Player(this.currentUser);
            secondPlayer = new Player(this.secondUser);
            currentTurnPlayer = firstPlayer;
            opponentTurnPlayer = secondPlayer;
            this.phase = new Phase(this);
            numberOfRounds--;
        }
        return false;
    }

    public void changeTurn() {
        currentTurnPlayer.setSummoned(false);
        opponentTurnPlayer.setSummoned(false);
        if (!isAi) {
            currentTurnPlayer.setCurrentSelectedCard(null);
            opponentTurnPlayer.setCurrentSelectedCard(null);
            Player player = currentTurnPlayer;
            currentTurnPlayer = opponentTurnPlayer;
            opponentTurnPlayer = player;
            if (isFirstRound) {
                isFirstRound = false;
            }
        } else {
            if (hasAiMoved) {
                hasAiMoved = false;
            } else {
                ai.doTurn();
                hasAiMoved = true;
                changeTurn();
            }
        }
    }

    public boolean isEndCard() {
        return currentTurnPlayer.getMainDeckCard().size() == 0;
    }

    public String getTerminalOutput() {
        String returnValue = terminalOutput;
        terminalOutput = "";
        return returnValue + "\n";
    }

    private void refresh() {
        MainDeck firstDeck = firstPlayer.getUser().getActiveDeck().getMainDeck();
        MainDeck secondDeck = secondPlayer.getUser().getActiveDeck().getMainDeck();
        for (Card card : firstDeck.getMainDeckCards()) {
            card.setOn(false);
            card.setAttack(false);
            card.setField(false);
        }
        for (Card card : secondDeck.getMainDeckCards()) {
            card.setOn(false);
            card.setAttack(false);
            card.setField(false);
        }
    }

    public Matcher getMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }

}