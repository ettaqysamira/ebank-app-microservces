package net.ettaqy.ebankbot.agents;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;

@Service
public class EBankAiAgent {

    private final ChatClient chatClient;

    public EBankAiAgent(ChatClient.Builder builder, ToolCallbackProvider toolCallbackProvider) {
        ChatMemory chatMemory = MessageWindowChatMemory.builder().build();
        this.chatClient = builder
                .defaultSystem("You are an assistant that answers user questions based on the provided context about customers and bank accounts. If no context is provided, reply with: I don't know.")
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .defaultTools(toolCallbackProvider)
                .build();
    }

    public String chat(Prompt prompt) {
        return chatClient.prompt(prompt)
                .call()
                .content();
    }
}
