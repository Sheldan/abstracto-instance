package dev.sheldan.crimson.listener;

import dev.sheldan.abstracto.core.config.FeatureEnum;
import dev.sheldan.abstracto.core.listener.MessageReceivedListener;
import net.dv8tion.jda.api.entities.Message;
import org.springframework.stereotype.Component;

@Component
public class WordListener implements MessageReceivedListener {
    @Override
    public void execute(Message message) {
        if(message.getContentRaw().contains("trigger word")) {
            message.getTextChannel().sendMessage("answer").queue();
        }
    }

    @Override
    public FeatureEnum getFeature() {
        return ListenerFeature.EXAMPLE_FEATURE;
    }
}
