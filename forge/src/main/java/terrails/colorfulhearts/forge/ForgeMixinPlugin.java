package terrails.colorfulhearts.forge;

import net.minecraftforge.fml.loading.LoadingModList;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import terrails.colorfulhearts.CColorfulHearts;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForgeMixinPlugin implements IMixinConfigPlugin {

    private static final Pattern MOD_ID_PATTERN = Pattern.compile("^terrails\\.colorfulhearts\\.forge\\.mixin\\.compat\\.([a-z0-9_-]+)\\.([a-zA-Z]+)$");

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        Matcher matcher = MOD_ID_PATTERN.matcher(mixinClassName);
        if (matcher.matches()) {
            String modid = matcher.group(1);
            String className = matcher.group(2);
            if (LoadingModList.get().getModFileById(modid) != null) {
                CColorfulHearts.LOGGER.info("Applying mixin {} as mod {} is present.", className, modid);
                return true;
            } else {
                CColorfulHearts.LOGGER.debug("Skipped applying mixin {} as mod {} is not present.", className, modid);
                return false;
            }
        }
        return true;
    }

    // unneeded
    @Override
    public void onLoad(String mixinPackage) { }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) { }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) { }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) { }
}
