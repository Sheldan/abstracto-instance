package dev.sheldan.crimson.listener;

import dev.sheldan.abstracto.core.config.FeatureConfig;
import dev.sheldan.abstracto.core.config.FeatureEnum;
import org.springframework.stereotype.Component;

@Component
public class ExampleFeatureConfig implements FeatureConfig {
    @Override
    public FeatureEnum getFeature() {
        return ListenerFeature.EXAMPLE_FEATURE;
    }
}
