package characters;

import basemod.abstracts.CustomPlayer;
import cards.DefendGladiator;
import cards.Net;
import cards.StrikeGladiator;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import patches.AbstractCardEnum;
import patches.TheGladiatorEnum;
import relics.BloodLust;

import java.util.ArrayList;

public class TheGladiator extends CustomPlayer {

    //CLASS INFO
    public static final String NAME = "The Gladiator";
    public static final String DESCRIPTION = "N/A";

    //CLASS STATS
    private static final int ENERGY_PER_TURN = 3;
    public static final int STARTING_HP = 75;
    public static final int MAX_HP = 75;
    public static final int STARTING_GOLD = 99;
    public static final int HAND_SIZE = 5;
    private static final int ORB_SLOTS = 0;

    //CLASS ANIMATION PATHS
    private static final String GLADIATOR_CHARACTER_PATH = "img/characters/gladiator/";
    private static final String GLADIATOR_SKELETON_ATLAS_PATH = GLADIATOR_CHARACTER_PATH + "idle/skeleton.atlas";
    private static final String GLADIATOR_SKELETON_JSON_PATH = GLADIATOR_CHARACTER_PATH + "idle/skeleton.json";
    private static final String GLADIATOR_SHOULDER_1 = GLADIATOR_CHARACTER_PATH + "shoulder.png";
    private static final String GLADIATOR_SHOULDER_2 = GLADIATOR_CHARACTER_PATH + "shoulder2.png";
    private static final String GLADIATOR_CORPSE = GLADIATOR_CHARACTER_PATH + "corpse.png";

    public TheGladiator(String name, PlayerClass setClass) {
        super(name, setClass, null, null, (String) null, null);

        initializeClass(
            null
            ,GLADIATOR_SHOULDER_2
            ,GLADIATOR_SHOULDER_1
            ,GLADIATOR_CORPSE
            ,getLoadout()
            ,-4.0F
            ,-16.0F
            ,220.0F
            ,290.0F
            ,new EnergyManager(ENERGY_PER_TURN)
        );
        loadAnimation(GLADIATOR_SKELETON_ATLAS_PATH,GLADIATOR_SKELETON_JSON_PATH,1.0F);
        AnimationState.TrackEntry trackEntry = state.setAnimation(0,"Idle", true);
        trackEntry.setTime(trackEntry.getEndTime()*MathUtils.random());
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> startingDeck = new ArrayList<>();
        startingDeck.add(StrikeGladiator.ID);
        startingDeck.add(StrikeGladiator.ID);
        startingDeck.add(StrikeGladiator.ID);
        startingDeck.add(StrikeGladiator.ID);
        startingDeck.add(StrikeGladiator.ID);
        startingDeck.add(DefendGladiator.ID);
        startingDeck.add(DefendGladiator.ID);
        startingDeck.add(DefendGladiator.ID);
        startingDeck.add(DefendGladiator.ID);
        startingDeck.add(Net.ID);
        return startingDeck;
    }

    @Override
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> startingRelics = new ArrayList<>();
        startingRelics.add(BloodLust.ID);
        UnlockTracker.markRelicAsSeen(BloodLust.ID);
        return startingRelics;
    }


    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAME, DESCRIPTION,STARTING_HP,MAX_HP,ORB_SLOTS,STARTING_GOLD,HAND_SIZE,this,getStartingRelics(),getStartingDeck(),false);
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        return NAME;
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return AbstractCardEnum.GLADIATOR_MAROON;
    }

    @Override
    public Color getCardRenderColor() {
        return Color.MAROON;
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new Net();
    }

    @Override
    public Color getCardTrailColor() {
        return Color.MAROON;
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 4;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("ATTACK_HEAVY", MathUtils.random(-0.2f, 0.2f));
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, true);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_HEAVY";
    }

    @Override
    public String getLocalizedCharacterName() {
        return NAME;
    }

    @Override
    public AbstractPlayer newInstance() {
        return new TheGladiator(NAME, TheGladiatorEnum.THE_GLADIATOR_CLASS);
    }

    @Override
    public String getSpireHeartText() {
        return "NL You ready your Weapon...";
    }

    @Override
    public Color getSlashAttackColor() {
        return Color.MAROON;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[0];
    }

    //TODO: Character Specific Dialog
    @Override
    public String getVampireText() {
        return "Navigating an unlit street, you come across several hooded figures in the midst of some dark ritual. As you approach, they turn to you in eerie unison. The tallest among them bares fanged teeth and extends a long, pale hand towards you. NL ~\"Join~ ~us,~ ~oh Mighty Warrior,~ ~and~ ~feel~ ~the~ ~warmth~ ~of~ ~the~ ~Spire.\"~";
    }
}
