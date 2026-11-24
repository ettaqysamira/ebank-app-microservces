package net.ettaqy.ebankbot.telegram;

import jakarta.annotation.PostConstruct;
import net.ettaqy.ebankbot.agents.EBankAiAgent;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.ActionType;
import org.telegram.telegrambots.meta.api.methods.send.SendChatAction;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final EBankAiAgent ebankAiAgent;

    @Value("${telegram.bot.token}")
    private String botToken;

    public TelegramBot(EBankAiAgent ebankAiAgent) {
        this.ebankAiAgent = ebankAiAgent;
    }

    @PostConstruct
    public void registerBot() {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(this);
        } catch (TelegramApiException e) {
            throw new RuntimeException("Erreur d'enregistrement du bot Telegram", e);
        }
    }

    @Override
    public String getBotUsername() {
        return "EBankBot";
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Long chatId = update.getMessage().getChatId();
            String query = update.getMessage().getText();

            try {
                SendChatAction typingAction = SendChatAction.builder()
                        .chatId(chatId.toString())
                        .action(ActionType.TYPING.toString())
                        .build();
                execute(typingAction);

                String response = ebankAiAgent.chat(new Prompt(query));

                SendMessage message = SendMessage.builder()
                        .chatId(chatId.toString())
                        .text(response)
                        .build();
                execute(message);
            } catch (TelegramApiException e) {
                throw new RuntimeException("Erreur lors de l'envoi de la réponse Telegram", e);
            }
        }
    }
}