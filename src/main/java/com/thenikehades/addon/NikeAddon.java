package com.thenikehades.addon;

import com.thenikehades.addon.modules.*;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.systems.modules.Modules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author TheNikeHades
 * Project: TheNikeHades-AutoDupe-Addon
 */
public class NikeAddon extends MeteorAddon {
    public static final Logger LOG = LoggerFactory.getLogger("TheNikeHades-AutoDupe");

    @Override
    public void onInitialize() {
        LOG.info("TheNikeHades-AutoDupe-Addon 1.21.4 başlatılıyor... Başarılar TheNikeHades!");
        
        // Modülleri Meteor sistemine kaydet
        Modules.get().add(new NikeDupe());
    }

    @Override
    public String getPackage() {
        return "com.thenikehades.addon";
    }
}
