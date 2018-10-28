package relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BloodLust extends CustomRelic {

    public static final String ID = "gladiator:BloodLust";

    //TODO: PLACE HOLDER TEXTURE
    public BloodLust() {
        super(
            ID
            , new Texture("img/relics/bloodLust.png")
            , RelicTier.STARTER
            , LandingSound.HEAVY
        );
    }

    @Override
    public void onMonsterDeath(AbstractMonster abstractMonster) {
        if((abstractMonster.currentHealth == 0) && (!AbstractDungeon.getMonsters().areMonstersBasicallyDead())) {
            flash();
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(abstractMonster, this));
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
