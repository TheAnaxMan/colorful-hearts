package terrails.colorfulhearts.fabric;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.GuiGraphics;
import terrails.colorfulhearts.CColorfulHearts;
import terrails.colorfulhearts.api.event.HeartRenderEvent;
import terrails.colorfulhearts.fabric.api.event.FabHeartRenderEvent;
import terrails.colorfulhearts.heart.CHeartType;

import static terrails.colorfulhearts.CColorfulHearts.LOGGER;

public class LoaderExpectPlatformImpl {

    public static void applyConfig() {
        ColorfulHearts.FILE_CONFIG.save();
        LOGGER.debug("Successfully saved changes to {} config file.", CColorfulHearts.MOD_ID + ".toml");
    }

    public static boolean forcedHardcoreHearts() {
        if (FabricLoader.getInstance().getObjectShare().get("colorfulhearts:force_hardcore_hearts") instanceof Boolean forced) {
            return forced;
        } else return false;
    }

    public static HeartRenderEvent.Pre preRenderEvent(
            GuiGraphics guiGraphics, int x, int y,
            boolean blinking, boolean hardcore,
            CHeartType healthType, CHeartType absorbingType
    ) {
        HeartRenderEvent.Pre event = new HeartRenderEvent.Pre(guiGraphics, x, y, blinking, hardcore, healthType, absorbingType);
        FabHeartRenderEvent.PRE.invoker().accept(event);
        return event;
    }

    public static void postRenderEvent(
            GuiGraphics guiGraphics, int x, int y,
            boolean blinking, boolean hardcore,
            CHeartType healthType, CHeartType absorbingType
    ) {
        HeartRenderEvent.Post event = new HeartRenderEvent.Post(guiGraphics, x, y, blinking, hardcore, healthType, absorbingType);
        FabHeartRenderEvent.POST.invoker().accept(event);
    }

}
