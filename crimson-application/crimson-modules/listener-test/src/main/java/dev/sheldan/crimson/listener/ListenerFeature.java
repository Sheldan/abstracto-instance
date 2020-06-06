package dev.sheldan.crimson.listener;

import dev.sheldan.abstracto.core.config.FeatureEnum;
import lombok.Getter;

@Getter
public enum ListenerFeature implements FeatureEnum {
    EXAMPLE_FEATURE("exampleFeature");

    private String key;

    ListenerFeature(String key) {
        this.key = key;
    }

}
