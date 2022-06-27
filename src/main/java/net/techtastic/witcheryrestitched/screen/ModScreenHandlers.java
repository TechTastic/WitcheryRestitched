package net.techtastic.witcheryrestitched.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.techtastic.witcheryrestitched.WitcheryRestitched;

public class ModScreenHandlers {
    public static ScreenHandlerType<CastIronOvenScreenHandler> CAST_IRON_OVEN_SCREEN_HANDLER;

    public static void registerAllScreenHandlers() {
        CAST_IRON_OVEN_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(WitcheryRestitched.MOD_ID, "cast_iron_oven"),
                CastIronOvenScreenHandler::new);
    }
}
