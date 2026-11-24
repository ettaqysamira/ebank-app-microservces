package net.ettaqy.ebankbot.discord;

import net.ettaqy.ebankbot.agents.EBankAiAgent;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Component;

@Component
public class DiscordBot {

    private final EBankAiAgent ebankAiAgent;

    public DiscordBot(EBankAiAgent ebankAiAgent) {
        this.ebankAiAgent = ebankAiAgent;
    }

}