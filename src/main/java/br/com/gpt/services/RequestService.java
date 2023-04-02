package br.com.gpt.services;

import br.com.gpt.dtos.MessageResponseDTO;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class RequestService {

    OpenAiService service = new OpenAiService("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");

    public MessageResponseDTO translateMessage(String messageForTranslate, String languageForTranslate) {
        var messages = new ArrayList<String>();

        service.createCompletion(
                builderCompletionRequest(
                        String.format(
                                "Translate the following message into %s:\n%s",
                                languageForTranslate,
                                messageForTranslate)))
                .getChoices().forEach(txt -> messages.add(txt.getText()));

        return builderMessage(messages);
    }

    public MessageResponseDTO resumeMessage(String messageForResume) {
        var messages = new ArrayList<String>();

        service.createCompletion(
                        builderCompletionRequest(
                                String.format(
                                        "Create a summary for the text below:\n%s",
                                        messageForResume)))
                .getChoices().forEach(txt -> messages.add(txt.getText()));

        return builderMessage(messages);
    }

    private MessageResponseDTO builderMessage(List<String> messages) {
        AtomicReference<String> x = new AtomicReference<>("");

        messages.forEach(m -> {
            x.set(String.format("%s %s\n", x.get(), m));
        });

        return MessageResponseDTO.builder().response(x.get()).build();
    }
    private CompletionRequest builderCompletionRequest(String prompt) {
        return CompletionRequest.builder()
                .prompt(prompt)
                .model("text-davinci-003")
                .echo(false)
                .temperature(0.0)
                .maxTokens(1000)
                .stream(false)
                .build();
    }
}
