import basemod.BaseMod;
import basemod.ModPanel;
import basemod.interfaces.*;
import cards.DefendGladiator;
import cards.Net;
import cards.StrikeGladiator;
import characters.TheGladiator;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.compression.lzma.Base;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import patches.AbstractCardEnum;
import patches.TheGladiatorEnum;
import relics.BloodLust;

import java.nio.charset.StandardCharsets;

@SpireInitializer
public class TheGladiatorMod implements PostInitializeSubscriber,EditCharactersSubscriber, EditCardsSubscriber, EditRelicsSubscriber, EditStringsSubscriber {

    public static final Logger logger = LogManager.getLogger(TheGladiatorMod.class.getName());

    //MOD INFO
    private static final String MOD_NAME = "The Glaidator";
    private static final String AUTHOR = "Residualshade";
    private static final String DESCRIPTOIN = "Adds The Gladiator as a new playable character.";
    public static final String MOD_BADGE_PATH = "img/ModBadge.png";

    //CARD BACKGROUNDS
    private static final Color MOD_COLOR = Color.MAROON;
    private static final String CARD_512_PATH = "img/cardui/512/";
    private static final String ATTACK_MAROON_PATH =  CARD_512_PATH + "bg_attack_maroon.png";
    private static final String SKILL_MAROON_PATH = CARD_512_PATH + "bg_skill_maroon.png";
    private static final String POWER_MAROON_PATH = CARD_512_PATH + "bg_power_maroon.png";
    private static final String ENERGY_ORB_MAROON_PATH = CARD_512_PATH + "card_maroon_orb.png";
    private static final String CARD_1024_PATH = "img/cardui/1024/";
    private static final String ATTACK_MAROON_PORTRAIT_PATH = CARD_1024_PATH + "bg_attack_maroon.png";
    private static final String SKILL_MAROON_PORTRAIT_PATH = CARD_1024_PATH + "bg_skill_maroon.png";
    private static final String POWER_MAROON_PORTRAIT_PATH = CARD_1024_PATH + "bg_power_maroon.png";
    private static final String ENERGY_ORB_MAROON_PORTRAIT_PATH = CARD_1024_PATH + "card_maroon_orb.png";
    private static final String ENERGY_ORB_IN_DESCRIPTION_PATH = "img/orbs/orb.png";

    //CHARACTER SELECT SCREEN IMAGE PATHS
    private static final String GLADIATOR_BUTTON_PATH = "img/ui/charSelect/gladiatorButton.png";
    private static final String GLADIATOR_PORTRAIT_PATH = "img/ui/charSelect/ironcladPortrait.jpg";

    //STRINGS PATHS
    private static final String GLADIATOR_CARD_STRINGS_PATH = "localization/TheGladiator-CardStrings.json";
    private static final String GLADIATOR_RELIC_STRINGS_PATH = "localization/TheGladiator-RelicStrings.json";

    public TheGladiatorMod(){
        BaseMod.subscribe(this);

        logger.info("creating the color " + AbstractCardEnum.GLADIATOR_MAROON.toString());
        BaseMod.addColor(
                AbstractCardEnum.GLADIATOR_MAROON
                ,MOD_COLOR,ATTACK_MAROON_PATH
                ,SKILL_MAROON_PATH
                ,POWER_MAROON_PATH
                ,ENERGY_ORB_MAROON_PATH
                ,ATTACK_MAROON_PORTRAIT_PATH
                ,SKILL_MAROON_PORTRAIT_PATH
                ,POWER_MAROON_PORTRAIT_PATH
                ,ENERGY_ORB_MAROON_PORTRAIT_PATH
                ,ENERGY_ORB_IN_DESCRIPTION_PATH
        );
    }

    //Used by @SpireInitializer
    public static void initialize(){
        logger.debug("Started initializing " + MOD_NAME);
        new TheGladiatorMod();
        logger.debug("Finished initializing " + MOD_NAME);
    }

    @Override
    public void receiveEditCharacters() {
        logger.info("adding " + TheGladiatorEnum.THE_GLADIATOR_CLASS.toString() + " character to game");
        BaseMod.addCharacter(
            new TheGladiator(
                TheGladiator.NAME
                ,TheGladiatorEnum.THE_GLADIATOR_CLASS
            )
            ,GLADIATOR_BUTTON_PATH
            ,GLADIATOR_PORTRAIT_PATH
            , TheGladiatorEnum.THE_GLADIATOR_CLASS
        );
        logger.info("added " + TheGladiatorEnum.THE_GLADIATOR_CLASS.toString() + " character to game");
    }

    @Override
    public void receiveEditCards() {
        logger.info("adding " + TheGladiatorEnum.THE_GLADIATOR_CLASS.toString() + " cards to game");
        BaseMod.addCard(new StrikeGladiator());
        BaseMod.addCard(new DefendGladiator());
        BaseMod.addCard(new Net());
        logger.info("added " + TheGladiatorEnum.THE_GLADIATOR_CLASS.toString() + " cards to game");
    }

    @Override
    public void receiveEditRelics() {
        logger.info("adding " + TheGladiatorEnum.THE_GLADIATOR_CLASS.toString() + " relics to game");
        BaseMod.addRelicToCustomPool(new BloodLust(),AbstractCardEnum.GLADIATOR_MAROON);
        logger.info("added " + TheGladiatorEnum.THE_GLADIATOR_CLASS.toString() + " relics to game");
    }

    @Override
    public void receiveEditStrings() {
        logger.info("adding " + TheGladiatorEnum.THE_GLADIATOR_CLASS.toString() + " card strings to game");
        String cardStrings = Gdx.files.internal(GLADIATOR_CARD_STRINGS_PATH).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
        String relicStrings = Gdx.files.internal(GLADIATOR_RELIC_STRINGS_PATH).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
        logger.info("added " + TheGladiatorEnum.THE_GLADIATOR_CLASS.toString() + " card strings to game");
    }

    @Override
    public void receivePostInitialize() {
        Texture badgeTexture = new Texture(MOD_BADGE_PATH);
        ModPanel settingsPanel = new ModPanel();
        BaseMod.registerModBadge(badgeTexture,MOD_NAME,AUTHOR,DESCRIPTOIN,settingsPanel);

        Settings.isDailyRun = false;
        Settings.isTrial = false;
        Settings.isDemo = false;
    }
}
