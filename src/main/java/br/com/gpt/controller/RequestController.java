package br.com.gpt.controller;

import br.com.gpt.dtos.MessageResponseDTO;
import br.com.gpt.dtos.ResumeMessageRequestDTO;
import br.com.gpt.dtos.TranslateMessageRequestDTO;
import br.com.gpt.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/rest", produces = MediaType.APPLICATION_JSON_VALUE)
public class RequestController {

    @Autowired
    RequestService translateService;

    @GetMapping("/translate")
    public ResponseEntity<MessageResponseDTO> translateMessage(@RequestBody TranslateMessageRequestDTO translateMessageRequestDTO) {
        var translateMessage = translateService.translateMessage(
                translateMessageRequestDTO.getMessageForTranslate(),
                translateMessageRequestDTO.getLanguageForTranslate());

        return ResponseEntity.ok(translateMessage);
    }

    @GetMapping("/resume")
    public ResponseEntity<MessageResponseDTO> resumeMessage(@RequestBody ResumeMessageRequestDTO resumeMessageRequestDTO) {
        var resumeMessage = translateService.resumeMessage(resumeMessageRequestDTO.getMessageForResume());

        return ResponseEntity.ok(resumeMessage);
    }
}
