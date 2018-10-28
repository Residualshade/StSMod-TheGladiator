package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import patches.AbstractCardEnum;

//TODO: IMPLEMENT NET CARD
public class Net extends CustomCard {

    public static final String ID = "gladiator:Net";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTOIN = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int DMG = 6;
    private static final int UPGRADE_DAMAGE_BONUS = 3;

    //TODO: CARD IMAGE
    public Net() {
        super(
                ID
                ,NAME
                ,null
                ,COST
                ,DESCRIPTOIN
                , AbstractCard.CardType.ATTACK
                , AbstractCardEnum.GLADIATOR_MAROON
                , CardRarity.BASIC
                , AbstractCard.CardTarget.ENEMY
        );

        this.exhaust = true;
    }

    @Override
    public void upgrade() {
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(abstractMonster,abstractPlayer, new StrengthPower(abstractMonster, -99)));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(abstractMonster,abstractPlayer, new LoseStrengthPower(abstractMonster, -99)));
    }
}
