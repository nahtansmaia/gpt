package br.com.gpt.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TranslateMessageRequestDTO {

    private String messageForTranslate;
    private String languageForTranslate;
}
