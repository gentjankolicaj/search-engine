package org.search.engine.console.message;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message<H, B> {
    private H header;
    private B body;


}
