package net.ettaqy.ebankbot.controllers;


import net.ettaqy.ebankbot.agents.EBankAiAgent;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EBankChatBotController {

    private final EBankAiAgent ebankAiAgent;

    public EBankChatBotController(EBankAiAgent ebankAiAgent) {
        this.ebankAiAgent = ebankAiAgent;
    }

    @GetMapping(value = "/chat", produces = MediaType.TEXT_PLAIN_VALUE)
    public String chat(@RequestParam(defaultValue = "Bonjour") String query) {
        return ebankAiAgent.chat(new Prompt(query));
    }
}