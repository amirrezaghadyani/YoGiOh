package model.mat;

import model.card.Card;
import model.card.Monster;
import model.user.Deck;
import model.user.MainDeck;

import java.util.ArrayList;

public class Mat {
    private ArrayList<Card> graveyard = new ArrayList<>();
    private Monster[] monsterZone = new Monster[5];
    private Card[] spellAndTrapZone = new Card[5];
    private Card fieldZone;
    private Card[] handCard = new Card[6];
    private boolean[] isChanged = new boolean[5];

    public Mat() {
        for (int i = 0; i < 5; i++) {
            monsterZone[i] = null;
            spellAndTrapZone[i] = null;
            handCard[i] = null;
            isChanged[i] = false;
        }
        handCard[5] = null;
        fieldZone = null;
    }

    public void setIsChanged(boolean isChanged, int number) {
        this.isChanged[number] = isChanged;
    }

    public boolean isChangedCard(int number) {
        return isChanged[number];
    }

    public boolean setHandCard(Card card) {
        for (int i = 0; i < 6; i++) {
            if (handCard[i] == null) {
                handCard[i] = card;
                return true;
            }
        }
        return false;
    }

    public void setHandCard(Card card, int index) {
        handCard[index] = card;
    }

    public void setFieldZone(Card fieldZone) {
        this.fieldZone = fieldZone;
    }

    public void addCardToGraveyard(Card card) {
        graveyard.add(card);
    }

    public void setMonsterZone(int number, Monster monster) {
        this.monsterZone[number] = monster;
    }

    public void setSpellAndTrapZone(int number, Card card) {
        this.spellAndTrapZone[number] = card;
    }

    public ArrayList<Card> getGraveyard() {
        return graveyard;
    }

    public Card getFieldZone() {
        return fieldZone;
    }

    public Card getSpellAndTrapZone(int number) {
        return spellAndTrapZone[number];
    }

    public Card getHandCard(int number) {
        return this.handCard[number];
    }

    public void deleteHandCard(int number){
        handCard[number-1] = null;
    }

    public void deleteMonsterZone(int address){
        monsterZone[address] = null;
    }

    public Monster getMonsterZone(int number) {
        return this.monsterZone[number];
    }

    public void addMonster(Monster monster) {
        if (monsterZone[2] == null) {
            monsterZone[2] = monster;
            return;
        }
        if (monsterZone[3] == null) {
            monsterZone[3] = monster;
            return;
        }
        if (monsterZone[1] == null) {
            monsterZone[1] = monster;
            return;
        }
        if (monsterZone[4] == null) {
            monsterZone[4] = monster;
            return;
        }
        if (monsterZone[0] == null) {
            monsterZone[0] = monster;
        }

    }

    public void addSpellOrTrap(Card card) {
        if (spellAndTrapZone[2] == null) {
            spellAndTrapZone[2] = card;
            return;
        }
        if (spellAndTrapZone[3] == null) {
            spellAndTrapZone[3] = card;
            return;
        }
        if (spellAndTrapZone[1] == null) {
            spellAndTrapZone[1] = card;
            return;
        }
        if (spellAndTrapZone[4] == null) {
            spellAndTrapZone[4] = card;
            return;
        }
        if (spellAndTrapZone[0] == null) {
            spellAndTrapZone[0] = card;
        }

    }

    public int getNumberOfCardMonsterZone() {
        int number = 0;
        for (int i = 0; i < 5; i++) {
            if (monsterZone[i] != null) {
                number += 1;
            }
        }
        return number;
    }

    public boolean isMonsterZoneIsFull() {
        for (int i = 0; i < 5; i++) {
            if (monsterZone[i] == null) {
                return false;
            }
        }
        return true;
    }

    public boolean isSpellAndTrapZoneIsFull() {
        for (int i = 0; i < 5; i++) {
            if (spellAndTrapZone[i] == null) {
                return false;
            }
        }
        return true;
    }

    public boolean isHandFull() {
        for (int i = 0; i < 6; i++) {
            if (handCard[i] == null) {
                return false;
            }
        }
        return true;
    }

    public String printMat(Deck deck, boolean isReversed) {
        MainDeck mainDeck = deck.getMainDeck();
        String ret = "";
        if (isReversed) {
            for (int i = 0; i < 6; i++)
                if (handCard[i] != null)
                    ret += "c   ";
            ret += "\n" + mainDeck.getMainDeckSize();
            ret += "\n" + printSpellZone();
            ret += "\n" + printMonsterZone() + "\n";
            ret += graveyard.size() + "                  " + ((fieldZone == null) ? "E" : "O");
        } else {
            ret += ((fieldZone == null) ? "E" : "O") + "                  " + graveyard.size();
            ret += "\n" + printMonsterZone();
            ret += "\n" + printSpellZone();
            ret += "\n                  " + mainDeck.getMainDeckSize() + "\n";
            for (int i = 0; i < 6; i++)
                if (handCard[i] != null)
                    ret += "c   ";
        }
        return ret + "\n";
    }

    private String printMonsterZone() {
        String ret = "";
        for (int i = 0; i < 5; i++) {
            if (monsterZone[i] == null)
                ret += "E    ";
            else if (!monsterZone[i].isAttack() && monsterZone[i].isOn())
                ret += "DO   ";
            else if (!monsterZone[i].isAttack() && !monsterZone[i].isOn())
                ret += "DH   ";
            else if (monsterZone[i].isAttack())
                ret += "OO   ";
        }
        return ret;
    }

    private String printSpellZone() {
        String ret = "";
        for (int i = 0; i < 5; i++) {
            if (spellAndTrapZone[i] == null)
                ret += "E    ";
            else if (spellAndTrapZone[i].isOn())
                ret += "O    ";
            else
                ret += "H    ";
        }
        return ret;
    }

    public void addToHand(Card card) {
        for (int i = 0; i < 6; i++) {
            if (handCard[i] == null) {
                handCard[i] = card;
                return;
            }
        }
    }
}

